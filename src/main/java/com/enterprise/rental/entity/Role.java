package com.enterprise.rental.entity;

/**
 * The enum Role represent the {@link User} role.
 */
public enum Role {
    /**
     * Admin role.
     */
    ADMIN("admin"),
    /**
     * Manager role.
     */
    MANAGER("manager"),
    /**
     * User role.
     */
    USER("user"),

    /**
     * Guest role.
     */
    GUEST("guest");

    private final String role;

    /**
     * Default constructor with the role field
     *
     * @param role the role to set
     */
    Role(String role) {
        this.role = role;
    }

    /**
     * Getter the role field
     *
     * @return the role
     */
    public String role() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}




