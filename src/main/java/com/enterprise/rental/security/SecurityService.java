package com.enterprise.rental.security;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.service.UserService;

import java.util.Objects;

public class SecurityService {
    private final UserService userService;


    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    public boolean checkUser(String name, String password) {

        User user = userService.findByName(name).orElse(null);
        if (Objects.requireNonNull(user).getPassword().equals(password) && user.getName().equals(name)) {
            return true;
        }
        return false;
    }
}
