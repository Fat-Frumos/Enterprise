package com.enterprise.rental.entity;

import com.enterprise.rental.dao.jdbc.builder.Column;
import com.enterprise.rental.dao.jdbc.builder.Entity;
import com.enterprise.rental.dao.jdbc.builder.Table;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Java class that represent a Car,
 * implements {@link Serializable} interface.
 * <p>
 * Annotation for query builder
 *
 * @author Pasha Polyak
 * @see Column
 * @see Table
 * @see Entity
 */
@Entity
@Table(name = "car")
public class Car extends BaseEntity {
    @Column(name = "id", length = 256)
    private Long id;
    @Column(name = "name", length = 256)
    private String name;
    @Column(name = "brand", length = 256)
    private String brand;
    @Column(name = "model", length = 256)
    private String model;
    @Column(name = "path", length = 256)
    private String path;
    @Column(name = "price", length = 256)
    private Double price;
    @Column(name = "cost", length = 256)
    private Double cost;
    @Column(name = "year", length = 256)
    private int year;
    @Column(name = "rent", length = 8)
    private boolean rent;
    @Column(name = "date", length = 256)
    private Timestamp date;
    private User driver;

    public Car() {
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    protected void setBrand(String brand) {
        this.brand = brand;
    }

    public void setId(Long id) {
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

    public User user() {
        return driver;
    }

    public void user(User user) {
        this.driver = user;
    }

    /**
     * The builder pattern creates new entity of User.
     */
    public static class Builder {
        private Long id;
        private String name;
        private String brand;
        private String model;
        private String path;
        private Double price;
        private Double cost;
        private int year;
        private boolean rent;
        private Timestamp date;

        public Builder id(Long id) {
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

        public Builder rent(boolean rent) {
            this.rent = rent;
            return this;
        }

        public Builder date(Timestamp date) {
            this.date = date;
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
            car.rent = this.rent;
            car.date = this.date;
            return car;
        }

        public Builder term(Timestamp timestamp) {
            this.date = timestamp;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && rent == car.rent && Objects.equals(id, car.id) && Objects.equals(name, car.name) && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(path, car.path) && Objects.equals(price, car.price) && Objects.equals(cost, car.cost) && Objects.equals(date, car.date) && Objects.equals(driver, car.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, model, path, price, cost, year, rent, date, driver);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name:'" + name + '\'' +
                ", brand:'" + brand + '\'' +
                ", model:'" + model + '\'' +
                ", path:" + path +
                ", price:" + price +
                ", cost:" + cost +
                '}';
    }
}
