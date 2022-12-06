package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
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
        log.debug(query);
        return getOrdersQuery(query);
    }

    public boolean save(Invoice invoice) {
        long id = invoice.getInvoiceId();
        long userId = invoice.getUserId();
        long carId = invoice.getCarId();
        String damage = invoice.getDamage();
        String passport = invoice.getPassport();
        String phone = invoice.getPhone();
        String reason = invoice.getReason();
        double payment = invoice.getPayment();
        String query = String.format("INSERT INTO invoices (invoice_id, user_id, car_id, damage, payment, reason, passport, phone) VALUES (%d, %d, %d, '%s', %f,'%s','%s','%s');",
                id,
                userId,
                carId,
                damage,
                payment,
                reason,
                passport,
                phone);

        log.debug(String.format("%s", query));

        return templateOrder(query);
    }

    private static boolean templateOrder(String query) {
        boolean execute = false;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            execute = statement.execute();
            connection.commit();

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);

        } finally {
            eventually(connection, statement);
        }
        return execute;
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

        log.debug(String.format("%s%n%s", order, query));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            boolean update = statement.executeUpdate() > 0;
            log.debug(String.format("%s %s", update, query));
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

        String query = String.format("%s%d", DELETE_ORDER_SQL, id);
        log.debug(query);
        return templateOrder(query);
    }

    private static Optional<Order> getOrder(
            ResultSet executeSet) throws SQLException {
        return !executeSet.next()
                ? Optional.empty()
                : Optional.of(ORDER_ROW_MAPPER.mapRow(executeSet));
    }

    private Optional<Order> getOrderById(Long id) {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = String.format("%s%d", FILTER_ORDER_BY_ID_SQL, id);
        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL);

            statement.setLong(1, id);
            log.debug(query);
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
        ResultSet executeSet = null;
        List<Order> orders = new ArrayList<>();
        log.debug(String.format("%s%s%s", YELLOW, query, RESET));
        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            executeSet = statement.executeQuery();
            connection.commit();

            if (executeSet.next()) {
                while (executeSet.next()) {
                    Order order = ORDER_ROW_MAPPER.mapRow(executeSet);
                    orders.add(order);
                }
                log.debug(String.format("%d order(s)", orders.size()));
                return orders;
            }

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
        } finally {
            eventually(connection, statement, executeSet);
        }
        return orders;
    }


    private boolean setOrderQuery(Order order, String query) {
        boolean execute = false;
        log.debug(String.format("Query: %s", query));
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            setOrders(order, statement);
            execute = statement.execute();
            connection.commit();

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);

        } finally {
            eventually(connection, statement);
        }
        return execute;
    }

    private static void setOrders(
            Order order,
            PreparedStatement statement)
            throws SQLException {

        boolean driver = order.isDriver();
        Timestamp create = Timestamp.valueOf(LocalDateTime.now());
        String passport = order.getPassport();
        String reason = order.getReason();
        double payment = order.getPayment();
        Timestamp term = order.getTerm();
        log.debug(String.valueOf(order));

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
