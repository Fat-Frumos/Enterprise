package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.service.CarService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(urlPatterns = "/card")
public class CardServlet extends HttpServlet {
    private Set<Car> cars = new HashSet<>();
    private final CarService carService = new CarService();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

//        try {
//            long id = Long.parseLong(request.getParameter("id"));
//            Car car = carService.getById(id);
//            cars.add(car);
//        } catch (Exception e) {
//            cars = carService.getAll();
//        }
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);

        boolean isAuth = false;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("user-token")) {
//                    log.info("Auth token user");
//                    isAuth = true;
//                }
//            }
//        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            Car car = carService.getById(id);
            cars.add(car);
        } catch (Exception e) {
            cars = carService.getAll();
        }
        request.setAttribute("cars", cars);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }
}
