package com.enterprise.rental.service;

import com.enterprise.rental.dao.Dao;
import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class CarServiceTest {
    @Mock
    private Dao dao;
    private Service service;
    List<Car> cars = new ArrayList<>();
    static final Car X7 = new Car.Builder().id(1l).name("X7").brand("BMW").model("G07").path("http//").price(25000.0).year(2022).build();
    static final Car X5 = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).year(2020).build();
    @BeforeEach
    void init() {
        service = new CarService(dao);
        cars.add(X5);
        cars.add(X7);
    }

    @Test
    void addCar() {
        service.addCar(X7);
        service.addCar(X5);
        assertEquals(2, service.getAll().size());
        when(service.getAll("BMW")).thenReturn(cars);
    }

    @Test
    void getAll() {
        Car X5 = new Car.Builder().id(2l).name("X5").brand("BMW").model("GT-2").path("http//").price(22000.0).year(2020).build();
        when(service.getAll("BMW")).thenReturn(cars);
        System.out.println(service.getAll());
    }

    @Test
    void getByBrand() {
        System.out.println(service.getAll("BMW"));

    }
}