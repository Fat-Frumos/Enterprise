package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService implements Service<User> {

    private static final Logger log = Logger.getLogger(UserService.class);
    private final JdbcUserDao jdbcUserDao = new JdbcUserDao();

    public Optional<User> findByName(@NotNull String name) {
        return jdbcUserDao.findByName(name);
    }

    public boolean validateUser(String name, String password, User user) {
        return name.equalsIgnoreCase(user.getName()) && password.equals(user.getPassword());
    }

    @Override
    public boolean save(User user) {
        return jdbcUserDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return jdbcUserDao.findAll();
    }

    @Override
    public List<User> getAll(String sql) {
        return jdbcUserDao.findAll(sql);
    }

    @Override
    public List<User> getAll(Map<String, String> params) {
        return jdbcUserDao.findAll();
    }

    @Override
    public List<User> getAll(Map<String, String> params, int offset) {
        return jdbcUserDao.findAll();
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
        return null;
    }

    public String sendEmail(String name) {
        Optional<User> optionalUser = jdbcUserDao.findByName(name);

        log.info(optionalUser);

        return optionalUser.isPresent() ? optionalUser.get().getName() : "User not found";
    }

    public User bookCar(@NotNull Car car, @NotNull User user) {
        if (!user.getCars().contains(car)) {
            user.addCar(car);
        }
        return user;
    }
}