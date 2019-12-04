package com.example.restaurantapplication.services;

import com.example.restaurantapplication.controllers.configuration.RestaurantOrderConfigurationProperties;
import com.example.restaurantapplication.repository.MenuItemsRepository;
import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.RestaurantOrderItems.MenuItem;
import com.example.restaurantapplication.repository.RestaurantOrderItems.MenuItemsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // spring transactional better transaction btwn different database(isolation = )
@EnableConfigurationProperties(RestaurantOrderConfigurationProperties.class)

public class RestaurantServiceJPA {

    @Autowired
    private RestaurantOrderConfigurationProperties restaurantOrderConfigurationProperties;

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
        Pageable pageable = PageRequest.of(0,restaurantOrderConfigurationProperties.getPageSize());
        return orderRepository.findByWaiterNameOrderByDateDesc(waiterName,pageable);
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
