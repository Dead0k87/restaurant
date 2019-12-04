package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class Pepperoni extends Component {

    private Pepperoni() {
        this.name = "Pepperoni";
        this.price = 7;
        this.type = Type.MEAT;
    }

    static public Pepperoni getPepperoni() {
        return new Pepperoni();
    }

}
