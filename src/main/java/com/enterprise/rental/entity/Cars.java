package com.enterprise.rental.entity;

import java.util.HashSet;
import java.util.Set;

public class Cars {
    private static Cars instance;

    protected Set<Car> car;

    public static Cars getInstance() {
        return instance;
    }

    public Set<Car> getCars() {
        if (car == null) {
            car = new HashSet<>();
        }
        return this.car;
    }

    @Override
    public String toString() {
        return "Cars=" + car;
    }
}
