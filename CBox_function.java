/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author paul.ube
 */
public class CBox_function {
    
    public static void CBox_function(JComboBox name){ //show all illness
        
        
        try{    
        
            if (name.getItemCount()==0){
            Connection conn = SQLConnection.ConnDB();
            PreparedStatement pstmt = null;
            
            
            String yuh = "SELECT ill_name FROM ill_table"
                    + " WHERE ill_id != '1000'"
                    + " ORDER BY ill_name;";
            pstmt = conn.prepareStatement(yuh);
            
            ResultSet rs = pstmt.executeQuery(yuh);
         
            while(rs.next()){
              
                String ya = rs.getString("ill_name");
                name.addItem(ya);
            
                
            }
            name.setSelectedIndex(0);
            
            }

            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
                } 
            
    }
    
    public static void CBox_function02(JComboBox name){ //show all ...
        
        
        try{    
        
            if (name.getItemCount()==0){
            Connection conn = SQLConnection.ConnDB();
            PreparedStatement pstmt = null;
            
            
            String yuh = "SELECT med_name FROM med_table"
                    + " WHERE med_name IN ('Hepatitis B Vaccine', 'Influenza Vaccine')"
                    + " ORDER BY med_id;";
            pstmt = conn.prepareStatement(yuh);
            
            ResultSet rs = pstmt.executeQuery(yuh);
         
            while(rs.next()){
              
                String ya = rs.getString("ill_name");
                name.addItem(ya);
            
                
            }
            name.setSelectedIndex(0);
            
            }

            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
                } 
            
    }
    
    
    
    
    public static void CBox_function01(JComboBox name1){ // show all medicines
        
        
        try{
            if (name1.getItemCount()==0){
                
                
            Connection conn = SQLConnection.ConnDB();
            PreparedStatement pstmt;
            
            
            String yah = "SELECT med_name FROM med_table"
                    + " ORDER BY med_name;";
            pstmt = conn.prepareStatement(yah);
            
            ResultSet rs = pstmt.executeQuery(yah);
         
            while(rs.next()){
              
                String ya = rs.getString("med_name");
                name1.addItem(ya);
            }
            name1.setSelectedIndex(0);
            }                                                    
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    
  
    
}
