package com.enterprise.rental.entity;

import com.enterprise.rental.security.Token;

public class UserDto {

    private String name;
    private String password;

    private String email;
    private Token token;
    private Role role;
    private boolean active;

    public UserDto(String name, String password, String email, Token token, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.token = token;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
