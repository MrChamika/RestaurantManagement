package com.restaurant.dao.impl;

import com.restaurant.dao.StaffDAO;
import com.restaurant.db.DBConnection;
import com.restaurant.model.Staff;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAOImpl implements StaffDAO {
    private Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public Staff findById(int id) {
        String sql = "SELECT * FROM staff WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractStaff(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Staff> findAll() {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM staff ORDER BY name";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(extractStaff(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Staff s) {
        String sql = "INSERT INTO staff (name, role, phone, email, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRole());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getEmail());
            ps.setDouble(5, s.getSalary());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean update(Staff s) {
        String sql = "UPDATE staff SET name=?, role=?, phone=?, email=?, salary=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getRole());
            ps.setString(3, s.getPhone());
            ps.setString(4, s.getEmail());
            ps.setDouble(5, s.getSalary());
            ps.setInt(6, s.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM staff WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Staff extractStaff(ResultSet rs) throws SQLException {
        Staff s = new Staff();
        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setRole(rs.getString("role"));
        s.setPhone(rs.getString("phone"));
        s.setEmail(rs.getString("email"));
        s.setSalary(rs.getDouble("salary"));
        return s;
    }
}
