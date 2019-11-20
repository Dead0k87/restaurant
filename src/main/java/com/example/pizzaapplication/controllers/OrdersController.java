package com.example.pizzaapplication.controllers;

import com.example.pizzaapplication.repository.OrderRepository;
import com.example.pizzaapplication.repository.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes("login")
public class OrdersController {

    @Autowired
    private OrderRepository repository;

    @GetMapping("/orders_list")
    public String showOrdersListPerWaiter(ModelMap model) {
        String login = getLoginID(model);

        List<RestaurantOrder> allOrders = repository.findByWaiterName(login);
        model.put("orders_list", allOrders);

        return "orders_list";
    }

    //ADD ORDER
    @GetMapping("/add_order")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder = new RestaurantOrder(LocalDateTime.now(), getLoginID(model), "default components+");
        model.addAttribute("restaurantOrder", restaurantOrder);
        return "add-or-update-order";
    }

    @PostMapping("/add_order")
    public String submitOrder(ModelMap model, @Valid RestaurantOrder pizza, BindingResult result) {
        if (result.hasErrors()) {
            return "add-or-update-order";
        }
        RestaurantOrder newPizza = new RestaurantOrder(LocalDateTime.now(), getLoginID(model), pizza.getComponents());
        newPizza.setNotes(pizza.getNotes());

        repository.save(newPizza);
        return "redirect:/orders_list";
    }

    //UPDATE ORDER
    @GetMapping("/update_order")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = repository.getById(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add-or-update-order";
    }

    @PostMapping("/update_order")
    public String showUpdatedOrderOnAList(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-or-update-order";
        }
        restaurantOrder.setWaiterName(getLoginID(model));

        repository.save(restaurantOrder);
        return "redirect:/orders_list";
    }

    //DELETE ORDER
    @GetMapping("/delete_order")
    public String deleteOrder(@RequestParam long id) {
        repository.deleteById(id);
        return "redirect:/orders_list";
    }


    private String getLoginID(ModelMap model) {
        return (String) model.get("login");
    }
}
