package com.example.restaurantapplication.security;

import com.example.restaurantapplication.repository.UserRepository;
import com.example.restaurantapplication.security.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String submitRegistration(RegistrationForm registrationForm) {
        userRepository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
