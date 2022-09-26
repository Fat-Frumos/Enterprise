package com.enterprise.car.service;

import com.enterprise.car.dao.CarDao;
import com.enterprise.car.dao.jdbc.JdbcCarDao;
import com.enterprise.car.entity.Car;
import com.enterprise.car.exception.CarException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class CarService implements Service {
    private final CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public CarService() {
        this.carDao = new JdbcCarDao();
    }

    @Override
    public boolean addCar(Car car) {
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
    public List<Car> getAll(String brand) {
        return carDao.findAll(brand);
    }
}
