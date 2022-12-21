package com.enterprise.rental.dao.jdbc.builder;

import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.Test;

import static com.enterprise.rental.dao.jdbc.Constants.FIND_ALL_LIMIT_SQL;
import static org.junit.jupiter.api.Assertions.*;

class QueryGeneratorTest {

    @Test
    void findAll() {
        Car car = new Car();
        String query = new QueryGenerator().findAll(car.getClass());
        assertEquals(FIND_ALL_LIMIT_SQL, query);
    }
}