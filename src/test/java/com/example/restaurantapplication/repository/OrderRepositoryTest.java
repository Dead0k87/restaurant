package com.example.restaurantapplication.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.NoSuchElementException;
import java.util.Optional;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    public void getWaiternameByID() {
//        RestaurantOrder order = new RestaurantOrder(LocalDateTime.now(), "tom", "1,2,3)");

        //Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));

        Assert.assertEquals("login", repository.getById(2001L).getWaiterName());

    }

    @Test
    @DirtiesContext
    public void deleteOrderByID() {
        repository.deleteById(2001L);
        Assert.assertEquals(Optional.empty(), repository.findById(2001L) );
    }


    @Test
    @DirtiesContext
    public void changeOrderItems() {
        RestaurantOrder order = repository.findById(2001L).get();
        order.setItems("CHANGED ITEMS");
        repository.save(order);

        Assert.assertEquals("CHANGED ITEMS",repository.getById(2001L).getItems());
    }


}