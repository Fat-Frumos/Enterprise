package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Service class for managing Car, implementations of {@link CarService} interface.
 * The CarService provides information useful for forcing a car to create, edit, or remove,
 * and retrieving information about the cars of user who is currently logged-in.
 *
 * @author Pasha Pollack
 * @see JdbcCarDao
 */
public class DefaultCarService implements CarService {
    private static final Logger LOGGER = LogManager.getLogger();

    private final CarDao carDao;

    /**
     * Init JdbcCarDao in constructor
     */
    public DefaultCarService() {
        this.carDao = new JdbcCarDao();
    }

    /**
     * Autowired carDao in constructor
     *
     * @param carDao the entity of type CarDao
     */
    public DefaultCarService(CarDao carDao) {
        this.carDao = carDao;
    }

    /**
     * Is used to save the given Car in the database.
     *
     * @param car the entity of type Car
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean save(Car car) throws ServiceException {
        try {
            return carDao.save(car);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Count of all cars from Database.
     *
     * @return number of all cars
     */
    public Integer getNumberOfRows() {
        return carDao.countId("SELECT COUNT(id) FROM car");
    }

    /**
     * Retrieves the Car with the specified id.
     * <p>
     * Is used to return a car from the database by the given id,
     * otherwise {@code Optional.empty}.
     *
     * @param id The id of the car to be returned.
     * @return the <code>Optional Car</code>
     * or <code>Optional.empty</code> if the car was not found
     * @see Optional#empty
     */
    @Override
    public Optional<Car> findBy(Long id) {
        return carDao.findById(id);
    }

    /**
     * Find all cars and returns a sequential IntStream for the range of int elements.
     * Randomly permutes the specified list using a default source of randomness.
     *
     * @param size max cars
     * @return the list of cars
     */
    public List<Car> getRandom(int size) {

        List<Car> list = null;
        try {
            list = new ArrayList<>(carDao.findAll());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }

        Collections.shuffle(list);
        return IntStream.range(0, size)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    /**
     * Is used to find all cars from the database
     *
     * @return the {@link List} of {@link Car}
     * and {@link List#isEmpty()} if no results are found
     */
    @Override
    public List<Car> findAllBy() throws ServiceException {
        try {
            return carDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all cars with parameters.
     *
     * @param params Map (String key, String value): pagination, sort, price, cost, name
     * @param offset for sql query
     * @return the list of cars
     */
    @Override
    public List<Car> findAllBy(Map<String, String> params, int offset) throws ServiceException {
        try {
            return carDao.findAll(params, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all cars with parameters key-value map:
     * pagination, sort by price, cost, name,
     * id, brand, model, asc/desc direction
     *
     * @param params Map (String key, String value)
     * @return the list of cars
     */
    @Override
    public List<Car> findAllBy(Map<String, String> params) throws ServiceException {

        try {
            return carDao.findAll(params);
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
    public List<Car> findAllBy(String query) throws ServiceException {
        try {
            return carDao.findAll(query);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "{}", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Update the Car with changes date to database</p>
     * Is used to edit the given car.
     *
     * @param car The entity to update
     * @return Car changed object.
     */
    @Override
    public Car edit(Car car) throws ServiceException {
        try {
            return carDao.edit(car);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "{}", e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Removes the Car by ID</p>
     * Is used to delete the car with the given id from the database.
     *
     * @param id the resource ID.
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return carDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
