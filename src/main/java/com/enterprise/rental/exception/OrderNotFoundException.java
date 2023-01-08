package com.enterprise.rental.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * The <code>OrderNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Polyak
 * @see RuntimeException
 */
public class OrderNotFoundException extends RuntimeException {
    static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message      the detail message.
     * @param sqlException the cause SQLException.
     */
    public OrderNotFoundException(String message, SQLException sqlException) {
        super(message);
        LOGGER.log(Level.ERROR, sqlException.getMessage());
    }

    /**
     * Constructs a new SQLException with the specified cause Exception
     *
     * @param sqlException the cause.
     */
    public OrderNotFoundException(SQLException sqlException) {
        super(sqlException.getMessage());
        LOGGER.log(Level.ERROR, sqlException.getMessage());
    }
}
