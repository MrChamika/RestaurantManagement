package com.restaurant.view;

import com.restaurant.controller.OrderController;
import com.restaurant.exception.InvalidOrderException;
import com.restaurant.model.*;
import com.restaurant.dao.impl.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderPanel extends JPanel {
    private OrderController controller = new OrderController();
    private JComboBox cmbCustomer, cmbStaff, cmbPaymentMethod;
    private JTable menuItemsTable, cartTable;
    private DefaultTableModel menuTableModel, cartTableModel;
    private JTextField txtQuantity, txtSubtotal, txtTax, txtTotal;
    private JButton btnAddToCart, btnRemoveFromCart, btnPlaceOrder, btnClearCart;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7;
    private JScrollPane jScrollPane1, jScrollPane2;
    private JPanel topPanel, leftPanel, rightPanel, summaryPanel;

    private List<CartItem> cart = new ArrayList<>();

    static class CartItem {
        com.restaurant.model.MenuItem item;
        int qty;
        CartItem(com.restaurant.model.MenuItem item, int qty) { this.item = item; this.qty = qty; }
        double getSubtotal() { return item.getPrice() * qty; }
    }

    public PlaceOrderPanel() {
        controller = new OrderController();
        initComponents();
        loadData();
    }

    private void initComponents() {
        topPanel = new JPanel(); leftPanel = new JPanel(); rightPanel = new JPanel(); summaryPanel = new JPanel();
        jLabel1 = new JLabel(); jLabel2 = new JLabel(); jLabel3 = new JLabel();
        jLabel4 = new JLabel(); jLabel5 = new JLabel(); jLabel6 = new JLabel(); jLabel7 = new JLabel();
        cmbCustomer = new JComboBox(); cmbStaff = new JComboBox(); cmbPaymentMethod = new JComboBox();
        txtQuantity = new JTextField(); txtSubtotal = new JTextField(); txtTax = new JTextField(); txtTotal = new JTextField();
        btnAddToCart = new JButton(); btnRemoveFromCart = new JButton(); btnPlaceOrder = new JButton(); btnClearCart = new JButton();
        jScrollPane1 = new JScrollPane(); jScrollPane2 = new JScrollPane();
        menuItemsTable = new JTable(); cartTable = new JTable();

        jLabel1.setText("Customer:"); jLabel2.setText("Staff:"); jLabel3.setText("Payment:");
        jLabel4.setText("Qty:"); jLabel5.setText("Subtotal:"); jLabel6.setText("Tax (10%):"); jLabel7.setText("Total:");
        cmbPaymentMethod.setModel(new DefaultComboBoxModel(new String[]{"Cash", "Card", "Online"}));
        txtQuantity.setText("1");
        txtQuantity.setPreferredSize(new Dimension(60, 25));
        txtSubtotal.setEditable(false); txtTax.setEditable(false); txtTotal.setEditable(false);
        btnAddToCart.setText("Add to Cart >>"); btnRemoveFromCart.setText("Remove"); btnClearCart.setText("Clear"); btnPlaceOrder.setText("Place Order");

        menuTableModel = new DefaultTableModel(new String[][]{}, new String[]{"ID", "Name", "Price", "Category"}) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        menuItemsTable.setModel(menuTableModel);
        menuItemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(menuItemsTable);

        cartTableModel = new DefaultTableModel(new String[][]{}, new String[]{"Item", "Price", "Qty", "Subtotal"}) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        cartTable.setModel(cartTableModel);
        cartTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(cartTable);

        // Top panel layout
        GroupLayout topLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topLayout);
        topLayout.setHorizontalGroup(
            topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbCustomer, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbStaff, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPaymentMethod, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        topLayout.setVerticalGroup(
            topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(topLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1).addComponent(cmbCustomer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2).addComponent(cmbStaff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3).addComponent(cmbPaymentMethod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Left panel - Menu items
        GroupLayout leftLayout = new GroupLayout(leftPanel);
        leftPanel.setLayout(leftLayout);
        leftLayout.setHorizontalGroup(
            leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
            .addGroup(leftLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel4)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnAddToCart).addContainerGap())
        );
        leftLayout.setVerticalGroup(
            leftLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(leftLayout.createSequentialGroup()
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leftLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4).addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddToCart))
                .addContainerGap())
        );

        // Right panel - Cart + Summary
        GroupLayout rightLayout = new GroupLayout(rightPanel);
        rightPanel.setLayout(rightLayout);

        // Summary panel layout
        GroupLayout summaryLayout = new GroupLayout(summaryPanel);
        summaryPanel.setLayout(summaryLayout);
        summaryLayout.setHorizontalGroup(
            summaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5).addComponent(jLabel6).addComponent(jLabel7))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(summaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtSubtotal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTax, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        summaryLayout.setVerticalGroup(
            summaryLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(summaryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(summaryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(txtSubtotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(summaryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(txtTax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(summaryLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel7).addComponent(txtTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightLayout.setHorizontalGroup(
            rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addGroup(rightLayout.createSequentialGroup()
                .addComponent(btnRemoveFromCart).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClearCart).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(btnPlaceOrder))
            .addComponent(summaryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        rightLayout.setVerticalGroup(
            rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(rightLayout.createSequentialGroup()
                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveFromCart).addComponent(btnClearCart).addComponent(btnPlaceOrder))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Main layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(leftPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rightPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btnAddToCart.addActionListener(e -> addToCart());
        btnRemoveFromCart.addActionListener(e -> removeFromCart());
        btnClearCart.addActionListener(e -> clearCart());
        btnPlaceOrder.addActionListener(e -> placeOrder());
    }

    private void loadData() {
        for (Customer c : new CustomerDAOImpl().findAll()) { cmbCustomer.addItem(c.getName()); }
        for (Staff s : new StaffDAOImpl().findAll()) { cmbStaff.addItem(s.getName()); }
        refreshMenuTable();
    }

    private void refreshMenuTable() {
        menuTableModel.setRowCount(0);
        for (com.restaurant.model.MenuItem m : controller.getAvailableItems()) {
            menuTableModel.addRow(new Object[]{m.getId(), m.getName(), m.getPrice(), m.getCategoryName()});
        }
    }

    private void addToCart() {
        int row = menuItemsTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select a menu item"); return; }
        int qty;
        try { qty = Integer.parseInt(txtQuantity.getText().trim()); if (qty <= 0) throw new NumberFormatException(); }
        catch (NumberFormatException e) { JOptionPane.showMessageDialog(this, "Invalid quantity"); return; }

        com.restaurant.model.MenuItem item = new com.restaurant.model.MenuItem();
        item.setId((int) menuTableModel.getValueAt(row, 0));
        item.setName(menuTableModel.getValueAt(row, 1).toString());
        item.setPrice((double) menuTableModel.getValueAt(row, 2));

        for (CartItem ci : cart) {
            if (ci.item.getId() == item.getId()) { ci.qty += qty; refreshCart(); return; }
        }
        cart.add(new CartItem(item, qty));
        refreshCart();
    }

    private void removeFromCart() {
        int row = cartTable.getSelectedRow();
        if (row >= 0) { cart.remove(row); refreshCart(); }
    }

    private void clearCart() { cart.clear(); refreshCart(); }

    private void refreshCart() {
        cartTableModel.setRowCount(0);
        double subtotal = 0;
        for (CartItem ci : cart) {
            cartTableModel.addRow(new Object[]{ci.item.getName(), ci.item.getPrice(), ci.qty, ci.getSubtotal()});
            subtotal += ci.getSubtotal();
        }
        double tax = subtotal * 0.1;
        double total = subtotal + tax;
        txtSubtotal.setText(String.format("%.2f", subtotal));
        txtTax.setText(String.format("%.2f", tax));
        txtTotal.setText(String.format("%.2f", total));
    }

    private void placeOrder() {
        if (cart.isEmpty()) { JOptionPane.showMessageDialog(this, "Cart is empty"); return; }
        try {
            Order order = new Order();
            order.setCustomerId(cmbCustomer.getSelectedIndex() >= 0 ? cmbCustomer.getSelectedIndex() + 1 : 0);
            order.setStaffId(cmbStaff.getSelectedIndex() >= 0 ? cmbStaff.getSelectedIndex() + 1 : 0);

            List<OrderItem> items = new ArrayList<>();
            for (CartItem ci : cart) {
                OrderItem oi = new OrderItem();
                oi.setItemId(ci.item.getId());
                oi.setItemName(ci.item.getName());
                oi.setQuantity(ci.qty);
                oi.setUnitPrice(ci.item.getPrice());
                items.add(oi);
            }
            order.setItems(items);

            String pm = (String) cmbPaymentMethod.getSelectedItem();
            int orderId = controller.placeOrder(order, pm);

            JOptionPane.showMessageDialog(this, "Order #" + orderId + " placed!\nTotal: Rs " + txtTotal.getText(), "Confirmed", JOptionPane.INFORMATION_MESSAGE);
            clearCart();
            refreshMenuTable();
        } catch (InvalidOrderException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
