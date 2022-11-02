package com.enterprise.rental.dao.factory;

import com.enterprise.rental.dao.DbManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DbManagerTest {
    @InjectMocks
    private DbManager dbManager;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement mockStatement;

    @Test
    @DisplayName(value = "Test get Instance Singleton and check if it not null")
    void getInstanceTest() {
        dbManager = DbManager.getInstance();
        assertNotNull(dbManager);
    }

    @Test
    @DisplayName(value = "Test get Connection Test and check if it not null")
    void getConnectionTest() {
        dbManager = DbManager.getInstance();
        connection = dbManager.getConnection();
        assertNotNull(connection);
    }

    @Test
    @DisplayName(value = "Test get Connection StatementTest and check if it not null")
    void getConnectionStatementTest() throws SQLException {
        dbManager = DbManager.getInstance();
        connection = dbManager.getConnection();
        mockStatement = connection.prepareStatement("SELECT *");
        assertNotNull(mockStatement);
    }
}