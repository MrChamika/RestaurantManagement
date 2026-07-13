package com.restaurant.view;

import com.restaurant.controller.DashboardController;
import com.restaurant.model.Order;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;

public class DashboardPanel extends JPanel {
    private DashboardController controller = new DashboardController();
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JLabel lblRevenueValue, lblOrdersValue, lblMenuItemsValue, lblCustomersValue;
    private JScrollPane jScrollPane1;
    private JTable recentOrdersTable;
    private DefaultTableModel tableModel;
    private JButton btnRefresh;

    public DashboardPanel() {
        controller = new DashboardController();
        initComponents();
        loadData();
    }

    private void initComponents() {
        jLabel1 = new JLabel();
        btnRefresh = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        lblRevenueValue = new JLabel();
        lblOrdersValue = new JLabel();
        lblMenuItemsValue = new JLabel();
        lblCustomersValue = new JLabel();
        jScrollPane1 = new JScrollPane();
        recentOrdersTable = new JTable();

        jLabel1.setFont(new Font("Arial", Font.BOLD, 24));
        jLabel1.setText("Dashboard");

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(e -> loadData());

        jLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel2.setText("Today's Revenue");
        jLabel3.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel3.setText("Today's Orders");
        jLabel4.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel4.setText("Menu Items");
        jLabel5.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel5.setText("Customers");

        lblRevenueValue.setFont(new Font("Arial", Font.BOLD, 22));
        lblRevenueValue.setText(".00");
        lblOrdersValue.setFont(new Font("Arial", Font.BOLD, 22));
        lblOrdersValue.setText("0");
        lblMenuItemsValue.setFont(new Font("Arial", Font.BOLD, 22));
        lblMenuItemsValue.setText("0");
        lblCustomersValue.setFont(new Font("Arial", Font.BOLD, 22));
        lblCustomersValue.setText("0");

        jLabel2.setOpaque(true);
        jLabel2.setBackground(new Color(46, 204, 113));
        jLabel2.setForeground(Color.WHITE);
        lblRevenueValue.setOpaque(true);
        lblRevenueValue.setBackground(new Color(46, 204, 113));
        lblRevenueValue.setForeground(Color.WHITE);
        lblRevenueValue.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel3.setOpaque(true);
        jLabel3.setBackground(new Color(52, 152, 219));
        jLabel3.setForeground(Color.WHITE);
        lblOrdersValue.setOpaque(true);
        lblOrdersValue.setBackground(new Color(52, 152, 219));
        lblOrdersValue.setForeground(Color.WHITE);
        lblOrdersValue.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel4.setOpaque(true);
        jLabel4.setBackground(new Color(155, 89, 182));
        jLabel4.setForeground(Color.WHITE);
        lblMenuItemsValue.setOpaque(true);
        lblMenuItemsValue.setBackground(new Color(155, 89, 182));
        lblMenuItemsValue.setForeground(Color.WHITE);
        lblMenuItemsValue.setHorizontalAlignment(SwingConstants.CENTER);

        jLabel5.setOpaque(true);
        jLabel5.setBackground(new Color(230, 126, 34));
        jLabel5.setForeground(Color.WHITE);
        lblCustomersValue.setOpaque(true);
        lblCustomersValue.setBackground(new Color(230, 126, 34));
        lblCustomersValue.setForeground(Color.WHITE);
        lblCustomersValue.setHorizontalAlignment(SwingConstants.CENTER);

        tableModel = new DefaultTableModel(
            new String[][]{},
            new String[]{"Order ID", "Customer", "Staff", "Status", "Total", "Date"}
        );
        recentOrdersTable.setModel(tableModel);
        jScrollPane1.setViewportView(recentOrdersTable);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 606, Short.MAX_VALUE)
                        .addComponent(btnRefresh))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblRevenueValue, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblOrdersValue, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMenuItemsValue, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCustomersValue, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRevenueValue, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOrdersValue, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMenuItemsValue, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCustomersValue, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
    }

    public void loadData() {
        try {
            lblRevenueValue.setText("Rs " + String.format("%.2f", controller.getTodayRevenue()));
            lblOrdersValue.setText(String.valueOf(controller.getTodayOrderCount()));
            lblMenuItemsValue.setText(String.valueOf(controller.getTotalMenuItems()));
            lblCustomersValue.setText(String.valueOf(controller.getTotalCustomers()));

            tableModel.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            for (Order o : controller.getRecentOrders()) {
                tableModel.addRow(new Object[]{
                    o.getId(),
                    o.getCustomerName() != null ? o.getCustomerName() : "Walk-in",
                    o.getStaffName(),
                    o.getStatus(),
                    "Rs " + String.format("%.2f", o.getTotal()),
                    o.getOrderDate() != null ? sdf.format(o.getOrderDate()) : ""
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
