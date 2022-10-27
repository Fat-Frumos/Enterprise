//package com.enterprise.rental.service;
//
//import com.enterprise.rental.dao.CarDao;
//import com.enterprise.rental.dao.OrderDao;
//import com.enterprise.rental.dao.UserDao;
//import com.enterprise.rental.dao.mapper.OrderMapper;
//import com.enterprise.rental.entity.Order;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//import static org.mockito.Mockito.when;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.verifyNoMoreInteractions;
//
//@ExtendWith(MockitoExtension.class)
//class OrderServiceTest {
//
////    @Test
////    void getCost() {
////    }
////
////    @Test
////    void createOrder() {
////    }
//
//    @Mock
//    private OrderDao orderDao;
//    @Mock
//    private UserDao userDao;
//    @Mock
//    private CarDao carDao;
//    @Spy
//    private OrderMapper orderMapper;
//
//    private OrderService orderService;
//
//    private static final List<Order> testOrders = getOrders();
//
//    @BeforeEach
//    public void setUp() {
//        orderService = new OrderService
//                (orderDao, userDao, carDao, orderMapper);
//    }
//
//
//}