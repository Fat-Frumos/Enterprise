package com.enterprise.rental.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
/**
 * Java class that represent an Invoice,
 * implements {@link Serializable} interface.
 *
 * @author Pasha Polyak
 */
public class Invoice extends BaseEntity {
    private final Long invoiceId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
    private final Long userId;
    private final Long carId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.payment, payment) == 0 && Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(userId, invoice.userId) && Objects.equals(carId, invoice.carId) && Objects.equals(damage, invoice.damage) && Objects.equals(passport, invoice.passport) && Objects.equals(phone, invoice.phone) && Objects.equals(reason, invoice.reason) && Objects.equals(email, invoice.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, userId, carId, damage, passport, phone, payment, reason, email);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", userId=" + userId +
                ", damage='" + damage + '\'' +
                ", payment=" + payment +
                ", reason='" + reason + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
