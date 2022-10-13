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

        response.setContentType("text/html;charset=UTF-8");

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

        List<Car> cars;

        if (field.equals("") ) {
            cars = carService.getAll(page, recordsPerPage);
        } else {
            cars = carService.getAll(String.format("%s'", field));
//            cars = carService.getAll(field, currentPage, recordsPerPage);
        }
        int rows = cars.size();
        log.info(rows + " total cars");
        request.setAttribute("cars", cars);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/cars.jsp");
        dispatcher.forward(request, response);


        //TODO & params
        //
        //        log.info(currentPage + " records per page");
//        log.info(recordsPerPage + " records per total");

//        int nOfPages = rows / recordsPerPage;
//
//        if (nOfPages % recordsPerPage > 0) {
//
//            nOfPages++;
//        }
//
//        request.setAttribute("noOfPages", nOfPages);
//        request.setAttribute("currentPage", currentPage);
//        request.setAttribute("recordsPerPage", recordsPerPage);

//  cars = carService.getAll(String.format("id BETWEEN 220 AND 228 LIMIT %d OFFSET %d", total, page));
//        int page = 1;
//
//        int total = 12;
//
//        if (request.getParameter("page") != null) {
//            page = Integer.parseInt(request.getParameter("page"));
//        }
//
//
//        request.setAttribute("page", page);
//
//
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
