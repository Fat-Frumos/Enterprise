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

    protected FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.info(String.format("SecurityFilter: %s", filterName));
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
                log.info(String.format("User Level#3: Access is granted for %s, role %s", user.getName(), user.getRole()));
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
            servletResponse.sendRedirect(LOGIN);
        }
    }

    public void destroy() {
        filterConfig = null;
    }
}