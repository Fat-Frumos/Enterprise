package com.enterprise.rental.entity;

import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

public class Order {
    private long id;
    private long userId;
    private long carId;
    private boolean driver;
    private Timestamp start;
    private Timestamp end;
    @OneToMany
    private List<Cars> cars;
}
