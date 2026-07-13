package com.restaurant.view;

import com.restaurant.report.ReportManager;
import javax.swing.*;
import java.awt.*;

public class ReportViewerFrame extends JPanel {
    private JComboBox cmbReportType;
    private JButton btnGenerate;
    private JLabel jLabel1, jLabel2;

    private static final String[] REPORTS = {"Sales Report (Orders + Items)", "Popular Items (Items + Categories)", "Customer History (Customers + Orders)"};
    private static final String[] FILES = {"sales_report.jrxml", "popular_items.jrxml", "customer_history.jrxml"};

    public ReportViewerFrame() {
        initComponents();
    }

    private void initComponents() {
        jLabel1 = new JLabel(); cmbReportType = new JComboBox(); btnGenerate = new JButton(); jLabel2 = new JLabel();

        jLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        jLabel1.setText("Generate Reports");
        cmbReportType.setModel(new DefaultComboBoxModel(REPORTS));
        cmbReportType.setPreferredSize(new Dimension(300, 30));
        btnGenerate.setText("Generate & Preview");
        jLabel2.setFont(new Font("Arial", Font.PLAIN, 14));
        jLabel2.setForeground(Color.GRAY);
        jLabel2.setText("Select a report and click 'Generate & Preview'");
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbReportType, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerate))
                    .addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReportType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate))
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        btnGenerate.addActionListener(e -> generateReport());
    }

    private void generateReport() {
        int idx = cmbReportType.getSelectedIndex();
        if (idx < 0) return;
        String file = FILES[idx];
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override protected Void doInBackground() {
                jLabel2.setText("Generating...");
                ReportManager.showReport(file);
                return null;
            }
            @Override protected void done() {
                jLabel2.setText("Report generated");
            }
        };
        worker.execute();
    }
}
