package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class BBQSauce extends Component {
    private BBQSauce() {
        this.name = "BBQ sauce";
        this.price = 4;
        this.type = Type.SAUCE;
    }

    static public BBQSauce getBBQSauce() {
        return new BBQSauce();
    }
}
