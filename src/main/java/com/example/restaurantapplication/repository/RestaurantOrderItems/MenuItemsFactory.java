package com.example.restaurantapplication.repository.RestaurantOrderItems;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MenuItemsFactory {

    // тут при добавлении, надо будет создавать в базе данных запись save(pizza)
    // далее добавлять ее к заказу
    //  и потом возвращать из базы новый список.

    public MenuItem getEmptyMenuItem() {
        return new MenuItem("EMPT", "BLANK MENU ITEM", "BLANK MENU ITEM DESCR", ItemType.PIZZA, 0.0d);
    }

    public MenuItem getHotRoadPizza() {
        return new MenuItem("HTRP", "HOT ROAD PIZZA", "mozzarella, pepperoni, carmel onion, jalapeño", ItemType.PIZZA, 25.0d);
    }

    public MenuItem getBBQPizza() {
        return new MenuItem("BBQP", "BBQ PIZZA", "BBQ sauce, mozzarella, bacon, smocked chicken, carmel onion", ItemType.PIZZA, 30.0d);
    }

    public MenuItem getItalianPizza() {
        return new MenuItem("ITLP", "ITALIAN PIZZA", "tomato sauce, mozzarella, carpaccio, oregano, corregio", ItemType.PIZZA, 30.0d);
    }

    public MenuItem getCosmicPizza() {
        return new MenuItem("CSMP", "COSMIC PIZZA", "space dust, star sauce", ItemType.PIZZA, 300.0d);
    }

    public MenuItem getGreekSalad() {
        return new MenuItem("GRKS", "GREEK SALAD",
                "vine tomatoes, cucumber, red onion, kalamata olives, dried oregano, feta cheese,Greek extra virgin olive oil",
                ItemType.SALAD, 15.0d);
    }

    public MenuItem getCocaCola() {
        return new MenuItem("CCLD", "Coca-Cola", "0.5L", ItemType.DRINK, 2.0d);
    }

    public List<MenuItem> getMenuAllPizzas() {
        return Arrays.asList(
                getHotRoadPizza(),
                getBBQPizza(),
                getItalianPizza(), getCosmicPizza(),
                getCocaCola(),
                getGreekSalad()
        );
    }


    public MenuItem getNewMenuItemByUID(String UID) {
        Optional<MenuItem> firstItemThatSuitsUID
                = getMenuAllPizzas().stream().filter(x -> UID.equalsIgnoreCase(x.getUID())).findFirst();
        if (firstItemThatSuitsUID.isPresent()) {
            MenuItem menuItem = firstItemThatSuitsUID.get();
            return new MenuItem(menuItem);
        } else {
            return new MenuItem(getEmptyMenuItem());
        }
    }

}
