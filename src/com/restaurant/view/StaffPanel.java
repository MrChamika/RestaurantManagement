package com.restaurant.view;

import com.restaurant.controller.StaffController;
import com.restaurant.model.Staff;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StaffPanel extends JPanel {
    private StaffController controller = new StaffController();
    private JTable staffTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtEmail, txtSalary, txtSearch;
    private JComboBox cmbRole;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private JScrollPane jScrollPane1;
    private JSplitPane splitPane;
    private JPanel formPanel, tablePanel;

    public StaffPanel() {
        controller = new StaffController();
        initComponents();
        loadTableData();
    }

    private void initComponents() {
        formPanel = new JPanel(); tablePanel = new JPanel();
        jLabel1 = new JLabel(); jLabel2 = new JLabel(); jLabel3 = new JLabel(); jLabel4 = new JLabel(); jLabel5 = new JLabel(); jLabel6 = new JLabel();
        txtName = new JTextField(); txtPhone = new JTextField(); txtEmail = new JTextField(); txtSalary = new JTextField(); txtSearch = new JTextField();
        cmbRole = new JComboBox();
        btnAdd = new JButton(); btnUpdate = new JButton(); btnDelete = new JButton(); btnClear = new JButton(); btnSearch = new JButton();
        jScrollPane1 = new JScrollPane(); staffTable = new JTable(); splitPane = new JSplitPane();

        jLabel1.setText("Name:"); jLabel2.setText("Role:"); jLabel3.setText("Phone:"); jLabel4.setText("Email:"); jLabel5.setText("Salary:"); jLabel6.setText("Search:");
        cmbRole.setModel(new DefaultComboBoxModel(new String[]{"Manager", "Chef", "Waiter", "Cashier", "Cleaner"}));
        btnAdd.setText("Add"); btnUpdate.setText("Update"); btnDelete.setText("Delete"); btnClear.setText("Clear"); btnSearch.setText("Search");

        tableModel = new DefaultTableModel(new String[][]{}, new String[]{"ID", "Name", "Role", "Phone", "Email", "Salary"}) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        staffTable.setModel(tableModel);
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        staffTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedStaff();
        });
        jScrollPane1.setViewportView(staffTable);

        // Form layout
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(cmbRole, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtSalary, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap().addComponent(btnAdd).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(cmbRole, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(txtSalary, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(btnAdd).addComponent(btnUpdate).addComponent(btnDelete).addComponent(btnClear))
                .addContainerGap())
        );

        // Table layout
        GroupLayout tableLayout = new GroupLayout(tablePanel);
        tablePanel.setLayout(tableLayout);
        tableLayout.setHorizontalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tableLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(btnSearch)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        tableLayout.setVerticalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel6).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnSearch))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE).addContainerGap())
        );

        splitPane.setLeftComponent(formPanel); splitPane.setRightComponent(tablePanel); splitPane.setDividerLocation(280);
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane));

        btnAdd.addActionListener(e -> addStaff()); btnUpdate.addActionListener(e -> updateStaff());
        btnDelete.addActionListener(e -> deleteStaff()); btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchStaff());
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (Staff s : controller.getAllStaff()) {
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getPhone(), s.getEmail(), s.getSalary()});
        }
    }

    private void loadSelectedStaff() {
        int row = staffTable.getSelectedRow();
        if (row < 0) return;
        txtName.setText(tableModel.getValueAt(row, 1).toString());
        cmbRole.setSelectedItem(tableModel.getValueAt(row, 2).toString());
        txtPhone.setText(tableModel.getValueAt(row, 3).toString());
        txtEmail.setText(tableModel.getValueAt(row, 4).toString());
        txtSalary.setText(tableModel.getValueAt(row, 5).toString());
    }

    private void addStaff() {
        if (controller.addStaff(getFormData())) { loadTableData(); clearForm(); }
    }

    private void updateStaff() {
        int row = staffTable.getSelectedRow(); if (row < 0) return;
        Staff s = getFormData(); s.setId((int) tableModel.getValueAt(row, 0));
        if (controller.updateStaff(s)) { loadTableData(); clearForm(); }
    }

    private void deleteStaff() {
        int row = staffTable.getSelectedRow(); if (row < 0) return;
        if (JOptionPane.showConfirmDialog(this, "Delete?") == JOptionPane.YES_OPTION) {
            controller.deleteStaff((int) tableModel.getValueAt(row, 0)); loadTableData(); clearForm();
        }
    }

    private void clearForm() { txtName.setText(""); txtPhone.setText(""); txtEmail.setText(""); txtSalary.setText(""); cmbRole.setSelectedIndex(0); }

    private void searchStaff() {
        String kw = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0);
        for (Staff s : controller.getAllStaff()) {
            if (s.getName().toLowerCase().contains(kw) || s.getRole().toLowerCase().contains(kw)) {
                tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getRole(), s.getPhone(), s.getEmail(), s.getSalary()});
            }
        }
    }

    private Staff getFormData() {
        Staff s = new Staff(); s.setName(txtName.getText().trim()); s.setRole((String) cmbRole.getSelectedItem());
        s.setPhone(txtPhone.getText().trim()); s.setEmail(txtEmail.getText().trim());
        try { s.setSalary(Double.parseDouble(txtSalary.getText().trim())); } catch (Exception e) { s.setSalary(0); }
        return s;
    }
}
