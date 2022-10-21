package com.enterprise.rental.controller;

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
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.login;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;

    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(LoginServlet.class);

    /**
     * Login screen
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();

        dispatch(request, response, login);
    }

    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        dispatch(request, response, "/WEB-INF/views/users.jsp");
    }

    /**
     * Create new User
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            log.info(String.format("User: %s", optionalUser));

            request.setAttribute("errorMessage", String.format("User %s is exists", name));
            request.getRequestDispatcher(login)
                    .forward(request, response);
        } else {
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            User user = new User(
                    UUID.randomUUID().getMostSignificantBits() & 0x7ffffffffL,
                    name, password, email, "ua", "user", true);

            boolean save = userService.save(user);

            log.info(String.format("%s is created: %s", user, save));
            request.setAttribute("errorMessage", String.format("User %s is created", name));

            dispatch(request, response, login);
        }
    }

    /**
     * Request Dispatcher
     */
    void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        dispatcher.include(request, response);
    }
}