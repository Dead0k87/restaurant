package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents;

public class Jalapeno extends Component {

    private Jalapeno() {
        this.name = "Jalapeño";
        this.price = 5;
        this.type = Type.VEGETABLES;
    }

    static public Jalapeno getJalapeno() {
        return new Jalapeno();
    }

}
