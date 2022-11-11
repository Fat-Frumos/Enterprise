package com.enterprise.rental.entity;

import java.io.Serializable;
import java.sql.Timestamp;
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
    private Timestamp created;
    private String passport;
    private String phone;

    public static class Builder {
        private long userId;
        private String name;
        private String email;
        private String language;
        private String phone;
        private boolean active;
        private boolean closed;
        private Timestamp created;
        private String passport;
        private String role;
        private String password;

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder closed(boolean closed) {
            this.closed = closed;
            return this;
        }

        public Builder created(Timestamp created) {
            this.created = created;
            return this;
        }

        public Builder passport(String passport) {
            this.passport = passport;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String name) {
            this.name = name;
            return this;
        }

        public Builder active(boolean active) {
            this.active = active;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            User user = new User();
            user.userId = this.userId;
            user.name = this.name;
            user.email = this.email;
            user.language = this.language;
            user.active = this.active;
            user.closed = this.closed;
            user.created = this.created;
            user.email = this.email;
            user.passport = this.passport;
            user.password = this.password;
            user.phone = this.phone;
            user.role = this.role;
            return user;
        }
    }


    public User() {
    }

    public boolean isAdmin() {
        return role.equals("admin");
    }

    public void addCar(Car car) {
        cars.add(car);
    }


    public List<Car> getCars() {
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
        return userId == user.userId && active == user.active && closed == user.closed && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(language, user.language) && Objects.equals(role, user.role) && Objects.equals(orders, user.orders) && Objects.equals(cars, user.cars) && Objects.equals(params, user.params) && Objects.equals(car, user.car) && Objects.equals(created, user.created) && Objects.equals(passport, user.passport) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, email, language, role, active, closed, orders, cars, params, car, created, passport, phone);
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
                ", cars=" + cars +
                ", params=" + params +
                ", passport='" + passport + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
