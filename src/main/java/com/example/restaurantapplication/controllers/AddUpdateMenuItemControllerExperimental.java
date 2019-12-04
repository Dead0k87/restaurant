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
public class AddUpdateMenuItemControllerExperimental {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestaurantServiceJPA restaurantService;

    @Autowired
    private MenuItemsFactory menuItemsFactory;

    @Autowired
    MenuItemsRepository menuItemsRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //ADD PIZZA
    @GetMapping("/add_menu_item")
    public String showNewOrderPage(ModelMap model) {
        RestaurantOrder restaurantOrder = new RestaurantOrder(LocalDateTime.now(), getLoginID()); //Arrays.asList()
        model.put("restaurantOrder", restaurantOrder);

        model.put("restaurantMenu", restaurantService.getRestaurantMenu());

        return "add_or_update_menu_item";
    }

    @PostMapping("/add_menu_item")
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
        return "redirect:/orders_list";
    }

    //UPDATE PIZZA
    @GetMapping("/update_menu_item")
    public String updateOrder(ModelMap model, @RequestParam long id) {
        RestaurantOrder restaurantOrder = repository.getById(id);
        model.put("restaurantOrder", restaurantOrder);
        return "add_or_update_menu_item";
    }

    @PostMapping("/update_menu_item")
    public String showUpdatedOrderOnAList(ModelMap model, @Valid RestaurantOrder restaurantOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_or_update_menu_item";
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
