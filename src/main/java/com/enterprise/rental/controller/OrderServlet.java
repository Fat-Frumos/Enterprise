package com.enterprise.rental.controller;

import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
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
import java.util.ArrayList;
import java.util.List;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(OrderServlet.class);
    private static final CarService carService = new CarService();
    private static final OrderService orderService = new OrderService();
    private static final OrderMapper ORDER_MAPPER = new OrderMapper();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {

        String path = LOGIN;
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            String id = request.getParameter("id");
            if (id != null) {
                long carId = Long.parseLong(id);

                request.setAttribute("auto", carService.getById(carId));

                List<Car> userCars = user.getCars();

                setUserAttribute(request, user, carId, userCars);

                path = MAIN;
            } else {
                path = CARS;
            }
        }
        dispatch(request, response, path);
    }

    private static void setUserAttribute(HttpServletRequest request, User user, long carId, List<Car> userCars) {
        for (Car car : userCars) {
            if (car.getId() == carId) {
                log.info(String.format("Rental Car: %s", car));
                log.info(String.format("User : %s", user));

                List<Car> cars = new ArrayList<>();
                List<Car> carList = user.getCars();
                carList.stream().filter(auto -> cars.size() < 3).forEachOrdered(cars::add);
                user.setCar(car);
                request.setAttribute("auto", car);
                request.setAttribute("cars", cars);
                request.setAttribute("car", user.getCars().size());
                request.setAttribute("user", user);
            }
        }
    }

    /***
     * Save car in order
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                log.info(String.format("user %s", user));
                Car car = user.getCar();
                log.info(String.format("Car %s", car));
                if (car != null) {
                    Order orderMapper = ORDER_MAPPER.orderMapper(request);
                    boolean saved = orderService.createOrder(orderMapper);
                    if (saved) {
                        List<Car> userCars = user.getCars();
                        for (Car userCar : userCars) {
                            if (userCar.getId() == car.getId()) {
                                userCars.remove(userCar);
                                user.setCars(userCars);
                            }
                        }
                        List<Order> orderList = orderService.getAll(user);
                        request.setAttribute("order", orderList);
                        user.setCar(new Car());
                    }
                }
//                request.setAttribute("cars", carService.getAll());
            }
        }
        response.sendRedirect("/user");
    }


    @Override
    protected void doDelete(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String id = (request.getParameter("orderId"));
        log.info(String.format("%s", id));
        String path = LOGIN;
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user.getRole().equals("manager")) {
                boolean delete = orderService.delete(id);
                log.info(String.format("%s %b", id, delete));
//                List<Order> userOrders = orderService.getAll();
//                request.setAttribute("orders", userOrders);
                request.setAttribute("user", user);
                request.setAttribute("cars", user.getCars());
                log.info(String.format("%s", user.getCars()));
                log.info(String.format("%s", user));
                path = "/user";

            }
            response.sendRedirect(path);
//            dispatch(request, response, path);
        }

    }

    void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path) {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");
        try {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(path);
            dispatcher.include(request, response);
        } catch (Exception e) {
            throw new DataException(e.getMessage());
        }
    }
}