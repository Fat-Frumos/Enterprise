package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.List;
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

    //TODO edit/delete/ByName

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
            String sql = String.format("%sFROM car LIMIT %d OFFSET %d",FIELDS, recordsPerPage, start);
        return getCarsQuery(sql);

    }
    @Override
    public List<Car> findAll(String params, int limit, int offset) {
        String sql = String.format("%s LIMIT %d OFFSET %d WHERE %s';", FILTER_BY_SQL, params, limit, offset);
        return getCarsQuery(sql);
    }
}
