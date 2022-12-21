package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * The <code>UserNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Pollack
 * @see RuntimeException
 */
public class UserNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(UserNotFoundException.class);

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message      the detail message.
     * @param sqlException the cause SQLException.
     */
    public UserNotFoundException(String message, SQLException sqlException) {
        super(message, sqlException);
        log.error(String.format("%s %s", message, sqlException.getMessage()));
    }

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
        log.error(String.format("%s", message));
    }
}
