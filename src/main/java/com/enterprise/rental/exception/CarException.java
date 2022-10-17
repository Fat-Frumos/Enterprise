package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

public class CarException extends RuntimeException {
    private static final Logger log = Logger.getLogger(CarException.class);
    public CarException(String message) {
        log.debug(message);
    }
}
