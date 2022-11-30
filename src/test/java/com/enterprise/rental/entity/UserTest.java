package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class UserTest {
    Logger logger = Logger.getLogger(UserTest.class);
    private static final List<User> users = new ArrayList<>();

    User bob = new User.Builder()
            .userId(11L)
            .name("John")
            .password("password")
            .passport("passport")
            .language("ua")
            .email("email@i.ua")
            .active(true)
            .role("user")
            .build();

    User jack = new User.Builder()
            .userId(12L)
            .name("Jack")
            .password("111")
            .passport("passport")
            .language("en")
            .email("jack@i.ua")
            .active(true)
            .role("user")
            .build();
    JdbcUserDao mockUserDao = mock(JdbcUserDao.class);
    UserService service = new UserService(mockUserDao);

    @BeforeEach
    void init() {
        users.add(jack);
        users.add(bob);
        service = new UserService(mockUserDao);
    }


    @Test
    @DisplayName(value = "Set User name")
    void testSetNameNewUser() {
        String name = "John";
        User user = new User();
        user.setName(name);
        assertEquals(name, user.getName());
    }

    @Test
    public void testFindByBrand() {

        when(service.getAll("John")).thenReturn(users);

        logger.info(String.format("%s", service.getAll()));
        logger.info(String.format("%s", bob));
        logger.info(String.format("%s", users));

        assertEquals("John", bob.getName());
    }

    @Test
    public void testFindAllReturnCorrectData() {
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        List<User> users = jdbcUserDao.findAll();
        assertFalse(users.isEmpty());

        for (User user : users) {
            assertNotEquals(0, user.getUserId());
            assertNotNull(user.getPassword());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());
            //TODO
//            assertNotNull(user.getSalt());
        }
    }

    @Test
    @DisplayName(value = "Test Find All Users")
    void testFindAllUsers() throws Exception {
        when(mockUserDao.findAll()).thenReturn(users);
        List<User> auto = mockUserDao.findAll();
        assertEquals(auto, users);
    }

    @Test
    @DisplayName(value = "Test Find Users By Brand Id Length")
    void testFindUsersByBrandIdSize() throws Exception {
        String name = "John";
        List<User> users = mockUserDao.findAll(name);
        when(mockUserDao.findAll(name)).thenReturn(List.of(bob));
        assertEquals(users.size(), service.getAll().size());

    }


    @Test
    @DisplayName(value = "Test get All Users")
    void getAll() {
        when(mockUserDao.findAll()).thenReturn(List.of(bob, jack));
        assertEquals(2, service.getAll().size());
    }

    @Test
    @DisplayName(value = "Test Find User By Id")
    void findById() {
        when(mockUserDao.findById(bob.getUserId())).thenReturn(Optional.of(bob));
        Optional<User> option = service.getById(bob.getUserId());
        assertTrue(option.isPresent());
        assertEquals("John", option.get().getName());
    }

    @Test
    @DisplayName(value = "Test Save User")
    void save() {
        when(mockUserDao.save(bob)).thenReturn(true);
        assertTrue(service.save(bob));
    }

    @Test
    @DisplayName(value = "Test Delete User")
    void delete() {
        when(mockUserDao.delete(bob.getUserId())).thenReturn(true);
        assertTrue(service.delete(bob.getUserId()));
    }

}
