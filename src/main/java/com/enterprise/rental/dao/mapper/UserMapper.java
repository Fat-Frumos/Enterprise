package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.dao.jdbc.JdbcUserTemplate;
import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.entity.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class UserMapper extends Mapper<User> {
    private static final Logger log = Logger.getLogger(UserMapper.class.getName());

    public User mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("nickname");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");
        log.info(String.format("id: %d, name: %s, pwd: %s, email: %s", id, name, password, email));
        return new User(id, name, password, email);
    }

//    public UserDto toDto(User user) {
//        UserDto dto = new UserDto();
//        dto.setLogin(user.getName());
//        dto.setPassword(user.getPassword());
//        dto.setActive(user.getDeactivationDate() == null);
//        return dto;
//    }

}
