package com.enterprise.car.dao.jdbc;

import com.enterprise.car.dao.mapper.CarMapper;
import com.enterprise.car.entity.Car;
import com.enterprise.car.exception.CarException;
import com.enterprise.car.exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class JdbcCarTemplate extends PropsReader {
    private static final CarMapper ROW_MAPPER = new CarMapper();
    private static final Logger log = Logger.getLogger(JdbcCarTemplate.class.getName());
    private static final int COLUMN = 1;

    protected static List<Car> getCarByBrand(String sql, String brand) {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, brand);
            return exec(cars, statement);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }

    }

    private static List<Car> exec(List<Car> cars, PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                while (resultSet.next()) {
                    Car car = ROW_MAPPER.mapRow(resultSet);
                    cars.add(car);
                    log.info(car.toString());
                }
                return cars;
            }
            throw new CarException("Vehicle not found");
        }
    }

    protected static Optional<Car> getCarById(String sql, long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static List<Car> getCarsQuery(String sql) {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return exec(cars, statement);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    protected static boolean setCarQuery(String sql, Car car) throws DataException {
        if (car != null) {

            try (Connection connection = getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                String name = car.getName();
                String brand = car.getBrand();
                String model = car.getModel();
                String path = car.getPath();
                Double price = car.getPrice();
                int year = car.getYear();
                log.info("New car " + name + " " + brand + " " + model + " " + year + " " + " " + path);

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setString(3, brand);
                statement.setString(4, model);
                statement.setString(5, path);
                statement.setDouble(6, price);
                statement.setInt(7, year);

                return statement.execute();
            } catch (SQLException e) {
                log.info("Car can`t be created");
                throw new DataException(e);
            }
        }
        return false;
    }
}
