package com.enterprise.rental.filter;

import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.enterprise.rental.dao.jdbc.Constants.*;

public class UserFilter implements Filter {
    protected FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(UserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.debug(String.format("%sSecurityFilter: %s%s", RED, filterName, RESET));
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
                log.debug(String.format(
                        "%sUser Level#3: Access is granted for %s, role %s%s",
                        CYAN_BOLD, user.getName(), user.getRole(), RESET));
                if (Objects.equals(user.getRole(), Role.USER.role())
                        || Objects.equals(user.getRole(), Role.MANAGER.role())
                        || Objects.equals(user.getRole(), Role.ADMIN.role())) {
                    request.setAttribute("user", user);
                    chain.doFilter(request, response);
                }
            } else {
                log.debug(String.format("%sAccess is FORBIDDEN", RED));
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