package com.enterprise.rental.dao.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.enterprise.rental.dao.jdbc.Constants.FIND_ALL_SQL;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class JdbcCarDaoTest {

    @Test
    void findAll() throws SQLException {
        DbManager mockDataSource = mock(DbManager.class);

        Connection connection = mockDataSource.getConnection();

        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        ResultSet mockResultSet = mock(ResultSet.class);

//        when(connection).thenReturn(mockDataSource.getConnection());
//        connection = getConfigConnection();

//        PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL);
//        connection.commit();
//        ResultSet resultSet = preparedStatement.executeQuery();
//        System.out.println(resultSet.next());

        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);

        doNothing().when(preparedStatement).setString(anyInt(), anyString());
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
    }
}