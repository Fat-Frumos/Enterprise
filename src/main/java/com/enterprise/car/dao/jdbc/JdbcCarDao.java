package com.enterprise.car.dao.jdbc;

import com.enterprise.car.dao.mapper.CarMapper;
import com.enterprise.car.dao.CarDao;
import com.enterprise.car.dao.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCarDao extends PropsReader implements CarDao {

    private static final CarMapper ROW_MAPPER = new CarMapper();
    private static final String FIND_ALL_SQL = "SELECT card_id, name, brand, price, date FROM cars;";

    private static final String FIND_ASC_SQL = "select price from cars order by CAST(price AS Double) asc";

    private static final String FIND_DESC_SQL = "select price from cars order by CAST(price AS Double) desc";

    private static final String FILTER_BY_BRAND_DESC_SQL = "SELECT card_id, name, brand, price, date FROM cars WHERE brand = 'brand' ORDER BY price DESC";

    private static final String FILTER_BY_ID_SQL = "SELECT card_id, name, brand, price, date FROM cars WHERE card_id = ?";
    @Override
    public List<Car> findAll() {
        return select(FIND_ALL_SQL);
    }

    @Override
    public List<Car> findById(Long id) {
        return select(FIND_ALL_SQL);
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Car> carList = new ArrayList<>();

            while (resultSet.next()) {
                Car car = ROW_MAPPER.carRow(resultSet);
                carList.add(car);
            }
            return carList;
        } catch (SQLException sqlException) {
            throw new RuntimeException("Failed fetch to DB "
                    + sqlException.getMessage(), sqlException);
        }
    }
}