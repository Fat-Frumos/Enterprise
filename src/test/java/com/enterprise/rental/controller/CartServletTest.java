package com.enterprise.rental.controller;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.enterprise.rental.controller.Parameter.CLIENT;
import static com.enterprise.rental.entity.Role.USER;
import static org.mockito.Mockito.*;

class CartServletTest {

    private static CartServlet servlet;
    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static RequestDispatcher dispatcher;
    private static HttpSession session;
    private static DefaultUserService userService;
    private static UserDao mockDao;
    User bob;
    int port;

    @BeforeEach
    void setUp() {
        port = 8080;
        servlet = new CartServlet();
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mockDao = mock(JdbcUserDao.class);
        userService = new DefaultUserService(mockDao);
        bob = new User.Builder()
                .userId(11L)
                .name("Bob")
                .password("password")
                .passport("passport")
                .language("ua")
                .email("email@i.ua")
                .active(true)
                .role(USER.role())
                .build();
        session.setAttribute(CLIENT.value(), bob);
    }

    @Test
    void testSessionInvalidate() {
        when(request.getSession()).thenReturn(session);
        doNothing().when(session).invalidate();

        HttpSession session = request.getSession();
        session.invalidate();

        verify(request, times(1)).getSession();
        verify(session, times(1)).invalidate();
    }
}

