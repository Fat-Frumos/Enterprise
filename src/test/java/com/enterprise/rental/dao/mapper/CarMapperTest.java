package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarMapperTest {
    //TODO Builder resultSet parser //TEST find by brand

    @Test
    void testMapRow() throws SQLException {
        CarMapper mapper = new CarMapper();

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(1l);
        when(resultSet.getString("name")).thenReturn("X7");
        when(resultSet.getString("brand")).thenReturn("BMW");
        when(resultSet.getString("model")).thenReturn("G07");
        when(resultSet.getString("path")).thenReturn("http//");
        when(resultSet.getDouble("price")).thenReturn(25000.0);
        when(resultSet.getInt("year")).thenReturn(2022);

        Car car = mapper.mapRow(resultSet);

        assertEquals(1l, car.getId());
        assertEquals("BMW", car.getBrand());
        assertEquals("X7", car.getName());
        assertEquals("G07", car.getModel());
        assertEquals("http//", car.getPath());
        assertEquals(25000.0, car.getPrice());
        assertEquals(2022, car.getYear());

    }
}