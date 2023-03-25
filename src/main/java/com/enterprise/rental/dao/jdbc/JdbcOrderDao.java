package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.DbManager.*;

/**
 * Jdbc Java class that represent an implementing OrderDao
 * performing CRUD operations on instances of {@link Order}.
 * JdbcOrderDao the Java API that manages connecting to a database,
 * issuing queries and commands, and handling result sets obtained from the database.
 *
 * @author Pasha Polyak
 * @see OrderDao
 */
public class JdbcOrderDao implements OrderDao {

    private static final OrderMapper ORDER_ROW_MAPPER = new OrderMapper();

    private static final Logger LOGGER = LogManager.getLogger();

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
        LOGGER.log( Level.INFO, query);
        return getOrdersQuery(query);
    }

    public boolean save(Invoice invoice) {
        Long id = invoice.getInvoiceId();
        long userId = invoice.getUserId();
        long carId = invoice.getCarId();
        String damage = invoice.getDamage();
        String passport = invoice.getPassport();
        String phone = invoice.getPhone();
        String reason = invoice.getReason();
        double payment = invoice.getPayment();
        String query = String.format("%s VALUES (%d, %d, %d, '%s', %f,'%s','%s','%s');",
                INSERT_INVOICE,
                id,
                userId,
                carId,
                damage,
                payment,
                reason,
                passport,
                phone);

        LOGGER.log( Level.INFO, "{}{}", YELLOW, query);

        return templateOrder(query);
    }

    /**
     * @return
     */
    @Override
    public List<Invoice> findAllInvoices() {
        String query = "SELECT * FROM invoices";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet executeSet = null;
        List<Invoice> orders = new ArrayList<>();
        LOGGER.log( Level.INFO, "{}{}{}", YELLOW, query, RESET);
        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            executeSet = statement.executeQuery();
            connection.commit();

            if (executeSet.next()) {
                while (executeSet.next()) {
                    Order order = ORDER_ROW_MAPPER.mapRow(executeSet);
//                    orders.add(order);
                }
                LOGGER.log( Level.INFO, "{} order(s)", orders.size());
                return orders;
            }

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement, executeSet);
        }
        return orders;
    }

    private static boolean templateOrder(String query) {
        boolean execute = false;

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            execute = statement.execute();
            connection.commit();

        } catch (SQLException sqlException) {
            LOGGER.log( Level.INFO, "{}{}", YELLOW, query);
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement);
        }
        return execute;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return getOrderById(id);
    }

    /**
     * Find User by Order.
     *
     * @param name the Order
     * @return {@code Optional<User>}, if a value is present, otherwise {@code Optional.empty()}.
     */
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

        LOGGER.log( Level.INFO, "{}{}{}%n{}{}", PURPLE, order, YELLOW, query, RESET);

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            boolean update = statement.executeUpdate() > 0;
            LOGGER.log( Level.INFO, "{} {}", update, query);
            connection.commit();
            return order;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            LOGGER.log( Level.INFO, "{}{}{}", YELLOW, query, RESET);
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection, statement);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = String.format("%s%d", DELETE_ORDER_SQL, id);
        LOGGER.log( Level.INFO, "{}{}{}", RED, query, RESET);
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
            connection = proxyConnection();
            statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL);

            statement.setLong(1, id);
            return getOrder(statement.executeQuery());

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            throw new DataException(sqlException);
        } finally {
            LOGGER.log( Level.INFO, "{}{}{}", YELLOW, query, RESET);
            eventually(connection, statement);
        }
    }

    private List<Order> getOrdersQuery(String query) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet executeSet = null;
        List<Order> orders = new ArrayList<>();
        LOGGER.log( Level.INFO, "{}{}{}", YELLOW, query, RESET);
        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            executeSet = statement.executeQuery();
            connection.commit();

            if (executeSet.next()) {
                while (executeSet.next()) {
                    Order order = ORDER_ROW_MAPPER.mapRow(executeSet);
                    orders.add(order);
                }
                LOGGER.log( Level.INFO, "{} order(s)", orders.size());
                return orders;
            }

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement, executeSet);
        }
        return orders;
    }


    private boolean setOrderQuery(Order order, String query) {
        boolean execute = false;
        LOGGER.log( Level.INFO, "{}Query: {}{}", YELLOW, query, RESET);
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            setOrders(order, statement);
            execute = statement.execute();
            connection.commit();

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);

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
        LOGGER.log( Level.INFO, "{}", order);

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
