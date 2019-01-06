/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import static cms.Home.order_table;
import static cms.function.Loginfunction.Firstname;
import static cms.function.Loginfunction.Lastname;
import static cms.function.Loginfunction.user_ID;
import cms.function.SQLConnection;
import static cms.function.tbl_function.tbl_function;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;

/**
 *
 * @author Benjie
 */
public class frm_preview extends javax.swing.JFrame {

    /**
     * Creates new form frm_preview
     */
    Connection conn = SQLConnection.ConnDB();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Statement st = null;
    Date max_date = new Date();
    public static SimpleDateFormat abc = new SimpleDateFormat("YYYY-MM-dd");
    public static LocalDate nowna = LocalDate.now();
    String userReceivedby = "";
    String userCreatedby = "";
    
    
    
    public frm_preview() {
        initComponents();
        tbl_function(tbl_result_med);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        lbl_stat = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        txt_supid3 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_poid = new javax.swing.JTextField();
        txt_sup = new javax.swing.JTextField();
        txt_supid4 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_result_med = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        txt_date = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txt_recibdate = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(10, 61, 115));
        jPanel2.setPreferredSize(new java.awt.Dimension(1299, 96));

        jLabel45.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("Purchase Orders Breakdown");

        lbl_stat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_stat.setForeground(new java.awt.Color(204, 0, 51));
        lbl_stat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_stat.setText("asdasdasdasd");
        lbl_stat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(lbl_stat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_stat, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 6));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115)));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(10, 61, 115));
        jButton2.setText("Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(120, 120, 120));
        jLabel43.setText("Supplier Name");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(120, 120, 120));
        jLabel48.setText("Received by");

        txt_supid3.setEditable(false);
        txt_supid3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_supid3.setForeground(new java.awt.Color(10, 61, 115));
        txt_supid3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_supid3KeyTyped(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(120, 120, 120));
        jLabel44.setText("Purchase Order Number");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(120, 120, 120));
        jLabel47.setText("Prepared by");

        txt_poid.setEditable(false);
        txt_poid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_poid.setForeground(new java.awt.Color(10, 61, 115));
        txt_poid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_poidKeyTyped(evt);
            }
        });

        txt_sup.setEditable(false);
        txt_sup.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_sup.setForeground(new java.awt.Color(10, 61, 115));
        txt_sup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_supKeyTyped(evt);
            }
        });

        txt_supid4.setEditable(false);
        txt_supid4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_supid4.setForeground(new java.awt.Color(10, 61, 115));
        txt_supid4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_supid4KeyTyped(evt);
            }
        });

        jScrollPane2.setBorder(null);

        tbl_result_med.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_result_med.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine ID", "Name", "Quantity Ordered"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_result_med.setOpaque(false);
        tbl_result_med.setRowHeight(27);
        tbl_result_med.getTableHeader().setReorderingAllowed(false);
        tbl_result_med.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_result_medMouseClicked(evt);
            }
        });
        tbl_result_med.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_result_medKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbl_result_medKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_result_med);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(120, 120, 120));
        jLabel46.setText("Created Date");

        txt_date.setEditable(false);
        txt_date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_date.setForeground(new java.awt.Color(10, 61, 115));
        txt_date.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_dateKeyTyped(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton3.setForeground(new java.awt.Color(10, 61, 115));
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt_recibdate.setEditable(false);
        txt_recibdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_recibdate.setForeground(new java.awt.Color(10, 61, 115));
        txt_recibdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_recibdateKeyTyped(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(120, 120, 120));
        jLabel49.setText("Date Received");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_poid, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_supid3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_recibdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(txt_supid4, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_poid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_supid3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_recibdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_supid4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_poidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_poidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_poidKeyTyped

    private void txt_dateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dateKeyTyped

    private void txt_supid3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_supid3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_supid3KeyTyped

    private void txt_supKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_supKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_supKeyTyped

    private void txt_supid4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_supid4KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_supid4KeyTyped

    private void tbl_result_medMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_result_medMouseClicked

    }//GEN-LAST:event_tbl_result_medMouseClicked

    private void tbl_result_medKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_result_medKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_result_medKeyPressed

    private void tbl_result_medKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_result_medKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_result_medKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        int row = order_table.getSelectedRow();
        String poid = (order_table.getModel().getValueAt(row, 0).toString());
        System.out.println(poid);
        txt_poid.setText(poid);
        previewOrder();
        showUserNameCreate();
        showUserNameReCa();
    }//GEN-LAST:event_formWindowActivated

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_result_med.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\medOrdersPrev.jrxml";
        
        int x = mod.getRowCount();
        String poid = txt_poid.getText();
        String supp = txt_sup.getText();
        String txtdate = txt_date.getText();
        String prepared = txt_supid3.getText();
        String received = txt_supid4.getText();
        String status = lbl_stat.getText();
        String recibdate = txt_recibdate.getText();
        
        String lblrecibOrCanby = "";
        String lblrecibOrCandate = "";
        
        
        
        if("CANCELLED".equals(status)){
            lblrecibOrCanby = "Cancelled by";
            lblrecibOrCandate = "Cancelled Date";  
        }else{
            lblrecibOrCanby = "Received by";
            lblrecibOrCandate = "Received Date";
        }
        
        
        
        String nurseName = Firstname +" "+ Lastname;

        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("poid", poid);
                params.put("supp", supp);
                params.put("txtdate", txtdate);
                params.put("prepared", prepared);
                params.put("received", received);
                params.put("status", status);
                params.put("recibdate", recibdate);
                params.put("nurseName", nurseName);
                
                params.put("lblrecibOrCanby", lblrecibOrCanby);
                params.put("lblrecibOrCandate", lblrecibOrCandate);
                
                
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
                JasperPrintManager.printReport(jp, false);

                JasperExportManager.exportReportToPdfFile(jp,"report.pdf");
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("report.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }

            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_recibdateKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recibdateKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_recibdateKeyTyped

    public void previewOrder(){
        
        DefaultTableModel mod = (DefaultTableModel)tbl_result_med.getModel();
        mod.setRowCount(0);
        
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM med_table INNER JOIN med_orders ON"
                    + " med_orders.med_id = med_table.med_id"
                    + " INNER JOIN purchase_orders ON purchase_orders.po_id = med_orders.po_order WHERE purchase_orders.po_id = '"+txt_poid.getText()+"';";
            rs = st.executeQuery(query);

            while (rs.next()){
                String med_id = rs.getString("med_id");
                String med_name = rs.getString("med_name");
                String med_qnty = rs.getString("med_qnty");
                
                
                String po_sup = rs.getString("po_sup");
                String po_date = rs.getString("po_date");
                String po_prepby = rs.getString("po_prepby");
                String po_recby = rs.getString("po_recby");
                String po_status = rs.getString("po_status");
                String po_recibdate = rs.getString("po_recibdate");
                
                mod.addRow(new Object[]{med_id, med_name, med_qnty});
                
                txt_sup.setText(po_sup);
                txt_date.setText(po_date);
                //txt_supid3.setText(po_prepby);
                //txt_supid4.setText(po_recby);
                lbl_stat.setText(po_status);
                txt_recibdate.setText(po_recibdate);
                
                
                userReceivedby = po_prepby;
                userCreatedby = po_recby;
                        
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        
        
        
    }
    
    
    public void showUserNameCreate(){
        
        
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl WHERE id_no = '"+userCreatedby+"';";
            rs = st.executeQuery(query);

            while (rs.next()){
                String fname = rs.getString("firstname");
                String mname = rs.getString("middlename");
                String lname = rs.getString("lastname");
                
                
                String createName = fname+" "+mname+" "+lname;
                txt_supid3.setText(createName);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        
        
        
    }
    
    public void showUserNameReCa(){
        
        
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl WHERE id_no = '"+userReceivedby+"';";
            rs = st.executeQuery(query);

            while (rs.next()){
                String fname = rs.getString("firstname");
                String mname = rs.getString("middlename");
                String lname = rs.getString("lastname");
                
                
                String createName = fname+" "+mname+" "+lname;
                txt_supid4.setText(createName);
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        
        
        
    }
    
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
            java.util.logging.Logger.getLogger(frm_preview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_preview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_preview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_preview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_preview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_stat;
    private javax.swing.JTable tbl_result_med;
    private javax.swing.JTextField txt_date;
    public static javax.swing.JTextField txt_poid;
    private javax.swing.JTextField txt_recibdate;
    private javax.swing.JTextField txt_sup;
    private javax.swing.JTextField txt_supid3;
    private javax.swing.JTextField txt_supid4;
    // End of variables declaration//GEN-END:variables
}
