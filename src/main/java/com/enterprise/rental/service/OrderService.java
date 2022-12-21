package com.enterprise.rental.service;

import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * The service interface for Order
 * extends abstract Service {@link Order}
 *
 * @author Pasha Pollack
 */
public interface OrderService extends Service<Order> {

    /**
     * Save the order to Database.
     *
     * @param order the type of the entity
     * @return true if method was executed and vice-versa
     */
    boolean save(Order order);

    /**
     * Find all Orders from database
     *
     * @return the list of Orders
     */
    List<Order> getAll();

    /**
     * Find all orders with String query parameter.
     *
     * @param name the parameter
     * @return the list of Orders
     */
    List<Order> getAll(String name);

    /**
     * Find all Orders by user.
     *
     * @param user the User
     * @return the list of Orders
     */
    List<Order> getAll(User user);

    /**
     * Update order.
     *
     * @param order Order
     * @return order type of the Order
     */
    Order edit(Order order);

    /**
     * Delete the order.
     *
     * @param id the id
     * @return true if method was executed and vice-versa
     */
    boolean delete(long id);

    /**
     * Find the Optional order by id.
     *
     * @param id the order id
     * @return Optional Order
     */
    Optional<Order> getById(long id);

    /**
     * Save the invoice to Database.
     *
     * @param invoice the type of entity
     * @return true if method was executed and vice-versa
     */
    boolean save(Invoice invoice);
}
