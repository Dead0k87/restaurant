package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class Mozzarella extends Component {

    private Mozzarella() {
        this.name = "Mozzarella";
        this.price = 5;
        this.type = Type.CHEESE;
    }

    static public Mozzarella getMozzarella() {
        return new Mozzarella();
    }

}
