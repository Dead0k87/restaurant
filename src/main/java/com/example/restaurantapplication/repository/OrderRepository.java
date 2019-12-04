package com.example.restaurantapplication.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<RestaurantOrder, Long> {

    public RestaurantOrder getById(long id);

    public List<RestaurantOrder> findByWaiterNameOrderByDateDesc(String waiterName, Pageable pageable);


}
