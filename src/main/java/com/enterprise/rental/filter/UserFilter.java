package com.enterprise.rental.filter;

import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.locale.CurrencyConvector;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.service.locale.CurrencyConvector.exchangeRate;

/**
 * Filter contains pages that require authentication
 * is an object that performs filtering tasks
 * <p>
 * Checks the {@link User} with {@link Role}
 * and give permission for admin, manager. and users
 *
 * @author Pasha Pollack
 * @see javax.servlet.Filter
 */
public class UserFilter implements Filter {

    /**
     * A filter configuration object used by a servlet container to pass information to a filter during initialization
     */
    protected FilterConfig filterConfig;

    private static final Logger log = Logger.getLogger(UserFilter.class);

    /**
     * <p>The servlet container calls the init method exactly once after instantiating the filter
     *
     * @param filterConfig <code> FilterConfig</code> configuration and initialization
     *                     Filter-name of this filter as defined in the deployment descriptor.
     * @see javax.servlet.FilterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.debug(String.format("%sSecurityFilter: %s%s", RED, filterName, RESET));
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due
     * to a client request for a resource at the end of the chain.
     * Method allows the Filter to pass session User
     * on the request and response to the next entity in the chain.
     * If user role not registered, send on Login page
     * Returns the current <code>HttpSession</code>
     * associated with this request or, if there is no
     * current session and <code>create</code> is true,
     * returns a new session.
     *
     * @param request  the <code>ServletRequest</code> an object to provide client request information to a servlet
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see javax.servlet.FilterChain
     **/
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

        if (session != null && session.getAttribute("user") != null) {
            // Get the user from session if it exists
            User user = (User) session.getAttribute("user");
            // Check the user is registered, otherwise send on Login page
            if (Objects.equals(user.getRole(), Role.USER.role())
                    || Objects.equals(user.getRole(), Role.MANAGER.role())
                    || Objects.equals(user.getRole(), Role.ADMIN.role())) {
                user.setExchange("ua".equals(user.getLanguage()) ? exchangeRate : 1);
                log.debug(String.format(
                        "%sUser Level#3: Access is granted for %s, role %s, exchange: %s%s",
                        CYAN, user.getName(), user.getRole(), exchangeRate, RESET));
                request.setAttribute("user", user);
                chain.doFilter(request, response);
            } else {
                log.debug(String.format("%sAccess is FORBIDDEN", RED));
                request.getRequestDispatcher(LOGIN)
                        .forward(servletRequest, servletResponse);
            }
        }
    }

    /**
     * Clean up all the filters supplied,
     * calling each one's destroy method in turn,
     * but in reverse order.
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void destroy() {
        log.info("User filter destroyed");
        filterConfig = null;
    }
}