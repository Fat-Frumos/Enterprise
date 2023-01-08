package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.*;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Provides storage and access to Order objects, implementations of {@link OrderService} interface.
 * The OrderService provides information useful for forcing an order to create, edit, or remove,
 * and retrieving information about the orders of user who is currently logged-in.
 *
 * @author Pasha Polyak
 * @see JdbcOrderDao
 */
public class DefaultOrderService implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger();

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
    public boolean save(Order order) throws ServiceException {
        try {
            return orderDao.save(order);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "{}", e.getMessage());
            throw new ServiceException(e);
        }
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
    public Optional<Order> findBy(Long id) {

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
    public List<Order> findAllBy() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Retrieves all defined used</p>
     * Is used to get Cars from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of all Generic Cars.
     */
    @Override
    public List<Order> findAllBy(String query) throws ServiceException {
        try {
            return orderDao.findAll(query);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all cars with parameters key-value map
     *
     * @param params Map (String key, String value)
     * @return the list of cars
     */
    @Override
    public List<Order> findAllBy(Map<String, String> params) throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Retrieves all defined used with query parameter</p>
     * Is used to get User Orders from the database.
     *
     * @param user the User.
     * @return the collection of all Generic Order.
     */
    @Override
    public List<Order> getAll(User user) throws ServiceException {
        try {
            return user.isActive()
                    && Objects.equals(user.getRole(), Role.USER.role())
                    ? findAllBy(String.valueOf(user.getUserId()))
                    : new ArrayList<>();
        } catch (ServiceException e) {
            throw new ServiceException(e);
        }
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
    public boolean delete(Long id) throws ServiceException {

        if (id != 0 && this.findBy(id).isPresent()) {
            try {
                return orderDao.delete(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
}