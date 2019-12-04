package com.example.restaurantapplication.repository;


import com.example.restaurantapplication.repository.RestaurantOrderItems.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItem, Long> {

    public MenuItem getById(long id);

  //  public List<MenuItem> getPizzasByOrder(RestaurantOrder order);

}
