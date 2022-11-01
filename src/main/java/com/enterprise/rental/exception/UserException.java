package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class UserException extends RuntimeException {
    private static final Logger log = Logger.getLogger(UserException.class);

    public UserException(String message) {
        super(message);
        log.info(message);
    }

    public UserException(String message, SQLException exception) {
        super(message, exception);
        log.error(String.format("%s %s", message, exception.getMessage()));
    }
}
