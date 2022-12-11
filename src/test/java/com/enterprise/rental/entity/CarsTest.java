package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.impl.DefaultCarService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarsTest {
    Logger log = Logger.getLogger(CarsTest.class);
    private static final List<Car> cars = new ArrayList<>();
    static final Car X7 = new Car.Builder().id(1L).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).build();
    static final Car X5 = new Car.Builder().id(2L).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).build();
    JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    CarService service = new DefaultCarService(mockCarDao);

    @BeforeEach
    void init() {
        cars.add(X5);
        cars.add(X7);
        service = new DefaultCarService(mockCarDao);
    }

    @Test
    @DisplayName(value = "Set Car name")
    void testSetBrandNewCar() {
        String brand = "BMW";
        Car car = new Car();
        car.setBrand(brand);
        assertEquals(brand, car.getName());
    }

    @Test
    void testFindByBrand() {
        Car car = new Car.Builder().id(2L).name("X5").brand("BMW").model("GT-2").path("http//").price(10000.0).cost(10000.0).year(2020).build();
        when(service.getAll("BMW")).thenReturn(cars);

        log.debug(String.format("%s", service.getAll()));
        log.debug(String.format("%s", car));
        log.debug(String.format("%s", cars));

        assertEquals(2, car.getId());
        assertEquals(10000.0, car.getPrice());
        assertEquals("BMW", car.getBrand());
    }

    @Test
    void testFindAllReturnCorrectData() {
        JdbcCarDao jdbcCarDao = new JdbcCarDao();
        List<Car> cars = jdbcCarDao.findAll();
        assertFalse(cars.isEmpty());

        for (Car car : cars) {
            assertNotEquals(0, car.getId());
            assertNotNull(car.getBrand());
            assertNotEquals(0, car.getPrice());
        }
    }

    @Test
    @DisplayName(value = "Test Find All Cars")
    void testFindAllCars() {
        when(mockCarDao.findAll()).thenReturn(cars);
        List<Car> auto = mockCarDao.findAll();
        auto.forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName(value = "Test Size Find Cars By Brand")
    void testFindCarsByBrandIdSize() {
        String brand = "BMW";
        List<Car> cars = mockCarDao.findAll(brand);
        when(mockCarDao.findAll(brand)).thenReturn(List.of(X5, X7));
        assertEquals(cars.size(), service.getAll().size());
    }

    @Test
    @DisplayName(value = "Test get All Cars")
    void getAll() {
        when(mockCarDao.findAll()).thenReturn(List.of(X5, X7));
        assertEquals(2, service.getAll().size());
    }

    @Test
    @DisplayName(value = "Test Find Car By Id")
    void findById() {
        when(mockCarDao.findById(X7.getId())).thenReturn(Optional.of(X7));
        Optional<Car> optionalCar = service.getById(X7.getId());
        assertTrue(optionalCar.isPresent());
        assertEquals("X7", optionalCar.get().getName());
    }

    @Test
    @DisplayName(value = "Test Save Car")
    void save() {
        when(mockCarDao.save(X5)).thenReturn(true);
        assertTrue(service.save(X5));
    }

    @Test
    @DisplayName(value = "Test Delete Car")
    void delete() {
        when(mockCarDao.delete(X5.getId())).thenReturn(true);
        assertTrue(service.delete(X5.getId()));
    }

}
