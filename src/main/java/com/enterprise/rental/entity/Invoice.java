package com.enterprise.rental.entity;

import java.util.Objects;
import java.util.UUID;

public class Invoice {
    private final long invoiceId = UUID.randomUUID().getMostSignificantBits() & 0x7fffffL;
    private final long userId;
    private final long carId;
    private final String damage;
    private final double payment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return invoiceId == invoice.invoiceId && userId == invoice.userId && carId == invoice.carId && Double.compare(invoice.payment, payment) == 0 && Objects.equals(damage, invoice.damage);
    }

    public Invoice(long userId, long carId, String damage, double payment) {
        this.userId = userId;
        this.carId = carId;
        this.damage = damage;
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", damage='" + damage + '\'' +
                ", payment=" + payment +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, userId, carId, damage, payment);
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

    public double getPayment() {
        return payment;
    }
}
