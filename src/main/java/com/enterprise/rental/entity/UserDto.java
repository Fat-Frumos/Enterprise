package com.enterprise.rental.entity;

public class UserDto {

    private String name;
    private String password;
    private Role role;
    private boolean active;

//    public UserDto(User user) {
//        this.setToken(user.getToken());
//        this.setLogin(user.getLogin());
//        this.setLogin(user.getLogin());
//        this.setActive(user.getDeactivationDate() == null);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
