package com.enterprise.rental.service;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;

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

    public Integer getNumberOfRows() {
        Integer numOfRows;
        String sql = "SELECT COUNT(id) FROM car";
        numOfRows = carDao.countId(sql);
        return numOfRows;
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
    public Car edit(Car car) {
        return carDao.edit(car);
    }

    @Override
    public Optional<Car> getById(long id) {
        return carDao.findById(id);
    }

    public List<Car> getRandom(int size) {

        List<Car> list = new ArrayList<>(carDao.findAll());

        Collections.shuffle(list);

        return IntStream.range(0, size)
                .mapToObj(list::get)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getAll(Map<String, String> params, int offset) {

        return carDao.findAll(params, offset);
    }

    @Override
    public List<Car> getAll(Map<String, String> params) {
        return carDao.findAll(params);
    }

    @Override
    public List<Car> getAll(String sql) {
        return carDao.findAll(sql);
    }

}
