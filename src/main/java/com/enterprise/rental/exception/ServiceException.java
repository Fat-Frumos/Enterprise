package com.enterprise.rental.exception;

/**
 * The <code>ServiceException</code> extends <code>Exception</code>
 * is used to wrap any exception that occurs during data performing.
 *
 * @author Pasha Polyak
 * @see Exception
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}