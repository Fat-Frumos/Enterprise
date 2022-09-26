package com.enterprise.user.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        forwardRequest(request, response, "/WEB-INF/views/user.jsp");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("name", getRequestParameter(request, "name"));
        request.setAttribute("email", getRequestParameter(request, "email"));
    }

    protected void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }

    protected String getRequestParameter(HttpServletRequest request, String name) {
        String param = request.getParameter(name);
        return param.isEmpty() ? getInitParameter(name) : param;
    }
}