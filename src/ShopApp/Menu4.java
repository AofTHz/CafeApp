/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ShopApp;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AofTHz
 */
public class Menu4 extends javax.swing.JFrame {
    boolean load;
    

    /**
     * Creates new form Menu4
     */
    public Menu4() {
        initComponents();
    }
    static String TargetTable = "product";
    static boolean CheckEnable = false;

    public static ArrayList<product> ProductList() {
        ArrayList<product> ProductList = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain", UserPass.username, UserPass.password);

            Statement stm = con.createStatement();

            String get = "SELECT * FROM " + TargetTable;

            ResultSet rs = stm.executeQuery(get);
            product user;
            System.out.println("Check");

            while (rs.next()) {
                user = new product(rs.getString("productName"), rs.getString("Brand"), rs.getInt("Stock"), rs.getInt("Price"), rs.getString("ExpireDate"));
                ProductList.add(user);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return ProductList;
    }

    public static ArrayList<product> ProductLoad() {
        ArrayList<product> ProductLoad = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain",UserPass.username, UserPass.password);

            Statement stm = con.createStatement();
            String getSelect = (SelectRow.getSelectedItem().toString());
            String[] getSelectArr = getSelect.split(", ", 2);
            String get = "SELECT * FROM " + TargetTable+" WHERE productName = '" + getSelectArr[0] + "' AND Brand = '" + getSelectArr[1] + "'";

            ResultSet rs = stm.executeQuery(get);
            product user;
            System.out.println("Check");

            while (rs.next()) {
                user = new product(rs.getString("productName"), rs.getString("Brand"), rs.getInt("Stock"), rs.getInt("Price"), rs.getString("ExpireDate"));
                ProductLoad.add(user);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return ProductLoad;
        
    }
    
     public static ArrayList<product> ProductDelete() {
        ArrayList<product> ProductDelete = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain",UserPass.username, UserPass.password);

            Statement stm = con.createStatement();
            String getSelect = (SelectRow.getSelectedItem().toString());
            String[] getSelectArr = getSelect.split(", ", 2);
            String del = "DELETE FROM product WHERE productName = '"+getSelectArr[0].toString()+"' AND Brand = '"+getSelectArr[1]+"'";
            System.out.println(del);
            try{
                stm.executeUpdate(del);
                Status.setText("สถานะ : ลบสำเร็จ");
            }catch(Exception e){
                Status.setText("สถานะ : ลบล้มเหลว");
                e.printStackTrace();

            }
            ProductUpdate();
            PNameInsert.setText("");
            BInsert.setText("");
            StockInsert.setText("");
            PriceInsert.setText("");
            expInsert.setText("");
            CheckEnable = false;
            show_user();
            ;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return ProductDelete;
        
    }
    
    public static ArrayList<product> ProductUpdate() {
        ArrayList<product> ProductUpdate = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain", UserPass.username, UserPass.password);

            Statement stm = con.createStatement();

            String pnField = PNameInsert.getText();
            String bField = BInsert.getText();
            String sField = StockInsert.getText();
            String pField = PriceInsert.getText();
            String expField = expInsert.getText();
            
            String getSelect = (SelectRow.getSelectedItem().toString());
            String[] getSelectArr = getSelect.split(", ", 2);
            for (String a : getSelectArr) {
                System.out.println(a);
            }

            if (pnField.isEmpty() == false && bField.isEmpty() == false && sField.isEmpty() == false && pField.isEmpty() == false && expField.isEmpty() == false) {
                try {
                    String get = "UPDATE " + TargetTable + " SET " + "productName = '"+pnField+"', Brand = '"+bField+"', Stock = "+sField+", Price = "+pField+", ExpireDate = '"+expField+"'" + " WHERE productName = '" + getSelectArr[0] + "' AND Brand = '" + getSelectArr[1] + "'";
                    System.out.println(get);
                    stm.executeUpdate(get);
                    System.out.println("Updated");
                    Status.setText("สถานะ : อัพเดทข้อมูลสำเร็จ");
                } catch (Exception e) {
                    System.out.println("Failed to update data");
                    Status.setText("สถานะ : อัพเดทข้อมูลล้มเหลว");
                }

            }else{
                System.out.println("Empty Data");
                Status.setText("สถานะ : กรุณาใส่ข้อมูลให้ครับ");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return ProductUpdate;
    }

    public static void show_user() {
        
        System.out.println("FunctionLoad");
        ArrayList<product> list = ProductList();
        System.out.println("List");
        String row;
        SelectRow.removeAllItems();
        for (int i = 0; i < list.size(); i++) {
            row = list.get(i).getProductN().toString() + " , " + list.get(i).getBrand().toString();

            SelectRow.addItem(row);

        }
        CheckEnable = true;
    }
    public void load_user() {
        ArrayList<product> list = ProductLoad();
        Object[] row = new Object[5];
        
            row[0] = list.get(0).getProductN();
            row[1] = list.get(0).getBrand();
            row[2] = list.get(0).getStock();
            row[3] = list.get(0).getPrice();
            row[4] = list.get(0).getExpDate();
            
             PNameInsert.setText(row[0].toString());
            BInsert.setText(row[1].toString());
            StockInsert.setText(row[2].toString());
            PriceInsert.setText(row[3].toString());
            expInsert.setText(row[4].toString());
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        SelectRow = new javax.swing.JComboBox<>();
        expInsert = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        PNameInsert = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        BInsert = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        StockInsert = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        PriceInsert = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        UpdateButton = new javax.swing.JButton();
        DelButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SelectRow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        SelectRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectRowActionPerformed(evt);
            }
        });
        getContentPane().add(SelectRow, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 14, 280, 30));

