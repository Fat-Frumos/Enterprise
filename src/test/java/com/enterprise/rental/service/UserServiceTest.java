package com.enterprise.rental.service;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private static final UserDao mockDao = mock(UserDao.class);

    private static final UserService userService = new UserService(mockDao);

    List<User> users = new ArrayList<>();
    User john = new User.Builder()
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

    @BeforeEach
    void init() {
        users.add(jack);
        users.add(john);
    }

    @Test
    @DisplayName(value = "Test find User by Id invokes and return true")
    void findUser() {
        when(mockDao.findById(12L)).thenReturn(Optional.ofNullable(jack));
        Optional<User> actual = userService.getById(12L);
        assertEquals(jack, actual);
        verify(mockDao).findById(12L);
    }


    @Test
    @DisplayName(value = "Test find User by Name invokes and return entity")
    void findUserByName() {
        when(mockDao.findByName("jack")).thenReturn(Optional.ofNullable(jack));
        User actual = userService.findByName("jack").get();
        assertEquals(jack, actual);
        verify(mockDao).findByName("jack");
    }


    @Test
    @DisplayName(value = "Test save user invokes and verify")
    void save() {
        when(mockDao.save(jack)).thenReturn(true);
        boolean saved = userService.save(jack);
        verify(mockDao).save(jack);
        assertTrue(saved);
    }

    @Test
    @DisplayName(value = "Test get All Users invokes and check not Null")
    void getAllUsers() {
        when(userService.getAll()).thenReturn(users);
        List<User> userList = userService.getAll();
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test find all Users invokes and return size of List users")
    void addUser() {
        when(userService.getAll()).thenReturn(users);
        assertEquals(2, userService.getAll().size());
    }
}