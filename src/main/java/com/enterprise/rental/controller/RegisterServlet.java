package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
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

public class RegisterServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(RegisterServlet.class);

    /***
     * Update date User
     * Role: admin
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UserService userService = new UserService();

        String name = request.getParameter("name");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String role = request.getParameter("role");
            String access = request.getParameter("active");
            String close = request.getParameter("closed");

            String active = access != null ? access : "off";
            String closed = close != null ? close : "off";

            user.setActive(active.equals("on"));
            user.setClosed(closed.equals("on"));

            log.info(String.format(" active: %s lock: %s, role: %s", active, closed, role));

            user.setRole(role);
            User update = userService.edit(user);

            log.info(update);
            String[] fields = {"name", "email", "role", "active", "closed"};
            Map<String, String> params = Arrays.stream(fields)
                    .filter(key -> !"".equals(request.getParameter(key))
                            && request.getParameter(key) != null)
                    .collect(Collectors.toMap(
                            key -> key,
                            request::getParameter,
                            (a, b) -> b));

            List<User> users = userService.getAll(params);
            log.info(users.size() + " users");
            request.setAttribute("users", users);
        } else {
            log.info("Could not find user");
        }
        response.sendRedirect("/user");

    }

    /***
     * Update date Order
     * Role: manager
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderService orderService = new OrderService();

        OrderMapper mapper = new OrderMapper();

        Order order = mapper.orderMapper(request);

        Order update = orderService.updateOrder(order);

        log.info(update);

        List<Order> orders = orderService.getAll();

        request.setAttribute("orders", orders);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher(CONTRACT)
                .forward(request, response);
    }
}