        expInsert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        expInsert.setEnabled(false);
        expInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expInsertActionPerformed(evt);
            }
        });
        getContentPane().add(expInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 75, 95, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ExpireDate");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 57, 95, 12));

        Status.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Status.setText("สถานะ : กำลังรอ");
        getContentPane().add(Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 111, 523, 30));

        PNameInsert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PNameInsert.setEnabled(false);
        PNameInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNameInsertActionPerformed(evt);
            }
        });
        getContentPane().add(PNameInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 75, 112, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("ProductName");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 58, 118, 11));

        BInsert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BInsert.setEnabled(false);
        BInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInsertActionPerformed(evt);
            }
        });
        getContentPane().add(BInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 75, 120, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Brand");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(136, 58, 67, 11));

        StockInsert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        StockInsert.setEnabled(false);
        StockInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockInsertActionPerformed(evt);
            }
        });
        getContentPane().add(StockInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 75, 80, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText(" Stock");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(258, 57, 80, 12));

        PriceInsert.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        PriceInsert.setEnabled(false);
        PriceInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceInsertActionPerformed(evt);
            }
        });
        getContentPane().add(PriceInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 75, 78, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Price");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 57, 60, 12));

        UpdateButton.setBackground(new java.awt.Color(255, 255, 204));
        UpdateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UpdateButton.setText("Update");
        UpdateButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        UpdateButton.setEnabled(false);
        UpdateButton.setPreferredSize(new java.awt.Dimension(75, 45));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(UpdateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 60, 89, -1));

        DelButton.setBackground(new java.awt.Color(255, 255, 153));
        DelButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DelButton.setText("LoadData");
        DelButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        DelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(DelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 14, 79, 31));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void expInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expInsertActionPerformed

    private void StockInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockInsertActionPerformed

    private void PriceInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceInsertActionPerformed

    private void SelectRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectRowActionPerformed
        // TODO add your handling code here:
        if(CheckEnable == true){
            if(SelectRow.getSelectedItem() != null){
            load_user();
            }
        }
    }//GEN-LAST:event_SelectRowActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        // TODO add your handling code here:
        /* System.out.println(SelectRow.getSelectedIndex());
            System.out.println(SelectRow.getSelectedItem());
            String getSelect = (SelectRow.getSelectedItem().toString());
            String[] getSelectArr = getSelect.split(", ",2);
            for(String a : getSelectArr){
                System.out.println(a);
            }
        System.out.println(getSelectArr[0]);
         */
        ProductUpdate();
        PNameInsert.setText("");
        BInsert.setText("");
        StockInsert.setText("");
        PriceInsert.setText("");
        expInsert.setText("");
        CheckEnable = false;
        show_user();
        
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void DelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("LoadButtonClick");
        if (load == false){
            System.out.println("LoadFalse");
        show_user();
        PNameInsert.setEnabled(true);
        BInsert.setEnabled(true);
        StockInsert.setEnabled(true);
        PriceInsert.setEnabled(true);
        expInsert.setEnabled(true);
        UpdateButton.setEnabled(true);
        DelButton.setText("Delete");
        Color customcolor = new Color(255,102,102);
        DelButton.setBackground(customcolor);
        load = true;
        }else{
            System.out.println("DELETE LOADING");
            ProductDelete();
        }
    }//GEN-LAST:event_DelButtonActionPerformed

    private void BInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BInsertActionPerformed

    private void PNameInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNameInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNameInsertActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        show_user();
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu4().setVisible(true);
            }

        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField BInsert;
    private javax.swing.JButton DelButton;
    private static javax.swing.JTextField PNameInsert;
    private static javax.swing.JTextField PriceInsert;
    private static javax.swing.JComboBox<String> SelectRow;
    private static javax.swing.JLabel Status;
    private static javax.swing.JTextField StockInsert;
    private javax.swing.JButton UpdateButton;
    private static javax.swing.JTextField expInsert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
