package com.enterprise.rental.security;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "UserFilter", urlPatterns = "/user/*")
public class SecurityFilter implements Filter {

    private static final Logger log = Logger.getLogger(SecurityFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String filterName = filterConfig.getFilterName();
        log.info(String.format("filterName: %s", filterName));
//        ServletContext servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        if (session.getAttribute("user") != null) {
            chain.doFilter(request, response);
        } else {
            String contextPath = servletRequest.getContextPath();
            log.info(contextPath);
            servletResponse.sendRedirect("login.jsp");}

//        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
//        Transaction transactional =  connection dao setConnection();
//        dao(Sessions.getConnection);
    }

    @Override
    public void destroy() {
    }
}
// .ClassCastException: class com.enterprise.rental.controller.UserFilter cannot be cast to class javax.servlet.Servlet
// (com.enterprise.rental.controller.UserFilter is in unnamed module of loader org.apache.catalina.loader.Web appClassLoader
