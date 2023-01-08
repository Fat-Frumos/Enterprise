package com.enterprise.rental.controller;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.controller.Parameter.CLIENT;
import static com.enterprise.rental.dao.jdbc.Constants.USERS_JSP;
import static com.enterprise.rental.entity.Role.USER;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginServletTest {

    private static LoginServlet servlet;
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
        servlet = new LoginServlet();
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        mockDao = mock(JdbcUserDao.class);
//        userService = new DefaultUserService(mockDao);
        userService = new DefaultUserService();
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

    @Test
    void testLoginDoGetSessionRequestDispatcherForward() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        servlet.doGet(request, response);

        verify(request, times(1)).getSession();
        verify(dispatcher).forward(request, response);
    }


    @Test
    void testDoGetInvalidateSessionRequestDispatcherForward() throws IOException, ServletException {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        servlet.doGet(request, response);
        verify(session, times(1)).invalidate();
        verify(dispatcher).forward(request, response);

    }

    @Test
    void testDoPutDispatcherForward() throws ServletException, IOException {
        when(request.getRequestDispatcher(USERS_JSP)).thenReturn(dispatcher);

        servlet.doPut(request, response);

        verify(dispatcher).forward(request, response);

    }
}