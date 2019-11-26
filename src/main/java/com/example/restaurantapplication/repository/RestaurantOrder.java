package com.example.restaurantapplication.repository;

import com.example.restaurantapplication.repository.RestaurantOrderItems.pizzas.RestaurantOrderItem;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RestaurantOrder {
    // RestaurantOrder status = delivered yes not
    // RestaurantOrder payed yes/no

    @Id
    @GeneratedValue
    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String waiterName;

    @Size(min = 10, message = "not enough components")
    private String items;

    private double price;
    private String notes;


//    @OneToMany(mappedBy = "order" )
//    //@JoinColumn(name = "order_id")
//    private List<RestaurantOrderItem> items = new ArrayList<>();

    public RestaurantOrder(LocalDateTime date, String waiterName, String items ) { //List<RestaurantOrderItem> orderItems
        this.date = date;
        this.waiterName = waiterName;
        this.items = items;

    }

    private RestaurantOrder() {
    }

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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "RestaurantOrder{" +
                "id=" + id +
                ", date=" + date +
                ", price=" + price +
                ", notes='" + notes + '\'' +
                ", waiterName='" + waiterName + '\'' +
                ", items=" + items +
                '}';
    }
}
