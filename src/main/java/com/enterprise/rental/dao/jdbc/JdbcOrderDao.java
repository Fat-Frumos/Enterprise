package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.DbManager;
import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.enterprise.rental.dao.DbManager.getInstance;
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
    public List<Order> findAll(String param) {
        long userId = Long.parseLong(param);
        return getOrderQuery(String.format("%s%d", FILTER_ORDER_BY_USER_ID_SQL, userId));
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
        } catch (SQLException sqlException) {
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection);
        }
    }

    private boolean setOrderQuery(Order order) {
        //TODO: implement PreparedStatement gret + set
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DbManager.getInstance().getConnection();
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
            statement.setLong(2, order.getUserId());
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
        } catch (SQLException sqlException) {
            return caught(connection, sqlException);
        } finally {
            return eventually(connection);
        }
    }

    private static boolean eventually(Connection connection) {
        try {
            Objects.requireNonNull(connection).close();
            return false;
        } catch (SQLException e) {
            log.info(String.format("Connection not closed: %s", e.getMessage()));
            throw new DataException(e);
        }
    }

    private static boolean caught(Connection connection, SQLException sqlException) {
        try {
            Objects.requireNonNull(connection).rollback();
            throw new DataException("Rollback", sqlException);
        } catch (SQLException exception) {
            throw new DataException("Can`t roll back", exception);
        }
    }
}
