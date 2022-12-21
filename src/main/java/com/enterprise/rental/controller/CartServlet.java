package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultCarService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.itextpdf.text.BaseColor.ORANGE;

/**
 * Java class that represent a user Cart in HTTP servlet suitable for a Web-site
 * <p>
 * A subclass of <code>Servlet</code> override four methods:
 * <ul>
 * <li> <code>doGet</code>, for HTTP GET requests (get all cars from cart)
 * <li> <code>doPost</code>, for HTTP POST requests (create new order)
 * <li> <code>doPut</code>, if the servlet supports HTTP PUT requests (put car to cart)
 * <li> <code>doDelete</code>, if the servlet supports HTTP DELETE requests (remove car to cart)
 * </ul>
 *
 * @author Pasha Pollack
 */
public class CartServlet extends Servlet {
    private final CarService carService = new DefaultCarService();
    private final UserService userService = new DefaultUserService();
    private static final Logger log = Logger.getLogger(CartServlet.class);

    /**
     * <p>Cars bucket for user: get list and set Attribute User from Session</p>
     * {@code Optional<User>}, if a value is present, otherwise {@code Optional.empty()}.
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, set user carCart in attribute </p>
     * <p>If User not registered redirect to Login page,
     * otherwise redirect to index page </p>
     * Returns {@code Optional<User>} instance.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the GET
     *                          could not be handled
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        Optional<User> optionalUser = getUser(request);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.debug(String.format("Get into session User %s bucket cars: %s",
                    user.getName(), user.getCars().size()));
            List<Car> userCars = user.getCars();
            int size = userCars.size();
            request.setAttribute("car", size);
            request.setAttribute("cars", userCars);
            dispatch(request, response, INDEX);
        } else {
            redirect(request, response, LOGIN);
        }
    }

    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, set rental Car to user Cart
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

        Optional<User> optionalUser = getUser(request);
        String path = LOGIN;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.debug(String.format("Session %s", user));
            if (Objects.equals(user.getRole(), "user")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Optional<Car> optional = carService.getById(id);
                if (optional.isPresent()) {
                    Order order = new Order(id, user.getUserId());
                    log.debug(String.format("%s from User session %s saved: %s",
                            optional.get().getName(), user.getName(), order));
                    Set<Order> orders = user.getOrders();
                    if (orders != null) {
                        orders.add(order);
                        user.setOrders(orders);
                        path = USER;
                    } else {
                        path = MAIN;
                    }
                } else {
                    path = CART;
                }
            }
        }
        redirect(request, response, path);
    }

    /**
     * <p>If the HTTP PUT request is correctly formatted,
     * <code>doPut</code>, set car to User Cart
     * <p>If User not registered redirect to Login page,
     * otherwise redirect to ORDERS page
     * <p>Returns {@code Optional<User>} instance.
     * <p>Put new car to cart for user:
     * get list and set Attribute User from Session
     * {@code Optional<Car>}, if a value is present, otherwise {@code Optional.empty()}.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) {
        String path = LOGIN;
        Optional<User> optionalUser = getUser(request);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.debug(String.format("%sSession %s %s From Put%s",
                    RED, user.getRole(), user.getName(), RESET));

            String carId = request.getParameter("id");
            try {
                long id = Long.parseLong(carId);
                Optional<Car> optionalCar = carService.getById(id);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    user = userService.toCart(car, user);
                    request.setAttribute("auto", user.getCar());
                    request.setAttribute("user", user);
                    log.debug(String.format("Put new Car %s into the Cart: %s",
                            car.getBrand(), user.getCars().size()));
                    path = CART;
                }
            } catch (NumberFormatException e) {
                log.debug(String.format("%sCar by id not found%s", ORANGE, RESET));
                request.setAttribute("errorMessage", "Car not found");
                path = ORDERS;
            } finally {
                redirect(request, response, path);
            }
        }
    }

    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a DELETE request.
     *
     * <p> The DELETE operation allows a client to remove a car from the cart.
     *
     * <p>If the HTTP DELETE request is incorrectly formatted,
     * <code>doDelete</code> returns an HTTP "Bad Request"
     * message.
     *
     * @param request  the {@link HttpServletRequest} object that
     *                 contains the request the client made of
     *                 the servlet
     * @param response the {@link HttpServletResponse} object that
     *                 contains the response the servlet returns
     *                 to the client
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) {
        dispatch(request, response, ORDERS);
    }
}