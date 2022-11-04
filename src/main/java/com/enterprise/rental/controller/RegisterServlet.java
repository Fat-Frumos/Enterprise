package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.UserNotFoundException;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/register")
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
        User user = userService.findByName(name)
                .orElseThrow(() -> new UserNotFoundException("Could not find user"));

        String role = request.getParameter("role");
        String active = request.getParameter("active");
        String closed = request.getParameter("closed");

        if (active == null) {
            active = "off";
        }
        if (closed == null) {
            closed = "off";
        }
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
        response.sendRedirect("/user");
//        request.setAttribute("users", users);
//        request.getRequestDispatcher("/user")
//                .forward(request, response);
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

        log.info(order);

        Order update = orderService.updateOrder(order);
        log.info(update);
//        List<Order> orders = orderService.getAllByUser(params);
        request.setAttribute("orders", orderService.getAll());
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
//        request.getRequestDispatcher(CONTRACT)
//                .forward(request, response);
        response.sendRedirect("/user");
    }
}
