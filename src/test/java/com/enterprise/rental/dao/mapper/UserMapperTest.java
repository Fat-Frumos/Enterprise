package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserMapperTest {

    UserMapper mapper = new UserMapper();
    HttpServletRequest request = mock(HttpServletRequest.class);
    ResultSet resultSet = mock(ResultSet.class);

    @Test
    void testResultSetMapRow() {
        try {
            Timestamp created = new Timestamp(System.currentTimeMillis());
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String salt = resultSet.getString("salt");
            String password = resultSet.getString("password");
            String passport = resultSet.getString("passport");
            String phone = resultSet.getString("phone");
            boolean active = resultSet.getBoolean("active");
            boolean closed = resultSet.getBoolean("closed");
            String language = resultSet.getString("language");

            String role = resultSet.getString("role")
                    != null ? resultSet.getString("role") : "guest";

            User build = new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
                    .language(language)
                    .email(email)
                    .salt(salt)
                    .passport(passport)
                    .phone(phone)
                    .created(created)
                    .active(active)
                    .closed(closed)
                    .role(role)
                    .build();
            for (Field declaredField : build.getClass().getDeclaredFields()) {
                assertNotNull(declaredField.getName());
            }

        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException(e.getMessage(), exception);
            }
            throw new UserNotFoundException("User not found", exception);
        }
    }


    @Test
    void  TestMapperRowResultSetDeclaredFields() throws SQLException {

        when(resultSet.getLong("id")).thenReturn(1L);
        when(resultSet.getString("name")).thenReturn("bob");
        when(resultSet.getString("password")).thenReturn("password");
        when(resultSet.getString("salt")).thenReturn("salt");
        when(resultSet.getString("phone")).thenReturn("phone");
        when(resultSet.getString("passport")).thenReturn("passport");
        when(resultSet.getString("email")).thenReturn("email@i.ua");
        when(resultSet.getBoolean("active")).thenReturn(true);
        when(resultSet.getBoolean("closed")).thenReturn(false);

        User user = mapper.mapRow(resultSet);

        for (Field declaredField : user.getClass().getDeclaredFields()) {
            assertNotNull(declaredField.getName());
        }
        assertEquals(1L, user.getUserId());
        assertEquals("bob", user.getName());
        assertEquals("password", user.getPassword());
        assertEquals("passport", user.getPassport());
        assertEquals("phone", user.getPhone());
        assertEquals("salt", user.getSalt());
        assertEquals("bob", user.getName());
        assertEquals("email@i.ua", user.getEmail());
        assertTrue(user.isActive());
        assertFalse(user.isClosed());
    }

    @Test
    void testRowRequestDeclaredFieldsWithBuilderNotNull() {

        Timestamp created = new Timestamp(System.currentTimeMillis());
//            Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String salt = request.getParameter("salt");
        String password = request.getParameter("password");
        String passport = request.getParameter("passport");
        String phone = request.getParameter("phone");
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        boolean closed = Boolean.parseBoolean(request.getParameter("closed"));
        String language = request.getParameter("language");

        String role = request.getParameter("role")
                != null ? request.getParameter("role") : "guest";

        User build = new User.Builder()
//                .userId(id)
                .name(name)
                .password(password)
                .language(language)
                .email(email)
                .salt(salt)
                .passport(passport)
                .phone(phone)
                .created(created)
                .active(active)
                .closed(closed)
                .role(role)
                .build();
        for (Field declaredField : build.getClass().getDeclaredFields()) {
            assertNotNull(declaredField.getName());
        }
    }

    @Test
    void testMapperRowRequestNotNull() {



//        when(request.getParameter("id")).thenReturn("11");
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("passport")).thenReturn("AA 123456789");
        when(request.getParameter("password")).thenReturn("123456789");
        when(request.getParameter("phone")).thenReturn("+1234565789123");
        when(request.getParameter("language")).thenReturn("UAH");
        when(request.getParameter("email")).thenReturn("email@i.ua");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(request.getParameter("term")).thenReturn(String.valueOf(now));

        User user = mapper.mapper(request);

        //        assertEquals(11, user.getUserId());
        assertEquals("name", user.getName());
        assertEquals("AA 123456789", user.getPassport());
        assertEquals("123456789", user.getPassword());
        assertEquals("+1234565789123", user.getPhone());
        assertEquals("UAH", user.getLanguage());
        assertEquals("email@i.ua", user.getEmail());

        for (Field declaredField : user.getClass().getDeclaredFields()) {
            assertNotNull(declaredField.getName());
        }
    }
}
