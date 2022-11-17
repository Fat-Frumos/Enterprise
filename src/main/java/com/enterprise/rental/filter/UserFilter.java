package com.enterprise.rental.filter;

import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.*;

public class UserFilter implements Filter {
    private static final Logger log = Logger.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException,
            ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info(String.format("User filter Access is granted for %s", user));
                if (user.getRole().equals("user")
                        || user.getRole().equals("manager")
                        || user.getRole().equals("admin")) {
                    request.setAttribute("user", user);
                    chain.doFilter(request, response);
                }
            } else {
                log.info("Access is FORBIDDEN");
                request.getRequestDispatcher(FORBIDDEN)
                        .forward(servletRequest, servletResponse);
            }
        } else {
            request.getRequestDispatcher(LOGIN)
                    .forward(servletRequest, servletResponse);
        }
    }

    public void destroy() {
    }
}