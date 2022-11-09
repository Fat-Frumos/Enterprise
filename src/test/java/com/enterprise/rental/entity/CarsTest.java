package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CarsTest {
    Logger logger = Logger.getLogger(CarsTest.class);
    private static final List<Car> cars = new ArrayList<>();
    static final Car X7 = new Car.Builder().id(1L).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).build();
    static final Car X5 = new Car.Builder().id(2L).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).build();
    JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    CarService service = new CarService(mockCarDao);

    @BeforeEach
    void init() {
        cars.add(X5);
        cars.add(X7);
        service = new CarService(mockCarDao);
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
    public void testFindByBrand() {
        Car car = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(10000.0).cost(10000.0).year(2020).build();
        when(service.getAll("BMW")).thenReturn(cars);

        logger.info(String.format("%s", service.getAll()));
        logger.info(String.format("%s", car));
        logger.info(String.format("%s", cars));

        assertEquals(2, car.getId());
        assertEquals(10000.0, car.getPrice());
        assertEquals("BMW", car.getBrand());
    }

    @Test
    public void testFindAllReturnCorrectData() {
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
    void testFindAllCars() throws Exception {
        when(mockCarDao.findAll()).thenReturn(cars);
        List<Car> auto = mockCarDao.findAll();
        assertEquals(auto, cars);
    }

    @Test
    @DisplayName(value = "Test Find Cars By Brand Id Length")
    void testFindCarsByBrandIdSize() throws Exception {
        String brand = "BMW";
        List<Car> cars = mockCarDao.findAll(brand);
        when(mockCarDao.findAll(brand)).thenReturn(List.of(X5, X7));
        assertEquals(cars.size(), service.getAll().size());

    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Asc Founded Cars")
    void testSortedByRatingAscFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Sorted By Rating Desc Founded Cars")
    void testSortedByRatingDescFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Sorted By Price Asc Founded Cars")
    void testSortedByPriceAscFoundedCars() throws Exception {

    }

    @Test
    @DisplayName(value = "Test Sorted By Price Desc Founded Cars")
    void testSortedByPriceDescFoundedCars() throws Exception {
    }

    @Test
    @DisplayName(value = "Test Find Car By Id")
    void testFindCarById() throws Exception {

    }

    @Test
    void getAll() {
        when(mockCarDao.findAll()).thenReturn(List.of(X5, X7));
        assertEquals(2, service.getAll().size());
    }

    @Test
    void findById() {
        when(mockCarDao.findById(X7.getId())).thenReturn(Optional.of(X7));
        assertEquals("X7", service.getById(X7.getId()).get().getName());
    }

    @Test
    void save() {
        when(mockCarDao.save(X5)).thenReturn(true);
        assertTrue(service.save(X5));
    }

    @Test
    void delete() {
        when(mockCarDao.delete(X5.getId())).thenReturn(true);
        assertTrue(service.delete(X5.getId()));
    }

}
