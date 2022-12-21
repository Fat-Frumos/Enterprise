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

/**
 * Filter contains pages that require authorization
 * is an object that performs filtering tasks
 * <p>
 * Checks the {@link User} with {@link Role} and give permission for admin and manager
 * <p>
 *
 * @author Pasha Pollack
 * @see javax.servlet.Filter
 */
public class SecurityFilter implements Filter {
    /**
     * A filter configuration object used by a servlet container to pass information to a filter during initialization
     */
    protected FilterConfig filterConfig;
    private static final Logger log = Logger.getLogger(SecurityFilter.class);

    /**
     * The servlet container calls the init method exactly once after instantiating the filter
     *
     * @param filterConfig <code> FilterConfig</code> configuration and initialization
     * @see javax.servlet.FilterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        String filterName = filterConfig.getFilterName();
        log.debug(String.format("Init Security Filter: %s", filterName));
    }

    /**
     * The <code>doFilter</code> method of the Filter is called by the container
     * each time a request/response pair is passed through the chain due
     * to a client request for a resource at the end of the chain.
     * Method allows the Filter to pass session User
     * on the request and response to the next entity in the chain
     *
     * @param request  the <code>ServletRequest</code> an object to provide client request information to a servlet
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException      if an I/O related error has occurred during the processing
     * @throws ServletException if an exception occurs that interferes with the
     *                          filter's normal operation
     * @see javax.servlet.FilterChain
     */
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        HttpSession session = servletRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            log.debug("Access is FORBIDDEN");
            RequestDispatcher dispatcher = request.getRequestDispatcher(LOGIN);
            if (dispatcher != null) {
                dispatcher.forward(servletRequest, servletResponse);
            }
        } else {
            User user = (User) session.getAttribute("user");
            // Check the user role is manager or admin, otherwise send on Login page
            if (!Role.ADMIN.role().equals(user.getRole())
                    && !Role.MANAGER.role().equals(user.getRole())) {
                return;
            }
            log.debug(format("%sManager Level#2:%s Access is granted for %s, role %s", RED, RESET, user.getName(), user.getRole()));
            request.setAttribute("user", user);
            chain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service. This
     * method is only called once all threads within the filter's doFilter method have exited or after
     * a timeout period has passed. After the web container calls this method, it will not call the
     * doFilter method again on this instance of the filter. <br>
     * This method gives the filter an opportunity to clean up any resources that are being held (for
     * example, memory, file handles, threads) and make sure that any persistent state is synchronized
     * with the filter's current state in memory.
     */
    @Override
    public void destroy() {
        log.info("Security Filter destroyed");
        filterConfig = null;
    }
}