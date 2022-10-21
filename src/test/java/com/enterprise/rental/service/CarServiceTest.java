package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CarServiceTest {

    private static final JdbcCarDao mockDao = mock(JdbcCarDao.class);
    private static final CarService carService = new CarService(mockDao);
    private static final List<Car> actual = new ArrayList<>();
    private static List<Car> expected = new ArrayList<>();
    List<Car> cars = new ArrayList<>();
    static final Car car = new Car.Builder().id(1l).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).build();
    static final Car X7 = new Car.Builder().id(4l).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).build();
    static final Car X5 = new Car.Builder().id(5l).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).build();

    @BeforeEach
    void init() {
        cars.add(X5);
        cars.add(X7);
    }

    @Test
    void addCar() {
        carService.save(X7);
        carService.save(X5);
        assertEquals(2, carService.getAll().size());
        when(carService.getAll("BMW")).thenReturn(cars);
    }

    @Test
    void getAllCars() {
        Car X5 = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).build();
        when(carService.getAll("BMW")).thenReturn(cars);
        System.out.println(carService.getAll());
    }

    @Test
    void getByBrand() {
        System.out.println(carService.getAll("BMW"));
    }

    @Test
    void save() {
    }

    @Test
    void testGetAll() {
    }


    @Test
    @DisplayName(value = "Test save Car invokes and return true")
    void saveCar() {
        when(mockDao.save(car)).thenReturn(true);
        boolean saved = carService.save(car);
        verify(mockDao).save(car);
        assertEquals(saved, car);
    }

    @Test
    @DisplayName(value = "Test find Car by Id invokes and return true")
    void findCar() {
        when(mockDao.findById(1L)).thenReturn(Optional.ofNullable(car));
        Car actual = carService.getById(1L);
        verify(mockDao).findById(1L);
        assertEquals(Optional.of(car), actual);
    }

    @Test
    @DisplayName("Test Iterable with all cars, check if List contains cars Returns True")
    void getAll() {
//        when(mockDao.findAll()).thenReturn(expected);
//        Iterable<Car> actual = carService.getAll();
//        actual.forEach(actual::add);
//        actual.forEach(car -> assertTrue(expected.contains(car)));
//        verify(mockDao).findAll();
//        assertEquals(expected, actual);
//        assertEquals(expected.size(), actual.size());
    }
}