package com.enterprise.rental.controller;

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

        if (active == null) {
            active = "off";
        }
        user.setActive(active.equals("on"));
        log.info(active);
        log.info(role);
        user.setRole(role);
        User update = userService.edit(user);

        log.info(update);

        String[] fields = {"name", "email", "role", "active"};
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

        Order order = orderMapper(request);

        log.info(order);

        Order update = orderService.updateOrder(order);

        log.info(update);
        request.setAttribute("orders", orderService.getAll());
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
//        request.getRequestDispatcher(CONTRACT)
//                .forward(request, response);
        response.sendRedirect("/user");
    }

    private Order orderMapper(HttpServletRequest request) {
//        String[] fields = {"orderId", "carId", "userId", "passport", "damage"};
        String orderId = request.getParameter("orderId");
        String carId = request.getParameter("carId");
        String userId = request.getParameter("userId");
        String passport = request.getParameter("passport");
        String damage = request.getParameter("damage");

        Double payment = Double.valueOf(request.getParameter("payment"));
        Timestamp term = Timestamp.valueOf(request.getParameter("term"));
        Timestamp created = Timestamp.valueOf(request.getParameter("created"));

        boolean driver = Boolean.parseBoolean(request.getParameter("driver"));

        String rejected = (request.getParameter("rejected"));
        String closed = (request.getParameter("closed"));

        Order order = new Order();
        order.setRejected(rejected.equals("on"));
        order.setClosed(closed.equals("on"));

        order.setCarId(Long.parseLong(carId));
        order.setUserId(Long.parseLong(userId));
        order.setOrderId(Long.parseLong(orderId));
        order.setDriver(driver);
        order.setPassport(passport);
        order.setDamage(damage);
        order.setPayment(payment);
        order.setTerm(term);
        order.setCreated(created);

        log.info(order);

        return order;
    }
}
