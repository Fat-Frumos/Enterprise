package com.enterprise.car.service;

import com.enterprise.car.dao.jdbc.JdbcCarDao;
import com.enterprise.car.entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {

    private final JdbcCarDao jdbcCarDao = new JdbcCarDao();
    private static List<Car> cars = new ArrayList();

    static {
        cars.add(Car.builder().brand("BMW").name("X8").price(22000.0).year(2022).path("https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-1.jpg").build());
        cars.add(Car.builder().brand("Mustang").name("Camaro").price(20000.0).year(2020).path("https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-2.jpg").build());
        cars.add(Car.builder().brand("Porsche").name("Cayenne").model("Turbo GT").price(25000.0).year(2022).path("https://raw.githubusercontent.com/Fat-Frumos/Cars/master/images/slider-3.jpg").build());
    }

    public List<Car> retrieveCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getAll() {
        return jdbcCarDao.findAll();
    }
}
