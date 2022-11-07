package com.enterprise.rental.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServletTest extends Mockito  {

    private UserServlet servlet = new UserServlet();
    private HttpServletRequest request = mock(HttpServletRequest.class);

    private HttpServletResponse response = mock(HttpServletResponse.class);


    @Test
    public void correctUsernameInRequest() throws ServletException, IOException {


        when(request.getParameter("name")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("secret");

        servlet.doPost(request, response);

        assertEquals("text/html", response.getContentType());

    }

    @Test
    void givenHttpServletRequest_whenMockedWithMockito_thenReturnsParameterValues() throws IOException, ServletException {
        // mock HttpServletRequest & HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // mock the returned value of request.getParameterMap()
        when(request.getParameter("firstName")).thenReturn("Mockito");
        when(request.getParameter("lastName")).thenReturn("Test");
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

    }

    @Test
    public void testServlet() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("username")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("secret");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("username"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("My expected string"));
    }
}