/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Benjie
 */
//public class Auto_logout extends TimerTask {
//    
//    PreparedStatement pstmt = null;
//    Connection conn = SQLConnection.ConnDB();
//    
//        
//    
//    
//     public void run(){
//                                                            
//                                                            
//        try {
//
//            String auto_logout = "UPDATE med_dspnsd SET outTime = '"+taym+"'"
//                                 + " WHERE stud_no = '"+getnum+"'";
//            pstmt = conn.prepareStatement(auto_logout);
//            pstmt.executeUpdate();
//            JOptionPane.showMessageDialog(null, last+", "+first+" is now logging out");
//
//
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
//        }
//     }
//    
//}
