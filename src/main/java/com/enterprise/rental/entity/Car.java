package com.enterprise.rental.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Car implements Serializable {
    private long id;
    private String name;
    private String brand;
    private String model;
    private String path;
    private Double price;
    private Double cost;
    private int year;
    private boolean rent;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPath() {
        return path;
    }

    public Double getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    protected void setBrand(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static class Builder {
        private long id;
        private String name;
        private String brand;
        private String model;
        private String path;
        private Double price;
        private Double cost;
        private int year;
        private boolean rent;
        private LocalDateTime created;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder cost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder year(int year) {
            this.year = year;
            return this;
        }

        public Builder created(LocalDateTime time) {
            this.created = time;
            return this;
        }
        public Builder rent(boolean rent) {
            this.rent = rent;
            return this;
        }

        public Car build() {
            Car car = new Car();
            car.id = this.id;
            car.name = this.name;
            car.brand = this.brand;
            car.model = this.model;
            car.path = this.path;
            car.price = this.price;
            car.cost = this.cost;
            car.year = this.year;
            return car;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return id == car.id && year == car.year && Objects.equals(name, car.name) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(price, car.price) && Objects.equals(cost, car.cost) && Objects.equals(path, car.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, model, year, price, cost, path);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", cost=" + cost +
                ", rent=" + rent +
                '}';
    }
}
