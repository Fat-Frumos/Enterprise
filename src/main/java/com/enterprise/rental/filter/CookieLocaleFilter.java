//package com.enterprise.rental.filter;
//
//import org.apache.log4j.Logger;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = { "/*" })
//public class CookieLocaleFilter implements Filter {
//    protected FilterConfig filterConfig;
//    private static final Logger log = Logger.getLogger(CookieLocaleFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.filterConfig = filterConfig;
//        String filterName = filterConfig.getFilterName();
//        log.info(String.format("LocaleFilter: %s", filterName));
//    }
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        if (request.getParameter("cookieLocale") != null) {
//
//            Cookie cookie = new Cookie("lang", request.getParameter("cookieLocale"));
//
//            response.addCookie(cookie);
//
//
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    public void destroy() {
//        filterConfig = null;
//    }
//}