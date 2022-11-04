package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.CarMapper;
import com.enterprise.rental.dao.mapper.UserMapper;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.CARS;
import static com.enterprise.rental.dao.jdbc.Constants.MAIN;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CarsServlet.class);
    private static final CarMapper CAR_MAPPER = new CarMapper();
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        CarService carService = new CarService();

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

        Car mapRow = CAR_MAPPER.carMapper(request);

        CarService carService = new CarService();

        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("user");

        String id = (request.getParameter("id"));
        Car car = null;
        if (id != null) {
            car = carService.getById(Long.parseLong(id));
        }


        log.info(String.format("Car#%s%n mapRow: %s%n car: %s", id, mapRow, car));

        //        Map<String, String> params = Arrays.stream(carFields)
//                .filter(key -> !"".equals(request.getParameter(key))
//                        && request.getParameter(key) != null)
//                .collect(Collectors.toMap(
//                        key -> key,
//                        request::getParameter,
//                        (a, b) -> b));


//        request.setAttribute("auto", user.getCars().get(0));
        request.setAttribute("auto", car);
        dispatch(request, response, MAIN);
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