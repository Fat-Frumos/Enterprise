package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.forgot;
import static com.enterprise.rental.dao.jdbc.Constants.login;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final Logger log = Logger.getLogger(UserServlet.class);

    /**
     * View all user if forgot
     * <p>
     * if Admin role show all users
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String path = forgot;

        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            String role = user != null ? user.getRole() : "guest";
            if ("admin".equals(role)) {
                session.setAttribute("user", user);
                List<User> users = userService.getAll();
                log.info("users: " + users);
                request.setAttribute("users", users);
                path = "/WEB-INF/views/users.jsp";
            }
        } else {
            path = login;
        }
        request.getRequestDispatcher(path)
                .forward(request, response);
    }

    /**
     * Authentication and authorization User
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
//        String role = request.getParameter("role");

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            log.info(String.format("User: %s", optionalUser));
            request.setAttribute("errorMessage", "User is exists please try again");
            response.sendRedirect("/WEB-INF/views/login.jsp");
        } else {

            User user = new User(
                    UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                    name, password, email, "ua", true);

            boolean save = userService.save(user);

            log.info(String.format("%s is created: %s", user, save));

            request.setAttribute("user", user.getName() + "role: ");

            request.getRequestDispatcher("/WEB-INF/views/main.jsp")
                    .forward(request, response);
        }
    }

    /**
     * Send an email if the password has been forgotten
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String message = userService.sendEmail(request.getParameter("username"));

        response.sendRedirect(login);

        log.info(String.format("%s check your email address", message));


//        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
//                .forward(request, response);
    }
}

///**
// * View All users
// * */
//    @Override
//    protected void doPut(
//            HttpServletRequest request,
//            HttpServletResponse response)
//            throws ServletException, IOException {
//        dispatch(request, response, "/WEB-INF/views/forgot.jsp");
//    }
//
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//        Optional<User> optionalUser = userService.findByName(name);
//
//        User user = optionalUser.orElse(new User(0, "guest", "", "guest@i.ua", "en", false));
//
//        boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());
//
//        HttpSession session = request.getSession();
//
//        if (isValid) {
//            session.setAttribute("user", user);
//            Set<Car> cars = carService.getRandom(3);
//            request.setAttribute("cars", cars);
//            request.setAttribute("username", String.format("(%s:%s)", user.getName(), user.getRole()));
//            dispatch(request, response, "/WEB-INF/views/main.jsp");
//        } else {
//            request.setAttribute("errorMessage", "Invalid credentials");
//            dispatch(request, response, "/WEB-INF/views/login.jsp");
//        }
//        log.info(String.format("Session customer: %s, Creation Time: %s, Last Accessed Time: %s",
//                session.getAttribute("user"),
//                new Date(session.getCreationTime()),
//                new Date(session.getLastAccessedTime())));