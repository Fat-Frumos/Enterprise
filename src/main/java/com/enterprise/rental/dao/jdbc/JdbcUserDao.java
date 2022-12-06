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

import static com.enterprise.rental.dao.jdbc.Connections.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.DbManager.getInstance;
import static com.enterprise.rental.service.UserService.addSalt;
import static com.enterprise.rental.service.UserService.saltedPassword;

public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();

    private static final Logger log = Logger.getLogger(JdbcUserDao.class);

    @Override
    public Optional<User> findByName(String name) {
        if (name != null) {
            String sql = String.format("%s'%s'",
                    FILTER_BY_NAME_SQL, name);
            Optional<User> user = getUserSql(sql);
            log.debug(String.format("%s %s %s", YELLOW, sql, RESET));
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

        log.debug(String.format("%s%n%s", user, query));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);

            boolean update = statement.executeUpdate() > 0;
            log.debug(String.valueOf(update));
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
            log.debug("Connection closed");
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
            connection = getConfigConnection();
            statement = connection.prepareStatement(USERS_SQL);
            resultSet = statement.executeQuery();
            connection.commit();
            return getUser(users, resultSet);

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, USERS_SQL);
            log.debug(String.format("Customer not found %s",
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

        log.debug(String.format("%s%s%s", YELLOW, query, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
            log.debug("rollback (connection)");

        } finally {
            eventually(connection, statement, resultSet);
//            log.debug("closed (connection)");
        }
        return Optional.empty();
    }

    private boolean setUserQuery(@NotNull User user) throws DataException {

        Connection connection = null;
        PreparedStatement statement = null;
        boolean execute = false;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(INSERT_USER_SQL);
            log.debug(String.format("%s%s%s", YELLOW, INSERT_USER_SQL, RESET));
            connection.commit();
            setUsers(user, statement);
            execute = statement.execute();
            connection.commit();
        } catch (SQLException sqlException) {
            rollback(connection, sqlException, INSERT_USER_SQL);
            log.debug(String.format("%s User can`t added", user.getName()));
        } finally {
            eventually(connection, statement);
        }
        return execute;
    }

    private static void setUsers(
            @NotNull User user,
            PreparedStatement statement) throws SQLException {

        String salt = addSalt(user);
        String sha256Hex = saltedPassword(user.getPassword(), salt);
        String email = user.getEmail();
        String role = user.getRole();
        String language = user.getLanguage() == null || user.getLanguage().equals("") ? "en" : user.getLanguage();
        String phone = user.getPhone();
        String passport = user.getPassport();
        log.debug(String.format("salted password:%s%n rawPassword: %s%n salt:%s", sha256Hex, user.getPassword(), user.getSalt()));
        log.debug(String.format("%s%n%s", INSERT_USER_SQL, user.getName()));
        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7fffL);
        statement.setString(2, user.getName());
        statement.setString(3, email);
        statement.setString(4, sha256Hex);
        statement.setString(5, passport);
        statement.setString(6, phone);
        statement.setString(7, role);
        statement.setBoolean(8, true);
        statement.setBoolean(9, false);
        statement.setString(10, language);
        statement.setString(11, salt);
    }
}
