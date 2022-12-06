package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMapper extends Mapper<User> {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    public User mapRow(ResultSet resultSet) {
        try {
            Timestamp created = new Timestamp(System.currentTimeMillis());
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String salt = resultSet.getString("salt");
            String password = resultSet.getString("password");
            String passport = resultSet.getString("passport");
            String phone = resultSet.getString("phone");
            boolean active = resultSet.getBoolean("active");
            boolean closed = resultSet.getBoolean("closed");
            String language = resultSet.getString("language");

            String role = resultSet.getString("role")
                    != null ? resultSet.getString("role") : "guest";

            log.debug(String.format("language:%s, password:%s, salt:%s", language, password, salt));

            return new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
                    .language(language)
                    .email(email)
                    .salt(salt)
                    .passport(passport)
                    .phone(phone)
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