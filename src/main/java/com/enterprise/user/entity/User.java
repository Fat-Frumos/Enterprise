package com.enterprise.user.entity;

import java.time.LocalDateTime;

public class User {
    private String name;
    private String email;
    private String password;
    private Token token;
    private LocalDateTime deactivateDate;
    private final boolean active = false;

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LocalDateTime getDeactivationDate() {
        return deactivateDate;
    }

    public void setDeactivateDate(LocalDateTime deactivateDate) {
        this.deactivateDate = deactivateDate;
    }
}
