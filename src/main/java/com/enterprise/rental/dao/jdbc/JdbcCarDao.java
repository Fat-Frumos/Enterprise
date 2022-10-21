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
        Optional<Car> carQuery = getCarQuery(name,
                FILTER_BY_CAR_NAME_SQL);
        return carQuery;
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

        String sort = Optional.ofNullable(params.get("sort")).orElse("");
        String direction = Optional.ofNullable(params.get("direction")).orElse("cost");
        String brand = Optional.ofNullable(String.format(" brand='%s' AND", params.get("brand"))).orElse("");
        String model = Optional.ofNullable(String.format(" model='%s' AND", params.get("model"))).orElse("");
        int price = Optional.of(Integer.parseInt(params.get("price"))).orElse(100);
        int page = Optional.of(Integer.parseInt(params.get("page"))).orElse(0);
        int cost = Optional.of(Integer.parseInt(params.get("cost"))).orElse(1);

        String records = params.get("limit");
        int limit = records
                != null && Integer.parseInt(records) >= 1
                ? Integer.parseInt(records)
                : 10;

        int start = page * limit - limit;

        if (start < 0) {
            start = 0;
        }
        String sql = String.format("%sWHERE%s price>=%d ORDER BY %s %s LIMIT %d OFFSET %d;",
                FILTER_CAR_BY_SQL, brand, price, direction, sort, limit, start);

        return getCarsQuery(sql);

        //TODO
//        try {
//            price = Integer.parseInt(params.get("price"));
//        } catch (NumberFormatException e) {
//            price = 100;
//        }
//        int page;
//
//        try {
//            page = Integer.parseInt(params.get("page"));
//        } catch (NumberFormatException e) {
//            page = 0;
//        }
//
//        int cost;
//        try {
//            cost = Integer.parseInt(params.get("cost"));
//        } catch (NumberFormatException e) {
//            cost = 1;
//        }
    }

    @Override
    public List<Car> findAll(
            Map<String, String> params) {

        return params == null
                ? findAll()
                : findAll(params, 0);
    }
}
