package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(UserServlet.class);
    private final UserService userService;

    public UserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userService.findAll();

        log.info("Found users: " + users.size());

        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/views/users.jsp")
                .forward(request, response);
    }
}
