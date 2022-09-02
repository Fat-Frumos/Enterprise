package com.enterprise.car.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}

//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
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
