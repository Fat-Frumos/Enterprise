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
import java.util.UUID;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;
    private final CarService carService = new CarService();

    private Set<Car> auto = carService.getAll();
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info(String.format("User: %s, login: %s & %s", user, name, password));

            boolean isValid = Objects.equals(name, user.getName())
                    && password.equals(user.getPassword());

            if (isValid) {
                request.setAttribute("cars", auto);
                request.setAttribute("user", user.getName());
            }
        }

        // TODO user.getRole()
        //response.sendRedirect("/WEB-INF/views/main.jsp");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher("/WEB-INF/views/main.jsp");
        dispatcher.include(request, response);
    }

    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/signup.jsp")
                .forward(request, response);
    }
}