package com.enterprise.car.web;

import com.enterprise.car.entity.Car;
import com.enterprise.car.service.CarService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(urlPatterns = "/")
public class CarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        List<Car> cars = carService.getAll();
        log.info(cars.toString());

        request.setAttribute("cars", cars);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        request.getRequestDispatcher("/WEB-INF/views/card.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String path = request.getParameter("path");

        //TODO
//       int year = Integer.parseInt(request.getParameter("year"));
//       Double price = Double.parseDouble(request.getParameter("price"));


        if ("".equals(name)) {
            request.setAttribute("errorMessage", "Enter a valid car");
        }

        Car car = Car.builder()
                .name(name)
                .brand(brand)
                .model(model)
//                    .year(year)
//                    .price(price)
                .path(path)
                .build();

        log.info(car.toString());

        carService.addCar(car);
        List<Car> cars = carService.getAll();
        log.info("new Car: {}", car);
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/views/card.jsp").forward(request, response);
    }
}
