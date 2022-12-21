package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.impl.DefaultUserService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.LOGIN;
import static com.enterprise.rental.dao.jdbc.Constants.USERS;

/**
 * Servlet implementation class Login User Servlet
 * the core Login classes to get the job done.
 *
 * @author Pasha Pollack
 */
public class LoginServlet extends Servlet {

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;
    private static final Logger log = Logger.getLogger(LoginServlet.class);
    private static final UserService userService = new DefaultUserService();

    /**
     * @see #LoginServlet()
     */
    public LoginServlet() {
        super();
    }

    /**
     * @see #doGet(HttpServletRequest request, HttpServletResponse response)
     * Logs out a user by deliting the session attribute "username" and then updates
     * the user session created to the user by adding the end time of the session
     */
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        session.invalidate();

        dispatch(request, response, LOGIN);
    }
    /**
     * @see #doPut(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) {
        dispatch(request, response, USERS);
    }

    /**
     * Creates a user in database . Gets Parameters from create in users.jsp
     * Logs in user by setting session attribute and then creating a user session.
     * Then the time of the user session start is also set as an attribute
     * Redirects an excited user to the home page and a new user to a page to
     * set their name and password
     *
     * @see #doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        userService.setUserToken(request, response, session);

        String name = request.getParameter("name");

        Optional<User> optionalUser = userService.getByName(name);

        if (optionalUser.isPresent()) {
            log.debug(String.format("User: %s", optionalUser));
            request.setAttribute("errorMessage", String.format("User %s is exists", name));
//            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            long id = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;
            String password = request.getParameter("password");
            String passport = request.getParameter("passport");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String language = request.getParameter("language");
            Timestamp created = new Timestamp(System.currentTimeMillis());

            User user = new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
                    .passport(passport)
                    .phone(phone)
                    .language(language)
                    .email(email)
//                    .created(created)
                    .active(true)
                    .closed(false)
                    .role("user")
                    .build();

            log.debug(String.format("Raw password: %s", password));
            boolean save = userService.save(user);

            log.debug(String.format("%s is created: %s", user.getName(), save));

            request.setAttribute("errorMessage",
                    String.format("User %s is created", name));

        }
            dispatch(request, response, LOGIN);
    }
}