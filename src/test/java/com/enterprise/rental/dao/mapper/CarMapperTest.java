package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarMapperTest {

    @Test
    void testMapRow() throws SQLException {

        CarMapper mapper = new CarMapper();

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(1l);
        when(resultSet.getString("name")).thenReturn("X7");
        when(resultSet.getString("brand")).thenReturn("BMW");
        when(resultSet.getString("model")).thenReturn("G07");
        when(resultSet.getString("path")).thenReturn("http//");
        when(resultSet.getDouble("price")).thenReturn(500.0);
        when(resultSet.getDouble("cost")).thenReturn(25000.0);
        when(resultSet.getString("year")).thenReturn("2022");

        Car car = mapper.mapRow(resultSet);

        assertEquals(1l, car.getId());
        assertEquals("BMW", car.getBrand());
        assertEquals("X7", car.getName());
        assertEquals("G07", car.getModel());
        assertEquals("http//", car.getPath());
        assertEquals(500.0, car.getPrice());
        assertEquals(25000.0, car.getCost());
        assertEquals(2022, car.getYear());
    }
}