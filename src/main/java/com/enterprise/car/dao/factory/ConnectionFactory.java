package com.enterprise.car.dao.factory;

import com.enterprise.car.exception.DataException;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionFactory implements DataSource {
    private final String url;
    private final String username;
    private final String password;

    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";

    public ConnectionFactory(Properties properties) {
        this.url = properties.getProperty(DB_URL_PROPERTY_NAME);
        this.username = properties.getProperty(DB_NAME_PROPERTY_NAME);
        this.password = properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
    }

    @Override
    public Connection getConnection() {
        return getConnection(username, password);
    }

    @Override
    public Connection getConnection(String username, String password) {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException exception) {
            throw new DataException(exception.getMessage(), exception);
        }
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {
    }

    @Override
    public void setLoginTimeout(int seconds) {
    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }
}