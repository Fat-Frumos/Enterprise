package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.Order;

import static java.lang.StrictMath.round;

public class OrderService {

    public double getCost(double price, int days) {
        return round((price * days) * 100) / 100.0;
    }

    private final JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();

    public boolean createOrder(Order order) {
        return jdbcOrderDao.save(order);
    }
}