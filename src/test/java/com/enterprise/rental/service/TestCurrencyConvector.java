package com.enterprise.rental.service;

import com.enterprise.rental.service.locale.CurrencyConvector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.junit.jupiter.api.Test;

import static com.enterprise.rental.service.locale.CurrencyConvector.exchange;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCurrencyConvector {
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void testCurrencyConvector() {

        CurrencyConvector currencyConvector = new CurrencyConvector();
        System.out.println(currencyConvector.exchangeMultiply(1D, "USD"));
        assertEquals(35, currencyConvector.exchangeMultiply(1D, "USD"), 2.001);
    }

    @Test
    void testCurrency() {

        CurrencyConvector currencyConvector = new CurrencyConvector();
        double usd = currencyConvector.exchangeDivide(3600D, "USD");
        System.out.println(usd);
        assertEquals(100, usd, 2.001);
    }

    @Test
    void testCurrencyDiscount() {

        CurrencyConvector currencyConvector = new CurrencyConvector();
        double discount = currencyConvector.discount(25, 100);
        LOGGER.log(Level.INFO, discount);
        assertEquals(25, discount, 0.001);
    }

    @Test
    void testCurrencyStatic() {
        CurrencyConvector currencyConvector = new CurrencyConvector();
        double usd = currencyConvector.exchangeMultiply(1D, "USD");
        LOGGER.log(Level.INFO, usd);
        assertEquals(exchange, usd, 2.001);
    }
}