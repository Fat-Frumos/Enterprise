package com.enterprise.rental.dao.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JdbcOrderDaoTest {

    private static Connection connection = mock(Connection.class);
    private static PreparedStatement preparedStatement = mock(PreparedStatement.class);
    private static ResultSet resultSet = mock(ResultSet.class);

    @Test
    void findAll() throws SQLException {
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
    }

    @Test
    void testSelect() throws SQLException {

        int id = 1;
        String login = "testUser";
        String password = "testPassword";

        when(resultSet.next())
                .thenReturn(true)
                .thenReturn(false);

        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getString("login")).thenReturn(login);
        when(resultSet.getString("password")).thenReturn(password);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(Constants.FILTER_BY_NAME_SQL)).thenReturn(preparedStatement);

    }

}