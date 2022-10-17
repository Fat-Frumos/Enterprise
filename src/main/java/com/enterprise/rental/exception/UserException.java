package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

public class UserException extends RuntimeException {
    private static final Logger log = Logger.getLogger(com.enterprise.rental.exception.UserException.class);

    public UserException(String message) {
        log.debug(message);
    }
}
