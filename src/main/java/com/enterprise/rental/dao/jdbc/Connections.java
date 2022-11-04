package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Connections {
    private static final Logger log = Logger.getLogger(Connections.class);

    protected static boolean rollback(Connection connection, SQLException sqlException) {
        try {
            log.info(sqlException.getMessage(), sqlException);
            Objects.requireNonNull(connection).rollback();
            return true;
        } catch (SQLException e) {
            throw new DataException("Can`t rollback", e);
        }
    }

    protected static boolean close(Connection connection) {
        try {
            Objects.requireNonNull(connection).close();
            return true;
        } catch (SQLException e) {
            throw new DataException("Connection not closed: %s", e);
        }
    }

    protected static boolean close(PreparedStatement statement) {
        try {
            Objects.requireNonNull(statement).close();
            return true;
        } catch (SQLException e) {
            throw new DataException(e.getMessage(), e);
        }
    }

    protected static boolean close(ResultSet resultSet) {
        try {
            Objects.requireNonNull(resultSet).close();
            return true;
        } catch (SQLException e) {
            log.info(String.format("resultSet not closed: %s", e.getMessage()));
            throw new DataException(e);
        }
    }
    protected static void eventually(Connection connection, PreparedStatement statement, ResultSet resultSet) {
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
