package com.example.pizzaapplication.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @GetMapping("/login")
//    @ResponseBody
    public String login(ModelMap model) {
//        model.put("loginName", login);
        return "login_page";
    }

    @PostMapping("/login")
    public String startConfigYourCar(ModelMap model, @RequestParam String login, @RequestParam String password) {
        if (("login".equalsIgnoreCase(login.toLowerCase())
                && ("password".equalsIgnoreCase(password.toLowerCase())))) {
            model.put("login", login);

            return "redirect:/orders_list";
        } else {
            model.put("errorMessage", "Wrong login or password");
            return "login_page";
        }
    }
}
