package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;

import java.util.List;

public interface CarDao extends Dao<Car> {
    List<Car> findAll(int currentPage, int recordsPerPage);
    List<Car> findAll(String params, int limit, int offset);
}
