package com.enterprise.rental.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class Mapper<T> {
    abstract T mapRow(ResultSet resultSet);

    public Set<T> getAll(
            ResultSet resultSet,
            Set<T> elements)
            throws SQLException {

        if (!resultSet.next()) {
            return elements;
        } else {
            T t = mapRow(resultSet);
            elements.add(t);
            return getAll(resultSet, elements);
        }
    }
}
