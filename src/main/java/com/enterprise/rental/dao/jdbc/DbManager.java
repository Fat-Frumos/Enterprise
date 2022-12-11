package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class DbManager implements AutoCloseable {
    private static final Logger log = Logger.getLogger(DbManager.class);
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String FILE_PROPS = "src/main/resources/application.properties";
    private Properties properties;
    private static DbManager dbManager;

    public DbManager() {
        loadProperties();
    }

    public static synchronized DbManager getInstance() {
        return dbManager == null
                ? new DbManager()
                : dbManager;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    getDBUrl(),
                    getDBName(),
                    getDBPassword());
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    public static Connection getConfigConnection() {
        try {
            Connection connection = getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            return connection;
        } catch (SQLException sqlException) {
            throw new DataException(sqlException.getMessage(), sqlException);
        }
    }

    protected static void eventually(
            Connection connection,
            PreparedStatement statement) {
        close(statement);
        close(connection);
    }

    protected static void eventually(
            Connection connection,
            PreparedStatement statement,
            ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    protected static boolean rollback(
            Connection connection,
            SQLException sqlException, String query) {
        log.debug(String.format("Rollback: %s%n%s%n", sqlException.getMessage(), query));

        try {
            Objects.requireNonNull(connection).rollback();
            return true;
        } catch (SQLException exception) {
            throw new DataException(
                    String.format("Can`t rollback: %s",
                            exception.getMessage()));
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e) {
                log.debug(String.format("connection can not rollback: %s", e.getMessage()));
            }
        }
    }

    private static void close(Connection connection) {
        try {
            Objects.requireNonNull(connection).close();
        } catch (SQLException e) {
            throw new DataException("Connection not closed: %s", e);
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                log.debug(String.format("connection not closed: %s", e.getMessage()));
            }
        }
    }

    private static void close(PreparedStatement statement) {
        try {
            Objects.requireNonNull(statement).close();
        } catch (SQLException e) {
            log.debug("Prepared Statement not closed");
            throw new DataException(e.getMessage(), e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                log.debug(String.format("Prepared Statement not closed: %s", e.getMessage()));
            }
        }
    }

    private static boolean close(ResultSet resultSet) throws DataException {
        try {
            Objects.requireNonNull(resultSet).close();
            return true;
        } catch (SQLException sqlException) {
            log.debug(String.format("resultSet not closed: %s",
                    sqlException.getMessage()));
            throw new DataException(sqlException);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                log.debug(String.format("ResultSet closed: %s", e.getMessage()));
            }
        }
    }

    @Override
    public void close() throws SQLException {
        getConnection().close();
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(FILE_PROPS)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new DataException("Cannot read the file", exception);
        }
    }

    private String getDBUrl() {
        return properties.getProperty(DB_URL_PROPERTY_NAME);
    }

    private String getDBName() {
        return properties.getProperty(DB_NAME_PROPERTY_NAME);
    }

    private String getDBPassword() {
        return properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
    }


}