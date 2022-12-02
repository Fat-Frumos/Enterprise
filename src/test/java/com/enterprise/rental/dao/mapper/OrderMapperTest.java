package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderMapperTest {

    @Test
    void mapRow() throws SQLException {
        OrderMapper mapper = new OrderMapper();

        ResultSet resultSet = mock(ResultSet.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        String create = String.valueOf(now);

        String term = resultSet.getString("term");


        when(resultSet.getLong("order_id")).thenReturn(1L);
        when(resultSet.getLong("user_id")).thenReturn(11L);
        when(resultSet.getLong("car_id")).thenReturn(111L);
        when(resultSet.getDouble("payment")).thenReturn(1500.0);
        when(resultSet.getBoolean("driver")).thenReturn(true);
        when(resultSet.getBoolean("rejected")).thenReturn(false);
        when(resultSet.getBoolean("closed")).thenReturn(true);
        when(resultSet.getString("phone")).thenReturn("1234565789123");
        when(resultSet.getString("damage")).thenReturn("Scratches");
        when(resultSet.getString("passport")).thenReturn("AA 123456789");
        when(resultSet.getString("term")).thenReturn(term);
        when(resultSet.getString("created")).thenReturn(create);

        Order order = mapper.mapRow(resultSet);

        assertEquals(1l, order.getOrderId());
        assertEquals(11l, order.getUserId());
        assertEquals(111l, order.getCarId());
        assertEquals(1500.0, order.getPayment());
        assertEquals(now, order.getCreated());
        assertEquals(term, order.getTerm());
        assertFalse(order.isRejected());
        assertEquals("1234565789123", order.getPhone());
        assertEquals("Scratches", order.getDamage());
        assertEquals("AA 123456789", order.getPassport());

    }
}