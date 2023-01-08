package com.enterprise.rental.service;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.impl.DefaultOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.enterprise.rental.entity.Role.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private static final OrderDao mockDao = mock(JdbcOrderDao.class);
    private static final OrderService orderService = new DefaultOrderService(mockDao);

    List<Order> orders = new ArrayList<>();

    User jack = new User.Builder()
            .userId(11L)
            .name("Jack")
            .password("password")
            .salt("salt")
            .passport("passport")
            .language("ua")
            .email("email@i.ua")
            .active(true)
            .role(USER.role())
            .build();
    static final Order order = new Order.Builder().userId(1L).orderId(11L).carId(111L).payment(25000.0).build();
    static final Order order1 = new Order.Builder().userId(3L).orderId(11L).carId(111L).payment(35000.0).build();
    static final Order order2 = new Order.Builder().userId(2L).orderId(11L).carId(111L).payment(45000.0).build();

    @BeforeEach
    void init() throws ServiceException, DataException {
        orders.add(order1);
        orders.add(order2);
    }

    @Test
    @DisplayName(value = "Test Order size")
    void randomOrder() throws ServiceException, DataException {
        when(orderService.findAllBy()).thenReturn(orders);
        assertEquals(2, orders.size());
    }

    @Test
    @DisplayName(value = "Test find all Orders invokes and return size of List orders")
    void addOrder() throws ServiceException, DataException {
        when(orderService.findAllBy()).thenReturn(orders);
        assertEquals(2, orderService.findAllBy().size());
    }

    @Test
    @DisplayName(value = "Test get All Orders invokes and check not Null")
    void getAllOrders() throws ServiceException, DataException {
        when(orderService.findAllBy()).thenReturn(orders);
        List<Order> orderList = orderService.findAllBy();
        orderList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test save Orders invokes and verify")
    void save() throws ServiceException, DataException {
        when(orderService.save(order1)).thenReturn(true);
        boolean save = orderService.save(order1);
        assertTrue(save);
    }

    @Test
    @DisplayName(value = "Test save Order invokes and return true")
    void saveOrder() throws ServiceException, DataException, DaoException {
        when(mockDao.save(order)).thenReturn(true);
        boolean saved = orderService.save(order);
        assertTrue(saved);
    }

    @Test
    @DisplayName(value = "Test find Order by Id invokes and return true")
    void findOrder() throws ServiceException, DataException, DaoException {
        when(mockDao.findById(1L)).thenReturn(Optional.ofNullable(order));
        Optional<Order> actual = orderService.findBy(1L);
        verify(mockDao).findById(1L);
        assertEquals(order, actual.get());
    }

    @Test
    void getAll() throws ServiceException, DataException {
        when(orderService.getAll(jack)).thenReturn(orders);
        List<Order> orderList = orderService.findAllBy();
        orderList.stream().map(Objects::nonNull)
                .forEach(Assertions::assertTrue);
    }

    @Test
    @DisplayName(value = "Test update Order invokes and verify")
    void updateOrder() throws ServiceException, DataException, DaoException {

        when(mockDao.edit(order)).thenReturn(order);
        Order actual = orderService.edit(order);
        verify(mockDao).edit(order);
        assertEquals(order, actual);
    }

    @Test
    void delete() throws ServiceException, DataException, DaoException {
        when(mockDao.delete(11L)).thenReturn(true);
        boolean actual = orderService.delete(order.getOrderId());
//        verify(mockDao).delete(11L);
//        assertEquals(true, actual);
    }
}
