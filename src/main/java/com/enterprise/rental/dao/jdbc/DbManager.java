package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

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
 * @author Pasha Pollack
 */
public class DbManager implements AutoCloseable {
    private static final Logger log = Logger.getLogger(DbManager.class);

    // Reads properties for the database from application.properties
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
//    private static final String FILE_PROPS = "src/main/resources/application.properties";
    private Properties properties;
    private static DbManager dbManager;

    /**
     * Default constructor user for upload properties connection
     */
    public DbManager() {
//        loadProperties();
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
    public static Connection configConnection() {
        try {
            Connection connection = getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);
            return connection;
        } catch (SQLException sqlException) {
            throw new DataException(sqlException.getMessage(), sqlException);
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
        log.error(String.format("Rollback: %s%n", sqlException.getMessage()));

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
                log.error(String.format("connection can not rollback: %s", e.getMessage()));
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
                log.error(String.format("connection not closed: %s", e.getMessage()));
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


    // Reads resource properties file
//    private void loadProperties() {
//        properties = new Properties();
//        try (FileInputStream inputStream = new FileInputStream(FILE_PROPS)) {
//            properties.load(inputStream);
//        } catch (IOException exception) {
//            throw new DataException("Cannot read the file", exception);
//        }
//    }

    // Get credentials for the property with the specified key in this property list.
    // Params: key – the property key.
    // Returns: the value in this property list with the specified key value
    private String getDBUrl() {
        return "jdbc:postgresql://peanut.db.elephantsql.com:5432/lnycichw";

//        return properties.getProperty(DB_URL_PROPERTY_NAME);
    }

    private String getDBName() {
        return "lnycichw";

//        return properties.getProperty(DB_NAME_PROPERTY_NAME);
    }

    private String getDBPassword() {
        return "hGsOS0-qZ1UiYkneTgpy8iXJZ7RzP8lY";
//        return properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
    }
}