package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class UserNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(UserNotFoundException.class);

    public UserNotFoundException(String message) {
//        super(message);
        log.error(message);
    }

    public UserNotFoundException(String message, SQLException exception) {
//        super(message, exception);
        log.error(String.format("%s %s", message, exception.getMessage()));
    }
}
