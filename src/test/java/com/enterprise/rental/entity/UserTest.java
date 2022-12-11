package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.service.impl.DefaultUserService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserTest {
    private static final Logger log = Logger.getLogger(UserTest.class);
    private static final List<User> users = new ArrayList<>();
    private static final Car X5 = new Car.Builder()
            .id(2L)
            .name("X5")
            .brand("BMW")
            .model("GT-2")
            .path("http//")
            .price(22000.0)
            .cost(10000.0)
            .year(2020)
            .build();
    User bob = new User.Builder()
            .userId(11L)
            .name("John")
            .password("password")
            .passport("passport")
            .phone("+380123456789")
            .language("ua")
            .salt(UUID.randomUUID().toString())
            .email("email@i.ua")
            .active(true)
            .role(Role.ADMIN.role())
            .build();

    User jack = new User.Builder()
            .userId(12L)
            .name("Jack")
            .password("111")
            .passport("passport")
            .phone("+380123456789")
            .salt(UUID.randomUUID().toString())
            .language("en")
            .email("jack@i.ua")
            .active(true)
            .role(Role.USER.role())
            .build();

    JdbcUserDao mockUserDao = mock(JdbcUserDao.class);
    UserService service = new DefaultUserService(mockUserDao);

    @BeforeEach
    void init() {
        users.add(jack);
        users.add(bob);
        service = new DefaultUserService(mockUserDao);
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
    @DisplayName(value = "Set User Active")
    void testSetActiveNewUser() {
        boolean active = true;
        User user = new User();
        user.setActive(active);
        assertTrue(user.isActive());
    }

    @Test
    @DisplayName(value = "Check User is Admin")
    void testCheckIfAdminNewUser() {
        assertTrue(bob.isAdmin());
        assertFalse(jack.isAdmin());
    }

    @Test
    @DisplayName(value = "Set User Closed")
    void testSetClosedNewUser() {
        boolean closed = true;
        User user = new User();
        user.setClosed(closed);
        assertTrue(user.isClosed());
    }

    @Test
    @DisplayName(value = "Set User Salt")
    void testSetSaltNewUser() {
        String salt = UUID.randomUUID().toString();
        User user = new User();
        user.setSalt(salt);
        assertEquals(salt, user.getSalt());
    }

    @Test
    @DisplayName(value = "Set User Phone")
    void testSetPhoneNewUser() {
        String phone = "+380123456789";
        User user = new User();
        user.setPhone(phone);
        assertEquals(phone, user.getPhone());
    }

    @Test
    @DisplayName(value = "Set User Password")
    void testSetPasswordNewUser() {
        String password = "12345";
        User user = new User();
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    @DisplayName(value = "Set User Passport")
    void testSetPassportNewUser() {
        String passport = "KA123456789";
        User user = new User();
        user.setPassport(passport);
        assertEquals(passport, user.getPassport());
    }

    @Test
    @DisplayName(value = "Set User Language")
    void testSetLanguageNewUser() {
        String lang = "ua";
        User user = new User();
        user.setLanguage(lang);
        assertEquals(lang, user.getLanguage());
    }

    @Test
    @DisplayName(value = "Set Rental Car")
    void testSetCarNewUser() {
        User user = new User();
        user.setCar(X5);
        assertEquals(X5, user.getCar());
    }

    @Test
    @DisplayName(value = "Add Rental Car into User Card")
    void testAddCarNewUser() {
        User user = new User();
        user.addCar(X5);
        assertEquals(X5, user.getCars().get(0));
    }

    @Test
    void testFindByBrand() {

        when(service.getAll("John")).thenReturn(users);

        log.debug(String.format("%s", service.getAll()));
        log.debug(String.format("%s", bob));
        log.debug(String.format("%s", users));

        assertEquals("John", bob.getName());
    }

    @Test
    @DisplayName(value = "Test Find All Users and check fields")
    void testFindAllReturnCorrectData() {
        when(mockUserDao.findAll()).thenReturn(users);
        assertFalse(users.isEmpty());

        for (User user : users) {
            assertNotEquals(0, user.getUserId());
            assertNotNull(user.getPassword());
            assertNotNull(user.getPassport());
            assertNotNull(user.getPhone());
            assertNotNull(user.getEmail());
            assertNotNull(user.getName());
            assertNotNull(user.getSalt());
            assertNotNull(user.getLanguage());
        }
    }

    @Test
    @DisplayName(value = "Test Find All Users")
    void testFindAllUsers() {
        when(mockUserDao.findAll()).thenReturn(users);
        List<User> auto = mockUserDao.findAll();
        assertEquals(auto, users);
    }

    @Test
    @DisplayName(value = "Test Find Users By Brand Id Length")
    void testFindUsersByBrandIdSize() {
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
