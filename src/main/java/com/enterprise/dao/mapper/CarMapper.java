package com.enterprise.dao.mapper;

import com.enterprise.entity.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CarMapper {
    public Car carRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String brand = resultSet.getString("brand");
        String model = resultSet.getString("model");
        int price = resultSet.getInt("price");
        Timestamp date = resultSet.getTimestamp("date");
        return new Car(id, name, brand, model, price, date.toLocalDateTime());
    }
}

