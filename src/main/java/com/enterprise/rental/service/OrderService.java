package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.OrderNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();

    public boolean createOrder(Order order) {
        return jdbcOrderDao.save(order);
    }

    public List<Order> getAll() {
        return jdbcOrderDao.findAll();
    }

    public List<Order> getUserOrders(User user) {
        if (user.isActive()) {
            return jdbcOrderDao.findAll(String.valueOf(user.getUserId()));
        } else {
            throw new OrderNotFoundException("Orders " + user.getOrders());
        }
        //TODO: 443 manager
    }

    public Order updateOrder(Order order) {
        Order edit = jdbcOrderDao.edit(order);
        return edit;
    }
}