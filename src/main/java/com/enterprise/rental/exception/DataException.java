package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class);

    public DataException(SQLException e) {
        super(e);
        log.info(e.getMessage());
    }

    public DataException(FileNotFoundException e) {
        super(e);
        log.info(e.getMessage());
    }

    public DataException(String message) {
        log.info(message);
    }

    public DataException(String message, SQLException exception) {
        throw new DataException(String.format("%s%s", message, exception.getMessage()));
    }

    public DataException(String message, IOException exception) {
        super(String.format("%s%s", message, exception.getMessage()));
    }
}
