/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Walker
 */
public class CartManagement extends javax.swing.JFrame {

    /**
     * Creates new form CartManagement
     */
    private static String username;
    
    public CartManagement(String username) {
        initComponents();
        this.username = username;
        loadCartItems();
        setupTableClickListener();
    }

    private void loadCartItems() {
    DefaultTableModel model = new DefaultTableModel(
        new Object[][]{}, 
        new String[]{"Product Id", "Product Name", "Price"}
    ) {
        Class[] types = new Class[]{Integer.class, String.class, String.class};

        @Override
        public Class getColumnClass(int columnIndex) {
            return types[columnIndex];
        }
    };

    jTable1.setModel(model); // Set the table model
    model.setRowCount(0); // Clear existing rows

    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password)) {
        // Get the User_ID based on the username
        String getUserQuery = "SELECT id FROM users WHERE name = ? LIMIT 1";
        int userId = -1;

        try (PreparedStatement userStmt = con.prepareStatement(getUserQuery)) {
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                userId = userRs.getInt("id"); // Fetch User_ID
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Fetch only the cart items belonging to the logged-in user
        String query = "SELECT Product_id, Product_name, Product_Price FROM cart WHERE User_ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Product_id");
                String name = rs.getString("Product_name");
                String price = rs.getString("Product_Price");

                model.addRow(new Object[]{id, name, price});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading cart items: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

     
    private void setupTableClickListener() {
    jTable1.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            int row = jTable1.getSelectedRow();
            if (row != -1) {
                selectedProductId = (int) jTable1.getValueAt(row, 0); // Column 0 is Product_id
                selectedProductName = (String) jTable1.getValueAt(row, 1); // Column 1 is Product_name
            }
        }
    });
}
    
    private void processPayment() {
    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password)) {
        // Get the User_ID based on the username
        String getUserQuery = "SELECT id FROM users WHERE name = ? LIMIT 1";
        int userId = -1;

        try (PreparedStatement userStmt = con.prepareStatement(getUserQuery)) {
            userStmt.setString(1, username);
            ResultSet userRs = userStmt.executeQuery();

            if (userRs.next()) {
                userId = userRs.getInt("id"); // Fetch User_ID
            } else {
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Insert new order into orders table with current date
        String insertOrderQuery = "INSERT INTO orders (User_Id, totalAmount, Date) " +
                                  "SELECT ?, SUM(Product_Price), CURDATE() FROM cart WHERE User_ID = ?";

        try (PreparedStatement insertStmt = con.prepareStatement(insertOrderQuery)) {
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, userId);
            int rowsInserted = insertStmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Payment successful! Your order has been placed.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the cart after successful order placement
                clearCart(userId);
            } else {
                JOptionPane.showMessageDialog(this, "Payment failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

     private void clearCart(int userId) {
    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password)) {
        String deleteCartQuery = "DELETE FROM cart WHERE User_ID = ?";
        try (PreparedStatement stmt = con.prepareStatement(deleteCartQuery)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        }

        // Refresh the cart table in UI
        loadCartItems();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error clearing cart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

     
     private int selectedProductId = -1; 
    private String selectedProductName = "";
    
    private void removeFromCart() {
    String url = "jdbc:mysql://localhost:3306/bicycle_system";
    String user = "root";
    String password = "";

    try (Connection con = DriverManager.getConnection(url, user, password)) {
        String deleteQuery = "DELETE FROM cart WHERE Product_id = ? AND User_ID = (SELECT id FROM users WHERE name = ?)";
        try (PreparedStatement stmt = con.prepareStatement(deleteQuery)) {
            stmt.setInt(1, selectedProductId);
            stmt.setString(2, username);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(this, selectedProductName + " has been removed from the cart.", "Item Removed", JOptionPane.INFORMATION_MESSAGE);
                loadCartItems(); // Refresh the table
                selectedProductId = -1; // Reset selection
                selectedProductName = ""; // Reset selection
            } else {
                JOptionPane.showMessageDialog(this, "Failed to remove the item.", "Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        backbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Removebtn = new javax.swing.JButton();
        makepaymentbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 51));

        backbtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        backbtn.setForeground(new java.awt.Color(0, 153, 51));
        backbtn.setText("Back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product Id", "Product Name", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        Removebtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Removebtn.setForeground(new java.awt.Color(255, 0, 0));
        Removebtn.setText("Remove");
        Removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovebtnActionPerformed(evt);
            }
        });

        makepaymentbtn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        makepaymentbtn.setForeground(new java.awt.Color(0, 153, 51));
        makepaymentbtn.setText("Make Payment");
        makepaymentbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makepaymentbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(Removebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(makepaymentbtn)
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Removebtn)
                    .addComponent(makepaymentbtn))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void makepaymentbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makepaymentbtnActionPerformed
        int response = JOptionPane.showConfirmDialog(
        this,
        "Do you want to proceed with the transaction?",
        "Confirm Payment",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (response == JOptionPane.YES_OPTION) {
        processPayment();
    }
    }//GEN-LAST:event_makepaymentbtnActionPerformed

    private void RemovebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemovebtnActionPerformed
       int response = JOptionPane.showConfirmDialog(
        this,
        "Do you want to proceed with the transaction?",
        "Confirm Payment",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (response == JOptionPane.YES_OPTION) {
        processPayment(); // Calls the payment processing method
    }
    }//GEN-LAST:event_RemovebtnActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        CustomerInterface w = new CustomerInterface(username);
        this.dispose();
        w.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

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
            java.util.logging.Logger.getLogger(CartManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CartManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CartManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CartManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CartManagement(username).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Removebtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton makepaymentbtn;
    // End of variables declaration//GEN-END:variables
}
