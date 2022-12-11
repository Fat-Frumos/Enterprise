package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultOrderService implements OrderService {
    private final OrderDao orderDao;

    public DefaultOrderService() {
        orderDao = new JdbcOrderDao();
    }

    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public boolean save(Order order) {

        return orderDao.save(order);
    }

    @Override
    public List<Order> getAll() {

        return orderDao.findAll();
    }

    @Override
    public List<Order> getAll(String name) {

        return orderDao.findAll(name);
    }

    @Override
    public List<Order> getAll(User user) {
        return user.isActive()
                && Objects.equals(user.getRole(), Role.USER.role())
                ? getAll(String.valueOf(user.getUserId()))
                : new ArrayList<>();
    }

    @Override
    public Order edit(Order order) {
        return orderDao.edit(order);
    }

    @Override
    public boolean delete(long id) {

        if (id != 0 && getById(id).isPresent()) {
            return orderDao.delete(id);
        }
        return false;
    }

    @Override
    public Optional<Order> getById(long id) {

        return id != 0
                ? orderDao.findById(id)
                : Optional.empty();
    }

    @Override
    public boolean save(Invoice invoice) {

        return orderDao.save(invoice);
    }
}