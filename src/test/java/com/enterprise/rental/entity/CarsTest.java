package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.JdbcCarDao;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.impl.DefaultCarService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarsTest {
    static final Logger LOGGER = LogManager.getLogger();
    private static final List<Car> cars = new ArrayList<>();
    private static final Timestamp now = new Timestamp(System.currentTimeMillis());
    static final Car X7 = new Car.Builder().id(1L).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).date(now).build();
    static final Car X5 = new Car.Builder().id(2L).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).date(now).build();
    static JdbcCarDao mockCarDao = mock(JdbcCarDao.class);
    static CarService service = new DefaultCarService(mockCarDao);

    @BeforeEach
    void init() throws ServiceException, DaoException {
        cars.add(X5);
        cars.add(X7);
        service = new DefaultCarService(mockCarDao);
    }

    @Test
    @DisplayName(value = "Set Car name")
    void testSetBrandNewCar() throws ServiceException, DaoException {
        String brand = "BMW";
        Car car = new Car();
        car.setBrand(brand);
        assertEquals(brand, car.getBrand());
    }

    @Test
    void testFindByBrand() throws ServiceException, DaoException {
        Car car = new Car.Builder().id(2L).name("X5").brand("BMW").model("GT-2").path("http//").price(10000.0).cost(10000.0).year(2020).date(now).build();
        when(service.findAllBy("BMW")).thenReturn(cars);

        LOGGER.log(Level.INFO, "{}", service.findAllBy());
        LOGGER.log(Level.INFO, "{}", car);
        LOGGER.log(Level.INFO, "{}", cars);

        assertEquals(2, car.getId());
        assertEquals(10000.0, car.getPrice());
        assertEquals("BMW", car.getBrand());
    }

    @Test
    void testFindAllReturnCorrectData() throws ServiceException, DaoException {
        when(mockCarDao.findAll()).thenReturn(cars);
        List<Car> auto = mockCarDao.findAll();
        assertFalse(auto.isEmpty());

        for (Car car : cars) {
            assertNotEquals(0, car.getId());
            assertNotNull(car.getBrand());
            assertNotEquals(0, car.getPrice());
        }
    }

    @Test
    @DisplayName(value = "Test Find All Cars")
    void testFindAllCars() throws ServiceException, DaoException {
        when(mockCarDao.findAll()).thenReturn(cars);
        List<Car> auto = mockCarDao.findAll();
        auto.forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName(value = "Test Size Find Cars By Brand")
    void testFindCarsByBrandIdSize() throws ServiceException, DaoException {
        String brand = "BMW";
        List<Car> cars = mockCarDao.findAll(brand);
        when(mockCarDao.findAll(brand)).thenReturn(List.of(X5, X7));
        assertEquals(cars.size(), service.findAllBy().size());
    }

    @Test
    @DisplayName(value = "Test get All Cars")
    void getAll() throws ServiceException, DaoException {
        when(mockCarDao.findAll()).thenReturn(List.of(X5, X7));
        assertEquals(2, service.findAllBy().size());
    }

    @Test
    @DisplayName(value = "Test Find Car By Id")
    void findById() throws ServiceException, DaoException {
        when(mockCarDao.findById(X7.getId())).thenReturn(Optional.of(X7));
        Optional<Car> optionalCar = service.findBy(X7.getId());
        assertTrue(optionalCar.isPresent());
        assertEquals("X7", optionalCar.get().getName());
    }

    @Test
    @DisplayName(value = "Test Save Car")
    void save() throws ServiceException, DaoException {
        when(mockCarDao.save(X5)).thenReturn(true);
        assertTrue(service.save(X5));
    }

    @Test
    @DisplayName(value = "Test Delete Car")
    void delete() throws ServiceException, DaoException {
        when(mockCarDao.delete(X5.getId())).thenReturn(true);
        assertTrue(service.delete(X5.getId()));
    }

}
