package com.enterprise.rental.exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = 1L;

    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}