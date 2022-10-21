package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper extends Mapper<Car> {
    public Car mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        String path = resultSet.getString("path");
        Double price = resultSet.getDouble("price");
        Double cost = resultSet.getDouble("cost");
        int year = Integer.parseInt(resultSet.getString("year"));
        return new Car.Builder()
                .id(id)
                .name(name)
                .brand(brand)
                .model(model)
                .path(path)
                .price(price)
                .cost(cost)
                .year(year)
                .build();
    }
}