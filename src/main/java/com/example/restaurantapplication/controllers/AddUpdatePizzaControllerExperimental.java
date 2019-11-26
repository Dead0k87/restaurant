package com.example.restaurantapplication.controllers;

import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.services.RestaurantServiceJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
public class AddUpdatePizzaControllerExperimental {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestaurantServiceJPA restaurantService;



    //ADD PIZZA
    @GetMapping("/add_pizza")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder = new RestaurantOrder(LocalDateTime.now(), getLoginID(), "dough +"); //Arrays.asList()
        model.put("restaurantOrder", restaurantOrder);

        model.put("restaurantMenu", restaurantService.getRestaurantMenu());

        return "add_or_update_pizza";
    }

    @PostMapping("/add_pizza")
    public String submitOrder(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult result) {
        if (result.hasErrors()) {
            return "add_or_update_pizza";
        }
        RestaurantOrder newPizza = new RestaurantOrder(LocalDateTime.now(), getLoginID(), restaurantOrder.getItems());
        newPizza.setNotes(restaurantOrder.getNotes());


        repository.save(newPizza);
        return "redirect:/orders_list";
    }

    //UPDATE PIZZA
    @GetMapping("/update_pizza")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = repository.getById(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_pizza";
    }

    @PostMapping("/update_pizza")
    public String showUpdatedOrderOnAList(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_or_update_pizza";
        }
        restaurantOrder.setWaiterName(getLoginID());
        restaurantOrder.setDate(LocalDateTime.now());
        repository.save(restaurantOrder);
        return "redirect:/orders_list";
    }





    private String getLoginID() {
        Object authentication =
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authentication instanceof UserDetails) {
            return ((UserDetails) authentication).getUsername();
        }
        return authentication.toString();
    }


}
