package com.enterprise.rental.exception;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The <code>DataException</code> extends <code>RuntimeException</code>
 * Is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Pollack
 * @see RuntimeException
 */
public class DataException extends RuntimeException {
    private static final Logger log = Logger.getLogger(DataException.class);


    /**
     * Constructs a new exception with the specified cause Exception
     *
     * @param exception the underlying reason for this <code>SQLException</code>
     *                  (which is saved for later retrieval by the <code>getCause()</code> method);
     */
    public DataException(SQLException exception) {
        super(exception.getMessage());
        log.error(exception.getMessage());
    }

    /**
     * Constructs a new FileNotFoundException with the specified cause Exception
     *
     * @param exception the cause.
     */
    public DataException(FileNotFoundException exception) {
        super(exception.getMessage());
        log.error(exception.getMessage());
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public DataException(String message) {
        super(message);
        log.error(message);
    }

    /**
     * Constructs a new SQLException with the specified detail message and cause.
     *
     * @param message   the detail message.
     * @param exception the cause SQLException.
     */
    public DataException(String message, SQLException exception) {
        super(message);
        log.error(String.format("%s%s", message, exception.getMessage()));
    }

    /**
     * Constructs a new IOException with the specified detail message and cause.
     *
     * @param message   the detail message.
     * @param exception the cause IOException.
     */
    public DataException(String message, IOException exception) {
        super(exception.getMessage());
        String format = String.format("%s%s", message, exception.getMessage());
        log.error(format);
    }

    /**
     * Constructs a new Exception with the specified cause Exception
     *
     * @param exception the cause.
     */
    public DataException(Exception exception) {
        super(exception.getMessage());
        log.error(String.format("%s", exception.getMessage()));
    }
}
