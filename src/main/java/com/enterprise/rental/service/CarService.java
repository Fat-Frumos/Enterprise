package com.enterprise.rental.service;

import com.enterprise.rental.dao.Dao;
import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class CarService implements Service {
    private final Dao dao;

    public CarService(Dao dao) {
        this.dao = dao;
    }

    public CarService() {
        this.dao = new JdbcCarDao();
    }

    @Override
    public boolean addCar(Car car) {
        return dao.save(car);
    }

    @Override
    public List<Car> getAll() {
        return dao.findAll();
    }

    @Override
    public boolean delete(long id) {
        return dao.delete(id);
    }

    @Override
    public Car getById(long id) {
        Optional<Car> car = dao.findById(id);
        return car.orElseThrow(() ->
                new CarException("Car not found"));
    }

    @Override
    public List<Car> getRandom() {
        List<Car> cars = dao.findAll();
        Collections.shuffle(cars);
        return cars.subList(0, 3);
    }

    @Override
    public List<Car> getAll(String brand) {
        return dao.findAll(brand);
    }
}
