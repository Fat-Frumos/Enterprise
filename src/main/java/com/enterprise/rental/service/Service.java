package com.enterprise.rental.service;

import java.util.List;
import java.util.Map;

public interface Service<T> {
    boolean save(T t);

    List<T> getAll();

    List<T> getAll(String sql);

    List<T> getAll(Map<String, String> params);

    List<T> getAll(Map<String, String> params, int offset);

    List<T> getRandom();

    boolean delete(long id);

    T getById(long id);
}
