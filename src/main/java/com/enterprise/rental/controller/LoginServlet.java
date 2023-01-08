package com.enterprise.rental.controller;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.controller.Parameter.ERROR;
import static com.enterprise.rental.dao.jdbc.Constants.LOGIN_JSP;
import static com.enterprise.rental.dao.jdbc.Constants.USERS_JSP;
import static com.enterprise.rental.entity.Role.USER;

/**
 * Servlet implementation class Login User Servlet
 * the core Login classes to get the job done.
 *
 * @author Pasha Polyak
 */
public class LoginServlet extends Servlet {

    private static final long serialVersionUID = UUID.randomUUID().getMostSignificantBits() & 0x7ffffL;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final UserService userService = new DefaultUserService();

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

        dispatch(request, response, LOGIN_JSP);
    }

    /**
     * @see #doPut(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPut(
            HttpServletRequest request,
            HttpServletResponse response) {
        dispatch(request, response, USERS_JSP);
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

        Optional<User> optionalUser = userService.findByName(name);

        if (optionalUser.isPresent()) {
            LOGGER.log( Level.INFO, "User: {}", optionalUser);
            request.setAttribute(ERROR.value(), String.format("User %s is exists", name));
        } else {
            User user = new User.Builder()
                    .userId(UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL)
                    .name(name)
                    .password(request.getParameter("password"))
                    .passport(request.getParameter("passport"))
                    .phone(request.getParameter("phone"))
                    .language(request.getParameter("language"))
                    .email(request.getParameter("email"))
                    .active(true)
                    .closed(false)
                    .role(USER.role())
                    .build();

            try {
                boolean save = userService.save(user);
                LOGGER.log( Level.INFO, "{} is created: {}", user.getName(), save);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Can not created User {}", user.getName());
            }

            request.setAttribute(ERROR.value(), String.format("User %s is created", name));

        }
        dispatch(request, response, LOGIN_JSP);
    }
}