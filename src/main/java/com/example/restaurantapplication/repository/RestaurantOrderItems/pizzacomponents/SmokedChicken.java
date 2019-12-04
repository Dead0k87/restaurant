package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class SmokedChicken extends Component {
    private SmokedChicken() {
        this.name = "Smocked chicken";
        this.price = 6;
        this.type = Type.MEAT;
    }

    static  public SmokedChicken getSmokedChicken() {
        return new SmokedChicken();
    }
}
