package com.restaurant.controller;

import com.restaurant.dao.StaffDAO;
import com.restaurant.factory.DAOFactory;
import com.restaurant.model.Staff;
import java.util.List;

public class StaffController {
    private StaffDAO staffDAO = DAOFactory.getStaffDAO();

    public List<Staff> getAllStaff() { return staffDAO.findAll(); }

    public String validateStaff(Staff s) {
        if (s.getName() == null || s.getName().trim().isEmpty()) return "Staff name is required";
        if (s.getRole() == null || s.getRole().trim().isEmpty()) return "Role is required";
        if (s.getSalary() < 0) return "Salary cannot be negative";
        return null;
    }

    public boolean addStaff(Staff s) {
        if (validateStaff(s) != null) return false;
        return staffDAO.save(s);
    }

    public boolean updateStaff(Staff s) {
        if (validateStaff(s) != null) return false;
        return staffDAO.update(s);
    }

    public boolean deleteStaff(int id) { return staffDAO.delete(id); }
}
