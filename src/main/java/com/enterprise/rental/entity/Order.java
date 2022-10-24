package com.enterprise.rental.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {
    private long orderId = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;;
    private long userId;
    private long carId;
    private int day;
    private double payment;
    private boolean driver;
    private boolean rejected;
    private boolean closed;
    private Timestamp created;
    private Timestamp ended;
    private String damage;
    private String passport;

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Order(long carId, long userId, boolean driver) {
        this.carId = carId;
        this.userId = userId;
        this.driver = driver;
    }

    public Order(long orderId, long userId, long carId, int day, double payment, boolean driver, boolean rejected, boolean closed, Timestamp created, Timestamp ended, String damage, String passport) {
        this.orderId = orderId;
        this.userId = userId;
        this.carId = carId;
        this.day = day;
        this.payment = payment;
        this.driver = driver;
        this.rejected = rejected;
        this.closed = closed;
        this.created = created;
        this.ended = ended;
        this.damage = damage;
        this.passport = passport;
    }

    public Order() {
    }

    public int getDay() {
        return day;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isDriver() {
        return driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEnded() {
        return ended;
    }

    public void setEnded(Timestamp ended) {
        this.ended = ended;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return day == order.day && orderId == order.orderId && userId == order.userId && driver == order.driver && rejected == order.rejected && Double.compare(order.payment, payment) == 0 && closed == order.closed && Objects.equals(created, order.created) && Objects.equals(ended, order.ended) && Objects.equals(damage, order.damage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, orderId, userId, driver, rejected, created, ended, damage, payment, closed);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", day=" + day +
                ", payment=" + payment +
                ", driver=" + driver +
                ", rejected=" + rejected +
                ", closed=" + closed +
                ", start=" + created +
                ", end=" + ended +
                ", damage='" + damage + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }

    public static class Builder {
        private long orderId;
        private long userId;
        private long carId;
        private int day;
        private double payment;
        private boolean driver;
        private boolean rejected;
        private boolean closed;
        private Timestamp start;
        private Timestamp end;
        private String damage;
        private String passport;
    }
}
