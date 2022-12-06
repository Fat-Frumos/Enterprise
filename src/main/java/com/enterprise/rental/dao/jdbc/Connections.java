package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.enterprise.rental.dao.jdbc.DbManager.getInstance;
import static java.sql.Connection.TRANSACTION_READ_COMMITTED;

public class Connections {
    private static final Logger log = Logger.getLogger(Connections.class);

    protected static Connection getConfigConnection() {

        Connection connection;
        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(TRANSACTION_READ_COMMITTED);

        } catch (SQLException sqlException) {
            throw new DataException(sqlException.getMessage(), sqlException);
        }
        return connection;
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
        return rollback(connection);
    }

    private static boolean rollback(Connection connection) {
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
//                log.debug("connection rollback");
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
//                log.debug("connection closed");
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
}