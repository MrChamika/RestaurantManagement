package com.restaurant.view;

import com.restaurant.controller.MenuController;
import com.restaurant.exception.MenuItemNotAvailableException;
import com.restaurant.model.Category;
import com.restaurant.model.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuPanel extends JPanel {
    private MenuController controller = new MenuController();
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JTextField txtName, txtPrice, txtSearch;
    private JTextArea txtDescription;
    private JComboBox cmbCategory;
    private JComboBox cmbStatus;
    private JButton btnAdd, btnUpdate, btnDelete, btnClear, btnSearch;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6;
    private JScrollPane jScrollPane1, jScrollPane2;
    private JSplitPane splitPane;
    private JPanel formPanel, tablePanel;

    public MenuPanel() {
        controller = new MenuController();
        initComponents();
        loadTableData();
        loadCategories();
    }

    private void initComponents() {
        formPanel = new JPanel();
        tablePanel = new JPanel();
        jLabel1 = new JLabel();
        txtName = new JTextField();
        jLabel2 = new JLabel();
        txtPrice = new JTextField();
        jLabel3 = new JLabel();
        cmbCategory = new JComboBox();
        jLabel4 = new JLabel();
        cmbStatus = new JComboBox();
        jLabel5 = new JLabel();
        txtDescription = new JTextArea();
        jScrollPane2 = new JScrollPane();
        btnAdd = new JButton();
        btnUpdate = new JButton();
        btnDelete = new JButton();
        btnClear = new JButton();
        jScrollPane1 = new JScrollPane();
        menuTable = new JTable();
        jLabel6 = new JLabel();
        txtSearch = new JTextField();
        btnSearch = new JButton();
        splitPane = new JSplitPane();

        jLabel1.setText("Name:");
        jLabel2.setText("Price:");
        jLabel3.setText("Category:");
        jLabel4.setText("Status:");
        jLabel5.setText("Description:");
        jLabel6.setText("Search:");

        cmbStatus.setModel(new DefaultComboBoxModel(new String[]{"Available", "Unavailable"}));

        txtDescription.setColumns(20);
        txtDescription.setRows(3);
        jScrollPane2.setViewportView(txtDescription);

        btnAdd.setText("Add");
        btnUpdate.setText("Update");
        btnDelete.setText("Delete");
        btnClear.setText("Clear");
        btnSearch.setText("Search");

        tableModel = new DefaultTableModel(
            new String[][]{},
            new String[]{"ID", "Name", "Price", "Category", "Status"}
        ) {
            Class[] types = {Integer.class, String.class, Double.class, String.class, String.class};
            public Class getColumnClass(int columnIndex) { return types[columnIndex]; }
            public boolean isCellEditable(int row, int col) { return false; }
        };
        menuTable.setModel(tableModel);
        menuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) loadSelectedItem();
        });
        jScrollPane1.setViewportView(menuTable);

        // Form panel layout
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(txtPrice, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(cmbCategory, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(cmbStatus, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jLabel2).addComponent(txtPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3).addComponent(cmbCategory, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4).addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5).addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
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
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
        );
        tableLayout.setVerticalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(tableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tableLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6).addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(btnSearch))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );

        splitPane.setLeftComponent(formPanel);
        splitPane.setRightComponent(tablePanel);
        splitPane.setDividerLocation(280);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        btnAdd.addActionListener(e -> addItem());
        btnUpdate.addActionListener(e -> updateItem());
        btnDelete.addActionListener(e -> deleteItem());
        btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchItems());
    }

    private void loadTableData() {
        tableModel.setRowCount(0);
        for (MenuItem m : controller.getAllMenuItems()) {
            tableModel.addRow(new Object[]{m.getId(), m.getName(), m.getPrice(), m.getCategoryName(), m.getStatus()});
        }
    }

    private void loadCategories() {
        cmbCategory.removeAllItems();
        for (Category c : controller.getAllCategories()) {
            cmbCategory.addItem(c.getName());
        }
    }

    private void loadSelectedItem() {
        int row = menuTable.getSelectedRow();
        if (row < 0) return;
        txtName.setText(tableModel.getValueAt(row, 1).toString());
        txtPrice.setText(tableModel.getValueAt(row, 2).toString());
        cmbCategory.setSelectedItem(tableModel.getValueAt(row, 3).toString());
        cmbStatus.setSelectedItem(tableModel.getValueAt(row, 4).toString());
    }

    private void addItem() {
        try {
            MenuItem item = getFormData();
            if (controller.addMenuItem(item)) {
                JOptionPane.showMessageDialog(this, "Menu item added");
                loadTableData();
                clearForm();
            }
        } catch (MenuItemNotAvailableException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateItem() {
        int row = menuTable.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Select an item"); return; }
        try {
            MenuItem item = getFormData();
            item.setId((int) tableModel.getValueAt(row, 0));
            if (controller.updateMenuItem(item)) {
                JOptionPane.showMessageDialog(this, "Item updated");
                loadTableData();
                clearForm();
            }
        } catch (MenuItemNotAvailableException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int row = menuTable.getSelectedRow();
        if (row < 0) return;
        if (JOptionPane.showConfirmDialog(this, "Delete?") == JOptionPane.YES_OPTION) {
            controller.deleteMenuItem((int) tableModel.getValueAt(row, 0));
            loadTableData();
            clearForm();
        }
    }

    private void clearForm() {
        txtName.setText(""); txtPrice.setText(""); txtDescription.setText("");
        cmbCategory.setSelectedIndex(0); cmbStatus.setSelectedIndex(0);
        menuTable.clearSelection();
    }

    private void searchItems() {
        String kw = txtSearch.getText().trim();
        tableModel.setRowCount(0);
        List<MenuItem> items = kw.isEmpty() ? controller.getAllMenuItems() : controller.searchMenuItems(kw);
        for (MenuItem m : items) {
            tableModel.addRow(new Object[]{m.getId(), m.getName(), m.getPrice(), m.getCategoryName(), m.getStatus()});
        }
    }

    private MenuItem getFormData() {
        MenuItem item = new MenuItem();
        item.setName(txtName.getText().trim());
        item.setPrice(Double.parseDouble(txtPrice.getText().trim()));
        item.setDescription(txtDescription.getText().trim());
        item.setStatus((String) cmbStatus.getSelectedItem());
        item.setCategoryId(getSelectedCategoryId());
        return item;
    }

    private int getSelectedCategoryId() {
        String name = (String) cmbCategory.getSelectedItem();
        for (Category c : controller.getAllCategories()) {
            if (c.getName().equals(name)) return c.getId();
        }
        return 1;
    }
}
