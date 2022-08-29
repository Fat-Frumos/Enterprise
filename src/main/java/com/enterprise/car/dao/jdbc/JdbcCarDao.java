package com.enterprise.car.dao.jdbc;

import com.enterprise.car.dao.CarDao;
import com.enterprise.car.dao.mapper.CarMapper;
import com.enterprise.car.entity.Car;
import com.enterprise.car.exception.DataException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JdbcCarDao extends PropsReader implements CarDao {

    private static final CarMapper ROW_MAPPER = new CarMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, brand, price, model, path FROM car;";

    private static final String FIND_PRICE_ASC_SQL = "SELECT price FROM car order by CAST(price AS Double) asc";

    private static final String FIND_PRICE_DESC_SQL = "SELECT price FROM car order by CAST(price AS Double) desc";

    private static final String FILTER_BY_BRAND_DESC_SQL = "SELECT id, name, brand, price, model, path FROM car WHERE brand = ? ORDER BY price DESC";
    //    private static final String FILTER_BY_BRAND_SQL = "SELECT id, name, brand, price, model, year, path FROM car WHERE brand = ?";
    private static final String FILTER_BY_ID_SQL = "SELECT id, name, brand, price, model FROM car WHERE id = ?;";

    @Override
    public List<Car> findAll() {
        return select(FIND_ALL_SQL);
    }

    @Override
    public List<Car> findById(Long id) {
        return select(FILTER_BY_ID_SQL);
    }

    @Override
    public List<Car> findByBrand(String brand) {
        return new ArrayList<>();
    }


    @Override
    public boolean save(Car car) {
        return false;
    }

    @Override
    public Car edit(Car car) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    private List<Car> select(String sql) {
        List<Car> cars = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    while (resultSet.next()) {

                        Car car = ROW_MAPPER.carRow(resultSet);

                        cars.add(car);
                    }
                }
            }
            return cars;
        } catch (SQLException e) {
            throw new DataException(e.getMessage());
        }
    }
}
