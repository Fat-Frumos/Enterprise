package com.enterprise.car.dao;

import com.enterprise.car.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    List<Car> findAll();

    Optional<Car> findById(Long id);

    List<Car> findAll(String brand);

    boolean save(Car car);

    Car edit(Car car);

    boolean delete(long id);
}
