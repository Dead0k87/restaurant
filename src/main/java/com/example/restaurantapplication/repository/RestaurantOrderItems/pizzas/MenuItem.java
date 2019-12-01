package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas;

import com.example.restaurantapplication.repository.RestaurantOrder;

import javax.persistence.*;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    protected Long id;
    protected String UID;
    protected String name;
    protected String description;
    protected double price;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @ManyToOne
    private RestaurantOrder order; // order is a cell in table too

    public MenuItem() {
    }

    public MenuItem(String UID, String name, String description, ItemType itemType, double price) {
        this.UID = UID;
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.price = price;
    }

    public MenuItem(MenuItem menuItem) {
        if (menuItem != null) {
            this.UID = menuItem.UID;
            this.name = menuItem.name;
            this.description = menuItem.description;
            this.itemType = menuItem.itemType;
            this.price = menuItem.price;
        }
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantOrder getOrder() {
        return order;
    }

    public void setOrder(RestaurantOrder order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " (" + description + ")";
    }
}
