package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultOrderService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * <p>User Servlet extends an abstract Servlet suitable for a Web-site.
 * <p>A subclass of <code>HttpServlet</code> override three methods:
 * <ul>
 * <li> <code>doGet</code>, for HTTP GET requests (get users)
 * <li> <code>doPost</code>, for HTTP POST requests (auth user)
 * <li> <code>doPut</code>, if the servlet supports HTTP PUT requests (send email to user)
 * </ul>
 *
 * @author Pasha Pollack
 */
@WebServlet(urlPatterns = "/user")
public class UserServlet extends Servlet {
    private final UserService userService = new DefaultUserService();
    private static final Logger log = Logger.getLogger(UserServlet.class);

    /**
     * View list of orders
     * for user role: order page with invoices,
     * for manager role: contract page
     * If user role is Admin: list of users
     * <p>
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * <p>Overriding this method to support a GET request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a GET request that returns without body in the
     * response, only the request header fields.
     *
     * <p>The GET method should be safe, that is, without
     * any side effects for which users are held responsible.
     * For example, most form queries have no side effects.
     * If a client request is intended to change stored data,
     * the request should use some other HTTP method.
     *
     * <p>If the request is incorrectly formatted, <code>doGet</code>
     * returns an HTTP "Bad Request" message.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends
     *                 to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the GET request
     * @throws ServletException if the request for the GET
     *                          could not be handled
     * @see ServletResponse#setContentType
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService = new DefaultOrderService();
        HttpSession session = request.getSession(false);
        String path = "/";
        User user;
        if (session == null) {
            path = FORGOT;
        } else {
            user = (User) session.getAttribute("user");
            String role = user != null ? user.getRole() : Role.GUEST.role();
            session.setAttribute("user", user);
            if (Objects.equals(role, Role.ADMIN.role())) {
                List<User> users = userService.getAll();
                log.debug(String.format("There are %d users", users.size()));
                request.setAttribute("users", users);
                path = USERS;
            } else if (Objects.equals(role, Role.MANAGER.role())) {
                List<Order> orders = orderService.getAll();
                request.setAttribute("orders", orders);
                path = CONTRACT;
            } else if (Objects.equals(role, Role.USER.role()) && user != null) {
                List<Order> userOrders = orderService.getAll()
                        .stream()
                        .filter(order -> order.getUserId() == (user.getUserId()))
                        .collect(Collectors.toList());
                log.debug("Get userOrders: " + userOrders.size());
                request.setAttribute("orders", userOrders);
                path = ORDERS;
            } else if (Objects.equals(role, Role.GUEST.role()) && user != null) {
                path = FORGOT;
            }
        }
        log.debug(String.format("path: %s", path));
        dispatch(request, response, path);
    }

    /**
     * Authentication and authorization User
     * <p>
     * Overriding this method to support a POST request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a POST request that returns with body in the
     * response, only the request header fields.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends
     *                 to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the POST request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        if (userService.getByName(request.getParameter("name")).isEmpty()) {
            User user = new User.Builder()
                    .userId(UUID.randomUUID().getMostSignificantBits() & 0x7ffffffffffL)
                    .name(request.getParameter("name"))
                    .password(request.getParameter("password"))
                    .passport(request.getParameter("passport"))
                    .phone(request.getParameter("phone"))
                    .language(request.getParameter("language"))
                    .email(request.getParameter("email"))
                    .active(true)
                    .role(request.getParameter("role"))
                    .build();
            boolean save = userService.save(user);

            log.debug(String.format("%s is created: %s", user, save));
            request.setAttribute("user", user);
            dispatch(request, response, MAIN);
        } else {
            log.debug(String.format("%sUser is exists please try again %s", PURPLE, RESET));
            request.setAttribute("errorMessage", "User is exists please try again");
            redirect(request, response, LOGIN);
        }
    }

    /**
     * Send an email if the password has been forgotten
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends
     *                 to the client
     * @see Servlet
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) {

        boolean sendEmail = userService.sendEmail(request.getParameter("username"));
        log.debug(String.format("Letter sent: %b", sendEmail));

        redirect(request, response, FORGOT);
    }
}