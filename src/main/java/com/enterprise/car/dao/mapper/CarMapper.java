package com.enterprise.car.dao.mapper;

import com.enterprise.car.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CarMapper {
    public Car carRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        String path = resultSet.getString("path");
        Double price = resultSet.getDouble("price");
//        LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();
//        int year = Integer.parseInt(resultSet.getString("year"));

        return Car.builder()
                .id(id)
                .name(name)
                .brand(brand)
                .model(model)
//                .year(year)
                .price(price)
                .path(path)
//                .date(date)
                .build();

    }
}

