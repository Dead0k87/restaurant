package com.example.pizzaapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    public Ingredient getById(long id);

    //List<Ingredient> getByProductType(Ingredient.IngredientType type);

}
