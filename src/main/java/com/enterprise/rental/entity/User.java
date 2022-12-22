package com.enterprise.rental.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Java class that represent a User,
 * implements {@link Serializable} interface.
 *
 * @author Pasha Pollack
 */
public class User implements Serializable {
    private long userId;
    private String name;
    private String password;
    private String salt;
    private String email;
    private String language;
    private String role;
    private String passport;
    private String phone;
    private boolean active;
    private boolean closed;
    private Set<Order> orders;
    private List<Car> cars = new ArrayList<>();
    private final Map<String, String> params = new HashMap<>();
    private Car car;
    private Timestamp created;

    /**
     * The builder pattern creates new entity of {@link User}.
     * <p>
     * The effect of {@code Builder} is that an inner class is generated named
     * <code><strong>User</strong>Builder</code>, with a private constructor.
     * <p>
     * Instances of <code><strong>User</strong>Builder</code>
     * are made with the method named {@code builder()} which is also generated
     * for you in the class itself (not in the builder class).
     * <p>
     * The return type of this method will be the same as the relevant class
     */
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
        private String salt;

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

        public Builder phone(String phone) {
            this.phone = phone;
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

        public Builder salt(String salt) {
            this.salt = salt;
            return this;
        }

        public User build() {
            User user = new User();
            user.userId = this.userId;
            user.name = this.name;
            user.language = this.language;
            user.active = this.active;
            user.closed = this.closed;
            user.created = this.created;
            user.email = this.email;
            user.passport = this.passport;
            user.password = this.password;
            user.salt = this.salt;
            user.phone = this.phone;
            user.role = this.role;
            return user;
        }
    }

    /**
     * Default constructor
     */
    protected User() {
    }

    /**
     * Getter the passport field
     *
     * @return the passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Setter the passport field
     *
     * @param passport the passport to set
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Getter the phone field
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter the phone field
     *
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Setter the name field
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter the name field
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter the email field
     *
     * @param email the User email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter the email field
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter the password field
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter the password field
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter the salt field
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Setter the salt field
     *
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Check if user admin
     *
     * @return the boolean if user is admin
     */
    public boolean isAdmin() {
        return role.equals("admin");
    }

    /**
     * Add new Car to profile
     *
     * @param car the Car added to Card
     */
    public void addCar(Car car) {
        cars.add(car);
    }

    /**
     * Getter the cars field
     *
     * @return the List of Cars
     */
    public List<Car> getCars() {
        return cars;
    }

    /**
     * Setter the cars field
     *
     * @param cars the list of cars to set
     */
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    /**
     * Add Params to profile
     *
     * @param params the Map (String, String)
     */
    public void addParams(Map<String, String> params) {
        params.keySet()
                .stream()
                .filter(key -> params.get(key) != null)
                .forEach(key -> this.params.put(key, params.get(key)));
    }

    /**
     * Getter the language field
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Setter the language field
     *
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Getter the closed field
     *
     * @return if the user is blocked
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Setter the closed field
     *
     * @param closed the closed to set
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * Getter the active field
     *
     * @return true if user is Active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Setter the active status
     *
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Getter the User role field
     *
     * @return the User role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter the User role
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Setter the Car field
     *
     * @param car the car to set in profile
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Getter the Car field
     *
     * @return the profile
     */
    public Car getCar() {
        return car;
    }

    /**
     * Getter the user ID
     *
     * @return the user ID
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Getter the Params query
     *
     * @return params the Map (String, String)
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * Getter the orders
     *
     * @return the Set of orders
     */
    public Set<Order> getOrders() {
        return orders;
    }

    /**
     * Setter the orders
     *
     * @param orders the Map of {@link Set} (Orders)
     */
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    /**
     * Returns {@code true} if the arguments are equal to each other
     * and {@code false} otherwise.
     *
     * @param o an object
     * @return the hash code of a non-{@code null} argument and 0 for
     * a {@code null} argument
     * @see Object#hashCode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && active == user.active && closed == user.closed && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(salt, user.salt) && Objects.equals(email, user.email) && Objects.equals(language, user.language) && Objects.equals(role, user.role) && Objects.equals(orders, user.orders) && Objects.equals(cars, user.cars) && Objects.equals(params, user.params) && Objects.equals(car, user.car) && Objects.equals(created, user.created) && Objects.equals(passport, user.passport) && Objects.equals(phone, user.phone);
    }

    /**
     * <p>This method is useful for implementing {@link Object#hashCode()}
     * on objects containing multiple fields.
     *
     * @return the hash code of a non-{@code null} argument
     * and 0 for a {@code null} argument
     */
    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, salt, email, language, role, active, closed, orders, cars, params, car, created, passport, phone);
    }

    /**
     * Returns a string representation of the object.
     * <p>
     * The {@code toString} method returns a result that should be
     * an informative representation that is easy for to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", closed=" + closed +
                ", phone='" + phone + '\'' +
                '}';
    }
}
