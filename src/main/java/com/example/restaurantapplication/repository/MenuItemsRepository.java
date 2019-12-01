package com.example.restaurantapplication.repository;


import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItem, Long> {

    public MenuItem getById(long id);

    public List<MenuItem> getPizzasByOrder(RestaurantOrder order);

    //List<Ingredient> getByProductType(Ingredient.IngredientType type);

}
