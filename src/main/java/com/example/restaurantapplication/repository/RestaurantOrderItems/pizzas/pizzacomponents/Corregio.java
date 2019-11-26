package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents;

public class Corregio extends Component {

    private Corregio() {
        this.name = "Corregio";
        this.price = 6;
        this.type = Type.CHEESE;
    }

    static public Corregio getCorregio() {
        return new Corregio();
    }

}
