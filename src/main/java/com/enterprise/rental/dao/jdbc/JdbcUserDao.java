package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.factory.ConnectionFactory;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

import static com.enterprise.rental.dao.jdbc.JdbcUserTemplate.getUserByName;

public class JdbcUserDao implements UserDao {

//    private final ConnectionFactory connectionFactory;

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public User edit(User user) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Car> findAll(String name) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(String name) {
        return getUserByName(name);
    }
}
