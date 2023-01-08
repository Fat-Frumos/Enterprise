package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * JdbcTemplate is a central class in the JDBC core package
 * that simplifies the use of JDBC and helps to avoid common errors.
 * It internally uses JDBC API and eliminates a lot of problems with JDBC API
 *
 * @author Pasha Polyak
 */
public class JdbcCarTemplate extends DbManager {
    private static final CarMapper CAR_ROW_MAPPER = new CarMapper();
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * <p>Retrieves the Car with the specified id</p>
     * Is used to return an Car from the database by the given id,
     * otherwise {@code Optional.empty}.
     *
     * @param id of car
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    protected static Optional<Car> getCarById(Long id) {

        LOGGER.log( Level.INFO, "{}{}{}", YELLOW, FILTER_BY_ID_SQL, RESET);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(FILTER_BY_ID_SQL);
            statement.setLong(1, id);

            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(CAR_ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            LOGGER.log( Level.INFO, "Car by id not found: {}", id);
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement, resultSet);
        }
        return Optional.empty();
    }

    /**
     * <p>Retrieves the entity with the specified name</p>
     * Is used to return an object from the database by the given name,
     * otherwise {@code Optional.empty}.
     *
     * @param name of object
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    protected static Optional<Car> getCarQuery(String name) {
        String query = String.format("%s %s", FILTER_BY_CAR_NAME_SQL, name);
        LOGGER.log( Level.INFO, query);
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(FILTER_BY_CAR_NAME_SQL);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            connection.commit();

            while (resultSet.next()) {
                cars.add(CAR_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException sqlException) {
            LOGGER.log( Level.INFO, "Cars not found: {}", query);
            rollback(connection, sqlException);
        } finally {
            eventually(connection, statement, resultSet);
        }
        return Optional.of(cars.get(0));
    }

    /**
     * <p>Retrieves all defined Cars</p>
     * Is used to get objects from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of Cars.
     */
    protected static List<Car> getCarsQuery(String query) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();
            LOGGER.log( Level.INFO, "{}{}{}", YELLOW, query, RESET);
            if (resultSet.next()) {
                return getCars(resultSet);
            } else {
                LOGGER.log( Level.INFO, "Vehicle not found");
            }
        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            LOGGER.log(Level.ERROR, "Vehicle not found");

        } finally {
            eventually(connection, statement, resultSet);
        }
        return new ArrayList<>();
    }

    /**
     * Get instances of the destination class {@link Car} to List using the {@link CarMapper}
     *
     * @param resultSet the ResultSet returned by JDBC API
     * @return list of Cars
     */
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

    /**
     * Creates a PreparedStatement object for sending parameterized SQL statements to the database
     *
     * @param car instances of Car
     * @return list of Cars
     * @throws DataException custom exception
     */
    protected static boolean setCarQuery(
            @NotNull Car car)
            throws DataException {

        LOGGER.log( Level.INFO, INSERT_CAR_SQL);
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = proxyConnection();
            statement = connection.prepareStatement(INSERT_CAR_SQL);

            boolean preparedStatement =
                    setPreparedStatement(car, statement);

            connection.commit();
            return preparedStatement;

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            LOGGER.log( Level.INFO, "Car can`t be created: {}", INSERT_CAR_SQL);

        } finally {
            eventually(connection, statement);
        }
        return false;
    }

    /**
     * Set instance of the destination class {@link Car} to PreparedStatement
     *
     * @param car instances of Car
     * @return boolean true if successful
     */
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
        statement.setBoolean(9, car.isRent());
        statement.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));

        return statement.execute();
    }
}
