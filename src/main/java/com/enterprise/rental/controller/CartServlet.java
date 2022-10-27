package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(CartServlet.class);

    /**
     * Cars basket for user: get list
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(false);

        String path;

        if (session != null && session.getAttribute("user") != null) {

            User user = (User) session.getAttribute("user");
            log.info(String.format("Get into session User %s bucket cars: %s", user.getName(), user.getCars().size()));
            request.setAttribute("cars", user.getCars());
            request.setAttribute("car", user.getCars().size());
            dispatch(request, response, index);
        } else {
            dispatch(request, response, login);
        }
    }

    /**
     * put + to Basket, post -> save user to db
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        log.info(String.format("Post from session %s", user));
        String role = user != null ? user.getRole() : "guest";
        int id = 0;
        if (role.equals("")) {
            id = Integer.parseInt(request.getParameter("id"));
            Car car = carService.getById(id);
            log.info(car);
        }
        user.setRole(role);
        log.info(user);
        log.info(String.format("Main post car %d from session %s: %s", id, role, user));

        boolean driver = false;

        Order order = new Order(id, user.getUserId(), driver);

        log.info(String.format("saved %s", order));

        Set<Order> orders = user.getOrders();

        if (orders != null) {
            orders.add(order);
            user.setOrders(orders);
        }
        dispatch(request, response, main);
    }

    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");

            if (user != null) {

                log.info(String.format("Session User Basket: %s", user));

                try {
                    long id = Long.parseLong(request.getParameter("id"));
                    Car car = carService.getById(id);
                    if (car != null) {
                        userService.bookCar(car, user);
                        user.setCar(car);
                        request.setAttribute("auto", user.getCar());
                        request.setAttribute("user", user);
                        log.info(String.format("Car: %s", user.getCar()));
                        log.info(String.format("Put new Car %s into the basket: %s", car.getBrand(), user.getCars().size()));

                    }
                } catch (NumberFormatException e) {
                    log.info("Car by id not found");
                    request.setAttribute("errorMessage", "Car not found");
                }
            }
        }
        request.getRequestDispatcher("/order")
                .forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        super.doDelete(request, response);
    }

    private void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        dispatcher.include(request, response);
    }
}

//
//            log.info(String.format("Put User into bucket session: %s", user));
//
//            long id = Long.parseLong(request.getParameter("id"));
//
//            if (id != 0) {
//                Car car = carService.getById(id);
//                car.setUserId(user.getUserId());
//                user.addCar(car);
//                log.info(String.format("Put cars into User session bucket: %s", car));

//            request.setAttribute("cars", user.getCars());
//            request.getRequestDispatcher("/cart")
//                    .forward(request, response);
//        } else {
//            request.setAttribute("errorMessage",
//                    String.format("User not found"));
//            request.getRequestDispatcher(login)
//                    .forward(request, response);
//        }


//TODO order
// userService.sendEmail(user.getEmail());


//        try {
//            User user = (User) session.getAttribute("user");
//            if (user != null) {
//                log.info("session " + user);
//                session.setAttribute("user", user);
//                request.setAttribute("cars", user.getCars());
//                request.getRequestDispatcher("/WEB-INF/views/index.jsp")
//                        .forward(request, response);
//            }
//        } catch (UserNotFoundException e) {
//            request.getRequestDispatcher("/WEB-INF/views/cars.jsp")
//                    .forward(request, response);
//        }