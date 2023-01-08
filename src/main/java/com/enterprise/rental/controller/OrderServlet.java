package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.impl.DefaultCarService;
import com.enterprise.rental.service.impl.DefaultOrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.enterprise.rental.controller.Parameter.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.entity.Role.MANAGER;

/**
 * <p>Order Servlet extends an abstract Servlet suitable for a Web-site.
 * <p>A subclass of <code>HttpServlet</code> override four methods:
 * <code>doGet</code>, for HTTP GET requests
 * <code>doPost</code>, for HTTP POST requests
 * <code>doPut</code>, for HTTP PUT requests
 * <code>doDelete</code>, for HTTP DELETE requests
 * <code>setUserAttribute</code>, setUserAttribute
 *
 * @author Pasha Pollack
 */

public class OrderServlet extends Servlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final CarService carService = new DefaultCarService();
    private static final OrderService orderService = new DefaultOrderService();
    private static final OrderMapper ORDER_MAPPER = new OrderMapper();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {

        String path;
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(CLIENT.value()) == null) {
            path = LOGIN_JSP;
        } else {
            User user = (User) session.getAttribute(CLIENT.value());
            String id = request.getParameter("id");
            if (id != null) {
                long carId = Long.parseLong(id);

                Optional<Car> optionalCar = carService.findBy(carId);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    LOGGER.log(Level.INFO, "Get order Rental Car: {}, User : {}", car.getBrand(), user.getName());
                    request.setAttribute("auto", car);
                }
                setUserAttribute(request, user, carId);
                path = MAIN_JSP;
            } else {
                path = CARS_JSP;
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
            LOGGER.log(Level.INFO, "{} {} From Request session", user.getRole(), user.getName());
            Car car = user.getCar();
            if (car != null) {
                LOGGER.log(Level.INFO, "Car {}", car.getBrand());

                Order mapper = ORDER_MAPPER.mapper(request);

                if (mapper != null) {
                    boolean saved = false;
                    try {
                        saved = orderService.save(mapper);
                    } catch (ServiceException e) {
                        LOGGER.log(Level.ERROR, "Car {} ", car.getBrand());
                    }
                    if (saved) {
                        List<Car> userCars = user.getCars();
                        for (Car userCar : userCars) {
                            if (userCar.getId() == car.getId()) {
                                userCars.remove(userCar);
                                user.setCars(userCars);
                            }
                        }
                        List<Order> orderList = new ArrayList<>();
                        try {
                            orderList = orderService.getAll(user);
                        } catch (ServiceException e) {
                            LOGGER.log(Level.ERROR, "{}", e.getMessage());
                        }
                        request.setAttribute("order", orderList);
                        user.setCar(new Car());
                    }
                }
            }
        }
        redirect(request, response, USER_URL);
    }

    /**
     * role manager, create the invoice
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
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
        Invoice invoice = new Invoice(uid, cid, damage, passport, phone, reason, "bob@i.ua", pay);
        LOGGER.log(Level.INFO, "Invoice: {}", invoice);
        boolean created = orderService.save(invoice);
        LOGGER.log(Level.INFO, "create Invoice: {}", created);
        redirect(request, response, USER_URL);
    }

    /**
     * role manager, remove the order
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) {

        String id = (request.getParameter("orderId"));
        LOGGER.log(Level.INFO, "Delete order {}", id);

        Optional<User> optionalUser = getUser(request.getSession(false));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (MANAGER.role().equals(user.getRole()) && id != null) {

                try {
                    boolean delete = orderService.delete(Long.parseLong(id));
                    LOGGER.log(Level.INFO, "Order# {}, deleted {}", id, delete);
                    List<Order> userOrders = orderService.findAllBy();
                    request.setAttribute(ORDER.value(), userOrders);
                    request.setAttribute(CLIENT.value(), user);
                    LOGGER.log(Level.INFO, "{}{}{}, {} {} From Request session",
                            GREEN, user, RESET, user.getRole(), user.getName());
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                }
            }
        }
        redirect(request, response, USER_URL);
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
                request.setAttribute(CARS.value(), cars);
                break;
            }
        }
        request.setAttribute(CAR.value(), userCars.size());
        request.setAttribute(CLIENT.value(), user);
    }
}
