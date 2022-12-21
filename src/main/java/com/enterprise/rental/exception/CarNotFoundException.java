package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

/**
 * The <code>CarNotFoundException</code> extends <code>RuntimeException</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Pollack
 * @see RuntimeException
 */
public class CarNotFoundException extends RuntimeException {
    private static final Logger log = Logger.getLogger(CarNotFoundException.class);

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public CarNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
