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
        Connection connection = getConnection();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(FILTER_ORDER_BY_ID_SQL);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(ORDER_ROW_MAPPER.mapRow(resultSet));
        } catch (
                SQLException e) {
            throw new DataException(e);
        } finally {
            eventually(connection);
        }

    }

    @Override
    public boolean save(Order order) {
        return setOrderQuery(order);
    }

    @Override
    public Order edit(Order order) {
        Connection connection = getConnection();

        String damage = order.getDamage();
        double payment = order.getPayment();
        boolean rejected = order.isRejected();
        boolean closed = order.isClosed();

        String query = String.format(UPDATE_ORDER_SQL, damage, payment, rejected, closed, order.getOrderId());
        log.info(order);
        log.info(query);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            boolean update = statement.executeUpdate() > 0;
            log.info(update);
            connection.commit();
            return order;
        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            return new Order();
        } finally {
            eventually(connection);
        }
    }

    @Override
    public boolean delete(long id) {
        //TODO delete
        return false;
    }

    @Override
    public List<Order> findAll(String param) {
        long userId = Long.parseLong(param);
        String query = String.format("%s%d",
                FILTER_ORDER_BY_USER_ID_SQL, userId);
        log.info(query);
        return getOrderQuery(query);
    }

    private List<Order> getOrderQuery(String sql) {
        Connection connection = getConnection();
        try {
            PreparedStatement statement =
                    connection.prepareStatement(sql);

            try (ResultSet resultSet = statement.executeQuery()) {

                connection.commit();
                List<Order> orders = new ArrayList<>();

                if (resultSet.next()) {
                    return getOrderList(resultSet, orders);
                } else {
                    throw new OrderNotFoundException("Order not found");
                }
            }
        } catch (SQLException sqlException) {
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection);
        }
    }

    private static List<Order> getOrderList(
            ResultSet resultSet,
            List<Order> orders)
            throws SQLException {
        while (resultSet.next()) {
            Order order = ORDER_ROW_MAPPER.mapRow(resultSet);
            orders.add(order);
        }
        return orders;
    }

    private static Connection getConnection() {
        Connection connection;
        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataException(e);
        }
        return connection;
    }

    private boolean setOrderQuery(Order order) {

        Connection connection = getConnection();
        try {
            PreparedStatement prepareStatement =
                    connection.prepareStatement(INSERT_ORDER_SQL);

            setOrders(order, prepareStatement);

            boolean execute = prepareStatement.execute();
            connection.commit();
            return execute;
        } catch (SQLException sqlException) {
            return rollback(connection, sqlException);
        } finally {
            eventually(connection);
        }
    }

    private static void setOrders(Order order, PreparedStatement statement) throws SQLException {
        boolean driver = order.isDriver();
        Timestamp create = new Timestamp(System.currentTimeMillis());
        String passport = order.getPassport();
        String card = order.getPhone();
        double payment = order.getPayment();
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

    private static boolean rollback(Connection connection, SQLException sqlException) {
        try {
            Objects.requireNonNull(connection).rollback();
            throw new DataException("Rollback", sqlException);
        } catch (SQLException exception) {
            throw new DataException("Can`t roll back", exception);
        }
    }
}
