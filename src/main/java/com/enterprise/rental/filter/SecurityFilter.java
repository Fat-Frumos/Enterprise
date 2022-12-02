package com.enterprise.rental.filter;

import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.LOGIN;

public class SecurityFilter implements Filter {
    protected FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.info(String.format("SecurityFilter: %s", filterName));
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info(String.format("Manager Level#2: Access is granted for %s, role %s", user.getName(), user.getRole()));
                if (user.getRole().equals("admin") || user.getRole().equals("manager")) {
                    request.setAttribute("user", user);
                    chain.doFilter(servletRequest, servletResponse);
                }
            }
        } else {
            log.info("Access is FORBIDDEN");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN);
            if (dispatcher != null) {
                dispatcher.forward(servletRequest, servletResponse);
            }
        }
    }

    public void destroy() {
        filterConfig = null;
    }
}