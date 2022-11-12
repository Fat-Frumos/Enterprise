package com.enterprise.rental.filter;

import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.FORBIDDEN;

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

        User user = (User) session.getAttribute("user");

        if (user != null) {
            if (user.getRole().equals("manager") || user.getRole().equals("admin") || user.getRole().equals("user")) {
                log.info(String.format("Access is granted for %s", user));
                request.setAttribute("user", user);
                chain.doFilter(request, response);
            } else {
                log.info("Access is FORBIDDEN");
                servletResponse.sendRedirect(FORBIDDEN);
            }
        } else {
            servletResponse.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
