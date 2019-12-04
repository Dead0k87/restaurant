package com.example.restaurantapplication.repository.RestaurantOrderItems.pizzacomponents;

public class CarmelOnion extends Component {

    private CarmelOnion() {
        this.name = "Carmel onion";
        this.price = 6;
        this.type = Type.VEGETABLES;
    }

    static  public CarmelOnion getCarmelOnion() {
        return new CarmelOnion();
    }

}
