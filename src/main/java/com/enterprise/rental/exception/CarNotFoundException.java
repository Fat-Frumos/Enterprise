package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

public class CarNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(CarNotFoundException.class);
    public CarNotFoundException(String message) {
        super(message);
        log.info(message);
    }
}
