package com.enterprise.rental.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.service.OrderService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class OrderDaoTest {

        @Mock
        private OrderDao daoMock;

        @InjectMocks
        private OrderService service;

        public void setUp() throws Exception {

            MockitoAnnotations.openMocks(this);
        }

//        @Test
//        public void testAddOrder_returnsNewOrder() {
//            Order booking = new Order();
//            when(daoMock.save(any(Order.class))).thenReturn(booking);
//
//            Order order = new Order();
//
//            assertThat(service.createOrder(order), is(notNullValue()));
//
//        }


        //Using Answer to set an id to the order which is passed in as a parameter to the mock method.
        @Test
        void testAddOrder_returnsNewOrderWithId() throws Exception {
            setUp();
            when(daoMock.save(any(Order.class))).thenAnswer((Answer<Order>) invocation -> {

                Object[] arguments = invocation.getArguments();

                if (arguments != null && arguments.length > 0 && arguments[0] != null){

                    Order order = (Order) arguments[0];
                    order.setOrderId(1);
                    return order;
                }

                return null;
            });

            Order order = new Order();

            assertThat(service.createOrder(order), is(notNullValue()));

        }
}