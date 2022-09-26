package com.enterprise.user.web;

import com.enterprise.car.entity.Car;
import com.enterprise.car.service.CarService;
import com.enterprise.car.service.Service;
import com.enterprise.user.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
//2. @WebServlet(urlPatterns = "/login")
//3. doGet(HttpServletRequest request, HttpServletResponse response)
//3. doPost(HttpServletRequest request, HttpServletResponse response)
//4. How is the response created?

@WebServlet(urlPatterns = "/")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Service service = new CarService();
    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        boolean isValidUser = loginService.validateUser(name, password);
        if (isValidUser) {

            List<Car> cars = service.getRandom();

            request.setAttribute("cars", cars);

            request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);

        } else {
            request.setAttribute("errorMessage", "Invalid Credentials");
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        }
    }
}