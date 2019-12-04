package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class BeefCarpaccio extends Component {
    private BeefCarpaccio() {
        this.name = "Beef carpaccio";
        this.price = 8;
        this.type = Type.MEAT;
    }

    static public BeefCarpaccio getBeefCarpaccio() {
        return new BeefCarpaccio();
    }
}
