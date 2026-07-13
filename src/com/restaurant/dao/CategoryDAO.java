package com.restaurant.dao;

import com.restaurant.model.Category;
import java.util.List;

public interface CategoryDAO {
    Category findById(int id);
    List<Category> findAll();
    boolean save(Category category);
    boolean update(Category category);
    boolean delete(int id);
}
