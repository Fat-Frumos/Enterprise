package com.enterprise.user.dao;

import com.enterprise.user.entity.User;
import com.enterprise.user.entity.UserDto;
public class UserMapper {
    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setLogin(user.getName());
        dto.setPassword(user.getPassword());
        dto.setActive(user.getDeactivationDate() == null);
        return dto;
    }
}
