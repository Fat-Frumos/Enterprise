package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper extends Mapper<Car> {
    public Car mapRow(ResultSet resultSet) {

        try {
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
        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException("Cannot close connection", e);
            }
            throw new CarNotFoundException(exception.getMessage());
        }
    }
}