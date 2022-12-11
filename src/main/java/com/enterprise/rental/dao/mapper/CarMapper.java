package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CarMapper extends Mapper<Car> {

    private static final Logger log = Logger.getLogger(CarMapper.class);

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
            String rent = resultSet.getString("rent");
            boolean closed = Boolean.parseBoolean(rent);

            return new Car.Builder()
                    .id(id)
                    .name(name)
                    .brand(brand)
                    .model(model)
                    .path(path)
                    .price(price)
                    .cost(cost)
                    .year(year)
                    .rent(closed)
                    .build();
        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException("Cannot close resultSet", e);
            }
            throw new CarNotFoundException(exception.getMessage());
        }
    }

    public Car carMapper(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "rent"};
        for (String field : carFields) {
            String parameter = request.getParameter(field);
            log.info(parameter);
            if (parameter != null) {
                params.put(field, parameter);
            }
        }
        String rent = params.get("rent") == null
                ? "off" : params.get("rent");

        long carId;

        if (params.get(carFields[0]) != null) {
            carId = Long.parseLong(params.get(carFields[0]));
        } else {
            carId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
        }

        return new Car.Builder()
                .id(carId)
                .name(params.get(carFields[1]))
                .brand(params.get(carFields[2]))
                .model(params.get(carFields[3]))
                .path(params.get(carFields[4]))
                .price(Double.valueOf(params.get(carFields[5])))
                .cost(Double.valueOf(params.get(carFields[6])))
//                .year(Integer.parseInt(params.get(carFields[7])))
                .rent(rent.equals("on"))
                .build();
    }
}