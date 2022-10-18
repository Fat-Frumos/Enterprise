package com.enterprise.rental.service;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CarService implements Service<Car> {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public CarService() {
        this.carDao = new JdbcCarDao();
    }

    @Override
    public boolean save(Car car) {
        return carDao.save(car);
    }

    @Override
    public Set<Car> getAll() {
        return carDao.findAll();
    }

    @Override
    public boolean delete(long id) {
        return carDao.delete(id);
    }

    @Override
    public Car getById(long id) {
        Optional<Car> car = carDao.findById(id);

        return car.orElseThrow(() ->
                new CarException("Car not found"));
    }

    @Override
    public Set<Car> getRandom(int size) {

        List<Car> list = new ArrayList<>(carDao.findAll());

        Collections.shuffle(list);

        return IntStream.range(0, size)
                .mapToObj(list::get)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Car> getAll(Map<String, String> params, int offset) {
        return carDao.findAll(params, offset);
    }

    public Set<Car> getAll(Map<String, String> params) {
        return carDao.findAll(params);
    }

    public Set<Car> getAll(String sql) {
        return carDao.findAll(sql);
    }
}
