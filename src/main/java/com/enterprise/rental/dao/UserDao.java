package com.enterprise.rental.dao;

import com.enterprise.rental.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserDao extends Dao<User> {
    Optional<User> findByName(String name);
    Set<User> findAll();
}
