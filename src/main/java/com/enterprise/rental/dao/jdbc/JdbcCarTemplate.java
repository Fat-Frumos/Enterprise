package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcCarTemplate extends DbManager {
    private static final CarMapper CAR_ROW_MAPPER = new CarMapper();
    private static final Logger log = Logger.getLogger(JdbcCarTemplate.class);

    protected static Optional<Car> getCarById(long id) {

        log.debug(String.format("%s%s%s", YELLOW, FILTER_BY_ID_SQL, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(FILTER_BY_ID_SQL);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(CAR_ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            log.debug("Car by id not found");
            rollback(connection, sqlException, String.valueOf(id));
        } finally {
            eventually(connection, statement, resultSet);
        }
        return Optional.empty();
    }

    protected static Optional<Car> getCarQuery(String name) {

        String query = String.format("%s %s", FILTER_BY_CAR_NAME_SQL, name);
        log.debug(query);
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(FILTER_BY_CAR_NAME_SQL);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                cars.add(CAR_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException sqlException) {
            log.debug("Cars not found");
            rollback(connection, sqlException, query);
        } finally {
            eventually(connection, statement, resultSet);
        }
        return Optional.of(cars.get(0));
    }

    protected static List<Car> getCarsQuery(String query) {
        log.debug(String.format("%s%s%s", YELLOW, query, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            if (resultSet.next()) {
                return getCars(resultSet);
            } else {
                log.debug("Vehicle not found");
            }
        } catch (SQLException sqlException) {
            rollback(connection, sqlException, query);
            log.debug("Vehicle not found");

        } finally {
            eventually(connection, statement, resultSet);
        }
        return new ArrayList<>();
    }

    private static List<Car> getCars(
            ResultSet resultSet)
            throws SQLException {

        List<Car> cars = new ArrayList<>();
        while (resultSet.next()) {
            cars.add(CAR_ROW_MAPPER
                    .mapRow(resultSet));
        }
        return cars;
    }

    protected static boolean setCarQuery(
            @NotNull Car car)
            throws DataException {

        log.debug(INSERT_CAR_SQL);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConfigConnection();
            statement = connection.prepareStatement(INSERT_CAR_SQL);

            boolean preparedStatement =
                    setPreparedStatement(car, statement);

            connection.commit();
            return preparedStatement;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException, INSERT_CAR_SQL);
            log.debug("Car can`t be created");

        } finally {
            eventually(connection, statement);
        }
        return false;
    }

    private static boolean setPreparedStatement(
            Car car,
            PreparedStatement statement)
            throws SQLException {

        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7fffff);
        statement.setString(2, car.getName());
        statement.setString(3, car.getBrand());
        statement.setString(4, car.getModel());
        statement.setString(5, car.getPath());
        statement.setDouble(6, car.getPrice());
        statement.setDouble(7, car.getCost());
        statement.setInt(8, car.getYear());
        statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));

        return statement.execute();
    }
}
