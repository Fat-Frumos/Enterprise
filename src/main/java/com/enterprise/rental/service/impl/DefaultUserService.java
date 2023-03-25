package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.Session;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import com.enterprise.rental.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.*;
import javax.validation.constraints.NotNull;
import java.util.*;

import static com.enterprise.rental.controller.Parameter.CLIENT;
import static com.enterprise.rental.service.email.InvoicePdf.createPdf;
import static com.enterprise.rental.service.email.Mail.sendEmailWithAttachments;

/**
 * Service class for managing users, implementations of {@link UserService} interface.
 * The UserService provides information useful for forcing a user to log in or out,
 * and retrieving information about the user who is currently logged-in.
 *
 * @author Pasha Polyak
 * @see JdbcUserDao
 */
public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LogManager.getLogger();

    private final OrderDao orderDao;
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final Session session;


    public Map<String, List<String>> getUserDamageMap() throws ServiceException {
        Map<String, List<String>> allDamages = new HashMap<>();

        try {
            List<User> userDaoAll = userDao.findAll();
            for (User user : userDaoAll) {
                List<String> damages = new ArrayList<>();
                if (user.getOrders().size() > 0) {
                    for (Order order : user.getOrders()) {
                        if (order.getDamage() != null) {
                            damages.add(order.getDamage());
                        }
                    }
                    allDamages.put(user.getName(), damages);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return allDamages;
    }


    /**
     * Set userDao in constructor
     * Init UserMapper, Session
     */
    public DefaultUserService() {
        this.orderDao = new JdbcOrderDao();
        this.userDao = new JdbcUserDao();
        this.session = new Session();
        this.userMapper = new UserMapper();
    }

    /**
     * Init UserMapper, Session, userDao into constructor
     */
    public DefaultUserService(UserDao jdbcUserDao) {
        this.orderDao = new JdbcOrderDao();
        this.userDao = jdbcUserDao;
        this.session = new Session();
        this.userMapper = new UserMapper();
    }

    /**
     * Is used to save the given User in the database.
     *
     * @param user the entity of type User
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean save(User user) throws ServiceException {
        try {
            return userDao.save(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find the User by name.
     * The annotated element must not be null. Accepts any type.
     *
     * @param name the Username
     * @return Optional User
     * @see javax.validation.constraints.NotNull
     */
    @Override
    public Optional<User> findByName(@NotNull String name) {
        return userDao.findByName(name);
    }

    /**
     * Is used to Find all users from the database
     *
     * @return the {@link List} of {@link User}
     * and {@link List#isEmpty()} if no results are found
     */
    @Override
    public List<User> findAllBy() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Retrieves all defined used</p>
     * Is used to get users from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of all Generic User.
     */
    @Override
    public List<User> findAllBy(String query) throws ServiceException {
        try {
            return userDao.findAll(query);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all users with parameters key-value map
     *
     * @param params Map (String key, String value)
     * @return the list of cars
     */
    @Override
    public List<User> findAllBy(Map<String, String> params) throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * <p>Retrieves the User with the specified id
     * Is used to return a user from the database by the given id,
     * otherwise {@code Optional.empty}.
     *
     * @param id The id of the user to be returned.
     * @return the <code>Optional User</code>
     * or <code>Optional.empty</code> if the user was not found
     * @see Optional#empty
     */
    @Override
    public Optional<User> findBy(Long id) {
        return userDao.findById(id);
    }

    /**
     * Add car to User profiler.
     * Check if car not contains
     * The annotated element must not be null. Accepts any type.
     *
     * @param user the User
     * @param car  the rental Car
     * @return User with booked car
     * @see javax.validation.constraints.NotNull
     */
    @Override
    public User toCart(@NotNull Car car, @NotNull User user) {
        if (!user.getCars().contains(car)) {
            user.addCar(car);
        }
        return user;
    }

    /**
     * Update the User by object.
     *
     * @param user type of User
     * @return User type of the new User
     */
    @Override
    public User edit(User user) throws ServiceException {
        try {
            return userDao.edit(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * Delete the User by ID.
     *
     * @param id the User id
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Sends email message fo user.
     * Created pdf, attachments letter
     *
     * @param name a username
     */
    @Override
    public boolean sendEmail(String name) throws DaoException {
        Optional<User> optionalUser = userDao.findByName(name);
        if (optionalUser.isEmpty()) {
            return false;
        }

        LOGGER.log(Level.INFO, optionalUser);
        createPdf();

        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "pasha-fghjkl11@i.ua";
        String password = "pasha-fghjkl11pasha-fghjkl11";

        // message info
        String mailTo = "fghjkl11@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "d:/letter.pdf";

        try {
            sendEmailWithAttachments(
                    host, port, mailFrom, password,
                    mailTo, subject, message,
                    attachFiles);

            LOGGER.log(Level.INFO, "Email sent");
            return true;
        } catch (Exception ex) {
            throw new DaoException("\"Could not send email.\"" + ex.getMessage());
        }
    }

    /**
     * Set user-token to Session user
     * an array containing all of the <code>Cookie</code>
     * and check user-token of the client sent with this request.
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends
     *                 to the client
     * @param session  an {@link HttpSession} Session information
     *                 is scoped only to the current web application,
     *                 so information stored in one context
     *                 will not be directly visible in another.
     * @see HttpSessionBindingListener
     */
    @Override
    public void setUserToken(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session) {

        User user = userMapper.mapper(request);
        this.session.setUser(user);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user-token")) {
                this.session.setToken((cookie.getValue()));
                session.setAttribute(CLIENT.value(), user);
            } else {
                cookie = new Cookie("user-token", UserService.generateUUID());
                cookie.setMaxAge(300);
                this.session.getUser().setActive(true);
                this.session.setToken(cookie.getValue());
                response.addCookie(cookie);
            }
        }
    }
}