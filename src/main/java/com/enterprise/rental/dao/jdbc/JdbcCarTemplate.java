package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.factory.DbManager;
import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarException;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import static com.enterprise.rental.dao.jdbc.Constants.FILTER_BY_ID_SQL;
import static com.enterprise.rental.dao.jdbc.Constants.INSERT_CAR_SQL;

public class JdbcCarTemplate extends DbManager {
    private static final CarMapper CAR_ROW_MAPPER = new CarMapper();
    private static final Logger log = Logger.getLogger(JdbcCarTemplate.class);

    protected static Optional<Car> getCarById(long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FILTER_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(CAR_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            //TODO close
        }
    }

    protected static Optional<Car> getCarQuery(String sql, String name) {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cars.add(CAR_ROW_MAPPER.mapRow(resultSet));
                }
            }
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
        //TODO unique user
        return Optional.of(cars.get(0));
    }

    protected static List<Car> getCarsQuery(String sql) {

        log.info(sql);

        Connection connection;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataException(e);
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {

                connection.commit();

                if (resultSet.next()) {
                    List<Car> cars = new ArrayList<>();
                    while (resultSet.next()) {
                        Car car = CAR_ROW_MAPPER.mapRow(resultSet);
                        cars.add(car);
                    }
                    return cars;
                }
                throw new CarNotFoundException(("Vehicle not found"));
            }
        } catch (SQLException ex) {
            throw new CarException(ex.getMessage());
        }
    }

    protected static boolean setCarQuery(@NotNull Car car) throws DataException {

        Connection connection = null;

        try {
            connection = getInstance().getConnection();
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(INSERT_CAR_SQL)) {

                long id = UUID.randomUUID().getMostSignificantBits() & Integer.MAX_VALUE;
                String name = car.getName();
                String brand = car.getBrand();
                String model = car.getModel();
                String path = car.getPath();
                Double price = car.getPrice();
                Double cost = car.getCost();
                int year = car.getYear();

                LocalDateTime time = LocalDateTime.now();

                statement.setLong(1, id);
                statement.setString(2, name);
                statement.setString(3, brand);
                statement.setString(4, model);
                statement.setString(5, path);
                statement.setDouble(6, price);
                statement.setDouble(7, cost);
                statement.setInt(8, year);
                statement.setTimestamp(9, Timestamp.valueOf(time));
                statement.execute();

                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                Objects.requireNonNull(connection).rollback();
            } catch (SQLException ex) {
                log.info("rollback");
                throw new DataException(ex);
            }
            log.info("Car can`t be created");
            throw new DataException(e);
        } finally {
            try {
                Objects.requireNonNull(connection).close();
            } catch (SQLException e) {
                log.info("Connection not closed: " + e.getMessage());
            }
        }
    }
}
