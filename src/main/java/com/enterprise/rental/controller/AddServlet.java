package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(urlPatterns = "/user")
public class AddServlet extends HttpServlet {
    private final UserService userService = new UserService();

    private static final Logger log = Logger.getLogger(AddServlet.class);

    @Override
    protected void doGet(
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
        String email = request.getParameter("email");

        Optional<User> optionalUser = userService.findByName(name);

        log.info(String.format("User: %s", optionalUser.toString()));

        if (optionalUser.isEmpty()) {
            User user = new User(
                    UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE,
                    name, password, email, "ua", true);

            boolean save = userService.save(user);

            log.info(String.format("User: %s is created: %s", user, save));

            request.setAttribute("user", user.getName());
        } else {
            request.setAttribute("user", optionalUser.get().toString());
        }
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);


//        if (isValid) {
//            HttpSession session = request.getSession();
//            session.setAttribute("user", user.getName());
//            session.setMaxInactiveInterval(30*60);
//            Cookie userName = new Cookie("user", user.getName());
//            userName.setMaxAge(30*60);
//            response.addCookie(userName);
//            List<Car> cars = carService.getRandom();
//            response.sendRedirect("/WEB-INF/views/main.jsp");
//        request.setAttribute("cars", userService.getRandom());
//        } else {
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
//            PrintWriter out = response.getWriter();
//            out.println("<font color=red>Either user name or password is wrong.</font>");
//            request.setAttribute("errorMessage", "Invalid Credentials");
//            dispatcher.include(request, response);
//            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
//        }
    }
}
