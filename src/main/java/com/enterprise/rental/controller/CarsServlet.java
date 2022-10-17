package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.service.CarService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final String[] fields = {"id", "name", "brand", "model", "path", "price", "year", "sort", "direction", "page"};
    private static final Logger log = Logger.getLogger(CarsServlet.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        Map<String, String> params = Arrays.stream(fields)
                .filter(key -> !"".equals(request.getParameter(key)) && request.getParameter(key) != null)
                .collect(Collectors.toMap(key -> key, request::getParameter, (a, b) -> b));

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

        try {
            limit = Integer.parseInt(params.get("limit"));
        } catch (NumberFormatException e) {
            limit = 10;
        }

        params.put("limit", String.valueOf(limit));


        log.info(String.format("Params: %s", params));

        String field = params.keySet()
                .stream()
                .map(key -> String.format("&%s=%s", key, params.get(key)))
                .collect(Collectors.joining());

        List<Car> auto = field.equals("")
                ? carService.getAll(params)
                : carService.getAll(params, page);

        response.setContentType("text/html;charset=UTF-8");

        request.setAttribute("page", page);
        request.setAttribute("cars", auto);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        Car car = getCar(request);

        boolean saved = carService.save(car);

        log.info(String.format("saved %s %s", car.toString(), saved));

        List<Car> cars = carService.getAll();

        request.setAttribute("cars", cars);

        request.getRequestDispatcher("/WEB-INF/views/cars.jsp")
                .forward(request, response);
    }

    private Car getCar(HttpServletRequest request) {

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            id = UUID.randomUUID().version();
        }

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String path = request.getParameter("path");
        Double price = Double.parseDouble(request.getParameter("price"));
        int year = Integer.parseInt(request.getParameter("year"));
        LocalDateTime time = LocalDateTime.now();

        if ("".equals(name) || "".equals(brand)) {
            request.setAttribute("errorMessage", "Enter a valid car");
        }

        return new Car.Builder()
                .id(id)
                .name(name)
                .brand(brand)
                .model(model)
                .path(path)
                .price(price)
                .year(year)
                .created(time)
                .build();
    }
}
