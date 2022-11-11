package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.UserNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserMapper extends Mapper<User> {

    public User mapRow(ResultSet resultSet) {
        try {
            Timestamp created = new Timestamp(System.currentTimeMillis());
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
//            String passport = resultSet.getString("passport");
//            String language = resultSet.getString("language")
//                    != null ? resultSet.getString("language") : "en";
//            String phone = resultSet.getString("phone");
            boolean active = resultSet.getBoolean("active");
            boolean closed = resultSet.getBoolean("closed");
            String role = resultSet.getString("role")
                    != null ? resultSet.getString("role") : "guest";

            return new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
//                    .passport(passport)
//                    .language(language)
                    .email(email)
//                    .phone(phone)
                    .created(created)
                    .active(active)
                    .closed(closed)
                    .role(role)
                    .build();

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