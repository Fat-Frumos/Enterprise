package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderService {

    private final JdbcOrderDao jdbcOrderDao = new JdbcOrderDao();

    public boolean createOrder(Order order) {
        return jdbcOrderDao.save(order);
    }

    public List<Order> getAll() {
        return jdbcOrderDao.findAll();
    }

    public List<Order> getAll(User user) {
        List<Order> orderList = new ArrayList<>();
        if (user.isActive() && Objects.equals(user.getRole(), "user")) {
            orderList = jdbcOrderDao.findAll(user.getUserId());
        }
        return orderList;
    }

    public Order updateOrder(Order order) {
        return jdbcOrderDao.edit(order);
    }

    public boolean delete(String id) {
        if (id != null && getById(id).isPresent()) {
            return jdbcOrderDao.delete(Long.parseLong(id));
        }
        return false;
    }

    public Optional<Order> getById(String id) {
        if (id != null) {
            long parseLong = Long.parseLong(id);
            if (parseLong != 0) {
                return jdbcOrderDao.findById(parseLong);
            }
        }
        return Optional.empty();
    }
}