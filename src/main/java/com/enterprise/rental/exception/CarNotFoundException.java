package com.enterprise.rental.exception;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The <code>CarNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Polyak
 * @see RuntimeException
 */
public class CarNotFoundException extends RuntimeException {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public CarNotFoundException(String message) {
        super(message);
        LOGGER.log(Level.ERROR, message);
    }
}
