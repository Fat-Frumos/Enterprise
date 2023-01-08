package com.enterprise.rental.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static com.enterprise.rental.dao.jdbc.Constants.*;


class TestFilters extends Mockito {
    BufferedReader br = new BufferedReader(new StringReader("test"));
    HttpServletRequest mockReq;
    HttpServletResponse mockResp;
    FilterChain mockFilterChain;
    FilterConfig mockFilterConfig;

    @BeforeEach
    void init() {
        mockReq = Mockito.mock(HttpServletRequest.class);
        mockResp = Mockito.mock(HttpServletResponse.class);
        mockFilterChain = Mockito.mock(FilterChain.class);
        mockFilterConfig = Mockito.mock(FilterConfig.class);
    }

    @Mock
    RequestDispatcher dispatcher;

    @Test
    void TestDoFilter() throws ServletException, IOException {

        UserFilter filter = new UserFilter();

        when(mockReq.getRequestURI()).thenReturn("/");
        when(mockReq.getReader()).thenReturn(br);
        when(mockReq.getRequestDispatcher(USERS_JSP)).thenReturn(dispatcher);

        filter.init(mockFilterConfig);
        filter.doFilter(mockReq, mockResp, mockFilterChain);
        filter.destroy();
    }


    @Test
    void TestManagerFilter() throws ServletException, IOException {

        SecurityFilter filter = new SecurityFilter();

        when(mockReq.getReader()).thenReturn(br);
        when(mockReq.getRequestURI()).thenReturn("/register");
        when(mockReq.getRequestDispatcher(ORDER_JSP)).thenReturn(dispatcher);
        when(mockReq.getRequestURI()).thenReturn("/user");
        when(mockReq.getRequestDispatcher(USERS_JSP)).thenReturn(dispatcher);

        filter.init(mockFilterConfig);
        filter.doFilter(mockReq, mockResp, mockFilterChain);
        filter.destroy();
    }

    @Test
    void TestCharsetFilter() throws ServletException, IOException {

        CharsetFilter filter = new CharsetFilter();

        when(mockReq.getRequestURI()).thenReturn("/");
        when(mockReq.getRequestDispatcher(INDEX_JSP)).thenReturn(dispatcher);
        when(mockReq.getReader()).thenReturn(br);

        filter.init(mockFilterConfig);
        filter.doFilter(mockReq, mockResp, mockFilterChain);
        filter.destroy();
    }
}