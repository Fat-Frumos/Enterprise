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

public class BrandCarServlet extends HttpServlet {

    @Slf4j
    @WebServlet(urlPatterns = "/")
    public static class CarServlet extends HttpServlet {

        private static final long serialVersionUID = 1L;
        private final CarService carService = new CarService();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws IOException, ServletException {

            String brand = request.getParameter("brand");
            log.info("{}",brand);

            List<Car> cars = carService.getByBrand(brand);
            for (Car car : cars) {
                log.info("{}", car);
            }
            request.setAttribute("cars", cars);
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);

            try {
                request.getRequestDispatcher("/WEB-INF/views/brand.jsp").forward(request, response);
            } catch (Exception e) {
                log.debug(e.getMessage());
            }
            request.getRequestDispatcher("/WEB-INF/views/error/404.jsp").forward(request, response);
        }
    }
}

