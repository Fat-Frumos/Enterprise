package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.UserService;
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
import java.util.*;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(OrderServlet.class);
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {

        String path;

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null) {
                path = setSession(request);
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
        } catch (ServletException | IOException e) {
            throw new DataException(e.getMessage());
        }
    }

    private String setSession(HttpServletRequest request) {
        User user = getRequestUser(request);
        List<Car> cars = new ArrayList<>();
        List<Car> carList = user.getCars();
        carList.stream().filter(auto -> cars.size() < 3).forEachOrdered(cars::add);
        request.setAttribute("cars", cars);
        request.setAttribute("car", user.getCars().size());
        return main;
    }

    private User getRequestUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Car car = user.getCar();
        session.setAttribute("auto", car);
        log.info(String.format("Rental Car: %s", car));
        log.info(String.format("User : %s", user));
        return user;
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String[] userField = {"id", "name", "passport", "payment", "term", "card", "expires", "username", "cvc", "driver"};
        //TODO CHECK USER
        //TODO CHECK CAR

        User user = getRequestUser(request);
        if (user != null) {

            Map<String, String> orderParams = new HashMap<>();
            for (String field : userField) {
                if (field != null) {
                    log.info(String.format("Order params %s: %s",
                            field, request.getParameter(field)));
                    orderParams.put(field, request.getParameter(field));
                }
            }

            log.info(String.format("Rental User order Params: %s", orderParams));
            Car car = user.getCar();
            log.info(String.format("Car %s", car));
            Order order = new Order();
            order.setCarId(car.getId());
            order.setUserId(user.getUserId());
            order.setPassport(orderParams.get("passport"));
//            order.setPayment(Double.parseDouble(orderParams.get("payment")));

            log.info(String.format("order %s", order));


            boolean saved = orderService.createOrder(order);
            request.setAttribute("cars", carService.getAll());
        }

        dispatch(request, response, cars);
    }

    //        User user = (User) request.getAttribute("user");
//        if (car != null) {
//            boolean saved = carService.save(car);
//            log.info(String.format("saved %s %s", car, saved));
//        }
//                int carId = Integer.parseInt(request.getParameter("id"));
//                Car car = carService.getById(carId);

    void dispatch(
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
