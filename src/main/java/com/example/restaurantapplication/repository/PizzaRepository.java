package com.example.restaurantapplication.repository;


import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    public Pizza getById(long id);

    public List<Pizza> getPizzasByOrder(RestaurantOrder order);

    //List<Ingredient> getByProductType(Ingredient.IngredientType type);

}
