package com.example.restaurantapplication.services;

import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.PizzaRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class RestaurantServiceJPA {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getRestaurantMenu() {
        return pizzaRepository.findAll();
    }


    public List<RestaurantOrder> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<RestaurantOrder> findAllOrdersByWaiterName(String waiterName) {
        return orderRepository.findByWaiterName(waiterName);
    }

    public RestaurantOrder saveOrder(RestaurantOrder order) {
        return orderRepository.save(order);
    }

    public RestaurantOrder getOrderByID(long id) {
        return orderRepository.getById(id);
    }

    public void deleteOrderByID(long id) {
        orderRepository.deleteById(id);
    }


}
