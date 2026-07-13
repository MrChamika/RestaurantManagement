package com.restaurant.dao;

import com.restaurant.model.MenuItem;
import java.util.List;

public interface MenuItemDAO {
    MenuItem findById(int id);
    List<MenuItem> findAll();
    List<MenuItem> findByCategory(int categoryId);
    List<MenuItem> search(String keyword);
    boolean save(MenuItem item);
    boolean update(MenuItem item);
    boolean delete(int id);
}
