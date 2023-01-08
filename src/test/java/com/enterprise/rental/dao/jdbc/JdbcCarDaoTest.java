package com.enterprise.rental.dao.jdbc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JdbcCarDaoTest {
    static PreparedStatement preparedStatement = mock(PreparedStatement.class);
    static ResultSet mockResultSet = mock(ResultSet.class);

    @Test
    @DisplayName("Test Jdbc prepared Statement execute")
    void testExecute() throws SQLException {

        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);

        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
    }
}