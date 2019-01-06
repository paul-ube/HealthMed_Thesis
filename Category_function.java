/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Benjie
 */
public class Category_function {
    
     public static void ill_category(JComboBox name){
        try{
            Connection conn = SQLConnection.ConnDB();
            PreparedStatement pstmt;
            
            if (name.getItemCount()==0){
            
            
            String yuh = "SELECT category_name FROM ill_category"
                    + " ORDER BY category_id;";
            pstmt = conn.prepareStatement(yuh);
            
            ResultSet rs = pstmt.executeQuery(yuh);
         
            while(rs.next()){
              
                String ya = rs.getString("category_name");
                name.addItem(ya);
            }

            }                                                    
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
     
     public static void med_category(JComboBox name){
        try{
            Connection conn = SQLConnection.ConnDB();
            PreparedStatement pstmt;
            
            
            String yuh = "SELECT category_name FROM med_category"
                    + " ORDER BY category_id;";
            pstmt = conn.prepareStatement(yuh);
            
            ResultSet rs = pstmt.executeQuery(yuh);
         
            while(rs.next()){
              
                String ya = rs.getString("category_name");
                name.addItem(ya);

            }                                                    
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
}
