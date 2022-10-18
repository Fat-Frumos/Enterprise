package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(UserServlet.class);

    /**
     * Modal window
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Set<User> users = userService.findAll();

        log.debug("users: " + users);
        request.setAttribute("users", users);

        request.getRequestDispatcher("/WEB-INF/views/users.jsp")
                .forward(request, response);
    }

    /**
     * Create new user
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
//        String role = request.getParameter("role");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            log.info(String.format("User: %s", optionalUser));
            request.setAttribute("errorMessage", "User is exists");
        }

        User user = new User(
                UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                name, password, email, "ua", true);

        boolean save = userService.save(user);

        log.info(String.format("%s is created: %s", user, save));

        request.setAttribute("user", user.getName() + "role: ");

        response.sendRedirect("/WEB-INF/views/main.jsp");

        //        request.getRequestDispatcher("/WEB-INF/views/main.jsp")
//                .forward(request, response);
    }

    /**
     * Send the email if forgot password
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = userService.sendEmail(request.getParameter("name"));
        log.info(String.format("%s check your email address", name));
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(request, response);
    }
}
