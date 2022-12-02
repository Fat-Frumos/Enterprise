package com.enterprise.rental.entity;

import java.io.Serializable;
import java.util.UUID;

public class Invoice implements Serializable {
    private final long invoiceId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
    private final long userId;
    private final long carId;
    private final String damage;
    private final String passport;
    private final String phone;
    private final double payment;
    private final String reason;
    private final String email;

    public Invoice(long userId, long carId, String damage, String passport, String phone, String reason, String email, double payment) {
        this.userId = userId;
        this.carId = carId;
        this.damage = damage;
        this.passport = passport;
        this.phone = phone;
        this.payment = payment;
        this.reason = reason;
        this.email = email;
    }

    public String getReason() {
        return reason;
    }

    public String getEmail() {
        return email;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public long getUserId() {
        return userId;
    }

    public long getCarId() {
        return carId;
    }

    public String getDamage() {
        return damage;
    }

    public String getPassport() {
        return passport;
    }

    public String getPhone() {
        return phone;
    }

    public double getPayment() {
        return payment;
    }
}
