package com.enterprise.car.exception;

import java.sql.SQLException;
import java.util.logging.Logger;

public class CarException extends RuntimeException {
    private static final Logger log = Logger.getLogger(CarException.class.getName());
    public CarException(String message) {
        log.info(message);
    }
    public CarException(String message, SQLException sqlException) {
        new CarException(message);
    }
}
