package com.enterprise.rental.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class User implements Serializable {
    private long userId;
    private String name;
    private String password;
    private String email;
    private String language;
    private String role;
    private boolean active;
    private Set<Order> orders;

    private Set<Car> cars;

    public User(Set<Car> cars) {
        this.cars = cars;
    }

    public User() {
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && active == user.active && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(language, user.language) && Objects.equals(role, user.role) && Objects.equals(orders, user.orders) && Objects.equals(cars, user.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, email, language, role, active, orders, cars);
    }

    public User(long userId, String name, String password, String email, String language, boolean active) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.language = language;
        this.active = active;
    }

    public User(long userId, String name, String password, String email, String role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
