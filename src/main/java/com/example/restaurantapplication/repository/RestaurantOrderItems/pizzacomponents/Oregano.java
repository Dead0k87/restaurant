package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class Oregano extends Component {

    private Oregano() {
        this.name = "Oregano";
        this.price = 3;
        this.type = Type.VEGETABLES;
    }

    static  public Oregano getOregano() {
        return new Oregano();
    }

}
