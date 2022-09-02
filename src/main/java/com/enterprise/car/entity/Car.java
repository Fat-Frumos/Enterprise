package com.enterprise.car.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Car {
    private long id;
    private String name;
    private String brand;
    private String model;
    private String path;
    private Double price;
    private int year;
    private LocalDateTime date = LocalDateTime.now();

    public Car() {

    }

    public Double getPrice() {
        return price;
    }

    public Car(long id, String name, String brand, String model, String path, Double price, int year) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.path = path;
        this.price = price;
        this.year = year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && Objects.equals(name, car.name) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(price, car.price) && Objects.equals(path, car.path) && Objects.equals(date, car.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, model, year, price, path, date);
    }

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
