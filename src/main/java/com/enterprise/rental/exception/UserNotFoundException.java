package com.enterprise.rental.exception;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The <code>UserNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Polyak
 * @see RuntimeException
 */
public class UserNotFoundException extends RuntimeException {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message      the detail message.
     * @param sqlException the cause SQLException.
     */
    public UserNotFoundException(String message, SQLException sqlException) {
        super(message, sqlException);
        LOGGER.log(Level.ERROR, "{}{}", message, sqlException.getMessage());
    }

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message the detail message.
     */
    public UserNotFoundException(String message) {
        super(message);
        LOGGER.log(Level.ERROR, "{}", message);
    }
}
