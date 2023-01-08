package com.enterprise.rental.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order orderBuilder;
    Order order;
    Timestamp timestamp;

    @BeforeEach
    void init() {
        order = new Order();
        timestamp = new Timestamp(System.currentTimeMillis());
        orderBuilder = new Order.Builder()
                .orderId(10L)
                .userId(11L)
                .carId(12L)
                .payment(100.0)
                .reason("Reason")
                .passport("Passport")
                .damage("Damage")
                .driver(true)
                .rejected(true)
                .closed(true)
                .term(timestamp)
                .build();
    }

    @Test
    @DisplayName(value = "Test Order ID and get Long")
    void testNewOrderID() {
        assertNotNull(order.getOrderId());
        Long id = 10l;
        order.setOrderId(id);
        assertEquals(id, order.getOrderId());
    }


    @Test
    @DisplayName(value = "Test User ID and get Long")
    void testSetNewOrderUserID() {
        Long id = 11l;
        order.setUserId(id);
        assertEquals(id, order.getUserId());
        assertNotNull(order.getUserId());
    }

    @Test
    @DisplayName(value = "Test Car ID")
    void testSetNewOrderCarID() {
        Long id = 12l;
        order.setCarId(id);
        assertEquals(id, order.getCarId());
        assertNotNull(order.getCarId());
    }

    @Test
    @DisplayName(value = "Test Order Reason and get String")
    void testSetNewOrderReason() {
        String reason = "Reason";
        order.setReason(reason);
        assertEquals(reason, order.getReason());
        assertNotNull(order.getReason());
    }

    @Test
    @DisplayName(value = "Test Order Passport and get String")
    void testSetNewOrderPassport() {
        String passport = "Passport";
        order.setPassport(passport);
        assertEquals(passport, order.getPassport());
        assertNotNull(order.getPassport());
    }

    @Test
    @DisplayName(value = "Test Order Damage and get String")
    void testSetNewOrderDamage() {
        String damage = "Damage";
        order.setDamage(damage);
        assertEquals(damage, order.getDamage());
        assertNotNull(order.getDamage());
    }

    @Test
    @DisplayName(value = "Test Order phone and get String")
    void testSetNewOrderPhone() {
        String phone = "+380123456789";
        order.setPhone(phone);
        assertEquals(phone, order.getPhone());
        assertNotNull(order.getPhone());
    }

    @Test
    @DisplayName(value = "Test Order Term and get Timestamp")
    void testSetNewOrderTerm() {
        order.setTerm(timestamp);
        assertEquals(timestamp, order.getTerm());
        assertNotNull(order.getTerm());
    }

    @Test
    @DisplayName(value = "Test Order Created and get Timestamp")
    void testSetNewOrderCreated() {
        order.setCreated(timestamp);
        assertEquals(timestamp, order.getCreated());
        assertNotNull(order.getCreated());
    }

    @Test
    @DisplayName(value = "Test Order Driver and get Boolean")
    void testSetNewOrderDriver() {
        boolean driver = true;
        order.setDriver(driver);
        assertEquals(driver, order.isDriver());
        assertTrue(order.isDriver());
    }

    @Test
    @DisplayName(value = "Test Order Rejected and get Boolean")
    void testSetNewOrderRejected() {
        boolean rejected = true;
        order.setRejected(rejected);
        assertEquals(rejected, order.isRejected());
        assertTrue(order.isRejected());
    }

    @Test
    @DisplayName(value = "Test Order Closed and get Boolean")
    void testSetNewOrderClosed() {
        boolean closed = true;
        order.setClosed(closed);
        assertEquals(closed, order.isClosed());
        assertTrue(order.isClosed());
    }

    @Test
    @DisplayName(value = "Test Order Payment and get double")
    void testNewOrderPayment() {
        double pay = 100.0;
        order.setPayment(pay);
        assertNotNull(order.getPayment());
        assertEquals(pay, order.getPayment());
    }

}