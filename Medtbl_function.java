/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author paul.ube
 */
public class Medtbl_function {
    
    public static void showmed_tbl(JTable t_name, DefaultTableModel dt_model){
        try{
            PreparedStatement pstmt=null;
            ResultSet rs=null;
            Connection conn = SQLConnection.ConnDB();
            ResultSetMetaData rsmd1;
            
            t_name.setModel(dt_model);
            
           if(t_name.getRowCount()!=0){
                for (int i = t_name.getRowCount(); i > 0; i--){
                    t_name.remove(i);
                }
            }else{ 

            String sql = "SELECT med_name as 'Medicine Name', med_remain as 'Medicine Qty' FROM med_table;";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            rsmd1 = rs.getMetaData();
            int col = rsmd1.getColumnCount();

            Vector colv = new Vector();
            for(int i = 1; i<=col; i++){
                colv.addElement(rsmd1.getColumnLabel(i));
            }
            dt_model.setColumnIdentifiers(colv);
            while(rs.next()){
            Vector rowv = new Vector();
                for(int i = 1; i <= col; i++){
                    rowv.addElement(rs.getString(i));
                }
                dt_model.addRow(rowv);
            }
            t_name.setModel(dt_model);
           }
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   
}

