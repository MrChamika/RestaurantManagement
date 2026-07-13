package com.restaurant.view;

import com.restaurant.controller.CustomerController;
import com.restaurant.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel {
    private CustomerController controller = new CustomerController();
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPhone, txtEmail, txtAddress, txtSearch;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5;
    private JScrollPane jScrollPane1;
    private JSplitPane splitPane;
    private JPanel formPanel, tablePanel;

    public CustomerPanel() {
        controller = new CustomerController();
        initComponents();
        loadTableData();
    }

    private void initComponents() {
        formPanel = new JPanel();
        tablePanel = new JPanel();
        jLabel1 = new JLabel(); jLabel2 = new JLabel(); jLabel3 = new JLabel(); jLabel4 = new JLabel(); jLabel5 = new JLabel();
        txtName = new JTextField(); txtPhone = new JTextField(); txtEmail = new JTextField(); txtAddress = new JTextField(); txtSearch = new JTextField();
        btnAdd = new JButton(); btnUpdate = new JButton(); btnDelete = new JButton(); btnClear = new JButton(); btnSearch = new JButton();
        jScrollPane1 = new JScrollPane();
        customerTable = new JTable();
        splitPane = new JSplitPane();

        jLabel1.setText("Name:"); jLabel2.setText("Phone:"); jLabel3.setText("Email:"); jLabel4.setText("Address:"); jLabel5.setText("Search:");

        btnAdd.setText("Add"); btnUpdate.setText("Update"); btnDelete.setText("Delete"); btnClear.setText("Clear"); btnSearch.setText("Search");

        tableModel = new DefaultTableModel(new String[][]{}, new String[]{"ID", "Name", "Phone", "Email", "Address", "Points"}) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        customerTable.setModel(tableModel);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedCustomer();
        });
        jScrollPane1.setViewportView(customerTable);

        // Form layout
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnClear)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1).addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2).addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3).addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4).addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd).addComponent(btnUpdate).addComponent(btnDelete).addComponent(btnClear))
                .addContainerGap())
        );

        // Table panel layout
        GroupLayout tableLayout = new GroupLayout(tablePanel);
        tablePanel.setLayout(tableLayout);
        tableLayout.setHorizontalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tableLayout.createSequentialGroup()
                .addContainerGap().addComponent(jLabel5)
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
                .addGroup(tableLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnSearch))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPane.setLeftComponent(formPanel);
        splitPane.setRightComponent(tablePanel);
        splitPane.setDividerLocation(280);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(splitPane));

        btnAdd.addActionListener(e -> addCustomer());
        btnUpdate.addActionListener(e -> updateCustomer());
        btnDelete.addActionListener(e -> deleteCustomer());
        btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchCustomers());
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (Customer c : controller.getAllCustomers()) {
            tableModel.addRow(new Object[]{c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress(), c.getLoyaltyPoints()});
        }
    }

    private void loadSelectedCustomer() {
        int row = customerTable.getSelectedRow();
        if (row < 0) return;
        txtName.setText(tableModel.getValueAt(row, 1).toString());
        txtPhone.setText(tableModel.getValueAt(row, 2).toString());
        txtEmail.setText(tableModel.getValueAt(row, 3).toString());
        txtAddress.setText(tableModel.getValueAt(row, 4).toString());
    }

    private void addCustomer() {
        if (controller.addCustomer(getFormData())) {
            JOptionPane.showMessageDialog(this, "Customer added");
            loadTableData(); clearForm();
        }
    }

    private void updateCustomer() {
        int row = customerTable.getSelectedRow();
        if (row < 0) return;
        Customer c = getFormData();
        c.setId((int) tableModel.getValueAt(row, 0));
        if (controller.updateCustomer(c)) { loadTableData(); clearForm(); }
    }

    private void deleteCustomer() {
        int row = customerTable.getSelectedRow();
        if (row < 0) return;
        if (JOptionPane.showConfirmDialog(this, "Delete?") == JOptionPane.YES_OPTION) {
            controller.deleteCustomer((int) tableModel.getValueAt(row, 0));
            loadTableData(); clearForm();
        }
    }

    private void clearForm() { txtName.setText(""); txtPhone.setText(""); txtEmail.setText(""); txtAddress.setText(""); }

    private void searchCustomers() {
        String kw = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        List<Customer> list = kw.isEmpty() ? controller.getAllCustomers() : controller.searchCustomers(kw);
        for (Customer c : list) {
            tableModel.addRow(new Object[]{c.getId(), c.getName(), c.getPhone(), c.getEmail(), c.getAddress(), c.getLoyaltyPoints()});
        }
    }

    private Customer getFormData() {
        Customer c = new Customer();
        c.setName(txtName.getText().trim()); c.setPhone(txtPhone.getText().trim());
        c.setEmail(txtEmail.getText().trim()); c.setAddress(txtAddress.getText().trim());
        return c;
    }
}
