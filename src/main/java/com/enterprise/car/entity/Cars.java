package com.enterprise.car.entity;


import java.util.ArrayList;
import java.util.List;

public class Cars {
    private static Cars instance;

    protected List<Car> car;

    public static Cars getInstance() {
        return instance;
    }

    public List<Car> getCars() {
        if (car == null) {
            car = new ArrayList<>();
        }
        return this.car;
    }

    @Override
    public String toString() {
        return "Cars=" + car;
    }
}
