package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.Map;
import java.util.Set;

public interface CarDao extends Dao<Car> {

    Set<Car> findAll(Map<String, String> params);

    Set<Car> findAll(Map<String, String> params, int offset);
}
