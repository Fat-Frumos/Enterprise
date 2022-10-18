package com.enterprise.rental.entity;

import java.sql.Timestamp;


public class Order {

    private int day;
    private long orderId;
    private long userId;
    private boolean withDriver;
    private boolean rejected;
    private Timestamp start;
    private Timestamp end;
    private String damage;
    private double repairs;
    private boolean closed;
}
