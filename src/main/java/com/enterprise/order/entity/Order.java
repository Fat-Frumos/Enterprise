package com.enterprise.order.entity;

import java.sql.Timestamp;

public class Order {
    private long id;
    private long userId;
    private long carId;
    private boolean driver;
    private Timestamp start;
    private Timestamp end;
}
