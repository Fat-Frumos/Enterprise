package com.enterprise.car.web;

import com.enterprise.car.entity.Car;
import com.enterprise.car.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Car> cars = carService.getAll();
        request.setAttribute("cars", cars);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher("/WEB-INF/views/card.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Car car = getCar(request);

        boolean saved = carService.addCar(car);
//TODO
        log(String.valueOf(saved));

        List<Car> cars = carService.getAll();

        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/views/card.jsp").forward(request, response);
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

        if ("".equals(name) ||"".equals(brand)) {
            request.setAttribute("errorMessage", "Enter a valid car");
        }
        return new Car(id, name, brand, model, path, price, year);
    }
}
