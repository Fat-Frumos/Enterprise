package com.enterprise.car.service;

import com.enterprise.car.entity.Car;

import java.util.List;

public interface Service {
    boolean addCar(Car car);

    List<Car> getAll();

    boolean delete(long id);

    Car getById(long id);

    List<Car> getRandom();

    List<Car> getAll(String brand);
}
