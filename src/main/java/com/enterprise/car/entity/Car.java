package com.enterprise.car.dao.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Car {

    private Long carId;
    private String name;
    private final String brand;
    private String model;
    private Double price;

    public Car(Long carId, String name, String brand, String model, Double price) {
        this.carId = carId;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Car(String brand) {
        this.brand = brand;
    }
}
