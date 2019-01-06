/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;
import static cms.Medication.lbl_id;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
public class DBConnect {   
public DBConnect(){
    Connection conn = null;
    try{
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms_v2.0","root","");
    } catch (Exception e) {
        System.out.println("Error in Connection"+ e);
 
    }
    
    
    try {
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\medicalrecords.jrxml");

            String ci = "SELECT * FROM patient_tbl" +
                    "INNER JOIN father_table ON patient_tbl.id_no = father_table.id_no" +
                    "INNER JOIN mother_table ON patient_tbl.id_no = mother_table.id_no" +
                    "INNER JOIN spouse_table ON patient_tbl.id_no = spouse_table.id_no" +
                    "INNER JOIN guardian_table ON patient_tbl.id_no = guardian_table.id_no" +
                    "INNER JOIN photo ON patient_tbl.id_no = photo.id_no" +
                    "WHERE patient_tbl.id_no = '10000202952';";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(ci);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp=JasperFillManager.fillReport(jr, null, conn);
            
            JasperExportManager.exportReportToPdfFile(jp,"medicalrecords.pdf");
                
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File("medicalrecords.pdf");
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                        // no application registered for PDFs
                    }
                }
            
            
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    
    
    
    
    
}}
  
