package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final JdbcUserDao jdbcUserDao;

    public UserService(JdbcUserDao jdbcUserDao) {
        this.jdbcUserDao = jdbcUserDao;
    }

    public UserService() {
        this.jdbcUserDao = new JdbcUserDao();
    }

    public Optional<User> findByName(String name) {
        return jdbcUserDao.findByName(name);
    }

    public boolean validateUser(String name, String password, User user) {
        return name.equalsIgnoreCase(user.getName()) && password.equals(user.getPassword());
    }

    public List<User> findAll() {
        return jdbcUserDao.findAll();
    }
}