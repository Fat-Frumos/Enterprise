package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.service.CarService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final String[] fields = {"id", "name", "brand", "model", "price", "year", "sort", "direction"};
    private static final Logger log = Logger.getLogger(CarsServlet.class.getName());

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        Map<String, String> params = new HashMap<>();

        for (String field : fields) {
            try {
                String parameter = request.getParameter(field);
                if (!parameter.equals("")) {
                    params.put(field, parameter);
                }
            } catch (Exception e) {
                // INFO: Params: null
            }
        }

        log.info(String.format("%s", params));

        int page;
        int recordsPerPage;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 0;
        }

        try {
            recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
        } catch (NumberFormatException e) {
            recordsPerPage = 10;
        }

        String field = params.keySet()
                .stream()
                .map(key -> String.format("&%s=%s", key, params.get(key)))
                .collect(Collectors.joining());

        List<Car> auto = field.equals("")
                ? carService.getAll(page, recordsPerPage)
                : carService.getAll(params, page, recordsPerPage);

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

        boolean saved = carService.addCar(car);

        log.info(String.format("%s%s", saved, car.toString()));

        List<Car> cars = carService.getAll();

        request.setAttribute("cars", cars);

        request.getRequestDispatcher("/WEB-INF/views/cars.jsp")
                .forward(request, response);
        //TODO boolean saved
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
