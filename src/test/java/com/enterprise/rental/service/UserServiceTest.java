package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.enterprise.rental.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private static DefaultUserService userService;
    JdbcUserDao mockDao;
    List<User> users = new ArrayList<>();
    User bob = new User.Builder()
            .userId(11L)
            .name("Bob")
            .password("password")
            .passport("passport")
            .language("ua")
            .email("email@i.ua")
            .active(true)
            .role(USER.role())
            .build();

    User jack = new User.Builder()
            .userId(12L)
            .name("Jack")
            .password("111")
            .passport("passport")
            .language("en")
            .email("jack@i.ua")
            .active(true)
            .role(USER.role())
            .build();

    @BeforeEach
    void init() {
        users.add(jack);
        users.add(bob);
        mockDao = mock(JdbcUserDao.class);
        userService = new DefaultUserService(mockDao);
    }

    @Test
    @DisplayName(value = "Test find User by Id invokes and return true")
    void findUser() throws ServiceException, DaoException {
        when(mockDao.findById(12L)).thenReturn(Optional.ofNullable(jack));
        Optional<User> userOptional = userService.findBy(12L);
        assertTrue(userOptional.isPresent());
        User actual = userOptional.get();
        assertEquals(jack, actual);
        verify(mockDao).findById(12L);
    }

    @Test
    @DisplayName(value = "Test find User by Name invokes and return entity")
    void findUserByName() throws ServiceException, DaoException {
        when(mockDao.findByName("jack")).thenReturn(Optional.ofNullable(jack));
        Optional<User> optionalJack = userService.findByName("jack");
        assertTrue(optionalJack.isPresent());
        User actual = optionalJack.get();
        assertEquals(jack, actual);
        verify(mockDao).findByName("jack");
    }


    @Test
    @DisplayName(value = "Test save user invokes and verify")
    void save() throws ServiceException, DaoException {
        when(mockDao.save(jack)).thenReturn(true);
        boolean saved = userService.save(jack);
        verify(mockDao).save(jack);
        assertTrue(saved);
    }

    @Test
    @DisplayName(value = "Test user update invokes and verify")
    void edit() throws ServiceException, DaoException {
        when(mockDao.edit(jack)).thenReturn(jack);
        User actual = userService.edit(jack);
        verify(mockDao).edit(jack);
        assertEquals(jack, actual);
    }

    @Test
    @DisplayName(value = "Test get users invokes and verify")
    void getAllUserWithParameters() throws ServiceException {
        Map<String, String> params = new HashMap<>();
        when(userService.findAllBy(params)).thenReturn(users);
        List<User> userList = userService.findAllBy(params);
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test get All Users invokes and check not Null")
    void getAllUsers() throws ServiceException {
        when(userService.findAllBy()).thenReturn(users);
        List<User> userList = userService.findAllBy();
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test get All Users with Query invokes and check not Null")
    void getAllUsersWithQuery() throws ServiceException {
        when(mockDao.findAll("id BETWEEN 11 AND 12")).thenReturn(users);
        List<User> userList = userService.findAllBy("id BETWEEN 11 AND 12");
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test find all Users invokes and return size of List users")
    void addUser() throws ServiceException {
        when(mockDao.findAll()).thenReturn(users);
        assertEquals(2, userService.findAllBy().size());
    }

    @Test
    @DisplayName(value = "Test save User invokes and return true")
    void saveUser() throws ServiceException, DaoException {
        when(mockDao.save(bob)).thenReturn(true);
        boolean isSaved = userService.save(bob);
        verify(mockDao).save(bob);
        assertTrue(isSaved);
    }

    @Test
    @DisplayName(value = "Test get user by name and return optional users")
    void findUserById() {
        Optional<User> optionalUser = Optional.ofNullable(bob);
        Long id = optionalUser.orElseThrow().getUserId();
        when(mockDao.findById(id)).thenReturn(optionalUser);
        Optional<User> actual = userService.findBy(id);
        assertEquals(optionalUser, actual);
        verify(mockDao).findById(id);
    }
}