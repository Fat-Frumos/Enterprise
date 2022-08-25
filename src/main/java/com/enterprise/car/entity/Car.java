package com.enterprise.car.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Car {

    private Long carId;
    private String name;
    private String brand;
    private String model;
    private int year;
    private Double price;
    private String path;

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", path='" + path + '\'' +
                '}';
    }
}
