package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/")
public class StaticServlet extends HttpServlet {
    private static final CarService carService = new CarService();
    private static final List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");
    private static final int size = carService.getAll().size();
    private static final Logger log = Logger.getLogger(StaticServlet.class);

    /**
     * Main view
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page"};

        Map<String, String> params = Arrays.stream(carFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(
                        key -> key,
                        request::getParameter,
                        (a, b) -> b));

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
        String offset = "limit";

        try {
            limit = Integer.parseInt(params.get(offset));
        } catch (NumberFormatException e) {
            limit = 10;
        }

        params.put(offset, String.valueOf(limit));
        params.put("page", String.valueOf(page));

        if (Integer.parseInt(params.get(offset)) == 10
                && Integer.parseInt(params.get("page")) <= 1
                && params.size() == 2) {
            request.setAttribute("cars", carList);

        } else {
            if (page * limit > size) {
                page--;
            }

            List<Car> cars = getAuto(params, page);

            if (cars.isEmpty()) {
                page--;
            } else {
                request.setAttribute("cars", cars);
            }
        }

        if (page > size / (limit + 1) - 1) {
            page = size / (limit);
        }

        request.setAttribute("page", page);
        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            user.addParams(params);
            request.setAttribute("car", user.getCars().size());
            log.info(String.format("Users Params: %s", user.getParams()));
        }
        dispatch(request, response, CARS);
    }

    private static List<Car> getAuto(Map<String, String> params, int page) {
        return params.keySet()
                .stream()
                .map(key -> String.format("&%s=%s", key, params.get(key)))
                .collect(Collectors.joining())
                .equals("")
                ? carService.getAll(params)
                : carService.getAll(params, page);
    }

    /**
     * Create new user
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        UserService userService = new UserService();

        List<Car> auto = carService.getRandom(3);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String path;
        if (name != null && password != null) {
            Optional<User> optionalUser = userService.findByName(name);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());
                path = getPath(request, auto, user, isValid);
            } else {
                request.setAttribute("errorMessage", "User not found");
                path = LOGIN;
            }
            dispatch(request, response, path);
        }
    }

    private static String getPath(HttpServletRequest request, List<Car> auto, User user, boolean isValid) {
        String path;
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("cars", auto);
            request.setAttribute("auto", auto.get(0));
            log.info(String.format("Main Car: %s", auto.get(0)));
            log.info(String.format("Session customer: %s", session.getAttribute("user")));
            path = MAIN;
        } else {
            request.setAttribute("errorMessage", "Your name/password is incorrect");
            path = LOGIN;
        }
        return path;
    }

    /**
     * Request Dispatcher
     */
    void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path) {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        try {
            dispatcher.include(request, response);
        } catch (ServletException | IOException e) {
            throw new DataException(e.getMessage());
        }
    }
}
