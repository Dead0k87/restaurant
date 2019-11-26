package com.example.restaurantapplication.controllers;

import com.example.restaurantapplication.repository.RestaurantOrder;
import com.example.restaurantapplication.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.Month;

@Controller
public class AdminStatisticsController {
    @Autowired
    private OrderRepository repository;

    @GetMapping("/statistics")
    public String showStatisticsPage() {

        return "admin_statistics";
    }

    @GetMapping("/statistics_today")
    public String getSummaryForToday(ModelMap model) {
        double revenue = repository.findAll()
                .stream()
                .filter(x -> x.getDate().getDayOfMonth() == todayDayOfMonth())
                .filter(x -> x.getDate().getMonth() == todayMonth())
                .filter(x -> x.getDate().getYear() == todayYear())
                .mapToDouble(ord -> ord.getPrice()).sum();

        long count = repository.findAll()
                .stream()
                .filter(x -> x.getDate().getDayOfMonth() == todayDayOfMonth())
                .filter(x -> x.getDate().getMonth() == todayMonth())
                .filter(x -> x.getDate().getYear() == todayYear())
                .count();
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);

        return "admin_statistics";
    }

    @GetMapping("/statistics_this_month")
    public String getSummaryForMonth(ModelMap model) {
        double revenue = repository.findAll()
                .stream()
                .filter(x -> x.getDate().getMonth() == todayMonth())
                .filter(x -> x.getDate().getYear() == todayYear())
                .mapToDouble(ord -> ord.getPrice()).sum();

        long count = repository.findAll()
                .stream()
                .filter(x -> x.getDate().getMonth() == todayMonth())
                .filter(x -> x.getDate().getYear() == todayYear())
                .count();
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);

        return "admin_statistics";
    }

    @GetMapping("/statistics_this_year")
    public String getSummaryForYear(ModelMap model) {
        double revenue = repository.findAll()
                .stream()
                .filter(x -> x.getDate().getYear() == todayYear())
                .mapToDouble(ord -> ord.getPrice()).sum();

        long count = repository.findAll()
                .stream()

                .filter(x -> x.getDate().getYear() == todayYear())
                .count();
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);

        return "admin_statistics";
    }

    @GetMapping("/statistics_all_time")
    public String getSummaryForAllTime(ModelMap model) {
        double revenue = repository.findAll().stream().mapToDouble(RestaurantOrder::getPrice).sum();
        long count = repository.findAll()
                .stream()
                .count();
        model.put("totalOrders", count);
        model.put("totalRevenue", revenue);

        return "admin_statistics";
    }


    private LocalDateTime todayDate() {
        return LocalDateTime.now();
    }

    private int todayDayOfMonth() {
        return todayDate().getDayOfMonth();
    }

    private Month todayMonth() {
        return todayDate().getMonth();
    }

    private int todayYear() {
        return todayDate().getYear();
    }




}
