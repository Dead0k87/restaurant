package com.example.restaurantapplication.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

//    static {
//        repository = Arrays.asList(
//                new Ingredient("Ham", Ingredient.IngredientType.MEAT, 5),
//                new Ingredient("Corn", Ingredient.IngredientType.VEGETABLES, 3),
//                new Ingredient("Diced Tomatoes", Ingredient.IngredientType.VEGETABLES, 2),
//                new Ingredient("Hot chili pepper", Ingredient.IngredientType.VEGETABLES, 2),
//                new Ingredient("Gouda", Ingredient.IngredientType.CHEESE, 4),
//                new Ingredient("Mozzarella", Ingredient.IngredientType.CHEESE, 4),
//                new Ingredient("Salami", Ingredient.IngredientType.MEAT, 4),
//                new Ingredient("Smoked sausage", Ingredient.IngredientType.MEAT, 4),
//                new Ingredient("Sour Cream", Ingredient.IngredientType.SAUCE, 3),
//                new Ingredient("Spicy sauce", Ingredient.IngredientType.SAUCE, 3)
//        );
//    }

    public List<Ingredient> getIngredients() {
        return repository.findAll();
    }

    public void addIngredient(Ingredient ingredient) {
        repository.save(ingredient);
    }

}
