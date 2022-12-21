package com.enterprise.rental.exception;

/**
 * The HTTP status code series can be retrieved
 * Constants enumerating the HTTP status codes
 *
 * @author Pasha Pollack
 */
public enum HTTPStatus {
    NOT_FOUND(404, "Not found"),
    FORBIDDEN(403, "FORBIDDEN"),
    OK(200, "OK"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_REQUEST(400, "Bad Request");

    private final Integer code;
    private final String message;

    HTTPStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
