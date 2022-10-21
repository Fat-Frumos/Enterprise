package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

public class UserNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(com.enterprise.rental.exception.UserNotFoundException.class);
    public UserNotFoundException(String message) {
        log.error(message);
    }

}
