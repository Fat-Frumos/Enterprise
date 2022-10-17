package com.enterprise.rental.dao.factory;

import com.enterprise.rental.exception.DataException;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class ConnectionFactory implements AutoCloseable {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class);
    private final BasicDataSource dataSource;

    public ConnectionFactory(String url, String user, String password) {
        dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.info("can't fetch connection");
            throw new DataException("can't fetch connection");
        }
    }

    @Override
    public void close() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }
}