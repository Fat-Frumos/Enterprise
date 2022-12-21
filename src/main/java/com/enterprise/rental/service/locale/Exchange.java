package com.enterprise.rental.service.locale;

/**
 * The enum for Currency Exchange Service Convector
 * Gets fields from json format NBUStatService
 * If fields contains a currency enum value
 * then set data to the locale currency rate
 *
 * @author Pasha Pollack
 */
public enum Exchange {
    UAH("UAH"),
    USD("USD"),
    EUR("EUR");
    private final String name;

    Exchange(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}