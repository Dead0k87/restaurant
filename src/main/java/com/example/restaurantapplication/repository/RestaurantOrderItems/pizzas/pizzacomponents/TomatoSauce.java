package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.pizzacomponents;

public class TomatoSauce extends Component {
    private TomatoSauce() {
        this.name = "Tomato sauce";
        this.price = 2;
        this.type = Type.SAUCE;
    }

    static public TomatoSauce getTomatoSauce() {
        return new TomatoSauce();
    }
}
