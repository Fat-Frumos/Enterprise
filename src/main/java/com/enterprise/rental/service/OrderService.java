package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Order;

import java.util.List;

public class OrderService {

    private final JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();

    public boolean createOrder(Order order) {
        return jdbcOrderDao.save(order);
    }

    public List<Order> getAll() {
        return jdbcOrderDao.findAll();
    }
}