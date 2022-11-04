package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends Mapper<User> {

    public User mapRow(ResultSet resultSet) {
        try {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            boolean status = resultSet.getBoolean("active");
            boolean closed = resultSet.getBoolean("closed");
            String role = resultSet.getString("role") != null ? resultSet.getString("role") : "guest";
            return new User(id, name, password, email, "en", role, status, closed);
        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException(e.getMessage(), exception);
            }
            throw new UserNotFoundException("User not found", exception);
        }
    }
}