package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    OrderService orderService = new OrderService();
    private static final Logger log = Logger.getLogger(UserServlet.class);

    /**
     * View all user if forgot
     * <p>
     * if Admin role show all users
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            String role = user != null ? user.getRole() : "guest";
            if ("admin".equals(role)) {
                session.setAttribute("user", user);
                List<User> users = userService.getAll();
                log.info(String.format("There are %d users", users.size()));
                request.setAttribute("users", users);
                dispatch(request, response, USERS);
            } else if ("manager".equals(role)) {
                session.setAttribute("user", user);
                List<Order> orders = orderService.getAll();
                log.info(orders.size());
                request.setAttribute("orders", orders);
                dispatch(request, response, CONTACT);
            } else {
                dispatch(request, response, FORGOT);
            }
        } else {
            dispatch(request, response, LOGIN);
        }
    }

    /**
     * Authentication and authorization User
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
            request.setAttribute("errorMessage", "User is exists please try again");
            response.sendRedirect(LOGIN);
        } else {
            User user = new User(
                    UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                    name, password, email, "en", true);

            boolean save = userService.save(user);

            log.info(String.format("%s is created: %s", user, save));

            request.setAttribute("user", user.getName() + "role: ");

            request.getRequestDispatcher(MAIN)
                    .forward(request, response);
        }
    }

    /**
     * Send an email if the password has been forgotten
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String message = userService.sendEmail(request.getParameter("username"));

        response.sendRedirect(LOGIN);

        log.info(String.format("%s check your email address", message));

    }

    void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path) {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        try {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(path);
            dispatcher.include(request, response);
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
    }
}