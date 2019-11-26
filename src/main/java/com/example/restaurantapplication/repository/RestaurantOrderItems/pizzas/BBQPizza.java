package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas;

import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents.*;

import java.util.Arrays;
import java.util.List;

public class BBQPizza extends Pizza {
    public static BBQPizza getInstance() {
        return new BBQPizza();
    }

    private BBQPizza() {
        this.id="BBQP";
        this.name = "BBQ PIZZA";
        this.components = getComponents();
        this.price = getPrice();

    }

    protected List<Component> getComponents() {
        return Arrays.asList(
                BBQSauce.getBBQSauce(),
                Mozzarella.getMozzarella(),
                Bacon.getBacon(),
                SmokedChicken.getSmokedChicken(),
                CarmelOnion.getCarmelOnion()
        );
    }


}
