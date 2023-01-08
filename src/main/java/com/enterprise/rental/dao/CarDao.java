package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * <p>Resource collection API of Cars
 * <code>CarDao</code> extends <code>Dao</code> interface
 * for performing CRUD operations on instances of {@link Car}.
 *
 * @author Pasha Polyak
 */
public interface CarDao extends Dao<Car> {

    /**
     * Find all cars with parameters.
     *
     * @param params Map (String key, String value):
     *               pagination, sort, price, cost, name
     * @return the list of cars
     */
    List<Car> findAll(Map<String, String> params) throws DaoException;

    /**
     * Find all cars with parameters and offset.
     *
     * @param params Map (String key, String value)
     * @param offset range of query
     * @return the list of cars
     */
    List<Car> findAll(Map<String, String> params, int offset) throws DaoException;

    /**
     * Count of all cars from DataBase.
     * @param sql query
     * @return size of all cars
     */
    Integer countId(String sql);

}
