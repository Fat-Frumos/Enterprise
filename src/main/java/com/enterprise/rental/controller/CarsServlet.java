package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.impl.DefaultCarService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static com.enterprise.rental.controller.Parameter.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * <p>CarsServlet extends an abstract Servlet suitable for a Web-site.
 * <p>A subclass of <code>HttpServlet</code> override four methods:
 * <ul>
 * <li> <code>doGet</code>, for HTTP GET requests (get cars)
 * <li> <code>doPost</code>, for HTTP POST requests (edit cars)
 * <li> <code>doPut</code>, if the servlet supports HTTP PUT requests (add cars)
 * <li> <code>doDelete</code>, if the servlet supports HTTP DELETE requests (remove cars)
 * </ul>
 *
 * @author Pasha Polyak
 */
public class CarsServlet extends Servlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CarMapper CAR_MAPPER = new CarMapper();
    private static final CarService carService = new DefaultCarService();
    private static List<Car> cars = new ArrayList<>();

    public CarsServlet() {
        try {
            cars = carService.findAllBy("id BETWEEN 219 AND 235");
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    /**
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, fetches cars from the database for index page</p>
     * Cars list for index page: get list
     * A Page Request object by passing in the requested page number and the page limit.
     * {@code Map<String, String> params} is a map of parameters to the sorting and pagination
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     *                 {@code List<Cars>}, if a value is present, otherwise {@code empty List<Cars>}.
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

        Collections.shuffle(cars);
        request.setAttribute(CARS.value(), cars);
        dispatch(request, response, INDEX_JSP);
    }

    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, admin updates vehicle date to database
     * <p>If User not registered redirect to Login page, otherwise redirect to Main page
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

        HttpSession session = request.getSession(false);

        Optional<User> optionalUser = getUser(session);

        if (optionalUser.isPresent()
                && Objects.equals(optionalUser.get().getRole(), Role.ADMIN.role())) {

            Optional<Car> optionalCar = carService.findBy(request.getParameter("id") != null
                    ? Long.parseLong(request.getParameter("id"))
                    : 0);

            Car requestCar = CAR_MAPPER.mapper(request);
            if (optionalCar.isPresent() && optionalCar.get().getId() == requestCar.getId()) {

                try {
                    Car update = carService.edit(requestCar);
                    LOGGER.log(Level.INFO, "Updated: {}", update);
                    update.setPath(optionalCar.get().getPath());
                    request.setAttribute(AUTO.value(), update);
                    LOGGER.log(Level.INFO, "{}{}{}", GREEN, optionalCar, RESET);
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, "{}", e.getMessage());
                }

            }
            session.setAttribute(CLIENT.value(), optionalUser.get());
        }

        dispatch(request, response, MAIN_JSP);

    }

    /**
     * <p>If the HTTP PUT request is correctly formatted,
     * <code>doPut</code>, admin saves vehicle data to database
     * If User not registered redirect to Login page, otherwise redirect to Main page
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

        Optional<User> user = getUser(request.getSession(false));
        //check admin role and car before create
        if (user.isPresent() && Objects.equals(user.get().getRole(), Role.ADMIN.role())) {
            Car carRow = CAR_MAPPER.mapper(request);
            boolean save = false;
            try {
                save = carService.save(carRow);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
            LOGGER.log(Level.INFO, "{}{}%nCreated: {}", user.get().getRole(), carRow, save);
            request.setAttribute(AUTO.value(), carRow);
            request.setAttribute(CARS.value(), user.get().getCars());
            path = INDEX_JSP;
        }
        dispatch(request, response, path);
    }

    /**
     * <p>If the HTTP DELETE request is correctly formatted,
     * <code>doDelete</code>, admin removes vehicle date from database
     * If User not registered redirect to Login page, otherwise redirect to Main page
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     */
    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)  {

        String path = CAR_URL;
        Optional<User> optionalUser = getUser(request.getSession(false));
        //check admin role and car before remove
        if (optionalUser.isPresent() && Objects.equals(optionalUser.get().getRole(), Role.ADMIN.role())) {
            User user = optionalUser.get();
            long carId = (request.getParameter("id")) != null
                    ? Long.parseLong((request.getParameter("id")))
                    : 0;
            Optional<Car> optionalCar = carService.findBy(carId);
            if (optionalCar.isPresent()) {

                try {
                    boolean delete = carService.delete(carId);
                    LOGGER.log(Level.INFO, "Car was removed {}", delete);
                } catch (ServiceException e) {
                    LOGGER.log(Level.ERROR, e.getMessage());
                    path = ERROR_404_JSP;
                }

                List<Car> userCars = user.getCars();
                userCars.remove(optionalCar.get());
                user.setCars(userCars);
                request.setAttribute(CLIENT.value(), user);
                request.setAttribute(CARS.value(), user.getCars());
            }
        }
        redirect(request, response, path);
    }
}