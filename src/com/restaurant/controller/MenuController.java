package com.restaurant.controller;

import com.restaurant.dao.*;
import com.restaurant.factory.DAOFactory;
import com.restaurant.model.*;
import com.restaurant.exception.MenuItemNotAvailableException;
import java.util.List;

public class MenuController {
    private CategoryDAO categoryDAO = DAOFactory.getCategoryDAO();
    private MenuItemDAO menuItemDAO = DAOFactory.getMenuItemDAO();

    public List<Category> getAllCategories() { return categoryDAO.findAll(); }
    public List<MenuItem> getAllMenuItems() { return menuItemDAO.findAll(); }
    public List<MenuItem> searchMenuItems(String keyword) { return menuItemDAO.search(keyword); }

    public boolean addCategory(Category c) {
        if (c.getName() == null || c.getName().trim().isEmpty()) return false;
        return categoryDAO.save(c);
    }

    public boolean updateCategory(Category c) {
        if (c.getName() == null || c.getName().trim().isEmpty()) return false;
        return categoryDAO.update(c);
    }

    public boolean deleteCategory(int id) { return categoryDAO.delete(id); }

    public String validateMenuItem(MenuItem item) {
        if (item.getName() == null || item.getName().trim().isEmpty()) return "Item name is required";
        if (item.getPrice() <= 0) return "Price must be greater than 0";
        if (item.getCategoryId() <= 0) return "Category is required";
        return null;
    }

    public boolean addMenuItem(MenuItem item) throws MenuItemNotAvailableException {
        String error = validateMenuItem(item);
        if (error != null) throw new MenuItemNotAvailableException(error);
        return menuItemDAO.save(item);
    }

    public boolean updateMenuItem(MenuItem item) throws MenuItemNotAvailableException {
        String error = validateMenuItem(item);
        if (error != null) throw new MenuItemNotAvailableException(error);
        return menuItemDAO.update(item);
    }

    public boolean deleteMenuItem(int id) { return menuItemDAO.delete(id); }
}
