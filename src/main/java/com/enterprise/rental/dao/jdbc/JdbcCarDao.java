package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.DbManager.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.JdbcCarTemplate.*;

/**
 * Java class that represent an implementing CarDao
 * Jdbc Car Dao performing CRUD operations on instances of {@link Car}.
 * JdbcCarDao the Java API that manages connecting to a database,
 * issuing queries and commands, and handling result sets obtained from the database.
 *
 * @author Pasha Pollack
 * @see OrderDao
 */
public class JdbcCarDao implements CarDao {
    private static final Logger log = Logger.getLogger(JdbcCarDao.class);
    private static final List<Car> carList = getCarsQuery(FIND_ALL_SQL);

    @Override
    public List<Car> findAll() {
        return carList;
    }

    @Override
    public Optional<Car> findById(Long id) {
        return getCarById(id);
    }

    @Override
    public Optional<Car> findByName(String name) {
        return getCarQuery(name);
    }

    @Override
    public List<Car> findAll(String query) {
        String sql = String.format("%s %s;",
                FILTER_CAR_BY_SQL, query);
        return getCarsQuery(sql);
    }

    @Override
    public boolean save(Car car) {
        return setCarQuery(car);
    }

    @Override
    public Car edit(Car car) {
        long id = car.getId();
        String brand = car.getBrand();
        String model = car.getModel();
        String name = car.getName();
        Double price = car.getPrice();
        Double cost = car.getCost();

        String query = String.format(
                UPDATE_CAR_SQL,
                brand, model, name, price, cost, id);

        return updateCar(car, query);
    }


    @Override
    public boolean delete(long id) {

        Optional<Car> optionalCar = getCarById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            String query = String.format("DELETE FROM car where id = %d", id);
            Car updatedCar = updateCar(car, query);
            log.debug(String.format("%sDeleted: %n%s%s %s%s", RED, updatedCar, YELLOW, query, RESET));
            return true;
        }
        return false;
    }

    @Override
    public List<Car> findAll(
            Map<String, String> params,
            int offset) {

        String query = getQuery(params);

        return getCarsQuery(query);
    }

    private static Car updateCar(Car car, String query) {
        log.debug(String.format("%s%s%s", YELLOW, query, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = configConnection();
            statement = connection.prepareStatement(query);
            boolean update = statement.executeUpdate() > 0;
            log.debug(String.format("update car %s", update));
            connection.commit();
            return car;
        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            throw new OrderNotFoundException(sqlException);
        } finally {
            eventually(connection, statement);
        }
    }

    @Override
    public Integer countId(String sql) {
        log.debug(String.format("%s%s%s", YELLOW, sql, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = configConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            connection.commit();

            return resultSet.next() ? resultSet.getInt(1) : 0;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            log.debug("Vehicle not found");
            return 0;
        } finally {
            eventually(connection, statement, resultSet);
        }
    }

    @Override
    public List<Car> findAll(
            Map<String, String> params) {
        return params == null
                ? findAll()
                : findAll(params, 0);
    }

    private static String getQuery(
            Map<String, String> params) {

        String sort = params.get("sort");
        sort = sort == null ? "" : sort;

        String direction = params.get("direction");
        direction = direction == null ? "cost" : direction;

        String brand = params.get("brand");
        brand = brand == null ? " " : String.format(" brand='%s' AND", brand);

        int price = getPrice(params);

        int limit = getLimit(params.get("limit"));

        int page = getPage(params);
        int start = page * limit - limit;

        if (start < 0) {
            start = 0;
        }

        return String.format("%s%s price>=%d ORDER BY %s %s LIMIT %d OFFSET %d;",
                FILTER_CAR_BY_SQL, brand, price, direction, sort, limit, start);
    }

    private static int getPage(Map<String, String> params) {
        int page;
        try {
            page = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }
        return page;
    }

    private static int getLimit(String records) {
        return records
                != null && Integer.parseInt(records) >= 1
                ? Integer.parseInt(records)
                : 10;
    }

    private static int getPrice(Map<String, String> params) {
        int price;
        try {
            price = Integer.parseInt(params.get("price"));
        } catch (NumberFormatException e) {
            price = 1;
        }
        return price;
    }
}
