package com.enterprise.rental.entity;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class User implements Serializable {
    private long userId;
    private String name;
    private String password;
    private String email;
    private Role role;

    @OneToMany
    private List<Order> orders;


    private LocalDateTime deactivateDate;
    private final boolean active = false;

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

    public Role getToken() {
        return role;
    }

    public void setToken(Role role) {
        this.role = role;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivateDate;
    }

    public void setDeactivateDate(LocalDateTime deactivateDate) {
        this.deactivateDate = deactivateDate;
    }

    public User(long userId, String name, String password, String email) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
