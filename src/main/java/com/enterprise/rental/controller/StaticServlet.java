package com.enterprise.rental.controller;

import com.enterprise.rental.dao.jdbc.Constants;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.enterprise.rental.dao.jdbc.Constants.*;

@WebServlet(urlPatterns = "/")
public class StaticServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(StaticServlet.class);
    private List<Car> cars = carService.getAll("id BETWEEN 219 AND 237");
    List<Car> auto = carService.getRandom(3);


    /**
     * Main view
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Collections.shuffle(cars);
        request.setAttribute("cars", cars);
        dispatch(request, response, index);
    }

    /**
     * Create new user
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name != null && password != null) {
            User user = userService.findByName(name)
                    .orElse(new User(0, "guest", "", "guest@i.ua", "en", false));

            boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());

            if (isValid) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.setAttribute("cars", auto);
                request.setAttribute("auto", auto.get(0));
                log.info(String.format("Main Car: %s", auto.get(0)));
                dispatch(request, response, main);
                log.info(String.format("Session customer: %s", session.getAttribute("user")));
            } else {
                request.setAttribute("errorMessage", "Your name/password is incorrect");
                dispatch(request, response, login);
            }
        } else {
            dispatch(request, response, login);
        }
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
