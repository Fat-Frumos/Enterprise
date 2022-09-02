package com.enterprise.car.service;

import com.enterprise.car.dao.jdbc.JdbcCarDao;
import com.enterprise.car.entity.Car;
import com.enterprise.car.exception.CarException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarService {
    private static final Logger log = Logger.getLogger(CarService.class.getName());
    private final JdbcCarDao carDao;

    public CarService(JdbcCarDao carDao) {
        this.carDao = carDao;
    }

    public CarService() {
        this.carDao = new JdbcCarDao();
    }


    public boolean addCar(Car car) {
        boolean save = carDao.save(car);
        log.log(Level.INFO, "New car saved: {0} ", save);
        return save;
    }

    public List<Car> getAll() {

        List<Car> cars = carDao.findAll();

        return cars;
    }

    public List<Car> getByBrand(String brand) {
        return carDao.findByBrand(brand);
    }

    public boolean delete(long id) {
        return carDao.delete(id);
    }

    public Car getById(long id) {
        Optional<Car> car = carDao.findById(id);
        log.info(String.valueOf(car.get()));
        return car.orElseThrow(() ->
                new CarException("Car not found"));
    }

    public List<Car> getRandom() {
        List<Car> cars = carDao.findAll();
        Collections.shuffle(cars);
        return cars.subList(0, 3);
    }
}
