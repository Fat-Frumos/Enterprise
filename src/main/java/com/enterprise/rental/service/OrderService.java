package com.enterprise.rental.service;

import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    boolean save(Order order);

    List<Order> getAll();

    List<Order> getAll(String name);

    List<Order> getAll(User user);

    Order edit(Order order);

    boolean delete(long id);

    Optional<Order> getById(long id);

    boolean save(Invoice invoice);
}
