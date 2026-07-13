package com.restaurant.dao;

import com.restaurant.model.Staff;
import java.util.List;

public interface StaffDAO {
    Staff findById(int id);
    List<Staff> findAll();
    boolean save(Staff staff);
    boolean update(Staff staff);
    boolean delete(int id);
}
