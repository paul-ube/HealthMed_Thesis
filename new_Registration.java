/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;



import cms.function.LimitDocumentFilter;
import static cms.function.Loginfunction.user_ID;

import cms.function.SQLConnection;
import static cms.function.UI_function.hello;
import cms.function.Uppercase;
import static cms.function.Uppercase.uppercase;
import static cms.function.tbl_function.tbl_function;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import static java.lang.Math.round;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import org.apache.commons.lang.WordUtils;
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
public class new_Registration extends javax.swing.JDialog {

    
    
    public static LocalDate nowna = LocalDate.now();
    
    
    public static SimpleDateFormat abc = new SimpleDateFormat("YYYY-MM-dd");
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = SQLConnection.ConnDB();
    
    
    VideoCapture  webSource = null;
    Mat frame = new Mat();
    Size sz = new Size(160, 160);
    MatOfByte mem = new MatOfByte();
    byte[] person_image = null;
    
    private DaemonThread thisThread = null;
    int count = 0;
    
    
    
    /**
     * Creates new form new_Registration
     */
    public new_Registration() {
        initComponents();
        
        
        
        JTextFieldDateEditor editor_last = (JTextFieldDateEditor) date_last_attack.getDateEditor();
        editor_last.setEditable(false);
        
        JTextFieldDateEditor editor_birth = (JTextFieldDateEditor) date_birth.getDateEditor();
        editor_birth.setEditable(false);
        
        JTextFieldDateEditor editor_mens = (JTextFieldDateEditor) date_mens.getDateEditor();
        editor_mens.setEditable(false);
        
        tbl_function(tbl_medproblem);
        tbl_function(tbl_allergy);
        tbl_function(tbl_famhis);
        tbl_function(tbl_submit);
        
        
        
        Date kasalukuyan = new Date();
        date_last_attack.setMaxSelectableDate(kasalukuyan);
        date_mens.setMaxSelectableDate(kasalukuyan);
        
        try {
            date_birth.getJCalendar().setMaxSelectableDate(new SimpleDateFormat("MM-DD-YYYY").parse("01-01-2002"));
            date_birth.setDate(new SimpleDateFormat("MM-DD-YYYY").parse("12-25-2000"));
        } catch (ParseException ex) {
            Logger.getLogger(new_Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ((AbstractDocument)txt_scontact.getDocument()).setDocumentFilter(new LimitDocumentFilter(11));
        ((AbstractDocument)txt_mcontact.getDocument()).setDocumentFilter(new LimitDocumentFilter(11));
        
        ((AbstractDocument)txt_gcontact.getDocument()).setDocumentFilter(new LimitDocumentFilter(11));
        
        ((AbstractDocument)txt_id.getDocument()).setDocumentFilter(new LimitDocumentFilter(11));
        ((AbstractDocument)txt_lname.getDocument()).setDocumentFilter(new LimitDocumentFilter(30));
        ((AbstractDocument)txt_fname.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_mname.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)txt_address.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_city.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)txt_brgy.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)txt_children.getDocument()).setDocumentFilter(new LimitDocumentFilter(2));
        ((AbstractDocument)txt_drug.getDocument()).setDocumentFilter(new LimitDocumentFilter(30));
        ((AbstractDocument)txt_father.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_foccupation.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_gaddress.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        
        ((AbstractDocument)txt_gname.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_grelation.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)txt_hospital.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_injuries.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        ((AbstractDocument)txt_maintenance.getDocument()).setDocumentFilter(new LimitDocumentFilter(30));
        ((AbstractDocument)txt_mcompany.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        
        ((AbstractDocument)txt_moccupation.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        ((AbstractDocument)txt_mother.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_numbot.getDocument()).setDocumentFilter(new LimitDocumentFilter(2));
        ((AbstractDocument)txt_operations.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        ((AbstractDocument)txt_other_lab.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        ((AbstractDocument)txt_other_vacc.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_past_illness.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_pbirth.getDocument()).setDocumentFilter(new LimitDocumentFilter(70));
        ((AbstractDocument)txt_present_ill.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_recommend.getDocument()).setDocumentFilter(new LimitDocumentFilter(30));
        
        ((AbstractDocument)txt_rfid.getDocument()).setDocumentFilter(new LimitDocumentFilter(10));
        
        ((AbstractDocument)txt_smoke.getDocument()).setDocumentFilter(new LimitDocumentFilter(2));
        ((AbstractDocument)txt_spouse.getDocument()).setDocumentFilter(new LimitDocumentFilter(40));
        ((AbstractDocument)txt_times.getDocument()).setDocumentFilter(new LimitDocumentFilter(5));
        
  
   
    }
    
    public void e_sinave(){
        try{

            Statement stmt = conn.createStatement();
           
            conn.setAutoCommit(false);
            String basicinfo = "";
            String pro = null;
            String yr = null;
            String typ = null;
            if (cmb_program.isEnabled()){
                pro = cmb_program.getSelectedItem().toString();
                yr = cmb_lvl.getSelectedItem().toString();
                typ = cmb_type.getSelectedItem().toString();
            }
            ////// BASIC INFO //////
            String id = txt_rfid.getText();
            if (!id.isEmpty()){
                basicinfo = "INSERT INTO patient_tbl (id_no, lastname, firstname, middlename, occupation,"
                        + " program, year_level, student_type, gender, bday,"
                        + " nationality, status, place_birth, unit_no, house_building, street, brgy, city, religion, contact, insertedOn, user_ID, RFID)"
                        + " VALUES("
                        + " '"+txt_id.getText()+"',"
                        + " '"+txt_lname.getText()+"',"
                        + " '"+txt_fname.getText()+"',"
                        + " '"+txt_mname.getText()+"',"
                        + " '"+cmb_occupation.getSelectedItem().toString()+"',"
                        + " '"+pro+"',"
                        + " '"+yr+"',"
                        + " '"+typ+"',"
                        + " '"+cmb_gender.getSelectedItem().toString()+"',"
                        + " '"+abc.format(date_birth.getDate())+"',"

                        + " '"+cmb_nationality.getSelectedItem().toString()+"',"
                        + " '"+cmb_status.getSelectedItem().toString()+"',"
                        + " '"+txt_pbirth.getText()+"',"
                        + " '"+txt_address.getText()+"',"
                        + " '"+txt_address1.getText()+"',"
                        + " '"+txt_address2.getText()+"',"
                        + " '"+txt_brgy.getText()+"',"
                        + " '"+txt_city.getText()+"',"
                        + " '"+cmb_religion.getSelectedItem().toString()+"',"
                        + " '"+txt_contact.getText()+"',"
                        + " '"+nowna+"',"
                        + " '"+user_ID+"',"
                        + " '"+id+"'"
                        + ");";
            }else{
                basicinfo = "INSERT INTO patient_tbl (id_no, lastname, firstname, middlename, occupation,"
                    + " program, year_level, student_type, gender, bday,"
                    + " nationality, status, place_birth, unit_no, house_building, street, brgy, city, religion, contact, insertedOn, user_ID)"
                    + " VALUES("
                    + " '"+txt_id.getText()+"',"
                    + " '"+txt_lname.getText()+"',"
                    + " '"+txt_fname.getText()+"',"
                    + " '"+txt_mname.getText()+"',"
                    + " '"+cmb_occupation.getSelectedItem().toString()+"',"
                    + " '"+pro+"',"
                    + " '"+yr+"',"
                    + " '"+typ+"',"
                    + " '"+cmb_gender.getSelectedItem().toString()+"',"
                    + " '"+abc.format(date_birth.getDate())+"',"
                    
                    + " '"+cmb_nationality.getSelectedItem().toString()+"',"
                    + " '"+cmb_status.getSelectedItem().toString()+"',"
                    + " '"+txt_pbirth.getText()+"',"
                    + " '"+txt_address.getText()+"',"
                    + " '"+txt_address1.getText()+"',"
                    + " '"+txt_address2.getText()+"',"
                    + " '"+txt_brgy.getText()+"',"
                    + " '"+txt_city.getText()+"',"
                    + " '"+cmb_religion.getSelectedItem().toString()+"',"
                    + " '"+txt_contact.getText()+"',"
                    + " '"+nowna+"',"
                    + " '"+user_ID+"'"
                    + ");";
            }
            
            stmt.executeUpdate(basicinfo);
            
            
            //Add Guardian

            String guardian = "INSERT INTO guardian_table (id_no, g_fname, g_relation, g_add, g_contact)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"',"
                    + " '"+txt_father.getText()+"',"
                    + " '"+txt_grelation.getText()+"',"
                    + " '"+txt_gaddress.getText()+"',"
                    + " '"+txt_gcontact.getText()+"'"
                    + ");";
            
            stmt.executeUpdate(guardian);
            
            //Add father
            String father = "INSERT INTO father_table (id_no, f_fname, f_occupation, f_company_address, f_contact)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"',"
                    + " '"+txt_father.getText()+"',"
                    + " '"+txt_foccupation.getText()+"',"
                    + " '"+txt_fcompany.getText()+"',"
                    + " '"+txt_fcontact.getText()+"'"
                    + ");";
            
            stmt.executeUpdate(father);
            
            //add mother
            String mother = "INSERT INTO mother_table (m_fname, m_occupation, m_company_address, m_contact,id_no)"
                    + " VALUES ("
                    
                    + " '"+txt_mother.getText()+"',"
                    + " '"+txt_moccupation.getText()+"',"
                    + " '"+txt_mcompany.getText()+"',"
                    + " '"+txt_mcontact.getText()+"',"
                    + " '"+txt_id.getText()+"'"
                    + ");";
            
            stmt.executeUpdate(mother);
            
            ////// ADD SPOUSE REGARDLESS KUNG MERON OR WALA ///////
            
            String spouse = "INSERT INTO spouse_table (id_no)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"'"
                    + ");";
            
            stmt.executeUpdate(spouse);
            
            ////// IF MAY SPOUSE NGA SIYA //////
            if (cmb_status.getSelectedIndex()==2){
                String if_spouse = "UPDATE spouse_table SET"
                        + " s_fname = '"+txt_spouse.getText()+"',"
                        + " s_age = '"+cmb_age.getSelectedItem().toString()+"',"
                        + " s_child = '"+txt_children.getText()+"',"
                        + " s_contact = '"+txt_scontact.getText()+"'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                        
                        

                stmt.executeUpdate(if_spouse);
            }
            
            //add medical history
            String medhis = "INSERT INTO med_history (id_no, past_illness, injuries, operations, hospital)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"',"
                    + " '"+txt_past_illness.getText()+"',"
                    + " '"+txt_injuries.getText()+"',"
                    + " '"+txt_operations.getText()+"',"
                    + " '"+txt_hospital.getText()+"'"
                    + ");";
            
            stmt.executeUpdate(medhis);
            
            //Check the checkbox if it is checked... tooooo much check
                int bcg, dpt, opv, measles, influenza;

                if (chk_bcg.isSelected()){
                    bcg = 1;
                }else{
                    bcg = 0;
                }

                if (chk_dpt.isSelected()){
                    dpt = 1;
                }else{
                    dpt = 0;
                }

                if (chk_opv.isSelected()){
                    opv = 1;
                }else{
                    opv = 0;
                }

                if (chk_measles.isSelected()){
                    measles = 1;
                }else{
                    measles = 0;
                }

                if (chk_influenza.isSelected()){
                    influenza = 1;
                }else{
                    influenza = 0;
                }
               
  
            
            //add vaccines
            String vaccine = "INSERT INTO vacc_history (id_no, BCG, DPT, OPV, measles)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"',"
                    + " '"+bcg+"',"
                    + " '"+dpt+"',"
                    + " '"+opv+"',"
                    + " '"+measles+"'"
                    + ");";
            
            stmt.executeUpdate(vaccine);
            
            ////////////// CHECK IF OTHER CHECKBOX IS SELECTED ////////////////////
            
            if (chk_other.isSelected()){
                String add_other = "UPDATE vacc_history SET"
                        + " other_vaccines = '"+txt_other_vacc.getText()+"'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                
                stmt.executeUpdate(add_other);
                
            }
            
            
            ///////////// CHECK IF INFLUENZA CHECKBOX IS SELECTED ////////////////////
            if (chk_influenza.isSelected()){
            //add flu
                String flu = "INSERT INTO vacc_flu (id_no, influenza, date_given)"
                        + " VALUES ("
                        + " '"+txt_id.getText()+"',"
                        + " '"+influenza+"',"
                        + " '"+nowna+"'"
                        + ");";

                stmt.executeUpdate(flu);
            }else{
                String flow = "INSERT INTO vacc_flu SET"
                    + " id_no = '"+txt_id.getText()+"'";
            
                stmt.executeUpdate(flow);
            }
            
            
            ///////////// CHECK IF HEPA CHECKBOX IS SELECTED /////////////////////
            
            if (chk_hepa.isSelected()){
            
                //add hepatitis B
                String hepab = "INSERT INTO vacc_hepa_b SET"
                    + " id_no = '"+txt_id.getText()+"',"
                        + " status= 'Ongoing' ;";
            
                stmt.executeUpdate(hepab);
            
                
                
                if (cmb_doses.getSelectedIndex()+1 == 3){
                    
                    String complete = "UPDATE vacc_hepa_b SET"
                            + " dose_no = '3',"
                            + " status = 'Completed'"
                            + " WHERE id_no = '"+txt_id.getText()+"';";
                    
                    stmt.executeUpdate(complete);
                     
                    
                }else{
                    int i = cmb_doses.getSelectedIndex()+1;
                    String dicomplete = "UPDATE vacc_hepa_b SET"
                            + " dose_no = '"+i+"'"
                            + " WHERE id_no = '"+txt_id.getText()+"';";
                    stmt.executeUpdate(dicomplete);
                }
            }else{
                String hepab = "INSERT INTO vacc_hepa_b SET"
                    + " id_no = '"+txt_id.getText()+"',"
                        + " status = 'Ongoing';";
            
                stmt.executeUpdate(hepab);
            }
            
            
            ///////////INSERT TO VACC HEPA AND FLU ////// REGARDLESS OF PROGRAM
            
           
            

            /////// PERSONAL HISTORY ////////////
            
            // GET ALL CHECKBOX THAT ARE SELECTED
            
                int drink, smoke, drugs, sex;

                if (chk_drink.isSelected()){
                    drink = 1;   
                }else{
                    drink = 0;
                }

                if (chk_smoke.isSelected()){
                    smoke = 1;   
                }else{
                    smoke = 0;
                }

                if (chk_drugs.isSelected()){
                    drugs = 1;   
                }else{
                    drugs = 0;
                }

                if (chk_sex.isSelected()){
                    sex = 1;   
                }else{
                    sex = 0;
                }
            
                
            ////// GET BASICS FROM PERSONAL HISTORY /////////////////    
            String personal = "INSERT INTO personal_history (id_no, drinking, smoking, drugs, sexual)"
                    + " VALUES ("
                    + " '"+txt_id.getText()+"',"
                    + " '"+drink+"',"
                    + " '"+smoke+"',"
                    + " '"+drugs+"',"
                    + " '"+sex+"'"
                    + ")";
                
            stmt.executeUpdate(personal);
            
            /////// PERSONAL HISTORY CONDITIONS  ////////////////
            
            ////// IF DRINKING //////
            
            
            if (chk_drink.isSelected()){
                
            // ADD DETAILS //
                String inom = "UPDATE personal_history"
                        + " SET bottles = '"+txt_numbot.getText()+" bottles "+cmb_bottle.getSelectedItem().toString()+"'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                
                stmt.executeUpdate(inom);
            }
            
            ////// IF SMOKING //////
            
            if (chk_smoke.isSelected()){
                String usok = "UPDATE personal_history"
                        + " SET sticks = '"+txt_smoke.getText()+" "+cmb_stick.getSelectedItem().toString()+" per day'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                
                stmt.executeUpdate(usok);
            }
            ////// IF DRUGS //////
            
            if (chk_drugs.isSelected()){
                String droga = "UPDATE personal_history"
                        + " SET type_times = '"+txt_drug.getText()+" ; "+txt_times.getText()+" times'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                
                stmt.executeUpdate(droga);
  
            }
            
            
            ////// SEE IF GENDER IS FEMALE FOR MENSTRUATION //////
            if (cmb_gender.getSelectedIndex()==1){
                
                int reg;
                
                if (chk_regular.isSelected()){
                    reg = 1;
                }else{
                    reg = 0;
                }
                
                String mens = "UPDATE personal_history SET"
                        + " mens_date = '"+abc.format(date_mens.getDate())+"',"
                        + " mens_regular = '"+reg+"',"
                        + " mens_duration = '"+cmb_duration.getSelectedIndex()+1+"',"
                        + " mens_pads = '"+cmb_pads.getSelectedIndex()+1+"',"
                        + " dysm = '"+cmb_dys.getSelectedItem().toString()+"'"
                        + " WHERE id_no = '"+txt_id.getText()+"';";
                stmt.executeUpdate(mens);
                
                if (cmb_status.getSelectedIndex()==1 || cmb_status.getSelectedIndex()==2 || cmb_status.getSelectedIndex()==3){
                    
                    String anak = "UPDATE personal_history SET"
                            + " no_pregnancy = '"+cmb_preg.getSelectedIndex()+"',"
                            + " no_alive_child = '"+cmb_alive.getSelectedIndex()+"',"
                            + " no_abort_child = '"+txt_abortion.getText()+"',"
                            + " no_born_nsd = '"+cmb_nsd.getSelectedIndex()+"',"
                            + " no_born_cs = '"+txt_cs.getText()+"',"
                            + " no_premature = '"+cmb_premature.getSelectedIndex()+"',"
                            + " term = '"+cmb_term.getSelectedItem().toString()+"'"
                            + " WHERE id_no = '"+txt_id.getText()+"';";
                    stmt.executeUpdate(anak);
                    
                }
            }
                
                
                ////// FAMILY HISTORY //////
                int row = tbl_famhis.getRowCount();
                for (int x = 0; x!=row; x++){
                    
                    String get_illname = (tbl_famhis.getModel().getValueAt(x, 0).toString());
                    String get_famember = (tbl_famhis.getModel().getValueAt(x, 1).toString());
                    
                    String getrow = "INSERT INTO fam_history SET"
                            + " fam_ill = '"+get_illname+"',"
                            + " id_no = '"+txt_id.getText()+"',"
                            + " fam_member = '"+get_famember+"';";
                    
                    stmt.executeUpdate(getrow);
                    
                }
                
                ////// PRESENT MEDICAL PROBLEM //////
                
                
                int row_of_active = tbl_medproblem.getRowCount();
                
                for (int x = 0; x!=row_of_active; x++){
                    
                    String illness = (tbl_medproblem.getModel().getValueAt(x, 0).toString());
                    String last_attack = (tbl_medproblem.getModel().getValueAt(x, 1).toString());
                    String medication = (tbl_medproblem.getModel().getValueAt(x, 2).toString());
                    
                    String insert_active = "INSERT INTO med_problem SET"
                            + " active_ill = '"+illness+"',"
                            + " lastattack = '"+last_attack+"',"
                            + " maintenance = '"+medication+"',"
                            + " id_no = '"+txt_id.getText()+"';";
                    
                    stmt.executeUpdate(insert_active);
                    
                }
                
                ////// ALLERGIES //////
                
                int row_of_allergy = tbl_allergy.getRowCount();
                
                for (int x = 0; x!=row_of_allergy; x++){
                    
                    String type = (tbl_allergy.getModel().getValueAt(x, 0).toString());
                    String cause = (tbl_allergy.getModel().getValueAt(x, 1).toString());
                    
                    
                    String insert_active = "INSERT INTO allergy_tbl SET"
                            + " allergy_type = '"+type+"',"
                            + " id_no = '"+txt_id.getText()+"',"
                            + " allergy_cause = '"+cause+"';";
                    
                    stmt.executeUpdate(insert_active);
                    
                }
                
                
                ////// ASSESSMENT //////
                
                
                String assessment = "INSERT INTO assessment_tbl SET"
                        + " id_no = '"+txt_id.getText()+"';";
                
                stmt.executeUpdate(assessment);
                
                String update_assessment = "UPDATE assessment_tbl SET"
                        + " assessment = '"+cmb_assess.getSelectedItem().toString()+"',"
                        + " recommendation = '"+txt_recommend.getText()+"',"
                        + " assdate = '"+nowna+"',"
                        + " systolic = '"+txt_systolic.getText()+"',"
                        + " diastolic = '"+txt_diastolic.getText()+"',"
                        + " bp_result = '"+lbl_bpresult.getText()+"',"
                        + " rr = '"+txt_rr.getText()+"',"
                        + " rr_result = '"+lbl_rrresult.getText()+"',"
                        + " hr = '"+txt_hr.getText()+"',"
                        + " hr_result = '"+lbl_hrresult.getText()+"',"
                        + " height = '"+cmb_height.getSelectedItem().toString()+"',"
                        + " height_in = '"+cmb_heightin.getSelectedItem().toString()+"',"
                        //+ " height = '"+txt_height.getText()+"',"
                        + " weight = '"+txt_weight.getText()+"',"
                        + " bmi_point = '"+lbl_point.getText()+"',"
                        + " bmi = '"+lbl_bmi.getText()+"',"
                        + " isCreated = '"+user_ID+"'"
                        + " WHERE id_no = '"+txt_id.getText()+"'";
                
                stmt.executeUpdate(update_assessment);
                
                if (cmb_assess.getSelectedIndex()==2){
         
                
                    int row_of_submit = tbl_submit.getRowCount();
                    for (int x = 0; x!=row_of_submit; x++){

                        String submit = (tbl_submit.getModel().getValueAt(x, 0).toString());

                        String insert_active = "INSERT INTO submit_tbl SET"
                                + " documents = '"+submit+"',"
                                + " id_no = '"+txt_id.getText()+"';";


                        stmt.executeUpdate(insert_active);
                    }
                }

            String a = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS_Image\\" +txt_id.getText()+ ".png";
            String b = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS_Image\\" +txt_id.getText()+ ".png";

            try{
                File image  = new File (a);
                FileInputStream fis =  new FileInputStream(image);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                byte[] buf = new byte[1024];
                for (int readNum; (readNum=fis.read(buf))!=-1;){
                        bos.write(buf,0,readNum);
                }
                
                person_image = bos.toByteArray();
               // photoattach.setIcon(person_image);
                System.out.println(person_image.toString());
               //photoattach.setIcon(person_image.toString());
                String sql = "INSERT INTO photo(id_no, image) VALUES (?,?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, txt_id.getText());
                    pstmt.setBytes(2, person_image);
                    pstmt.execute();
                
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
                

//            int[] count = stmt.executeBatch();
            
            conn.commit();
            
            JOptionPane.showMessageDialog(null, "Successfully Added");
            
            this.dispose();
            new Home().setVisible(true);
            
    
            
            
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Navigation = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        lbl_basic = new javax.swing.JLabel();
        lbl_medical = new javax.swing.JLabel();
        lbl_assess = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        lbl_other = new javax.swing.JLabel();
        tgl_basic = new javax.swing.JToggleButton();
        tgl_med = new javax.swing.JToggleButton();
        tgl_contact = new javax.swing.JToggleButton();
        tgl_personal = new javax.swing.JToggleButton();
        tgl_fam = new javax.swing.JToggleButton();
        tgl_problem = new javax.swing.JToggleButton();
        tgl_assess = new javax.swing.JToggleButton();
        tgl_rfid = new javax.swing.JToggleButton();
        tgl_bmi = new javax.swing.JToggleButton();
        Layout_Panel = new javax.swing.JPanel();
        BasicInfo = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txt_mname = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        txt_age = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        txt_pbirth = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        txt_city = new javax.swing.JTextField();
        txt_brgy = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        cmb_program = new javax.swing.JComboBox();
        cmb_lvl = new javax.swing.JComboBox();
        cmb_gender = new javax.swing.JComboBox();
        cmb_type = new javax.swing.JComboBox();
        date_birth = new com.toedter.calendar.JDateChooser();
        cmb_status = new javax.swing.JComboBox();
        cmb_nationality = new javax.swing.JComboBox();
        cmb_occupation = new javax.swing.JComboBox();
        cmb_religion = new javax.swing.JComboBox();
        jLabel103 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_address1 = new javax.swing.JTextField();
        txt_address2 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        ContactInfo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txt_gname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txt_grelation = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_gaddress = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_gcontact = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txt_father = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_foccupation = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_fcompany = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_fcontact = new javax.swing.JTextField();
        txt_mcontact = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txt_mcompany = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_moccupation = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_mother = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txt_scontact = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txt_children = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txt_spouse = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cmb_age = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        MedHistory = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        txt_past_illness = new javax.swing.JTextField();
        txt_injuries = new javax.swing.JTextField();
        txt_operations = new javax.swing.JTextField();
        txt_hospital = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        chk_bcg = new javax.swing.JCheckBox();
        chk_dpt = new javax.swing.JCheckBox();
        chk_opv = new javax.swing.JCheckBox();
        chk_measles = new javax.swing.JCheckBox();
        chk_influenza = new javax.swing.JCheckBox();
        txt_other_vacc = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        chk_past = new javax.swing.JCheckBox();
        chk_injuries = new javax.swing.JCheckBox();
        chk_operations = new javax.swing.JCheckBox();
        chk_hospital = new javax.swing.JCheckBox();
        chk_hepa = new javax.swing.JCheckBox();
        cmb_doses = new javax.swing.JComboBox();
        chk_other = new javax.swing.JCheckBox();
        PerHistory = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        chk_regular = new javax.swing.JCheckBox();
        chk_drink = new javax.swing.JCheckBox();
        jLabel68 = new javax.swing.JLabel();
        cmb_premature = new javax.swing.JComboBox();
        jLabel72 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        cmb_pads = new javax.swing.JComboBox();
        chk_drugs = new javax.swing.JCheckBox();
        jLabel63 = new javax.swing.JLabel();
        cmb_preg = new javax.swing.JComboBox();
        txt_times = new javax.swing.JTextField();
        cmb_bottle = new javax.swing.JComboBox();
        jLabel65 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        cmb_nsd = new javax.swing.JComboBox();
        jLabel62 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cmb_dys = new javax.swing.JComboBox();
        chk_sex = new javax.swing.JCheckBox();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        txt_smoke = new javax.swing.JTextField();
        txt_drug = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        cmb_alive = new javax.swing.JComboBox();
        cmb_duration = new javax.swing.JComboBox();
        cmb_stick = new javax.swing.JComboBox();
        jLabel66 = new javax.swing.JLabel();
        cmb_term = new javax.swing.JComboBox();
        txt_numbot = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        chk_smoke = new javax.swing.JCheckBox();
        jLabel75 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        date_mens = new com.toedter.calendar.JDateChooser();
        txt_abortion = new javax.swing.JTextField();
        txt_cs = new javax.swing.JTextField();
        ShowFamHistory = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_famhis = new javax.swing.JTable();
        jLabel102 = new javax.swing.JLabel();
        cmb_fam = new javax.swing.JComboBox();
        chk_father = new javax.swing.JCheckBox();
        chk_sibling = new javax.swing.JCheckBox();
        chk_aunt = new javax.swing.JCheckBox();
        chk_grandfather = new javax.swing.JCheckBox();
        chk_uncle = new javax.swing.JCheckBox();
        chk_grandmother = new javax.swing.JCheckBox();
        chk_children = new javax.swing.JCheckBox();
        chk_mother = new javax.swing.JCheckBox();
        chk_nieces = new javax.swing.JCheckBox();
        chk_nephew = new javax.swing.JCheckBox();
        jButton9 = new javax.swing.JButton();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        Add_MedProb = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txt_present_ill = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txt_maintenance = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        date_last_attack = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_medproblem = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbl_allergy = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        txt_allergy_cause = new javax.swing.JTextField();
        cmb_allergy = new javax.swing.JComboBox();
        jLabel48 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        Assessment = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        cmb_assess = new javax.swing.JComboBox();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        txt_other_lab = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        txt_recommend = new javax.swing.JTextField();
        cmb_submit = new javax.swing.JComboBox();
        btn_add_lab = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tbl_submit = new javax.swing.JTable();
        lbl_bpresult1 = new javax.swing.JLabel();
        lbl_rrresult1 = new javax.swing.JLabel();
        lbl_hrresult1 = new javax.swing.JLabel();
        lbl_bmi1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        Photo = new javax.swing.JPanel();
        pokemon = new javax.swing.JLabel();
        btn_open_web = new javax.swing.JButton();
        btn_capture = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        txt_rfid = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        BMI = new javax.swing.JPanel();
        txt_systolic = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        txt_hr = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        txt_rr = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        txt_weight = new javax.swing.JTextField();
        jLabel111 = new javax.swing.JLabel();
        txt_diastolic = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lbl_bpresult = new javax.swing.JLabel();
        lbl_rrresult = new javax.swing.JLabel();
        lbl_hrresult = new javax.swing.JLabel();
        cmb_assess1 = new javax.swing.JComboBox();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        lbl_bmi = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        cmb_height = new javax.swing.JComboBox();
        cmb_heightin = new javax.swing.JComboBox();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl_bmi2 = new javax.swing.JLabel();
        lbl_bmi3 = new javax.swing.JLabel();
        lbl_bmi4 = new javax.swing.JLabel();
        lbl_bmi5 = new javax.swing.JLabel();
        lbl_point = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Control_Panel = new javax.swing.JPanel();
        jSeparator9 = new javax.swing.JSeparator();
        btn_save = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1404, 759));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Navigation.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator1.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator3.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator7.setBackground(new java.awt.Color(10, 61, 115));

        lbl_basic.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_basic.setForeground(new java.awt.Color(255, 255, 0));
        lbl_basic.setText("BASIC");

        lbl_medical.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_medical.setForeground(new java.awt.Color(255, 255, 255));
        lbl_medical.setText("MEDICAL");

        lbl_assess.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_assess.setForeground(new java.awt.Color(255, 255, 255));
        lbl_assess.setText("ASSESSMENT");

        jSeparator8.setBackground(new java.awt.Color(10, 61, 115));

        lbl_other.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_other.setForeground(new java.awt.Color(255, 255, 255));
        lbl_other.setText("OTHER");

        buttonGroup1.add(tgl_basic);
        tgl_basic.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_basic.setForeground(new java.awt.Color(255, 255, 0));
        tgl_basic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/info_1.png"))); // NOI18N
        tgl_basic.setSelected(true);
        tgl_basic.setText("   Basic Information");
        tgl_basic.setBorderPainted(false);
        tgl_basic.setContentAreaFilled(false);
        tgl_basic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_basic.setFocusPainted(false);
        tgl_basic.setFocusable(false);
        tgl_basic.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_basic.setIconTextGap(6);
        tgl_basic.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/info_2.png"))); // NOI18N
        tgl_basic.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/info_2.png"))); // NOI18N
        tgl_basic.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/info_2.png"))); // NOI18N
        tgl_basic.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_basicItemStateChanged(evt);
            }
        });
        tgl_basic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_basicMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_basicMouseExited(evt);
            }
        });
        tgl_basic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_basicActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_med);
        tgl_med.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_med.setForeground(new java.awt.Color(193, 195, 198));
        tgl_med.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/list (1).png"))); // NOI18N
        tgl_med.setText("   Medical History");
        tgl_med.setBorderPainted(false);
        tgl_med.setContentAreaFilled(false);
        tgl_med.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_med.setFocusPainted(false);
        tgl_med.setFocusable(false);
        tgl_med.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_med.setIconTextGap(6);
        tgl_med.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/list.png"))); // NOI18N
        tgl_med.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/list.png"))); // NOI18N
        tgl_med.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/list.png"))); // NOI18N
        tgl_med.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_medItemStateChanged(evt);
            }
        });
        tgl_med.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_medMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_medMouseExited(evt);
            }
        });
        tgl_med.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_medActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_contact);
        tgl_contact.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_contact.setForeground(new java.awt.Color(193, 195, 198));
        tgl_contact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/contact_1.png"))); // NOI18N
        tgl_contact.setText("   Contact Information");
        tgl_contact.setBorderPainted(false);
        tgl_contact.setContentAreaFilled(false);
        tgl_contact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_contact.setFocusPainted(false);
        tgl_contact.setFocusable(false);
        tgl_contact.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_contact.setIconTextGap(6);
        tgl_contact.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/contact_2.png"))); // NOI18N
        tgl_contact.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/contact_2.png"))); // NOI18N
        tgl_contact.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/contact_2.png"))); // NOI18N
        tgl_contact.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_contactItemStateChanged(evt);
            }
        });
        tgl_contact.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_contactMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_contactMouseExited(evt);
            }
        });
        tgl_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_contactActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_personal);
        tgl_personal.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_personal.setForeground(new java.awt.Color(193, 195, 198));
        tgl_personal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/person_1.png"))); // NOI18N
        tgl_personal.setText("   Personal History");
        tgl_personal.setBorderPainted(false);
        tgl_personal.setContentAreaFilled(false);
        tgl_personal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_personal.setFocusPainted(false);
        tgl_personal.setFocusable(false);
        tgl_personal.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_personal.setIconTextGap(6);
        tgl_personal.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/person_2.png"))); // NOI18N
        tgl_personal.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/person_2.png"))); // NOI18N
        tgl_personal.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/person_2.png"))); // NOI18N
        tgl_personal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_personalItemStateChanged(evt);
            }
        });
        tgl_personal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_personalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_personalMouseExited(evt);
            }
        });
        tgl_personal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_personalActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_fam);
        tgl_fam.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_fam.setForeground(new java.awt.Color(193, 195, 198));
        tgl_fam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/family_1.png"))); // NOI18N
        tgl_fam.setText("   Family History");
        tgl_fam.setBorderPainted(false);
        tgl_fam.setContentAreaFilled(false);
        tgl_fam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_fam.setFocusPainted(false);
        tgl_fam.setFocusable(false);
        tgl_fam.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_fam.setIconTextGap(6);
        tgl_fam.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/family_2.png"))); // NOI18N
        tgl_fam.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/family_2.png"))); // NOI18N
        tgl_fam.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/family_2.png"))); // NOI18N
        tgl_fam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_famItemStateChanged(evt);
            }
        });
        tgl_fam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_famMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_famMouseExited(evt);
            }
        });
        tgl_fam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_famActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_problem);
        tgl_problem.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_problem.setForeground(new java.awt.Color(193, 195, 198));
        tgl_problem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/problem_1.png"))); // NOI18N
        tgl_problem.setText("   Medical Problems");
        tgl_problem.setBorderPainted(false);
        tgl_problem.setContentAreaFilled(false);
        tgl_problem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_problem.setFocusPainted(false);
        tgl_problem.setFocusable(false);
        tgl_problem.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_problem.setIconTextGap(6);
        tgl_problem.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/problem_2.png"))); // NOI18N
        tgl_problem.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/problem_2.png"))); // NOI18N
        tgl_problem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/problem_2.png"))); // NOI18N
        tgl_problem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_problemItemStateChanged(evt);
            }
        });
        tgl_problem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_problemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_problemMouseExited(evt);
            }
        });
        tgl_problem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_problemActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_assess);
        tgl_assess.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_assess.setForeground(new java.awt.Color(193, 195, 198));
        tgl_assess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/clipboard-variant-with-pencil-and-check-mark-variant.png"))); // NOI18N
        tgl_assess.setText("   Assessment");
        tgl_assess.setBorderPainted(false);
        tgl_assess.setContentAreaFilled(false);
        tgl_assess.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_assess.setFocusPainted(false);
        tgl_assess.setFocusable(false);
        tgl_assess.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_assess.setIconTextGap(6);
        tgl_assess.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/clipboard-variant-with-pencil-and-check-mark-variant (1).png"))); // NOI18N
        tgl_assess.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/clipboard-variant-with-pencil-and-check-mark-variant (1).png"))); // NOI18N
        tgl_assess.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/clipboard-variant-with-pencil-and-check-mark-variant (1).png"))); // NOI18N
        tgl_assess.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_assessItemStateChanged(evt);
            }
        });
        tgl_assess.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_assessMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_assessMouseExited(evt);
            }
        });
        tgl_assess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_assessActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_rfid);
        tgl_rfid.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_rfid.setForeground(new java.awt.Color(193, 195, 198));
        tgl_rfid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/camera_1.png"))); // NOI18N
        tgl_rfid.setText("   Camera & RFID");
        tgl_rfid.setBorderPainted(false);
        tgl_rfid.setContentAreaFilled(false);
        tgl_rfid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_rfid.setFocusPainted(false);
        tgl_rfid.setFocusable(false);
        tgl_rfid.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_rfid.setIconTextGap(6);
        tgl_rfid.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/camera_2.png"))); // NOI18N
        tgl_rfid.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/camera_2.png"))); // NOI18N
        tgl_rfid.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/camera_2.png"))); // NOI18N
        tgl_rfid.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_rfidItemStateChanged(evt);
            }
        });
        tgl_rfid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_rfidMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_rfidMouseExited(evt);
            }
        });
        tgl_rfid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_rfidActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_bmi);
        tgl_bmi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_bmi.setForeground(new java.awt.Color(193, 195, 198));
        tgl_bmi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/bmi_1.png"))); // NOI18N
        tgl_bmi.setText("   BMI");
        tgl_bmi.setBorderPainted(false);
        tgl_bmi.setContentAreaFilled(false);
        tgl_bmi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_bmi.setFocusPainted(false);
        tgl_bmi.setFocusable(false);
        tgl_bmi.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_bmi.setIconTextGap(6);
        tgl_bmi.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/bmi_2.png"))); // NOI18N
        tgl_bmi.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/bmi_2.png"))); // NOI18N
        tgl_bmi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/registration/bmi_2.png"))); // NOI18N
        tgl_bmi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_bmiItemStateChanged(evt);
            }
        });
        tgl_bmi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_bmiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_bmiMouseExited(evt);
            }
        });
        tgl_bmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_bmiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_other)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgl_bmi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_assess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tgl_basic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tgl_rfid, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_contact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_personal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_fam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_problem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8)
                            .addComponent(lbl_basic)
                            .addComponent(lbl_medical)
                            .addComponent(lbl_assess)
                            .addComponent(jSeparator7)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator3)
                            .addGroup(NavigationLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(tgl_med, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbl_basic)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_basic, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_medical)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_med, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_personal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_problem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_assess)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_assess, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_other)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_rfid, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Layout_Panel.setPreferredSize(new java.awt.Dimension(1338, 715));
        Layout_Panel.setLayout(new java.awt.CardLayout());

        BasicInfo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                BasicInfoComponentShown(evt);
            }
        });

        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane5.setPreferredSize(new java.awt.Dimension(100, 768));

        jLabel80.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(10, 61, 115));
        jLabel80.setText("Basic Information");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(120, 120, 120));
        jLabel81.setText("ID Number");

        txt_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(46, 47, 51));
        txt_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idFocusLost(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idKeyTyped(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(120, 120, 120));
        jLabel82.setText("Last Name");

        txt_lname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_lname.setForeground(new java.awt.Color(46, 47, 51));
        txt_lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lnameActionPerformed(evt);
            }
        });
        txt_lname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_lnameKeyTyped(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(120, 120, 120));
        jLabel83.setText("First Name");

        txt_fname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_fname.setForeground(new java.awt.Color(46, 47, 51));
        txt_fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fnameKeyTyped(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(120, 120, 120));
        jLabel84.setText("Middle Name");

        txt_mname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mname.setForeground(new java.awt.Color(46, 47, 51));
        txt_mname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mnameKeyTyped(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(120, 120, 120));
        jLabel85.setText("Program");

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(120, 120, 120));
        jLabel86.setText("Year Level");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(120, 120, 120));
        jLabel87.setText("Student Type");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(120, 120, 120));
        jLabel88.setText("Gender");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(120, 120, 120));
        jLabel89.setText("Date of Birth");

        txt_age.setEditable(false);
        txt_age.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_age.setForeground(new java.awt.Color(46, 47, 51));

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(120, 120, 120));
        jLabel91.setText("Occupation");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(120, 120, 120));
        jLabel92.setText("Nationality");

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(120, 120, 120));
        jLabel93.setText("Status");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(120, 120, 120));
        jLabel94.setText("Place of Birth");

        txt_pbirth.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pbirth.setForeground(new java.awt.Color(46, 47, 51));
        txt_pbirth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pbirthKeyTyped(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(120, 120, 120));
        jLabel95.setText("Unit Number");

        txt_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_address.setForeground(new java.awt.Color(46, 47, 51));
        txt_address.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_addressKeyTyped(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(120, 120, 120));
        jLabel96.setText("City");

        txt_city.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_city.setForeground(new java.awt.Color(46, 47, 51));
        txt_city.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cityKeyTyped(evt);
            }
        });

        txt_brgy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_brgy.setForeground(new java.awt.Color(46, 47, 51));
        txt_brgy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_brgyKeyTyped(evt);
            }
        });

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(120, 120, 120));
        jLabel97.setText("Baranggay");

        jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(120, 120, 120));
        jLabel98.setText("Contact Number/s");

        txt_contact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_contact.setForeground(new java.awt.Color(46, 47, 51));
        txt_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_contactKeyTyped(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(120, 120, 120));
        jLabel99.setText("Religion");

        cmb_program.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_program.setForeground(new java.awt.Color(46, 47, 51));
        cmb_program.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ABCOMM", "BSIT", "BSITDA", "BSCS", "BSCOE", "BSTM", "BSBM", "BSHRM", "BSAT", "Senior" }));
        cmb_program.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_program.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_programItemStateChanged(evt);
            }
        });
        cmb_program.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmb_programFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmb_programFocusLost(evt);
            }
        });
        cmb_program.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmb_programMouseClicked(evt);
            }
        });
        cmb_program.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_programActionPerformed(evt);
            }
        });

        cmb_lvl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_lvl.setForeground(new java.awt.Color(46, 47, 51));
        cmb_lvl.setMaximumRowCount(4);
        cmb_lvl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        cmb_lvl.setPreferredSize(new java.awt.Dimension(56, 30));

        cmb_gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_gender.setForeground(new java.awt.Color(46, 47, 51));
        cmb_gender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        cmb_gender.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_gender.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_genderItemStateChanged(evt);
            }
        });

        cmb_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_type.setForeground(new java.awt.Color(46, 47, 51));
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Irregular" }));
        cmb_type.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_typeActionPerformed(evt);
            }
        });

        date_birth.setForeground(new java.awt.Color(46, 47, 51));
        date_birth.setDateFormatString("MM-dd-YYYY");
        date_birth.setFont(new java.awt.Font("Segoe UI", 0, 14));
        date_birth.setMaxSelectableDate(new java.util.Date(253370739661000L));
        date_birth.setPreferredSize(new java.awt.Dimension(87, 30));
        date_birth.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Calendar dateofBirth = Calendar.getInstance();
                dateofBirth.setTime((Date)evt.getNewValue());

                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - dateofBirth.get(Calendar.YEAR);

                if(today.get(Calendar.MONTH) < dateofBirth.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == dateofBirth.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dateofBirth.get(Calendar.DAY_OF_MONTH))) {
                    age--;
                }

                txt_age.setText(String.valueOf(age));
            }
        });

        cmb_status.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_status.setForeground(new java.awt.Color(46, 47, 51));
        cmb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single", "Widow/er", "Married", "Single Parent" }));
        cmb_status.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_statusItemStateChanged(evt);
            }
        });

        cmb_nationality.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_nationality.setForeground(new java.awt.Color(46, 47, 51));
        cmb_nationality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "American", "Filipino", "Japanese", "Korean", "Russian", "Indian", "Chinese" }));
        cmb_nationality.setSelectedIndex(1);
        cmb_nationality.setPreferredSize(new java.awt.Dimension(56, 30));

        cmb_occupation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_occupation.setForeground(new java.awt.Color(46, 47, 51));
        cmb_occupation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student - College", "Student - Senior High", "Employee" }));
        cmb_occupation.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_occupation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_occupationItemStateChanged(evt);
            }
        });

        cmb_religion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_religion.setForeground(new java.awt.Color(46, 47, 51));
        cmb_religion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Christian", "Islam", "Secular / Atheist", "Sikhism", "Judaism", "Shinto" }));
        cmb_religion.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(120, 120, 120));
        jLabel103.setText("years old");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("*");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("*");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("*");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("*");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("*");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        txt_address1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_address1.setForeground(new java.awt.Color(46, 47, 51));
        txt_address1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_address1KeyTyped(evt);
            }
        });

        txt_address2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_address2.setForeground(new java.awt.Color(46, 47, 51));
        txt_address2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_address2KeyTyped(evt);
            }
        });

        jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(120, 120, 120));
        jLabel123.setText("House/Building/Street Number");

        jLabel124.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(120, 120, 120));
        jLabel124.setText("Street Name");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel80)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel97, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel96, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_pbirth)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(cmb_program, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel85, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                                    .addComponent(jLabel91, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                                                .addGap(78, 78, 78)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel86)
                                                    .addComponent(cmb_lvl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_mname, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_occupation, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_nationality, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmb_gender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(date_birth, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel103))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txt_brgy, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cmb_religion, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_address1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                                            .addComponent(txt_address2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(96, 96, 96))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel80)
                .addGap(40, 40, 40)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jLabel83)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_mname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jLabel91)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_occupation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jLabel86)
                    .addComponent(jLabel87))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_program, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel89)
                        .addGap(6, 6, 6)
                        .addComponent(date_birth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_age, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel103)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_nationality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pbirth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95)
                    .addComponent(jLabel123)
                    .addComponent(jLabel124))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_address1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_address2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_brgy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(jLabel99)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_religion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel5);

        javax.swing.GroupLayout BasicInfoLayout = new javax.swing.GroupLayout(BasicInfo);
        BasicInfo.setLayout(BasicInfoLayout);
        BasicInfoLayout.setHorizontalGroup(
            BasicInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
        );
        BasicInfoLayout.setVerticalGroup(
            BasicInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        Layout_Panel.add(BasicInfo, "card2");

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setPreferredSize(new java.awt.Dimension(100, 768));

        jLabel21.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(10, 61, 115));
        jLabel21.setText("Contact Information");

        jLabel22.setFont(new java.awt.Font("Arcon", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(10, 61, 115));
        jLabel22.setText("In Case of Emergency");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(120, 120, 120));
        jLabel23.setText("Name of responsible individual");

        txt_gname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_gname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_gnameKeyTyped(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(120, 120, 120));
        jLabel24.setText("Relation");

        txt_grelation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_grelation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_grelationKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(120, 120, 120));
        jLabel25.setText("Address");

        txt_gaddress.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_gaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_gaddressKeyTyped(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(120, 120, 120));
        jLabel26.setText("Contact Number/s");

        txt_gcontact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_gcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_gcontactKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arcon", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(10, 61, 115));
        jLabel27.setText("Father's Information");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(120, 120, 120));
        jLabel28.setText("Father's Name");

        txt_father.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_father.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fatherKeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(120, 120, 120));
        jLabel29.setText("Occupation");

        txt_foccupation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_foccupation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_foccupationKeyTyped(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(120, 120, 120));
        jLabel30.setText("Company Address");

        txt_fcompany.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_fcompany.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fcompanyKeyTyped(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(120, 120, 120));
        jLabel32.setText("Contact Number/s");

        txt_fcontact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_fcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fcontactKeyTyped(evt);
            }
        });

        txt_mcontact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mcontactKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(120, 120, 120));
        jLabel33.setText("Contact Number/s");

        txt_mcompany.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mcompany.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mcompanyKeyTyped(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(120, 120, 120));
        jLabel35.setText("Company Address");

        txt_moccupation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_moccupation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_moccupationKeyTyped(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(120, 120, 120));
        jLabel36.setText("Occupation");

        txt_mother.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mother.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_motherKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(120, 120, 120));
        jLabel37.setText("Mother's Name");

        jLabel38.setFont(new java.awt.Font("Arcon", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(10, 61, 115));
        jLabel38.setText("Mother's Information");

        txt_scontact.setEditable(false);
        txt_scontact.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_scontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_scontactKeyTyped(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(120, 120, 120));
        jLabel40.setText("Contact Number");

        txt_children.setEditable(false);
        txt_children.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_children.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_childrenKeyTyped(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(120, 120, 120));
        jLabel41.setText("Number of Children");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(120, 120, 120));
        jLabel42.setText("Age");

        txt_spouse.setEditable(false);
        txt_spouse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_spouse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_spouseKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(120, 120, 120));
        jLabel43.setText("Spouse's Name");

        jLabel44.setFont(new java.awt.Font("Arcon", 0, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(10, 61, 115));
        jLabel44.setText("If Married");

        cmb_age.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_age.setForeground(new java.awt.Color(46, 47, 51));
        cmb_age.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        cmb_age.setEnabled(false);
        cmb_age.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("*");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));
        jLabel13.setText("*");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("*");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));
        jLabel17.setText("*");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(702, 702, 702))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38)
                                    .addComponent(txt_mcompany, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txt_fcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                                            .addComponent(jLabel44)
                                            .addComponent(txt_spouse)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(cmb_age, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(50, 50, 50)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txt_children, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txt_scontact, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txt_moccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mother, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel27)
                                                .addComponent(txt_father)
                                                .addComponent(txt_foccupation)
                                                .addComponent(txt_fcompany))
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel22)
                                                .addComponent(txt_gname)
                                                .addComponent(txt_grelation)
                                                .addComponent(txt_gaddress)
                                                .addComponent(txt_gcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 675, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel21)
                .addGap(30, 30, 30)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_grelation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(40, 40, 40)
                .addComponent(jLabel27)
                .addGap(18, 18, 18)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_father, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_foccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_fcompany, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_fcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mother, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_moccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mcompany, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel44)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_spouse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_scontact, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_children, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel4);

        javax.swing.GroupLayout ContactInfoLayout = new javax.swing.GroupLayout(ContactInfo);
        ContactInfo.setLayout(ContactInfoLayout);
        ContactInfoLayout.setHorizontalGroup(
            ContactInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
        );
        ContactInfoLayout.setVerticalGroup(
            ContactInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        Layout_Panel.add(ContactInfo, "card3");

        jLabel34.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(10, 61, 115));
        jLabel34.setText("Past Medical Problem");

        txt_past_illness.setEditable(false);
        txt_past_illness.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_past_illness.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_past_illnessKeyTyped(evt);
            }
        });

        txt_injuries.setEditable(false);
        txt_injuries.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_injuries.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_injuriesKeyTyped(evt);
            }
        });

        txt_operations.setEditable(false);
        txt_operations.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_operations.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_operationsKeyTyped(evt);
            }
        });

        txt_hospital.setEditable(false);
        txt_hospital.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hospital.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hospitalKeyTyped(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(10, 61, 115));
        jLabel39.setText("Vaccinations");

        chk_bcg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_bcg.setForeground(new java.awt.Color(46, 47, 51));
        chk_bcg.setText("BCG [Bacille Calmette Guerin]");
        chk_bcg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_bcgActionPerformed(evt);
            }
        });

        chk_dpt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_dpt.setForeground(new java.awt.Color(46, 47, 51));
        chk_dpt.setText("DPT [Diphtheria, Pertussis, and Tetanus]");
        chk_dpt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_dptActionPerformed(evt);
            }
        });

        chk_opv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_opv.setForeground(new java.awt.Color(46, 47, 51));
        chk_opv.setText("Polio / OPV [Oral Polio Vaccine]");
        chk_opv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_opvActionPerformed(evt);
            }
        });

        chk_measles.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_measles.setForeground(new java.awt.Color(46, 47, 51));
        chk_measles.setText("Measles");

        chk_influenza.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_influenza.setForeground(new java.awt.Color(46, 47, 51));
        chk_influenza.setText("Influenza");
        chk_influenza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_influenzaActionPerformed(evt);
            }
        });

        txt_other_vacc.setEditable(false);
        txt_other_vacc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_other_vacc.setForeground(new java.awt.Color(46, 47, 51));
        txt_other_vacc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_other_vaccKeyTyped(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(145, 148, 150));
        jLabel53.setText("Specify");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(145, 148, 150));
        jLabel54.setText("Number of Doses");

        chk_past.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_past.setForeground(new java.awt.Color(46, 47, 51));
        chk_past.setText("Past Illnesses");
        chk_past.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_pastItemStateChanged(evt);
            }
        });

        chk_injuries.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_injuries.setForeground(new java.awt.Color(46, 47, 51));
        chk_injuries.setText("Accidents and Injuries");
        chk_injuries.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_injuriesItemStateChanged(evt);
            }
        });

        chk_operations.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_operations.setForeground(new java.awt.Color(46, 47, 51));
        chk_operations.setText("Operations");
        chk_operations.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_operationsItemStateChanged(evt);
            }
        });

        chk_hospital.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_hospital.setForeground(new java.awt.Color(46, 47, 51));
        chk_hospital.setText("Hospitalizations");
        chk_hospital.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_hospitalItemStateChanged(evt);
            }
        });

        chk_hepa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_hepa.setForeground(new java.awt.Color(46, 47, 51));
        chk_hepa.setText("Hepa B");
        chk_hepa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_hepaItemStateChanged(evt);
            }
        });

        cmb_doses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_doses.setForeground(new java.awt.Color(46, 47, 51));
        cmb_doses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        cmb_doses.setEnabled(false);
        cmb_doses.setPreferredSize(new java.awt.Dimension(56, 30));

        chk_other.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_other.setForeground(new java.awt.Color(46, 47, 51));
        chk_other.setText("Others");
        chk_other.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_otherItemStateChanged(evt);
            }
        });
        chk_other.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_otherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MedHistoryLayout = new javax.swing.GroupLayout(MedHistory);
        MedHistory.setLayout(MedHistoryLayout);
        MedHistoryLayout.setHorizontalGroup(
            MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedHistoryLayout.createSequentialGroup()
                .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MedHistoryLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jLabel34)
                            .addGroup(MedHistoryLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk_past)
                                    .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_past_illness, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                                        .addComponent(txt_injuries)
                                        .addComponent(txt_operations)
                                        .addComponent(txt_hospital))
                                    .addComponent(chk_injuries)
                                    .addComponent(chk_operations)
                                    .addComponent(chk_hospital)))))
                    .addGroup(MedHistoryLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chk_bcg)
                            .addComponent(chk_dpt)
                            .addComponent(chk_opv)
                            .addComponent(chk_measles))
                        .addGap(102, 102, 102)
                        .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MedHistoryLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel54)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_doses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(chk_influenza)
                            .addComponent(chk_hepa)
                            .addComponent(chk_other)
                            .addGroup(MedHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(18, 18, 18)
                                .addComponent(txt_other_vacc, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        MedHistoryLayout.setVerticalGroup(
            MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MedHistoryLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel34)
                .addGap(45, 45, 45)
                .addComponent(chk_past)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_past_illness, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(chk_injuries)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_injuries, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(chk_operations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_operations, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(chk_hospital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel39)
                .addGap(40, 40, 40)
                .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_bcg)
                    .addComponent(chk_influenza))
                .addGap(18, 18, 18)
                .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_dpt)
                    .addComponent(chk_hepa))
                .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MedHistoryLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(chk_opv)
                        .addGap(19, 19, 19)
                        .addComponent(chk_measles))
                    .addGroup(MedHistoryLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(cmb_doses, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chk_other)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MedHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(txt_other_vacc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        Layout_Panel.add(MedHistory, "card4");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 768));

        chk_regular.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_regular.setForeground(new java.awt.Color(46, 47, 51));
        chk_regular.setText("Regular [28-30 days] interval of menses");
        chk_regular.setEnabled(false);
        chk_regular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_regularActionPerformed(evt);
            }
        });

        chk_drink.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_drink.setForeground(new java.awt.Color(46, 47, 51));
        chk_drink.setText("Drinking");
        chk_drink.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_drinkItemStateChanged(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(10, 61, 115));
        jLabel68.setText("If married");

        cmb_premature.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_premature.setForeground(new java.awt.Color(46, 47, 51));
        cmb_premature.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0" }));
        cmb_premature.setEnabled(false);
        cmb_premature.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(145, 148, 150));
        jLabel72.setText("Born through CS");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(145, 148, 150));
        jLabel71.setText("Born through NSD ");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(145, 148, 150));
        jLabel74.setText("Premature");

        cmb_pads.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_pads.setForeground(new java.awt.Color(46, 47, 51));
        cmb_pads.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmb_pads.setEnabled(false);
        cmb_pads.setPreferredSize(new java.awt.Dimension(56, 30));

        chk_drugs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_drugs.setForeground(new java.awt.Color(46, 47, 51));
        chk_drugs.setText("Took prohibited drugs?");
        chk_drugs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_drugsItemStateChanged(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(145, 148, 150));
        jLabel63.setText("Date of last menstrual period");

        cmb_preg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_preg.setForeground(new java.awt.Color(46, 47, 51));
        cmb_preg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7" }));
        cmb_preg.setEnabled(false);
        cmb_preg.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_preg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_pregItemStateChanged(evt);
            }
        });

        txt_times.setEditable(false);
        txt_times.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_times.setForeground(new java.awt.Color(46, 47, 51));
        txt_times.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timesKeyTyped(evt);
            }
        });

        cmb_bottle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_bottle.setForeground(new java.awt.Color(46, 47, 51));
        cmb_bottle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Per Week", "Per Occasion" }));
        cmb_bottle.setEnabled(false);
        cmb_bottle.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(145, 148, 150));
        jLabel65.setText("Duration [ in days ]");

        jLabel64.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(10, 61, 115));
        jLabel64.setText("Menstration");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(145, 148, 150));
        jLabel70.setText("Number of alive children");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(145, 148, 150));
        jLabel73.setText("Number of abortion");

        cmb_nsd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_nsd.setForeground(new java.awt.Color(46, 47, 51));
        cmb_nsd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0" }));
        cmb_nsd.setEnabled(false);
        cmb_nsd.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_nsd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_nsdItemStateChanged(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(10, 61, 115));
        jLabel62.setText("For females only");

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(145, 148, 150));
        jLabel60.setText("Type of drug");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(46, 47, 51));
        jLabel59.setText("per day");

        cmb_dys.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_dys.setForeground(new java.awt.Color(46, 47, 51));
        cmb_dys.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Always", "Occasionally", "Never" }));
        cmb_dys.setEnabled(false);
        cmb_dys.setPreferredSize(new java.awt.Dimension(56, 30));

        chk_sex.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_sex.setForeground(new java.awt.Color(46, 47, 51));
        chk_sex.setText("History of sexual contact?");

        jLabel57.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(10, 61, 115));
        jLabel57.setText("Personal History");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(46, 47, 51));
        jLabel58.setText("bottles");

        txt_smoke.setEditable(false);
        txt_smoke.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_smoke.setForeground(new java.awt.Color(46, 47, 51));
        txt_smoke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_smokeKeyTyped(evt);
            }
        });

        txt_drug.setEditable(false);
        txt_drug.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_drug.setForeground(new java.awt.Color(46, 47, 51));
        txt_drug.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_drugKeyTyped(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(145, 148, 150));
        jLabel69.setText("Number of pregnancies");

        cmb_alive.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_alive.setForeground(new java.awt.Color(46, 47, 51));
        cmb_alive.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0" }));
        cmb_alive.setEnabled(false);
        cmb_alive.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_alive.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_aliveItemStateChanged(evt);
            }
        });

        cmb_duration.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_duration.setForeground(new java.awt.Color(46, 47, 51));
        cmb_duration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmb_duration.setEnabled(false);
        cmb_duration.setPreferredSize(new java.awt.Dimension(56, 30));

        cmb_stick.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_stick.setForeground(new java.awt.Color(46, 47, 51));
        cmb_stick.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sticks", "Pack" }));
        cmb_stick.setEnabled(false);
        cmb_stick.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(145, 148, 150));
        jLabel66.setText("Amount of pads per day");

        cmb_term.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_term.setForeground(new java.awt.Color(46, 47, 51));
        cmb_term.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Early Term", "Full Term", "Late Term", "Post Term" }));
        cmb_term.setEnabled(false);
        cmb_term.setPreferredSize(new java.awt.Dimension(56, 30));

        txt_numbot.setEditable(false);
        txt_numbot.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_numbot.setForeground(new java.awt.Color(46, 47, 51));
        txt_numbot.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_numbotKeyTyped(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(145, 148, 150));
        jLabel67.setText("Experiences Dysmenorrhea?");

        chk_smoke.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_smoke.setForeground(new java.awt.Color(46, 47, 51));
        chk_smoke.setText("Smoking");
        chk_smoke.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_smokeItemStateChanged(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(145, 148, 150));
        jLabel75.setText("Term");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(145, 148, 150));
        jLabel61.setText("How many times");

        date_mens.setForeground(new java.awt.Color(46, 47, 51));
        date_mens.setDateFormatString("MM-dd-YYYY");
        date_mens.setEnabled(false);
        date_mens.setFont(new java.awt.Font("Segoe UI", 0, 14));
        date_mens.setMaxSelectableDate(new java.util.Date(253370739661000L));
        date_mens.setPreferredSize(new java.awt.Dimension(87, 30));
        date_birth.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Calendar dateofBirth = Calendar.getInstance();
                dateofBirth.setTime((Date)evt.getNewValue());

                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - dateofBirth.get(Calendar.YEAR);

                if(today.get(Calendar.MONTH) < dateofBirth.get(Calendar.MONTH) ||
                    (today.get(Calendar.MONTH) == dateofBirth.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < dateofBirth.get(Calendar.DAY_OF_MONTH))) {
                    age--;
                }

                txt_age.setText(String.valueOf(age));
            }
        });

        txt_abortion.setEditable(false);
        txt_abortion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_abortion.setForeground(new java.awt.Color(46, 47, 51));
        txt_abortion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_abortionKeyTyped(evt);
            }
        });

        txt_cs.setEditable(false);
        txt_cs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_cs.setForeground(new java.awt.Color(46, 47, 51));
        txt_cs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_csKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel64)
                            .addComponent(jLabel67)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmb_duration, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_pads, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cmb_dys, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(date_mens, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chk_regular, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(109, 109, 109)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_preg, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel70)
                                    .addComponent(jLabel71)
                                    .addComponent(jLabel74))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_premature, 0, 1, Short.MAX_VALUE)
                                    .addComponent(cmb_nsd, 0, 1, Short.MAX_VALUE)
                                    .addComponent(cmb_alive, 0, 43, Short.MAX_VALUE))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(cmb_term, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel73)
                                    .addComponent(jLabel72))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_abortion, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_cs, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel62)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chk_smoke)
                                    .addComponent(chk_drugs)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(chk_sex, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chk_drink)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(txt_numbot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel58)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_bottle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_drug, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_times, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txt_smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cmb_stick, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addContainerGap(228, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel57)
                .addGap(40, 40, 40)
                .addComponent(chk_drink)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_bottle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(txt_numbot, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chk_smoke)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_stick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addGap(23, 23, 23)
                .addComponent(chk_drugs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_drug, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_times, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(chk_sex)
                .addGap(30, 30, 30)
                .addComponent(jLabel62)
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel68))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_mens, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_preg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chk_regular)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addComponent(jLabel65))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_pads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_dys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70)
                            .addComponent(cmb_alive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel73)
                            .addComponent(txt_abortion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_nsd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel71)
                            .addComponent(jLabel72)
                            .addComponent(txt_cs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_premature, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel74))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_term, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel75)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout PerHistoryLayout = new javax.swing.GroupLayout(PerHistory);
        PerHistory.setLayout(PerHistoryLayout);
        PerHistoryLayout.setHorizontalGroup(
            PerHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
        );
        PerHistoryLayout.setVerticalGroup(
            PerHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );

        Layout_Panel.add(PerHistory, "card5");

        tbl_famhis.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_famhis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DISEASE NAME", "FAMILY MEMBER/S"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_famhis.setRowHeight(27);
        tbl_famhis.setShowHorizontalLines(false);
        tbl_famhis.setShowVerticalLines(false);
        tbl_famhis.getTableHeader().setReorderingAllowed(false);
        tbl_famhis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_famhisMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbl_famhis);
        if (tbl_famhis.getColumnModel().getColumnCount() > 0) {
            tbl_famhis.getColumnModel().getColumn(0).setResizable(false);
            tbl_famhis.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel102.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(10, 61, 115));
        jLabel102.setText("Family History");

        cmb_fam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chk_father.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_father.setForeground(new java.awt.Color(46, 47, 51));
        chk_father.setText("Father");
        chk_father.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_fatherItemStateChanged(evt);
            }
        });
        chk_father.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_fatherActionPerformed(evt);
            }
        });

        chk_sibling.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_sibling.setForeground(new java.awt.Color(46, 47, 51));
        chk_sibling.setText("Siblings");
        chk_sibling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_siblingActionPerformed(evt);
            }
        });

        chk_aunt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_aunt.setForeground(new java.awt.Color(46, 47, 51));
        chk_aunt.setText("Aunts");
        chk_aunt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_auntActionPerformed(evt);
            }
        });

        chk_grandfather.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_grandfather.setForeground(new java.awt.Color(46, 47, 51));
        chk_grandfather.setText("Grandfather");
        chk_grandfather.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_grandfatherActionPerformed(evt);
            }
        });

        chk_uncle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_uncle.setForeground(new java.awt.Color(46, 47, 51));
        chk_uncle.setText("Uncles");
        chk_uncle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_uncleActionPerformed(evt);
            }
        });

        chk_grandmother.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_grandmother.setForeground(new java.awt.Color(46, 47, 51));
        chk_grandmother.setText("Grandmother");
        chk_grandmother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_grandmotherActionPerformed(evt);
            }
        });

        chk_children.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_children.setForeground(new java.awt.Color(46, 47, 51));
        chk_children.setText("Children");
        chk_children.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_childrenActionPerformed(evt);
            }
        });

        chk_mother.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_mother.setForeground(new java.awt.Color(46, 47, 51));
        chk_mother.setText("Mother");
        chk_mother.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_motherActionPerformed(evt);
            }
        });

        chk_nieces.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_nieces.setForeground(new java.awt.Color(46, 47, 51));
        chk_nieces.setText("Nieces");
        chk_nieces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_niecesActionPerformed(evt);
            }
        });

        chk_nephew.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chk_nephew.setForeground(new java.awt.Color(46, 47, 51));
        chk_nephew.setText("Nephews");
        chk_nephew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_nephewActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Arcon", 0, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(46, 47, 51));
        jButton9.setText("ADD");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(145, 148, 150));
        jLabel108.setText("Disease Name");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(145, 148, 150));
        jLabel109.setText("Family member afflicted");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 0, 0));
        jLabel120.setText("Double Click to Delete");

        javax.swing.GroupLayout ShowFamHistoryLayout = new javax.swing.GroupLayout(ShowFamHistory);
        ShowFamHistory.setLayout(ShowFamHistoryLayout);
        ShowFamHistoryLayout.setHorizontalGroup(
            ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowFamHistoryLayout.createSequentialGroup()
                        .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chk_nephew)
                            .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                                    .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chk_father)
                                        .addComponent(chk_sibling)
                                        .addComponent(chk_grandfather)
                                        .addComponent(chk_aunt))
                                    .addGap(69, 69, 69)
                                    .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chk_mother)
                                        .addComponent(chk_children)
                                        .addComponent(chk_grandmother)
                                        .addComponent(chk_uncle)
                                        .addComponent(chk_nieces)))
                                .addComponent(cmb_fam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel109, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel120)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(221, 221, 221))))
        );
        ShowFamHistoryLayout.setVerticalGroup(
            ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ShowFamHistoryLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel102)
                .addGap(29, 29, 29)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                        .addComponent(cmb_fam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel109)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                                .addComponent(chk_father)
                                .addGap(18, 18, 18)
                                .addComponent(chk_sibling)
                                .addGap(18, 18, 18)
                                .addComponent(chk_grandfather)
                                .addGap(18, 18, 18)
                                .addComponent(chk_aunt))
                            .addGroup(ShowFamHistoryLayout.createSequentialGroup()
                                .addComponent(chk_mother)
                                .addGap(18, 18, 18)
                                .addComponent(chk_children)
                                .addGap(18, 18, 18)
                                .addComponent(chk_grandmother)
                                .addGap(18, 18, 18)
                                .addComponent(chk_uncle)))
                        .addGap(18, 18, 18)
                        .addGroup(ShowFamHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chk_nephew)
                            .addComponent(chk_nieces))
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        Layout_Panel.add(ShowFamHistory, "card10");

        jLabel55.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(10, 61, 115));
        jLabel55.setText("Present Medical Problem");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(120, 120, 120));
        jLabel49.setText("Illness Name");

        txt_present_ill.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_present_ill.setForeground(new java.awt.Color(46, 47, 51));
        txt_present_ill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_present_illKeyTyped(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(120, 120, 120));
        jLabel50.setText("Last Attack");

        txt_maintenance.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_maintenance.setForeground(new java.awt.Color(46, 47, 51));
        txt_maintenance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_maintenanceKeyTyped(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(120, 120, 120));
        jLabel51.setText("Maintenance Medications");

        date_last_attack.setDateFormatString("YYYY-MM-dd");
        date_last_attack.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        date_last_attack.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                date_last_attackComponentAdded(evt);
            }
        });
        date_last_attack.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                date_last_attackFocusLost(evt);
            }
        });
        date_last_attack.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                date_last_attackInputMethodTextChanged(evt);
            }
        });
        date_last_attack.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                date_last_attackPropertyChange(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        tbl_medproblem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_medproblem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEDICAL PROBLEM", "LAST ATTACK", "MEDICATION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_medproblem.setRowHeight(27);
        tbl_medproblem.setShowHorizontalLines(false);
        tbl_medproblem.setShowVerticalLines(false);
        tbl_medproblem.getTableHeader().setReorderingAllowed(false);
        tbl_medproblem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_medproblemMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tbl_medproblem);
        if (tbl_medproblem.getColumnModel().getColumnCount() > 0) {
            tbl_medproblem.getColumnModel().getColumn(0).setResizable(false);
            tbl_medproblem.getColumnModel().getColumn(1).setResizable(false);
            tbl_medproblem.getColumnModel().getColumn(2).setResizable(false);
        }

        tbl_allergy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_allergy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " ALLERGY TYPE", "CAUSE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_allergy.setRowHeight(27);
        tbl_allergy.setShowHorizontalLines(false);
        tbl_allergy.setShowVerticalLines(false);
        tbl_allergy.getTableHeader().setReorderingAllowed(false);
        tbl_allergy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_allergyMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tbl_allergy);
        if (tbl_allergy.getColumnModel().getColumnCount() > 0) {
            tbl_allergy.getColumnModel().getColumn(0).setResizable(false);
            tbl_allergy.getColumnModel().getColumn(1).setResizable(false);
            tbl_allergy.getColumnModel().getColumn(1).setHeaderValue("CAUSE");
        }

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        txt_allergy_cause.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_allergy_cause.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_allergy_causeKeyTyped(evt);
            }
        });

        cmb_allergy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_allergy.setForeground(new java.awt.Color(46, 47, 51));
        cmb_allergy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Food Allergy", "Pet Allergy", "Hay Fever", "Hives", "Drug Allergy" }));
        cmb_allergy.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(120, 120, 120));
        jLabel48.setText("Type of Allergy");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(120, 120, 120));
        jLabel52.setText("Cause");

        jLabel110.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(10, 61, 115));
        jLabel110.setText("Allergies");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 0, 0));
        jLabel121.setText("Double Click to Delete");

        jLabel122.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 0, 0));
        jLabel122.setText("Double Click to Delete");

        javax.swing.GroupLayout Add_MedProbLayout = new javax.swing.GroupLayout(Add_MedProb);
        Add_MedProb.setLayout(Add_MedProbLayout);
        Add_MedProbLayout.setHorizontalGroup(
            Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Add_MedProbLayout.createSequentialGroup()
                .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_MedProbLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_maintenance)
                            .addComponent(txt_present_ill)
                            .addComponent(date_last_attack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(Add_MedProbLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel110)
                            .addGroup(Add_MedProbLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_allergy_cause, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmb_allergy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel55))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                        .addComponent(jScrollPane9))
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );
        Add_MedProbLayout.setVerticalGroup(
            Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_MedProbLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel55)
                .addGap(30, 30, 30)
                .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Add_MedProbLayout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_present_ill, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_last_attack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel121)
                .addGap(34, 34, 34)
                .addComponent(jLabel110)
                .addGroup(Add_MedProbLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_MedProbLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_allergy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_allergy_cause, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addGroup(Add_MedProbLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel122)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Layout_Panel.add(Add_MedProb, "card7");

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jLabel76.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(10, 61, 115));
        jLabel76.setText("Summary");

        cmb_assess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_assess.setForeground(new java.awt.Color(46, 47, 51));
        cmb_assess.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Physically and mentally Fit", "Needs close observation", "Needs further work up" }));
        cmb_assess.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_assess.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_assessItemStateChanged(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(10, 61, 115));
        jLabel77.setText("Assessment");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(145, 148, 150));
        jLabel78.setText("Ancillaries / Laboratories to be submitted:");

        txt_other_lab.setEditable(false);
        txt_other_lab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_other_lab.setForeground(new java.awt.Color(46, 47, 51));

        jLabel79.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(10, 61, 115));
        jLabel79.setText("Recommendation");

        txt_recommend.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_recommend.setForeground(new java.awt.Color(46, 47, 51));
        txt_recommend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_recommendKeyTyped(evt);
            }
        });

        cmb_submit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_submit.setForeground(new java.awt.Color(46, 47, 51));
        cmb_submit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CBC", "Urinalysis", "Fecalysis", "Blood Chem", "ECG", "X-ray", "Others" }));
        cmb_submit.setEnabled(false);
        cmb_submit.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_submit.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_submitItemStateChanged(evt);
            }
        });

        btn_add_lab.setText("ADD");
        btn_add_lab.setEnabled(false);
        btn_add_lab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_labActionPerformed(evt);
            }
        });

        tbl_submit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_submit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TO BE SUBMITTED"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_submit.setRowHeight(27);
        tbl_submit.setShowHorizontalLines(false);
        tbl_submit.setShowVerticalLines(false);
        tbl_submit.getTableHeader().setReorderingAllowed(false);
        jScrollPane10.setViewportView(tbl_submit);
        if (tbl_submit.getColumnModel().getColumnCount() > 0) {
            tbl_submit.getColumnModel().getColumn(0).setResizable(false);
        }

        lbl_bpresult1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bpresult1.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bpresult1.setText("Result");

        lbl_rrresult1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_rrresult1.setForeground(new java.awt.Color(70, 70, 70));
        lbl_rrresult1.setText("Result");

        lbl_hrresult1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_hrresult1.setForeground(new java.awt.Color(70, 70, 70));
        lbl_hrresult1.setText("Result");

        lbl_bmi1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi1.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi1.setText("Blood Type");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(20, 20, 20));
        jLabel19.setText("Heart Rate");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(20, 20, 20));
        jLabel20.setText("Respiratory Rate");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(20, 20, 20));
        jLabel47.setText("Blood Pressure");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(20, 20, 20));
        jLabel105.setText("BMI");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btn_add_lab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cmb_submit, javax.swing.GroupLayout.Alignment.LEADING, 0, 172, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_other_lab))
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_assess, javax.swing.GroupLayout.Alignment.LEADING, 0, 345, Short.MAX_VALUE))
                                .addGap(124, 124, 124)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel79)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_recommend, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel47)
                                            .addComponent(jLabel105))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbl_hrresult1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_bmi1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbl_rrresult1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(lbl_bpresult1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(28, 28, 28)))))))))
                .addGap(230, 230, 230))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel76)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmb_assess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel79)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_recommend, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(lbl_bpresult1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(lbl_rrresult1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(lbl_hrresult1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel105)
                            .addComponent(lbl_bmi1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel78)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_submit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_other_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_add_lab)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(208, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout AssessmentLayout = new javax.swing.GroupLayout(Assessment);
        Assessment.setLayout(AssessmentLayout);
        AssessmentLayout.setHorizontalGroup(
            AssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        AssessmentLayout.setVerticalGroup(
            AssessmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssessmentLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2))
        );

        Layout_Panel.add(Assessment, "card8");

        pokemon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_open_web.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_open_web.setText("Open Webcam");
        btn_open_web.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_open_webActionPerformed(evt);
            }
        });

        btn_capture.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_capture.setText("Capture Photo");
        btn_capture.setEnabled(false);
        btn_capture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_captureActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(10, 61, 115));
        jLabel46.setText("Capture Photo");

        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(120, 120, 120));
        jLabel101.setText("Tap ID to the scanner");

        txt_rfid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rfid.setForeground(new java.awt.Color(46, 47, 51));
        txt_rfid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rfidKeyTyped(evt);
            }
        });

        jLabel56.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(10, 61, 115));
        jLabel56.setText("RFID");

        javax.swing.GroupLayout PhotoLayout = new javax.swing.GroupLayout(Photo);
        Photo.setLayout(PhotoLayout);
        PhotoLayout.setHorizontalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhotoLayout.createSequentialGroup()
                .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PhotoLayout.createSequentialGroup()
                        .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PhotoLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PhotoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pokemon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(228, 228, 228)
                        .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(txt_rfid, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PhotoLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_open_web, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(btn_capture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(201, Short.MAX_VALUE))
        );
        PhotoLayout.setVerticalGroup(
            PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PhotoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel56))
                .addGap(18, 18, 18)
                .addGroup(PhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PhotoLayout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addGap(17, 17, 17)
                        .addComponent(txt_rfid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PhotoLayout.createSequentialGroup()
                        .addComponent(pokemon, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_open_web)))
                .addGap(11, 11, 11)
                .addComponent(btn_capture)
                .addContainerGap(313, Short.MAX_VALUE))
        );

        Layout_Panel.add(Photo, "card11");

        txt_systolic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_systolic.setForeground(new java.awt.Color(46, 47, 51));
        txt_systolic.setText("0");
        txt_systolic.setNextFocusableComponent(txt_diastolic);
        txt_systolic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_systolicFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_systolicFocusLost(evt);
            }
        });
        txt_systolic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_systolicActionPerformed(evt);
            }
        });
        txt_systolic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_systolicKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_systolicKeyTyped(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(120, 120, 120));
        jLabel90.setText("Blood Pressure(BP) :");

        jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(120, 120, 120));
        jLabel100.setText("Heart Rate (HR) / Pulse Rate (PR)");

        txt_hr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hr.setForeground(new java.awt.Color(46, 47, 51));
        txt_hr.setText("0");
        txt_hr.setNextFocusableComponent(cmb_height);
        txt_hr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hrFocusLost(evt);
            }
        });
        txt_hr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hrKeyTyped(evt);
            }
        });

        jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(120, 120, 120));
        jLabel104.setText("Respiratory Rate (RR) :");

        txt_rr.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rr.setForeground(new java.awt.Color(46, 47, 51));
        txt_rr.setText("0");
        txt_rr.setNextFocusableComponent(txt_hr);
        txt_rr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rrFocusLost(evt);
            }
        });
        txt_rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rrKeyTyped(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(120, 120, 120));
        jLabel106.setText("Blood Type");

        jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(120, 120, 120));
        jLabel107.setText("Weight");

        txt_weight.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_weight.setForeground(new java.awt.Color(46, 47, 51));
        txt_weight.setText("0");
        txt_weight.setNextFocusableComponent(jButton2);
        txt_weight.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_weightFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_weightFocusLost(evt);
            }
        });
        txt_weight.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_weightKeyTyped(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(120, 120, 120));
        jLabel111.setText("Height");

        txt_diastolic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_diastolic.setForeground(new java.awt.Color(46, 47, 51));
        txt_diastolic.setText("0");
        txt_diastolic.setNextFocusableComponent(jButton1);
        txt_diastolic.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_diastolicFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_diastolicFocusLost(evt);
            }
        });
        txt_diastolic.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_diastolicKeyTyped(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(10, 61, 115));
        jLabel31.setText("Body Mass Index");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(145, 148, 150));
        jLabel112.setText("BMI");

        jLabel45.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(10, 61, 115));
        jLabel45.setText("Blood");

        lbl_bpresult.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bpresult.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bpresult.setText("Result");

        lbl_rrresult.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_rrresult.setForeground(new java.awt.Color(70, 70, 70));
        lbl_rrresult.setText("Result");

        lbl_hrresult.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_hrresult.setForeground(new java.awt.Color(70, 70, 70));
        lbl_hrresult.setText("Result");

        cmb_assess1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_assess1.setForeground(new java.awt.Color(46, 47, 51));
        cmb_assess1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "O" }));
        cmb_assess1.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_assess1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_assess1ItemStateChanged(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(145, 148, 150));
        jLabel116.setText("Systolic");

        jLabel117.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(145, 148, 150));
        jLabel117.setText("Diastolic");

        lbl_bmi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi.setText("Result");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(70, 70, 70));
        jLabel113.setText("Feet");

        jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(70, 70, 70));
        jLabel114.setText("Inches");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(70, 70, 70));
        jLabel115.setText("Kilogram");

        cmb_height.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_height.setForeground(new java.awt.Color(46, 47, 51));
        cmb_height.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "4", "5", "6", "7" }));
        cmb_height.setNextFocusableComponent(cmb_heightin);
        cmb_height.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_height.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_heightItemStateChanged(evt);
            }
        });

        cmb_heightin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_heightin.setForeground(new java.awt.Color(46, 47, 51));
        cmb_heightin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" }));
        cmb_heightin.setNextFocusableComponent(txt_weight);
        cmb_heightin.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_heightin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_heightinItemStateChanged(evt);
            }
        });

        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(70, 70, 70));
        jLabel118.setText("per minute");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(70, 70, 70));
        jLabel119.setText("per minute");

        jButton1.setText("Get Result");
        jButton1.setNextFocusableComponent(txt_rr);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Calculate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl_bmi2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi2.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi2.setText("Underweight = less than 18.5");

        lbl_bmi3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi3.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi3.setText("Normal Weight = 18.6 - 24.9");

        lbl_bmi4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi4.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi4.setText("Overweight = 25 - 29.9");

        lbl_bmi5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_bmi5.setForeground(new java.awt.Color(70, 70, 70));
        lbl_bmi5.setText("Obese = greater than 30");

        lbl_point.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_point.setForeground(new java.awt.Color(70, 70, 70));
        lbl_point.setText("BMI point");

        javax.swing.GroupLayout BMILayout = new javax.swing.GroupLayout(BMI);
        BMI.setLayout(BMILayout);
        BMILayout.setHorizontalGroup(
            BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BMILayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(txt_diastolic, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(txt_systolic, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_assess1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(txt_hr, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel119))
                    .addComponent(jLabel100)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(txt_rr, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel118))
                    .addComponent(jLabel45)
                    .addComponent(lbl_bpresult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_hrresult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_rrresult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
                .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(cmb_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel113)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_heightin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel114))
                    .addComponent(jLabel31)
                    .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel115))
                    .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bmi3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bmi4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bmi5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_point, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_bmi2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
                .addGap(280, 280, 280))
        );
        BMILayout.setVerticalGroup(
            BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BMILayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel113)
                            .addComponent(jLabel114)
                            .addComponent(cmb_height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_heightin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel107)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_weight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel115))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel112)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_point)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_bmi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_bmi2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_bmi3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_bmi4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_bmi5))
                    .addGroup(BMILayout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_assess1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel90)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_systolic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_diastolic, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel117))
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_bpresult)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_rrresult)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BMILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_hr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel119))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_hrresult)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        Layout_Panel.add(BMI, "card12");

        btn_save.setIcon(new javax.swing.ImageIcon("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\icons\\iconkuno\\savebutton.png")); // NOI18N
        btn_save.setBorderPainted(false);
        btn_save.setContentAreaFilled(false);
        btn_save.setFocusPainted(false);
        btn_save.setFocusable(false);
        btn_save.setHideActionText(true);
        btn_save.setIconTextGap(0);
        btn_save.setRolloverIcon(new javax.swing.ImageIcon("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\icons\\iconkuno\\savebutton_hover.png")); // NOI18N
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arcon", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(46, 47, 51));
        jButton3.setText("CANCEL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Control_PanelLayout = new javax.swing.GroupLayout(Control_Panel);
        Control_Panel.setLayout(Control_PanelLayout);
        Control_PanelLayout.setHorizontalGroup(
            Control_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Control_PanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Control_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Control_PanelLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btn_save)))
                .addGap(89, 89, 89))
        );
        Control_PanelLayout.setVerticalGroup(
            Control_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Control_PanelLayout.createSequentialGroup()
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Control_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Control_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Layout_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1076, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Layout_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Control_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chk_bcgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_bcgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_bcgActionPerformed

    private void chk_dptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_dptActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_dptActionPerformed

    private void chk_opvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_opvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_opvActionPerformed

    private void chk_influenzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_influenzaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_influenzaActionPerformed

    private void date_last_attackComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_date_last_attackComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_date_last_attackComponentAdded

    private void date_last_attackFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_date_last_attackFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_date_last_attackFocusLost

    private void date_last_attackInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_date_last_attackInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_date_last_attackInputMethodTextChanged

    private void date_last_attackPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_date_last_attackPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_date_last_attackPropertyChange

    private void chk_regularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_regularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_regularActionPerformed

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        ///// BASIC NA BASIC
        
        
        if (txt_id.getText().isEmpty() ||
                txt_lname.getText().isEmpty() ||
                txt_fname.getText().isEmpty() ||
                txt_mname.getText().isEmpty() ||
                date_birth.getDate().toString().isEmpty() ||
                txt_pbirth.getText().isEmpty() ||
                txt_address.getText().isEmpty() ||
                txt_brgy.getText().isEmpty() ||
                txt_city.getText().isEmpty() ||
                txt_contact.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Complete the required details on basic info", "WARNING!", JOptionPane.WARNING_MESSAGE);
            
            //// CALL ME MAYBE
            if (txt_gname.getText().isEmpty() ||
                txt_grelation.getText().isEmpty() ||
                txt_gaddress.getText().isEmpty() ||
                txt_gcontact.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please Complete the required details on contact info", "WARNING!", JOptionPane.WARNING_MESSAGE);
                
                if (cmb_status.getSelectedIndex()==2){
                    if (txt_spouse.getText().isEmpty() || txt_children.getText().isEmpty() || txt_scontact.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on the spouse section", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                //// PAST IS PAST
                if (chk_past.isSelected()){
                    if(txt_past_illness.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on past history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (chk_injuries.isSelected()){
                    if(txt_injuries.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on past history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (chk_operations.isSelected()){
                    if(txt_operations.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on past history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (chk_hospital.isSelected()){
                    if(txt_hospital.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on past history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                //// PERSONALAN
                if (chk_smoke.isSelected()){
                    if(txt_smoke.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on personal history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (chk_drugs.isSelected()){
                    if(txt_drug.getText().isEmpty() || txt_times.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on personal history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (chk_drink.isSelected()){
                    if(txt_numbot.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on personal history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                ///// FEMINIST
                if (cmb_gender.getSelectedIndex()==1){
                    if(date_mens.getDate().toString().isEmpty() || cmb_pads.getSelectedIndex()==0){
                        JOptionPane.showMessageDialog(null, "Please Complete the required details on personal history", "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
                
                if (txt_recommend.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Recommendation Needed");
                }
                
  
            }
            
        }else{
            e_sinave();
        }
                
            
        
    }//GEN-LAST:event_btn_saveActionPerformed

    private void chk_pastItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_pastItemStateChanged
        if (chk_past.isSelected()==true){
            txt_past_illness.setEditable(true);
        }
        else{
            txt_past_illness.setText("");
            txt_past_illness.setEditable(false);
        }
    }//GEN-LAST:event_chk_pastItemStateChanged

    private void chk_injuriesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_injuriesItemStateChanged
        if (chk_injuries.isSelected()==true){
            txt_injuries.setEditable(true);
        }
        else{
            txt_injuries.setText("");
            txt_injuries.setEditable(false);
        }
    }//GEN-LAST:event_chk_injuriesItemStateChanged

    private void chk_operationsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_operationsItemStateChanged
        if (chk_operations.isSelected()==true){
            txt_operations.setEditable(true);
        }
        else{
            txt_operations.setText("");
            txt_operations.setEditable(false);
        }
    }//GEN-LAST:event_chk_operationsItemStateChanged

    private void chk_hospitalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_hospitalItemStateChanged
        if (chk_hospital.isSelected()==true){
            txt_hospital.setEditable(true);
        }
        else{
            txt_hospital.setText("");
            txt_hospital.setEditable(false);
        }
    }//GEN-LAST:event_chk_hospitalItemStateChanged

    private void cmb_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_typeActionPerformed

    private void chk_otherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_otherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_otherActionPerformed

    private void chk_hepaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_hepaItemStateChanged
        if (chk_hepa.isSelected()){
                cmb_doses.setEnabled(true);
        }else{
                cmb_doses.setSelectedIndex(0);
                cmb_doses.setEnabled(false);
        }
    }//GEN-LAST:event_chk_hepaItemStateChanged

    private void chk_otherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_otherItemStateChanged
        if (chk_other.isSelected()){
                txt_other_vacc.setEditable(true);
        }else{
                txt_other_vacc.setText("");
                txt_other_vacc.setEditable(false);
        }
    }//GEN-LAST:event_chk_otherItemStateChanged

    private void chk_drinkItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_drinkItemStateChanged
        if (chk_drink.isSelected()){
                txt_numbot.setEditable(true);
                cmb_bottle.setEnabled(true);
        }else{
                txt_numbot.setText("");
                txt_numbot.setEditable(false);
                cmb_bottle.setEnabled(false);
        }
    }//GEN-LAST:event_chk_drinkItemStateChanged

    private void chk_smokeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_smokeItemStateChanged
        if (chk_smoke.isSelected()){
                txt_smoke.setEditable(true);
                cmb_stick.setEnabled(true);
        }else{
                txt_smoke.setText("");
                txt_smoke.setEditable(false);
                cmb_stick.setEnabled(false);
        }
    }//GEN-LAST:event_chk_smokeItemStateChanged

    private void chk_drugsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_drugsItemStateChanged
        if (chk_drugs.isSelected()){
                txt_drug.setEditable(true);
                txt_times.setEditable(true);
        }else{
                txt_drug.setText("");
                txt_times.setText("");
                txt_drug.setEditable(false);
                txt_times.setEditable(false);
        }
    }//GEN-LAST:event_chk_drugsItemStateChanged

    private void cmb_genderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_genderItemStateChanged
        int x = cmb_gender.getSelectedIndex();
        
        if (x==1){
            cmb_pads.setEnabled(true);
            date_mens.setEnabled(true);
            cmb_duration.setEnabled(true);
            chk_regular.setEnabled(true);
            cmb_dys.setEnabled(true);
        }else{
            cmb_pads.setEnabled(false);
            date_mens.setEnabled(false);
            cmb_duration.setEnabled(false);
            chk_regular.setEnabled(false);
            cmb_dys.setEnabled(false);
        }
    }//GEN-LAST:event_cmb_genderItemStateChanged

    private void cmb_statusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_statusItemStateChanged
        int y = cmb_status.getSelectedIndex();
        int x = cmb_gender.getSelectedIndex();
        
        if (y==2){
            txt_scontact.setEditable(true);
            txt_children.setEditable(true);
            cmb_age.setEnabled(true);
            txt_spouse.setEditable(true);
        }else{
            txt_scontact.setText("");
            txt_children.setText("");
            txt_spouse.setText("");
            txt_scontact.setEditable(false);
            txt_children.setEditable(false);
            cmb_age.setEnabled(false);
            txt_spouse.setEditable(false);
            
        }
        
        ///// CHECK IF THE PATIENT IS A GIRL AND MARRIED ////
        if ((y==1 || y==2 || y==3) && x==1){
            cmb_preg.setEnabled(true);
            cmb_alive.setEnabled(true);
            cmb_premature.setEnabled(true);
            cmb_nsd.setEnabled(true);
            txt_cs.setEnabled(true);
            txt_cs.setEditable(false);
            txt_abortion.setEnabled(true);
            txt_abortion.setEditable(false);
            cmb_term.setEnabled(true);
        }else{
            cmb_preg.setEnabled(false);
            cmb_alive.setEnabled(false);
            cmb_premature.setEnabled(false);
            cmb_nsd.setEnabled(false);
            txt_cs.setText("");
            txt_cs.setEnabled(false);
            txt_cs.setEditable(false);
            txt_abortion.setEnabled(false);
            txt_abortion.setEditable(false);
            txt_abortion.setText("");
            cmb_term.setEnabled(false);
        }
        
        
        
        
    }//GEN-LAST:event_cmb_statusItemStateChanged

    private void chk_fatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_fatherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_fatherActionPerformed

    private void chk_siblingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_siblingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_siblingActionPerformed

    private void chk_auntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_auntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_auntActionPerformed

    private void chk_grandfatherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_grandfatherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_grandfatherActionPerformed

    private void chk_uncleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_uncleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_uncleActionPerformed

    private void chk_grandmotherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_grandmotherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_grandmotherActionPerformed

    private void chk_childrenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_childrenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_childrenActionPerformed

    private void chk_motherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_motherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_motherActionPerformed

    private void chk_niecesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_niecesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_niecesActionPerformed

    private void chk_nephewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_nephewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chk_nephewActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        if (txt_present_ill.getText().isEmpty() || date_last_attack.getDateFormatString().isEmpty() || txt_maintenance.getText().isEmpty()){
            
            JOptionPane.showMessageDialog(null, "Please complete all textfields");
            txt_present_ill.setText("");
            date_last_attack.setDate(null);
            txt_maintenance.setText("");
        }else{
            
        
        
            String present, petsa, maintenance;

            present = txt_present_ill.getText();
            petsa = abc.format(date_last_attack.getDate());
            maintenance = txt_maintenance.getText();


            DefaultTableModel yea = (DefaultTableModel)tbl_medproblem.getModel();        
            yea.addRow(new Object[]{present, petsa, maintenance});
        
        }
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (chk_father.isSelected()==true ||
            chk_nieces.isSelected()==true ||
            chk_nephew.isSelected()==true ||
            chk_uncle.isSelected()==true ||
            chk_aunt.isSelected()==true ||
            chk_grandmother.isSelected()==true ||
            chk_grandfather.isSelected()==true ||
            chk_children.isSelected()==true ||
            chk_sibling.isSelected()==true ||
            chk_mother.isSelected()==true){
        
        
        String z = cmb_fam.getSelectedItem().toString();
        String x = "";
        
        if (chk_father.isSelected()==true){
            x = x + "Father ";
        }
        
        if (chk_mother.isSelected()==true){
            x = x + "Mother ";
        }
        
        if (chk_sibling.isSelected()==true){
            x = x + "Sibling ";
        }
        
        if (chk_children.isSelected()==true){
            x = x + "Children ";
        }
        
        if (chk_grandfather.isSelected()==true){
            x = x + "Grandfather ";
        }
        
        if (chk_grandmother.isSelected()==true){
            x = x + "Grandmother ";
        }
        
        if (chk_aunt.isSelected()==true){
            x = x + "Aunt ";
        }
        
        if (chk_uncle.isSelected()==true){
            x = x + "Uncle ";
        }
        
        if (chk_nephew.isSelected()==true){
            x = x + "Nephew ";
        }
        
        if (chk_nieces.isSelected()==true){
            x = x + "Niece ";
        }
        
        
        DefaultTableModel yea = (DefaultTableModel)tbl_famhis.getModel();        
        yea.addRow(new Object[]{z,x});
        
        
        int remove = cmb_fam.getSelectedIndex();
        cmb_fam.removeItemAt(remove);
        
        chk_nieces.setSelected(false);
        chk_nephew.setSelected(false);
        chk_uncle.setSelected(false);
        chk_aunt.setSelected(false);
        chk_grandmother.setSelected(false);
        chk_grandfather.setSelected(false);
        chk_father.setSelected(false);
        chk_mother.setSelected(false);
        chk_sibling.setSelected(false);
        chk_children.setSelected(false);
        }else{
            JOptionPane.showMessageDialog(null,"Please select at least one family member");
        }
        
        
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void chk_fatherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_fatherItemStateChanged
        
    }//GEN-LAST:event_chk_fatherItemStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String type, cause;
        type = cmb_allergy.getSelectedItem().toString();
        cause = txt_allergy_cause.getText();        
        if (cause.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please complete the information");
        }else{
        DefaultTableModel yea = (DefaultTableModel)tbl_allergy.getModel();        
        yea.addRow(new Object[]{type, cause});        
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cmb_assessItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_assessItemStateChanged
        
        if (cmb_assess.getSelectedIndex()==2){
            cmb_submit.setEnabled(true);
            btn_add_lab.setEnabled(true);
        }else{
            cmb_submit.setEnabled(false);
            txt_other_lab.setText("");
            btn_add_lab.setEnabled(false);
        }
        
    }//GEN-LAST:event_cmb_assessItemStateChanged

    private void cmb_submitItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_submitItemStateChanged
        if (cmb_submit.getSelectedIndex()==6){
            txt_other_lab.setEditable(true);
        }else{
            txt_other_lab.setEditable(false);
            txt_other_lab.setText("");
        }
    }//GEN-LAST:event_cmb_submitItemStateChanged

    private void btn_add_labActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_labActionPerformed
    
        String submit = cmb_submit.getSelectedItem().toString();
        if (cmb_submit.getSelectedIndex()==6){
            submit = txt_other_lab.getText();
        }

        DefaultTableModel yea = (DefaultTableModel)tbl_submit.getModel();        
        yea.addRow(new Object[]{submit});
    }//GEN-LAST:event_btn_add_labActionPerformed

    private void btn_open_webActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_open_webActionPerformed
        
        webSource = new VideoCapture(0);
        thisThread = new DaemonThread();
            Thread t = new Thread(thisThread);
            t.setDaemon(true);
            thisThread.runnable = true;
            t.start();
            
        btn_capture.setEnabled(true);
        btn_open_web.setEnabled(false);
            
    }//GEN-LAST:event_btn_open_webActionPerformed

    private void btn_captureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_captureActionPerformed
        if (txt_id.getText().equals("")){       
                JOptionPane.showMessageDialog(null, "Please input first Patient ID");
                
        }else{
                String save = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS_Image\\" +txt_id.getText()+ ".png";
                Highgui.imwrite(save, frame);
                ImageIcon icon = new ImageIcon(save);
                thisThread.runnable = false;
                webSource.release();
                btn_capture.setEnabled(false);
                btn_open_web.setEnabled(true);
        }
    }//GEN-LAST:event_btn_captureActionPerformed
    
    public void checkCamOpen(){
        if(webSource.isOpened()){
            webSource.release();
        }
        
    }
    
    
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
        
        
        cmb_fam.addItem("Hypertension");
        cmb_fam.addItem("Heart Disease");
        cmb_fam.addItem("Diabetes Mellitus");
        cmb_fam.addItem("Tuberculosis");
        cmb_fam.addItem("Stroke");
        cmb_fam.addItem("Drug Addiction");
        cmb_fam.addItem("Bronchial Asthma");
        cmb_fam.addItem("Mental Illness");
        cmb_fam.addItem("Alcoholism");
        cmb_fam.addItem("Epilepsy");
        cmb_fam.addItem("Cancer");
        cmb_fam.addItem("Lung / Liver / Kidney Disease");
        cmb_fam.addItem("Headaches");
        cmb_fam.addItem("Others");
        cmb_fam.setSelectedIndex(0);
        
        
        
        
        
        
        int getrow = tbl_famhis.getRowCount();
        for (int i = 0; i!=getrow; i++){
            
            String removeSakit = (tbl_famhis.getValueAt(i, 0).toString());

            cmb_fam.removeItem(removeSakit);
            
        }
        
        if (cmb_occupation.getSelectedIndex()==0){
            tgl_med.setEnabled(true);
            tgl_personal.setEnabled(true);
            tgl_fam.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_formWindowActivated

    private void cmb_occupationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_occupationItemStateChanged
             
        if (cmb_occupation.getSelectedIndex()==0 || cmb_occupation.getSelectedIndex()==1 ){
            try {
                tgl_med.setEnabled(true);
                tgl_fam.setEnabled(true);
                tgl_personal.setEnabled(true);
                cmb_lvl.setEnabled(true);
                cmb_program.setEnabled(true);
                cmb_type.setEnabled(true);
                int i = 0;
                if (cmb_occupation.getSelectedIndex()==0){
                    i=1;
                }else if (cmb_occupation.getSelectedIndex()==1){
                    i=0;
                }
                cmb_program.removeAllItems();
                String sql = "SELECT * FROM course WHERE College = '"+i+"'";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while(rs.next()){
                    String ya = rs.getString("course_name");
                    cmb_program.addItem(ya);
                }
                cmb_lvl.removeAllItems();
               
                String getyears = "SELECT years from course where course_name = '"+cmb_program.getSelectedItem().toString()+"'";
                pstmt = conn.prepareStatement(getyears);
                rs = pstmt.executeQuery();
                while(rs.next()){
                    int a = rs.getInt("years");
                    for (int zz = 0; zz<a; zz++){
                        int y = zz+1;
                        cmb_lvl.addItem(y);
                    } 
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(new_Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            tgl_med.setEnabled(false);
            tgl_fam.setEnabled(false);
            tgl_personal.setEnabled(false);
            cmb_program.setEnabled(false);
            cmb_type.setEnabled(false);
            cmb_lvl.setEnabled(false); 
        }
    }//GEN-LAST:event_cmb_occupationItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (txt_id.getText().isEmpty()==true){
            //checkCamOpen();
            this.dispose();
            new Home().setVisible(true);
        }else{            
            int dialogresult = JOptionPane.showConfirmDialog(null, "The data you entered will not be saved. Are you sure you want to exit?", "Warning!", JOptionPane.YES_NO_OPTION);           
            if (dialogresult==0){
                try {
                    //checkCamOpen();
                    this.dispose();
                    new Home().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();                    
                }
            }       
        }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cmb_programItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_programItemStateChanged
         

                
            
    }//GEN-LAST:event_cmb_programItemStateChanged

    private void txt_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
    }//GEN-LAST:event_txt_idKeyTyped

    private void txt_lnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lnameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_lname);
    }//GEN-LAST:event_txt_lnameKeyTyped

    private void txt_fnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fnameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_fname);
    }//GEN-LAST:event_txt_fnameKeyTyped

    private void txt_mnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mnameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_mname);
        
    }//GEN-LAST:event_txt_mnameKeyTyped

    private void txt_pbirthKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pbirthKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_pbirth);
    }//GEN-LAST:event_txt_pbirthKeyTyped

    private void txt_addressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_addressKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_PERIOD) ||
            (c==KeyEvent.VK_COMMA) ||   
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_address);
    }//GEN-LAST:event_txt_addressKeyTyped

    private void txt_brgyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_brgyKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_brgy);
    }//GEN-LAST:event_txt_brgyKeyTyped

    private void txt_cityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cityKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
         uppercase(txt_city);
    }//GEN-LAST:event_txt_cityKeyTyped

    private void txt_contactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_contactKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_contactKeyTyped

    private void txt_gnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gnameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_gname);
        
       
    }//GEN-LAST:event_txt_gnameKeyTyped

    private void txt_grelationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_grelationKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_grelation);
    }//GEN-LAST:event_txt_grelationKeyTyped

    private void txt_fatherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fatherKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_father);
        
    }//GEN-LAST:event_txt_fatherKeyTyped

    private void txt_foccupationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_foccupationKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_foccupation);
    }//GEN-LAST:event_txt_foccupationKeyTyped

    private void txt_motherKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_motherKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_mother);
    }//GEN-LAST:event_txt_motherKeyTyped

    private void txt_moccupationKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_moccupationKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_moccupation);
    }//GEN-LAST:event_txt_moccupationKeyTyped

    private void txt_spouseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_spouseKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_spouse);
    }//GEN-LAST:event_txt_spouseKeyTyped

    private void txt_past_illnessKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_past_illnessKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_past_illness);
    }//GEN-LAST:event_txt_past_illnessKeyTyped

    private void txt_injuriesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_injuriesKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_injuries);
    }//GEN-LAST:event_txt_injuriesKeyTyped

    private void txt_operationsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_operationsKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_operations);
    }//GEN-LAST:event_txt_operationsKeyTyped

    private void txt_drugKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_drugKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_drugKeyTyped

    private void txt_present_illKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_present_illKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_present_illKeyTyped

    private void txt_maintenanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maintenanceKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_maintenanceKeyTyped

    private void txt_allergy_causeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_allergy_causeKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_allergy_causeKeyTyped

    private void txt_recommendKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_recommendKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_recommend);
    }//GEN-LAST:event_txt_recommendKeyTyped

    private void txt_rfidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rfidKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE) ||
            (c==KeyEvent.VK_ENTER))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_rfidKeyTyped

    private void txt_timesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timesKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_timesKeyTyped

    private void txt_smokeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_smokeKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_smokeKeyTyped

    private void txt_numbotKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numbotKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_numbotKeyTyped

    private void txt_gcontactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gcontactKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_gcontactKeyTyped

    private void txt_fcontactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fcontactKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_fcontactKeyTyped

    private void txt_mcontactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mcontactKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_mcontactKeyTyped

    private void txt_childrenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_childrenKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_childrenKeyTyped

    private void txt_scontactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_scontactKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_scontactKeyTyped

    private void txt_gaddressKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gaddressKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_gaddress);
    }//GEN-LAST:event_txt_gaddressKeyTyped

    private void txt_fcompanyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fcompanyKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_fcompanyKeyTyped

    private void txt_mcompanyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mcompanyKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_mcompanyKeyTyped

    private void txt_hospitalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hospitalKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_COLON) ||
            (c==KeyEvent.VK_PERIOD) || 
            (c==KeyEvent.VK_COMMA) ||
            (c==KeyEvent.VK_SEMICOLON) ||
            (c==KeyEvent.VK_SEPARATOR) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_hospitalKeyTyped

    private void txt_other_vaccKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_other_vaccKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_other_vacc);
    }//GEN-LAST:event_txt_other_vaccKeyTyped

    private void txt_systolicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolicKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
        
    }//GEN-LAST:event_txt_systolicKeyTyped

    private void txt_hrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hrKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
        
    }//GEN-LAST:event_txt_hrKeyTyped

    private void txt_rrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rrKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_rrKeyTyped

    private void txt_weightKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_weightKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_weightKeyTyped

    private void txt_diastolicKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diastolicKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
    }//GEN-LAST:event_txt_diastolicKeyTyped

    private void cmb_assess1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_assess1ItemStateChanged
        
    }//GEN-LAST:event_cmb_assess1ItemStateChanged

    private void txt_systolicKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolicKeyPressed
        
        
        
        
    }//GEN-LAST:event_txt_systolicKeyPressed

    private void txt_diastolicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diastolicFocusLost
        
    }//GEN-LAST:event_txt_diastolicFocusLost

    private void txt_systolicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_systolicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_systolicActionPerformed

    private void txt_systolicFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_systolicFocusLost
        
    }//GEN-LAST:event_txt_systolicFocusLost

    private void txt_systolicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_systolicFocusGained
        txt_systolic.selectAll();
    }//GEN-LAST:event_txt_systolicFocusGained

    private void txt_diastolicFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diastolicFocusGained
        txt_diastolic.selectAll();
    }//GEN-LAST:event_txt_diastolicFocusGained

    private void cmb_heightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_heightItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_heightItemStateChanged

    private void cmb_heightinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_heightinItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_heightinItemStateChanged

    private void txt_weightFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_weightFocusLost
        
        
        
        
    }//GEN-LAST:event_txt_weightFocusLost

    private void txt_weightFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_weightFocusGained
        txt_weight.selectAll();
    }//GEN-LAST:event_txt_weightFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x = Integer.parseInt(txt_systolic.getText());
        int y = Integer.parseInt(txt_diastolic.getText());
        
        if (x<y){
            JOptionPane.showMessageDialog(null, "Systolic must be greater than Diastolic");
            txt_systolic.setText("0");
            txt_diastolic.setText("0");
            lbl_bpresult.setText("");
            lbl_bpresult1.setText("");
        }
        
        
        if (x<=89 && y<=59){
            lbl_bpresult.setText("Low Blood Pressure");
            lbl_bpresult1.setText("Low Blood Pressure");
        }else if ((x>=90 && x<=119) && (y>=59 && y<=79)){
            lbl_bpresult.setText("Normal");
            lbl_bpresult1.setText("Normal");
        }else if ((x>=120 && x<=139) || (y>=80 && y<=89)){   
            lbl_bpresult.setText("Prehypertension");  
            lbl_bpresult1.setText("Prehypertension");
        }else if ((x>=140 && x<=159) || (y>=90 && y<=99)){
            lbl_bpresult.setText("High Blood Pressure Stage 1");
            lbl_bpresult1.setText("High Blood Pressure Stage 1");
        }else if ((x>=160 && x<180) || (y>=100 && y<=110)){
            lbl_bpresult.setText("High Blood Pressure Stage 2");
            lbl_bpresult1.setText("High Blood Pressure Stage 2");
        }else{
            lbl_bpresult.setText("Hypertensive Crisis");
            lbl_bpresult1.setText("Hypertensive Crisis");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static double round1(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = cmb_height.getSelectedIndex()+3;
        int y = cmb_heightin.getSelectedIndex()+1;
        double z = Integer.parseInt(txt_weight.getText());
        
        ////// convert them shitness //////
        double ft_meter = x*0.3048;
        double inch_meter = y*0.0254;
        double getmeter = ft_meter + inch_meter;
        
        ///// calculate bmi //////
        double get_bmi = z/(getmeter+getmeter); 
        double ya = round1(get_bmi,2);
        
        String xyz = String.valueOf(ya);
                
        
        
        if (get_bmi <= 18.4){
            lbl_bmi.setText("Underweight");
            lbl_point.setText(xyz);
            lbl_bmi1.setText("Underweight");
        }else if (get_bmi >= 18.5 && get_bmi <= 24.9){
            lbl_bmi.setText("Normal Weight");
            lbl_point.setText(xyz);
            lbl_bmi1.setText("Normal Weight");
        }else if (get_bmi >=25 && get_bmi <= 29.9){
            lbl_bmi.setText("Overweight");
            lbl_point.setText(xyz);
            lbl_bmi1.setText("Overweight");
        }else{
            lbl_bmi.setText("Obese");
            lbl_point.setText(xyz);
            lbl_bmi1.setText("Obese");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_rrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rrFocusGained
        txt_rr.selectAll();
    }//GEN-LAST:event_txt_rrFocusGained

    private void txt_hrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hrFocusGained
        txt_hr.selectAll();
    }//GEN-LAST:event_txt_hrFocusGained

    private void txt_rrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rrFocusLost
        int x = Integer.parseInt(txt_rr.getText());
        
        if (x>=12 && x<=25){
            lbl_rrresult.setText("Normal");
            lbl_rrresult1.setText("Normal");
        }else{
            lbl_rrresult.setText("Abnormal");
            lbl_rrresult1.setText("Abnormal");
        }
    }//GEN-LAST:event_txt_rrFocusLost

    private void txt_hrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hrFocusLost
        int x = Integer.parseInt(txt_hr.getText());
        
        if (x>100){
            lbl_hrresult.setText("High Heart Rate");
            lbl_hrresult1.setText("High Heart Rate");
        }else if (x<60){
            
            lbl_hrresult.setText("Low Heart Rate");
            lbl_hrresult1.setText("Low Heart Rate");
        }else{
            lbl_hrresult.setText("Normal");
            lbl_hrresult1.setText("Normal");
        }
    }//GEN-LAST:event_txt_hrFocusLost

    private void BasicInfoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_BasicInfoComponentShown
        
    }//GEN-LAST:event_BasicInfoComponentShown

    private void tgl_basicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_basicActionPerformed
        // TODO add your handling code here:
         CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card2");
    }//GEN-LAST:event_tgl_basicActionPerformed

    private void tgl_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_contactActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card3");
    }//GEN-LAST:event_tgl_contactActionPerformed

    private void tgl_medActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_medActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card4");
    }//GEN-LAST:event_tgl_medActionPerformed

    private void tgl_personalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_personalActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card5");
    }//GEN-LAST:event_tgl_personalActionPerformed

    private void tgl_famActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_famActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card10");
    }//GEN-LAST:event_tgl_famActionPerformed

    private void tgl_problemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_problemActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card7");
    }//GEN-LAST:event_tgl_problemActionPerformed

    private void tgl_bmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_bmiActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card12");
    }//GEN-LAST:event_tgl_bmiActionPerformed

    private void tgl_assessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_assessActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card8");
    }//GEN-LAST:event_tgl_assessActionPerformed

    private void tgl_rfidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_rfidActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card11");
    }//GEN-LAST:event_tgl_rfidActionPerformed

    private void tgl_basicItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_basicItemStateChanged
        if (tgl_basic.isSelected()){
            tgl_basic.setForeground(Color.yellow);
            lbl_basic.setForeground(Color.yellow);
            
        }else{
            tgl_basic.setForeground(new Color(193,195,198)); 
            lbl_basic.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_basicItemStateChanged

    private void tgl_contactItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_contactItemStateChanged
        if (tgl_contact.isSelected()){
            tgl_contact.setForeground(Color.yellow);
            lbl_basic.setForeground(Color.yellow);
        }else{
            tgl_contact.setForeground(new Color(193,195,198)); 
            lbl_basic.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_contactItemStateChanged

    private void tgl_medItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_medItemStateChanged
        if (tgl_med.isSelected()){
            tgl_med.setForeground(Color.yellow);   
            lbl_medical.setForeground(Color.yellow);
        }else{
            tgl_med.setForeground(new Color(193,195,198)); 
            lbl_medical.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_medItemStateChanged

    private void tgl_personalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_personalItemStateChanged
        if (tgl_personal.isSelected()){
            tgl_personal.setForeground(Color.yellow);    
            lbl_medical.setForeground(Color.yellow);
        }else{
            tgl_personal.setForeground(new Color(193,195,198)); 
            lbl_medical.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_personalItemStateChanged

    private void tgl_famItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_famItemStateChanged
        if (tgl_fam.isSelected()){
            tgl_fam.setForeground(Color.yellow);    
            lbl_medical.setForeground(Color.yellow);
        }else{
            tgl_fam.setForeground(new Color(193,195,198)); 
            lbl_medical.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_famItemStateChanged

    private void tgl_problemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_problemItemStateChanged
        if (tgl_problem.isSelected()){
            tgl_problem.setForeground(Color.yellow);    
            lbl_medical.setForeground(Color.yellow);
        }else{
            tgl_problem.setForeground(new Color(193,195,198)); 
            lbl_medical.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_problemItemStateChanged

    private void tgl_bmiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_bmiItemStateChanged
        if (tgl_bmi.isSelected()){
            tgl_bmi.setForeground(Color.yellow);  
            lbl_assess.setForeground(Color.yellow);
        }else{
            tgl_bmi.setForeground(new Color(193,195,198)); 
            lbl_assess.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_bmiItemStateChanged

    private void tgl_assessItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_assessItemStateChanged
        if (tgl_assess.isSelected()){
            tgl_assess.setForeground(Color.yellow);  
            lbl_assess.setForeground(Color.yellow);
        }else{
            tgl_assess.setForeground(new Color(193,195,198));
            lbl_assess.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_assessItemStateChanged

    private void tgl_rfidItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_rfidItemStateChanged
        if (tgl_rfid.isSelected()){
            tgl_rfid.setForeground(Color.yellow);
            lbl_other.setForeground(Color.yellow);
        }else{
            tgl_rfid.setForeground(new Color(193,195,198));
            lbl_other.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_rfidItemStateChanged

    private void tgl_basicMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basicMouseEntered
        if (tgl_basic.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_basic.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_basicMouseEntered

    private void tgl_basicMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basicMouseExited
        if (tgl_basic.isSelected()){
            
        }else{
            tgl_basic.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_basicMouseExited

    private void tgl_contactMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contactMouseEntered
        if (tgl_contact.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_contact.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_contactMouseEntered

    private void tgl_contactMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contactMouseExited
        if (!tgl_contact.isSelected()){
      
            tgl_contact.setForeground(new Color(255,255,255));
        }
    }//GEN-LAST:event_tgl_contactMouseExited

    private void tgl_medMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medMouseEntered
        if (tgl_med.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_med.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_medMouseEntered

    private void tgl_personalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_personalMouseEntered
        if (tgl_personal.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_personal.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_personalMouseEntered

    private void tgl_famMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_famMouseEntered
        if (tgl_fam.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_fam.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_famMouseEntered

    private void tgl_problemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_problemMouseEntered
        if (tgl_problem.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_problem.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_problemMouseEntered

    private void tgl_bmiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bmiMouseEntered
        if (tgl_bmi.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_bmi.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_bmiMouseEntered

    private void tgl_assessMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_assessMouseEntered
        if (tgl_assess.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_assess.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_assessMouseEntered

    private void tgl_rfidMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_rfidMouseEntered
        if (tgl_rfid.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_rfid.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_rfidMouseEntered

    private void tgl_medMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medMouseExited
        if (!tgl_med.isSelected()){
      
            tgl_med.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_medMouseExited

    private void tgl_personalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_personalMouseExited
        if (!tgl_personal.isSelected()){
      
            tgl_personal.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_personalMouseExited

    private void tgl_famMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_famMouseExited
        if (!tgl_fam.isSelected()){
      
            tgl_fam.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_famMouseExited

    private void tgl_problemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_problemMouseExited
        if (!tgl_problem.isSelected()){
      
            tgl_problem.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_problemMouseExited

    private void tgl_bmiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bmiMouseExited
        if (!tgl_bmi.isSelected()){
      
            tgl_bmi.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_bmiMouseExited

    private void tgl_assessMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_assessMouseExited
        if (!tgl_assess.isSelected()){
      
            tgl_assess.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_assessMouseExited

    private void tgl_rfidMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_rfidMouseExited
        if (!tgl_rfid.isSelected()){
      
            tgl_rfid.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_rfidMouseExited

    private void txt_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusGained
        
        txt_id.setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }//GEN-LAST:event_txt_idFocusGained

    private void txt_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusLost
        
    }//GEN-LAST:event_txt_idFocusLost

    private void txt_abortionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_abortionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_abortionKeyTyped

    private void cmb_pregItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_pregItemStateChanged
        cmb_alive.removeAllItems();
        cmb_premature.removeAllItems();
        int i = cmb_preg.getSelectedIndex();
        if (i==0){
            cmb_alive.setEnabled(false);
            cmb_nsd.setEnabled(false);
            cmb_premature.setEnabled(false);
            cmb_term.setEnabled(false);
        }
        for (int x = 0; x<i; x++){
            
            cmb_alive.addItem(x);
            cmb_premature.addItem(x);
        }
    }//GEN-LAST:event_cmb_pregItemStateChanged

    private void cmb_aliveItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_aliveItemStateChanged
        int i = cmb_alive.getSelectedIndex();
        int o = cmb_preg.getSelectedIndex();
        int p = i-o;
        txt_abortion.setText(String.valueOf(p));
        
        for (int x = 0; x<i;x++){
            cmb_nsd.addItem(x);
        }
        
    }//GEN-LAST:event_cmb_aliveItemStateChanged

    private void txt_csKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_csKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_csKeyTyped

    private void cmb_nsdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_nsdItemStateChanged
        int i = cmb_nsd.getSelectedIndex();
        int o = cmb_alive.getSelectedIndex();
        int p = i-o;
        txt_cs.setText(String.valueOf(p));
    }//GEN-LAST:event_cmb_nsdItemStateChanged

    private void cmb_programMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmb_programMouseClicked
        
    }//GEN-LAST:event_cmb_programMouseClicked

    private void cmb_programActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_programActionPerformed
        
    }//GEN-LAST:event_cmb_programActionPerformed

    private void cmb_programFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_programFocusGained
         
//        cmb_lvl.removeAllItems();
//        
//        try{
// 
//            String getyears = "SELECT years from course where course_name = '"+cmb_program.getSelectedItem().toString()+"'";
//            pstmt = conn.prepareStatement(getyears);
//            rs = pstmt.executeQuery();
//            while(rs.next()){
//                int a = rs.getInt("years");
//                for (int i = 0; i<a; i++){
//                    int y = i+1;
//                    cmb_lvl.addItem(y);
//                }
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_cmb_programFocusGained

    private void cmb_programFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_programFocusLost
        cmb_lvl.removeAllItems();
        try{
            String getyears = "SELECT years from course where course_name = '"+cmb_program.getSelectedItem().toString()+"'";
            pstmt = conn.prepareStatement(getyears);
            rs = pstmt.executeQuery();
            
            while(rs.next()){
                
                int a = rs.getInt("years");
                
                for (int i = 0; i<a; i++){
                    int y = i+1;
                    cmb_lvl.addItem(y);
                }
             
            }
       
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_cmb_programFocusLost

    private void tbl_famhisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_famhisMouseClicked
        if (evt.getClickCount()==2){
            int i = tbl_famhis.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel)tbl_famhis.getModel();
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_tbl_famhisMouseClicked

    private void tbl_medproblemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_medproblemMouseClicked
        if (evt.getClickCount()==2){
            int i = tbl_medproblem.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel)tbl_famhis.getModel();
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_tbl_medproblemMouseClicked

    private void tbl_allergyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_allergyMouseClicked
        if (evt.getClickCount()==2){
            int i = tbl_allergy.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel)tbl_famhis.getModel();
            dtm.removeRow(i);
        }
    }//GEN-LAST:event_tbl_allergyMouseClicked

    private void txt_lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lnameActionPerformed

    private void txt_address1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_address1KeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_PERIOD) ||
            (c==KeyEvent.VK_COMMA) ||   
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_address1);
    }//GEN-LAST:event_txt_address1KeyTyped

    private void txt_address2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_address2KeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (Character.isDigit(c)) ||   
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_PERIOD) ||
            (c==KeyEvent.VK_COMMA) ||   
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Invalid Character", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        uppercase(txt_address2);
    }//GEN-LAST:event_txt_address2KeyTyped
    
    
    
    class DaemonThread implements Runnable{
        
    protected volatile boolean runnable = false;

    @Override
    public  void run()
    {
        synchronized(this)
        {
            while(runnable)
            {
                if(webSource.grab())
                {
		    	try
                        {
                            webSource.retrieve(frame);
                            Imgproc.resize(frame, frame, sz);
			    Highgui.imencode(".bmp", frame, mem);
			    Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
			    BufferedImage buff = (BufferedImage) im;
			    Graphics g=pokemon.getGraphics();
                            //Size sz = new Size(160,160);
			    if (g.drawImage(buff, 0, 0, pokemon.getWidth(), pokemon.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null))
			    if(runnable == false)
                            {
			    	System.out.println("CAPTURING");
			    	this.wait();
                            }
			}
			catch(Exception ex)
                        {
			    System.out.println("Error");
                        }
                    }
                }
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new new_Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Add_MedProb;
    private javax.swing.JPanel Assessment;
    private javax.swing.JPanel BMI;
    private javax.swing.JPanel BasicInfo;
    private javax.swing.JPanel ContactInfo;
    private javax.swing.JPanel Control_Panel;
    private javax.swing.JPanel Layout_Panel;
    private javax.swing.JPanel MedHistory;
    private javax.swing.JPanel Navigation;
    private javax.swing.JPanel PerHistory;
    private javax.swing.JPanel Photo;
    private javax.swing.JPanel ShowFamHistory;
    private javax.swing.JButton btn_add_lab;
    private javax.swing.JButton btn_capture;
    private javax.swing.JButton btn_open_web;
    private javax.swing.JButton btn_save;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chk_aunt;
    private javax.swing.JCheckBox chk_bcg;
    private javax.swing.JCheckBox chk_children;
    private javax.swing.JCheckBox chk_dpt;
    private javax.swing.JCheckBox chk_drink;
    private javax.swing.JCheckBox chk_drugs;
    private javax.swing.JCheckBox chk_father;
    private javax.swing.JCheckBox chk_grandfather;
    private javax.swing.JCheckBox chk_grandmother;
    private javax.swing.JCheckBox chk_hepa;
    private javax.swing.JCheckBox chk_hospital;
    private javax.swing.JCheckBox chk_influenza;
    private javax.swing.JCheckBox chk_injuries;
    private javax.swing.JCheckBox chk_measles;
    private javax.swing.JCheckBox chk_mother;
    private javax.swing.JCheckBox chk_nephew;
    private javax.swing.JCheckBox chk_nieces;
    private javax.swing.JCheckBox chk_operations;
    private javax.swing.JCheckBox chk_opv;
    private javax.swing.JCheckBox chk_other;
    private javax.swing.JCheckBox chk_past;
    private javax.swing.JCheckBox chk_regular;
    private javax.swing.JCheckBox chk_sex;
    private javax.swing.JCheckBox chk_sibling;
    private javax.swing.JCheckBox chk_smoke;
    private javax.swing.JCheckBox chk_uncle;
    private javax.swing.JComboBox cmb_age;
    private javax.swing.JComboBox cmb_alive;
    private javax.swing.JComboBox cmb_allergy;
    private javax.swing.JComboBox cmb_assess;
    private javax.swing.JComboBox cmb_assess1;
    private javax.swing.JComboBox cmb_bottle;
    private javax.swing.JComboBox cmb_doses;
    private javax.swing.JComboBox cmb_duration;
    private javax.swing.JComboBox cmb_dys;
    private javax.swing.JComboBox cmb_fam;
    private javax.swing.JComboBox cmb_gender;
    private javax.swing.JComboBox cmb_height;
    private javax.swing.JComboBox cmb_heightin;
    private javax.swing.JComboBox cmb_lvl;
    private javax.swing.JComboBox cmb_nationality;
    private javax.swing.JComboBox cmb_nsd;
    private javax.swing.JComboBox cmb_occupation;
    private javax.swing.JComboBox cmb_pads;
    private javax.swing.JComboBox cmb_preg;
    private javax.swing.JComboBox cmb_premature;
    private javax.swing.JComboBox cmb_program;
    private javax.swing.JComboBox cmb_religion;
    private javax.swing.JComboBox cmb_status;
    private javax.swing.JComboBox cmb_stick;
    private javax.swing.JComboBox cmb_submit;
    private javax.swing.JComboBox cmb_term;
    private javax.swing.JComboBox cmb_type;
    private com.toedter.calendar.JDateChooser date_birth;
    private com.toedter.calendar.JDateChooser date_last_attack;
    private com.toedter.calendar.JDateChooser date_mens;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbl_assess;
    private javax.swing.JLabel lbl_basic;
    private javax.swing.JLabel lbl_bmi;
    private javax.swing.JLabel lbl_bmi1;
    private javax.swing.JLabel lbl_bmi2;
    private javax.swing.JLabel lbl_bmi3;
    private javax.swing.JLabel lbl_bmi4;
    private javax.swing.JLabel lbl_bmi5;
    private javax.swing.JLabel lbl_bpresult;
    private javax.swing.JLabel lbl_bpresult1;
    private javax.swing.JLabel lbl_hrresult;
    private javax.swing.JLabel lbl_hrresult1;
    private javax.swing.JLabel lbl_medical;
    private javax.swing.JLabel lbl_other;
    private javax.swing.JLabel lbl_point;
    private javax.swing.JLabel lbl_rrresult;
    private javax.swing.JLabel lbl_rrresult1;
    private javax.swing.JLabel pokemon;
    private javax.swing.JTable tbl_allergy;
    private javax.swing.JTable tbl_famhis;
    private javax.swing.JTable tbl_medproblem;
    private javax.swing.JTable tbl_submit;
    private javax.swing.JToggleButton tgl_assess;
    private javax.swing.JToggleButton tgl_basic;
    private javax.swing.JToggleButton tgl_bmi;
    private javax.swing.JToggleButton tgl_contact;
    private javax.swing.JToggleButton tgl_fam;
    private javax.swing.JToggleButton tgl_med;
    private javax.swing.JToggleButton tgl_personal;
    private javax.swing.JToggleButton tgl_problem;
    private javax.swing.JToggleButton tgl_rfid;
    private javax.swing.JTextField txt_abortion;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_address1;
    private javax.swing.JTextField txt_address2;
    private javax.swing.JTextField txt_age;
    private javax.swing.JTextField txt_allergy_cause;
    private javax.swing.JTextField txt_brgy;
    private javax.swing.JTextField txt_children;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_cs;
    private javax.swing.JTextField txt_diastolic;
    private javax.swing.JTextField txt_drug;
    private javax.swing.JTextField txt_father;
    private javax.swing.JTextField txt_fcompany;
    private javax.swing.JTextField txt_fcontact;
    private javax.swing.JTextField txt_fname;
    private javax.swing.JTextField txt_foccupation;
    private javax.swing.JTextField txt_gaddress;
    private javax.swing.JTextField txt_gcontact;
    private javax.swing.JTextField txt_gname;
    private javax.swing.JTextField txt_grelation;
    private javax.swing.JTextField txt_hospital;
    private javax.swing.JTextField txt_hr;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_injuries;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_maintenance;
    private javax.swing.JTextField txt_mcompany;
    private javax.swing.JTextField txt_mcontact;
    private javax.swing.JTextField txt_mname;
    private javax.swing.JTextField txt_moccupation;
    private javax.swing.JTextField txt_mother;
    private javax.swing.JTextField txt_numbot;
    private javax.swing.JTextField txt_operations;
    private javax.swing.JTextField txt_other_lab;
    private javax.swing.JTextField txt_other_vacc;
    private javax.swing.JTextField txt_past_illness;
    private javax.swing.JTextField txt_pbirth;
    private javax.swing.JTextField txt_present_ill;
    private javax.swing.JTextField txt_recommend;
    private javax.swing.JTextField txt_rfid;
    private javax.swing.JTextField txt_rr;
    private javax.swing.JTextField txt_scontact;
    private javax.swing.JTextField txt_smoke;
    private javax.swing.JTextField txt_spouse;
    private javax.swing.JTextField txt_systolic;
    private javax.swing.JTextField txt_times;
    private javax.swing.JTextField txt_weight;
    // End of variables declaration//GEN-END:variables
}
