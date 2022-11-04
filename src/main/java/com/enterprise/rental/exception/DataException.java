package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class);

    public DataException(SQLException e) {
//        super(e);
        log.error(e.getMessage());
    }

    public DataException(FileNotFoundException e) {
//        super(e);
        log.error(e.getMessage());
    }

    public DataException(String message) {
//        super(message);
        log.info(message);
    }

    public DataException(String message, SQLException exception) {
//        super(exception);
        log.error(String.format("%s%s", message, exception.getMessage()));
    }

    public DataException(String message, IOException exception) {
        String format = String.format("%s%s", message, exception.getMessage());
        log.error(format);
    }
}
