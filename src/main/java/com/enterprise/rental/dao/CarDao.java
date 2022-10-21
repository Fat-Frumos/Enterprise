package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CarDao extends Dao<Car> {

    List<Car> findAll(Map<String, String> params);

    List<Car> findAll(Map<String, String> params, int offset);
}
