package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.enterprise.rental.dao.jdbc.Constants.FILTER_BY_SQL;
import static com.enterprise.rental.dao.jdbc.Constants.FIND_ALL_SQL;
import static com.enterprise.rental.dao.jdbc.JdbcCarTemplate.*;

public class JdbcCarDao implements CarDao {

    @Override
    public Set<Car> findAll() {
        return getCarsQuery(FIND_ALL_SQL);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return getCarById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Set<Car> findAll(String params) {
        String sql = String.format("%sWHERE %s;", FILTER_BY_SQL, params);
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
    public Set<Car> findAll(Map<String, String> params, int offset) {

        String sort = params.get("sort");
        sort = sort == null ? "" : sort;

        String direction = params.get("direction");
        direction = direction == null ? "" : direction;
        if (direction.equals("")) {
            direction = "cost";
        }

        String brand = params.get("brand");
        brand = brand == null ? "" : String.format(" brand='%s' AND", brand);


        int price;
        try {
            price = Integer.parseInt(params.get("price"));
        } catch (NumberFormatException e) {
            price = 1;
        }

        String records = params.get("limit");

        int limit = records
                != null && Integer.parseInt(records) >= 1
                ? Integer.parseInt(records)
                : 10;

        int page;
        try {
            page = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }

        int cost;
        try {
            cost = Integer.parseInt(params.get("cost"));
        } catch (NumberFormatException e) {
            cost = 1;
        }

        int start = page * limit - limit;

        if (start < 0) {
            start = 0;
        }

        String sql = String.format("%sWHERE%s cost>=%d ORDER BY %s %s LIMIT %d OFFSET %d;", FILTER_BY_SQL, brand, cost, direction, sort, limit, start);

        return getCarsQuery(sql);
    }

    @Override
    public Set<Car> findAll(Map<String, String> params) {
        return findAll(params, 0);
    }
}
