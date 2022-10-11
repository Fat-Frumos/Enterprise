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
import java.util.List;

@WebServlet(urlPatterns = "/card")
public class CardServlet extends HttpServlet {

    private static final long serialVersionUID = 123L;
    private final CarService carService;

    public CardServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        List<Car> cars = carService.getRandom();

        request.setAttribute("cars", cars);

        request.getRequestDispatcher("/WEB-INF/views/main.jsp")
                .forward(request, response);

        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    isAuth = true;
                }
            }
        }
        if (isAuth) {
            log("Auth token user");
        }
    }
}
