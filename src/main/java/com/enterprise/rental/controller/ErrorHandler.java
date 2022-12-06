package com.enterprise.rental.controller;

import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class ErrorHandler extends HttpServlet {

    private static final long serialVersionUID = 31L;

    private static final Logger log =Logger.getLogger(ErrorHandler.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        processError(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        processError(request, response);
    }

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

        // Set response content type
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