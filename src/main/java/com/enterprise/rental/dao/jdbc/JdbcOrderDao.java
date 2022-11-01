package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.factory.DbManager;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.enterprise.rental.dao.factory.DbManager.getInstance;
import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcOrderDao implements OrderDao {

    private static final OrderMapper ORDER_ROW_MAPPER = new OrderMapper();

    private static final Logger log = Logger.getLogger(JdbcOrderDao.class);

    @Override
    public List<Order> findAll() {
        return getOrderQuery(FIND_ALL_ORDERS_SQL);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return getOrderById(id);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return Optional.empty();
    }

    private Optional<Order> getOrderById(Long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ORDER_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            //TODO
        }
    }

    @Override
    public boolean save(Order order) {

        return setOrderQuery(order);
    }

    @Override
    public Order edit(Order order) {
        return new Order();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Order> findAll(String params) {
        String sql = String.format("%sWHERE %s;", FILTER_ORDER_BY_SQL, params);
        return getOrderQuery(sql);
    }

    private List<Order> getOrderQuery(String sql) {
        Connection connection;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {

                connection.commit();
                List<Order> orders = new ArrayList<>();

                if (resultSet.next()) {
                    while (resultSet.next()) {
                        Order order = ORDER_ROW_MAPPER.mapRow(resultSet);
                        orders.add(order);
                    }
                    return orders;
                }
                throw new OrderNotFoundException("Order not found");
            }
        } catch (SQLException ex) {
            throw new OrderNotFoundException(ex.getMessage());
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                log.info(String.format("Connection not closed: %s", e.getMessage()));
            }
        }
    }


    //TODO MAPPER
    private boolean setOrderQuery(Order order) {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = DbManager.getInstance().getConnection();


        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_ORDER_SQL);

            boolean driver = order.isDriver();

            Timestamp create = new Timestamp(System.currentTimeMillis());

            String passport = order.getPassport();
            String card = order.getCard();
            Double payment = order.getPayment();
            Timestamp term = order.getTerm();

            log.info(order);

            statement.setLong(1, order.getOrderId());
            statement.setLong(2, order.getOrderId());
            statement.setLong(3, order.getCarId());
            statement.setTimestamp(4, create);
            statement.setString(5, passport);
            statement.setString(6, card);
            statement.setDouble(7, payment);
            statement.setTimestamp(8, term);
            statement.setBoolean(9, driver);

            boolean execute = statement.execute();
            connection.commit();
            return execute;
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback();
            } catch (SQLException ex) {
                log.info("rollback");
                throw new DataException(ex.getMessage());
            }
            log.info(String.format("%s Order can`t be added, maybe order already exists", order.getOrderId()));
            throw new DataException(e);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                log.info(String.format("Connection not closed: %s", e.getMessage()));
            }
        }
    }
}
