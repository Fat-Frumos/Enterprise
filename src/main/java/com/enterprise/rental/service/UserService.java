package com.enterprise.rental.service;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.CarNotFoundException;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.enterprise.rental.utils.Invoice.createPdf;
import static com.enterprise.rental.utils.Mail.sendEmailWithAttachments;

public class UserService implements Service<User> {

    private final UserDao userDao;

    private static final Logger log = Logger.getLogger(UserService.class);

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
        this.userDao = new JdbcUserDao();
    }

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
    public List<User> getAll(String sql) {
        return userDao.findAll(sql);
    }

    @Override
    public List<User> getAll(Map<String, String> params) {
        return userDao.findAll();
    }

    @Override
    public List<User> getAll(Map<String, String> params, int offset) {
        return userDao.findAll();
    }

    @Override
    public List<User> getRandom(int size) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public User getById(long id) {

        Optional<User> user = userDao.findById(id);
        return user.orElseThrow(() ->
                new CarNotFoundException("Car not found"));
    }

    public String sendEmail(String name) {
        Optional<User> optionalUser = userDao.findByName(name);

        log.info(optionalUser);
        createPdf();

        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "pasha-fghjkl11@i.ua";
        String password = "pasha-fghjkl11pasha-fghjkl11";

        // message info
        String mailTo = "your-friend-email";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
        String[] attachFiles = new String[2];
        attachFiles[0] = "d:/letter.pdf";

        try {
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, message, attachFiles);
            log.info("Email sent.");
        } catch (Exception ex) {
            throw new DataException("\"Could not send email.\"" + ex.getMessage());
        }

        return optionalUser.isPresent() ? optionalUser.get().getName() : "User not found";
    }

    public User bookCar(@NotNull Car car, @NotNull User user) {
        if (!user.getCars().contains(car)) {
            user.addCar(car);
        }
        return user;
    }
}