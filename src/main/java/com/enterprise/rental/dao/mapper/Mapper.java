package com.enterprise.rental.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Mapper<T> {
    abstract T mapRow(ResultSet resultSet) throws SQLException;

    public List<T> getAll(ResultSet resultSet, List<T> elements) throws SQLException {
        if (!resultSet.next()) {
            return elements;
        }
        T t = mapRow(resultSet);
        elements.add(t);
        return getAll(resultSet, elements);
    }
}
