package com.enterprise.rental.service;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//implements Service<Order>
public class OrderService  {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderService() {
        orderDao = new JdbcOrderDao();
    }

    public boolean save(Order order) {
        return orderDao.save(order);
    }

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    public List<Order> getAll(String name) {
        return orderDao.findAll(name);
    }

    public List<Order> getAll(User user) {
        return user.isActive()
                && Objects.equals(user.getRole(), Role.USER.role())
                ? getAll(String.valueOf(user.getUserId()))
                : new ArrayList<>();
    }

    public Order edit(Order order) {
        return orderDao.edit(order);
    }

    public boolean delete(long id) {
        if (id != 0 && getById(id).isPresent()) {
            return orderDao.delete(id);
        }
        return false;
    }

    public Optional<Order> getById(long id) {

        return id != 0 ? orderDao.findById(id) : Optional.empty();
    }

    public boolean createInvoice(Invoice invoice) {
        return orderDao.save(invoice);
    }
}