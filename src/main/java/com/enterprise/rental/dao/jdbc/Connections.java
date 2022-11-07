package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.enterprise.rental.dao.DbManager.getInstance;

public class Connections {
    private static final Logger log = Logger.getLogger(Connections.class);

    protected static Connection getWithoutAutoCommit() {

        Connection connection;
        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
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
            SQLException sqlException) {
        log.info(String.format("Rollback: %s",
                sqlException.getMessage()));
        return rollback(connection);
    }

    private static boolean rollback(Connection connection) {
        try {
            Objects.requireNonNull(connection).rollback();
            log.info("Rollback");
            return true;
        } catch (SQLException exception) {
            throw new DataException(
                    String.format("Can`t rollback: %s",
                            exception.getMessage()));
        }
    }

    private static void close(Connection connection) {
        try {
            Objects.requireNonNull(connection).close();
            log.info("Connection closed");
        } catch (SQLException e) {
            throw new DataException("Connection not closed: %s", e);
        }
    }

    private static void close(PreparedStatement statement) {
        try {
            Objects.requireNonNull(statement).close();
            log.info("Prepared Statement closed");
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    private static boolean close(ResultSet resultSet) {
        try {
            Objects.requireNonNull(resultSet).close();
            log.info("resultSet closed");
            return true;
        } catch (SQLException sqlException) {
            log.info(String.format("resultSet not closed: %s",
                    sqlException.getMessage()));
            throw new DataException(sqlException);
        }
    }
}