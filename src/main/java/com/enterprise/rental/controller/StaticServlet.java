package com.enterprise.rental.controller;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.CarService;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.enterprise.rental.dao.jdbc.Constants.*;

/**
 * StaticServlet extends an HTTP servlet suitable for a Web-site.
 * A subclass of <code>HttpServlet</code> must override two methods:
 * <ul>
 * <li> <code>doGet</code>, if the servlet supports HTTP GET requests
 * <li> <code>doPost</code>, for HTTP POST requests
 * </ul>
 *
 * @author Pasha Pollack
 */
@WebServlet(urlPatterns = "/")
public class StaticServlet extends HttpServlet {
    //    private static final List<Car> carList = carService.getAll("price>=100 ORDER BY cost LIMIT 10 OFFSET 0");
    private static final Logger log = Logger.getLogger(StaticServlet.class);
    private static final CarService carService = new CarService();
    private static final int ROWS = carService.getNumberOfRows();

    /**
     * <p>If the HTTP GET request is correctly formatted,
     * <code>doGet</code>, cars from DataBase </p>
     * <p>If User not registered redirect to Login page,
     * otherwise redirect to index page </p>
     *
     * <p>Cars list for main page: get list</p>
     * A Page Request object by passing in the requested page number and the page limit.
     * {@code Map<String, String> params} is a map of parameters to the sorting and paging
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
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String[] carFields = {"id", "name", "brand", "model", "path", "price", "cost", "year", "sort", "direction", "page"};

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
        int nOfPages = ROWS / offset;
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

        List<Car> cars = getAuto(params, page);
        params.put("page", String.valueOf(page));
        params.put("limit", String.valueOf(start));
        request.setAttribute("cars", cars);
        request.setAttribute("page", page);
        request.setAttribute("noOfPages", tail);
        request.setAttribute("begin", String.valueOf(begin));
        request.setAttribute("recordsPerPage", offset);

        RequestDispatcher dispatcher = request.getRequestDispatcher(CARS);

        dispatcher.forward(request, response);

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            user.addParams(params);
            request.setAttribute("car", user.getCars().size());
            log.info(String.format("Users Params: %s", user.getParams()));
        }
        dispatch(request, response, CARS);
    }

    private static List<Car> getAuto(Map<String, String> params, int page) {
        return params.keySet()
                .stream()
                .map(key -> String.format("&%s=%s", key, params.get(key)))
                .collect(Collectors.joining())
                .equals("")
                ? carService.getAll(params)
                : carService.getAll(params, page);
    }


    /**
     * <p>If the HTTP POST request is correctly formatted,
     * <code>doPost</code>, check if user exists set the error message,
     * whereas user not found, create new User in DataBase
     * {@code Optional<User>}, if a value is present, otherwise {@code Optional.empty()}.
     * <p></>Redirect to Main page </p>
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

        UserService userService = new UserService();

        List<Car> auto = carService.getRandom(3);
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String path;
        if (name != null && password != null) {
            Optional<User> optionalUser = userService.findByName(name);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                boolean isValid = Objects.equals(name, user.getName()) && password.equals(user.getPassword());
                path = getPath(request, auto, user, isValid);
            } else {
                request.setAttribute("errorMessage", "User not found");
                path = LOGIN;
            }
            dispatch(request, response, path);
        }
    }

    /**
     * Returns {@code String path} instance.
     *
     * @param request an {@link HttpServletRequest} object that
     *                contains the request the client has made
     *                of the servlet
     * @param isValid an {@link boolean} if user is valid, set request attribute, otherwise redirect to login page
     * @param auto    an {@link List<Car>} set request attribute
     * @param user    an {@link User} set request attribute
     *
     *                <p>Get Attribute User from Session</p>
     * @return {@code Optional<User>}, if a value is present,
     * otherwise {@code Optional.empty()}.
     */
    private static String getPath(HttpServletRequest request, List<Car> auto, User user, boolean isValid) {
        String path;
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.setAttribute("cars", auto);
            request.setAttribute("auto", auto.get(0));
            log.info(String.format("Main Car: %s", auto.get(0)));
            log.info(String.format("Session customer: %s", session.getAttribute("user")));
            path = MAIN;
        } else {
            request.setAttribute("errorMessage", "Your name/password is incorrect");
            path = LOGIN;
        }
        return path;
    }

    /**
     * Defines an object that receives requests from the client
     * and sends them to any resource
     *
     * <p>The servlet container creates the <code>RequestDispatcher</code> object,
     * which is used as a wrapper around a server resource located
     * at a particular path or given by a particular name.
     *
     * <p>This interface is intended to wrap servlets,
     * but a servlet container can create <code>RequestDispatcher</code>
     * objects to wrap any type of resource.
     * <p>
     * Includes the content of a resource (servlet, JSP page,
     * HTML file) in the response. In essence, this method enables
     * programmatic server-side includes.
     *
     * <p>The {@link ServletResponse} object has its path elements
     * and parameters remain unchanged from the caller's. The included
     * servlet cannot change the response status code or set headers;
     * any attempt to make a change is ignored.
     *
     * <p>The request and response parameters must be either the same
     * objects as were passed to the calling servlet's service method or be
     * subclasses of the {@link ServletRequestWrapper} or {@link ServletResponseWrapper} classes
     * that wrap them.
     *
     * @param request  the {@link HttpServletRequest} object that
     *                 contains the request the client made of
     *                 the servlet
     * @param response the {@link HttpServletResponse} object that
     *                 contains the response the servlet returns
     *                 to the client
     * @param path     the String that contains the response the servlet returns
     *                 to the client
     */
    void dispatch(
            HttpServletRequest request,
            HttpServletResponse response, String path) {

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html;charset=utf-8");

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(path);
        try {
            dispatcher.include(request, response);
        } catch (ServletException | IOException e) {
            throw new DataException(e.getMessage());
        }
    }
}
