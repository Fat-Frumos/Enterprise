package com.enterprise.rental.entity;

import java.util.Map;

public class Session {

    private String token;
    private boolean expired;
    private User user;
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

    public Map<User, String> getUserTokens() {
        return userTokens;
    }

    public void setUserTokens(Map<User, String> userTokens) {
        this.userTokens = userTokens;
    }

    public Session(String token, boolean expired, User user, Map<User, String> userTokens) {
        this.token = token;
        this.expired = expired;
        this.user = user;
        this.userTokens = userTokens;
    }

    public Session() {
    }
}