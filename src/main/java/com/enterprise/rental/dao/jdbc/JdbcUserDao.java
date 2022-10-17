package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.factory.DbManager;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.CarException;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.util.*;

public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();
    protected static final String USERS_SQL = "SELECT id, email, name, password FROM users";
    private static final String FILTER_BY_NAME_SQL = "SELECT id, name, email, password FROM users WHERE name=";
    protected static final String FIELD = " id, name, email, password ";
    protected static final String INSERT_USER_SQL = "INSERT INTO users(" + FIELD + ") VALUES ( ?, ?, ?, ?);";
    private final Logger log = Logger.getLogger(JdbcUserDao.class);

    @Override
    public Optional<User> findByName(String name) {

        String sql = String.format("%s'%s'", FILTER_BY_NAME_SQL, name);

        log.info(sql);

        return getUserSql(sql);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        Connection connection;

        try {
            connection = DbManager.getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataException(e);
        }
        try {
            PreparedStatement statement = connection.prepareStatement(USERS_SQL);
            try (ResultSet resultSet = statement.executeQuery()) {

                connection.commit();

                if (resultSet.next()) {
                    List<User> users = new ArrayList<>();
                    while (resultSet.next()) {
                        User user = ROW_MAPPER.mapRow(resultSet);
                        users.add(user);
                    }
                    return users;
                }
                throw new CarException("Customer not found");
            }
        } catch (SQLException ex) {
            throw new CarException(ex.getMessage());
        }
    }

    @Override
    public boolean save(User user) {
        return setUserQuery(user);
    }

    @Override
    public User edit(User user) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<User> findAll(String sql) {
        //TODO List<User> return GetSql(USERS_SQL);
        return findAll();
    }

    private boolean setUserQuery(@NotNull User user) throws DataException {

        Connection connection = null;
        PreparedStatement statement = null;

        connection = DbManager.getInstance().getConnection();
        String userName = user.getName();
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT_USER_SQL);


            String password = user.getPassword();
            String email = user.getEmail();

            statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
            statement.setString(2, userName);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback();
            } catch (SQLException ex) {
                log.info("rollback");
                throw new DataException(ex.getMessage());
            }
            log.info(String.format("%s User can`t added, maybe account already exists", userName));
            throw new DataException(e);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                log.info(String.format("Connection not closed: %s", e.getMessage()));
            }
        }
    }

    private Optional<User> getUserSql(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DbManager.getInstance().getConnection();
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
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    log.info("resultSet closed");
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    log.info("Prepared Statement closed");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.info("Connection closed");
                }
            }
        }
    }
}
