package com.enterprise.rental.service;

import java.util.Map;
import java.util.Set;

public interface Service<T> {
    boolean save(T t);

    Set<T> getAll();

    Set<T> getAll(String sql);

    Set<T> getAll(Map<String, String> params);

    Set<T> getAll(Map<String, String> params, int offset);

    Set<T> getRandom(int size);

    boolean delete(long id);

    T getById(long id);
}
