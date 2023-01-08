package com.enterprise.rental.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParameterTest {
    @Test
    void values() {
        for (Parameter value : Parameter.values()) {
            assertNotNull(value);
        }
    }

    @Test
    void valueOfClient() {
        assertEquals("user", Parameter.CLIENT.value());
    }

    @Test
    void valueOfManager() {
        assertEquals("cars", Parameter.CARS.value());
    }

    @Test
    void valueOfUser() {
        assertEquals("car", Parameter.CAR.value());
    }

    @Test
    void valueOfGuest() {
        assertEquals("role", Parameter.ROLE.value());
    }

    @Test
    void valueOfAuto() {
        assertEquals("auto", Parameter.AUTO.value());
    }

    @Test
    void valueOfPage() {
        assertEquals("page", Parameter.PAGE.value());
    }

    @Test
    void valueOfOrder() {
        assertEquals("orders", Parameter.ORDER.value());
    }
}