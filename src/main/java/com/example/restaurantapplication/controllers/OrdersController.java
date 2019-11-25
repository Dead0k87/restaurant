package com.example.restaurantapplication.controllers;

import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/")
    public String login(ModelMap model) {
        return "redirect:/summary";
    }


    @GetMapping("/summary")
    public String showHomePage(ModelMap model) {
        long count = 0;
        double revenue = 0;


        if (isAdmin()) {
            count = repository.findAll().size();
            revenue = repository.findAll()
                    .stream().mapToDouble(ord -> ord.getPrice()).sum();
        } else {
            count = repository.findByWaiterName(getLoginID()).size();
            revenue = repository.findByWaiterName(getLoginID())
                    .stream().mapToDouble(ord -> ord.getPrice()).sum();
        }
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);
        return "summary_page";
    }

    @GetMapping("/orders_list")
    public String showOrdersListPerWaiter(ModelMap model) {
        String loginID = getLoginID();
        model.put("login", loginID);

        if (isAdmin()) {
            List<RestaurantOrder> allOrders = repository.findAll();
            model.put("orders_list", allOrders);
        } else {
            List<RestaurantOrder> allOrders = repository.findByWaiterName(loginID);
            model.put("orders_list", allOrders);
        }
        return "orders_list";
    }

    @PostMapping("/orders_list")// UNDER CONSTRUCTION // i dont know how to put via dropdownlist a value, yet
    public String showOrderListForFilteredWaiter(@RequestParam String waiter, ModelMap model) {
        List<RestaurantOrder> allOrders = repository.findByWaiterName(waiter);
        model.put("orders_list", allOrders);
        return "orders_list";
    }


    //ADD ORDER
    @GetMapping("/add_order")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder = new RestaurantOrder(LocalDateTime.now(), getLoginID(), "default components+");
        model.addAttribute("restaurantOrder", restaurantOrder);
        return "add_or_update_order";
    }

    @PostMapping("/add_order")
    public String submitOrder(ModelMap model, @Valid RestaurantOrder pizza, BindingResult result) {
        if (result.hasErrors()) {
            return "add_or_update_order";
        }
        RestaurantOrder newPizza = new RestaurantOrder(LocalDateTime.now(), getLoginID(), pizza.getComponents());
        newPizza.setNotes(pizza.getNotes());

        repository.save(newPizza);
        return "redirect:/orders_list";
    }

    //UPDATE ORDER
    @GetMapping("/update_order")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = repository.getById(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_order";
    }

    @PostMapping("/update_order")
    public String showUpdatedOrderOnAList(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_or_update_order";
        }
        restaurantOrder.setWaiterName(getLoginID());
        restaurantOrder.setDate(LocalDateTime.now());
        repository.save(restaurantOrder);
        return "redirect:/orders_list";
    }

    //DELETE ORDER
    @GetMapping("/delete_order")
    public String deleteOrder(@RequestParam long id) {

        repository.deleteById(id);
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

    private boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasAdminRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        return hasAdminRole;
    }

}
