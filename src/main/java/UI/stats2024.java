/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.FunctionalityManagement.StatisticsManagement.ViewStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.*;
import java.awt.BorderLayout;

/**
 *
 * @author Walker
 */
public class stats2024 extends javax.swing.JFrame {

    /**
     * Creates new form stats2022
     */
    private static String username;

    public stats2024(String username) {
        initComponents();
         displayChart();
         this.username = username;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        graph = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        backbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        graph.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout graphLayout = new javax.swing.GroupLayout(graph);
        graph.setLayout(graphLayout);
        graphLayout.setHorizontalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphLayout.setVerticalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 505, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Viner Hand ITC", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Velo Vault");

        backbtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        backbtn.setForeground(new java.awt.Color(0, 153, 51));
        backbtn.setText("Back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(258, 258, 258))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(graph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        ViewStatistics w = new ViewStatistics(username);
        this.dispose();
        w.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    /**
     * @param args the command line arguments
     */
private void displayChart() {
    CategoryDataset dataset = createDataset();
    JFreeChart barChart = ChartFactory.createBarChart(
        "Sales Statistics for 2024", 
        "Month", 
        "Number of Sales", 
        dataset
    );
    
    // Change bar color to blue
    org.jfree.chart.plot.CategoryPlot plot = barChart.getCategoryPlot();
    org.jfree.chart.renderer.category.BarRenderer renderer = (org.jfree.chart.renderer.category.BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, java.awt.Color.BLUE);
    
    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setPreferredSize(new java.awt.Dimension(graph.getWidth(), graph.getHeight()));

    graph.removeAll();
    graph.setLayout(new java.awt.BorderLayout());
    graph.add(chartPanel, BorderLayout.CENTER);
    graph.revalidate();
    graph.repaint();
}
    
    private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = con.prepareStatement("SELECT month, noOfSales FROM statistics WHERE year = 2024 ORDER BY month ASC");
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String month = rs.getString("month");
            int sales = rs.getInt("noOfSales");
            dataset.addValue(sales, "Sales", month);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return dataset;
}
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(stats2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stats2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stats2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stats2022.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stats2022(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JPanel graph;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
