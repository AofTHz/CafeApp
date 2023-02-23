/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ShopApp;

import javax.swing.table.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AofTHz
 */
public class Menu2 extends javax.swing.JFrame {

    /**
     * Creates new form Menu2
     */
    public Menu2() {
        initComponents();
    }
    String TargetTable = "product";
    String TargetGroup = "";

    public ArrayList<product> ProductList() {
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

    public void show_user() {
        ArrayList<product> list = ProductList();
        DefaultTableModel model = (DefaultTableModel) TableShow.getModel();
        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getProductN();
            row[1] = list.get(i).getBrand();
            row[2] = list.get(i).getStock();
            row[3] = list.get(i).getPrice();
            row[4] = list.get(i).getExpDate();
            model.addRow(row);

        }
    }

    public ArrayList<product> ProductCheck() {
        ArrayList<product> ProductCheck = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain", UserPass.username, UserPass.password);

            Statement stm = con.createStatement();
            String get = "SELECT * FROM " + TargetTable + " WHERE ";
            String pname = NameSelect.getSelectedItem().toString();
            
            String bname = BrandSelect.getSelectedItem().toString();
            
            String stockcount = StockSelect.getSelectedItem().toString();
            
            String pricecount = PriceSelect.getSelectedItem().toString();
            
            String expdate = ExpSelect.getSelectedItem().toString();
            
           
            if(pname != "(None)") {
                pname = "ProductName = '"+pname+"' AND ";
                get = get + pname;
                System.out.println(get);
            }
            if(bname != "(None)") {
                bname = "Brand = '"+bname+"' AND ";
                get = get + bname;
            }
            if(stockcount != "(None)") {
                stockcount = "Stock "+stockcount+" AND ";
                get = get + stockcount;
            }
            if(pricecount != "(None)"){
                pricecount = "Price "+pricecount+" AND ";
                get = get + pricecount;
            }
            if(expdate != "(None)"){
                expdate = "YEAR(ExpireDate) = "+expdate+" AND ";
                get = get + expdate;
            }
             String endqry = " true = true";
             
             get = get + endqry;
             System.out.println(get);
            
            ResultSet rs = stm.executeQuery(get);
            product user;
            System.out.println("Check");

            while (rs.next()) {
                user = new product(rs.getString("productName"), rs.getString("Brand"), rs.getInt("Stock"), rs.getInt("Price"), rs.getString("ExpireDate"));
                ProductCheck.add(user);
            }
             
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.toString());
        }
        return ProductCheck;
    }

    public void show_search() {
        ArrayList<product> list = ProductCheck();
        DefaultTableModel model = (DefaultTableModel) TableShow.getModel();
        Object[] row = new Object[5];

        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getProductN();
            row[1] = list.get(i).getBrand();
            row[2] = list.get(i).getStock();
            row[3] = list.get(i).getPrice();
            row[4] = list.get(i).getExpDate();
            model.addRow(row);

        }
    }

    public ArrayList<product> SelectBox() {
        ArrayList<product> NameSelectBox = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectmain", UserPass.username, UserPass.password);

            Statement stm = con.createStatement();
            String GetType = "SELECT * FROM " + TargetTable + " GROUP BY " + TargetGroup;

            ResultSet ra = stm.executeQuery(GetType);
            product name;
            while (ra.next()) {
                name = new product(ra.getString("productName"), ra.getString("Brand"), ra.getInt("Stock"), ra.getInt("Price"), ra.getString("ExpireDate"));
                NameSelectBox.add(name);

            }
            
        } catch (Exception e) {
            System.out.println("!Error occured!");
        }
        return NameSelectBox;
    }

    public void set_name_box() {
        ArrayList<product> list = SelectBox();
        Object[] row = new Object[1];
        NameSelect.addItem("(None)");
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getProductN();
            System.out.println(i);
            if (NameSelect.getItemAt(i) != row[0].toString()) {
                
                System.out.println(NameSelect.getItemAt(i));
                System.out.println(row[0].toString());
                System.out.println("Added");
                NameSelect.addItem(row[0].toString());
                
            } else {
                System.out.println("Skip");
            }

        }
    }

    public void set_brand_box() {
        ArrayList<product> list = SelectBox();
        Object[] row = new Object[1];
        BrandSelect.addItem("(None)");
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getBrand();
            System.out.println(i);
            if (BrandSelect.getItemAt(i) != row[0].toString()) {

                System.out.println(BrandSelect.getItemAt(i));
                System.out.println(row[0].toString());
                System.out.println("Added");
                BrandSelect.addItem(row[0].toString());
            } else {
                System.out.println("Skip");
            }

        }
    }

    public void set_stock_box() {
        StockSelect.addItem("(None)");
        for (int i = 1; i < 7; i++) {
            int sItem = 10 * i;
            StockSelect.addItem("< " + sItem);
        }
    }

    public void set_price_box() {
        PriceSelect.addItem("(None)");
        for (int i = 1; i < 5; i++) {
            int sItem = 10 * i;
            PriceSelect.addItem("< " + sItem);
        }
    }

    public void set_exp_box() {
        ExpSelect.addItem("(None)");
        ExpSelect.addItem("2022");
        ExpSelect.addItem("2023");
        ExpSelect.addItem("2024");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableShow = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        BrandSelect = new javax.swing.JComboBox<>();
        NameSelect = new javax.swing.JComboBox<>();
        StockSelect = new javax.swing.JComboBox<>();
        PriceSelect = new javax.swing.JComboBox<>();
        ExpSelect = new javax.swing.JComboBox<>();
        SearchSubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        OpenInsert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DatabaseView");
        setBackground(new java.awt.Color(102, 102, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("FC Active", 0, 18)); // NOI18N
        setForeground(java.awt.Color.white);
        setResizable(false);

        TableShow.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        TableShow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TableShow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductName", "Brand", "Stock", "Price", "ExprieDate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TableShow.setToolTipText("");
        TableShow.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TableShow.setAutoscrolls(false);
        TableShow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TableShow.setName(""); // NOI18N
        TableShow.setSelectionBackground(new java.awt.Color(255, 255, 255));
        TableShow.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TableShow);

        jButton1.setBackground(new java.awt.Color(238, 198, 177));
        jButton1.setText("RefreshData");
        jButton1.setToolTipText("");
        jButton1.setAutoscrolls(true);
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(234, 227, 218));

        BrandSelect.setBackground(new java.awt.Color(162, 179, 204));
        BrandSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BrandSelect.setForeground(new java.awt.Color(255, 255, 255));
        BrandSelect.setBorder(null);
        BrandSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrandSelectActionPerformed(evt);
            }
        });

        NameSelect.setBackground(new java.awt.Color(162, 179, 204));
        NameSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NameSelect.setForeground(new java.awt.Color(255, 255, 255));
        NameSelect.setBorder(null);
        NameSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameSelectActionPerformed(evt);
            }
        });

        StockSelect.setBackground(new java.awt.Color(162, 179, 204));
        StockSelect.setForeground(new java.awt.Color(255, 255, 255));
        StockSelect.setBorder(null);
        StockSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockSelectActionPerformed(evt);
            }
        });

        PriceSelect.setBackground(new java.awt.Color(162, 179, 204));
        PriceSelect.setForeground(new java.awt.Color(255, 255, 255));
        PriceSelect.setBorder(null);
        PriceSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PriceSelectActionPerformed(evt);
            }
        });

        ExpSelect.setBackground(new java.awt.Color(162, 179, 204));
        ExpSelect.setForeground(new java.awt.Color(255, 255, 255));
        ExpSelect.setBorder(null);
        ExpSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExpSelectActionPerformed(evt);
            }
        });

        SearchSubmit.setBackground(new java.awt.Color(233, 162, 159));
        SearchSubmit.setText("Search");
        SearchSubmit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        SearchSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSubmitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("ชื่อสินค้า");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("แบรนสินค้า");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("สต็อคสินค้า");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("ราคาสินค้า");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("วันหมดอายุ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NameSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BrandSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(StockSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PriceSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExpSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SearchSubmit, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SearchSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BrandSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(StockSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriceSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ExpSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(16, 16, 16))
        );

        OpenInsert.setBackground(new java.awt.Color(204, 255, 204));
        OpenInsert.setText("InsertData");
        OpenInsert.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        OpenInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenInsertActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 204));
        jButton3.setText("EditData");
        jButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OpenInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableShow.getModel();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TableShow.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(renderer.RIGHT);
        model.setRowCount(0);

        //ทำการเคลีย combobox
        NameSelect.removeAllItems();
        BrandSelect.removeAllItems();
        StockSelect.removeAllItems();
        PriceSelect.removeAllItems();
        ExpSelect.removeAllItems();

        //เคลียสำเร็จ
        TargetGroup = "productName";
        set_name_box();
        TargetGroup = "Brand";
        set_brand_box();
        TargetGroup = "Stock";
        set_stock_box();
        TargetGroup = "Price";
        set_price_box();
        TargetGroup = "ExpireDate";
        set_exp_box();
        show_user();

        //userList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BrandSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrandSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BrandSelectActionPerformed

    private void NameSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameSelectActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_NameSelectActionPerformed

    private void StockSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StockSelectActionPerformed

    private void PriceSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PriceSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PriceSelectActionPerformed

    private void ExpSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExpSelectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExpSelectActionPerformed

    private void SearchSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSubmitActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TableShow.getModel();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) TableShow.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment(renderer.RIGHT);
        model.setRowCount(0);
        show_search();

    }//GEN-LAST:event_SearchSubmitActionPerformed

    private void OpenInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenInsertActionPerformed
        // TODO add your handling code here:
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    new Menu3().setVisible(true);
                    

            }
        });
    }//GEN-LAST:event_OpenInsertActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu4().setVisible(true);
            }
        });
        
    }//GEN-LAST:event_jButton3ActionPerformed

    public void List() {
        DefaultTableModel tblModel = (DefaultTableModel) TableShow.getModel();
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu2().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BrandSelect;
    private javax.swing.JComboBox<String> ExpSelect;
    private javax.swing.JComboBox<String> NameSelect;
    private javax.swing.JButton OpenInsert;
    private javax.swing.JComboBox<String> PriceSelect;
    private javax.swing.JButton SearchSubmit;
    private javax.swing.JComboBox<String> StockSelect;
    private javax.swing.JTable TableShow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
