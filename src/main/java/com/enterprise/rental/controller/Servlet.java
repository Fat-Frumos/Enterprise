package com.enterprise.rental.controller;

import com.enterprise.rental.exception.DataException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet extends an HTTP servlet suitable <code>HttpServlet</code> for a Web-site
 * An abstract class of <code>Servlet</code> has one method <code>dispatch</code>
 *
 * @author Pasha Pollack
 */
abstract class Servlet extends HttpServlet {

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
    void dispatch(
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
}
