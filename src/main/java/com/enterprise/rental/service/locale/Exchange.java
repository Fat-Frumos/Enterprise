package com.enterprise.rental.service.locale;

import static com.enterprise.rental.service.locale.CurrencyConvector.exchange;

/**
 * The enum for Currency Exchange Service Convector
 * Gets fields from json format NBUStatService
 * If fields contains a currency enum value
 * then set data to the locale currency rate
 * Added fields exchangeRate from
 *
 * @author Pasha Pollack
 */
public enum Exchange {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");
    private final String name;

    private final double exchangeRate;

    Exchange(String name) {
        this.name = name;
        exchangeRate = exchange;
    }

    public String get() {
        return name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}