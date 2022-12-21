package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.sql.SQLException;

/**
 * The <code>OrderNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Pollack
 * @see RuntimeException
 */
public class OrderNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(OrderNotFoundException.class);

    /**
     * Constructs a new SQLException with the specified detail message.
     *
     * @param message      the detail message.
     * @param sqlException the cause SQLException.
     */
    public OrderNotFoundException(String message, SQLException sqlException) {
        super(message);
        log.error(sqlException.getMessage());
    }

    /**
     * Constructs a new SQLException with the specified cause Exception
     *
     * @param sqlException the cause.
     */
    public OrderNotFoundException(SQLException sqlException) {
        super(sqlException.getMessage());
        log.error(sqlException.getMessage());
    }
}
