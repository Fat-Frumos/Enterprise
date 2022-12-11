package com.enterprise.rental.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.INDEX;

class StaticServletTest {

    HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
    HttpSession session = Mockito.mock(HttpSession.class);

    @Test
    void doGetTest() throws IOException, ServletException {

        Mockito.when(request.getRequestDispatcher(INDEX)).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(session);

        StaticServlet servlet = new StaticServlet();
        servlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    void doPostTest() throws IOException, ServletException {

        Mockito.when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(session);

        StaticServlet servlet = new StaticServlet();
        servlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }
}