package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.FILTER_BY_SQL;
import static com.enterprise.rental.dao.jdbc.Constants.FIND_ALL_SQL;
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
    public Optional<User> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Car> findAll(String params) {
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

//    @Override
//    public List<Car> findAll(int page, int limit) {
//
//        int start = page * limit - limit;
//
//        if (start < 0) {
//            start = 0;
//        }
//        if (limit <= 1) {
//            limit = 10;
//        }
//        String sql = String.format("%sFROM car LIMIT %d OFFSET %d", FIELDS, limit, start);
//        return getCarsQuery(sql);
//
//    }

    @Override
    public List<Car> findAll(Map<String, String> params, int offset) {

        String sort = params.get("sort");
        sort = sort == null ? "" : sort;

        String direction = params.get("direction");
        direction = direction == null ? "" : direction;
        if (direction.equals("")) {
            direction = "price";
        }

        String brand = params.get("brand");
        brand = brand == null ? "" : String.format(" brand='%s' AND", brand);


        int price;
        try {
            price = Integer.parseInt(params.get("price"));
        } catch (NumberFormatException e) {
            price = 0;
        }

        String records = params.get("limit");

        int limit = records
                != null && Integer.parseInt(records) >= 1
                ? Integer.parseInt(records)
                : 10;

//        if (sort.equals("")) {
//            sort = "ASC";
//        }

        int page;
        try {
            page = Integer.parseInt(params.get("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }


        int start = page * limit - limit;

        if (start < 0) {
            start = 0;
        }

        String sql = String.format("%sWHERE%s price>%d ORDER BY %s %s LIMIT %d OFFSET %d;", FILTER_BY_SQL, brand, price, direction, sort, limit, start);

        return getCarsQuery(sql);
    }

    @Override
    public List<Car> findAll(Map<String, String> params) {
        return findAll(params);
    }
}
