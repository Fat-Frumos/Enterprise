package com.enterprise.rental.controller;

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
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@WebServlet(urlPatterns = "/")
public class StaticServlet extends HttpServlet {
    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(StaticServlet.class);
    private Set<Car> cars = carService.getAll("id BETWEEN 219 AND 229");

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("cars", cars);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        request.getRequestDispatcher("/WEB-INF/views/index.jsp")
                .forward(request, response);
    }

    /**
     * Auth & valid user
     */
    @Override
    protected void doPost(

            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.findByName(name);

        log.info(String.format("optionalUser: %s. name:%s password:%s",
                optionalUser, name, password));


        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            log.info(String.format("User: %s. Input %s %s", user, name, password));

            boolean isValid = Objects.equals(name, user.getName())
                    && password.equals(user.getPassword());

            if (isValid) {
                request.setAttribute("cars", carService.getRandom(20));
                request.setAttribute("user", user);
            }
            request.setAttribute("errorMessage", "User is exists");
        }
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/WEB-INF/views/login.jsp");

        dispatcher.include(request, response);
    }
}
