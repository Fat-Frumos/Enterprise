package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@WebServlet(urlPatterns = "/")
public class CarsServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final String[] fields = {"id", "name", "brand", "model", "price", "year"};
    private final String[] params = new String[fields.length];
    private static final Logger log = Logger.getLogger(CarsServlet.class.getName());

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String field = "";

        IntStream.range(0, fields.length).forEachOrdered(i ->
                params[i] = request.getParameter(fields[i]));

        log.info(String.format("Params: %s", params));

        if (request.getParameter(fields[1]) != null) {
            field = (String.format("%s='%s", fields[1],
                    request.getParameter(fields[1])));
        }
        if (request.getParameter(fields[2]) != null) {
            field = (String.format("%s='%s", fields[2],
                    request.getParameter(fields[2])));
        }
        //TODO & params

        List<Car> cars;

        if (field.equals("")) {
            cars = carService.getAll();
        } else {
            cars = carService.getAll(field);
        }

        request.setAttribute("cars", cars);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("/WEB-INF/views/cars.jsp")
                .forward(request, response);
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
