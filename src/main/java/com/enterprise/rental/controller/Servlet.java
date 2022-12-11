package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet extends an HTTP servlet suitable <code>HttpServlet</code> for a Web-site
 * An abstract class of <code>Servlet</code> has one method <code>dispatch</code>
 *
 * @author Pasha Pollack
 */
public abstract class Servlet extends HttpServlet {

    /**
     * Defines an object that receives requests from the client
     * and sends them to any resource
     * <p>
     * The servlet container creates the <code>RequestDispatcher</code> object,
     * which is used as a wrapper around a server resource located
     * at a particular path or given by a particular name.
     * <p>
     * This abstract class is intended to wrap servlets,
     * but a servlet container can create <code>RequestDispatcher</code>
     * objects to wrap any type of resource.
     * <p>
     * Includes the content of a resource (servlet, JSP page,
     * HTML file) in the response. In essence, this method enables
     * programmatic server-side includes.
     * <p>
     * The {@link ServletResponse} object has its path elements
     * and parameters remain unchanged from the caller's. The included
     * servlet cannot change the response status code or set headers;
     * any attempt to make a change is ignored.
     * <p>
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
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(path);
            dispatcher.include(request, response);
        } catch (ServletException | IOException e) {
            throw new DataException(e.getMessage());
        }
    }

    /**
     * Get User instance {@code Optional<User>} from session
     * <p>Get Attribute User from Session</p>
     *
     * The servlet container uses this interface to create
     * a session between an HTTP client and an HTTP server.
     * The session persists for a specified time period,
     * across more than one connection or page request from the user.
     * A session usually corresponds to one user,
     * who may visit a site many times.
     * The server can maintain a session in many ways such as
     * using cookies or rewriting URLs.
     *<p>
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

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
