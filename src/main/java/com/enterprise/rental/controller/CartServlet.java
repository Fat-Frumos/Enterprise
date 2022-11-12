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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(CartServlet.class);

    /**
     * Cars bucket for user: get list
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
            log.info(String.format("Get into session User %s bucket cars: %s",
                    user.getName(), user.getCars().size()));

            List<Car> userCars = user.getCars();
            int size = userCars.size();
            request.setAttribute("car", size);
            request.setAttribute("cars", userCars);
        }
        dispatch(request, response, INDEX);
    }

    /**
     * post -> save user to db
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            log.info(String.format("Post from session %s", user));
            if (Objects.equals(user.getRole(), "user")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Optional<Car> optional = carService.getById(id);
                if (optional.isPresent()) {
                    Car car = optional.get();
                    log.info(car);
                    log.info(String.format("Main post car %d from User session %s", id, user.getName()));
                    Order order = new Order(id, user.getUserId(), false);
                    log.info(String.format("saved %s", order));
                    Set<Order> orders = user.getOrders();
                    if (orders != null) {
                        orders.add(order);
                        user.setOrders(orders);
                    }
                    response.sendRedirect(MAIN);
                }
            }
        } else {
            response.sendRedirect(LOGIN);
        }
    }


    /**
     * put Car to Bucket
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) throws
            ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user != null) {
            log.info(String.format("Session %s %s From Put ", user.getRole(), user.getName()));

            String carId = request.getParameter("id");
            try {
                long id = Long.parseLong(carId);
                Optional<Car> optionalCar = carService.getById(id);
                if (optionalCar.isPresent()) {
                    Car car = optionalCar.get();
                    user = userService.bookCar(car, user);
                    request.setAttribute("auto", user.getCar());
                    request.setAttribute("user", user);
                    log.info(String.format("Put new Car %s into the Cart: %s",
                            car.getBrand(), user.getCars().size()));
                }
            } catch (NumberFormatException e) {
                log.info("Car by id not found");
                request.setAttribute("errorMessage", "Car not found");
            }

            response.sendRedirect(ORDERS);
        } else {
            response.sendRedirect(LOGIN);
        }
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

//        dispatch(request, response, ORDERS);

//    private static String getPath(List<Car> userCars) {
//        return userCars.isEmpty() ? CARS : checkCard(userCars);
//    }

//    private static String checkCard(List<Car> userCars) {
//        return userCars.size() > 12 ? remove(userCars, 0) : INDEX;
//    }

//    private static String remove(List<Car> userCars, int index) {
//        userCars.remove(index);
//        return "/user";
//    }