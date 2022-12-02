package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * Cart Servlet extends an HTTP servlet suitable for a Web-site.
 * A subclass of <code>HttpServlet</code> must override four methods:
 * <ul>
 * <li> <code>doGet</code>, for HTTP GET requests
 * <li> <code>doPost</code>, for HTTP POST requests
 * <li> <code>doPut</code>, if the servlet supports HTTP PUT requests
 * <li> <code>doDelete</code>, if the servlet supports HTTP DELETE requests
 *
 * <li> <code>getSessionUser</code>, to get Attribute User from Session
 * </ul>
 *
 * @author Pasha Pollack
 */
@WebServlet(urlPatterns = "/cart")
public class CartServlet extends Servlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(CartServlet.class);

    /**
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, set user carCart in attribute </p>
     * <p>If User not registered redirect to Login page,
     * otherwise redirect to index page </p>
     * Returns {@code Optional<User>} instance.
     *
     * <p>Cars bucket for user: get list and set Attribute User from Session</p>
     * {@code Optional<User>}, if a value is present, otherwise {@code Optional.empty()}.
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

        Optional<User> optionalUser = getSessionUser(request);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info(String.format("Get into session User %s bucket cars: %s",
                    user.getName(), user.getCars().size()));
            List<Car> userCars = user.getCars();
            int size = userCars.size();
            request.setAttribute("car", size);
            request.setAttribute("cars", userCars);
            dispatch(request, response, INDEX);
        } else {
            response.sendRedirect(LOGIN);
        }
    }

    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, user car card and set rental Car
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

        Optional<User> optionalUser = getSessionUser(request);
        String path = LOGIN;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info(String.format("Post from session %s", user));
            if (Objects.equals(user.getRole(), "user")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Optional<Car> optional = carService.getById(id);
                if (optional.isPresent()) {
                    Car car = optional.get();
                    log.info(car);
                    log.info(String.format("Main post car %d from User session %s", id, user.getName()));
                    Order order = new Order(id, user.getUserId(), false);
                    log.info(String.format("saved %s", order));
                    Set<Order> orders = user.getOrders();
                    if (orders != null) {
                        orders.add(order);
                        user.setOrders(orders);
                        path = "/user";
                    } else {
                        path = MAIN;
                    }
                } else {
                    path = "/cart";
                }
            }
        }
        response.sendRedirect(path);
    }

    /**
     * <p>If the HTTP PUT request is correctly formatted,
     * <code>doPut</code>, set car to CartUser
     * If User not registered redirect to Login page,
     * otherwise redirect to ORDERS page </p>
     * <p>
     * Returns {@code Optional<User>} instance.
     *
     * <p>Put new car to bucket for user:
     * get list and set Attribute User from Session</p>
     * {@code Optional<Car>}, if a value is present, otherwise {@code Optional.empty()}.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the PUT
     *                          could not be handled
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException {
        String path = LOGIN;
        Optional<User> optionalUser = getSessionUser(request);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info(String.format("Session %s %s From Put ",
                    user.getRole(), user.getName()));

            String carId = request.getParameter("id");
            try {
                long id = Long.parseLong(carId);
                Optional<Car> optionalCar = carService.getById(id);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    user = userService.bookCar(car, user);
                    request.setAttribute("auto", user.getCar());
                    request.setAttribute("user", user);
                    log.info(String.format("Put new Car %s into the Cart: %s",
                            car.getBrand(), user.getCars().size()));
                    path = "/cart";
//                    response.sendRedirect("/cart");

                }
            } catch (NumberFormatException e) {
                log.info("Car by id not found");
                request.setAttribute("errorMessage", "Car not found");
                path = ORDERS;
//                response.sendRedirect(ORDERS);
            } finally {
                response.sendRedirect(path);
            }
        }
//        dispatch(request, response, "/login");
    }

    /**
     * Called by the server (via the <code>service</code> method)
     * to allow a servlet to handle a DELETE request.
     *
     * <p> The DELETE operation allows a client to remove a car from the card.
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
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          DELETE request
     * @throws ServletException if the request for the
     *                          DELETE cannot be handled
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //        dispatch(request, response, ORDERS);
//    private static String getPath(List<Car> userCars) {
//        return userCars.isEmpty() ? CARS : checkCard(userCars);
//        user.getCars().remove(id);
//    }
    }

    /**
     * Get User instance {@code Optional<User>} from session
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     *
     *                <p>Get Attribute User from Session</p>
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     */
    private Optional<User> getSessionUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null
                && session.getAttribute("user") != null
                ? Optional.of((User) session.getAttribute("user"))
                : Optional.empty();
    }
}