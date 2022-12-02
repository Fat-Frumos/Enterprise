package com.enterprise.rental.entity;

public enum Role {

    ADMIN("admin"),
    MANAGER("manager"),
    USER("user"),
    GUEST("guest");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String role() {
        return role;
    }
}
