package com.enterprise.rental.filter;

import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static java.lang.String.format;

public class SecurityFilter implements Filter {
    protected FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.debug(String.format("SecurityFilter: %s", filterName));
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
                log.debug(format("%sManager Level#2:%s Access is granted for %s, role %s", RED, RESET, user.getName(), user.getRole()));
                if (Role.ADMIN.role().equals(user.getRole())
                        || Role.MANAGER.role().equals(user.getRole())) {
                    request.setAttribute("user", user);
                    chain.doFilter(servletRequest, servletResponse);
                }
            }
        } else {
            log.debug("Access is FORBIDDEN");
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