package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents;

public abstract class Component {
    protected String name;
    protected double price;
    protected Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    enum Type {
        SAUCE, CHEESE, VEGETABLES, FRUITS, MEAT
    }

    @Override
    public String toString() {
        return name;
    }
}
