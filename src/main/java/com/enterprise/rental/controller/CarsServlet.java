package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CarsServlet.class);
    private static final CarMapper CAR_MAPPER = new CarMapper();
    private static final CarService carService = new CarService();
    private final List<Car> cars = carService.getAll("id BETWEEN 219 AND 235");


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
            if (Objects.equals(user.getRole(), "admin")) {
                Car carRow = CAR_MAPPER.carMapper(request);
                String id = (request.getParameter("id"));

                Optional<Car> optionalCar = carService.getById(Long.parseLong(id));
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    if (car.getId() == carRow.getId()) {
                        Car update = carService.edit(carRow);
                        log.info(String.format("Updated: %s", update));
                        update.setPath(car.getPath());
                        request.setAttribute("auto", update);
                    }
                    log.info(String.format("Car#%s mapRow: %s%n %s", id, carRow, optionalCar));
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
            throws ServletException, IOException {

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
            HttpServletResponse response) throws ServletException, IOException {
        String id = (request.getParameter("id"));
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("admin")) {
                long carId = Long.parseLong(id);
                Optional<Car> optionalCar = carService.getById(carId);
                if (optionalCar.isPresent()) {
                    boolean delete = carService.delete(carId);
                    List<Car> userCars = user.getCars();
                    //TODO DELETE vs UPDATE
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