package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    Optional<User> findByName(String name);

    boolean save(T t);

    T edit(T t);

    boolean delete(long id);

    List<T> findAll(String params);
}
