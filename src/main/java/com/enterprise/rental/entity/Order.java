package com.enterprise.rental.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Order implements Serializable {
    private long orderId = UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;
    private long userId;
    private long carId;
    private Timestamp term;
    private double payment;
    private boolean driver;
    private boolean rejected;
    private boolean closed;
    private Timestamp created;
    private String card;
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

    public Order(long orderId, long userId, long carId, Timestamp term, double payment, boolean driver, boolean rejected, boolean closed, Timestamp created, String card, String damage, String passport) {
        this.orderId = orderId;
        this.userId = userId;
        this.carId = carId;
        this.term = term;
        this.payment = payment;
        this.driver = driver;
        this.rejected = rejected;
        this.closed = closed;
        this.created = created;
        this.card = card;
        this.damage = damage;
        this.passport = passport;
    }

    public Order() {
    }

    public Timestamp getTerm() {
        return term;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public void setTerm(Timestamp term) {
        this.term = term;
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

    public String getPhone() {
        return card;
    }

    public void setPhone(String card) {
        this.card = card;
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
        return term == order.term && orderId == order.orderId && userId == order.userId && driver == order.driver && rejected == order.rejected && Double.compare(order.payment, payment) == 0 && closed == order.closed && Objects.equals(created, order.created) && Objects.equals(card, order.card) && Objects.equals(damage, order.damage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(term, orderId, userId, driver, rejected, created, card, damage, payment, closed);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", term=" + term +
                ", payment=" + payment +
                ", driver=" + driver +
                ", rejected=" + rejected +
                ", closed=" + closed +
                ", start=" + created +
                ", phone=" + card +
                ", damage='" + damage + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }

    public void setOrderId(long id) {
        this.orderId = id;
    }

    public static class Builder {
        private long orderId;
        private long userId;
        private long carId;
        private Timestamp term;
        private double payment;
        private boolean driver;
        private boolean rejected;
        private boolean closed;
        private Timestamp created;
        private String card;
        private String damage;
        private String passport;

        public Builder orderId(long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder carId(long carId) {
            this.carId = carId;
            return this;
        }

        public Builder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public Builder term(Timestamp term) {
            this.term = term;
            return this;
        }

        public Builder payment(Double payment) {
            this.payment = payment;
            return this;
        }

        public Builder driver(boolean driver) {
            this.driver = driver;
            return this;
        }

        public Builder rejected(boolean rejected) {
            this.rejected = rejected;
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

        public Builder damage(String damage) {
            this.damage = damage;
            return this;
        }

        public Builder card(String card) {
            this.card = card;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.userId = this.userId;
            order.carId = this.carId;
            order.orderId = this.orderId;
            order.term = this.term;
            order.payment = this.payment;
            order.driver = this.driver;
            order.rejected = this.rejected;
            order.closed = this.closed;
            order.created = this.created;
            order.card = this.card;
            order.damage = this.damage;
            order.passport = this.passport;
            return order;
        }
    }
}
