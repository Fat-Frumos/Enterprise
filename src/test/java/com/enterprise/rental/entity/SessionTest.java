package com.enterprise.rental.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionTest {

    Session session;
    User bob;

    @BeforeEach
    void init() {

        bob = new User.Builder()
                .userId(11L)
                .name("John")
                .password("password")
                .passport("passport")
                .language("ua")
                .email("email@i.ua")
                .active(true)
                .role("user")
                .build();

//        session = new Session("user-token", false, bob, new HashMap<>());
    }

    @Test
    @DisplayName(value = "Test Set Session Expired")
    void testSetExpiredNewSession() {
        boolean expired = true;
        Session session = new Session();
        session.setExpired(expired);
        assertTrue(session.isExpired());
    }

    @Test
    @DisplayName(value = "Test Set Session User")
    void testSetUserNewSession() {
        Session session = new Session();
        session.setUser(bob);
        assertEquals(bob, session.getUser());
    }

    @Test
    @DisplayName(value = "Test Order Reason and get String")
    void testSetNewOrderReason() {
        String token = "user-token";
        Session session = new Session();
        session.setToken(token);
        assertEquals(token, session.getToken());
    }
}