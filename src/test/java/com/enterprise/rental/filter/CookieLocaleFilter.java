package com.enterprise.rental.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CookieLocaleFilter", urlPatterns = {"/*"})
public class CookieLocaleFilter implements Filter {
    protected FilterConfig filterConfig;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        LOGGER.log(Level.INFO, String.format("LocaleFilter: %s", filterName));
    }

    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getParameter("cookieLocale") != null) {

            Cookie cookie = new Cookie("lang", request.getParameter("cookieLocale"));

            response.addCookie(cookie);

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {
        filterConfig = null;
    }
}