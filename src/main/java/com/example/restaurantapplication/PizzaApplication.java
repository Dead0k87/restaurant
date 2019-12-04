package com.example.restaurantapplication;

import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.MenuItemsRepository;
import com.example.restaurantapplication.services.RestaurantServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);

    }

    @Autowired
    private RestaurantServiceJPA restaurantServiceJPA;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

//        MenuItem menuItem1 = pizzaRepository.save(new MenuItem("HotPizza", "1 a ", ItemType.PIZZA,
//                20.0d));
//        MenuItem menuItem2 = pizzaRepository.save(new MenuItem("SnowPizza", "2 b",ItemType.PIZZA,
//                40.0d));
//
//        RestaurantOrder order = orderRepository.save(new RestaurantOrder(LocalDateTime.now(),
//                "tom"));
//        order.addMenuItem(menuItem1);
//        order.addMenuItem(menuItem2);

    }
}
