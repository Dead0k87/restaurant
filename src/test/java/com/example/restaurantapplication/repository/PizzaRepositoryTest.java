package com.example.restaurantapplication.repository;


import com.example.restaurantapplication.PizzaApplication;
import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.Pizza;
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
public class PizzaRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    @Transactional
    @DirtiesContext
    public void addPizzasToOrder() {

        Pizza pizza1 = pizzaRepository.save(new Pizza("HotPizza", "1 a ",
                20.0d));
        Pizza pizza2 = pizzaRepository.save(new Pizza("SnowPizza", "2 b",
                40.0d));

        RestaurantOrder order = orderRepository.save(new RestaurantOrder(LocalDateTime.now(),
                "tom", "something"));
        order.addPizza(pizza1);
        order.addPizza(pizza2);

        logger.info("order id {}", order.getId());
        String pizza1Name = orderRepository.findById(3L).get().getPizzas().get(0).getName();
        Assert.assertEquals("HotPizza", pizza1Name);
    }
}
