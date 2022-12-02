package com.enterprise.rental.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceEmailTest {

    Invoice invoice;

    @BeforeEach
    void init() {
        invoice = new Invoice(1L, 2L, "Damage", "passport", "phone", "reason", "email", 100.0);
    }


    @Test
    @DisplayName(value = "Test Invoice ID and check all fields")
    void testNewInvoiceID() {
        assertNotNull(invoice.getDamage());
        assertNotEquals(0, invoice.getInvoiceId());
        assertNotEquals(0, invoice.getCarId());
        assertNotEquals(0, invoice.getPayment());
        assertNotEquals(0, invoice.getUserId());

    }


    @Test
    @DisplayName(value = "Test Invoice User ID")
    void testSetNewInvoiceUserID() {
        assertEquals(2L, invoice.getCarId());
    }

    @Test
    @DisplayName(value = "Test Invoice Car ID")
    void testSetNewInvoiceCarID() {
        assertEquals(2L, invoice.getCarId());
    }


    @Test
    @DisplayName(value = "Test Invoice Damage and get String")
    void testSetNewInvoiceDamage() {
        String damage = "Damage";
        assertEquals(damage, invoice.getDamage());
    }


    @Test
    @DisplayName(value = "Test Invoice Payment and get double")
    void testNewInvoicePayment() {
        double pay = 100.0;
        assertEquals(pay, invoice.getPayment());
    }
}