package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService implements Service<User> {

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

    @Override
    public boolean save(User user) {
        return jdbcUserDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getAll(String sql) {
        return null;
    }

    @Override
    public List<User> getAll(Map<String, String> params) {
        return null;
    }

    @Override
    public List<User> getAll(Map<String, String> params, int offset) {
        return null;
    }

    @Override
    public List<User> getRandom() {
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
}