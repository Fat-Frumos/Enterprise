package com.enterprise.rental.service;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final UserMapper userMapper;

    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.userMapper = new UserMapper();
    }

    public Optional<User> findByName(String name) {
        return userDao.findByName(name);
    }

    public boolean validateUser(String name, String password, User user) {
        return name.equalsIgnoreCase(user.getName()) && password.equals(user.getPassword());
    }
}
