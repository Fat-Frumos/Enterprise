package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Connections.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcOrderDao implements OrderDao {

    private static final OrderMapper ORDER_ROW_MAPPER = new OrderMapper();

    private static final Logger log = Logger.getLogger(JdbcOrderDao.class);

    @Override
    public List<Order> findAll() {
        return getOrdersQuery(FIND_ALL_ORDERS_SQL);
    }

    @Override
    public List<Order> findAll(String id) {
        return id
                != null
                ? findAll(Long.parseLong(id))
                : new ArrayList<>();
    }

    public List<Order> findAll(long userId) {
        String query = String.format("%s%s",
                FILTER_ORDER_BY_USER_ID_SQL, userId);
        log.info(query);
        return getOrdersQuery(query);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return getOrderById(id);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean save(Order order) {
        return setOrderQuery(order);
    }

    @Override
    public Order edit(Order order) {
        String damage = order.getDamage();
        String reason = order.getReason();
        double payment = order.getPayment();
        boolean rejected = order.isRejected();
        boolean closed = order.isClosed();
        if (damage == null) {
            damage = "";
        }
        String query = String.format(
                UPDATE_ORDER_SQL,
                damage, reason, payment, rejected, closed, order.getOrderId());

        log.info(String.format("%s%n%s", order, query));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(query);
            boolean update = statement.executeUpdate() > 0;
            log.info(update);
            connection.commit();
            return order;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection, statement);
        }
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    private static Optional<Order> getOrder(
            ResultSet resultSet) throws SQLException {

        return !resultSet.next()
                ? Optional.empty()
                : Optional.of(ORDER_ROW_MAPPER.mapRow(resultSet));
    }

    private Optional<Order> getOrderById(Long id) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL);
            statement.setLong(1, id);
            return getOrder(statement.executeQuery());

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            throw new DataException(sqlException);
        } finally {
            eventually(connection, statement);
        }
    }

    private List<Order> getOrdersQuery(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            if (resultSet.next()) {
                while (resultSet.next()) {
                    Order order = ORDER_ROW_MAPPER.mapRow(resultSet);
                    orders.add(order);
                    log.info(order);
                }
                return orders;
            }

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement, resultSet);
        }
        return orders;
    }




    private boolean setOrderQuery(Order order) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(INSERT_ORDER_SQL);
            setOrders(order, statement);
            boolean execute = statement.execute();

            connection.commit();
            return execute;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement);
        }
        return false;
    }

    private static void setOrders(
            Order order,
            PreparedStatement statement)
            throws SQLException {

        boolean driver = order.isDriver();
        Timestamp create = new Timestamp(System.currentTimeMillis());
        String passport = order.getPassport();
        String reason = order.getReason();
        String card = order.getPhone();
        double payment = order.getPayment();
        Timestamp term = order.getTerm();
        log.info(order);

        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL);
        statement.setLong(2, order.getUserId());
        statement.setLong(3, order.getCarId());
        statement.setTimestamp(4, create);
        statement.setString(5, passport);
        statement.setString(6, reason);
        statement.setString(7, card);
        statement.setDouble(8, payment);
        statement.setTimestamp(9, term);
        statement.setBoolean(10, driver);
    }
}
