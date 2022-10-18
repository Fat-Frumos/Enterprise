package com.enterprise.rental.service;

import static java.lang.StrictMath.round;

public class OrderService {

    public double getCost(double price, int days) {
        return round((price * days) * 100) / 100.0;
    }
}