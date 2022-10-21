package com.enterprise.rental.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Order {
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


    public Order(int carId, long userId, boolean driver) {
        this.carId = carId;
        this.userId = userId;
        this.driver = driver;
    }

    public Order() {
    }

    public int getDay() {
        return day;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
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
        return day == order.day && orderId == order.orderId && userId == order.userId && driver == order.driver && rejected == order.rejected && Double.compare(order.payment, payment) == 0 && closed == order.closed && Objects.equals(start, order.start) && Objects.equals(end, order.end) && Objects.equals(damage, order.damage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, orderId, userId, driver, rejected, start, end, damage, payment, closed);
    }

    public static class Builder {

    }
}
