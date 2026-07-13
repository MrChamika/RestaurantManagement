package com.restaurant.report;

import com.restaurant.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.util.HashMap;

public class ReportManager {
    public static void showReport(String reportFileName) {
        try {
            InputStream reportStream = ReportManager.class.getClassLoader()
                .getResourceAsStream("reports/" + reportFileName);
            if (reportStream == null) {
                reportStream = new java.io.FileInputStream("reports/" + reportFileName);
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null,
                "Error loading report: " + e.getMessage(),
                "Report Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
}
