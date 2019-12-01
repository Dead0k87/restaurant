package com.example.restaurantapplication.repository;


import com.example.restaurantapplication.PizzaApplication;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.ItemType;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.MenuItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PizzaApplication.class)
public class MenuItemRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    @DirtiesContext
    public void addPizzasToOrder() {

        MenuItem menuItem1 = menuItemsRepository.save(new MenuItem("123","HotPizza", "1 a ",
                ItemType.PIZZA,  20.0d));
        MenuItem menuItem2 = menuItemsRepository.save(new MenuItem("123","SnowPizza", "2 b",
                ItemType.PIZZA,   40.0d));

        RestaurantOrder order = orderRepository.save(new RestaurantOrder(LocalDateTime.now(),
                "tom"));
        order.addPizza(menuItem1);
        order.addPizza(menuItem2);

        logger.info("order id {}", order.getId());
        String pizza1Name = orderRepository.findById(3L).get().getMenuItems().get(0).getName();
        Assert.assertEquals("HotPizza", pizza1Name);
    }
}
