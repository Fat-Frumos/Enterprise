package com.enterprise.rental.entity;

import java.util.List;
import java.util.Map;

public class Session {

    private String token;
    private boolean expired;
    private User user;
    private List<Car> CarList;
    private Map<User, String> userTokens;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Car> getCarList() {
        return CarList;
    }

    public void setCarList(List<Car> carList) {
        CarList = carList;
    }

    public Map<User, String> getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(Map<User, String> userTokens) {
        this.userTokens = userTokens;
    }
}