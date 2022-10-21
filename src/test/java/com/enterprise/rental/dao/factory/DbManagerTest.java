package com.enterprise.rental.dao.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DbManagerTest {

    @Test
    @DisplayName(value = "Test get Instance Singleton and check if it not null")
    void getInstanceTest() {
        DbManager dbManager = DbManager.getInstance();
        assertNotNull(dbManager);
    }

    @Test
    @DisplayName(value = "Test get Connection Test and check if it not null")
    void getConnectionTest() {
        DbManager dbManager = DbManager.getInstance();
        Connection connection = dbManager.getConnection();
        assertNotNull(connection);
    }

}