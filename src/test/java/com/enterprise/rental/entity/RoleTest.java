package com.enterprise.rental.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleTest {

    @Test
    void values() {
        for (Role value : Role.values()) {
            assertNotNull(value);
        }
    }

    @Test
    void valueOfAdmin() {
        assertEquals("admin", Role.ADMIN.role());
    }

    @Test
    void valueOfManager() {
        assertEquals("manager", Role.MANAGER.role());
    }

    @Test
    void valueOfUser() {
        assertEquals("user", Role.USER.role());
    }

    @Test
    void valueOfGuest() {
        assertEquals("guest", Role.GUEST.role());
    }
}