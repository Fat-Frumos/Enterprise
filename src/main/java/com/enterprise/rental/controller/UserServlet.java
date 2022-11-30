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
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
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

        OrderService orderService = new OrderService();
        HttpSession session = request.getSession(false);
        String path = "/";
        User user;
        if (session != null) {
            user = (User) session.getAttribute("user");
            String role = user != null ? user.getRole() : "guest";
            session.setAttribute("user", user);
            if (Objects.equals(role, "admin")) {
                List<User> users = userService.getAll();
                log.info(String.format("There are %d users", users.size()));
                request.setAttribute("users", users);
                path = USERS;
            } else if (Objects.equals(role, "manager")) {
                List<Order> orders = orderService.getAll();
                request.setAttribute("orders", orders);
                path = CONTRACT;
            } else if (Objects.equals(role, "user")) {
                List<Order> userOrders = orderService.getAll()
                        .stream()
                        .filter(order -> order.getUserId() == (user.getUserId()))
                        .collect(Collectors.toList());
                log.info("Get userOrders: " + userOrders.size());
                request.setAttribute("orders", userOrders);
                path = ORDERS;
            } else {
                path = FORGOT;

            }
        }
        dispatch(request, response, path);
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
        String role = request.getParameter("role");
        String language = request.getParameter("language");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            log.info(String.format("User: %s", optionalUser));
            request.setAttribute("errorMessage", "User is exists please try again");
            response.sendRedirect(LOGIN);
        } else {
            long id = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffffffL;

            User user = new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
                    .language(language)
                    .email(email)
                    .active(true)
                    .role(role)
                    .build();
            boolean save = userService.save(user);

            log.info(String.format("%s is created: %s", user, save));
            request.setAttribute("user", user);

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

        boolean sendEmail = userService.sendEmail(request.getParameter("username"));
        log.info(String.format("Letter sent: %b", sendEmail));
//        response.sendRedirect(NOT_FOUND);
        response.sendRedirect(LOGIN);
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