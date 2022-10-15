package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDao extends Dao<Car> {
    List<Car> findAll(int currentPage, int recordsPerPage);
    List<Car> findAll(Map<String, String> params, int limit, int offset);
}
