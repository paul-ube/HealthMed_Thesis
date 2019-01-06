/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Benjie
 */
public class tbl_function{
    
    
    public static void tbl_function(JTable hello) {
        
       
        hello.getTableHeader().setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        
        hello.getTableHeader().setForeground(new Color(10,61,115));
        hello.getTableHeader().setReorderingAllowed(false);
        hello.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        hello.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        hello.setForeground(Color.BLACK);
        hello.setFillsViewportHeight(true);
        
        
        
        
        
        
    }

    
    
    
}
