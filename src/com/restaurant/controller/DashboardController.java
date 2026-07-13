package com.restaurant.controller;

import com.restaurant.dao.*;
import com.restaurant.factory.DAOFactory;
import com.restaurant.model.Order;
import java.util.List;

public class DashboardController {
    private OrderDAO orderDAO = DAOFactory.getOrderDAO();
    private MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();
    private CustomerDAO customerDAO = DAOFactory.getCustomerDAO();

    public double getTodayRevenue() { return orderDAO.getTodayRevenue(); }
    public int getTodayOrderCount() { return orderDAO.findTodayOrders().size(); }
    public int getTotalMenuItems() { return menuItemDAO.findAll().size(); }
    public int getTotalCustomers() { return customerDAO.findAll().size(); }
    public List<Order> getRecentOrders() {
        List<Order> all = orderDAO.findAll();
        return all.size() > 10 ? all.subList(0, 10) : all;
    }
}
