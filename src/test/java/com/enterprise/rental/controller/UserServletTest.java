package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServletTest extends Mockito {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    private UserServlet servlet = new UserServlet();

    List<User> userList = new ArrayList<>();


    @BeforeEach
    void setUp() {

        User jack = new User.Builder()
                .userId(2L)
                .name("Jack")
                .password("jack")
                .language("ua")
                .email("qwerty@i.ua")
                .build();

        User bob = new User.Builder()
                .userId(1L)
                .name("bob")
                .password("bob")
                .language("en")
                .email("bob@i.ua")
                .build();

        userList.add(jack);
        userList.add(bob);
    }

    @Test
    @Order(1)
    void testDashboard() throws ServletException, IOException {

        User user = userList.get(0);

        when(request.getServletPath()).thenReturn("/user");

        when(request.getSession()).thenReturn(session);

        when(session.getAttribute("user")).thenReturn("2");
        when(request.getAttribute("GetUser")).thenReturn(user);

        when(request.getRequestDispatcher("/users.jsp")).thenReturn(dispatcher);

        StringWriter StringWriter = new StringWriter();
        PrintWriter PrintWriter = new PrintWriter(StringWriter);

        when(response.getWriter()).thenReturn(PrintWriter);

        new UserServlet().doGet(request, response);

        // verify
        verify(dispatcher).forward(request, response);

    }

    @Test
    @Order(2)
    void testEditForm() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);

        when(request.getServletPath()).thenReturn("/user");

        when(request.getParameter("id")).thenReturn("1");

        when(request.getRequestDispatcher("/users.jsp")).thenReturn(dispatcher);

        User jack = new User.Builder()
                .userId(2L)
                .name("Jack")
                .password("jack")
                .language("ua")
                .email("qwerty@i.ua")
                .build();

        StringWriter StringWriter = new StringWriter();
        PrintWriter PrintWriter = new PrintWriter(StringWriter);

        when(request.getAttribute("user")).thenReturn(jack);
        when(response.getWriter()).thenReturn(PrintWriter);

        servlet.doGet(request, response);

        // verify
        verify(dispatcher).forward(request, response);
        verify(request, atLeast(1)).getParameter("id");

    }

    @Test
    @Order(3)
    void testUpdate() throws ServletException, IOException {
        /* fail("Not yet implemented"); */
        when(request.getSession()).thenReturn(session);

        when(request.getServletPath()).thenReturn("/user");

        when(request.getParameter("userId")).thenReturn("1");
        when(request.getParameter("username")).thenReturn("test1");
        when(request.getParameter("email")).thenReturn("test1");
        when(request.getParameter("password")).thenReturn("test1");

        StringWriter StringWriter = new StringWriter();
        PrintWriter PrintWriter = new PrintWriter(StringWriter);

        when(response.getWriter()).thenReturn(PrintWriter);

        new UserServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("userId");
        verify(request, atLeast(1)).getParameter("username");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("password");

    }

    @Test
    @Order(4)
    void testLogout() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/login");

        StringWriter StringWriter = new StringWriter();
        PrintWriter PrintWriter = new PrintWriter(StringWriter);

        when(response.getWriter()).thenReturn(PrintWriter);

        new UserServlet().doGet(request, response);

        verify(session).setAttribute("user", null);
        verify(session).setAttribute("role", null);

    }

    @Test
    @Order(5)
    void testDelete() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getServletPath()).thenReturn("/user");

        when(request.getParameter("id")).thenReturn("1");

        StringWriter StringWriter = new StringWriter();
        PrintWriter PrintWriter = new PrintWriter(StringWriter);

        when(response.getWriter()).thenReturn(PrintWriter);

        new UserServlet().doGet(request, response);

        verify(request, atLeast(1)).getParameter("id");

    }


    @Test
    void correctUsernameInRequest() throws ServletException, IOException {


        when(request.getParameter("name")).thenReturn("bob");
        when(request.getParameter("password")).thenReturn("bob");

        servlet.doPost(request, response);

        assertEquals("text/html", response.getContentType());

    }

    @Test
    void givenHttpServletRequestWhenReturnsParameters() throws IOException, ServletException {
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

        when(request.getParameter("username")).thenReturn("bob");
        when(request.getParameter("password")).thenReturn("bob");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("username");

        writer.flush();
        assertTrue(stringWriter.toString().contains("My expected string"));
    }
}