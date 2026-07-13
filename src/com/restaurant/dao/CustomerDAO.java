package com.restaurant.dao;

import com.restaurant.model.Customer;
import java.util.List;

public interface CustomerDAO {
    Customer findById(int id);
    List<Customer> findAll();
    List<Customer> search(String keyword);
    boolean save(Customer customer);
    boolean update(Customer customer);
    boolean delete(int id);
}
