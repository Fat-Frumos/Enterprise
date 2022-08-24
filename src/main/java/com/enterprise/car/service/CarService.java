package com.enterprise.car.service;

import com.enterprise.car.dao.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private static List<Car> cars = new ArrayList();

    static {
        cars.add(new Car("BMW"));
        cars.add(new Car("Kia"));
        cars.add(new Car("Toyota"));
    }

    public List<Car> retrieveCars() {
        return cars;
    }
}
