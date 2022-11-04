package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class OrderNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(OrderNotFoundException.class);

    public OrderNotFoundException(String message) {
        super(message);
        log.error(message);
    }

    public OrderNotFoundException(String message, SQLException sqlException) {
        super(message);
        log.error(sqlException.getMessage());
    }

    public OrderNotFoundException(SQLException sqlException) {
        log.error(sqlException.getMessage());
    }
}