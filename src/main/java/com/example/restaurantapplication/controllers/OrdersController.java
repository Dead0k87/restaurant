package com.example.restaurantapplication.controllers;

import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.services.RestaurantServiceJPA;
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
//@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    private RestaurantServiceJPA service;

    @GetMapping("/")
    public String login(ModelMap model) {
        return "redirect:/summary";
    }

    @GetMapping("/summary")
    public String showHomePage(ModelMap model) {
        long count = 0;
        double revenue = 0;

        if (isAdmin()) {
            count = service.findAllOrders().size();
            revenue = service.findAllOrders()
                    .stream().mapToDouble(order -> order.getPrice()).sum();
        } else {
            count = service.findAllOrdersByWaiterName(getLoginID()).size();
            revenue = service.findAllOrdersByWaiterName(getLoginID())
                    .stream().mapToDouble(order -> order.getPrice()).sum();
        }
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);
        return "summary";
    }

    @GetMapping("/orders_list")
    public String showOrdersListPerWaiter(ModelMap model) {
        String loginID = getLoginID();
        model.put("login", loginID);

        if (isAdmin()) {
            List<RestaurantOrder> allOrders = service.findAllOrders();
            model.put("orders_list", allOrders);
        } else {
            List<RestaurantOrder> allOrders = service.findAllOrdersByWaiterName(loginID);
            model.put("orders_list", allOrders);
        }
        return "orders_list";
    }

    @PostMapping("/orders_list")// UNDER CONSTRUCTION // i dont know how to put via dropdownlist a value, yet
    public String showOrderListForFilteredWaiter(@RequestParam String waiter, ModelMap model) {
        List<RestaurantOrder> allOrders = service.findAllOrdersByWaiterName(waiter);
        model.put("orders_list", allOrders);
        return "orders_list";
    }


    //ADD ORDER
    @GetMapping("/add_order")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder
                = new RestaurantOrder(LocalDateTime.now(), getLoginID()); //Arrays.asList()
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_order";
    }

    @PostMapping("/add_order")
    public String submitOrder(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult result) {
        if (result.hasErrors()) {
            return "add_or_update_order";
        }
        RestaurantOrder newPizza = new RestaurantOrder(LocalDateTime.now(), getLoginID());
        newPizza.setNotes(restaurantOrder.getNotes());

        service.saveOrder(newPizza);
        return "redirect:/orders_list";
    }

    //UPDATE ORDER
    @GetMapping("/update_order")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = service.getOrderByID(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_order";
    }

    @PostMapping("/update_order")
    public String showUpdatedOrderOnAList(ModelMap model, @Valid RestaurantOrder restaurantOrder,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_or_update_order";
        }
//        restaurantOrder.setId(restaurantOrder.getId());
        restaurantOrder.setWaiterName(getLoginID());
        restaurantOrder.setDate(LocalDateTime.now());
//        restaurantOrder.setPrice(restaurantOrder.getPrice());


        service.saveOrder(restaurantOrder);
        return "redirect:/orders_list";
    }

    //DELETE ORDER
    @GetMapping("/delete_order")
    public String deleteOrder(@RequestParam long id) {
        service.deleteOrderByID(id);
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
