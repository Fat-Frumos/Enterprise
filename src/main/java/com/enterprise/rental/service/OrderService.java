package com.enterprise.rental.service;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public OrderService() {
        orderDao = new JdbcOrderDao();
    }

    public boolean createOrder(Order order) {
        return orderDao.save(order);
    }

    public List<Order> getAll() {
        return orderDao.findAll();
    }

    public List<Order> getAll(User user) {
        List<Order> orderList = new ArrayList<>();
        if (user.isActive() && Objects.equals(user.getRole(), "user")) {
            orderList = orderDao.findAll(String.valueOf(user.getUserId()));
        }
        return orderList;
    }

    public Order updateOrder(Order order) {
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
        //TODO
//        return orderDao.save(invoice);
        return false;
    }
}