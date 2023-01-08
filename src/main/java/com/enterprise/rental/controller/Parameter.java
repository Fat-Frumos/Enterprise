package com.enterprise.rental.controller;

public enum Parameter {

    CLIENT("user"), CARS("cars"), CAR("car"), ROLE("role"), AUTO("auto"), PAGE("page"), ORDER("orders"), ERROR("errorMessage");
    private final String value;

    Parameter(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
