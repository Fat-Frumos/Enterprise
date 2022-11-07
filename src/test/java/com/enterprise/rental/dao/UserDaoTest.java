package com.enterprise.rental.dao;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UserDaoTest {

    @Test
    void findByName() {
    }

    @Test
    void findAll() {
    }

    @Mock
    DataSource mockDataSource;
    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet mockResultSet;
    int userId = 100;

    public void setUp() throws SQLException {
        when(mockDataSource.getConnection()).thenReturn(mockConn);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConn);
        doNothing().when(mockConn).commit();
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mockResultSet.getInt(100)).thenReturn(userId);
    }

    @Test
    public void testCreateWithNoExceptions() throws SQLException {

        setUp();
        UserDao userDao = new JdbcUserDao();

        //verify and assert
        verify(mockConn, times(1)).prepareStatement(anyString(), anyInt());
        verify(preparedStatement, times(6)).setString(anyInt(), anyString());
        verify(preparedStatement, times(1)).execute();
        verify(mockConn, times(1)).commit();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(1)).getInt(100);
    }
}