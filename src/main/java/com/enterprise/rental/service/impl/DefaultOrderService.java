package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.*;
import com.enterprise.rental.service.OrderService;

import java.util.*;

/**
 * Provides storage and access to Order objects, implementations of {@link OrderService} interface.
 * The OrderService provides information useful for forcing an order to create, edit, or remove,
 * and retrieving information about the orders of user who is currently logged-in.
 *
 * @author Pasha Pollack
 * @see JdbcOrderDao
 */
public class DefaultOrderService implements OrderService {

    /**
     * Autowired OrderDao
     */
    private final OrderDao orderDao;

    /**
     * Init JdbcCarDao in constructor
     */
    public DefaultOrderService() {
        orderDao = new JdbcOrderDao();
    }

    /**
     * Set carDao in constructor
     *
     * @param orderDao the entity of type OrderCao
     */
    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    /**
     * Is used to save the given Order in the database.
     *
     * @param order the entity of type Order
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean save(Order order) {
        return orderDao.save(order);
    }

    /**
     * Is used to save the given Invoice in the database.
     *
     * @param invoice the entity of type Order
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean save(Invoice invoice) {
        return orderDao.save(invoice);
    }

    /**
     * Retrieves the Order with the specified id
     * Is used to return an order from the database by the given id,
     * otherwise {@code Optional.empty}.
     *
     * @param id The id of the car to be returned.
     * @return the <code>Optional Car</code>
     * or <code>Optional.empty</code> if the car was not found
     * @see Optional#empty
     */
    @Override
    public Optional<Order> getById(long id) {

        return id != 0
                ? orderDao.findById(id)
                : Optional.empty();
    }

    /**
     * Is used to Find all cars from the database
     *
     * @return the {@link List} of {@link Car}
     * and {@link List#isEmpty()} if no results are found
     */
    @Override
    public List<Order> getAll() {
        return orderDao.findAll();
    }

    /**
     * <p>Retrieves all defined used</p>
     * Is used to get Cars from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of all Generic Cars.
     */
    @Override
    public List<Order> getAll(String query) {
        return orderDao.findAll(query);
    }

    /**
     * Find all cars with parameters key-value map
     *
     * @param params Map (String key, String value)
     * @return the list of cars
     */
    @Override
    public List<Order> getAll(Map<String, String> params) {
        return orderDao.findAll();
    }

    /**
     * <p>Retrieves all defined used with query parameter</p>
     * Is used to get User Orders from the database.
     *
     * @param user the User.
     * @return the collection of all Generic Order.
     */
    @Override
    public List<Order> getAll(User user) {
        return user.isActive()
                && Objects.equals(user.getRole(), Role.USER.role())
                ? getAll(String.valueOf(user.getUserId()))
                : new ArrayList<>();
    }

    /**
     * <p>Update the Order with changes date to database</p>
     * Is used to edit the given order.
     *
     * @param order The entity to update
     * @return Order changed object.
     */
    @Override
    public Order edit(Order order) {
        return orderDao.edit(order);
    }

    /**
     * <p>Removes the Order by ID</p>
     * Is used to delete the order with the given id from the database.
     *
     * @param id the resource ID.
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean delete(long id) {

        if (id != 0 && getById(id).isPresent()) {
            return orderDao.delete(id);
        }
        return false;
    }
}