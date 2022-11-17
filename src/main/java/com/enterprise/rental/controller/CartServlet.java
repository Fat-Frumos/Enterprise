package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
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
 *
 * A subclass of <code>HttpServlet</code> must override two methods:
 * <ul>
 * <li> <code>doGet</code>, if the servlet supports HTTP GET requests
 * <li> <code>doPost</code>, for HTTP POST requests
 * <li> <code>doPut</code>, for HTTP PUT requests
 * <li> <code>doDelete</code>, for HTTP DELETE requests
 * <li> <code>getSessionUser</code>, to get Attribute User from Session</p>
 *</ul>
 *
 * @author Pasha Pollack
 */
@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
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
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the POST
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
//        dispatch(request, response, path);
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
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @return {@code Optional<Car>}, if a value is present,
     * otherwise {@code Optional.empty()}.
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
                    response.sendRedirect("/cart");
                }
            } catch (NumberFormatException e) {
                log.info("Car by id not found");
                request.setAttribute("errorMessage", "Car not found");
                response.sendRedirect(ORDERS);
            }
        }
        dispatch(request, response, "/login");
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
     * @param request   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param response  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @throws IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              DELETE request
     *
     * @throws ServletException  if the request for the
     *                                  DELETE cannot be handled
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
     * Returns {@code Optional<User>} instance.
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


    /**
     * Defines an object that receives requests from the client
     * and sends them to any resource

     * <p>The servlet container creates the <code>RequestDispatcher</code> object,
     * which is used as a wrapper around a server resource located
     * at a particular path or given by a particular name.
     *
     * <p>This interface is intended to wrap servlets,
     * but a servlet container can create <code>RequestDispatcher</code>
     * objects to wrap any type of resource.
     *
     * Includes the content of a resource (servlet, JSP page,
     * HTML file) in the response. In essence, this method enables
     * programmatic server-side includes.
     *
     * <p>The {@link ServletResponse} object has its path elements
     * and parameters remain unchanged from the caller's. The included
     * servlet cannot change the response status code or set headers;
     * any attempt to make a change is ignored.
     *
     * <p>The request and response parameters must be either the same
     * objects as were passed to the calling servlet's service method or be
     * subclasses of the {@link ServletRequestWrapper} or {@link ServletResponseWrapper} classes
     * that wrap them.
     *
     * @param request   the {@link HttpServletRequest} object that
     *                  contains the request the client made of
     *                  the servlet
     *
     * @param response  the {@link HttpServletResponse} object that
     *                  contains the response the servlet returns
     *                  to the client
     *
     * @param path  the String that contains the response the servlet returns
     *                  to the client

     *
     * @throws IOException   if an input or output error occurs
     *                              while the servlet is handling the
     *                              DELETE request
     *
     * @throws ServletException  if the request for the
     *                                  DELETE cannot be handled
     *
     */
    private void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        dispatcher.include(request, response);
    }
}