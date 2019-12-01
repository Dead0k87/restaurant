package com.example.restaurantapplication.services;

import com.example.restaurantapplication.repository.MenuItemsRepository;
import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.MenuItem;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.MenuItemsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional // spring transactional better transaction btwn different database(isolation = )
public class RestaurantServiceJPA {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private MenuItemsFactory menuItemsFactory;

    public List<MenuItem> getRestaurantMenu() {
        // return pizzaRepository.findAll();
        return menuItemsFactory.getMenuAllPizzas();
    }

    public List<RestaurantOrder> findAllOrders() {
        return orderRepository.findAll();
    }

    public List<RestaurantOrder> findAllOrdersByWaiterName(String waiterName) {
        return orderRepository.findByWaiterNameOrderByDateDesc(waiterName);
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
