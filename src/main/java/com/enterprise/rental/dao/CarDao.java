package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDao extends Dao<Car> {

    List<Car> findAll(Map<String, String> params);

    List<Car> findAll(Map<String, String> params, int offset);

    Integer countId(String sql);

//    List<Car> findCars(int recordsPerPage, );
}
