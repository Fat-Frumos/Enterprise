package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    Order edit(Order t);
}
