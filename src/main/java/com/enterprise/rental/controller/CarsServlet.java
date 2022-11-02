package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.CARS;

@WebServlet(urlPatterns = "/cars")
public class CarsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(CarsServlet.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String offset = "limit";

        CarService carService = new CarService();

        String[] fields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page"};

        List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");

        int size = carService.getAll().size();

        Map<String, String> params = Arrays.stream(fields)
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

        try {
            limit = Integer.parseInt(params.get(offset));
        } catch (NumberFormatException e) {
            limit = 10;
        }

        params.put(offset, String.valueOf(limit));
        params.put("page", String.valueOf(page));

        log.info(String.format("Quest Params: %s, page %s", params, page));

        if (Integer.parseInt(params.get(offset)) == 10
                && Integer.parseInt(params.get("page")) <= 1
                && params.size() == 2) {
            request.setAttribute("cars", carList);
        } else {
            if (page * limit > size) {
                page--;
            }
            List<Car> auto = params.keySet()
                    .stream()
                    .map(key -> String.format("&%s=%s", key, params.get(key)))
                    .collect(Collectors.joining())
                    .equals("")
                    ? carService.getAll(params)
                    : carService.getAll(params, page);
            if (auto.isEmpty()) {
                page--;
            } else {
                request.setAttribute("cars", auto);
            }
        }

        request.setAttribute("page", page);

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            user.addParams(params);
            log.info(String.format("Users Cars: %s", user.getCars()));
            request.setAttribute("car", user.getCars().size());
            log.info(String.format("Users Params: %s", user.getParams()));
        }

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(CARS);

        dispatcher.include(request, response);
    }
}