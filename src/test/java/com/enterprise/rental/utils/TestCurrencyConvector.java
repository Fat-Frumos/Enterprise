package com.enterprise.rental.utils;

import com.enterprise.rental.utils.locale.CurrencyConvector;
import org.junit.jupiter.api.Test;

class TestCurrencyConvector {

    @Test
    void testCurrencyConvector() {

        CurrencyConvector currencyConvector = new CurrencyConvector();
        currencyConvector.updateExchange();
        System.out.println(currencyConvector.exchangeTo(400D, "USD"));
        System.out.println(currencyConvector.exchangeFrom(400D, "USD"));

    }

}