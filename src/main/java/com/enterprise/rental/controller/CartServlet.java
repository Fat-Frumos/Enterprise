package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultCarService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.enterprise.rental.controller.Parameter.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.entity.Role.USER;

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
 * @author Pasha Polyak
 */
public class CartServlet extends Servlet {
    private final CarService carService = new DefaultCarService();
    private final UserService userService = new DefaultUserService();
    private static final Logger LOGGER = LogManager.getLogger();

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
        String path = LOGIN_JSP;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LOGGER.log( Level.INFO, "Get into session User {} bucket cars: {}",
                    user.getName(), user.getCars().size());
            List<Car> userCars = user.getCars();
            int size = userCars.size();
            if (size == 0) {
                try {
                    userCars = carService.findAllBy("id BETWEEN 219 AND 235");
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                }
            }
            request.setAttribute(CAR.value(), size);
            request.setAttribute(CARS.value(), userCars);
            path = INDEX_JSP;
        }
        dispatch(request, response, path);
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
        String path = LOGIN_JSP;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LOGGER.log( Level.INFO, "Session {}", user);
            if (Objects.equals(user.getRole(), USER.role())) {
                long id = Long.parseLong(request.getParameter("id"));
                Optional<Car> optional = carService.findBy(id);
                if (optional.isPresent()) {
                    Order order = new Order(id, user.getUserId());
                    LOGGER.log( Level.INFO, "{} from User session {} saved: {}",
                            optional.get().getName(), user.getName(), order);
                    Set<Order> orders = user.getOrders();
                    if (orders != null) {
                        orders.add(order);
                        user.setOrders(orders);
                        path = USER_URL;
                    } else {
                        path = MAIN_JSP;
                    }
                } else {
                    path = CART_URL;
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
        String path = LOGIN_JSP;
        Optional<User> optionalUser = getUser(request);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            LOGGER.log( Level.INFO, "{}Session {} {} From Put{}",
                    RED, user.getRole(), user.getName(), RESET);

            String carId = request.getParameter("id");
            try {
                Long id = Long.parseLong(carId);
                Optional<Car> optionalCar = carService.findBy(id);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    user = userService.toCart(car, user);
                    request.setAttribute(AUTO.value(), user.getCar());
                    request.setAttribute(CLIENT.value(), user);
                    LOGGER.log( Level.INFO, "Put new Car {} into the Cart: {}",
                            car.getBrand(), user.getCars().size());
                    path = CART_URL;
                }
            } catch (NumberFormatException e) {
                LOGGER.log( Level.INFO, "{}Car by id not found{}", PURPLE, RESET);
                request.setAttribute(ERROR.value(), "Car not found");
                path = ORDER_JSP;
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
        dispatch(request, response, ORDER_JSP);
    }
}