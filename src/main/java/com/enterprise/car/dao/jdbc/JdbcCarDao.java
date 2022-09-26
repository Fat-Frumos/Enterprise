package com.enterprise.car.dao.jdbc;

import com.enterprise.car.dao.CarDao;
import com.enterprise.car.entity.Car;

import java.util.List;
import java.util.Optional;

import static com.enterprise.car.dao.jdbc.Constants.FILTER_BY_SQL;
import static com.enterprise.car.dao.jdbc.Constants.FIND_ALL_SQL;
import static com.enterprise.car.dao.jdbc.JdbcCarTemplate.*;

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
    public List<Car> findAll(String param) {
        String sql = FILTER_BY_SQL + "WHERE " + param + "';";
        return getCarsQuery(sql);
    }

    @Override
    public boolean save(Car car) {
        return setCarQuery(car);
    }

    //TODO edit/delete
    @Override
    public Car edit(Car car) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
