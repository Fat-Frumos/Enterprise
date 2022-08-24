package com.enterprise.car.dao;

import com.enterprise.car.dao.entity.Car;

import java.util.List;

public interface CarDao {
    List<Car> findAll();

    List<Car> findById(Long id);

    boolean save(Car car);

    Car edit(Car car);

    boolean delete(int id);
}
