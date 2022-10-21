package com.enterprise.rental.exception;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class);

    public DataException(SQLException e) {
        log.info(e.getMessage());
    }

    public DataException(String message) {
        log.info(message);
    }

    public DataException(String message, IOException exception) {
        throw new DataException(String.format("%s%s", message, exception.getMessage()));
    }
}
