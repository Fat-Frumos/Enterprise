package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Role;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * CarsServlet extends an abstract Servlet suitable for a Web-site.
 * A subclass of <code>HttpServlet</code> must override four methods:
 * <ul>
 * <li> <code>doGet</code>, for HTTP GET requests
 * <li> <code>doPost</code>, for HTTP POST requests
 * <li> <code>doPut</code>, if the servlet supports HTTP PUT requests
 * <li> <code>doDelete</code>, if the servlet supports HTTP DELETE requests
 * </ul>
 *
 */
public class CarsServlet extends Servlet {
    private static final Logger log = Logger.getLogger(CarsServlet.class);
    private static final CarMapper CAR_MAPPER = new CarMapper();
    private static final CarService carService = new CarService();
    private final List<Car> cars = carService.getAll("id BETWEEN 219 AND 235");

    /**
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, fetches cars from the database for index page</p>
     *
     * <p>Cars list for index page: get list</p>
     * A Page Request object by passing in the requested page number and the page limit.
     * {@code Map<String, String> params} is a map of parameters to the sorting and paging
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
        request.setAttribute("cars", cars);
        dispatch(request, response, INDEX);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (Objects.equals(user.getRole(), Role.ADMIN.role())) {
                Car carRow = CAR_MAPPER.carMapper(request);
                String parameterId = request.getParameter("id");

                long id = parameterId != null ? Long.parseLong(parameterId) : 0;

                Optional<Car> optionalCar = carService.getById(id);

                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    if (car.getId() == carRow.getId()) {
                        Car update = carService.edit(carRow);
                        log.info(String.format("Updated: %s", update));
                        update.setPath(car.getPath());
                        request.setAttribute("auto", update);
                    }
                    log.info(String.format("Car#%d mapRow: %s%n %s", id, carRow, optionalCar));
                }
            }
            session.setAttribute("user", user);
        }
        dispatch(request, response, MAIN);
    }

    /***
     * Save new car in DB
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {

        String path;
        User user = getUser(request.getSession(false));

        if (user == null || !Objects.equals(user.getRole(), "admin")) {
            path = LOGIN;
        } else {
            Car carRow = CAR_MAPPER.carMapper(request);
            boolean save = carService.save(carRow);

            log.info(String.format("%s%s%nCreated: %s", user.getRole(), carRow, save));

            request.setAttribute("auto", carRow);
            request.setAttribute("cars", user.getCars());
            path = INDEX;
        }
        response.sendRedirect(path);
    }

    private User getUser(HttpSession session) {
        return session
                != null && session.getAttribute("user")
                != null ? (User) session.getAttribute("user")
                : null;
    }

    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String id = (request.getParameter("id"));
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (Objects.equals(user.getRole(), Role.ADMIN.role())) {
                long carId = Long.parseLong(id);
                Optional<Car> optionalCar = carService.getById(carId);
                if (optionalCar.isPresent()) {
                    boolean delete = carService.delete(carId);
                    List<Car> userCars = user.getCars();
                    userCars.remove(optionalCar.get());
                    user.setCars(userCars);
                    request.setAttribute("user", user);
                    request.setAttribute("cars", user.getCars());
                    log.info(String.format("%s %b", id, delete));
                    log.info(String.format("%s", user));
                    log.info(String.format("%s", user.getCars()));
                }
            }
        }
        response.sendRedirect("/cars");
    }
}