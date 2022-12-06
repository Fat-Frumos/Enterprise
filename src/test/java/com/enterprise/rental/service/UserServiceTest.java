package com.enterprise.rental.service;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private static UserDao mockDao;

    private static UserService userService;

    List<User> users = new ArrayList<>();
    User bob = new User.Builder()
            .userId(11L)
            .name("Bob")
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


    @BeforeAll
    static void beforeAll() {
        mockDao = mock(JdbcUserDao.class);
        userService = new UserService(mockDao);
    }

    @BeforeEach
    void init() {
        users.add(jack);
        users.add(bob);
    }

    @Test
    @DisplayName(value = "Test find User by Id invokes and return true")
    void findUser() {
        when(mockDao.findById(12L)).thenReturn(Optional.ofNullable(jack));
        Optional<User> userOptional = userService.getById(12L);
        assertTrue(userOptional.isPresent());
        User actual = userOptional.get();
        assertEquals(jack, actual);
        verify(mockDao).findById(12L);
    }


    @Test
    @DisplayName(value = "Test find User by Name invokes and return entity")
    void findUserByName() {
        when(mockDao.findByName("jack")).thenReturn(Optional.ofNullable(jack));
        Optional<User> optionalJack = userService.findByName("jack");
        assertTrue(optionalJack.isPresent());
        User actual = optionalJack.get();
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
    @DisplayName(value = "Test delete user invokes and verify")
    void delete() {
        when(mockDao.delete(jack.getUserId())).thenReturn(true);
        boolean saved = userService.delete(jack.getUserId());
        verify(mockDao).delete(jack.getUserId());
        assertTrue(saved);
    }

    @Test
    @DisplayName(value = "Test user update invokes and verify")
    void edit() {
        when(mockDao.edit(jack)).thenReturn(jack);
        User actual = userService.edit(jack);
        verify(mockDao).edit(jack);
        assertEquals(jack, actual);
    }

    @Test
    @DisplayName(value = "Test get users invokes and verify")
    void getAllUserWithParameters() {
        Map<String, String> params = new HashMap<>();
        when(userService.getAll(params)).thenReturn(users);
        List<User> userList = userService.getAll(params);
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
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
    @DisplayName(value = "Test send Email to User invokes and check not false")
    void sendEmailToUser() {
//        when(userService.sendEmail(jack.getName())).thenReturn(true);
//        boolean sendEmail = userService.sendEmail(jack.getName());
//        assertTrue(sendEmail);

    }

    @Test
    @DisplayName(value = "Test get All Users with Query invokes and check not Null")
    void getAllUsersWithQuery() {
        when(userService.getAll("id BETWEEN 11 AND 12")).thenReturn(users);
        List<User> userList = userService.getAll("id BETWEEN 11 AND 12");
        userList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test find all Users invokes and return size of List users")
    void addUser() {
        when(userService.getAll()).thenReturn(users);
        assertEquals(2, userService.getAll().size());
    }

    @Test
    @DisplayName(value = "Test save User invokes and return true")
    void saveUser() {
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
        Optional<User> actual = userService.getById(id);
        assertEquals(optionalUser, actual);
        verify(mockDao).findById(id);
    }
}