package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.enterprise.rental.dao.DbManager.getInstance;
import static com.enterprise.rental.dao.jdbc.Connections.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();

    private static final Logger log = Logger.getLogger(JdbcUserDao.class);

    @Override
    public Optional<User> findByName(@NotNull String name) {

        String sql = String.format("%s'%s'",
                FILTER_BY_NAME_SQL, name);
        Optional<User> user = getUserSql(sql);
        log.info(sql + " " + user);
        return user;
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
        String name = user.getName();
        String role = user.getRole();
        boolean active = user.isActive();
        boolean closed = user.isClosed();
        String query = String.format(UPDATE_USER_SQL, name, role, active, closed, user.getUserId());
        log.info(user);
        log.info(query);

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
            rollback(connection, sqlException);
        } finally {
            close(statement);
            close(connection);
        }
        return new User();
    }

//    private boolean close(PreparedStatement statement) {
//        try {
//            Objects.requireNonNull(statement).close();
//            return true;
//        } catch (SQLException e) {
//            throw new DataException(e);
//        }
//    }
//
//    private static boolean close(Connection connection) {
//        try {
//            Objects.requireNonNull(connection).close();
//            return true;
//        } catch (SQLException e) {
//            log.info(String.format("Connection not closed: %s", e.getMessage()));
//            throw new DataException(e);
//        }
//    }
//
//    private static boolean rollback(
//            Connection connection,
//            SQLException sqlException) {
//        try {
//            Objects.requireNonNull(connection).rollback();
//            throw new DataException("Rollback", sqlException);
//        } catch (SQLException exception) {
//            throw new DataException("Can`t rollback", exception);
//        }
//    }

    @Override
    public boolean delete(long id) {
        //TODO Auto-generated delete method
        return false;
    }

    @Override
    public List<User> findAll(String sql) {

        Connection connection = null;
        PreparedStatement statement;
        ResultSet resultSet;

        try {
            connection = getInstance().getConnection();
            statement = connection.prepareStatement(sql);
            try {
                resultSet = statement.executeQuery();
                connection.setAutoCommit(false);
                connection.commit();
                List<User> users = new ArrayList<>();

                if (resultSet.next()) {
                    while (resultSet.next()) {
                        User user = ROW_MAPPER.mapRow(resultSet);
                        users.add(user);
                    }
                    return users;
                }
                throw new UserNotFoundException("Customer not found");
            } catch (SQLException sqlException) {
                throw new UserNotFoundException("Customer not found", sqlException);
            }
        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            close(connection);
            throw new UserNotFoundException(
                    sqlException.getMessage());
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                throw new DataException("Connection not closed", e);
            }
        }
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

    private Optional<User> getUserSql(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            try {
                Objects.requireNonNull(connection).rollback();
                log.info("rollback (connection)");
            } catch (SQLException exception) {
                log.info(exception.getMessage());
                throw new DataException(exception);
            }
            log.info(sqlException.getMessage());
            throw new DataException(sqlException.getMessage());

        } finally {
            eventually(connection, statement, resultSet);
        }
    }

    private boolean setUserQuery(@NotNull User user) throws DataException {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = getInstance().getConnection();
        String userName = user.getName();

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_USER_SQL);

            String password = user.getPassword();
            String email = user.getEmail();
            String role = user.getRole();
            log.info(user);
            statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7fffL);
            statement.setString(2, userName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, role);
            statement.setBoolean(6, true);
            statement.setBoolean(7, false);
            statement.setString(8, "en");
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
            log.info(String.format("%s User can`t added", userName));
            throw new DataException(e);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                String message = (String.format("Connection not closed: %s", e.getMessage()));
                throw new DataException(message);
            }
        }
    }
}
