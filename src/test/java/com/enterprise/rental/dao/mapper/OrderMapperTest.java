package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class test OrderMapper
 * it used for test mapping data from ResultSet to Order
 *
 * @author Pasha Polyak
 */
class OrderMapperTest {
    OrderMapper mapper = new OrderMapper();

    /**
     * Creates a generic instance of the destination instance
     * Row Mapper that maps each row of a result set with the entity
     * Return an object of the destination type of class
     *
     * @see Mapper#mapRow(ResultSet)
     */
    @Test
    void mapRow() throws SQLException {

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
        when(resultSet.getString("created")).thenReturn(create);
        when(resultSet.getString("term")).thenReturn(term);

        Order order = mapper.mapRow(resultSet);

        assertEquals(1L, order.getOrderId());
        assertEquals(11L, order.getUserId());
        assertEquals(111L, order.getCarId());
        assertEquals(1500.0, order.getPayment());
        assertFalse(order.isRejected());
        assertEquals("Scratches", order.getDamage());
        assertEquals("AA 123456789", order.getPassport());
        assertEquals(now, order.getCreated());
        assertEquals(term, order.getTerm());
        assertEquals("1234565789123", order.getPhone());

        Double payment = resultSet.getDouble("payment");
        String passport = resultSet.getString("passport");
        String reason = resultSet.getString("reason");
        String phone = resultSet.getString("phone");
        String damage = resultSet.getString("damage");
        boolean rejected = resultSet.getBoolean("rejected");

        Timestamp created = create
                != null
                ? Timestamp.valueOf(create)
                : new Timestamp(System.currentTimeMillis());

        Timestamp timestamp = term != null ? Timestamp.valueOf(term) : null;

        String driver = resultSet.getString("driver")
                == null
                ? "off"
                : resultSet.getString("driver");
        long orderId = resultSet.getLong("order_id");
        long carId = resultSet.getLong("car_id");
        long userId = resultSet.getLong("user_id");

        Order build = new Order.Builder()
                .orderId(orderId)
                .userId(userId)
                .carId(carId)
                .payment(payment)
                .passport(passport)
                .reason(reason)
                .phone(phone)
                .damage(damage)
                .created(created)
                .term(timestamp)
                .rejected(rejected)
                .driver(driver.equals("on"))
                .build();
        System.out.println(build);
    }

    /**
     * Test class Mapper used for mapping data from HttpServletRequest to generic Entity
     * Returns the value of a request parameter as a <code>String</code>,
     * or <code>null</code> if the parameter does not exist. Request parameters
     * are extra information sent with the request.
     * Representing the single entity of the parameter
     *
     * @see HttpServletRequest
     */
    @Test
    void testMapRowRequest() {

        Timestamp now = new Timestamp(System.currentTimeMillis());

        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("orderId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("11");
        when(request.getParameter("carId")).thenReturn("111");
        when(request.getParameter("payment")).thenReturn("1500.0");
        when(request.getParameter("phone")).thenReturn("+1234565789123");
        when(request.getParameter("damage")).thenReturn("Scratches");
        when(request.getParameter("passport")).thenReturn("AA 123456789");
        when(request.getParameter("reason")).thenReturn("reason");
        when(request.getParameter("term")).thenReturn(String.valueOf(now));

        Order order = mapper.mapper(request);

        assertEquals(1, order.getOrderId());
        assertEquals(11, order.getUserId());
        assertEquals(111, order.getCarId());
        assertEquals(111, order.getCarId());
        assertEquals("AA 123456789", order.getPassport());
        assertEquals("Scratches", order.getDamage());
        assertEquals("reason", order.getReason());
        assertEquals(1500.0, order.getPayment());
        assertEquals("+1234565789123", order.getPhone());
    }
}