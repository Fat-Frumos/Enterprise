package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static com.enterprise.rental.controller.Parameter.CLIENT;

/**
 * Abstract class Servlet extends an HTTP servlet suitable <code>HttpServlet</code> for a web-site.
 * Class of <code>Servlet</code> has one method <code>dispatch</code>
 *
 * @author Pasha Polyak
 * @see HttpServlet
 */
public abstract class Servlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Defines an object that receives requests from the client
     * and sends them to any resource.
     * The servlet container creates the <code>RequestDispatcher</code> object,
     * which is used as a wrapper around a server resource located
     * at a particular path or given by a particular name.
     * This abstract class is intended to wrap servlets,
     * but a servlet container can create <code>RequestDispatcher</code>
     * objects to wrap any type of resource.
     * Includes the content of a resource (servlet, JSP page,
     * HTML file) in the response. In essence, this method enables
     * programmatic server-side includes.
     * The {@link ServletResponse} object has its path elements
     * and parameters remain unchanged from the caller's. The included
     * servlet cannot change the response status code or set headers;
     * any attempt to make a change is ignored.
     * The request and response parameters must be either the same
     * objects as were passed to the calling servlet's service method or be
     * subclasses of the {@link ServletRequestWrapper} or {@link ServletResponseWrapper} classes
     * that wrap them.
     *
     * @param request  the {@link HttpServletRequest} object that
     *                 contains the request the client made of
     *                 the servlet
     * @param response the {@link HttpServletResponse} object that
     *                 contains the response the servlet returns
     *                 to the client
     * @param path     the String that contains the response
     *                 the servlet returns to the client
     */
    protected void dispatch(
            HttpServletRequest request,
            HttpServletResponse response,
            String path) {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.log(Level.ERROR, "{}", e.getMessage());
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Sends a temporary redirect response to the client using the specified redirect location path.
     *
     * @param request  the {@link HttpServletRequest} object that
     *                 contains the request the client made of
     *                 the servlet
     * @param response the {@link HttpServletResponse} object that
     *                 contains the response the servlet returns
     *                 to the client
     * @param path     the String that contains the response
     *                 the servlet returns to the client
     */
    protected void redirect(
            HttpServletRequest request,
            HttpServletResponse response,
            String path) {
        try {
            response.sendRedirect(path);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, "{}", e.getMessage());
            dispatch(request, response, path);
        }
    }

    /**
     * Get User instance {@code Optional<User>} from session
     * <p>Get Attribute User from session scope</p>
     * <p>
     * The servlet container uses this interface to create
     * a session between an HTTP client and an HTTP server.
     * The session persists for a specified time period,
     * across more than one connection or page request from the user.
     * A session usually corresponds to one user,
     * who may visit a site many times.
     * The server can maintain a session in many ways such as
     * using cookies or rewriting URLs.
     * <p>
     * Set the object bound with the specified name in this session,
     * or null if no object is bound under the name.
     * {@code name} a string specifying the name of the object
     *
     * @param session an {@link HttpSession} object that
     *                contains the request the client has made
     *                of the servlet
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     */
    protected static Optional<User> getUser(HttpSession session) {

        if (session.getAttribute(CLIENT.value()) != null) {
            User user = (User) session.getAttribute(CLIENT.value());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    /**
     * Get User instance {@code Optional<User>} from request
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     *
     *                <p>Get Attribute User from HttpServletRequest</p>
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     */
    protected Optional<User> getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return getUser(session);
    }
}
