package com.enterprise.rental.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

class StaticServletTest {

    @Test
    public void doGetTest() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getRequestDispatcher("/index.jsp")).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(session);

        StaticServlet servlet = new StaticServlet();
        servlet.doGet(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }

    @Test
    public void doPostTest() throws IOException, ServletException {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = Mockito.mock(RequestDispatcher.class);
        HttpSession session = Mockito.mock(HttpSession.class);

        Mockito.when(request.getRequestDispatcher("/login.jsp")).thenReturn(dispatcher);
        Mockito.when(request.getSession()).thenReturn(session);

        StaticServlet servlet = new StaticServlet();
        servlet.doPost(request, response);

        Mockito.verify(dispatcher).forward(request, response);
    }
}