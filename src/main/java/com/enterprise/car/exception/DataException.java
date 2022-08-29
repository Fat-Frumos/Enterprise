package com.enterprise.car.exception;

import java.sql.SQLException;

public class DataException extends RuntimeException {
    public DataException(SQLException e) {
    }

    public DataException(String message) {

    }
}
