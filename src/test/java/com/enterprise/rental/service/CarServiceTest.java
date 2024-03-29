package com.enterprise.rental.service;

import com.enterprise.rental.dao.CarDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.impl.DefaultCarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceTest {
    private static final CarDao mockDao = mock(CarDao.class);
    private static final CarService carService = new DefaultCarService(mockDao);
    static final Timestamp now = new Timestamp(System.currentTimeMillis());
    List<Car> cars = new ArrayList<>();
    static final Car car = new Car.Builder().id(1L).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).date(now).build();
    static final Car X7 = new Car.Builder().id(4L).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).cost(10000.0).year(2022).date(now).build();
    static final Car X5 = new Car.Builder().id(5L).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).cost(10000.0).year(2020).date(now).build();

    @BeforeEach
    void init() throws ServiceException {
        cars.add(X5);
        cars.add(X7);
    }

    @Test

    @DisplayName(value = "Test random Car size")
    void randomCar() throws ServiceException {
        when(carService.findAllBy()).thenReturn(cars);
        List<Car> random = carService.getRandom(2);
        assertEquals(2, random.size());
    }

    @Test
    @DisplayName("Test get Declared Methods, check if field car contains value")
    void getAll() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ServiceException {

        Class<? extends Car> carClass = car.getClass();
        for (Car auto : carService.findAllBy()) {
            for (Method method : auto.getClass().getDeclaredMethods()) {
                if (method.getName().startsWith("get")) {
                    Method value = carClass.getMethod(method.getName());
                    Object resultValue = value.invoke(auto);
                    assertNotNull(resultValue);
                }
            }
        }
    }

    @Test
    @DisplayName(value = "Test find all Cars invokes and return size of List cars")
    void addCar() throws ServiceException {
        when(carService.findAllBy()).thenReturn(cars);
        assertEquals(2, carService.findAllBy().size());
    }

    @Test
    @DisplayName(value = "Test get All Cars invokes and check not Null")
    void getAllCars() throws ServiceException {
        when(carService.findAllBy()).thenReturn(cars);
        List<Car> carList = carService.findAllBy();
        carList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test save Cars invokes and verify")
    void save() throws ServiceException, DaoException {
        when(carService.save(X7)).thenReturn(true);
        boolean save = carService.save(X7);
        assertTrue(save);
        verify(mockDao).save(X7);
        when(carService.findAllBy()).thenReturn(cars);
    }

    @Test
    @DisplayName(value = "Test save Car invokes and return true")
    void saveCar() throws ServiceException, DaoException {
        when(mockDao.save(car)).thenReturn(true);
        boolean saved = carService.save(car);
        assertTrue(saved);
    }

    @Test
    @DisplayName(value = "Test find Car by Id invokes and return true")
    void findCar() throws ServiceException, DaoException {
        when(mockDao.findById(1L)).thenReturn(Optional.ofNullable(car));
        Optional<Car> actual = carService.findBy(1L);
        verify(mockDao).findById(1L);
        assertEquals(car, actual.get());
    }
}