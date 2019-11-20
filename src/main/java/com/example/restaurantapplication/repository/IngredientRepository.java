package com.example.restaurantapplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    public Ingredient getById(long id);

    //List<Ingredient> getByProductType(Ingredient.IngredientType type);

}
