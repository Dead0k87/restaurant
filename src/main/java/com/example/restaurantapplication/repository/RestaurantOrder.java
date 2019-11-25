package com.example.restaurantapplication.repository;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class RestaurantOrder {
    // RestaurantOrder status = delivered yes not
    // RestaurantOrder payed yes/no
    //List<Ingredient> pizzaIngredients;

    @GeneratedValue
    @Id
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;

    @Size(min = 10, message = "not enough components")
    private String components;

    private double price;
    private String notes;
    private String waiterName;

    private RestaurantOrder() {
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public RestaurantOrder(LocalDateTime date, String waiterName, String components) {
        this.date = date;
        this.waiterName = waiterName;
        this.components = components;
    }

//    public List<Ingredient> getPizzaIngredients() {
//        return pizzaIngredients;
//    }
//
//    public void setPizzaIngredients(List<Ingredient> pizzaIngredients) {
//        this.pizzaIngredients = pizzaIngredients;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "RestaurantOrder{" +
                "date=" + date +
                ", waiterName='" + waiterName + '\'' +
                ", components='" + components + '\'' +
                ", price=" + price +
                '}';
    }
}
