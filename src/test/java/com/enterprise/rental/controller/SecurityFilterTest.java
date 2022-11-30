package com.enterprise.rental.controller;

import com.enterprise.rental.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SecurityFilterTest extends Mockito {


    @Mock
    private UserService service;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Inject
    UserServlet userServlet;


    @Test
    void successfulldoGet() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("test");
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        userServlet.doGet(request, response);
        verify(dispatcher, times(1)).forward(any(), any());
    }

    @Test
    void faildoGet() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("test");
        when(service.getById(any())).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> userServlet.doGet(request, response));
    }


    @Test
    void init() {
    }

    @Test
    void doFilter() {
    }

    @Test
    void destroy() {
    }
}