package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

public class OrderNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(OrderNotFoundException.class);

    public OrderNotFoundException(String message, SQLException sqlException) {
        super(message);
        log.error(sqlException.getMessage());
    }

    public OrderNotFoundException(SQLException sqlException) {
        super(sqlException.getMessage());
        log.error(sqlException.getMessage());
    }
}
