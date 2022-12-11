//package com.enterprise.rental.controller;
//
//import org.junit.jupiter.api.Test;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.StringWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class TestEntryPoint {
//
//
//    @Test
//    public void testServlet() throws Exception {
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//
//        when(request.getParameter("name")).thenReturn("alice");
//        when(request.getParameter("password")).thenReturn("alice");
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        new StaticServlet().doGet(request, response);
//
//        verify(request, atLeast(1)).getParameter("name");
//
//        writer.flush(); // it may not have been flushed yet...
//        assertTrue(stringWriter.toString().contains("My expected string"));
//    }
//
//    @Test
//    public void postLogin() throws ServletException, IOException {
//        URL url = new URL("http://localhost:8080/?name=alice&password=alice");
//        HttpURLConnection http = (HttpURLConnection) url.openConnection();
//        http.disconnect();
//        assertEquals(200, http.getResponseCode());
//        assertEquals("OK", http.getResponseMessage());
//    }
//
//    @Test
//    public void sendRedirect() throws ServletException, IOException {
//        URL url = new URL("http://localhost:8080/?name=alice&password=alice");
//        HttpURLConnection http = (HttpURLConnection) url.openConnection();
//        http.disconnect();
//        assertEquals(200, http.getResponseCode());
//        assertEquals("OK", http.getResponseMessage());
//    }
//
//    @Test
//    public void getCars() throws ServletException, IOException {
//
//        URL url = new URL("http://localhost:8080/cars");
//        HttpURLConnection http = (HttpURLConnection) url.openConnection();
//        http.disconnect();
//        assertEquals(200, http.getResponseCode());
//        assertEquals("OK", http.getResponseMessage());
//    }
//
//    @Test
//    public void getMain() throws ServletException, IOException {
//
//        URL url = new URL("http://localhost:8080/");
//        HttpURLConnection http = (HttpURLConnection) url.openConnection();
//        http.disconnect();
//        assertEquals(200, http.getResponseCode());
//        assertEquals("OK", http.getResponseMessage());
//    }
//}
