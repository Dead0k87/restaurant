package com.example.restaurantapplication.controllers.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@ConfigurationProperties(prefix = "orders")
@PropertySource("classpath:configprops.properties")
public class RestaurantOrderConfigurationProperties {

    private int pageSize;
}
