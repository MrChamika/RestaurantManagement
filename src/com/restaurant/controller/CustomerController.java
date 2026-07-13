package com.restaurant.controller;

import com.restaurant.dao.CustomerDAO;
import com.restaurant.factory.DAOFactory;
import com.restaurant.model.Customer;
import java.util.List;

public class CustomerController {
    private CustomerDAO customerDAO = DAOFactory.getCustomerDAO();

    public List<Customer> getAllCustomers() { return customerDAO.findAll(); }
    public List<Customer> searchCustomers(String keyword) { return customerDAO.search(keyword); }

    public String validateCustomer(Customer c) {
        if (c.getName() == null || c.getName().trim().isEmpty()) return "Customer name is required";
        if (c.getPhone() != null && !c.getPhone().matches("\\d{10,}")) return "Phone must contain at least 10 digits";
        return null;
    }

    public boolean addCustomer(Customer c) {
        if (validateCustomer(c) != null) return false;
        return customerDAO.save(c);
    }

    public boolean updateCustomer(Customer c) {
        if (validateCustomer(c) != null) return false;
        return customerDAO.update(c);
    }

    public boolean deleteCustomer(int id) { return customerDAO.delete(id); }
}
