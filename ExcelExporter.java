/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Benjie
 */
public class ExcelExporter {
    
    
    public ExcelExporter(){}
    
    public void exportTable (JTable table, File file) throws IOException{
        try{
        
        TableModel mod = table.getModel();
        FileWriter out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
            for (int i = 0; i<mod.getColumnCount();i++){
                bw.write(mod.getColumnName(i)+"\t");
            }
            bw.write("\n");
            
            for (int i = 0; i<mod.getRowCount(); i++){
                for (int j=0; j<mod.getColumnCount(); j++){
                    bw.write(mod.getValueAt(i,j).toString()+"\t");
                }
            bw.write("\n"); 
            }
        bw.close();
        
        
        }catch (Exception e){
            e.printStackTrace();
        }
        
        
        
    }
    
}
