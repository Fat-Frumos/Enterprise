package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static com.enterprise.rental.dao.jdbc.Constants.INDEX;
import static org.mockito.Mockito.*;

class LoginServletTest {
    int port;
    HttpSession session;
    HttpServletRequest request;
    HttpServletResponse response;
    User bob;

    @BeforeEach
    void init() {
        port = 8080;
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        bob = new User.Builder()
                .userId(11L)
                .name("Bob")
                .password("password")
                .passport("passport")
                .language("ua")
                .email("email@i.ua")
                .active(true)
                .role("user")
                .build();
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
    void TestLoginServlet() throws IOException, ServletException {

        when(request.getParameter("name")).thenReturn("Mockito");
        when(request.getParameter("password")).thenReturn("Test");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getRequestDispatcher(INDEX)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

//        HttpGet req = new HttpGet(String.format("http://localhost:%d/cars", port));
//        HttpPost rep = new HttpPost(String.format("http://localhost:%d/cars", port));
//
//        System.out.println(req);
//        System.out.println(rep);

        UserServlet userServlet = new UserServlet();
        userServlet.doGet(request, response);
        verify(session).setAttribute("user", bob);
        verify(dispatcher).forward(request, response);

    }
}