package com.enterprise.rental.dao.factory;

import com.enterprise.rental.exception.DataException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager implements AutoCloseable {
    private static final String DB_URL_PROPERTY_NAME = "db.url";
    private static final String DB_NAME_PROPERTY_NAME = "db.username";
    private static final String DB_PASSWORD_PROPERTY_NAME = "db.password";
    private static final String FILE_PROPS = "src/main/resources/application.properties";
    private Properties properties;
    private static DbManager dbManager;

    public DbManager() {
        loadProperties();
    }

    public static synchronized DbManager getInstance() {
        return dbManager == null ? new DbManager() : dbManager;
    }

    public Connection getConnection()  {
        try {
            return DriverManager.getConnection(getDBUrl(), getDBName(), getDBPassword());
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        getConnection().close();
    }

    private void loadProperties() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(FILE_PROPS)) {
            properties.load(inputStream);
        } catch (IOException exception) {
            throw new DataException(exception.getMessage(), exception);
        }
    }

    private String getDBUrl() {

        return "jdbc:postgresql://ec2-18-235-114-62.compute-1.amazonaws.com:5432/dbgo3fr277jh86";
    }

    private String getDBName() {

        return "xhhiprtyikbzhv";
    }

    private String getDBPassword() {

        return "7e80cfaecb05a3a2ed082c914d07d5245f12fb7c41f0714047a38096481ae7f8";
    }
//    private String getDBUrl() {
//        return properties.getProperty(DB_URL_PROPERTY_NAME);
//    }
//
//    private String getDBName() {
//        return properties.getProperty(DB_NAME_PROPERTY_NAME);
//    }
//
//    private String getDBPassword() {
//        return properties.getProperty(DB_PASSWORD_PROPERTY_NAME);
//    }
}