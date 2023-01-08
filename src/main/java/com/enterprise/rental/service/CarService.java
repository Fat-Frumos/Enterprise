package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * The service interface for Car
 * extends abstract Service {@link Car}
 * Base class for all Car specific services.
 *
 * @author Pasha Polyak
 */
public interface CarService extends Service<Car> {

    /**
     * Find all cars with parameters.
     *
     * @param params map: pagination, sort, price, cost, name
     * @return the list of cars
     */
    List<Car> findAllBy(Map<String, String> params) throws ServiceException;

    /**
     * Find all cars with parameters.
     *
     * @param params map: pagination, sort, price, cost, name
     * @param offset for sql query
     * @return the list of cars
     */
    List<Car> findAllBy(Map<String, String> params, int offset) throws ServiceException;

    /**
     * Count of all cars from DataBase.
     *
     * @return number of cars
     */
    Integer getNumberOfRows();

    /**
     * Randomly permutes the specified list using a default source of randomness.
     * All permutations occur with approximately equal likelihood.
     *
     * @param n max cars
     * @return the list of cars
     */
    List<Car> getRandom(int n);
}
