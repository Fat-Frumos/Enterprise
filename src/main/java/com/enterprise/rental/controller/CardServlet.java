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
public class CarServlet extends HttpServlet {

    // private final SecurityService securityService;
    private final CarService carService;

    public CarServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        boolean isAuth = false;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.getName().equals("user-token");
                isAuth = true;
                break;
            }
        }

        if (isAuth) {
            List<Car> cars = carService.getRandom();

            request.setAttribute("cars", cars);

            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }
}

//            request.setAttribute("errorMessage", "Invalid Credentials");
//
//                    request.getRequestDispatcher("/").forward(request, response);


//
//@WebServlet(urlPatterns = "/")
//public class IndexServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
////        response.sendRedirect(request.getContextPath() + "/");
//        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
//    }
//}
//
//@WebServlet(urlPatterns = "/")
//public class BrandCarServlet extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//    private final CarService carService = new CarService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws IOException, ServletException {
//
//        Car carRequest = getCar(request);
//
//        List<Car> cars = carService.getByBrand(carRequest.getBrand());
//        for (Car car : cars) {
//            log.info("{}", car);
//        }
//        request.setAttribute("cars", cars);
//        response.setContentType("text/html;charset=utf-8");
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        try {
//            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
//        } catch (Exception e) {
//            log.debug(e.getMessage());
//        }
//        request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
//    }
//}
