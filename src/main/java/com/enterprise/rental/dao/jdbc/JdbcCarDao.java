package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.JdbcCarTemplate.*;

public class JdbcCarDao implements CarDao {
    private static final Logger log = Logger.getLogger(JdbcCarDao.class.getName());
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

    @Override
    public List<Car> findAll(int page, int recordsPerPage) {

        int start = page * recordsPerPage - recordsPerPage;

        if (start < 0) {
            start = 0;
        }
        if (recordsPerPage <= 0) {
            recordsPerPage = 9;
        }
            String sql = String.format("%sFROM car LIMIT %d OFFSET %d", FIELDS, recordsPerPage, start);
        return getCarsQuery(sql);

    }
    @Override
    public List<Car> findAll(@NotNull Map<String, String> params, int limit, int offset) {
        String sort = params.get("sort");
        sort = sort == null ? "" : sort;

        String direction = params.get("direction");
        direction = direction == null ? "" : direction;

        String brand = params.get("brand");
        brand = brand == null ? "" : String.format(" brand='%s' AND", brand);


        int price;
        try {
            price = Integer.parseInt(params.get("price"));
        } catch (NumberFormatException e) {
            price = 1;
        }

        if (limit == 0) {
            limit = 10;
        }

        String sql = String.format("%sWHERE%s price>%d ORDER BY price %s LIMIT %d OFFSET %d;", FILTER_BY_SQL, brand, price, sort, limit, offset);

        log.info(sql);
        //TODO direction
        return getCarsQuery(sql);
    }
}
