package com.enterprise.rental.service;

import com.enterprise.rental.dao.jdbc.JdbcUserDao;
import com.enterprise.rental.entity.User;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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

    public Set<User> findAll() {
        return jdbcUserDao.findAll();
    }

    @Override
    public boolean save(User user) {
        return jdbcUserDao.save(user);
    }

    @Override
    public Set<User> getAll() {
        return null;
    }

    @Override
    public Set<User> getAll(String sql) {
        return null;
    }

    @Override
    public Set<User> getAll(Map<String, String> params) {
        return null;
    }

    @Override
    public Set<User> getAll(Map<String, String> params, int offset) {
        return null;
    }

    @Override
    public Set<User> getRandom(int size) {
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
        return name;
    }
}