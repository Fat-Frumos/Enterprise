package com.enterprise.rental.entity;

public class UserDto {
    private String name;
    private String password;
    private String email;
    private Role user;

    public UserDto(String name, String password, String email, Role user) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.user = user;
    }

    public UserDto toDto(User user) {
        return new UserDto(
                user.getName(),
                user.getPassword(),
                user.getEmail(),
                Role.USER);
    }
}
