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
        return setOrderQuery(order, INSERT_ORDER_SQL);
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
            log.info(String.format("%s%s", update, query));
            connection.commit();
            return order;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection, statement);
        }
    }

    @Override
    public boolean delete(long id) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = String.format("%s%d", DELETE_ORDER_SQL, id);
        log.info(query);

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(query);
            statement.execute();
            connection.commit();
            return true;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
        } finally {
            eventually(connection, statement);
        }
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
        String query = String.format("%s%d", FILTER_ORDER_BY_ID_SQL, id);
        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL);

            statement.setLong(1, id);
            log.info(query);
            return getOrder(statement.executeQuery());

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
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
        log.info(String.format("%s", query));
        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            if (resultSet.next()) {
                while (resultSet.next()) {
                    Order order = ORDER_ROW_MAPPER.mapRow(resultSet);
                    orders.add(order);
                }
                log.info(String.format("%d order(s)", orders.size()));
                return orders;
            }

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
        } finally {
            eventually(connection, statement, resultSet);
        }
        return orders;
    }


    private boolean setOrderQuery(Order order, String query) {
        log.info(String.format(" sql: %s", query));
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(query);
            setOrders(order, statement);
            boolean execute = statement.execute();

            connection.commit();
            return execute;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
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
        double payment = order.getPayment();
        Timestamp term = order.getTerm();
        log.info(order);

        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL);
        statement.setLong(2, order.getUserId());
        statement.setLong(3, order.getCarId());
        statement.setDouble(4, payment);
        statement.setBoolean(5, driver);
        statement.setBoolean(6, order.isRejected());
        statement.setBoolean(7, order.isClosed());
        statement.setTimestamp(8, create);
        statement.setString(9, order.getDamage());
        statement.setString(10, passport);
        statement.setString(11, order.getPhone());
        statement.setTimestamp(12, term);
        statement.setString(13, reason);
    }
}
