package com.enterprise.rental.controller;

import com.enterprise.rental.dao.jdbc.Constants;
import com.enterprise.rental.entity.Car;
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
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.cars;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");
    private final String[] fields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page"};
    private static final Logger log = Logger.getLogger(CarsServlet.class);


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Map<String, String> params = Arrays.stream(fields)
                .filter(key -> !"".equals(request.getParameter(key)) && request.getParameter(key) != null)
                .collect(Collectors.toMap(key -> key, request::getParameter, (a, b) -> b));

        int page;

        try {
            page = Integer.parseInt(params.get("page"));
            if (page <= 1) {
                page = 1;
            }
        } catch (NumberFormatException e) {
            page = 1;
        }

        int limit;

        try {
            limit = Integer.parseInt(params.get("limit"));
        } catch (NumberFormatException e) {
            limit = 10;
        }
        params.put("limit", String.valueOf(limit));
        params.put("page", String.valueOf(page));

        log.info(String.format("Quest Params: %s", params));

        if (Integer.parseInt(params.get("limit")) == 10
                && Integer.parseInt(params.get("page")) <= 1 && params.size() == 2) {
            request.setAttribute("cars", carList);
        } else {
            List<Car> auto = params.keySet()
                    .stream()
                    .map(key -> String.format("&%s=%s", key, params.get(key)))
                    .collect(Collectors.joining())
                    .equals("")
                    ? carService.getAll(params)
                    : carService.getAll(params, page);
            if (!auto.isEmpty()) {
                request.setAttribute("cars", auto);
            } else {
                page--;
            }
        }
        request.setAttribute("page", page);

        if (user != null) {
            user.addParams(params);
            log.info(String.format("Users Cars: %s", user.getCars().size()));
            request.setAttribute("car", user.getCars().size());
            log.info(String.format("Users Params: %s", user.getParams()));
        }

        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(cars);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        Car car = getCar(request);

        if (car != null) {
            boolean saved = carService.save(car);
            log.info(String.format("saved %s %s", car, saved));
        }

        request.setAttribute("cars", carService.getAll());

        request.getRequestDispatcher(Constants.cars)
                .forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        long carId = Long.parseLong(request.getParameter("id"));
//        HttpSession session = request.getSession(false);
//        User user = (User) session.getAttribute("user");
//        String role = user != null ? user.getRole() : "guest";
//        log.info(String.format("Main post from session  %s: %s", role, user));

//        Order order = new Order(carId, user.getUserId());

//        log.info(String.format("saved %", order.getOrderId()));

//        Set<Order> orders = user.getOrders();
//        orders.add(order);
//        user.setOrders(orders);

//        session.setAttribute("user", user);
//        request.getRequestDispatcher("/WEB-INF/views/main.jsp")
//                .forward(request, response);

//.orElse(new User(0, "guest", "", "guest@i.ua", "en", false));
//

    }

    private Car getCar(HttpServletRequest request) {

        long id;

        try {
            id = Long.parseLong(request.getParameter("id"));

        } catch (Exception e) {
            id = UUID.randomUUID().version();
        }
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String path = request.getParameter("path");

        try {
            Double price = Double.parseDouble(request.getParameter("price"));
            Double cost = Double.parseDouble(request.getParameter("cost"));
            int year = Integer.parseInt(request.getParameter("year"));


            LocalDateTime time = LocalDateTime.now();

            if ("".equals(name) || "".equals(brand)) {
                request.setAttribute("errorMessage", "Enter a valid car");
            }

            return new Car.Builder()
                    .id(id)
                    .name(name)
                    .brand(brand)
                    .model(model)
                    .path(path)
                    .price(price)
                    .cost(cost)
                    .year(year)
                    .created(time)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }
}