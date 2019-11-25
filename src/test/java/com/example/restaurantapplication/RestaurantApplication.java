package com.example.restaurantapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantApplication {

    // add spring MVC dependency

    @LocalServerPort
    private int port;

    @Test
    //@WithMockUser(username = "user1", password = "secret1")
    void contextLoads() {
        String url = "http://localhost:" + port + "/";
    }

}
