package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas;

import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents.Component;

import java.util.List;

public abstract class Pizza extends RestaurantOrderItem {
    protected String id;
    protected String name;
    protected List<Component> components;
    protected double price;
    protected double basePrice = 10d;

    public Pizza() {
    }

    protected List<Component> getComponents() {
        return components;
    }

    protected double getPrice() {
        return basePrice + getComponents().stream().mapToDouble(Component::getPrice).sum();
    }

    @Override
    public String toString() {
        return name + "\t " + components;
    }
}
