package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserMapperTest {

    @Test
    void testMapRow() throws SQLException {
        UserMapper mapper = new UserMapper();

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(1l);
        when(resultSet.getString("name")).thenReturn("bob");
        when(resultSet.getString("password")).thenReturn("password");
        when(resultSet.getString("email")).thenReturn("email@i.ua");
        when(resultSet.getBoolean("active")).thenReturn(true);

        User user = mapper.mapRow(resultSet);

        assertEquals(1l, user.getUserId());
        assertEquals("bob", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals("email@i.ua", user.getEmail());
        assertTrue(user.isActive());
    }
}