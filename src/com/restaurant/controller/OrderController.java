package com.restaurant.controller;

import com.restaurant.dao.*;
import com.restaurant.factory.DAOFactory;
import com.restaurant.model.*;
import com.restaurant.exception.InvalidOrderException;
import java.util.List;

public class OrderController {
    private OrderDAO orderDAO = DAOFactory.getOrderDAO();
    private PaymentDAO paymentDAO = DAOFactory.getPaymentDAO();
    private MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();

    public List<Order> getAllOrders() { return orderDAO.findAll(); }
    public List<Order> getTodayOrders() { return orderDAO.findTodayOrders(); }
    public Order getOrderById(int id) { return orderDAO.findById(id); }

    public List<MenuItem> getAvailableItems() {
        List<MenuItem> all = menuItemDAO.findAll();
        all.removeIf(item -> !"Available".equals(item.getStatus()));
        return all;
    }

    public int placeOrder(Order order, String paymentMethod) throws InvalidOrderException {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidOrderException("Order must have at least one item");
        }

        double total = 0;
        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() <= 0) throw new InvalidOrderException("Quantity must be at least 1");
            if (item.getUnitPrice() <= 0) throw new InvalidOrderException("Invalid price for item: " + item.getItemName());
            total += item.getSubtotal();
        }

        order.setTotal(total);
        int orderId = orderDAO.save(order);
        if (orderId <= 0) throw new InvalidOrderException("Failed to create order");

        orderDAO.updateStatus(orderId, "Completed");

        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(total);
        payment.setMethod(paymentMethod);
        paymentDAO.save(payment);

        return orderId;
    }

    public double getTodayRevenue() { return orderDAO.getTodayRevenue(); }
    public List<OrderItem> getOrderItems(int orderId) { return orderDAO.getOrderItems(orderId); }
}
