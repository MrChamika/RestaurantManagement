package com.restaurant.dao.impl;

import com.restaurant.dao.OrderDAO;
import com.restaurant.db.DBConnection;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Connection conn = DBConnection.getInstance().getConnection();

    @Override
    public Order findById(int id) {
        String sql = "SELECT o.*, c.name as customer_name, s.name as staff_name FROM orders o LEFT JOIN customers c ON o.customer_id = c.id LEFT JOIN staff s ON o.staff_id = s.id WHERE o.id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order o = extractOrder(rs);
                o.setItems(getOrderItems(id));
                return o;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.*, c.name as customer_name, s.name as staff_name FROM orders o LEFT JOIN customers c ON o.customer_id = c.id LEFT JOIN staff s ON o.staff_id = s.id ORDER BY o.order_date DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(extractOrder(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Order> findByCustomer(int customerId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.*, c.name as customer_name, s.name as staff_name FROM orders o LEFT JOIN customers c ON o.customer_id = c.id LEFT JOIN staff s ON o.staff_id = s.id WHERE o.customer_id = ? ORDER BY o.order_date DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractOrder(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Order> findTodayOrders() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT o.*, c.name as customer_name, s.name as staff_name FROM orders o LEFT JOIN customers c ON o.customer_id = c.id LEFT JOIN staff s ON o.staff_id = s.id WHERE DATE(o.order_date) = CURDATE() ORDER BY o.order_date DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(extractOrder(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public int save(Order order) {
        String sql = "INSERT INTO orders (customer_id, staff_id, total, status) VALUES (?, ?, ?, 'Pending')";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, order.getCustomerId() > 0 ? order.getCustomerId() : null);
            ps.setObject(2, order.getStaffId() > 0 ? order.getStaffId() : null);
            ps.setDouble(3, order.getTotal());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                saveOrderItems(orderId, order.getItems());
                return orderId;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    private void saveOrderItems(int orderId, List<OrderItem> items) {
        String sql = "INSERT INTO order_items (order_id, item_id, quantity, unit_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (OrderItem item : items) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getItemId());
                ps.setInt(3, item.getQuantity());
                ps.setDouble(4, item.getUnitPrice());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public List<OrderItem> getOrderItems(int orderId) {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT oi.*, m.name as item_name FROM order_items oi JOIN menu_items m ON oi.item_id = m.id WHERE oi.order_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderItem oi = new OrderItem();
                oi.setId(rs.getInt("id"));
                oi.setOrderId(rs.getInt("order_id"));
                oi.setItemId(rs.getInt("item_id"));
                oi.setItemName(rs.getString("item_name"));
                oi.setQuantity(rs.getInt("quantity"));
                oi.setUnitPrice(rs.getDouble("unit_price"));
                list.add(oi);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    @Override
    public double getTodayRevenue() {
        String sql = "SELECT COALESCE(SUM(total), 0) FROM orders WHERE DATE(order_date) = CURDATE() AND status = 'Completed'";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    private Order extractOrder(ResultSet rs) throws SQLException {
        Order o = new Order();
        o.setId(rs.getInt("id"));
        o.setCustomerId(rs.getInt("customer_id"));
        o.setCustomerName(rs.getString("customer_name"));
        o.setStaffId(rs.getInt("staff_id"));
        o.setStaffName(rs.getString("staff_name"));
        o.setOrderDate(rs.getTimestamp("order_date"));
        o.setStatus(rs.getString("status"));
        o.setTotal(rs.getDouble("total"));
        return o;
    }
}
