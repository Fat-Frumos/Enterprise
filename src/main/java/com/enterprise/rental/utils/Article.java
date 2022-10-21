package com.enterprise.rental.utils;

public class Article{
    int SNO;
    String description;
    int quantity;
    double unitPrice;
    public Article(int SNO, String description, int quantity, double unitPrice)
    {
        this.SNO = SNO;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}