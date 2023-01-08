package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.exception.DaoException;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarMapperTest {
    private final CarMapper mapper = new CarMapper();

    @Test
    void testMapRow() throws SQLException, DaoException {

//        long carId = params.get(carFields[0]) != null ? Long.parseLong(params.get(carFields[0])) : UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getLong("id")).thenReturn(1l);
        when(resultSet.getString("name")).thenReturn("X7");
        when(resultSet.getString("brand")).thenReturn("BMW");
        when(resultSet.getString("model")).thenReturn("G07");
        when(resultSet.getString("path")).thenReturn("http//");
        when(resultSet.getDouble("price")).thenReturn(500.0);
        when(resultSet.getDouble("cost")).thenReturn(25000.0);
        when(resultSet.getString("year")).thenReturn("2022");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(resultSet.getString("date")).thenReturn(String.valueOf(now));


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

    @Test
    void testMapRowRequest()  {

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("X7");
        when(request.getParameter("brand")).thenReturn("BMW");
        when(request.getParameter("model")).thenReturn("G07");
        when(request.getParameter("path")).thenReturn("http//");
        when(request.getParameter("price")).thenReturn("500.0");
        when(request.getParameter("cost")).thenReturn("25000.0");
        Timestamp now = new Timestamp(System.currentTimeMillis());
        when(request.getParameter("date")).thenReturn(String.valueOf(now));

        Car car = mapper.mapper(request);

        assertEquals(1l, car.getId());
        assertEquals("BMW", car.getBrand());
        assertEquals("X7", car.getName());
        assertEquals("G07", car.getModel());
        assertEquals("http//", car.getPath());
        assertEquals(500.0, car.getPrice());
        assertEquals(25000.0, car.getCost());
    }
}