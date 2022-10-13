package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;

import java.util.List;

public interface Service {
    boolean addCar(Car car);

    List<Car> getAll();

    List<Car> getAll(String params);

    boolean delete(long id);

    Car getById(long id);

    List<Car> getRandom();

    List<Car> getAll(String params, int limit, int offset);
}
