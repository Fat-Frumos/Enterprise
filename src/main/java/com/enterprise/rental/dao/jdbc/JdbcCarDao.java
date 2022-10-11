package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.List;
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
        String sql = FILTER_BY_SQL + "WHERE " + params + "';";
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
}
