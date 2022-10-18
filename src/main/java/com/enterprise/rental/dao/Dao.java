package com.enterprise.rental.dao;

import com.enterprise.rental.entity.User;

import java.util.Optional;
import java.util.Set;

public interface Dao<T> {
    Set<T> findAll();

    Optional<T> findById(Long id);

    Optional<User> findByName(String name);

    boolean save(T t);

    T edit(T t);

    boolean delete(long id);

    Set<T> findAll(String params);
}
