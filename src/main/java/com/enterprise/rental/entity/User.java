package com.enterprise.rental.entity;

import com.enterprise.rental.security.Token;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private long userId;
    private String name;
    private String password;
    private String email;
    private Token token;

    private String language;
    private Role role;

    @OneToMany
    private List<Order> orders;
    private boolean active;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User(long userId, String name, String password, String email, String language, boolean active) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.language = language;
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", role=" + role +
                ", active=" + active +
                '}';
    }
}
