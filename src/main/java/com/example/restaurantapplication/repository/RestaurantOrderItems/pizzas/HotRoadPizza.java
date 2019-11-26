package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas;

import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents.*;

import java.util.Arrays;
import java.util.List;

public class HotRoadPizza extends Pizza {

    public static HotRoadPizza getInstance() {
        return new HotRoadPizza();
    }

    private HotRoadPizza() {
        this.id="HTRP";
        this.name = "HOT ROAD PIZZA";
        this.components = getComponents();
        this.price = getPrice();

    }

    protected List<Component> getComponents() {
        return Arrays.asList(
                Mozzarella.getMozzarella(),
                Pepperoni.getPepperoni(),
                CarmelOnion.getCarmelOnion(),
                Jalapeno.getJalapeno(),
                TomatoSauce.getTomatoSauce()
        );
    }
}
