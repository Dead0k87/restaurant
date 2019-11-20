package com.example.pizzaapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<RestaurantOrder, Long> {

    public RestaurantOrder getById(long id);
    public List<RestaurantOrder> findByWaiterName(String waiterName);

    //List<Ingredient> getByProductType(Ingredient.IngredientType type);

}
