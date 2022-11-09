package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CarsServlet.class);
    private static final CarMapper CAR_MAPPER = new CarMapper();
    private static final CarService carService = new CarService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");

        int size = carService.getAll().size();
        String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page"};

        Map<String, String> params = Arrays.stream(carFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(
                        key -> key,
                        request::getParameter,
                        (a, b) -> b));

        int page;

        try {
            page = Integer.parseInt(params.get("page"));
            if (page <= 1) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        int limit;

        String offset = "limit";
        try {
            limit = Integer.parseInt(params.get(offset));
        } catch (NumberFormatException e) {
            limit = 10;
        }

        params.put(offset, String.valueOf(limit));
        params.put("page", String.valueOf(page));

        log.info(String.format("Quest Params: %s, page %s", params, page));

        if (Integer.parseInt(params.get(offset)) == 10
                && Integer.parseInt(params.get("page")) <= 1
                && params.size() == 2) {
            request.setAttribute("cars", carList);
        } else {
            if (page * limit > size) {
                page--;
            }
            List<Car> auto = params.keySet()
                    .stream()
                    .map(key -> String.format("&%s=%s", key, params.get(key)))
                    .collect(Collectors.joining())
                    .equals("")
                    ? carService.getAll(params)
                    : carService.getAll(params, page);
            if (auto.isEmpty()) {
                page--;
            } else {
                request.setAttribute("cars", auto);
            }
        }

        request.setAttribute("page", page);
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            user.addParams(params);
            log.info(String.format("Users Cars: %s", user.getCars()));
            request.setAttribute("car", user.getCars().size());
            log.info(String.format("Users Params: %s", user.getParams()));
        }
        dispatch(request, response, CARS);
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
                    log.info(String.format("%s %b", id, delete));
                    List<Car> userCars = user.getCars();
                    userCars.remove(optionalCar.get());
                    user.setCars(userCars);
                    log.info(String.format("%s", user));
                    request.setAttribute("user", user);
                    request.setAttribute("cars", user.getCars());
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