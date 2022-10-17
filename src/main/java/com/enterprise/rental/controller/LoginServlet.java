package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.OrderService;
import com.enterprise.rental.service.Service;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = -2L;
    private final CarService carService = new CarService();

    private final OrderService orderService = new OrderService();

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
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/add.jsp")
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
        log.info(String.format("optionalUser: %s. name:%s password:%s", optionalUser, name, password));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            log.info(String.format("User: %s. Input %s %s", user, name, password));

            boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());

            if (isValid) {
                request.setAttribute("cars", carService.getRandom());
                request.setAttribute("user", user);
                request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
            }
        }
        request.setAttribute("errorMessage", "Either name | password is wrong");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.include(request, response);
    }
}
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user.getName());
//            session.setMaxInactiveInterval(30*60);
//
//            Cookie userName = new Cookie("user", user.getName());
//            userName.setMaxAge(30*60);
//            response.addCookie(userName);
//            List<Car> cars = carService.getRandom();
//            response.sendRedirect("/WEB-INF/views/main.jsp");
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

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