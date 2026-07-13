package com.restaurant.factory;

import com.restaurant.dao.*;
import com.restaurant.dao.impl.*;

public class DAOFactory {
    private static CategoryDAO categoryDAO;
    private static MenuItemDAO menuItemDAO;
    private static CustomerDAO customerDAO;
    private static StaffDAO staffDAO;
    private static OrderDAO orderDAO;
    private static PaymentDAO paymentDAO;

    public static CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) categoryDAO = new CategoryDAOImpl();
        return categoryDAO;
    }

    public static MenuItemDAO getMenuItemDAO() {
        if (menuItemDAO == null) menuItemDAO = new MenuItemDAOImpl();
        return menuItemDAO;
    }

    public static CustomerDAO getCustomerDAO() {
        if (customerDAO == null) customerDAO = new CustomerDAOImpl();
        return customerDAO;
    }

    public static StaffDAO getStaffDAO() {
        if (staffDAO == null) staffDAO = new StaffDAOImpl();
        return staffDAO;
    }

    public static OrderDAO getOrderDAO() {
        if (orderDAO == null) orderDAO = new OrderDAOImpl();
        return orderDAO;
    }

    public static PaymentDAO getPaymentDAO() {
        if (paymentDAO == null) paymentDAO = new PaymentDAOImpl();
        return paymentDAO;
    }
}
