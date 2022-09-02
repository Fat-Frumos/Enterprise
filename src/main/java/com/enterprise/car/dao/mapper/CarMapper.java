package com.enterprise.car.dao.mapper;

import com.enterprise.car.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class CarMapper extends Mapper<Car> {
    private static final Logger log = Logger.getLogger(CarMapper.class.getName());

    public Car mapRow(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        String path = resultSet.getString("path");
        Double price = resultSet.getDouble("price");
        int year = Integer.parseInt(resultSet.getString("year"));
        car.setId(id);
        car.setBrand(brand);
        car.setModel(model);
        car.setName(name);
        car.setName(name);
        car.setPath(path);
        car.setPrice(price);
        car.setYear(year);
        return car;
    }
}

