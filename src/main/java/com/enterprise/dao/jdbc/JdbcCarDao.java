package com.enterprise.dao.jdbc;

import com.enterprise.dao.CarDao;
import com.enterprise.dao.mapper.CarMapper;
import com.enterprise.entity.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcCarDao extends PropsHolder implements CarDao {

        private static final CarMapper ROW_MAPPER = new CarMapper();
        private static final String FIND_ALL_SQL = "SELECT id, name, brand, price, date FROM cars;";

        @Override
        public List<Car> findAll() {
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