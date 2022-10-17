package com.enterprise.rental.entity;

import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

public class Order {
    private long orderId;
    private long userId;
    private boolean withDriver;
    private boolean rejected;
    private Timestamp start;
    private Timestamp end;
    private int day;
    private String damage;
    private double repairs;
    private boolean closed;
    @OneToMany
    private List<Cars> cars;
}
