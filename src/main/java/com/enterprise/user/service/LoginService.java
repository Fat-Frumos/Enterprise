package com.enterprise.user.service;

public class LoginService {
    public boolean validateUser(String user, String password) {
        return user.equalsIgnoreCase("admin") && password.equals("admin");
    }

}