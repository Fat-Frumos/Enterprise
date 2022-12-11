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

    public Optional<User> userMapper(HttpServletRequest request) {
        String[] fields = {"userId", "name", "password", "passport", "phone", "language", "email", "created"};

        Map<String, String> params = Arrays.stream(fields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(
                        key -> key, request::getParameter, (a, b) -> b));

        long id = Long.parseLong(params.get(fields[0]));
        String name = params.get(fields[1]);
        String password = params.get(fields[2]);
        String passport = params.get(fields[3]);
        String phone = params.get(fields[4]);
        String language = params.get(fields[5]);
        String email = params.get(fields[6]);
        Timestamp createTimestamp = Timestamp.valueOf(params.get(fields[7]));


        User user = new User.Builder()
                .userId(id)
                .name(name)
                .password(password)
                .passport(passport)
                .phone(phone)
                .language(language)
                .email(email)
                .created(createTimestamp)
                .active(true)
                .closed(false)
                .build();

        log.info(user);
        return Optional.of(user);
    }
}