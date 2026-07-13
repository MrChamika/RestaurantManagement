package com.restaurant.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane;
    private DashboardPanel dashboardPanel;
    private MenuPanel menuPanel;
    private CustomerPanel customerPanel;
    private StaffPanel staffPanel;
    private PlaceOrderPanel placeOrderPanel;
    private ReportViewerFrame reportViewerFrame;

    public MainFrame() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();
        dashboardPanel = new DashboardPanel();
        menuPanel = new MenuPanel();
        customerPanel = new CustomerPanel();
        staffPanel = new StaffPanel();
        placeOrderPanel = new PlaceOrderPanel();
        reportViewerFrame = new ReportViewerFrame();

        tabbedPane.addTab("Dashboard", dashboardPanel);
        tabbedPane.addTab("Menu Items", menuPanel);
        tabbedPane.addTab("Customers", customerPanel);
        tabbedPane.addTab("Staff", staffPanel);
        tabbedPane.addTab("Place Order", placeOrderPanel);
        tabbedPane.addTab("Reports", reportViewerFrame);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Restaurant Management System\nVersion 1.0"));
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        setTitle("Restaurant Management System");
    }
}
