package com.enterprise.rental.service;

import static java.lang.StrictMath.round;

public class OrderService {

    public double getPrice(double price, int days) {
        return round((price * days) * 100) / 100.0;
    }
}