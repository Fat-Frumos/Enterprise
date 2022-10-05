package com.enterprise.rental.dao;

import com.enterprise.rental.entity.User;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByName(String name);
}
