package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.entity.UserDto;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper extends Mapper<User> {
    private static final Logger log = Logger.getLogger(UserMapper.class);

    public User mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        log.info(String.format("id: %d, name: %s, pwd: %s, email: %s", id, name, password, email));
        return new User(id, name, password, email, "ua", true);
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                user.getToken(),
                user.getRole());
    }
}