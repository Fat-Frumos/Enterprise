package com.enterprise.rental.utils;

import com.enterprise.rental.service.locale.CurrencyConvector;
import org.junit.jupiter.api.Test;

import static com.enterprise.rental.service.locale.CurrencyConvector.exchange;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCurrencyConvector {

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
        System.out.println(discount);
        assertEquals(25, discount, 0.001);
    }

    @Test
    void testCurrencyStatic() {
        CurrencyConvector currencyConvector = new CurrencyConvector();
        double usd = currencyConvector.exchangeMultiply(1D, "USD");
        System.out.println(usd);
        assertEquals(exchange, usd, 2.001);
    }
}