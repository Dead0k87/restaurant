package com.example.restaurantapplication.controllers;

import com.example.restaurantapplication.repository.MenuItemsRepository;
import com.example.restaurantapplication.repository.OrderRepository;
import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.RestaurantOrderItems.MenuItem;
import com.example.restaurantapplication.repository.RestaurantOrderItems.MenuItemsFactory;
import com.example.restaurantapplication.services.RestaurantServiceJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@SessionAttributes("login")
@RequestMapping(path = "/orders")
public class OrdersController {

    @Autowired
    private RestaurantServiceJPA service;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestaurantServiceJPA restaurantService;

    @Autowired
    private MenuItemsFactory menuItemsFactory;

    @Autowired
    MenuItemsRepository menuItemsRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String login(ModelMap model) {
        return "redirect:orders/summary";
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

    @GetMapping("/list")
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

    @PostMapping("/list")// UNDER CONSTRUCTION // i dont know how to put via dropdownlist a value, yet
    public String showOrderListForFilteredWaiter(@RequestParam String waiter, ModelMap model) {
        List<RestaurantOrder> allOrders = service.findAllOrdersByWaiterName(waiter);
        model.put("orders_list", allOrders);
        return "orders_list";
    }


    //ADD ORDER
    //ADD PIZZA
    @GetMapping("/add")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder = new RestaurantOrder(LocalDateTime.now(), getLoginID()); //Arrays.asList()
        model.put("restaurantOrder", restaurantOrder);

        model.put("restaurantMenu", restaurantService.getRestaurantMenu());

        return "add_or_update_menu_item";
    }

    @PostMapping("/add")
    public String submitOrder(ModelMap model, @Valid RestaurantOrder restaurantOrder,
                              @RequestParam(value = "menuItems", required = false) String[] UIDs,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "add_or_update_menu_item";
        }

        RestaurantOrder newPizza = new RestaurantOrder(LocalDateTime.now(), getLoginID());
        newPizza.setNotes(restaurantOrder.getNotes());

        if (UIDs != null) {
            for (String mItemUID : UIDs
            ) {
                MenuItem menuItem = menuItemsFactory.getNewMenuItemByUID(mItemUID);
                logger.info("new Menu item From factory Based on UID {}",menuItem);
                MenuItem menuItemSavedInRepository = menuItemsRepository.save(menuItem);
                menuItemSavedInRepository.setOrder(newPizza);
                newPizza.getMenuItems().add(menuItemSavedInRepository);
                repository.save(newPizza);
            }
        }
        repository.save(newPizza);
        return "redirect:/orders/list";
    }

    //UPDATE ORDER
    @GetMapping("/update")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = service.getOrderByID(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_order";
    }

    @PostMapping("/update")
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
        return "redirect:/orders/list";
    }

    //DELETE ORDER
    @GetMapping("/delete")
    public String deleteOrder(@RequestParam long id) {
        service.deleteOrderByID(id);
        return "redirect:/orders/list";
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
