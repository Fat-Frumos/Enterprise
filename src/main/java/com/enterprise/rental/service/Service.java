package com.enterprise.rental.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Service<T> {
    boolean save(T t);

    List<T> getAll();

    List<T> getAll(String query);

    List<T> getAll(Map<String, String> params);

    List<T> getAll(Map<String, String> params, int offset);

    List<T> getRandom(int size);

    boolean delete(long id);

    T edit(T t);

    Optional<T> getById(long id);
}
