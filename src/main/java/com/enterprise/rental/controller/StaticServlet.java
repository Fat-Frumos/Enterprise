package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import com.enterprise.rental.service.impl.DefaultCarService;
import com.enterprise.rental.service.impl.DefaultUserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.controller.Parameter.*;
import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.service.UserService.saltedPassword;


/**
 * StaticServlet class that represent an index page <code>Main</code> an HTTP servlet suitable for a Web-site.
 * A subclass of <code>Servlet</code> override two methods:
 * <ul>
 * <li> <code>doGet</code>, if the servlet supports HTTP GET requests (all cars)
 * <li> <code>doPost</code>, for HTTP POST requests
 *
 * <li> <code>getAuto</code> fetches {@code List<Car>} using <code>CarService</code>
 * <li> <code>setRequestAttribute</code> set request attribute from session
 * </ul>
 *
 * @author Pasha Polyak
 */
public class StaticServlet extends Servlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final CarService carService = new DefaultCarService();
    private static final UserService userService = new DefaultUserService();
    private static int rows = carService.getNumberOfRows();


    /**
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, fetches {@code List<Car>} from the database</p>
     *
     * <p>If User not registered redirect to Login page,
     * otherwise redirect to main page </p>
     * A Page Request object by passing in the requested page number and the page limit.
     * {@code Map<String, String> params} is a map of parameters to the sorting and pagination
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     *                 {@code List<Cars>}, if a value is present, otherwise {@code empty List<Cars>}.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     */
    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String lang = request.getParameter("lang");

        String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page", "date"};

        Map<String, String> params = Arrays.stream(carFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(
                        key -> key,
                        request::getParameter,
                        (a, b) -> b));

        String currentPage = request.getParameter("page");

        int tail = 6;
        int begin = 1;
        int offset = 9;

        int page = currentPage == null ? 1 : Integer.parseInt(currentPage);

        int nOfPages = rows / offset;
        int start = page * offset - offset;

        if (nOfPages % offset > 0) {
            nOfPages++;
        }

        if (page >= 2) {
            begin++;
        }

        if (nOfPages - 5 < page) {
            tail = nOfPages - 1;
            begin = nOfPages - 5;
        }

        if (nOfPages <= page + 1) {
            begin = nOfPages - 6;
        }


        List<Car> cars = getCars(params, page);
        params.put("page", String.valueOf(page));
        params.put("limit", String.valueOf(start));
        request.setAttribute(CARS.value(), cars);
        request.setAttribute(PAGE.value(), page);
        request.setAttribute("noOfPages", tail);
        request.setAttribute("begin", String.valueOf(begin));
        request.setAttribute("recordsPerPage", offset);

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute(CLIENT.value()) != null) {
            User user = (User) session.getAttribute(CLIENT.value());
            if (lang != null) {
                user.setLanguage(lang);
            }

            user.addParams(params);
            request.setAttribute("car", user.getCars().size());
            LOGGER.log(Level.INFO, "User: {}", user);
        }
        dispatch(request, response, CARS_JSP);
    }

    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, fetched user from DataBase and entered in the system
     * {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     * <p>Redirect to Main page </p>
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends to the client
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles the request
     * @throws ServletException if the request for the POST
     *                          could not be handled
     */
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        String path = LOGIN_JSP;
        if (name != null && password != null) {
            Optional<User> optionalUser = userService.findByName(name);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String saltedPassword = saltedPassword(password, user.getSalt());

                LOGGER.log(Level.INFO, "{}{}: {}/{}{}", PURPLE, user.getName(), password, saltedPassword, RESET);

                boolean isValid = Objects.equals(name, user.getName()) && saltedPassword.equals(user.getPassword());

                path = setRequestAttribute(request, carService.getRandom(3), user, isValid);
            } else {
                request.setAttribute(ERROR.value(), "User not found");
            }
            dispatch(request, response, path);
        }
    }

    /**
     * Get List of Cars from the CarService with parameters
     * <p>
     * A Page Request object by passing in the requested page number and the page limit.
     *
     * @param params an {@code Map<String, String> params}
     *               is a map of query parameters to the sorting and pagination
     * @param page   an {@link boolean} pagination set page and offset attribute.
     *               Default values: page = 1, offset = 9,
     *               max Page get Number Of Rows from database
     * @return {@code List<Car>}, if a value is present,
     * otherwise {@code empty List}.
     */
    private static List<Car> getCars(Map<String, String> params, int page) {
        List<Car> cars = new ArrayList<>();
        try {
            cars = params.keySet()
                    .stream()
                    .map(key -> String.format("&%s=%s", key, params.get(key)))
                    .collect(Collectors.joining())
                    .equals("")
                    ? carService.findAllBy(params)
                    : carService.findAllBy(params, page);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return cars;
    }

    /**
     * <p>Get Attribute User from Session</p>
     * Returns {@code String path} instance.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @param isValid an {@link boolean} if user is valid,
     *                set request attribute,
     *                otherwise redirect to login page
     * @param auto    an {@link List<Car>} set request attribute
     * @param user    an {@link User} set request attribute
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     */
    private static String setRequestAttribute(HttpServletRequest request, List<Car> auto, User user, boolean isValid) {
        String path = LOGIN_JSP;
        if (!isValid) {
            request.setAttribute(ERROR.value(), "Your name/password is incorrect");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute(CLIENT.value(), user);
            request.setAttribute(CARS.value(), auto);
            request.setAttribute(AUTO.value(), auto.get(0));
            LOGGER.log(Level.INFO, "{}{}{}", GREEN, auto.get(0), RESET);
            path = MAIN_JSP;
        }
        return path;
    }
}
