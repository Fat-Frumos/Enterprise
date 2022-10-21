package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.enterprise.rental.dao.jdbc.Constants.login;
import static com.enterprise.rental.dao.jdbc.Constants.main;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(OrderServlet.class);
    private final CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        String path;

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                int carId = Integer.parseInt(request.getParameter("id"));
                Car car = carService.getById(carId);

                log.info(String.format("User Cars: %s", car));
                request.setAttribute("cars", user.getCars());
                request.setAttribute("car", user.getCars().size());
                request.setAttribute("auto", car);
                path = main;
            } else {
                path = login;
            }
        } else {
            path = login;
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        try {
            dispatcher.include(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
