package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.Session;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.enterprise.rental.service.email.InvoicePdf.createPdf;
import static com.enterprise.rental.service.email.Mail.sendEmailWithAttachments;

/**
 * Service class for managing users, implementations of {@link UserService} interface.
 * The UserService provides information useful for forcing a user to log in or out,
 * and retrieving information about the user who is currently logged-in.
 *
 * @author Pasha Pollack
 * @see JdbcUserDao
 */
public class DefaultUserService implements UserService {
    private static final Logger log = Logger.getLogger(DefaultUserService.class);

    /**
     * Autowired UserDao, UserMapper, Session
     */
    private final UserMapper userMapper;
    private final UserDao userDao;
    private final Session session;

    /**
     * Init UserDao in constructor
     */
    public DefaultUserService() {
        this.session = new Session();
        this.userDao = new JdbcUserDao();
        this.userMapper = new UserMapper();
    }

    /**
     * Set userDao in constructor
     * Init UserMapper, Session
     *
     * @param userDao the entity of type UserDao
     */
    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
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
    public boolean save(User user) {
        return userDao.save(user);
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
    public Optional<User> getByName(@NotNull String name) {
        return userDao.findByName(name);
    }

    /**
     * Is used to Find all users from the database
     *
     * @return the {@link List} of {@link User}
     * and {@link List#isEmpty()} if no results are found
     */
    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    /**
     * <p>Retrieves all defined used</p>
     * Is used to get users from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of all Generic User.
     */
    @Override
    public List<User> getAll(String query) {
        return userDao.findAll(query);
    }

    /**
     * Find all users with parameters key-value map
     *
     * @param params Map (String key, String value)
     * @return the list of cars
     */
    @Override
    public List<User> getAll(Map<String, String> params) {
        return userDao.findAll();
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
    public Optional<User> getById(long id) {
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
    public User edit(User user) {
        return userDao.edit(user);
    }


    /**
     * Delete the User by ID.
     *
     * @param id the User id
     * @return true if method was executed and vice-versa
     */
    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    /**
     * Sends email message fo user.
     * Created pdf, attachments letter
     *
     * @param name a username
     */
    @Override
    public boolean sendEmail(String name) {
        Optional<User> optionalUser = userDao.findByName(name);
        if (optionalUser.isEmpty()) {
            return false;
        }

        log.debug(String.valueOf(optionalUser));
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

            log.debug("Email sent");
            return true;
        } catch (Exception ex) {
            throw new DataException("\"Could not send email.\"" + ex.getMessage());
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
                session.setAttribute("user", user);
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