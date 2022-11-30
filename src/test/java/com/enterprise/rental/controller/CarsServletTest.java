package com.enterprise.rental.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.enterprise.rental.dao.jdbc.Constants.LOGIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarsServletTest {

    private CarsServlet servlet = new CarsServlet();
    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletOutputStream outputStream;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher requestDispatcher;


//    @BeforeEach
//    void init() {
//        MockitoAnnotations.openMocks(this);
//    }

    @Test
    public void test() throws Exception {
        when(request.getParameter("user")).thenReturn("bob");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(LOGIN)).thenReturn(requestDispatcher);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);

        // Verify the session attribute value
        verify(session).setAttribute("user", "bob");

        verify(requestDispatcher).forward(request, response);

        String result = sw.getBuffer().toString().trim();

        System.out.println("Result: " + result);

        assertEquals("Login successfully", result);
    }
}