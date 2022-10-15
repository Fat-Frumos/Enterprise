package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.factory.DbManager;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.CarException;
import com.enterprise.rental.exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();
    protected static final String USERS_SQL = "SELECT id, email, name, password FROM users";
    private static final String FILTER_BY_NAME_SQL = "SELECT id, email, name, password FROM users WHERE name=";
    private final Logger log = Logger.getLogger(JdbcUserDao.class.getName());

    @Override
    public Optional<User> findByName(String name) {

        String sql = String.format("%s'%s'", FILTER_BY_NAME_SQL, name);

        log.info(sql);

        return getGetSql(sql);
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
                throw new CarException("Vehicle not found");
            }
        } catch (SQLException ex) {
            throw new CarException(ex.getMessage());
        }
    }

    @Override
    public boolean save(User user) {
        return false;
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
    public List<User> findAll(String params) {
        return new ArrayList<>();
    }


    private Optional<User> getGetSql(String sql) {
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
