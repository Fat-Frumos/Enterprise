package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderDaoTest {

    @Mock
    private OrderDao daoMock;

    @InjectMocks
    private OrderService service;

    @Test
    void testAddOrder_returnsNewOrder() {
        Order order = new Order();
        assertThat(service.createOrder(order), is(notNullValue()));
        verify(daoMock).save(order);
    }

}