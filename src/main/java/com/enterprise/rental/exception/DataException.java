package com.enterprise.rental.exception;

import java.io.IOException;
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
        throw new DataException(message + exception.getMessage());
    }

    public DataException(String message, IOException exception) {
        throw new DataException(message + exception.getMessage());
    }
}
