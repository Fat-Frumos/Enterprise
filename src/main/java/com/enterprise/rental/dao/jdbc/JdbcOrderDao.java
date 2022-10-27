package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.factory.DbManager;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

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

    private Optional<User> getOrderByUserName(String name) {
        return Optional.empty();
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
        return new ArrayList<>();
    }

    //TODO MAPPER
    private boolean setOrderQuery(Order order) {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = DbManager.getInstance().getConnection();


        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_ORDER_SQL);

//            boolean driver = order.isDriver();
//            boolean rejected = order.isRejected();
//            boolean closed = order.isClosed();
//            int day = order.getDay();
//            double payment = order.getPayment();
            Timestamp create = new Timestamp(System.currentTimeMillis());
//            Timestamp end = order.getEnded();
//            String damage = order.getDamage();
            String passport = order.getPassport();

            log.info(order);

            statement.setLong(1, order.getOrderId());
            statement.setLong(2, order.getUserId());
            statement.setLong(3, order.getCarId());
            statement.setTimestamp(4, create);
            statement.setString(5, passport);

//            statement.setInt(4, day);
//            statement.setDouble(5, payment);
//            statement.setBoolean(6, driver);
//            statement.setBoolean(7, rejected);
//            statement.setBoolean(8, closed);

//            statement.setTimestamp(10, end);
//            statement.setString(11, damage);

            int i = statement.executeUpdate();
            connection.commit();
            return true;
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


