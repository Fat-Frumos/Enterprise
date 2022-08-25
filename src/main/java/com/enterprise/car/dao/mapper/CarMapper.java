package com.enterprise.car.dao.mapper;

import com.enterprise.car.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper {
    public Car carRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        Double price = resultSet.getDouble("price");
        String path = resultSet.getString("path");
        int year = Integer.parseInt(resultSet.getString("year"));
        return Car.builder()
                .carId(id)
                .name(name)
                .brand(brand)
                .model(model)
                .year(year)
                .price(price)
                .path(path)
                .build();

//        Timestamp date = resultSet.getTimestamp("date"); // date.toLocalDateTime()

    }
}

