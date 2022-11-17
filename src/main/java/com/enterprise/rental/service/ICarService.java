package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICarService {

    Integer getNumberOfRows();

    boolean save(Car car);

    List<Car> getAll();

    boolean delete(long id);

    Car edit(Car car);

    Optional<Car> getById(long id);

    List<Car> getRandom(int size);

    List<Car> getAll(String sql);

    List<Car> getAll(Map<String, String> params);

    List<Car> getAll(Map<String, String> params, int offset);


}
