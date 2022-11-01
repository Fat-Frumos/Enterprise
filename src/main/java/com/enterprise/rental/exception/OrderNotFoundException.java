package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

public class OrderNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(OrderNotFoundException.class);
    public OrderNotFoundException(String message) {
        log.error(message);
    }

}
