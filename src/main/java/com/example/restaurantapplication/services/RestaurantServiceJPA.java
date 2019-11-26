package com.example.restaurantapplication.services;

import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.BBQPizza;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.HotRoadPizza;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.ItalianPizza;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.RestaurantOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RestaurantServiceJPA {

    public List<RestaurantOrderItem> getRestaurantMenu() {
        return Arrays.asList(HotRoadPizza.getInstance(), BBQPizza.getInstance(), ItalianPizza.getInstance());
    }


    @Autowired
    private OrderRepository repository;

    public List<RestaurantOrder> findAllOrders() {
        return repository.findAll();
    }

    public List<RestaurantOrder> findAllOrdersByWaiterName(String waiterName) {
        return repository.findByWaiterName(waiterName);
    }


    public RestaurantOrder saveOrder(RestaurantOrder order) {
        return repository.save(order);
    }

    public RestaurantOrder getOrderByID(long id) {
        return repository.getById(id);
    }

    public void deleteOrderByID(long id) {
        repository.deleteById(id);
    }


}
