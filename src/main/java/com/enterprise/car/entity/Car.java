package com.enterprise.car.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Car {

    private long id;
    private String name;
    private String brand;
    private String model;
    private int year;
    private Double price;

    private String path;

    private LocalDateTime date;

    @Override
    public String toString() {
        return "{ \"car\": {" +
                "\"id\":" + id +
                ", \"name\":\"" + name + "\"" +
                ", \"brand\":\"" + brand + "\"" +
                ", \"model\":\"" + model + "\"" +
                ", \"year\":\"" + year + "\"" +
                ", \"price\":\"" + price + "\"" +
                "}";
    }
}
