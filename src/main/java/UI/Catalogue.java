/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import UI.userManagement.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Walker
 */
public class Catalogue extends javax.swing.JFrame {
    /**
     * Creates new form Catalogue
     */
    private static String username;
    
    public Catalogue(String username) {
        initComponents();
        loadBicycles(); 
        setupTableClickListener();
        this.username = username;
    }

    private void loadBicycles() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear existing rows

    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = con.prepareStatement("SELECT name, description, price, size, type FROM Bicycle");
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            String price = rs.getString("price");
            String size = rs.getString("size"); // Fetching size
            String type = rs.getString("type");

            // Add row with size included
            model.addRow(new Object[]{name, description, price, size, type});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading bicycles: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
     private void setupTableClickListener() {
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = jTable1.getSelectedRow();
                if (row != -1) {
                    String name = jTable1.getValueAt(row, 0).toString();
                    String description = jTable1.getValueAt(row, 1).toString();
                    String price = jTable1.getValueAt(row, 2).toString();
                    String type = jTable1.getValueAt(row, 3).toString();

                    int response = JOptionPane.showConfirmDialog(
                            Catalogue.this,
                            "Do you want to add \"" + name + "\" to the cart?",
                            "Add to Cart",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        addToCart(name, price);
                    }
                }
            }
        });
    }
     
     private void addToCart(String name, String price) {
    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password)) {
        // Retrieve User_ID based on the logged-in username
        String getUserQuery = "SELECT id FROM users WHERE name = ? LIMIT 1";
        int userId = -1;

        try (PreparedStatement userStmt = con.prepareStatement(getUserQuery)) {
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                userId = userRs.getInt("id"); // Fetch User_ID
            } else {
                JOptionPane.showMessageDialog(this, "User not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Retrieve the ID of the selected bicycle
        String selectQuery = "SELECT id FROM Bicycle WHERE name = ? LIMIT 1";
        int productId = -1;

        try (PreparedStatement selectStmt = con.prepareStatement(selectQuery)) {
            selectStmt.setString(1, name);
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                productId = rs.getInt("id"); // Get the bicycle's ID
            } else {
                JOptionPane.showMessageDialog(this, "Bicycle not found in the database!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Insert into the cart with the retrieved User_ID
        String insertQuery = "INSERT INTO cart (User_ID, Product_id, Product_name, Product_Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertStmt = con.prepareStatement(insertQuery)) {
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, productId);
            insertStmt.setString(3, name);
            insertStmt.setInt(4, Integer.parseInt(price));

            int rowsInserted = insertStmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, name + " added to " + username+"'s cart" , "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add " + name + " to cart!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backbtn = new javax.swing.JButton();
        viewCartBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Name", "Description", "Price", "Size", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        backbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        backbtn.setForeground(new java.awt.Color(0, 153, 51));
        backbtn.setText("Back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        viewCartBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        viewCartBtn.setForeground(new java.awt.Color(255, 153, 51));
        viewCartBtn.setText("View Cart");
        viewCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCartBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        CustomerInterface w = new CustomerInterface(username);
        this.dispose();
        w.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void viewCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCartBtnActionPerformed
        new CartManagement(username).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_viewCartBtnActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> new Catalogue(username).setVisible(true));
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Catalogue(username).setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton viewCartBtn;
    // End of variables declaration//GEN-END:variables
}
