package com.enterprise.rental.service;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public List<Car> getAll() {
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
    public List<Car> getRandom() {
        List<Car> cars = carDao.findAll();
        Collections.shuffle(cars);
        return cars.subList(0, 3);
    }

    @Override
    public List<Car> getAll(Map<String, String> params, int offset) {
        return carDao.findAll(params, offset);
    }

    public List<Car> getAll(Map<String, String> params) {
        return carDao.findAll(params);
    }

    public List<Car> getAll(String sql) {
        return carDao.findAll(sql);
    }
}
