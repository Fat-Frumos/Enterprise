package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.enterprise.rental.service.locale.CurrencyConvector.exchange;

/**
 * Class CarMapper extends Mapper(Car).
 * It used for mapping data from ResultSet to Car
 * It used for mapping data from HttpServletRequest to Entity
 *
 * @author Pasha Polyak
 * @see Mapper#mapRow(ResultSet)
 */
public class CarMapper extends Mapper<Car> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "rent", "date"};

    /**
     * <p>Get a parameters to the servlet container <code>HttpServletRequest</code>
     * set to instance <code>Car</code>
     *
     * @param request HttpServletRequest
     * @return car entity
     */
    @Override
    public Car mapper(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();

        for (String field : carFields) {
            String parameter = request.getParameter(field);
            if (parameter != null) {
                params.put(field, parameter);
            }
        }

        String language = request.getParameter("language");

        double pay = language == null || "en".equals(language)
                ? Double.parseDouble(request.getParameter(carFields[5]))
                : Math.round((Double.parseDouble(request.getParameter(carFields[5])) / exchange) * 100) / 100.0;

        double cost = language == null || "en".equals(language)
                ? Double.parseDouble(request.getParameter(carFields[6]))
                : Math.round((Double.parseDouble(request.getParameter(carFields[6])) / exchange) * 100) / 100.0;

        Timestamp timestamp;

        String date = params.get(carFields[9]);
        if (date != null) {
            date += date.length() == 10 ? " 00:00:00.0" : "";
            timestamp = Timestamp.valueOf(date);
        } else {
            timestamp = new Timestamp(System.currentTimeMillis());
        }

        return new Car.Builder()
                .id(params.get(carFields[0]) != null
                        ? Long.parseLong(params.get(carFields[0]))
                        : UUID.randomUUID().getMostSignificantBits() & 0x7fffffL)
                .name(params.get(carFields[1]))
                .brand(params.get(carFields[2]))
                .model(params.get(carFields[3]))
                .path(params.get(carFields[4]))
                .price(pay)
                .cost(cost)
                .date(timestamp)
                .rent((params.get(carFields[8]) == null
                        ? "off"
                        : params.get(carFields[8]))
                        .equals("on"))
                .build();
    }

    /**
     * Create an instance of the destination class {@link Car}
     *
     * @param resultSet the ResultSet returned by JDBC API
     * @return an instance of the destination type of class
     */
    public Car mapRow(ResultSet resultSet) {
        Timestamp create = Timestamp.valueOf(LocalDateTime.now());

        try {
            String date = resultSet.getString(carFields[9]);
            Timestamp term = date == null ? create : Timestamp.valueOf(date);


            return new Car.Builder()
                    .id(resultSet.getLong(carFields[0]))
                    .year(Integer.parseInt(resultSet.getString(carFields[7])))
                    .rent(Boolean.parseBoolean(resultSet.getString(carFields[8])))
                    .name(resultSet.getString(carFields[1]))
                    .brand(resultSet.getString(carFields[2]))
                    .model(resultSet.getString(carFields[3]))
                    .path(resultSet.getString(carFields[4]))
                    .price(resultSet.getDouble(carFields[5]))
                    .cost(resultSet.getDouble(carFields[6]))
                    .date(term)
                    .build();
        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e);
                throw new DataException("Cannot close resultSet", e);
            }
            LOGGER.log(Level.ERROR, exception.getMessage());
            throw new CarNotFoundException(exception.getMessage());
        }
    }
}