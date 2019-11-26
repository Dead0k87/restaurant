package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents;

public class Bacon extends Component {
    private Bacon() {
        this.name = "Bacon";
        this.price = 5;
        this.type = Type.MEAT;
    }

    static public Bacon getBacon() {
        return new Bacon();
    }
}
