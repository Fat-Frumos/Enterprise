package com.enterprise.rental.controller;

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
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private static final Log log = LogFactory.getLog(OrderServlet.class);
    private final CarService carService = new CarService();
    private final OrderService orderService = new OrderService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            long carId = Long.parseLong(request.getParameter("id"));
            List<Car> userCars = user.getCars();
            for (Car car : userCars) {
                if (car.getId() == carId) {
                    log.info(String.format("Rental Car: %s", car));
                    log.info(String.format("User : %s", user));
                    List<Car> cars = new ArrayList<>();
                    List<Car> carList = user.getCars();
                    carList.stream().filter(auto -> cars.size() < 3).forEachOrdered(cars::add);
                    request.setAttribute("auto", car);
                    request.setAttribute("cars", cars);
                    request.setAttribute("car", user.getCars().size());
                }
            }
            dispatch(request, response, MAIN);
        } else {
            dispatch(request, response, LOGIN);
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
                long userId = user.getUserId();
                Car car = user.getCar();
                log.info(String.format("Car %s", car));

                String passport = request.getParameter("passport");
                String card = request.getParameter("card");
                boolean driver = request.getParameter("driver") != null;
                Double payment = Double.valueOf(request.getParameter("payment"));

                log.info(driver);

                String timestampAsString = request.getParameter("term");

                LocalDate localDate = LocalDate.parse(timestampAsString);

                Timestamp term = Timestamp.valueOf(localDate.atStartOfDay());

                log.info(term);

                if (car != null) {
                    Order order = new Order();
                    order.setCarId(car.getId());
                    order.setUserId(userId);
                    order.setDriver(driver);
                    order.setPassport(passport);
                    order.setCard(card);
                    order.setPayment(payment);
                    order.setTerm(term);

                    boolean saved = orderService.createOrder(order);
                    if (saved) {
                        List<Car> userCars = user.getCars();
//                        for (Car userCar : userCars) {
//                            if (userCar.getId() == car.getId()) {
//                                userCars.remove(userCar);
//                            }
//                        }
                        user.setCars(userCars);
                        user.setCar(new Car());
                    }
                }
                request.setAttribute("cars", carService.getAll());
            }
        }
        dispatch(request, response, CARS);
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
