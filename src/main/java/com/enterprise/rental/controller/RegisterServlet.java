package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultOrderService;
import com.enterprise.rental.service.impl.DefaultUserService;
import com.enterprise.rental.service.locale.CurrencyConvector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.CONTRACT;
import static com.enterprise.rental.dao.jdbc.Constants.USER;

/**
 * Java class that represent a Register service for update operations
 * Register Servlet extends abstract class Servlet
 *
 * @author Pasha Pollack
 */
public class RegisterServlet extends Servlet {
    private OrderMapper mapper;
    private OrderService orderService;
    private UserService userService;
    private static final Log log = LogFactory.getLog(RegisterServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
        mapper = new OrderMapper();
        orderService = new DefaultOrderService();
        userService = new DefaultUserService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * Updates a user by setting their name and active.
     * Username and password is not able to be updated.
     * <p>
     * Called by the server (via the <code>service</code> method) to
     * allow a servlet to handle a GET request.
     *
     * <p>Overriding this method to support a GET request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a GET request that returns with body in the
     * response, only the request header fields.
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        Optional<User> optionalUser = userService.getByName(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String role = request.getParameter("role");
            String access = request.getParameter("active");
            String close = request.getParameter("closed");
            String active = access != null ? access : "off";
            String closed = close != null ? close : "off";

            user.setActive(active.equals("on"));
            user.setClosed(closed.equals("on"));

            log.debug(String.format(" active: %s lock: %s, role: %s", active, closed, role));

            user.setRole(role);
            userService.edit(user);

            String[] fields = {"name", "email", "role", "active", "closed"};
            Map<String, String> params = Arrays.stream(fields)
                    .filter(key -> !"".equals(request.getParameter(key))
                            && request.getParameter(key) != null)
                    .collect(Collectors.toMap(
                            key -> key,
                            request::getParameter,
                            (a, b) -> b));

            request.setAttribute("users", userService.getAll(params));
        } else {
            log.debug("Could not find user");
        }
        redirect(request, response, USER);
    }

    /***
     * Update date Order
     * <p>
     * Overriding this method to support a POST request also
     * automatically supports an HTTP HEAD request. A HEAD
     * request is a POST request that returns with body in the
     * response, only the request header fields.
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Order order = mapper.mapper(request);

        Order update = orderService.edit(order);

        log.debug(String.format("Order #%d", update.getOrderId()));

        List<Order> orders = orderService.getAll();

        request.setAttribute("orders", orders);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        dispatch(request, response, CONTRACT);
    }
}
