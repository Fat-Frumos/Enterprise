package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.JdbcCarTemplate.*;

public class JdbcCarDao implements CarDao {

    @Override
    public List<Car> findAll() {
        return getCarsQuery(FIND_ALL_SQL);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return getCarById(id);
    }

    @Override
    public Optional<Car> findByName(String name) {
        return getCarQuery(FILTER_BY_CAR_NAME_SQL, name);
    }

    @Override
    public List<Car> findAll(String params) {
        String sql = String.format("%sWHERE %s;",
                FILTER_CAR_BY_SQL, params);
        return getCarsQuery(sql);
    }

    @Override
    public boolean save(Car car) {
        return setCarQuery(car);
    }

    @Override
    public Car edit(Car car) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Car> findAll(Map<String, String> params, int offset) {

        String sort = params.get("sort");
        sort = sort == null ? "" : sort;

        String direction = params.get("direction");
        direction = direction == null ? "cost" : direction;

        String brand = params.get("brand");
        brand = brand == null ? "" : String.format(" brand='%s' AND", brand);

        int price = getPrice(params);
        String records = params.get("limit");
        int limit = getLimit(records);
        int page = getPage(params);
        int start = page * limit - limit;

        if (start < 0) {
            start = 0;
        }

        String sql = String.format("%sWHERE%s price>=%d ORDER BY %s %s LIMIT %d OFFSET %d;",
                FILTER_CAR_BY_SQL, brand, price, direction, sort, limit, start);

        return getCarsQuery(sql);

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

    @Override
    public List<Car> findAll(Map<String, String> params) {
        return params == null
                ? findAll()
                : findAll(params, 0);
    }
}
