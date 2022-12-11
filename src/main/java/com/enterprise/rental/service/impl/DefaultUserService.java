package com.enterprise.rental.service.impl;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.Cookie;

import static com.enterprise.rental.utils.email.InvoiceEmail.createPdf;
import static com.enterprise.rental.utils.email.Mail.sendEmailWithAttachments;

public class DefaultUserService implements UserService {
    private final UserMapper userMapper = new UserMapper();
    private final UserDao userDao;

    private static final Logger log = Logger.getLogger(DefaultUserService.class);

    public DefaultUserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public DefaultUserService() {
        this.userDao = new JdbcUserDao();
    }

    @Override
    public Optional<User> findByName(@NotNull String name) {
        return userDao.findByName(name);
    }

    @Override
    public boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> getAll(String query) {
        return userDao.findAll(query);
    }

    @Override
    public List<User> getAll(Map<String, String> params) {
        return userDao.findAll();
    }

    @Override
    public boolean delete(long id) {
        return userDao.delete(id);
    }

    @Override
    public Optional<User> getById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User bookCar(@NotNull Car car, @NotNull User user) {
        if (!user.getCars().contains(car)) {
            user.addCar(car);
        }
        return user;
    }

    @Override
    public User edit(User user) {
        return userDao.edit(user);
    }

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

    @Override
    public void setUserToken(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

    }

//    @Override
//    public void setUserToken(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//        User user = userMapper.(request).orElseThrow();
//        session.setUser(user);
//
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("user-token")) {
//                session.setToken((cookie.getValue()));
//            } else {
//                cookie = new Cookie("user-token", generateUUID());
//                cookie.setMaxAge(300);
//                session.getUser().setAuth(true);
//                session.setToken(cookie.getValue());
//                response.addCookie(cookie);
//            }
//        }
//    }
//    private String generateToken(Long id) {
//        User userFromDb = userService.findUserById(id).orElseThrow();
//        Map<User, String> userTokens = session.getUserTokens();
//        userTokens.put(userFromDb, generateUUID());
//        session.setUserTokens(userTokens);
//        session.setExpired(true);
//        return session.getToken();
//    }
}