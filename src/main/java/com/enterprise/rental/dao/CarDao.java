package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;

/**
 * <p>Resource collection API of Cars
 * <code>CarDao</code> extends <code>Dao</code> interface
 * for performing CRUD operations on instances of {@link Car}.
 *
 * @author Pasha Pollack
 */
public interface CarDao extends Dao<Car> {

    /**
     * Find all cars with parameters.
     *
     * @param params Map (String key, String value):
     *               pagination, sort, price, cost, name
     * @return the list of cars
     */
    List<Car> findAll(Map<String, String> params);

    /**
     * Find all cars with parameters and offset.
     *
     * @param params Map (String key, String value)
     * @param offset range of query
     * @return the list of cars
     */
    List<Car> findAll(Map<String, String> params, int offset);

    /**
     * Count of all cars from DataBase.
     * @param sql query
     * @return size of all cars
     */
    Integer countId(String sql);

}
