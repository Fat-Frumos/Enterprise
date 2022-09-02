package com.enterprise.car.exception;

import java.sql.SQLException;
import java.util.logging.Logger;

public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class.getName());

    public DataException(SQLException e) {
        log.info(e.getMessage());
    }

    public DataException(String message) {
        log.info(message);
    }

    public DataException(String message, SQLException exception) {
        new DataException(message);
    }
}
