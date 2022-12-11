package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarService extends Service<Car> {
    List<Car> getAll(Map<String, String> params);

    List<Car> getAll(Map<String, String> params, int offset);

    Integer getNumberOfRows();

    List<Car> getRandom(int n);
}
