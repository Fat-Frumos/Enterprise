package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Properties;

import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

/**
 * The DbManager class describes the functions of a connection to Database.
 * Close ResultSet, PreparedStatement and Connection
 * Representing a Singleton instance.
 * Implementation of AutoCloseable
 *
 * @author Pasha Polyak
 */
public class DbManager implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    // Reads properties for the database from application.properties
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String FILE_PROPS = "src/main/resources/application.properties";
    private Properties properties;
    private static DbManager dbManager;

    /**
     * Default constructor user for upload properties connection
     */
    public DbManager() {
        loadProperties();
    }

    /**
     * Check the Singleton instance, if it exists, return the object, and vice versa return the new value
     *
     * @return instance of DbManager
     */
    public static synchronized DbManager getInstance() {
        return dbManager == null
                ? new DbManager()
                : dbManager;
    }

    /**
     * Attempts to establish a connection to the given database URL.
     * The DriverManager attempts to select an appropriate driver from the set of registered JDBC drivers.
     * Params: url – a database url of the form jdbc
     * user – the database user on whose behalf the connection is being made
     * password – the user's password
     *
     * @return a connection
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    getDBUrl(),
                    getDBName(),
                    getDBPassword());
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new DataException(e);
        }
    }

    /**
     * Configuration Connection
     * he JDBC driver will implicitly start a new transaction after each commit.
     * A constant indicating that dirty reads are prevented; non-repeatable reads and phantom reads can occur.
     * This level only prohibits a transaction from reading a row with uncommitted changes in it.
     *
     * @return a connection instance
     */
    public static Connection proxyConnection() {
        try {
            Connection connection = getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            return connection;
        } catch (SQLException sqlException) {
            LOGGER.log(Level.ERROR, sqlException.getMessage());
            throw new DataException(sqlException);
        }
    }

    /**
     * Manager for close Connection and PreparedStatement
     *
     * @param connection object's database is able to provide information describing its tables, its supported SQL grammar etc.
     * @param statement  An object that represents a precompiled SQL statement.
     */
    protected static void eventually(
            Connection connection,
            PreparedStatement statement) {
        close(statement);
        close(connection);
    }

    /**
     * Manager for close Connection and PreparedStatement
     *
     * @param connection object's database is able to provide information describing its tables, its supported SQL grammar etc.
     * @param statement  An object that represents a precompiled SQL statement.
     * @param resultSet  An object that represents a ResultSet
     */
    protected static void eventually(
            Connection connection,
            PreparedStatement statement,
            ResultSet resultSet) {
        close(resultSet);
        close(statement);
        close(connection);
    }

    /**
     * Undoes all changes made in the current transaction
     * and releases any database locks currently held by this Connection object.
     * This method should be used only when auto-commit mode has been disabled.
     *
     * @param connection   object's database is able to provide information describing its tables, its supported SQL grammar etc.
     * @param sqlException SQLException cause
     * @return boolean true if rollback was successful
     */
    protected static boolean rollback(
            Connection connection,
            SQLException sqlException) {
        try {
            Objects.requireNonNull(connection).rollback();
            return true;
        } catch (SQLException exception) {
            LOGGER.log(Level.ERROR, "Rollback: {}%n", sqlException.getMessage());
            throw new DataException(exception.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.rollback();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
    }

    /**
     * Calling the method <code>close</code> on a <code>Connection</code>
     * object that is already closed is a no-op.
     * <p>
     * Catch SQLException if a database access error occurs
     *
     * @param connection object's database is able to provide information describing its tables, its supported SQL grammar etc.
     */
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
                LOGGER.log(Level.ERROR, "connection not closed: {}", e.getMessage());
            }
        }
    }

    /**
     * Releases this ResultSet object's database and JDBC resources immediately
     * instead of waiting for this to happen when it is automatically closed
     *
     * @param statement An object that represents a precompiled SQL statement.
     */
    private static void close(PreparedStatement statement) {
        try {
            Objects.requireNonNull(statement).close();
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Prepared Statement not closed");
            throw new DataException(e.getMessage(), e);
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "Prepared Statement not closed: {}", e.getMessage());
            }
        }
    }

    /**
     * Releases this ResultSet object's database and JDBC resources immediately
     * instead of waiting for this to happen when it is automatically closed
     *
     * @param resultSet A table of data representing a database result set,
     *                  which is usually generated by executing a statement
     *                  that queries the database.
     */
    private static boolean close(ResultSet resultSet) throws DataException {
        try {
            Objects.requireNonNull(resultSet).close();
            return true;
        } catch (SQLException sqlException) {
            LOGGER.log(Level.INFO, "resultSet not closed: {}",
                    sqlException.getMessage());
            throw new DataException(sqlException);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.INFO, "ResultSet closed: {}", e.getMessage());
            }
        }
    }

    /**
     * Releases this <code>Connection</code> object's database and JDBC resources
     * immediately instead of waiting for them to be automatically released.
     * <p>
     * Catch SQLException if a database access error occurs
     */
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

    // Get credentials for the property with the specified key in this property list.
    // Params: key – the property key.
    // Returns: the value in this property list with the specified key value
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