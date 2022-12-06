package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class);

    public DataException(SQLException e) {
        super(e.getMessage());
        log.error(e.getMessage());
    }

    public DataException(FileNotFoundException e) {
        super(e.getMessage());
        log.error(e.getMessage());
    }

    public DataException(String message) {
        super(message);
        log.error(message);
    }

    public DataException(String message, SQLException exception) {
        super(message);
        log.error(String.format("%s%s", message, exception.getMessage()));
    }

    public DataException(String message, IOException exception) {
        super(exception.getMessage());
        String format = String.format("%s%s", message, exception.getMessage());
        log.error(format);
    }

    public DataException(Exception exception) {
        super(exception.getMessage());
        log.error(String.format("%s", exception.getMessage()));

    }
}
