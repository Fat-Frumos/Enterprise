package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.DbManager.getInstance;
import static com.enterprise.rental.dao.jdbc.Connections.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();

    private static final Logger log =
            Logger.getLogger(JdbcUserDao.class);

    @Override
    public Optional<User> findByName(String name) {
        if (name != null) {
            String sql = String.format("%s'%s'",
                    FILTER_BY_NAME_SQL, name);
            Optional<User> user = getUserSql(sql);
            log.info(sql + " " + user);
            return user;
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return findAll(USERS_SQL);
    }

    @Override
    public boolean save(User user) {
        return setUserQuery(user);
    }

    @Override
    public User edit(User user) {

        String query = getQuery(user);

        log.info(String.format("%s%n%s", user, query));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query);

            boolean update = statement.executeUpdate() > 0;
            log.info(update);
            connection.commit();
            return user;

        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
            rollback(connection, sqlException, query);

        } finally {
            eventually(connection, statement);
        }
        return user;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<User> findAll(String query) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getInstance().getConnection();
            statement = connection.prepareStatement(query);
            return getUsers(connection, statement, new ArrayList<>());

        } catch (SQLException sqlException) {
            log.error(sqlException.getMessage());
            rollback(connection, sqlException, query);

        } finally {
            eventually(connection, statement);
            log.info("Connection closed");
        }
        return new ArrayList<>();
    }

    private static String getQuery(User user) {
        String name = user.getName();
        String role = user.getRole();
        boolean active = user.isActive();
        boolean closed = user.isClosed();

        return String.format(UPDATE_USER_SQL,
                name, role, active, closed, user.getUserId());
    }

    private static List<User> getUsers(
            Connection connection,
            PreparedStatement statement,
            List<User> users) {

        ResultSet resultSet = null;

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(USERS_SQL);
            resultSet = statement.executeQuery();
            connection.commit();
            return getUser(users, resultSet);

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, USERS_SQL);
            log.info(String.format("Customer not found %s",
                    sqlException.getMessage()));
        } finally {
            eventually(connection, statement, resultSet);
        }
        return users;
    }

    private static List<User> getUser(
            List<User> users,
            ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            while (resultSet.next()) {
                User user = ROW_MAPPER.mapRow(resultSet);
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FILTER_USER_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    private Optional<User> getUserSql(String query) {
        log.info(query);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
            log.info("rollback (connection)");

        } finally {
            eventually(connection, statement, resultSet);
            log.info("closed (connection)");
        }
        return Optional.empty();
    }

    private boolean setUserQuery(@NotNull User user) throws DataException {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getWithoutAutoCommit();
            statement = connection.prepareStatement(INSERT_USER_SQL);
            log.info(String.format("%s", INSERT_USER_SQL));
            connection.commit();
            setUsers(user, statement);
            boolean execute = statement.execute();
            connection.commit();
            return execute;
        } catch (SQLException sqlException) {
            rollback(connection, sqlException, INSERT_USER_SQL);
            log.info(String.format("%s User can`t added", user.getName()));
        } finally {
            eventually(connection, statement);
        }
        return false;
    }

    private static void setUsers(
            @NotNull User user,
            PreparedStatement statement) throws SQLException {
        String password = user.getPassword();
        String email = user.getEmail();
        String role = user.getRole();
        String language = user.getLanguage();
        log.info(INSERT_USER_SQL + "\n" + user);
        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7fffL);
        statement.setString(2, user.getName());
        statement.setString(3, email);
        statement.setString(4, password);
        statement.setString(5, role);
        statement.setBoolean(6, true);
        statement.setBoolean(7, false);
        statement.setString(8, language);
        statement.setString(9, user.getSalt());
    }
}
