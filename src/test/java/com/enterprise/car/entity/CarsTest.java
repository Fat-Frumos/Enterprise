package com.enterprise.car.entity;

import com.enterprise.car.dao.jdbc.JdbcCarDao;
import com.enterprise.car.service.CarService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class CarsTest {
    private static final List<Car> cars = new ArrayList<>();

    private static final Cars INSTANCE = Cars.getInstance();
    static final Car X7 = new Car(1l, "X7", "BMW", "G07", "http//", 25000.0, 2022);
    static final Car X5 = new Car(1l, "X5", "BMW", "G03", "http//", 20000.0, 2020);

    JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    CarService carService = new CarService(mockCarDao);


    @Test
    @DisplayName(value = "Test-Mock Get Cars from db")
    void testGetCars() {
        JdbcCarDao carDao = mock(JdbcCarDao.class);
        List<Car> INSTANCE = carDao.findAll();
        List<Car> expected = new ArrayList<>();
        expected.add(X5);
        expected.add(X7);
        assertEquals(expected, INSTANCE);
    }

    String uri = "/cars";

    @Test
    public void testFindByBrand() {
        JdbcCarDao carDao = mock(JdbcCarDao.class);

        Car car = new Car();
        car.setId(2);
        car.setBrand("BMW");
        car.setPrice(100000.0);

        when(carDao.findByBrand("BMW")).thenReturn(cars);

        assertEquals(2, car.getId());
        assertEquals(100000.0, car.getPrice());
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

    }

    @Test
    @DisplayName(value = "Test Find Cars By Brand Id Length")
    void testFindCarsByBrandIdSize() throws Exception {
        String brand = "BMW";
        List<Car> cars = mockCarDao.findByBrand(brand);
        for (Car car : cars) {
            
        }
        when(mockCarDao.findByBrand(brand)).thenReturn(List.of(X5, X7));
        assertEquals(2, carService.getAll().size());

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
        assertEquals(2, carService.getAll().size());
    }

    @Test
    void findById() {
        when(mockCarDao.findById(X7.getId())).thenReturn(Optional.of(X7));
        assertEquals("X7", carService.getById(X7.getId()).getName());
    }

    @Test
    void save() {
        when(mockCarDao.save(X5)).thenReturn(true);
        assertTrue(carService.addCar(X5));
    }

    @Test
    void delete() {
        when(mockCarDao.delete(X5.getId())).thenReturn(true);
        assertTrue(carService.delete(X5.getId()));
    }

}
