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
            String language = resultSet.getString("language");
            log.info(language);
//            String passport = resultSet.getString("passport");
//            String phone = resultSet.getString("phone");
            boolean active = resultSet.getBoolean("active");
            boolean closed = resultSet.getBoolean("closed");
            String role = resultSet.getString("role")
                    != null ? resultSet.getString("role") : "guest";

            return new User.Builder()
                    .userId(id)
                    .name(name)
                    .password(password)
                    .language(language)
                    .email(email)
                    .salt(salt)
//                    .passport(passport)
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

    public Optional<User> userMapper(HttpServletRequest request) {

        String[] userFields = {"id", "name", "password", "email", "salt", "language", "role", "active", "closed"};

        Map<String, String> user = Arrays.stream(userFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(key -> key, request::getParameter, (a, b) -> b));

        return Optional.of(new User.Builder()
                .userId(Long.parseLong(user.get(userFields[0])))
                .name(user.get(userFields[1]))
                .password(user.get(userFields[2]))
                .email(user.get(userFields[3]))
                .salt(user.get(userFields[4]))
                .language(user.get(userFields[5]))
                .role(user.get(userFields[6]))
                .active(true)
                .build()
        );
    }
}