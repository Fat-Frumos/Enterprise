package com.enterprise.rental.controller;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ErrorHandler
 * allow an application to register an error event handler.
 * The <code>ErrorHandler</code> is used to process exceptions.
 * It`s catches all Exceptions for the Identity Provisioning UI
 * and sends them to ExceptionAction to handle.
 *
 * @author Pasha Pollack
  */
@WebServlet("/error")
public class ErrorHandler extends HttpServlet {

    private static final long serialVersionUID = 31L;

    private static final Logger log =Logger.getLogger(ErrorHandler.class);

    /**
     * @see HttpServlet #HttpServlet()
     */
    public ErrorHandler() {
        super();
    }

    /**
     * @see HttpServlet #doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        processError(request, response);
    }

    /**
     * @see HttpServlet #doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        processError(request, response);
    }


    /**
     * Is used to process exception.
     * It defines a type of exception and sets
     * required error message to session.
     * Analyze the servlet exception
     * Set response content type
     *
     * @param request The http request.
     * @param response The http response.
     */
    private void processError(
            HttpServletRequest request,
            HttpServletResponse response) {

        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        response.setContentType("text/html");

        try {
            PrintWriter out = response.getWriter();

            out.write("<html><head><title>Exception/Error Details</title></head><body>");

            if (statusCode != 500) {
                log.debug("Error Details");
                out.write("<h3>Error Details</h3>");
                out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
                out.write("<strong>Requested URI</strong>:" + requestUri);
            } else {
                log.debug("Exception Details");
                out.write("<h3>Exception Details</h3>");
                out.write(String.format("<ul><li>Servlet Name:%s</li>", servletName));
                out.write(String.format("<li>Exception Name:%s</li>", throwable.getClass().getName()));
                out.write(String.format("<li>status Code:%d</li>", statusCode));
                out.write(String.format("<li>Exception Message:%s</li></ul>", throwable.getMessage()));
            }

            out.write("<br><br>");
            out.write("<a href=\"index.jsp\">Home Page</a>");
            out.write("</body></html>");
        } catch (IOException e) {
            throw new DataException(e);
        }
    }
}