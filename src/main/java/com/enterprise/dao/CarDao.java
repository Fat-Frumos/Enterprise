package com.enterprise.dao;

import com.enterprise.entity.Car;

import java.util.List;

public interface CarDao {
    List<Car> findAll();

    boolean save(Car car);

    Car edit(Car car);

    boolean delete(int id);
}
