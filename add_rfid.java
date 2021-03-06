/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;


import cms.function.SQLConnection;
import com.sun.glass.events.KeyEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Benjie
 */
public class add_rfid extends javax.swing.JDialog {
    
    VideoCapture  webSource = null;
    Mat frame = new Mat();
    Size sz = new Size(160, 160);
    MatOfByte mem = new MatOfByte();
    byte[] person_image = null;
    
    
    int count = 0;
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = SQLConnection.ConnDB();

    /**
     * Creates new form add_rfid
     */
    public add_rfid() {
        initComponents();
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
        txt_rfid = new javax.swing.JTextField();
        txt_fname = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115), 2));
        jPanel1.setForeground(new java.awt.Color(10, 61, 115));

        txt_rfid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rfid.setForeground(new java.awt.Color(46, 47, 51));
        txt_rfid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rfidFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rfidFocusLost(evt);
            }
        });
        txt_rfid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_rfidActionPerformed(evt);
            }
        });
        txt_rfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rfidKeyTyped(evt);
            }
        });

        txt_fname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_fname.setForeground(new java.awt.Color(40, 40, 40));
        txt_fname.setText("RFID");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(120, 120, 120));
        jLabel82.setText("Tap RFID Here");

        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_id.setForeground(new java.awt.Color(40, 40, 40));
        txt_id.setText("RFID");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Save Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(10, 61, 115));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Add RFID");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_rfid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_fname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(txt_fname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id)
                .addGap(23, 23, 23)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_rfid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_rfidFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rfidFocusGained

        
    }//GEN-LAST:event_txt_rfidFocusGained

    private void txt_rfidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rfidFocusLost

    }//GEN-LAST:event_txt_rfidFocusLost

    private void txt_rfidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rfidKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE)||
            (c==KeyEvent.VK_ENTER))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }

    }//GEN-LAST:event_txt_rfidKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txt_rfid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please input necessary information");
        }else{
            try {
                String qqq = "SELECT RFID FROM patient_tbl WHERE RFID = '"+txt_rfid.getText()+"'";
                pstmt = conn.prepareStatement(qqq);
                rs = pstmt.executeQuery();
                if (rs.next()){
                        JOptionPane.showMessageDialog(null, "This ID is already registered", null, JOptionPane.INFORMATION_MESSAGE);
                }else{
                    String query = "UPDATE patient_tbl SET"
                            + " RFID = '"+txt_rfid.getText()+"'"
                            + " WHERE id_no = '"+txt_id.getText()+"'";
                    pstmt = conn.prepareStatement(query);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Profile Updated!");
                    this.dispose();
                    Home.show_inc();
                       
                }
            } catch (SQLException ex) {
                Logger.getLogger(add_rfid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_rfidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_rfidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_rfidActionPerformed
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new add_rfid().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JLabel txt_fname;
    public static javax.swing.JLabel txt_id;
    private javax.swing.JTextField txt_rfid;
    // End of variables declaration//GEN-END:variables
}
