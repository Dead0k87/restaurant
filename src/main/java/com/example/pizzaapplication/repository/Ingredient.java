package com.example.pizzaapplication.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String productType;
    private int price;

    public Ingredient() {
    }

    public Ingredient(String name, String type, int price) {
        this.name = name;
        this.productType = productType;
        this.price = price;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

//    enum IngredientType {
//        MEAT, FRUITS, VEGETABLES, DRINKS, CHEESE, SAUCE
//    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", productType='" + productType + '\'' +
                ", price=" + price +
                '}';
    }
}
