package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas;

import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents.*;

import java.util.Arrays;
import java.util.List;

public class ItalianPizza extends Pizza {

    public static ItalianPizza getInstance() {
        return new ItalianPizza();
    }

    private ItalianPizza() {
        this.id="ITLP";
        this.name = "ITALIAN PIZZA";
        this.components = getComponents();
        this.price = getPrice();

    }

    protected List<Component> getComponents() {
        return Arrays.asList(
                TomatoSauce.getTomatoSauce(),
                Mozzarella.getMozzarella(),
                BeefCarpaccio.getBeefCarpaccio(),
                Oregano.getOregano()
        );
    }
}
