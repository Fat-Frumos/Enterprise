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

import static com.enterprise.car.dao.jdbc.Constants.FILTER_BY_ID_SQL;
import static com.enterprise.car.dao.jdbc.Constants.INSERT_CAR_SQL;

public class JdbcCarTemplate extends PropsReader {
    private static final CarMapper ROW_MAPPER = new CarMapper();
    private static final Logger log = Logger.getLogger(JdbcCarTemplate.class.getName());

    private static List<Car> exec(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                List<Car> cars = new ArrayList<>();
                while (resultSet.next()) {
                    Car car = ROW_MAPPER.mapRow(resultSet);
                    cars.add(car);
                }
                return cars;
            }
            throw new CarException("Vehicle not found");
        }
    }

    protected static Optional<Car> getCarById(long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FILTER_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        }
    }

    protected static List<Car> getCarsQuery(String sql) {
        log.info(sql);
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            return exec(statement);
        } catch (SQLException ex) {
            throw new DataException(ex);
        }
    }

    protected static boolean setCarQuery(Car car) throws DataException {
        if (car != null) {

            try (Connection connection = getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT_CAR_SQL)) {

                long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

                String name = car.getName();
                String brand = car.getBrand();
                String model = car.getModel();
                String path = car.getPath();
                Double price = car.getPrice();
                int year = car.getYear();

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setString(3, brand);
                statement.setString(4, model);
                statement.setString(5, path);
                statement.setDouble(6, price);
                statement.setInt(7, year);
                statement.execute();
                return true;
            } catch (SQLException e) {
                log.info("Car can`t be created");
                throw new DataException(e);
            }
        }
        return false;
    }
}
