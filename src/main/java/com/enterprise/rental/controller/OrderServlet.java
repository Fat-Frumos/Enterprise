package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(OrderServlet.class);
    private static final CarService carService = new CarService();
    private static final OrderService orderService = new OrderService();
    private static final OrderMapper ORDER_MAPPER = new OrderMapper();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {

        String path;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            path = LOGIN;
        } else {
            User user = (User) session.getAttribute("user");
            String id = request.getParameter("id");
            if (id != null) {
                long carId = Long.parseLong(id);

                Optional<Car> optionalCar = carService.getById(carId);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    log.debug(String.format("Get order Rental Car: %s, User : %s", car.getBrand(), user.getName()));
                    request.setAttribute("auto", car);
                }
                setUserAttribute(request, user, carId);
                path = MAIN;
            } else {
                path = CARS;
            }
        }
        dispatch(request, response, path);
    }

    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, created user order and set rental Car
     * If User not registered redirect to Login page, otherwise redirect to Main page </p>
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        Optional<User> optional = getUser(request.getSession());

        if (optional.isPresent()) {
            User user = optional.get();
            log.debug(String.format("User %s ", user.getName()));
            Car car = user.getCar();
            if (car != null) {
                log.debug(String.format("Car %s ", car.getBrand()));
                Order orderMapper = ORDER_MAPPER.orderMapper(request);
                if (orderMapper != null) {
                    boolean saved = orderService.save(orderMapper);
                    if (saved) {
                        List<Car> userCars = user.getCars();
                        for (Car userCar : userCars) {
                            if (userCar.getId() == car.getId()) {
                                userCars.remove(userCar);
                                user.setCars(userCars);
                            }
                        }
                        List<Order> orderList = orderService.getAll(user);
                        request.setAttribute("order", orderList);
                        user.setCar(new Car());
                    }
                }
            }
        }
        response.sendRedirect("/user");
    }

    /**
     * role manager, create the invoice
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String carId = request.getParameter("carId");
        String damage = (request.getParameter("damage"));
        String payment = (request.getParameter("payment"));
        String reason = (request.getParameter("reason"));
        String passport = (request.getParameter("passport"));
        String phone = (request.getParameter("phone"));
        long uid = Long.parseLong(userId);
        long cid = Long.parseLong(carId);
        double pay = Double.parseDouble(payment);
//        Optional<User> optionalUser = userService.getById(uid);
//        log.debug(String.format("Email: %s", optionalUser.get().getEmail()));
//
        Invoice invoice = new Invoice(uid, cid, damage, passport, phone, reason, "bob@i.ua", pay);
        log.debug(String.format("Invoice: %s", invoice));
        boolean created = orderService.createInvoice(invoice);
        log.debug(String.format("create Invoice: %s", created));
        response.sendRedirect("/user");

    }

    /**
     * role manager, remove the order
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = (request.getParameter("orderId"));
        log.debug(String.format("Delete order %s", id));

        Optional<User> user = getUser(request.getSession(false));
        if ("manager".equals(user.get().getRole()) && user.isPresent() && id != null) {
            boolean delete = orderService.delete(Long.parseLong(id));
            log.debug(String.format("Order# %s, deleted %b", id, delete));
            List<Order> userOrders = orderService.getAll();
            request.setAttribute("orders", userOrders);
            request.setAttribute("user", user);
            log.debug(String.format("%s", user));
        }
        response.sendRedirect("/user");
    }

    private static void setUserAttribute(
            HttpServletRequest request,
            User user, long carId) {

        List<Car> userCars = user.getCars();

        for (Car car : userCars) {
            if (car.getId() == carId) {
                List<Car> cars = new ArrayList<>();
                userCars.stream()
                        .filter(auto -> cars.size() < 3)
                        .forEachOrdered(cars::add);
                user.setCar(car);
                request.setAttribute("cars", cars);
                break;
            }
        }
        request.setAttribute("car", userCars.size());
        request.setAttribute("user", user);
    }

    private static Optional<User> getUser(HttpSession session) {

        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            log.debug(String.format("%s %s From Request session",
                    user.getRole(), user.getName()));
            return Optional.of(user);
        }
        return Optional.empty();
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