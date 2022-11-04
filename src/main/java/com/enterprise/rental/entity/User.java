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
    private boolean closed;
    private Set<Order> orders;
    private List<Car> cars = new ArrayList<>();
    private final Map<String, String> params = new HashMap<>();
    private Car car;

    public User() {
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> getCars() {
        Collections.reverse(cars);
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addParams(Map<String, String> params) {
        params.keySet()
                .stream()
                .filter(key -> params.get(key) != null)
                .forEach(key -> this.params.put(key, params.get(key)));
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
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

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    public User(long userId, String name, String password, String email, String language, String role, boolean active, boolean closed) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.language = language;
        this.role = role;
        this.active = active;
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", closed=" + closed +
                ", params=" + params +
                ", car=" + car +
                '}';
    }
}
