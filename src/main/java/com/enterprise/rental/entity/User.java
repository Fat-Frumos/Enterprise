package com.enterprise.rental.entity;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private long userId;
    private String name;
    private String password;
    private String email;
    private String language;
    private String role;
    private boolean active;
    private Set<Order> orders;
    private Set<Car> cars = new HashSet<>();
    private Map<String, String> params = new HashMap<>();

    public User() {
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addParams(Map<String, String> params) {
        params.keySet()
                .stream()
                .filter(key -> params.get(key) != null)
                .forEach(key -> this.params.put(key, params.get(key)));
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public long getUserId() {
        return userId;
    }

    public boolean isActive() {
        return active;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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

    public User(long userId, String name, String password, String email, String language, String role, boolean active) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.language = language;
        this.role = role;
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", orders=" + orders +
                '}';
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
