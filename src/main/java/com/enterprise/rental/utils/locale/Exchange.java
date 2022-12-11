package com.enterprise.rental.utils.locale;

public enum Exchange {
    UAH("UAH"), USD("USD"), EUR("EUR");
    private final String name;

    Exchange(String name) {
        this.name = name;
    }

    public String get() {
        return name;
    }
}