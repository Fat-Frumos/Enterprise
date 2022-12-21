package com.enterprise.rental.utils;

import com.enterprise.rental.service.locale.CurrencyConvector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCurrencyConvector {

    @Test
    void testCurrencyConvector() {

        CurrencyConvector currencyConvector = new CurrencyConvector();
        System.out.println(currencyConvector.exchangeMultiply(1D, "USD"));
        assertEquals(35, currencyConvector.exchangeMultiply(1D, "USD"), 2.001);
    }
}