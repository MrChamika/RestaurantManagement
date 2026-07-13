package com.restaurant.dao.impl;

import com.restaurant.dao.PaymentDAO;
import com.restaurant.db.DBConnection;
import com.restaurant.model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    private Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public Payment findById(int id) {
        String sql = "SELECT * FROM payments WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractPayment(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Payment> findByOrder(int orderId) {
        List<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payments WHERE order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractPayment(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean save(Payment p) {
        String sql = "INSERT INTO payments (order_id, amount, method) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getOrderId());
            ps.setDouble(2, p.getAmount());
            ps.setString(3, p.getMethod());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    private Payment extractPayment(ResultSet rs) throws SQLException {
        Payment p = new Payment();
        p.setId(rs.getInt("id"));
        p.setOrderId(rs.getInt("order_id"));
        p.setAmount(rs.getDouble("amount"));
        p.setMethod(rs.getString("method"));
        p.setPaymentDate(rs.getTimestamp("payment_date"));
        return p;
    }
}
