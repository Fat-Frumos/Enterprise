package com.enterprise.user.entity;

public class UserDto {

    private String login;
    private String password;
    private Token token;
    private boolean active;

//    public UserDto(User user) {
//        this.setToken(user.getToken());
//        this.setLogin(user.getLogin());
//        this.setLogin(user.getLogin());
//        this.setActive(user.getDeactivationDate() == null);
//    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
