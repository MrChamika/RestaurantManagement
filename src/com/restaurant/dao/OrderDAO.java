package com.restaurant.dao;

import com.restaurant.model.Order;
import java.util.List;

public interface OrderDAO {
    Order findById(int id);
    List<Order> findAll();
    List<Order> findByCustomer(int customerId);
    List<Order> findTodayOrders();
    int save(Order order);
    boolean updateStatus(int id, String status);
    double getTodayRevenue();
    java.util.List<com.restaurant.model.OrderItem> getOrderItems(int orderId);
}

