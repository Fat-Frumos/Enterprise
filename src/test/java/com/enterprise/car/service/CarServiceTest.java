package com.enterprise.car.service;

import com.enterprise.car.dao.jdbc.JdbcCarDao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CarServiceTest {
    JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    CarService carService = new CarService(mockCarDao);

    @Test
    void addCar() {

    }

    @Test
    void getAll() {
    }

    @Test
    void getByBrand() {
    }
}