package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.Service;
import com.enterprise.rental.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -2L;
    private final Service carService = new CarService();

    private final OrderService orderService = new OrderService();

    private final UserService userService = new UserService();

    private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

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
        User user = optionalUser.orElse(new User(0, "guest", "guest", "guest@i.ua"));
        log.info(String.format("User: %s, password: %s. Input %s %s", user.getName(), user.getPassword(), name, password));

        //TODO boolean validateUser = userService.validateUser(name, password, user);
        // boolean isValidUser = securityService.checkUser(name, password);

        boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());

        if (isValid) {
            List<Car> cars = carService.getRandom();
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

        } else {
            request.setAttribute("errorMessage", "Invalid Credentials");
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        }
    }

    /*
     * Client sends Http Request to Web Server
     *
     * Code in Web Server => Input:HttpRequest, Output: HttpResponse JEE with Servlets
     *
     * Web Server responds with Http Response
     */

//Java Platform, Enterprise Edition (Java EE) JEE6

//Servlet is a Java programming language class
//used to extend the capabilities of servers
//that host applications accessed by means of
//a request-response programming model.

//1. extends javax.servlet.http.HttpServlet
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//3. doPost(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?

//        processRequest(request, response);

//        request.setAttribute("name", getRequestParameter(request, "name"));
//        request.setAttribute("email", getRequestParameter(request, "email"));
//        request.getRequestDispatcher(path).forward(request, response);
//        String param = request.getParameter(name);
//        return param.isEmpty() ? getInitParameter(name) : param;
//        response.addCookie(new Cookie("user", name));
//        response.addCookie(new Cookie(", password", password));
//        List<Car> cars = service.getRandom();
//        request.setAttribute("cars", cars);
//        forwardRequest(request, response, "/WEB-INF/views/user.jsp");
//        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
//        Optional<User> optionalUser = userService.findByName(name);
//
}