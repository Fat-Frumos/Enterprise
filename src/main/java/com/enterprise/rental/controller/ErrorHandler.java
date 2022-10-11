package com.enterprise.rental.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebServlet("/error")
public class ErrorHandler extends HttpServlet {

    private static final long serialVersionUID = 31L;

    private static final Logger log = Logger
            .getLogger(ErrorHandler.class.getName());

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
            HttpServletResponse response)
            throws IOException {

        String throwable = request.getAttribute("javax.servlet.error.exception").getClass().getName();
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String message = String.format("Servlet %s has thrown an exception %s: %s on the %s.", throwable, statusCode, servletName, requestUri);

        response.setContentType("text/html");

        log.info(message);

        PrintWriter out = response.getWriter();
        String title = "Error/Exception Information";
        String docType = "<!DOCTYPE html>\n";

        out.printf("%s<html>%n", docType);
        out.printf("<head><meta http-equiv=\"Content-Type\" content=\"text/html;");
        out.printf("charset=UTF-8\"><title>%s</title></head>%n<body>%n", title);

        if (statusCode != 500) {
            out.write("<h3>Error Details</h3>");
            out.write("<ul><li><strong>Status Code</strong>?= " + statusCode + "</li>");
            out.write("<li><strong>Requested URI</strong>?= " + requestUri + "</li></ul>");
        } else {
            out.println("<h3>Exception Details</h3>");
            out.printf("<ul><li><strong>Servlet Name</strong>?= %s</li>%n", servletName);
            out.printf("<li><strong>Exception Name</strong>?= %s</li>%n", throwable);
            out.printf("<li><strong>Requested URI</strong>?= %s</li>%n", requestUri);
            out.printf("<li><strong>Exception Message</strong>?= %s</li></ul>%n", throwable);
            out.println("<div> </div>Click <a id=\"homeUrl\" href=\"index.jsp\">Main</a>");
            out.println("</body>\n</html>");
            out.close();
        }
    }
}