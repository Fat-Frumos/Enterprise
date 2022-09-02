package com.enterprise.car.dao.jdbc;

import com.enterprise.car.dao.CarDao;
import com.enterprise.car.dao.mapper.CarMapper;
import com.enterprise.car.entity.Car;

import java.util.List;
import java.util.Optional;

import static com.enterprise.car.dao.jdbc.JdbcCarTemplate.*;

public class JdbcCarDao implements CarDao {

    static final String tags = " id, name, brand, model, path, price, year ";

    static final String tag = " id, name, brand, model, path ";

    static final CarMapper ROW_MAPPER = new CarMapper();
    static final String FIND_ALL_SQL = "SELECT" + tags + "FROM car;";

    static final String FIND_PRICE_ASC_SQL = "SELECT price FROM car order by CAST(price AS Double) asc";

    static final String FIND_PRICE_DESC_SQL = "SELECT price FROM car order by CAST(price AS Double) desc";

    static final String FILTER_BY_BRAND_DESC_SQL = "SELECT" + tags + "FROM car WHERE brand = ? ORDER BY price DESC";
    static final String FILTER_BY_BRAND_SQL = "SELECT" + tags + "FROM car WHERE brand = ?";
    static final String FILTER_BY_ID_SQL = "SELECT" + tag + "FROM car WHERE id = ?;";
    static final String INSERT_CAR_SQL = "INSERT INTO car(" + tags + ") VALUES ( ?, ?, ?, ?, ?, ?, ? )";

    @Override
    public List<Car> findAll() {
        return getCarsQuery(FIND_ALL_SQL);
    }

    @Override
    public Optional<Car> findById(Long id) {
        return getCarById(FILTER_BY_ID_SQL, id);
    }

    @Override
    public List<Car> findByBrand(String brand) {
        return getCarByBrand(FILTER_BY_BRAND_SQL, brand);
    }


    @Override
    public boolean save(Car car) {
        return setCarQuery(INSERT_CAR_SQL, car);
    }

    @Override
    public Car edit(Car car) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

}
