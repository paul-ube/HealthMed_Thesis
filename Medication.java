/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;



import static cms.Home.abc;
import static cms.function.CBox_function.CBox_function;
import static cms.function.CBox_function.CBox_function02;
import static cms.function.Loginfunction.Firstname;
import static cms.function.Loginfunction.Lastname;
import static cms.function.Loginfunction.user_ID;
import cms.function.SQLConnection;
import static cms.function.UI_function.hello;
import static cms.function.Uppercase.uppercase;
import static cms.function.tbl_function.tbl_function;
import static cms.new_Registration.nowna;
import static cms.new_Registration.round1;
import com.sun.glass.events.KeyEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.round;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author Benjie
 */
public class Medication extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    static LocalTime hour = LocalTime.now();
    final String result [] = {"Non Reactive","Reactive"};
    Date max_date = new Date();
    int x;

    /**
     * Creates new form Medication
     */
    public Medication() {
        conn = SQLConnection.ConnDB();
        initComponents();
        
        this.toFront();
        tbl_function(tbl_med);
        tbl_function(tbl_trans);
        tbl_function(tbl_vacc);
        tbl_function(jTable1);
        tbl_function(jTable2);
        tbl_function(tbl_fam);
        updatetrnsTbl();
        
        datefrom.setMaxSelectableDate(max_date);
        dateto.setMaxSelectableDate(max_date);
        if (datefrom.getDate()==null){
            //dateto.setEnabled(false);
        }else{
            dateto.setEnabled(true);
            dateto.setMinSelectableDate(datefrom.getDate());
        }
        
    }
    
    private void startup(){

        try{
            
            updatetrnsTbl();
            
            String label_id = lbl_id.getText();
            
            String query = "SELECT * FROM patient_tbl WHERE id_no = '"+label_id+"'";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                
                String lname = rs.getString("lastname");
                String fname = rs.getString("firstname");
                String mname = rs.getString("middlename");
                
                String up = WordUtils.capitalizeFully(lname);
                String down = WordUtils.capitalizeFully(fname);
                String initial = WordUtils.initials(mname);
                String upperinitial = WordUtils.capitalizeFully(initial);
                txt_fullname.setText(up+", "+down+" "+upperinitial+".");
                
                String add = rs.getString("unit_no");
                String add1 = rs.getString("house_building"); //house
                String add2 = rs.getString("street");
                String brgy = rs.getString("brgy");
                String city = rs.getString("city");
                String contact = rs.getString("contact");
                
                txt_brgy.setText(brgy);
                txt_add.setText(add); //complete address
                txt_add1.setText(add1);
                txt_add2.setText(add2);
                txt_city.setText(city);
                txt_contact.setText(contact);
                
                String program = rs.getString("program");
                if (program.equalsIgnoreCase("null") || program.isEmpty()){
                    program = "";
                }
                String lvl = rs.getString("year_level");
                
                ////////////////////change color/////////////////
                
                if (lvl.equals("1")){
                    lvl = "1st Year";
                }else if (lvl.equals("2")){
                    lvl = "2nd Year";
                }else if (lvl.equals("3")){
                    lvl = "3rd Year";
                }else if (lvl.equals("4")){
                    lvl = "4th Year";
                }else if (lvl.equals("5")){
                    lvl = "5th Year";
                }else{
                    lvl = " - ";
                }
                
                String sec = rs.getString("p_section");
                
                txt_progyear.setText(program+""+sec+" "+lvl);
                
                Date dob = rs.getDate("bday"); // GET BDAY
                Calendar today = Calendar.getInstance();
                Calendar dateofBirth = Calendar.getInstance();
                dateofBirth.setTime(dob);
                
                x = today.get(Calendar.YEAR) - dateofBirth.get(Calendar.YEAR);
                
                if (today.get(Calendar.MONTH) < dateofBirth.get(Calendar.MONTH) || (today.get(Calendar.MONTH)==dateofBirth.get(Calendar.MONTH)&& today.get(Calendar.DAY_OF_MONTH) < dateofBirth.get(Calendar.DAY_OF_MONTH))){
                    x--;
                }

                String gender = rs.getString("gender");
                String status = rs.getString("status");
                txt_asl.setText(x+" "+gender+", "+status);

                
            }

            
            ////////// GET OVERVIEW ASSESSMENT ////////
            String assessment = "SELECT * FROM assessment_tbl WHERE id_no = '"+label_id+"'";
            pstmt = conn.prepareStatement(assessment);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String bmi = rs.getString("bmi");
                String ft = rs.getString("height");
                String in = rs.getString("height_in");
                String bp = rs.getString("bp_result");
                String sys = rs.getString("systolic");
                String dia = rs.getString("diastolic");
                String we = rs.getString("weight");
                String res = rs.getString("bp_result");
                String assess = rs.getString("assessment");
                String rr = rs.getString("rr_result");
                String rec = rs.getString("recommendation");
                String resrate = rs.getString("rr");
                if (resrate.equals("null")){
                    resrate = "0";
                }
                String hartrate = rs.getString("hr");
                if (hartrate.equals("null")){
                    hartrate = "0";
                }
                String type = rs.getString("bloodtype");
                String harty = rs.getString("hr_result");
                String deyt = rs.getString("assdate");
                String point = rs.getString("bmi_point");
                txt_remarks.setText(rec);
                txt_rr1.setText(resrate);
                txt_hr1.setText(hartrate);
                txt_rec.setText(rec);
                cmb_type.setSelectedItem(type);
                lbl_bp.setText(bp);
                lbl_respi.setText(rr);
                lbl_heart.setText(harty);
                lbl_body.setText(point);
                lbl_bodyy.setText(bmi);
                lbl_blood.setText(type);
                
                txt_typ.setText(type);
                String ka = sys+" / "+dia;
                txt_sys.setText(ka);
                txt_pres.setText(res);
                txt_res.setText(resrate+" per minute ; "+rr);
                txt_hart.setText(hartrate+" per minute ; "+harty);
                txt_hayt.setText(ft+"' "+in+"''");
                txt_weyt.setText(we+" Kg");
                txt_mass.setText(point+" : "+bmi);
                lbl_date.setText(deyt);
                
   
                txt_assess.setText(assess);
                cmb_assess.setSelectedItem(assess);
                txt_rr.setText(rr);
                
                txt_bmi.setText(bmi);
                txt_height.setText(ft+"' "+in+"''");
                txt_bp.setText(bp);

                cmb_height1.setSelectedItem(ft);
                
                txt_weight1.setText(we);

                txt_systolic1.setText(sys);
                
                txt_diastolic1.setText(dia);
                
                
                txt_bigat.setText(we+ " Kg");
 
            }
            
            //////// GET PERSONAL ///////////////
            String personal = "SELECT * FROM personal_history WHERE id_no = '"+label_id+"'";
            
            pstmt = conn.prepareStatement(personal);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int smoker = rs.getInt("smoking");
                if (smoker==1){
                    txt_smoker.setText("Yes");
                }else{
                    txt_smoker.setText("No");
                }
    
            }

            ////////// GET MED PROBLEM ///////////////////
            String medical = "SELECT * FROM med_problem WHERE id_no = '"+label_id+"';";

            pstmt = conn.prepareStatement(medical);
            rs = pstmt.executeQuery();
            txt_medical.setText("");
            txt_medication.setText("");
            while (rs.next()){
                
                String active = rs.getString("active_ill");
                String medication = rs.getString("maintenance");
                
                txt_medical.setText(active);
                txt_medication.setText(medication);

            }
            ////// ALLERGY ///////
            String allergy = "SELECT * FROM allergy_tbl WHERE id_no = '"+label_id+"';";
            
            pstmt = conn.prepareStatement(allergy);
            rs = pstmt.executeQuery();
            txt_allergy.setText("");
            while (rs.next()){
                
                String type = rs.getString("allergy_type");
                String cause = rs.getString("allergy_cause");
                
                txt_allergy.append(type+" :  "+cause+" \n");
            }
            /////// CONTACTS ///////
            String contacts = "SELECT * FROM patient_tbl a"
                    + " INNER JOIN guardian_table b ON a.id_no = b.id_no"
                    + " INNER JOIN father_table c ON a.id_no = c.id_no"
                    + " INNER JOIN mother_table d ON a.id_no = d.id_no"
                    + " INNER JOIN spouse_table e ON a.id_no = e.id_no"
                    + " WHERE a.id_no = '"+lbl_id.getText()+"';";
            
            pstmt = conn.prepareStatement(contacts);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String grelation = rs.getString("g_relation");
                String g_fname = rs.getString("g_fname");
                String g_contact = rs.getString("g_contact");
                
                
                txt_gfname.setText(g_fname);
                txt_gcontact.setText(g_contact);
                lbl_relation.setText(grelation);
                String f_fname = rs.getString("f_fname");
                String f_contact = rs.getString("f_contact");
                
                txt_father.setText(f_fname);
                txt_father1.setText(f_contact);
                
                String m_fname = rs.getString("m_fname");
                String m_contact = rs.getString("m_contact");
                
                txt_mother.setText(m_fname);
                txt_mother1.setText(m_contact);
                
                String s_fname = rs.getString("s_fname");
                String s_contact = rs.getString("s_contact");
                
                if (s_fname == null){
                    txt_spouse.setText(" N/A");

                }else{
                
                txt_spouse.setText(s_fname);
                txt_spouse1.setText(s_contact);
                        
                
                }
            }
            
            
            
            ////// PICTURE
            
            String nullimage = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS_Image\\NOIMAGE.jpg"; 
            try{
                 //byte[] imageBytes;
                 Image image;

                 String getphoto = "SELECT image FROM photo"
                         + " WHERE id_no = '"+lbl_id.getText()+"';";
                 pstmt = conn.prepareStatement(getphoto);
                 rs = pstmt.executeQuery();

                 while (rs.next()){
                     byte[] imageBytes = rs.getBytes("image");
                     //System.out.println(Arrays.toString(imageBytes));
                     if(imageBytes == null){
                         //JOptionPane.showMessageDialog(this, "WALANG PICTURE GAGO");
                         //image = getToolkit().createImage(nullimage);
                         ImageIcon icon = new ImageIcon(nullimage);
                         photo_bomb.setIcon(icon);
                     }else{
                         image = getToolkit().createImage(imageBytes);
                         ImageIcon icon = new ImageIcon(image);
                         photo_bomb.setIcon(icon);
                     }

                 }
             }catch(SQLException e){
                     JOptionPane.showMessageDialog(null, e);
             }   
            
            
            
            
            
            CBox_function(cmb_illname);
            CBox_function02(cmb_vaccine);
            refreshsuggestedmed(tbl_med);
            
            
            String check_hrm = "SELECT program FROM patient_tbl WHERE id_no = '"+label_id+"'";
            pstmt = conn.prepareStatement(check_hrm);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String hrm_ba = rs.getString("program");
                
                if (hrm_ba.equalsIgnoreCase("BSHRM") || hrm_ba.equalsIgnoreCase("BSTM")){
                    jTabbedPane1.setEnabledAt(1, true);
                }else{
                    jTabbedPane1.setEnabledAt(1, false);
                }
                        
            }
            
            try {
                
                
            String bakanasalabasna = "SELECT id_no FROM clinic_assess WHERE id_no='"+label_id+"' AND assdate = '"+nowna+"'";
            pstmt = conn.prepareStatement(bakanasalabasna);
            rs = pstmt.executeQuery();
            
            
            if (rs.next()){
                
                jTabbedPane1.setEnabledAt(0,false);
                jTabbedPane1.setSelectedIndex(2);
            }
            
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
            
        try{
            
            //this is the code for auto generating ordernumber
            String assess_no = "SELECT COUNT(ass_num) as a FROM clinic_assess;";
            pstmt = conn.prepareStatement(assess_no);
            rs = pstmt.executeQuery(assess_no);

            if (rs.last() == false){
                lbl_assess.setText("1");
                //assno.setText("ORD-1");
            }else{
                String count =  rs.getString("a");
                int ord = Integer.parseInt(count)+1;
                lbl_assess.setText(Integer.toString(ord));      
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            
            //this is the code for auto generating ordernumber
            String assess_no = "SELECT COUNT(read_id) as a FROM readminssion_table;";
            pstmt = conn.prepareStatement(assess_no);
            rs = pstmt.executeQuery(assess_no);

            if (rs.last() == false){
                tnx_no.setText("1");
                //assno.setText("ORD-1");
            }else{
                String count =  rs.getString("a");
                int ord = Integer.parseInt(count)+1;
                tnx_no.setText(Integer.toString(ord));      
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        

        
        
        ///// PAST HISTORY
        String past_tense = "SELECT * FROM med_history WHERE id_no = '"+lbl_id.getText()+"'";
        pstmt = conn.prepareStatement(past_tense);
        rs = pstmt.executeQuery();
        while (rs.next()){
            String illness = rs.getString("past_illness");
            if (illness.isEmpty()){
                illness = "   -   ";
            }
            String injuries = rs.getString("injuries");
            if (injuries.isEmpty()){
                injuries = "   -   ";
            }
            String operations = rs.getString("operations");
            if (operations.isEmpty()){
                operations = "   -   ";
            }
            String hospital = rs.getString("hospital");
            if (hospital.isEmpty()){
                hospital = "   -   ";
            }
            
            txt_pastmed.setText(illness);
            txt_injuries.setText(injuries);
            txt_operations.setText(operations);
            txt_hospital.setText(hospital);
            
        }
        
        
        
        
        
            
   
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
     private void updatetrnsTbl(){
        
        try{
        DefaultTableModel mod = (DefaultTableModel)tbl_trans.getModel();
        mod.setRowCount(0);

            String q = "SELECT DATE_FORMAT(date_given, '%M %e, %Y') as 'Date', TIME_FORMAT(time, '%h:%i %p') AS 'Time',a.user_ID, a.bedrest as 'bedrest',"
                    + " c.ill_name as illname, b.med_name as medname, TIME_FORMAT(TIMEDIFF(a.outtime, a.intime), '%h') as a, TIME_FORMAT(TIMEDIFF(a.outtime, a.intime), '%i') as b, a.outTime"
                    + " FROM med_dspnsd a INNER JOIN med_table b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON a.ill_id = c.ill_id"
                    + " INNER JOIN nurse_table d ON a.user_ID = d.user_ID"
                    + " WHERE id_no = '"+lbl_id.getText()+"'"
                    + " ORDER BY date_given DESC, time DESC;";
            
            String p;
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
             while (rs.next()){
                String ab = rs.getString("Date");
                String cd = rs.getString("Time");
                String ef = rs.getString("illname");
                String gh = rs.getString("medname");
                int ij = rs.getInt("bedrest");
                int in = rs.getInt("a");
                int in2 = rs.getInt("b");
                Date check = rs.getDate("a.outtime");
                
                if (ij==0){
                    p = "No";
                }else{
                    
                    if (check == null){
                        p = "Yes"+"; Ongoing";
                    }else{
                        p = "Yes"+"; "+in+" hours & "+in2+" minutes";
                    }
                    
                }
                
                String user = rs.getString("user_ID");
                String kwe = "SELECT a.lastname,b.position  FROM patient_tbl a INNER JOIN nurse_table b ON a.id_no = b.user_id WHERE a.ID_no = '"+user+"'";
                PreparedStatement pst = conn.prepareStatement(kwe);
                ResultSet r = pst.executeQuery();
                String lah = "";
                
                if(r.next()){
                    lah = r.getString("b.position") + " "+r.getString("a.lastname");
                    
                }


                Object karga [] = {ab,cd,ef,gh,p, lah};
                mod.addRow(karga);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void insertFluVac(){
         String ya = "SELECT influenza FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"'";
        try {
            pstmt = conn.prepareStatement(ya);
            rs = pstmt.executeQuery();

            while (rs.next()){
                int getValue = rs.getInt("influenza");
                if ( getValue == 1){
                    JOptionPane.showMessageDialog(this, "This Person has already received Flu Vaccination");
                }else{
                    
                    
                    int p = 0; // lalagyan
                    String hi = "SELECT med_remain FROM med_table WHERE med_name = 'Influenza Vaccine'";
                    pstmt = conn.prepareStatement(hi);
                    rs = pstmt.executeQuery();
                    if (rs.next()){
                        int a = rs.getInt("med_remain"); //get remaining
                        if (a<=0){
                            JOptionPane.showMessageDialog(null, "There is no influenza vaccine left");
                        }else{
                            p = a - 1; // subtract by one ready for insert 
                           
                            //update ang med_remain
                            String walk = "UPDATE med_table SET"
                            + " med_remain = '"+p+"'"
                            + " WHERE med_name = 'Influenza Vaccine'";
                            pstmt = conn.prepareStatement(walk);
                            pstmt.executeUpdate();
                            
                            String sql = "UPDATE vacc_flu SET"
                           + " influenza = '1',"
                           + " date_given = '"+nowna+"',"
                           + " given_by = '"+user_ID+"'"
                           + " WHERE id_no = '"+lbl_id.getText()+"'";                        
                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
                            
                    
                    
                            String hello = "INSERT INTO med_dspnsd SET"
                            + " id_no = '"+lbl_id.getText()+"',"
                            + " med_id = '708',"
                            + " ill_id = '806',"
                            + " date_given = '"+nowna+"',"
                            + " time = '"+hour+"',"
                            + " user_ID = '"+user_ID+"';";
                            pstmt = conn.prepareStatement(hello);
                            pstmt.executeUpdate();
                            
                            JOptionPane.showMessageDialog(this, "Successfully Added");
                        }
                        
                    }
                    
                    
                    
                    
                }
            }
         }catch (Exception e){
                e.printStackTrace();
        }
     }
    
    
    public void refreshsuggestedmed(JTable hex1){
        
      try{
        
        String illname = cmb_illname.getSelectedItem().toString();  
        DefaultTableModel mod = (DefaultTableModel)hex1.getModel();
        mod.getDataVector().removeAllElements();
        hex1.repaint();
//        
//        String sql = "SELECT * FROM med_ill "
//                    + "INNER JOIN med_table "
//                    + "ON med_ill.med_id=med_table.med_id "
//                    + "INNER JOIN ill_table "
//                    + "ON med_ill.ill_id = ill_table.ill_id"
//                    + " INNER JOIN ill_category "
//                    + "ON ill_table.category_id = ill_category.category_id  "
//                    + "WHERE med_table.med_name NOT IN (SELECT allergy_cause FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"')"
//                    + " AND med_ill.ill_id = (SELECT ill_table.ill_id "
//                    + "FROM ill_table WHERE ill_name='"+illname+"')"
//                    + " ORDER BY med_table.med_name";
        
        String sql = "SELECT * FROM med_ill a INNER JOIN med_table b ON a.med_id = b.med_id "
                + "INNER JOIN ill_table c ON a.ill_id = c.ill_id "
                + "INNER JOIN ill_category d ON c.category_id = d.category_id "
                + "INNER JOIN pivot_medicine e ON a.med_id = e.med_id "
                + "INNER JOIN generic f ON e.generic_id = f.generic_id "
                + "WHERE f.generic_desc NOT IN (SELECT allergy_cause FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"') "
                + "AND b.med_name NOT IN (SELECT allergy_cause FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"') "
                + "AND a.ill_id = (SELECT ill_table.ill_id FROM ill_table WHERE ill_name = '"+illname+"')"
                + " AND b.isActive = '1' "
                + "GROUP BY b.med_name "
                + "ORDER BY b.med_name";
        
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                

                String mn = rs.getString("b.med_name");
                String mr = rs.getString("b.med_remain");
                String xy = rs.getString("d.category_name");
                
                txt_category.setText(xy);
                if (txt_category.getText().equalsIgnoreCase("Contagious")){
                    txt_category.setForeground(new Color(243, 156, 18));
                }else if (txt_category.getText().equalsIgnoreCase("Emergency")){
                    txt_category.setForeground(Color.red);
                }else if (txt_category.getText().equalsIgnoreCase("Severe")){  
                    txt_category.setForeground(new Color(211, 84, 0));
                }else{
                    txt_category.setForeground(new Color(127, 140, 141));
                }
                
                
                Object karga [] = {mn,mr};
                mod.addRow(karga);
                hex1.setModel(mod);
            }

        }catch (Exception e){
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

        jPanel1 = new javax.swing.JPanel();
        photo_bomb = new javax.swing.JLabel();
        txt_fullname = new javax.swing.JLabel();
        lbl_id = new javax.swing.JLabel();
        txt_asl = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_bmi = new javax.swing.JLabel();
        txt_bp = new javax.swing.JLabel();
        txt_height = new javax.swing.JLabel();
        txt_smoker = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_bigat = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txt_medical = new javax.swing.JLabel();
        txt_medication = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_blood = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_rr = new javax.swing.JLabel();
        txt_assess = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_rec = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_allergy = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        lbl_relation15 = new javax.swing.JLabel();
        txt_spouse = new javax.swing.JTextField();
        lbl_relation3 = new javax.swing.JLabel();
        aaaa = new javax.swing.JLabel();
        txt_add2 = new javax.swing.JTextField();
        lbl_relation14 = new javax.swing.JLabel();
        txt_brgy = new javax.swing.JTextField();
        txt_mother = new javax.swing.JTextField();
        txt_add1 = new javax.swing.JTextField();
        txt_contact = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        txt_add = new javax.swing.JTextField();
        txt_gfname = new javax.swing.JTextField();
        lbl_relation1 = new javax.swing.JLabel();
        txt_father = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        lbl_relation2 = new javax.swing.JLabel();
        txt_city = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        lbl_relation4 = new javax.swing.JLabel();
        txt_mother1 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txt_father1 = new javax.swing.JTextField();
        txt_spouse1 = new javax.swing.JTextField();
        txt_gcontact = new javax.swing.JTextField();
        lbl_relation = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        btn_save_contact = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        lbl_relation5 = new javax.swing.JLabel();
        txt_alcohol = new javax.swing.JTextField();
        lbl_relation6 = new javax.swing.JLabel();
        txt_smoke = new javax.swing.JTextField();
        lbl_relation7 = new javax.swing.JLabel();
        txt_drugs = new javax.swing.JTextField();
        txt_sex = new javax.swing.JTextField();
        lbl_relation8 = new javax.swing.JLabel();
        date_mens = new com.toedter.calendar.JDateChooser();
        lbl_relation9 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lbl_relation10 = new javax.swing.JLabel();
        txt_interval = new javax.swing.JTextField();
        lbl_relation11 = new javax.swing.JLabel();
        txt_duration = new javax.swing.JTextField();
        lbl_relation12 = new javax.swing.JLabel();
        txt_dysm = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbl_fam = new javax.swing.JTable();
        txt_progyear = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_trans = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cmb_illname = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_med = new javax.swing.JTable();
        btn_med = new javax.swing.JButton();
        tgl_bedrest = new javax.swing.JToggleButton();
        txt_category = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cmb_vaccine = new javax.swing.JComboBox();
        cmb_dose = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btn_addvacc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        txt_bmi1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_vacc = new javax.swing.JTable();
        lbl_status = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btn_print_assess = new javax.swing.JButton();
        lbl_assess = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_assessment = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        txt_recommend = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        daysnumber = new javax.swing.JSpinner();
        dateto = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        datefrom = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        tnx_no = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        checkbox1 = new javax.swing.JCheckBox();
        checkbox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        reason_txt = new javax.swing.JTextArea();
        Card_Past = new javax.swing.JPanel();
        Show_Past = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_pastmed = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_injuries = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_operations = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_hospital = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        Edit_Past = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        txt_past = new javax.swing.JTextField();
        txt_inj = new javax.swing.JTextField();
        txt_ope = new javax.swing.JTextField();
        txt_hos = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        Card_Vital = new javax.swing.JPanel();
        Show_Vital = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        txt_typ = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txt_sys = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_pres = new javax.swing.JLabel();
        txt_res = new javax.swing.JLabel();
        txt_hart = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txt_hayt = new javax.swing.JLabel();
        txt_weyt = new javax.swing.JLabel();
        txt_mass = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        lbl_date = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Edit_Vital = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        cmb_heightin1 = new javax.swing.JComboBox();
        jLabel119 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        txt_weight1 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        cmb_height1 = new javax.swing.JComboBox();
        jPanel13 = new javax.swing.JPanel();
        txt_hr1 = new javax.swing.JTextField();
        txt_rr1 = new javax.swing.JTextField();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel127 = new javax.swing.JLabel();
        cmb_type = new javax.swing.JComboBox();
        txt_systolic1 = new javax.swing.JTextField();
        txt_diastolic1 = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        lbl_bp = new javax.swing.JLabel();
        lbl_respi = new javax.swing.JLabel();
        lbl_heart = new javax.swing.JLabel();
        lbl_body = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txt_remarks = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        lbl_bodyy = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        cmb_assess = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setModalExclusionType(null);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(10, 61, 115));

        photo_bomb.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        photo_bomb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        photo_bomb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/business12 (1).png"))); // NOI18N
        photo_bomb.setIconTextGap(0);

        txt_fullname.setFont(new java.awt.Font("Segoe UI Light", 1, 16)); // NOI18N
        txt_fullname.setForeground(new java.awt.Color(240, 240, 240));
        txt_fullname.setText("Pascual, Benjie D.");

        lbl_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_id.setForeground(new java.awt.Color(240, 240, 240));
        lbl_id.setText("1000202952");

        txt_asl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_asl.setForeground(new java.awt.Color(240, 240, 240));
        txt_asl.setText("25 M, Single");

        jTabbedPane2.setForeground(new java.awt.Color(10, 61, 115));
        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTabbedPane2.setFocusable(false);
        jTabbedPane2.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(10, 61, 115));
        jLabel5.setText("Allergies");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(10, 61, 115));
        jLabel4.setText("Medical Condition");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(10, 61, 115));
        jLabel13.setText("Medications");

        txt_bmi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_bmi.setText("BMI");

        txt_bp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_bp.setText("Blood Pressure");

        txt_height.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_height.setText("Height");

        txt_smoker.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_smoker.setText("Smoker or Not?");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(10, 61, 115));
        jLabel14.setText("BMI");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(10, 61, 115));
        jLabel20.setText("Blood Pressure");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(10, 61, 115));
        jLabel21.setText("Height");

        txt_bigat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_bigat.setText("56");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(10, 61, 115));
        jLabel22.setText("Weight");

        txt_medical.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txt_medication.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_medication.setText("   ");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(10, 61, 115));
        jLabel24.setText("Blood Type");

        lbl_blood.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_blood.setText("Blood");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(10, 61, 115));
        jLabel25.setText("Respiratory Rate");

        txt_rr.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_rr.setText("RR");

        txt_assess.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        txt_assess.setText("Assessment");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(10, 61, 115));
        jLabel26.setText("Smoker");

        txt_rec.setFont(new java.awt.Font("Segoe UI Light", 1, 13)); // NOI18N
        txt_rec.setText("Assessment");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(10, 61, 115));
        jLabel32.setText("Assessment");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(10, 61, 115));
        jLabel35.setText("Remarks");

        jScrollPane4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txt_allergy.setEditable(false);
        txt_allergy.setColumns(15);
        txt_allergy.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        txt_allergy.setRows(2);
        txt_allergy.setTabSize(0);
        txt_allergy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane4.setViewportView(txt_allergy);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_bigat)
                                    .addComponent(txt_height, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_bp, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_rr, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_smoker)
                                    .addComponent(lbl_blood, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_rec, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(txt_assess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_medication, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(txt_medical, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_assess)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_rec)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_medical, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_medication))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_bmi))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txt_bp))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txt_rr))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txt_height))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txt_bigat))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(lbl_blood))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_smoker)
                    .addComponent(jLabel26))
                .addContainerGap())
        );

        jTabbedPane2.addTab("OVERVIEW", jPanel5);

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel11.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel11ComponentShown(evt);
            }
        });

        lbl_relation15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation15.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation15.setText("Street");
        lbl_relation15.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_spouse.setEditable(false);
        txt_spouse.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_spouse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_spouseKeyTyped(evt);
            }
        });

        lbl_relation3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation3.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation3.setText("City");
        lbl_relation3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        aaaa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        aaaa.setForeground(new java.awt.Color(46, 47, 51));
        aaaa.setText("Guardian");
        aaaa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_add2.setEditable(false);
        txt_add2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_add2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_add2KeyTyped(evt);
            }
        });

        lbl_relation14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation14.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation14.setText("House/ Building No");
        lbl_relation14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_brgy.setEditable(false);
        txt_brgy.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_brgy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_brgyKeyTyped(evt);
            }
        });

        txt_mother.setEditable(false);
        txt_mother.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_mother.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_motherKeyTyped(evt);
            }
        });

        txt_add1.setEditable(false);
        txt_add1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_add1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_add1KeyTyped(evt);
            }
        });

        txt_contact.setEditable(false);
        txt_contact.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_contact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_contactKeyTyped(evt);
            }
        });

        txt_add.setEditable(false);
        txt_add.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_addKeyTyped(evt);
            }
        });

        txt_gfname.setEditable(false);
        txt_gfname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_gfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_gfnameKeyTyped(evt);
            }
        });

        lbl_relation1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation1.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation1.setText("Unit No");
        lbl_relation1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_father.setEditable(false);
        txt_father.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_father.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_fatherKeyTyped(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(46, 47, 51));
        jLabel33.setText("Father");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_relation2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation2.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation2.setText("Brgy");
        lbl_relation2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_city.setEditable(false);
        txt_city.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_city.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cityKeyTyped(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(46, 47, 51));
        jLabel37.setText("Mother");
        jLabel37.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_relation4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation4.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation4.setText("Contact No.");
        lbl_relation4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_mother1.setEditable(false);
        txt_mother1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_mother1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mother1KeyTyped(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(46, 47, 51));
        jLabel38.setText("Spouse");
        jLabel38.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_father1.setEditable(false);
        txt_father1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_father1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_father1KeyTyped(evt);
            }
        });

        txt_spouse1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_spouse1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_spouse1KeyTyped(evt);
            }
        });

        txt_gcontact.setEditable(false);
        txt_gcontact.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_gcontact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_gcontactKeyTyped(evt);
            }
        });

        lbl_relation.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_relation4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(lbl_relation3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(247, 247, 247))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_gfname, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lbl_relation2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_relation15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_relation1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_relation14))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_add1)
                                            .addComponent(txt_add2)
                                            .addComponent(txt_add)
                                            .addComponent(txt_brgy)
                                            .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(lbl_relation, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_gcontact, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(aaaa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_mother, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_father, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_father1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mother1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_spouse1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_spouse, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(5, 5, 5)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_add, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation14)
                    .addComponent(txt_add1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_add2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_brgy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aaaa)
                    .addComponent(txt_gfname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_gcontact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_relation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_father, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_father1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mother, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_mother1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_spouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_spouse1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel11);

        btn_save_contact.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_save_contact.setText("Update Info");
        btn_save_contact.setEnabled(false);
        btn_save_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_save_contactActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton17.setText("Edit Info");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_save_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_save_contact)
                    .addComponent(jButton17))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("   EMERGENCY CONTACTS   ", jPanel6);

        jPanel10.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel10ComponentShown(evt);
            }
        });

        lbl_relation5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation5.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation5.setText("Frequency of Alcohol Intake");
        lbl_relation5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_alcohol.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbl_relation6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation6.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation6.setText("Smoking History");
        lbl_relation6.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_smoke.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbl_relation7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation7.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation7.setText("Took Prohibited Drugs");
        lbl_relation7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_drugs.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        txt_sex.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbl_relation8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation8.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation8.setText("History of Sexual Contact");
        lbl_relation8.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        date_mens.setBackground(new java.awt.Color(255, 255, 255));
        date_mens.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbl_relation9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation9.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation9.setText("Date of last mens");
        lbl_relation9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lbl_relation10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation10.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation10.setText("Interval of menses");
        lbl_relation10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_interval.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbl_relation11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation11.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation11.setText("Duration");
        lbl_relation11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_duration.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lbl_relation12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_relation12.setForeground(new java.awt.Color(46, 47, 51));
        lbl_relation12.setText("Dysmenorrhea");
        lbl_relation12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txt_dysm.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(txt_alcohol))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbl_relation12)
                        .addGap(42, 42, 42)
                        .addComponent(txt_dysm))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lbl_relation11)
                        .addGap(82, 82, 82)
                        .addComponent(txt_duration))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_relation6)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(lbl_relation5)
                                .addGap(93, 93, 93))
                            .addComponent(lbl_relation7)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(lbl_relation8)
                                .addGap(29, 29, 29)
                                .addComponent(txt_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_relation10)
                                    .addComponent(lbl_relation9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_mens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_interval)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_smoke)
                                    .addComponent(txt_drugs))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(73, 73, 73))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lbl_relation5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_alcohol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_relation6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_smoke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_relation7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_drugs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation8)
                    .addComponent(txt_sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_mens, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_relation9, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_interval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation11)
                    .addComponent(txt_duration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_relation12)
                    .addComponent(txt_dysm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(112, 112, 112))
        );

        jTabbedPane2.addTab("PERSONAL HISTORY", jPanel10);

        jPanel16.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel16ComponentShown(evt);
            }
        });

        tbl_fam.setAutoCreateRowSorter(true);
        tbl_fam.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_fam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Disease Name", "Relationship"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_fam.setRowHeight(27);
        tbl_fam.setRowMargin(3);
        tbl_fam.setShowHorizontalLines(false);
        tbl_fam.setShowVerticalLines(false);
        tbl_fam.getTableHeader().setReorderingAllowed(false);
        jScrollPane11.setViewportView(tbl_fam);
        if (tbl_fam.getColumnModel().getColumnCount() > 0) {
            tbl_fam.getColumnModel().getColumn(0).setResizable(false);
            tbl_fam.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("FAMILY HISTORY", jPanel16);

        txt_progyear.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_progyear.setForeground(new java.awt.Color(240, 240, 240));
        txt_progyear.setText("BSIT 4th Year");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(10, 61, 115));
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(10, 61, 115));
        jButton4.setText("Print Medical Record");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 0));
        jButton9.setText("Edit Basic Info");
        jButton9.setContentAreaFilled(false);
        jButton9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton9.setFocusPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/instagram-logo.png"))); // NOI18N
        jButton16.setBorderPainted(false);
        jButton16.setContentAreaFilled(false);
        jButton16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(photo_bomb, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_fullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(83, 83, 83))
                            .addComponent(txt_progyear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_asl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txt_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_progyear, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_asl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(6, 6, 6))))
                        .addComponent(photo_bomb, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115)));

        tbl_trans.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_trans.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "TIME", "ILLNESS", "MEDICINE GIVEN", "BEDREST", "CATERED BY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_trans.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_trans.setMinimumSize(new java.awt.Dimension(75, 185));
        tbl_trans.setPreferredSize(new java.awt.Dimension(375, 155));
        tbl_trans.setRowHeight(27);
        tbl_trans.setShowHorizontalLines(false);
        tbl_trans.setShowVerticalLines(false);
        tbl_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_trans);
        if (tbl_trans.getColumnModel().getColumnCount() > 0) {
            tbl_trans.getColumnModel().getColumn(0).setResizable(false);
            tbl_trans.getColumnModel().getColumn(1).setResizable(false);
            tbl_trans.getColumnModel().getColumn(2).setResizable(false);
            tbl_trans.getColumnModel().getColumn(3).setResizable(false);
            tbl_trans.getColumnModel().getColumn(4).setResizable(false);
            tbl_trans.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel7.setFont(new java.awt.Font("Arcon", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(10, 61, 115));
        jLabel7.setText("Recent Transactions");

        jTabbedPane1.setForeground(new java.awt.Color(10, 61, 115));
        jTabbedPane1.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N

        jPanel2.setFont(new java.awt.Font("Helvetica Neue", 0, 11)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(10, 61, 115));
        jLabel11.setText("Select Illness");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(70, 70, 70));
        jLabel34.setText("Name");

        cmb_illname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_illname.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cmb_illname.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_illnameItemStateChanged(evt);
            }
        });
        cmb_illname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_illnameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(10, 61, 115));
        jLabel12.setText("Suggested Medicines");

        tbl_med.setAutoCreateRowSorter(true);
        tbl_med.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_med.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Remaining Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_med.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbl_med.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_med.setRowHeight(27);
        tbl_med.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbl_med.setShowHorizontalLines(false);
        tbl_med.setShowVerticalLines(false);
        tbl_med.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_med);
        if (tbl_med.getColumnModel().getColumnCount() > 0) {
            tbl_med.getColumnModel().getColumn(0).setResizable(false);
            tbl_med.getColumnModel().getColumn(1).setResizable(false);
        }

        btn_med.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_med.setForeground(new java.awt.Color(10, 61, 115));
        btn_med.setText("Dispense");
        btn_med.setBorderPainted(false);
        btn_med.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_medActionPerformed(evt);
            }
        });

        tgl_bedrest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tgl_bedrest.setForeground(new java.awt.Color(10, 61, 115));
        tgl_bedrest.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/add139_w (1).png"))); // NOI18N
        tgl_bedrest.setText("Bedrest");
        tgl_bedrest.setBorderPainted(false);
        tgl_bedrest.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_bedrest.setFocusPainted(false);
        tgl_bedrest.setFocusable(false);
        tgl_bedrest.setIconTextGap(14);
        tgl_bedrest.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/check52.png"))); // NOI18N
        tgl_bedrest.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/check52.png"))); // NOI18N
        tgl_bedrest.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_bedrestItemStateChanged(evt);
            }
        });
        tgl_bedrest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tgl_bedrestMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tgl_bedrestMousePressed(evt);
            }
        });
        tgl_bedrest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_bedrestActionPerformed(evt);
            }
        });

        txt_category.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_category.setForeground(new java.awt.Color(120, 120, 120));
        txt_category.setText("Category");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tgl_bedrest, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(btn_med, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(26, 26, 26)
                        .addComponent(cmb_illname, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_category, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(425, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cmb_illname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_category))
                .addGap(39, 39, 39)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tgl_bedrest, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_med))
                .addGap(18, 18, 18))
        );

        jTabbedPane1.addTab(" MEDICATION ", jPanel2);

        jPanel3.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel3ComponentShown(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(10, 61, 115));
        jLabel15.setText("Vaccine Type ");

        cmb_vaccine.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_vaccine.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Influenza", "Hepatitis B" }));
        cmb_vaccine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_vaccineItemStateChanged(evt);
            }
        });

        cmb_dose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_dose.setEnabled(false);
        cmb_dose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_doseActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(10, 61, 115));
        jLabel18.setText("Dose No.");

        jLabel16.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(10, 61, 115));
        jLabel16.setText("Vaccination");

        btn_addvacc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_addvacc.setText("Proceed");
        btn_addvacc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addvaccActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        txt_bmi1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        txt_bmi1.setForeground(new java.awt.Color(70, 70, 70));
        txt_bmi1.setText("Status : ");

        tbl_vacc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_vacc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "VACCINE", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vacc.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_vacc.setMinimumSize(new java.awt.Dimension(75, 185));
        tbl_vacc.setPreferredSize(new java.awt.Dimension(375, 155));
        tbl_vacc.setRowHeight(27);
        tbl_vacc.setShowHorizontalLines(false);
        tbl_vacc.setShowVerticalLines(false);
        tbl_vacc.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbl_vacc);
        if (tbl_vacc.getColumnModel().getColumnCount() > 0) {
            tbl_vacc.getColumnModel().getColumn(0).setResizable(false);
            tbl_vacc.getColumnModel().getColumn(1).setResizable(false);
        }

        lbl_status.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        lbl_status.setForeground(new java.awt.Color(10, 61, 115));
        lbl_status.setText("Status");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmb_vaccine, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_dose, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btn_addvacc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_bmi1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_status, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel16)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmb_vaccine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(cmb_dose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(btn_addvacc))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_bmi1)
                            .addComponent(lbl_status))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(" VACCINATION ", jPanel3);

        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(10, 61, 115));
        jLabel17.setText("Assessment");

        jLabel19.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(10, 61, 115));
        jLabel19.setText("Recommendation");

        btn_print_assess.setBackground(new java.awt.Color(0, 99, 170));
        btn_print_assess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_print_assess.setText("Save and Print");
        btn_print_assess.setBorderPainted(false);
        btn_print_assess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_print_assessActionPerformed(evt);
            }
        });

        lbl_assess.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        lbl_assess.setForeground(new java.awt.Color(70, 70, 70));
        lbl_assess.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        txt_assessment.setColumns(20);
        txt_assessment.setRows(5);
        jScrollPane5.setViewportView(txt_assessment);

        txt_recommend.setColumns(20);
        txt_recommend.setRows(5);
        jScrollPane6.setViewportView(txt_recommend);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                        .addComponent(lbl_assess, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_print_assess, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6))
                .addContainerGap(397, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_assess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btn_print_assess, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab(" CLINICAL ASSESSMENT ", jPanel4);

        jLabel27.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(10, 61, 115));
        jLabel27.setText("Reason");

        daysnumber.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        daysnumber.setEnabled(false);

        dateto.setBackground(new java.awt.Color(255, 255, 255));
        dateto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dateto.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Calendar to = Calendar.getInstance();
                to.setTime((Date)evt.getNewValue());

                Calendar from = datefrom.getCalendar();
                from.setTime((Date)datefrom.getDate());

                //Calendar today = Calendar.getInstance();
                int days = to.get(Calendar.DAY_OF_MONTH) - from.get(Calendar.DAY_OF_MONTH);

                daysnumber.setValue(days);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel30.setText("To");

        datefrom.setBackground(new java.awt.Color(255, 255, 255));
        datefrom.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel29.setText("From");

        jLabel28.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(10, 61, 115));
        jLabel28.setText("Dates");

        jLabel31.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(10, 61, 115));
        jLabel31.setText("Days of Sickness");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(120, 120, 120));
        jLabel36.setText("Transaction No.:");

        jLabel57.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(10, 61, 115));
        jLabel57.setText("Document Submitted");

        checkbox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkbox1.setText("Letter from Parent/Guardian");

        checkbox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkbox2.setText("Medical Certificate");

        jButton1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(10, 61, 115));
        jButton1.setText("Save and Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        reason_txt.setColumns(20);
        reason_txt.setRows(5);
        jScrollPane9.setViewportView(reason_txt);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkbox2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(datefrom, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(daysnumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tnx_no, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel57)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tnx_no, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox1)
                    .addComponent(checkbox2))
                .addGap(15, 15, 15)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datefrom, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daysnumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        jTabbedPane1.addTab("RE-ADMISSION SLIP", jPanel8);

        Card_Past.setLayout(new java.awt.CardLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(10, 61, 115));
        jLabel6.setText("Past Illnesses");

        txt_pastmed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pastmed.setText("asdfsadf");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(10, 61, 115));
        jLabel8.setText("Injuries");

        txt_injuries.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_injuries.setText("asdfsadf");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(10, 61, 115));
        jLabel9.setText("Operations");

        txt_operations.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_operations.setText("asdfsadf");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(10, 61, 115));
        jLabel10.setText("Hospitalizations");

        txt_hospital.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hospital.setText("asdfsadf");

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton10.setText("Edit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Show_PastLayout = new javax.swing.GroupLayout(Show_Past);
        Show_Past.setLayout(Show_PastLayout);
        Show_PastLayout.setHorizontalGroup(
            Show_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Show_PastLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(Show_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Show_PastLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Show_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_pastmed, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                            .addComponent(txt_injuries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_operations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_hospital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Show_PastLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        Show_PastLayout.setVerticalGroup(
            Show_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Show_PastLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pastmed, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_injuries, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_operations, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton10)
                .addGap(27, 27, 27))
        );

        Card_Past.add(Show_Past, "card11");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(10, 61, 115));
        jLabel52.setText("Past Medical Condition");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(10, 61, 115));
        jLabel53.setText("Injuries");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(10, 61, 115));
        jLabel54.setText("Operations");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(10, 61, 115));
        jLabel56.setText("Hospitalizations");

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton11.setText("Save Changes");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        txt_past.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_past.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_pastFocusGained(evt);
            }
        });
        txt_past.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_pastKeyTyped(evt);
            }
        });

        txt_inj.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_inj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_injFocusGained(evt);
            }
        });
        txt_inj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_injKeyTyped(evt);
            }
        });

        txt_ope.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_ope.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_opeFocusGained(evt);
            }
        });
        txt_ope.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_opeKeyTyped(evt);
            }
        });

        txt_hos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hosFocusGained(evt);
            }
        });
        txt_hos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hosKeyTyped(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton12.setText("Cancel");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Edit_PastLayout = new javax.swing.GroupLayout(Edit_Past);
        Edit_Past.setLayout(Edit_PastLayout);
        Edit_PastLayout.setHorizontalGroup(
            Edit_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Edit_PastLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(Edit_PastLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(Edit_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Edit_PastLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Edit_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_hos, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ope, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_inj, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_past, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(143, Short.MAX_VALUE))
        );
        Edit_PastLayout.setVerticalGroup(
            Edit_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Edit_PastLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_past, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel53)
                .addGap(7, 7, 7)
                .addComponent(txt_inj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ope, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addGroup(Edit_PastLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12))
                .addGap(27, 27, 27))
        );

        Card_Past.add(Edit_Past, "card3");

        jTabbedPane1.addTab("MEDICAL HISTORY", Card_Past);

        Card_Vital.setLayout(new java.awt.CardLayout());

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(10, 61, 115));
        jLabel40.setText("Blood Type");

        txt_typ.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_typ.setText("asdfsadf");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(10, 61, 115));
        jLabel41.setText("Blood Pressure");

        txt_sys.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_sys.setText("asdfsadf");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(10, 61, 115));
        jLabel42.setText("Respiratory Rate");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(10, 61, 115));
        jLabel43.setText("Heart Rate");

        txt_pres.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_pres.setText("asdfsadf");

        txt_res.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_res.setText("asdfsadf");

        txt_hart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hart.setText("asdfsadf");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(10, 61, 115));
        jLabel44.setText("Height");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(10, 61, 115));
        jLabel46.setText("Weight");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(10, 61, 115));
        jLabel47.setText("Body Mass Index");

        txt_hayt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hayt.setText("asdfsadf");

        txt_weyt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_weyt.setText("asdfsadf");

        txt_mass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mass.setText("asdfsadf");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setText("Assessment Date:");

        lbl_date.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_date.setForeground(new java.awt.Color(180, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 0, 0));
        jLabel2.setText("View BMI Chart");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Show_VitalLayout = new javax.swing.GroupLayout(Show_Vital);
        Show_Vital.setLayout(Show_VitalLayout);
        Show_VitalLayout.setHorizontalGroup(
            Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Show_VitalLayout.createSequentialGroup()
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Show_VitalLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Show_VitalLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_typ, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_sys, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_pres, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txt_hart, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(Show_VitalLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(txt_res, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(54, 54, 54)
                        .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Show_VitalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_hayt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_weyt, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_mass, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(Show_VitalLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Show_VitalLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_date, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Show_VitalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        Show_VitalLayout.setVerticalGroup(
            Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Show_VitalLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_typ)
                    .addComponent(txt_hayt))
                .addGap(18, 18, 18)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sys)
                    .addComponent(txt_weyt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pres)
                .addGap(15, 15, 15)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jLabel47))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_res)
                    .addComponent(txt_mass))
                .addGap(18, 18, 18)
                .addGroup(Show_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_hart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(22, 22, 22))
        );

        Card_Vital.add(Show_Vital, "card3");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Body Mass Index", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 1, 15), new java.awt.Color(10, 61, 115))); // NOI18N

        cmb_heightin1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_heightin1.setForeground(new java.awt.Color(46, 47, 51));
        cmb_heightin1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11" }));
        cmb_heightin1.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_heightin1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_heightin1ItemStateChanged(evt);
            }
        });

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(70, 70, 70));
        jLabel119.setText("Kilogram");

        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(70, 70, 70));
        jLabel118.setText("Feet");

        txt_weight1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_weight1.setForeground(new java.awt.Color(46, 47, 51));
        txt_weight1.setText("0");
        txt_weight1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_weight1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_weight1FocusLost(evt);
            }
        });
        txt_weight1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_weight1KeyTyped(evt);
            }
        });

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(120, 120, 120));
        jLabel120.setText("Height");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(70, 70, 70));
        jLabel121.setText("Inches");

        jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(120, 120, 120));
        jLabel122.setText("Weight");

        cmb_height1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_height1.setForeground(new java.awt.Color(46, 47, 51));
        cmb_height1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "4", "5", "6", "7" }));
        cmb_height1.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_height1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_height1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(cmb_height1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel118)
                        .addGap(18, 18, 18)
                        .addComponent(cmb_heightin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel121))
                    .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(txt_weight1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel119)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118)
                    .addComponent(jLabel121)
                    .addComponent(cmb_height1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_heightin1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel122)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_weight1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel119))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Respiratory & Heart Rate", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 1, 15), new java.awt.Color(10, 61, 115))); // NOI18N

        txt_hr1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hr1.setForeground(new java.awt.Color(46, 47, 51));
        txt_hr1.setText("0");
        txt_hr1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_hr1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_hr1FocusLost(evt);
            }
        });
        txt_hr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_hr1KeyTyped(evt);
            }
        });

        txt_rr1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_rr1.setForeground(new java.awt.Color(46, 47, 51));
        txt_rr1.setText("0");
        txt_rr1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_rr1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_rr1FocusLost(evt);
            }
        });
        txt_rr1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_rr1KeyTyped(evt);
            }
        });

        jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(70, 70, 70));
        jLabel123.setText("per minute");

        jLabel124.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(70, 70, 70));
        jLabel124.setText("per minute");

        jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(120, 120, 120));
        jLabel125.setText("Respiratory Rate");

        jLabel126.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(120, 120, 120));
        jLabel126.setText("Heart Rate");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txt_hr1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel123))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txt_rr1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel124))
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel126, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel125)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_rr1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel126)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hr1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Blood", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft JhengHei UI", 1, 15), new java.awt.Color(10, 61, 115))); // NOI18N

        jLabel127.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(145, 148, 150));
        jLabel127.setText("Systolic");

        cmb_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_type.setForeground(new java.awt.Color(46, 47, 51));
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "AB", "O" }));
        cmb_type.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_type.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_typeItemStateChanged(evt);
            }
        });

        txt_systolic1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_systolic1.setForeground(new java.awt.Color(46, 47, 51));
        txt_systolic1.setText("0");
        txt_systolic1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_systolic1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_systolic1FocusLost(evt);
            }
        });
        txt_systolic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_systolic1ActionPerformed(evt);
            }
        });
        txt_systolic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_systolic1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_systolic1KeyTyped(evt);
            }
        });

        txt_diastolic1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_diastolic1.setForeground(new java.awt.Color(46, 47, 51));
        txt_diastolic1.setText("0");
        txt_diastolic1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_diastolic1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_diastolic1FocusLost(evt);
            }
        });
        txt_diastolic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_diastolic1KeyTyped(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(120, 120, 120));
        jLabel92.setText("Blood Pressure(BP) :");

        jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(120, 120, 120));
        jLabel108.setText("Blood Type");

        jLabel128.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(145, 148, 150));
        jLabel128.setText("Diastolic");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_type, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel92, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_systolic1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                            .addComponent(txt_diastolic1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel127)
                            .addComponent(jLabel128))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_type, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_systolic1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel127))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_diastolic1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel128))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton5.setForeground(new java.awt.Color(50, 50, 50));
        jButton5.setText("Get Results");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(10, 61, 115));
        jLabel39.setText("Blood Pressure");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(10, 61, 115));
        jLabel45.setText("Respiratory Rate");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(10, 61, 115));
        jLabel48.setText("Heart Rate");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(10, 61, 115));
        jLabel49.setText("Body Mass Index");

        lbl_bp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lbl_respi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lbl_heart.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        lbl_body.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(10, 61, 115));
        jLabel50.setText("Remarks");

        txt_remarks.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_remarks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_remarksKeyTyped(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        lbl_bodyy.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(10, 61, 115));
        jLabel51.setText("Assessment");

        cmb_assess.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_assess.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Physically and Mentally Fit", "Needs Close Observation", "Needs Further Work Up" }));

        javax.swing.GroupLayout Edit_VitalLayout = new javax.swing.GroupLayout(Edit_Vital);
        Edit_Vital.setLayout(Edit_VitalLayout);
        Edit_VitalLayout.setHorizontalGroup(
            Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_VitalLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Edit_VitalLayout.createSequentialGroup()
                        .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Edit_VitalLayout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Edit_VitalLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel45)
                                    .addComponent(jLabel48))
                                .addGap(34, 34, 34)
                                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_heart, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_respi, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_bp, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Edit_VitalLayout.createSequentialGroup()
                                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel50)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_remarks, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                        .addComponent(cmb_assess, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(Edit_VitalLayout.createSequentialGroup()
                                        .addComponent(lbl_body, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lbl_bodyy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Edit_VitalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        Edit_VitalLayout.setVerticalGroup(
            Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_VitalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Edit_VitalLayout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39)
                            .addComponent(jLabel49)
                            .addComponent(lbl_bp, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_body, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_bodyy, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_respi, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51)
                        .addComponent(cmb_assess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel48)
                        .addComponent(lbl_heart, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel50)
                        .addComponent(txt_remarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(Edit_VitalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        Card_Vital.add(Edit_Vital, "card5");

        jTabbedPane1.addTab("VITAL STATISTICS", Card_Vital);

        jPanel7.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel7ComponentShown(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(10, 61, 115));
        jLabel23.setText("Allergies");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(10, 61, 115));
        jLabel58.setText("Medical Condition");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Cause"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(27);
        jTable1.setRowMargin(3);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medical Problem", "Last Attack", "Medication"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(27);
        jTable2.setRowMargin(3);
        jTable2.setShowHorizontalLines(false);
        jTable2.setShowVerticalLines(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane8.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Add Allergy");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton13.setText("Delete");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setText("Add Condition");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton15.setText("Delete");
        jButton15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(118, 118, 118)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton13))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton14)
                        .addComponent(jButton15)))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("ALLERGIES & MEDICATIONS", jPanel7);

        jLabel55.setFont(new java.awt.Font("Arcon", 0, 24)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(10, 61, 115));
        jLabel55.setText("Services");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("See all transaction");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_illnameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_illnameItemStateChanged
        //CBox_function(cmb_name);
      
        /*
        try{

            DefaultTableModel mod = (DefaultTableModel)med_tbl.getModel();
            mod.getDataVector().removeAllElements();
            med_tbl.repaint();

            String sql = "SELECT med_table.med_name, med_table.med_remain FROM med_ill "
            + "INNER JOIN med_table "
            + "ON med_ill.med_id=med_table.med_id "
            + "WHERE med_ill.ill_id = (SELECT ill_table.ill_id "
            + "FROM ill_table WHERE ill_name='"+xo+"')"
            + "ORDER BY med_table.med_name";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String mn = rs.getString("med_table.med_name");
                String mr = rs.getString("med_table.med_remain");

                Object karga [] = {mn,mr};
                mod.addRow(karga);
                med_tbl.setModel(mod);
            }

        }catch (Exception e){
            e.printStackTrace();
        }*/
        
        refreshsuggestedmed(tbl_med);

    }//GEN-LAST:event_cmb_illnameItemStateChanged

    private void cmb_illnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_illnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_illnameActionPerformed

    private void btn_medActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_medActionPerformed
        String cat = "1";
        String patch = "1";
        try{
            String student = lbl_id.getText();
            
            String illname = cmb_illname.getSelectedItem().toString();
            
            String id_ng_koya_mo = "";
            
            
//            String ill_id = "SELECT ill_id FROM ill_table WHERE ill_name = '"+illname+"'";
            
            
            int row = tbl_med.getSelectedRow();
            
            
            
            
            
            
//            String med_id = "SELECT med_id FROM med_table WHERE med_name = '"+medname+"'";

//            ////// CHECK KUNG MAY PININDOT SA MEDICINE TABLE //////
//            if (!tbl_med.isRowSelected(row)){
//                medname = "";
//            }else{
//                medname = tbl_med.getValueAt(row, 0).toString();
//            }

            String b_result = "";
            String hello = null;

            ////// Check if bedrest is Toggled ///////
            if (tgl_bedrest.isSelected()){
                b_result = "1";
                hello = hour.toString();
                
            }else{
                b_result = "0";
            }
            
           
            
            
            
            
            if (!tbl_med.isRowSelected(row)){
                ////// IF BEDREST & NO MEDICINE /////
                
                //// INSTANTATION
                

              
                
                
                if (tgl_bedrest.isSelected()){
                    int decide = JOptionPane.showConfirmDialog(null, "Provide bedrest without medicine?",null,JOptionPane.YES_NO_OPTION);
                    ///// KUNG GUSTO HUMIGA NG WALANG GAMOT /////
                    if (decide==0){
                        
                        ///// CHECK KUNG NAKA NASA BED SIYA //////
            
                        String check = "SELECT date_given, inTime FROM med_dspnsd WHERE id_no = '"+lbl_id.getText()+"' AND outTime is null AND inTime is not null"
                                + " AND date_given='"+nowna+"';";
                        pstmt = conn.prepareStatement(check);
                        rs = pstmt.executeQuery(check);


                        ///// kung meron nga siya /////
                        if (rs.next()){

                            JOptionPane.showMessageDialog(null, "This person is already in bed");


                        }else{

                            ///// bilangin ang bed na na-okupa /////
                            String count = "SELECT COUNT(id_no) as 'A' FROM med_dspnsd WHERE inTime is not null and outTime is null;";

                            pstmt = conn.prepareStatement(count);
                            rs = pstmt.executeQuery(count);
                            while(rs.next()){
                               int get = rs.getInt("A");
                               if (get==4){
                               JOptionPane.showMessageDialog(null, "All Beds are currently occupied");

                                }else{

                                   String query = "INSERT INTO med_dspnsd SET"
                                        + " med_id = null,"
                                        + " ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+illname+"'),"
                                        + " bedrest = '"+b_result+"',"
                                        + " id_no = '"+lbl_id.getText()+"',"
                                        + " date_given = '"+nowna+"',"
                                        + " time = '"+hour+"',"
                                        + " user_ID = '"+user_ID+"',"
                                        + " inTime = '"+hello+"',"
                                        + " outTime = null;";

                                    pstmt = conn.prepareStatement(query);
                                    pstmt.executeUpdate();

                                    JOptionPane.showMessageDialog(null, "Added Transaction");


                               }

                            }   

                        }
                        
                        
                        
                        
                        
                        
                        
                        
                    }else{ ///// EH AYAW HUMIGA NG WALANG GAMOT
                        
                        JOptionPane.showMessageDialog(null,"Please Select Medicine", null, JOptionPane.WARNING_MESSAGE);
                    }
                    
                }
                
                
            //// MAY GAMOT NA PINILI    
            }else{
                ////// INSTANTIATION /////
                String medname = tbl_med.getValueAt(row, 0).toString();
            
                String med_id = "SELECT med_id, med_category FROM med_table WHERE med_name='"+medname+"'";
                pstmt = conn.prepareStatement(med_id);
                rs = pstmt.executeQuery(med_id);
                
                while (rs.next()){

                    id_ng_koya_mo = rs.getString("med_id");
                    cat = rs.getString("med_category");
                    
                    if (cat.equals("3")){
                        SpinnerNumberModel sModel = new SpinnerNumberModel(0, 0, 20, 1);
                        JSpinner spinner = new JSpinner(sModel);
                        int option = JOptionPane.showOptionDialog(null, spinner, "Enter valid number", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                        if (option == JOptionPane.CANCEL_OPTION){
                            // user hit cancel
                        }else if (option == JOptionPane.OK_OPTION){
                            patch = spinner.getValue().toString();
                        }
                    }
                }
            
            
                ////// CHECK KUNG NAKATATLO NA SIYA NGAYONG ARAW //////
                String limit = "SELECT COUNT(date_given) as 'A' FROM med_dspnsd WHERE id_no='"+student+"'"
                            + " AND date_given ='"+nowna+"';";
                    pstmt = conn.prepareStatement(limit);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        int i = rs.getInt("A");
                        
                            if (i==3){ /////// KAPAG NAKATATLO NA SIYA
                                JOptionPane.showMessageDialog(null, "This person has already taken 3 medicine today","WARNING",JOptionPane.WARNING_MESSAGE);
                                int decide = JOptionPane.showConfirmDialog(null, "Do you want to provide a Clinical Assessment?",null,JOptionPane.YES_NO_OPTION);

                                if (decide == 0){
                                    jTabbedPane1.setSelectedIndex(2);   
                                }


                            }else{ //////KUNG HINDI PA SIYA NAKATATLO
                                int dialogResult;

                                ///// TANUNGIN KUNG MAY BEDREST O WALA

                                if (tgl_bedrest.isSelected()){ // KAPAG MAY BEDREST
//                                    String bed = " bedrest = '1', inTime = '"+hour+"'";
                                    dialogResult = JOptionPane.showConfirmDialog (null, "Dispense "+medname+ " to "+txt_fullname.getText()+" with Bedrest. Proceed?","Warning",JOptionPane.YES_NO_OPTION);

                                }else{ // ETO WALANG BEDREST

                                    dialogResult = JOptionPane.showConfirmDialog (null, "Dispense "+medname+ " to "+txt_fullname.getText()+". Proceed?","Warning",JOptionPane.YES_NO_OPTION);

                                }

                                if (dialogResult==0){
                                    if (row == -1){
                                        
                                        JOptionPane.showMessageDialog(null, "Please Select Medicine");

                                    }else{
                                        
//                                        String getCount = tbl_med.getModel().getValueAt(row, 1).toString();
                                        
                                        String bilang = tbl_med.getModel().getValueAt(row, 1).toString();
                                        ///// TIGNAN KUNG MAY BILANG /////////
                                        if (bilang.equals("0")){
                                            int diablo = JOptionPane.showConfirmDialog(null, "This medicine is out of stock, Proceed to Clinical Assessment?","Hello Assessment",JOptionPane.YES_OPTION);

                                            //// PAG WALA
                                            if (diablo==0){
                                                jTabbedPane1.setSelectedIndex(2);
                                            }

                                        //// E MAY BILANG NGA SYA //////////    
                                        }else{
                                            
                                            if (tgl_bedrest.isSelected()){
                                                
                                                ///// CHECK KUNG NAKA NASA BED SIYA //////
                                                
                                                String check = "SELECT date_given, inTime FROM med_dspnsd WHERE id_no = '"+lbl_id.getText()+"' AND outTime is null AND inTime is not null"
                                                + " AND date_given='"+nowna+"';";
                                                pstmt = conn.prepareStatement(check);
                                                rs = pstmt.executeQuery(check);
                                                
                                                ///// kung meron nga siya /////
                                                if (rs.next()){

                                                    JOptionPane.showMessageDialog(null, "This person is already in bed");
                                                }else{
                                                    
                                                    ///// bilangin ang bed na na-okupa /////
                                                    String count = "SELECT COUNT(id_no) as 'A' FROM med_dspnsd WHERE inTime is not null and outTime is null;";
                                                    pstmt = conn.prepareStatement(count);
                                                    rs = pstmt.executeQuery();
                                                    
                                                    while (rs.next()){
                                                        int get = rs.getInt("A");
                                                        if (get==4){
                                                            JOptionPane.showMessageDialog(null, "All Beds are currently occupied");
                                                        }else{
                                                            ///// CHECK KUNG PATCH BA SIYA
                                                            String amen = "";
                                                            if (cat.equals("3")){
                                                                amen = ", amount = "+patch;
                                                            }
                                                            
                                                            /////DITO KA NA MAG INSERT SA DATABASE
                                                            String query = "INSERT INTO med_dspnsd SET"
                                                            + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+medname+"'),"
                                                            + " ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+illname+"'),"
                                                            + " bedrest = '"+b_result+"',"
                                                            + " id_no = '"+lbl_id.getText()+"',"
                                                            + " date_given = '"+nowna+"',"
                                                            + " time = '"+hour+"',"
                                                            + " user_ID = '"+user_ID+"',"
                                                            + " inTime = '"+hello+"',"
                                                            + " outTime = null"
                                                            + " "+amen+"";

                                                            pstmt = conn.prepareStatement(query);
                                                            pstmt.executeUpdate();
                                                            
                                                            
                                                                                                                        
                                                            String getqnty = "SELECT * FROM med_table WHERE med_id = (SELECT med_id FROM med_table WHERE med_name = '"+medname+"');";
                                                            pstmt = conn.prepareStatement(getqnty);
                                                            rs = pstmt.executeQuery();
                                                            
                                                            if(rs.next()){
                                                                int amt = Integer.parseInt(patch);
                                                                String gago = rs.getString("med_remain");
                                                                System.out.println(gago);
                                                                int finalresult = Integer.parseInt(gago) - amt;
                                                                System.out.println(finalresult);

                                                            String addqnty = "UPDATE med_table SET" 
                                                                +" med_remain  = '"+finalresult+"'"
                                                                + " WHERE med_id = '"+id_ng_koya_mo+"';";

                                                            pstmt = conn.prepareStatement(addqnty);
                                                            pstmt.executeUpdate();
                                                            
                                                            JOptionPane.showMessageDialog(null, "Added Transaction");
                                                            
                                                            
                                                            
                                                            }
                                                            
                                                        }
                                                        
                                                    }
                                                    
                                                }
                                                
                                                
                                                
                                                
                                                

                                                    
                                            
                                            }else{
                                                String amen = "";
                                                if (cat.equals("3")){
                                                    amen = ", amount = "+patch;
                                                }
                                                String sql = "INSERT INTO med_dspnsd SET"
                                                    + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+medname+"')"
                                                    + ", ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+illname+"')"
                                                    + ", id_no='"+lbl_id.getText()+"'"
                                                    + ", date_given='"+nowna+"'"
                                                    + ", time='"+hour+"'"
                                                    + ", user_ID='"+user_ID+"'"
                                                    + ", inTime=null"
                                                    + ", outTime=null"
                                                    + " "+amen+"";
                                                
                                                pstmt = conn.prepareStatement(sql);
                                                pstmt.executeUpdate();
                                                

                                                String getqnty = "SELECT med_remain FROM med_table WHERE med_id = (SELECT med_id FROM med_table WHERE med_name = '"+medname+"');";
                                                pstmt = conn.prepareStatement(getqnty);
                                                rs = pstmt.executeQuery();
                                                while(rs.next()){
                                                    int amt = Integer.parseInt(patch);
                                                    int gago1 = rs.getInt("med_remain");
                                                    System.out.println(gago1);
                                                    int finalresult = (gago1)-amt;
                                                    System.out.println(finalresult);

                                                    String addqnty = "UPDATE med_table SET"
                                                            +" med_remain  = '"+finalresult+"'"
                                                            + " WHERE med_id = '"+id_ng_koya_mo+"';";
                                                           

                                                    pstmt = conn.prepareStatement(addqnty);
                                                    pstmt.executeUpdate();
                                                    
                                                    JOptionPane.showMessageDialog(null, "Transaction Added");

                                                }


                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                            }
                                            
                                            String xo = cmb_illname.getSelectedItem().toString();
                                            
                                            String getCat = "SELECT category_id FROM ill_table WHERE ill_name='"+xo+"';";
                                            pstmt = conn.prepareStatement(getCat);
                                            rs = pstmt.executeQuery();
                                            while (rs.next()){
                                                int cat_id = rs.getInt("category_id");
                                                if (cat_id == 3){
                                                    JOptionPane.showMessageDialog(null, "Recommended to provide Clinical Assessment");
                                                    jTabbedPane1.setSelectedIndex(2);

                                                    txt_assessment.setText(xo);
                                                }
                                            }
                                        }   
                                    }
                                }  
                            }       
                    }

            }
            
        
        
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        
       
    }//GEN-LAST:event_btn_medActionPerformed

    private void tgl_bedrestItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_bedrestItemStateChanged
        
    }//GEN-LAST:event_tgl_bedrestItemStateChanged

    private void tgl_bedrestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_bedrestActionPerformed

    }//GEN-LAST:event_tgl_bedrestActionPerformed

    private void cmb_vaccineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_vaccineItemStateChanged
        
        
        
        
        if (cmb_vaccine.getSelectedIndex()==0){
            
            cmb_dose.removeAllItems();
            cmb_dose.setEnabled(false);
            
        }else{
            cmb_dose.setEnabled(true);
            cmb_dose.removeAllItems();
                
            try {
                ///// CHECK KUNG NAKA KUHA NA NG INFLUENZA VACCINE
                
                String getflu = "SELECT influenza FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"';";
                pstmt = conn.prepareStatement(getflu);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                    
                    int xo = rs.getInt("influenza");
                    
                    if (xo == 0){
                        
                        JOptionPane.showMessageDialog(null,"Flu Vaccine First");
                        cmb_vaccine.setSelectedIndex(0);
                        
                    }else{
                    
                
                    
                
                
                
                String gethepa = "SELECT result FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"';";
                        
                
                pstmt = conn.prepareStatement(gethepa);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                    
                    String getresult = rs.getString("result");
                    
                    if (getresult==null){
                        cmb_dose.addItem("Screening");
                    }else{
                        if (getresult.contains("Non")){
                            
                            String get = "SELECT dose_no FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"';";
                            pstmt = conn.prepareStatement(get);
                            rs = pstmt.executeQuery();

                            while (rs.next()){
                    
                            int dose = rs.getInt("dose_no");

                                if (dose==0){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(1);

                                }else if (dose==1){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(2);
                                }else if (dose==2){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(3);
                                }else{
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem("Completed");
                                }
                        }
                            
                            
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "This person is reactive to the vaccine");
                            
                        }
                    }
                    
                    
                }
                

                
                
                    }
                
                
            }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        }
    }//GEN-LAST:event_cmb_vaccineItemStateChanged

    private void cmb_doseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_doseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_doseActionPerformed

    private void btn_addvaccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addvaccActionPerformed
     
        
        String combo1 = cmb_vaccine.getSelectedItem().toString();
        
        
        if (combo1.equalsIgnoreCase("Influenza")){
            try {
                String query = "SELECT * FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"';";
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();

                while (rs.next()){
                    int flu = rs.getInt("influenza");

                    if (flu!=0){

                        JOptionPane.showMessageDialog(null, "This Person has already received Influenza Vaccination");

                    }else{
                        insertFluVac();
                    }

                }


            } catch (SQLException ex) {
                Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else if (combo1.equalsIgnoreCase("Hepatitis B")){
            /////////////////// SCREENING ////////////////////////
            try{
                String checkflu = "SELECT * FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"';";
                pstmt = conn.prepareStatement(checkflu);
                rs = pstmt.executeQuery();

                while (rs.next()){
                    int flu = rs.getInt("influenza");

                    if (flu==0){

                        JOptionPane.showMessageDialog(null, "Please Provide Flu Vaccination first");

                    }else{
                        
                    
         
            String combo2 = cmb_dose.getSelectedItem().toString();
            
            
            if (combo2.equalsIgnoreCase("Screening")){
                
                ////////////// CHECK KUNG NASCREENING NA //////////////
                try {
                String gethepa = "SELECT result FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"'";
                
                pstmt = conn.prepareStatement(gethepa);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                    
                    String getResult = rs.getString("result");
                    
                    if (getResult!=null){
                        JOptionPane.showMessageDialog(null, "This person has already received screening and the result is "+getResult);
                        
                        
                    }else if (getResult==null){
                        
                        String res = (String) JOptionPane.showInputDialog(this, "What is the result of the Screening?", "Screen Result", JOptionPane.YES_OPTION, null, result, result[0]);
                        
                        //get result
                        if ((res != null)&&(res.length()>0)){
                        
                        
                        if (res.equalsIgnoreCase("Reactive")){
                            
                            
//                            String addtrans = "SELECT trans_no FROM vacc_transaction;";
//                            pstmt = conn.prepareStatement(addtrans);
//                            rs = pstmt.executeQuery();
//                            
//                            if(rs.last()==false){
//                                
//                            }

                            
                            

                            String again = "UPDATE vacc_hepa_b SET"
                                    + " result = 'Reactive',"
                                    + " status = 'Completed'"
                                    + " WHERE id_no = '"+lbl_id.getText()+"'";

                            pstmt = conn.prepareStatement(again);
                            pstmt.executeUpdate();
                            
                            String kamon = "INSERT INTO med_dspnsd SET"
                                    + " id_no = '"+lbl_id.getText()+"',"
                                    + " med_id = '710',"
                                    + " ill_id = '806',"
                                    + " date_given = '"+nowna+"',"
                                    + " time = '"+hour+"',"
                                    + " user_ID = '"+user_ID+"'";
 
                            
                            pstmt = conn.prepareStatement(kamon);
                            pstmt.executeUpdate();
                            
                          
                            
                            
                            
                            JOptionPane.showMessageDialog(null, "Added Transaction");

                        }else if (res.equals("Non Reactive")){
                            
                            
                            
                            String nanaman = "update vacc_hepa_b set"
                                    + " result = 'Non Reactive'"
                                    + " WHERE id_no = '"+lbl_id.getText()+"'";
                            pstmt = conn.prepareStatement(nanaman);
                            pstmt.executeUpdate();
                            
                            String kamon = "INSERT INTO med_dspnsd SET"
                                    + " id_no = '"+lbl_id.getText()+"',"
                                    + " med_id = '710',"
                                    + " ill_id = '806',"
                                    + " date_given = '"+nowna+"',"
                                    + " time = '"+hour+"',"
                                    + " user_ID = '"+user_ID+"'";
 
                            
                            pstmt = conn.prepareStatement(kamon);
                            pstmt.executeUpdate();
                            
                            JOptionPane.showMessageDialog(null, "Added Transaction");
                            jPanel3.setVisible(false);
                            jPanel3.setVisible(true);
                            cmb_vaccine.setSelectedIndex(0);
                            
                        
                        
                        
                            }
                        }
   
                        
                    }
  
                    
                }

                } catch (SQLException ex) {
                    Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }else if (combo2.equalsIgnoreCase("1") || combo2.equalsIgnoreCase("2") || combo2.equalsIgnoreCase("3")){
                
                try {
                    String getresult = "SELECT * FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"'";
                    pstmt = conn.prepareStatement(getresult);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()){
                        
                        String anoba = rs.getString("result");
                       
                        
                        if (anoba.equalsIgnoreCase("Reactive")){
                            
                            JOptionPane.showMessageDialog(null, "This person is reactive to the vaccine");
                        
                        }else{
                            
                            int p = 0; // lalagyan
                            String hi = "SELECT med_remain FROM med_table WHERE med_name = 'Hepatitis B Vaccine'";
                            pstmt = conn.prepareStatement(hi);
                            rs = pstmt.executeQuery();
                            if (rs.next()){
                                int a = rs.getInt("med_remain"); // get remaining
                                if (a<=0){
                                    JOptionPane.showMessageDialog(null, "There is no HEPA B Vaccine Left");
                                }else{
                                    p = a - 1;
                                    // update med_remain
                                    String walk = "UPDATE med_table SET"
                                            + " med_remain = '"+p+"'"
                                            + " WHERE med_name = 'Hepatitis B Vaccine'";
                                    pstmt = conn.prepareStatement(walk);
                                    pstmt.executeUpdate();
                                    
                                    String i = cmb_dose.getSelectedItem().toString();
                            
                                   String sql = "UPDATE vacc_hepa_b SET"
                                       + " dose_no = '"+i+"'"
            //                           + " date_given = '"+nowna+"',"
            //                           + " given_by = '101'"
                                       + " WHERE id_no = '"+lbl_id.getText()+"'";  

                                   if (i.equals("3")){
                                       String qqq = "UPDATE vacc_hepa_b SET"
                                               + " status = 'Completed'"
                                               + " WHERE id_no = '"+lbl_id.getText()+"'";
                                       pstmt = conn.prepareStatement(qqq);
                                       pstmt.executeUpdate();
                                   }

                                   pstmt = conn.prepareStatement(sql);
                                   pstmt.executeUpdate();
                           
                           

                           
                                    String ulitulit = "INSERT INTO med_dspnsd SET"
                                            + " id_no = '"+lbl_id.getText()+"',"
                                            + " med_id = '709',"
                                            + " ill_id = '806',"
                                            + " date_given = '"+nowna+"',"
                                            + " time = '"+hour+"',"
                                            + " user_ID = '"+user_ID+"'";
 
                            
                                    pstmt = conn.prepareStatement(ulitulit);
                                    pstmt.executeUpdate();

                                    

                                    JOptionPane.showMessageDialog(null,"Added");
                                    jPanel3.setVisible(false);
                                    jPanel3.setVisible(true);
                                    
                                    
                                }
                            }
                           
                           
                            
                            }
                        }
                        
                        
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
                }
   
                    
            }else{
                JOptionPane.showMessageDialog(null, "This person had already completed the vaccine");
            }
                
            ///dito 
            }

                }
                
                
            }catch(Exception e){
                e.printStackTrace();
            }
                
                
        }
            
            
       
        
        
        
        
    }//GEN-LAST:event_btn_addvaccActionPerformed

    private void btn_print_assessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_print_assessActionPerformed
        if (txt_assessment.getText().equals("") || txt_recommend.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please provide Assessment and Recommendation");

        }else{
            
            try{
                
                String bakanasalabasna = "SELECT id_no, assdate FROM clinic_assess WHERE id_no='"+lbl_id.getText()+"' AND assdate = '"+nowna+"'";
                pstmt = conn.prepareStatement(bakanasalabasna);
                rs = pstmt.executeQuery();
                
                if (rs.next()){
                    
                    JOptionPane.showMessageDialog(null, "This person had already received a clinical assessment");
                        
                }else{
                        
                        try{
                            String nurseName = Firstname +" "+ Lastname;
                            String sql = "INSERT INTO clinic_assess SET ass_num= '"+lbl_assess.getText()+"', id_no = '"+lbl_id.getText()+"'"
                                    + ", assessment = '"+txt_assessment.getText()+"', recommendation = '"+txt_recommend.getText()+"', assdate = '"+nowna+"', isCreated = '"+user_ID+"';";
                            pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();



                            int dialogresult = JOptionPane.showConfirmDialog(rootPane, "Re-admission Completed, Click Yes to Print", "Hello Print", JOptionPane.YES_OPTION);;
                            if (dialogresult == 0){
                                JasperDesign jd=JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\assessmentslip.jrxml");
                                //String ci = "SELECT * FROM readmission WHERE stud_no = '"+stud_no.getText()+"';";
                                String ci = "SELECT * FROM clinic_assess INNER JOIN patient_tbl ON"
                                        + " clinic_assess.id_no = patient_tbl.id_no"
                                        + " INNER JOIN guardian_table ON clinic_assess.id_no = guardian_table.id_no"
                                        + " WHERE clinic_assess.ass_num = '"+lbl_assess.getText()+"';";

                                JRDesignQuery newQuery = new JRDesignQuery();
                                newQuery.setText(ci);

                                jd.setQuery(newQuery);
                                JasperReport jr = JasperCompileManager.compileReport(jd);
                                Map<String, Object> params = new HashMap<String, Object>();
                                params.put("nurseName", nurseName);

                                

                                JasperPrint jp=JasperFillManager.fillReport(jr, params,conn);
                                JasperPrintManager.printReport(jp, false);

                                JasperExportManager.exportReportToPdfFile(jp,"report.pdf");

                                if (Desktop.isDesktopSupported()) {
                                    try {
                                        File myFile = new File("report.pdf");
                                        Desktop.getDesktop().open(myFile);
                                    } catch (IOException ex) {
                                    }
                                }
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }    
                }
      
            }catch(SQLException ex){
                Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_print_assessActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        startup();
        showAllPass();
        showFam();
    }//GEN-LAST:event_formWindowActivated

    private void tgl_bedrestMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bedrestMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_bedrestMousePressed

    private void tgl_bedrestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bedrestMouseClicked
         
    }//GEN-LAST:event_tgl_bedrestMouseClicked
    public static double round1(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        //String nurseName = Firstname +" "+ Lastname;
        
        try {
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\medicalrecords.jrxml");

            String ci = "SELECT * FROM patient_tbl " +
            "INNER JOIN father_table ON patient_tbl.id_no = father_table.id_no " +
            "INNER JOIN mother_table ON patient_tbl.id_no = mother_table.id_no " +
            "INNER JOIN spouse_table ON patient_tbl.id_no = spouse_table.id_no " +
            "INNER JOIN guardian_table ON patient_tbl.id_no = guardian_table.id_no " +
            "INNER JOIN photo ON patient_tbl.id_no = photo.id_no " +
            "WHERE patient_tbl.id_no = '"+lbl_id.getText()+"';";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(ci);

            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
           

            JasperPrint jp=JasperFillManager.fillReport(jr, null,conn);
            JasperPrintManager.printReport(jp, false);

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
       //print2nd();

    }//GEN-LAST:event_jButton4ActionPerformed
    
    public void print2nd(){
        
        
        String pasMed = txt_pastmed.getText();
        String pasInju = txt_injuries.getText();
        String passOpe = txt_operations.getText();
        String passHos = txt_hospital.getText();
        
        String bloodType = txt_typ.getText();
        String bloddPress1 = txt_sys.getText();
        String bloddPress2 = txt_pres.getText();
        String respi = txt_res.getText();
        String hart = txt_hart.getText();
        String hayt = txt_hayt.getText();
        String weyt = txt_weyt.getText();
        String mass = txt_mass.getText();
        
        String P_name = txt_fullname.getText();
        String yir = txt_progyear.getText();
        
        
        String presentCol1;
        String presentCol2;
        String presentCol3;
        String famhis="";
        
        
        
        DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();
        int x = mod.getRowCount();
        System.out.println(x);
        if (x == 0){
            presentCol1 = "";
            presentCol2 = "";
            presentCol3 = "";
        }else{
            presentCol1 = jTable2.getValueAt(0, 0).toString();
            presentCol2 = jTable2.getValueAt(0, 1).toString();
            presentCol3 = jTable2.getValueAt(0, 2).toString();
        }
        
        
        
        DefaultTableModel mod1 = (DefaultTableModel) tbl_fam.getModel();
           
        int x1 = mod1.getRowCount();
        System.out.println(x1);
        if (x1 == 0){
            famhis = "";
        }else{
            for (int i = 0; i<tbl_fam.getRowCount();i++){
                String getValues = tbl_fam.getValueAt(i,0).toString()+" : "+tbl_fam.getValueAt(i,1).toString();
                famhis = famhis+", "+getValues;
                
            }
        }
        
        
        String nurseName = Firstname+" "+Lastname;
        
        String aller = txt_allergy.getText();
        
        String assess = txt_assess.getText();
        String rema = txt_rec.getText();
        
        //System.out.println(presentCol1+presentCol2+presentCol3);
        
        
//        String smoke = txt_smoker.getText()+" : "+txt_smoke.getText();
//        String alco = txt_alcohol.getText();
//        String drug = txt_drugs.getText();
//        String sex = txt_sex.getText();
//        
//        String dmens = date_mens.getDate().toString();
//        String inter = txt_interval.getText();
//        String dura = txt_duration.getText();
//        String dysm = txt_dysm.getText();
        
        try {
            JasperDesign jd = JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\medRec2.jrxml");

//            String ci = "SELECT * FROM fam_history " +
//            "WHERE id_no = '"+lbl_id.getText()+"';";

//            JRDesignQuery newQuery = new JRDesignQuery();
//            newQuery.setText(ci);
//
//            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            Map<String, Object> params = new HashMap<String, Object>();
            //params.put("nurseName", nurseName);
            params.put("pasMed", pasMed);
            params.put("pasInju", pasInju);
            params.put("passOpe", passOpe);
            params.put("passHos", passHos);
            
            params.put("bloodType", bloodType);
            params.put("bloddPress1", bloddPress1);
            params.put("bloddPress2", bloddPress2);
            params.put("respi", respi);
            params.put("hart", hart);
            params.put("hayt", hayt);
            params.put("weyt", weyt);
            params.put("mass", mass);
            
            params.put("P_name", P_name);
            params.put("yir", yir);
            
            params.put("presentCol1", presentCol1);
            params.put("presentCol2", presentCol2);
            params.put("presentCol3", presentCol3);
            
            params.put("aller", aller);
            
            
            params.put("assess", assess);
            params.put("rema", rema);
            params.put("nurseName", nurseName);
            
            params.put("famhis", famhis);
            
            
//            params.put("smoke", smoke);
//            params.put("alco", alco);
//            params.put("drug", drug);
//            params.put("sex", sex);
//            params.put("dmens", dmens);
//            params.put("inter", inter);
//            params.put("dura", dura);
//            params.put("dysm", dysm);
            
            JasperPrint jp=JasperFillManager.fillReport(jr, params,conn);
            JasperPrintManager.printReport(jp, false);

            JasperExportManager.exportReportToPdfFile(jp,"medRec2.pdf");

            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("medRec2.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new Home().setVisible(true);
//       print2nd();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        new all_transaction().setVisible(true);
       
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void cmb_heightin1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_heightin1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_heightin1ItemStateChanged

    private void txt_weight1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_weight1FocusGained
        txt_weight1.selectAll();
    }//GEN-LAST:event_txt_weight1FocusGained

    private void txt_weight1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_weight1FocusLost

    }//GEN-LAST:event_txt_weight1FocusLost

    private void txt_weight1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_weight1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_weight1KeyTyped

    private void cmb_height1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_height1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_height1ItemStateChanged

    private void txt_hr1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hr1FocusGained
        txt_hr1.selectAll();
    }//GEN-LAST:event_txt_hr1FocusGained

    private void txt_hr1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hr1FocusLost

    }//GEN-LAST:event_txt_hr1FocusLost

    private void txt_hr1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hr1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_hr1KeyTyped

    private void txt_rr1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rr1FocusGained
        txt_rr1.selectAll();
    }//GEN-LAST:event_txt_rr1FocusGained

    private void txt_rr1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_rr1FocusLost

    }//GEN-LAST:event_txt_rr1FocusLost

    private void txt_rr1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rr1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_rr1KeyTyped

    private void cmb_typeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_typeItemStateChanged

    }//GEN-LAST:event_cmb_typeItemStateChanged

    private void txt_systolic1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_systolic1FocusGained
        txt_systolic1.selectAll();
    }//GEN-LAST:event_txt_systolic1FocusGained

    private void txt_systolic1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_systolic1FocusLost

    }//GEN-LAST:event_txt_systolic1FocusLost

    private void txt_systolic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_systolic1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_systolic1ActionPerformed

    private void txt_systolic1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolic1KeyPressed

    }//GEN-LAST:event_txt_systolic1KeyPressed

    private void txt_systolic1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_systolic1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_txt_systolic1KeyTyped

    private void txt_diastolic1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diastolic1FocusGained
        txt_diastolic1.selectAll();
    }//GEN-LAST:event_txt_diastolic1FocusGained

    private void txt_diastolic1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_diastolic1FocusLost

    }//GEN-LAST:event_txt_diastolic1FocusLost

    private void txt_diastolic1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diastolic1KeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_diastolic1KeyTyped

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try{
            String assessment = "SELECT * FROM assessment_tbl WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(assessment);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String bmi = rs.getString("bmi");
                String ft = rs.getString("height");
                String in = rs.getString("height_in");
                String bp = rs.getString("bp_result");
                String sys = rs.getString("systolic");
                String dia = rs.getString("diastolic");
                String we = rs.getString("weight");
                
                String assess = rs.getString("assessment");
                String rr = rs.getString("rr_result");
                String rec = rs.getString("recommendation");
                String resrate = rs.getString("rr");
                String hartrate = rs.getString("hr");
                String type = rs.getString("bloodtype");
                String harty = rs.getString("hr_result");
                String deyt = rs.getString("assdate");
                String point = rs.getString("bmi_point");
                txt_remarks.setText(rec);
                txt_rr1.setText(resrate);
                txt_hr1.setText(hartrate);
                txt_rec.setText(rec);
                cmb_type.setSelectedItem(type);
                lbl_bp.setText(bp);
                lbl_respi.setText(rr);
                lbl_heart.setText(harty);
                lbl_body.setText(point);
                lbl_bodyy.setText(bmi);
                cmb_height1.setSelectedItem(ft);
                cmb_heightin1.setSelectedItem(in);
                
                txt_weight1.setText(we);
                
                cmb_assess.setSelectedItem(assess);
                
                txt_systolic1.setText(sys);
                
                txt_diastolic1.setText(dia);
                
                
                txt_bigat.setText(we);
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        CardLayout c = (CardLayout)Card_Vital.getLayout();
        c.show(Card_Vital,"card5");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CardLayout c = (CardLayout)Card_Vital.getLayout();
        c.show(Card_Vital,"card3");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int x = Integer.parseInt(txt_systolic1.getText());
        int y = Integer.parseInt(txt_diastolic1.getText());
        
        if (x<y){
            JOptionPane.showMessageDialog(null, "Systolic must be greater than Diastolic");
            txt_systolic1.setText("0");
            txt_diastolic1.setText("0");
            lbl_bp.setText("");
            
        }
        
        
        if (x<=89 && y<=59){
            lbl_bp.setText("Low Blood Pressure");
            
        }else if ((x>=90 && x<=119) && (y>=59 && y<=79)){
            lbl_bp.setText("Normal");
            
        }else if ((x>=120 && x<=139) || (y>=80 && y<=89)){   
            lbl_bp.setText("Prehypertension");  
            
        }else if ((x>=140 && x<=159) || (y>=90 && y<=99)){
            lbl_bp.setText("High Blood Pressure Stage 1");
            
        }else if ((x>=160 && x<180) || (y>=100 && y<=110)){
            lbl_bp.setText("High Blood Pressure Stage 2");
            
        }else{
            lbl_bp.setText("Hypertensive Crisis");
            
        }
        
        
        int get_rr = Integer.parseInt(txt_rr1.getText());
        
        if (get_rr>=12 && get_rr<=25){
            lbl_respi.setText("Normal");
            
        }else{
            lbl_respi.setText("Abnormal");
            
        }
        
        int get_hr = Integer.parseInt(txt_hr1.getText());
        
        if (get_hr>100){
            lbl_heart.setText("High Heart Rate");
            
        }else if (get_hr<60){
            
            lbl_heart.setText("Low Heart Rate");
            
        }else{
            lbl_heart.setText("Normal");
            
        }
        
        int xyz = cmb_height1.getSelectedIndex()+3;
        int abc = cmb_heightin1.getSelectedIndex()+1;
        double z = Integer.parseInt(txt_weight1.getText());
        
        ////// convert them shitness //////
        double ft_meter = xyz*0.3048;
        double inch_meter = abc*0.0254;
        double getmeter = ft_meter + inch_meter;
        
        ///// calculate bmi //////
        double get_bmi = z/(getmeter+getmeter); 
        double ya = round1(get_bmi,2);
        
        String point = String.valueOf(ya);
                
        
        
        if (get_bmi <= 18.4){
            lbl_bodyy.setText("Underweight");
            lbl_body.setText(point);
            
        }else if (get_bmi >= 18.5 && get_bmi <= 24.9){
            lbl_bodyy.setText("Normal Weight");
            lbl_body.setText(point);
            
        }else if (get_bmi >=25 && get_bmi <= 29.9){
            lbl_bodyy.setText("Overweight");
            lbl_body.setText(point);
            
        }else{
            lbl_bodyy.setText("Obese");
            lbl_body.setText(point);
            
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (txt_systolic1.getText().equals("0")|| txt_systolic1.getText().isEmpty() ||
                txt_diastolic1.getText().equals("0") || txt_diastolic1.getText().isEmpty() ||
                txt_rr1.getText().equals("0")||txt_rr1.getText().isEmpty()||
                txt_hr1.getText().equals("0")||txt_hr1.getText().isEmpty()||
                txt_weight1.getText().equals("0")||txt_weight1.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please Input Valid Information");
        }else{
        
            try {
                //Update Assessment
                String sql = "UPDATE assessment_tbl SET"
                        + " bloodtype = '"+cmb_type.getSelectedItem().toString()+"',"
                        + " systolic = '"+txt_systolic1.getText()+"',"
                        + " diastolic = '"+txt_diastolic1.getText()+"',"
                        + " bp_result = '"+lbl_bp.getText()+"',"
                        + " rr = '"+txt_rr1.getText()+"',"
                        + " rr_result = '"+lbl_respi.getText()+"',"
                        + " hr = '"+txt_hr1.getText()+"',"
                        + " hr_result = '"+lbl_heart.getText()+"',"
                        + " height = '"+cmb_height1.getSelectedItem().toString()+"',"
                        + " height_in = '"+cmb_heightin1.getSelectedItem().toString()+"',"
                        + " weight = '"+txt_weight1.getText()+"',"
                        + " bmi_point = '"+lbl_body.getText()+"',"
                        + " bmi = '"+lbl_bodyy.getText()+"',"
                        + " assessment = '"+cmb_assess.getSelectedItem().toString()+"',"
                        + " recommendation = '"+txt_remarks.getText()+"',"
                        + " assdate = '"+nowna+"'"
                        + " WHERE id_no = '"+lbl_id.getText()+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Successfully Edited");
                this.setVisible(false);
                this.setVisible(true);
                CardLayout c = (CardLayout)Card_Vital.getLayout();
                c.show(Card_Vital,"card3");
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txt_remarksKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_remarksKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            Character.isDigit(c) ||    
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Valid Characters", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
        uppercase(txt_remarks);
    }//GEN-LAST:event_txt_remarksKeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.dispose();
        Edit n = new Edit();
        n.txt_id.setText(lbl_id.getText());
        n.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try{
            String past_tense = "SELECT * FROM med_history WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(past_tense);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String illness = rs.getString("past_illness");
                if (illness.isEmpty()){
                    illness = "   -   ";
                }
                String injuries = rs.getString("injuries");
                if (injuries.isEmpty()){
                    injuries = "   -   ";
                }
                String operations = rs.getString("operations");
                if (operations.isEmpty()){
                    operations = "   -   ";
                }
                String hospital = rs.getString("hospital");
                if (hospital.isEmpty()){
                    hospital = "   -   ";
                }
                txt_past.setText(illness);
                txt_inj.setText(injuries);
                txt_ope.setText(operations);
                txt_hos.setText(hospital);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        CardLayout c1 = (CardLayout)Card_Past.getLayout();
        c1.show(Card_Past,"card3");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try{
            String query = "UPDATE med_history SET"
                    + " past_illness = '"+txt_past.getText()+"',"
                    + " injuries = '"+txt_inj.getText()+"',"
                    + " operations = '"+txt_ope.getText()+"',"
                    + " hospital = '"+txt_hos.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Updated!");
            this.setVisible(false);
            this.setVisible(true);
            jTabbedPane1.setSelectedIndex(4);
            CardLayout c1 = (CardLayout)Card_Past.getLayout();
            c1.show(Card_Past,"card11");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        CardLayout c1 = (CardLayout)Card_Past.getLayout();
        c1.show(Card_Past,"card11");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void txt_pastKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_pastKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
                Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Input Valid Characters", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_past);
    }//GEN-LAST:event_txt_pastKeyTyped

    private void txt_injKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_injKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
                Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Input Valid Characters", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_inj);
    }//GEN-LAST:event_txt_injKeyTyped

    private void txt_opeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_opeKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
                Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Input Valid Characters", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_ope);
    }//GEN-LAST:event_txt_opeKeyTyped

    private void txt_hosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hosKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
                Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Input Valid Characters", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_hos);
    }//GEN-LAST:event_txt_hosKeyTyped

    private void txt_pastFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_pastFocusGained
        txt_past.selectAll();
    }//GEN-LAST:event_txt_pastFocusGained

    private void txt_injFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_injFocusGained
        txt_inj.selectAll();
    }//GEN-LAST:event_txt_injFocusGained

    private void txt_opeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_opeFocusGained
        txt_ope.selectAll();
    }//GEN-LAST:event_txt_opeFocusGained

    private void txt_hosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_hosFocusGained
        txt_hos.selectAll();
    }//GEN-LAST:event_txt_hosFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (reason_txt.getText().equals("") || datefrom.getDate() == null || dateto.getDate() == null || daysnumber.equals("") || (datefrom.getDate().after(dateto.getDate()) || dateto.getDate().before(datefrom.getDate()))){
        
            JOptionPane.showMessageDialog(null, "Please Input Valid Information");
            
        }else{
            int dialogresult = JOptionPane.showConfirmDialog(rootPane, "Re-admission Completed, Click Yes to Print", "Hello Print", JOptionPane.YES_OPTION);;
            if (dialogresult == 0){
                insertReadmission();
                try {
                    iprintReadmission();
                } catch (JRException ex) {
                    Logger.getLogger(Medication.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        new NewJFrame().setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel7ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel7ComponentShown
//        try{
//            // show allergies
//            DefaultTableModel mod = (DefaultTableModel)jTable1.getModel();
//            mod.setRowCount(0);
//            String query = "SELECT * FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"'";
//            pstmt = conn.prepareStatement(query);
//            rs = pstmt.executeQuery();
//            while (rs.next()){
//                String type = rs.getString("allergy_type");
//                String cause = rs.getString("allergy_cause");
//                mod.addRow(new Object[]{type, cause});
//            }
//            //Show medical problem
//            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
//            model.setRowCount(0);
//            String que = "SELECT * FROM med_problem WHERE id_no = '"+lbl_id.getText()+"'";
//            pstmt = conn.prepareStatement(que);
//            rs = pstmt.executeQuery();
//            while (rs.next()){
//                String act = rs.getString("active_ill");
//                String la = rs.getString("lastattack");
//                String ma = rs.getString("maintenance");
//                model.addRow(new Object[]{act,la,ma});
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_jPanel7ComponentShown
    
    public void showAllPass(){
         try{
            // show allergies
            DefaultTableModel mod = (DefaultTableModel)jTable1.getModel();
            mod.setRowCount(0);
            String query = "SELECT * FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String type = rs.getString("allergy_type");
                String cause = rs.getString("allergy_cause");
                mod.addRow(new Object[]{type, cause});
            }
            //Show medical problem
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.setRowCount(0);
            String que = "SELECT * FROM med_problem WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(que);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String act = rs.getString("active_ill");
                String la = rs.getString("lastattack");
                String ma = rs.getString("maintenance");
                model.addRow(new Object[]{act,la,ma});
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new add_allergy().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row==-1){
            JOptionPane.showMessageDialog(null, "Please select an allergy to delete");
        }else{
            String tayp = jTable1.getValueAt(row, 0).toString();
            String cause = jTable1.getValueAt(row, 1).toString();
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this allergy?", "Warning!", JOptionPane.YES_NO_OPTION);
            if (dialogresult==0){
                try {
                    String sql = "DELETE FROM allergy_tbl WHERE id_no = '"+lbl_id.getText()+"' AND allergy_type = '"+tayp+"' AND allergy_cause = '"+cause+"'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted");
                    jPanel7.setVisible(false);
                    jPanel7.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        int row = jTable2.getSelectedRow();
        if (row==-1){
            JOptionPane.showMessageDialog(null, "Please select a problem to delete");
        }else{
            String a = jTable2.getValueAt(row, 0).toString();
            String b = jTable2.getValueAt(row, 1).toString();
            String c = jTable2.getValueAt(row, 2).toString();
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this problem?", "Warning!", JOptionPane.YES_NO_OPTION);
            if (dialogresult==0){
                try {
                    String sql = "DELETE FROM med_problem WHERE id_no = '"+lbl_id.getText()+"' AND active_ill = '"+a+"' AND lastattack = '"+b+"' AND maintenance = '"+c+"'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Deleted");
                    jPanel7.setVisible(false);
                    jPanel7.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new add_med().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jPanel3ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel3ComponentShown
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_vacc.getModel();
            mod.setRowCount(0);

            ///FLU
            String flu = "SELECT * FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(flu);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String y = rs.getString("date_given");
                String z = "Flu";

                mod.addRow(new Object[]{z,y});

            }


            ////// VACCINATION RECORDS

            String gethepa = "SELECT * FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"';";
            pstmt = conn.prepareStatement(gethepa);
            rs = pstmt.executeQuery();

            while (rs.next()){

                String resulta = rs.getString("result"); ///Screening result
                String statuses = rs.getString("status");
                lbl_status.setText(statuses);
                String x = "Screening";
                mod.addRow(new Object[]{x,resulta});
            }

            String petsa = "SELECT date_given FROM med_dspnsd WHERE id_no = '"+lbl_id.getText()+"' AND med_id = '710'";

            pstmt = conn.prepareStatement(petsa);
            rs = pstmt.executeQuery();

            while (rs.next()){


                String date = rs.getString("date_given");
                String blank = "";

                mod.addRow(new Object[]{blank,date});


            }




            //// HEPA B DOSAGES
            String dosages = "SELECT * FROM med_dspnsd WHERE id_no = '"+lbl_id.getText()+"' AND med_id = '709' ORDER BY date_given ASC;";

            pstmt = conn.prepareStatement(dosages);
            rs = pstmt.executeQuery();
            int i = 1;
            while (rs.next()){
                String date = rs.getString("date_given");
                mod.addRow(new Object[]{i,date});
                i++;
            }
            
            if (cmb_vaccine.getSelectedIndex()==0){
            
            cmb_dose.removeAllItems();
            cmb_dose.setEnabled(false);
            
        }else{
            cmb_dose.setEnabled(true);
            cmb_dose.removeAllItems();
                
            try {
                ///// CHECK KUNG NAKA KUHA NA NG INFLUENZA VACCINE
                
                String getflu = "SELECT influenza FROM vacc_flu WHERE id_no = '"+lbl_id.getText()+"';";
                pstmt = conn.prepareStatement(getflu);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                    
                    int xo = rs.getInt("influenza");
                    
                    if (xo == 0){
                        
                        JOptionPane.showMessageDialog(null,"Flu Vaccine First");
                        cmb_vaccine.setSelectedIndex(0);
                        
                    }else{
                    
                
                    
                
                
                
                String gethepa1 = "SELECT result FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"';";
                        
                
                pstmt = conn.prepareStatement(gethepa1);
                rs = pstmt.executeQuery();
                
                while (rs.next()){
                    
                    String getresult = rs.getString("result");
                    
                    if (getresult==null){
                        cmb_dose.addItem("Screening");
                    }else{
                        if (getresult.contains("Non")){
                            
                            String get = "SELECT dose_no FROM vacc_hepa_b WHERE id_no = '"+lbl_id.getText()+"';";
                            pstmt = conn.prepareStatement(get);
                            rs = pstmt.executeQuery();

                            while (rs.next()){
                    
                            int dose = rs.getInt("dose_no");

                                if (dose==0){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(1);

                                }else if (dose==1){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(2);
                                }else if (dose==2){
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem(3);
                                }else{
                                    cmb_dose.removeAllItems();
                                    cmb_dose.addItem("Completed");
                                }
                        }
                            
                            
                            
                        }else{
                            JOptionPane.showMessageDialog(null, "This person is reactive to the vaccine");
                            
                        }
                    }
                    
                    
                }
  
                    }
  
            }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jPanel3ComponentShown

    private void tbl_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2){
            int row = tbl_trans.getSelectedRow();
            String col0 = (tbl_trans.getModel().getValueAt(row, 0).toString());
            String col1 = (tbl_trans.getModel().getValueAt(row, 1).toString());
            String col2 = (tbl_trans.getModel().getValueAt(row, 2).toString());
            String col3 = (tbl_trans.getModel().getValueAt(row, 3).toString());
            String col4 = (tbl_trans.getModel().getValueAt(row, 4).toString());
            
            frm_sendNotif.transDate = col0;
            frm_sendNotif.transTime = col1;
            frm_sendNotif.ill = col2;
            frm_sendNotif.medGiven = col3;
            frm_sendNotif.bedRest = col4;
            frm_sendNotif.patientName = txt_fullname.getText();
            
            new frm_sendNotif().setVisible(true);
            
        }
    }//GEN-LAST:event_tbl_transMouseClicked

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        new Update_Photo().setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jPanel10ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel10ComponentShown
        try{
            
            if (txt_asl.getText().contains("Female")){
                date_mens.setEnabled(true);
                txt_interval.setEnabled(true);
                txt_duration.setEnabled(true);
                txt_dysm.setEnabled(true);
                
            }else{
                date_mens.setEnabled(false);
                txt_interval.setEnabled(false);
                txt_duration.setEnabled(false);
                txt_dysm.setEnabled(false);
                
            }
            String q = "SELECT * FROM personal_history WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String a = rs.getString("bottles");
                txt_alcohol.setText(a);
                String b = rs.getString("sticks");
                txt_smoke.setText(b);
                String c = rs.getString("type_times");
                txt_drugs.setText(c);
                String d = rs.getString("sexual");
                if (d.equals("0")){
                    txt_sex.setText("None");
                }else{
                    txt_sex.setText("Yes");
                }
                
                Date e = rs.getDate("mens_date");
                date_mens.setDate(e);
                String f = rs.getString("mens_regular");
                txt_interval.setText(f);
                String g = rs.getString("mens_duration");
                txt_duration.setText(g);
                String h = rs.getString("dysm");
                txt_dysm.setText(h);  
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jPanel10ComponentShown

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

    private void btn_save_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_save_contactActionPerformed
        try{
            
            Statement stmt = conn.createStatement();
            
            conn.setAutoCommit(false);
            String q = "UPDATE patient_tbl SET"
                    + " unit_no = '"+txt_add.getText()+"',"
                    + " house_building = '"+txt_add1.getText()+"',"
                    + " street = '"+txt_add2.getText()+"',"
                    + " brgy = '"+txt_brgy.getText()+"',"
                    + " city = '"+txt_city.getText()+"',"
                    + " contact = '"+txt_contact.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            stmt.executeUpdate(q);
            //guardian
            String w = "UPDATE guardian_table SET"
                    + " g_fname = '"+txt_gfname.getText()+"',"
                    + " g_relation = '"+lbl_relation.getText()+"',"
                    + " g_contact = '"+txt_gcontact.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            stmt.executeUpdate(w);
            //father
            String e = "UPDATE father_table SET"
                    + " f_fname = '"+txt_father.getText()+"',"
                    + " f_contact = '"+txt_father1.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            stmt.executeUpdate(e);
            //mother
            String r = "UPDATE mother_table SET"
                    + " m_fname = '"+txt_mother.getText()+"',"
                    + " m_contact = '"+txt_mother1.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            stmt.executeUpdate(r);
            
            //spouse
            String t = "UPDATE spouse_table SET"
                    + " s_fname = '"+txt_spouse.getText()+"',"
                    + " s_contact = '"+txt_spouse1.getText()+"'"
                    + " WHERE id_no = '"+lbl_id.getText()+"'";
            stmt.executeUpdate(t);

            conn.commit();
            JOptionPane.showMessageDialog(null, "Updated");
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_save_contactActionPerformed

    private void txt_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_addKeyTyped
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
        uppercase(txt_add);
    }//GEN-LAST:event_txt_addKeyTyped

    private void txt_add1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_add1KeyTyped
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
        uppercase(txt_add1);
    }//GEN-LAST:event_txt_add1KeyTyped

    private void txt_add2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_add2KeyTyped
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
        uppercase(txt_add2);
    }//GEN-LAST:event_txt_add2KeyTyped

    private void txt_brgyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_brgyKeyTyped
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

    private void txt_gfnameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gfnameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_gfname);
    }//GEN-LAST:event_txt_gfnameKeyTyped

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

    private void txt_father1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_father1KeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_father1KeyTyped

    private void txt_mother1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mother1KeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_mother1KeyTyped

    private void txt_spouse1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_spouse1KeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_spouse1KeyTyped

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

    private void jPanel16ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel16ComponentShown
        
    }//GEN-LAST:event_jPanel16ComponentShown
    
    public void showFam(){
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_fam.getModel();
            mod.setRowCount(0);
            
            String q = "SELECT * FROM fam_history WHERE id_no = '"+lbl_id.getText()+"'";
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String a = rs.getString("fam_ill");
                String b = rs.getString("fam_member");
                String c = WordUtils.capitalizeFully(b);
                
                mod.addRow(new Object[]{a,c});
            }
            
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    private void jPanel11ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel11ComponentShown
        startup();
    }//GEN-LAST:event_jPanel11ComponentShown

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jButton17.setEnabled(false);
        btn_save_contact.setEnabled(true);
        lbl_relation.setEditable(true);
        txt_father.setEditable(true);
        txt_father1.setEditable(true);
        txt_mother.setEditable(true);
        txt_mother1.setEditable(true);
        txt_add.setEditable(true);
        txt_add1.setEditable(true);
        txt_add2.setEditable(true);
        txt_contact.setEditable(true);
        txt_brgy.setEditable(true);
        txt_city.setEditable(true);
        txt_spouse.setEditable(true);
        txt_spouse1.setEditable(true);
        txt_gfname.setEditable(true);
        txt_gcontact.setEditable(true);
        
        
    }//GEN-LAST:event_jButton17ActionPerformed
    private void insertReadmission() { 
        
        String finalDocu = "";
        if(checkbox1.isSelected()){
            finalDocu = checkbox1.getText();
        }
        if(checkbox2.isSelected()){
            finalDocu = finalDocu + checkbox2.getText();
        }
        
        try{
            String query = "INSERT readminssion_table SET"
                    + " id_no = '"+lbl_id.getText()+"',"
                    + " datefrom = '"+abc.format(datefrom.getDate())+"',"
                    + " dateto = '"+abc.format(dateto.getDate())+"',"
                    + " no_days = '"+daysnumber.getValue().toString()+"',"
                    + " doc_given = '"+finalDocu+"',"
                    + " reason = '"+reason_txt.getText()+"',"
                    + " isCreated = '"+user_ID+"'";
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void iprintReadmission() throws JRException { 
        
        String nurseName = Firstname +" "+ Lastname;

        
        JasperDesign jd=JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\readmissionslip.jrxml");
        String ci = "SELECT * FROM readminssion_table"
                + " INNER JOIN patient_tbl ON readminssion_table.id_no = patient_tbl.id_no"
                + " INNER JOIN nurse_table ON readminssion_table.isCreated = nurse_table.user_ID"
                + " WHERE readminssion_table.read_id = '"+tnx_no.getText()+"';";

        JRDesignQuery newQuery = new JRDesignQuery();
        newQuery.setText(ci);
        jd.setQuery(newQuery);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("nurseName", nurseName);
        JasperPrint jp=JasperFillManager.fillReport(jr, params,conn);
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
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
      
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Medication().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Card_Past;
    private javax.swing.JPanel Card_Vital;
    private javax.swing.JPanel Edit_Past;
    private javax.swing.JPanel Edit_Vital;
    private javax.swing.JPanel Show_Past;
    private javax.swing.JPanel Show_Vital;
    private javax.swing.JLabel aaaa;
    private javax.swing.JButton btn_addvacc;
    private javax.swing.JButton btn_med;
    private javax.swing.JButton btn_print_assess;
    private javax.swing.JButton btn_save_contact;
    private javax.swing.JCheckBox checkbox1;
    private javax.swing.JCheckBox checkbox2;
    private javax.swing.JComboBox cmb_assess;
    private javax.swing.JComboBox cmb_dose;
    private javax.swing.JComboBox cmb_height1;
    private javax.swing.JComboBox cmb_heightin1;
    public javax.swing.JComboBox cmb_illname;
    private javax.swing.JComboBox cmb_type;
    private javax.swing.JComboBox cmb_vaccine;
    public com.toedter.calendar.JDateChooser date_mens;
    public com.toedter.calendar.JDateChooser datefrom;
    public com.toedter.calendar.JDateChooser dateto;
    private javax.swing.JSpinner daysnumber;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbl_assess;
    private javax.swing.JLabel lbl_blood;
    private javax.swing.JLabel lbl_body;
    private javax.swing.JLabel lbl_bodyy;
    private javax.swing.JLabel lbl_bp;
    private javax.swing.JLabel lbl_date;
    private javax.swing.JLabel lbl_heart;
    public static javax.swing.JLabel lbl_id;
    private javax.swing.JTextField lbl_relation;
    private javax.swing.JLabel lbl_relation1;
    private javax.swing.JLabel lbl_relation10;
    private javax.swing.JLabel lbl_relation11;
    private javax.swing.JLabel lbl_relation12;
    private javax.swing.JLabel lbl_relation14;
    private javax.swing.JLabel lbl_relation15;
    private javax.swing.JLabel lbl_relation2;
    private javax.swing.JLabel lbl_relation3;
    private javax.swing.JLabel lbl_relation4;
    private javax.swing.JLabel lbl_relation5;
    private javax.swing.JLabel lbl_relation6;
    private javax.swing.JLabel lbl_relation7;
    private javax.swing.JLabel lbl_relation8;
    private javax.swing.JLabel lbl_relation9;
    private javax.swing.JLabel lbl_respi;
    private javax.swing.JLabel lbl_status;
    private javax.swing.JLabel photo_bomb;
    private javax.swing.JTextArea reason_txt;
    private javax.swing.JTable tbl_fam;
    public javax.swing.JTable tbl_med;
    public static javax.swing.JTable tbl_trans;
    public static javax.swing.JTable tbl_vacc;
    private javax.swing.JToggleButton tgl_bedrest;
    private javax.swing.JTextField tnx_no;
    private javax.swing.JTextField txt_add;
    private javax.swing.JTextField txt_add1;
    private javax.swing.JTextField txt_add2;
    private javax.swing.JTextField txt_alcohol;
    private javax.swing.JTextArea txt_allergy;
    private javax.swing.JLabel txt_asl;
    private javax.swing.JLabel txt_assess;
    private javax.swing.JTextArea txt_assessment;
    private javax.swing.JLabel txt_bigat;
    private javax.swing.JLabel txt_bmi;
    private javax.swing.JLabel txt_bmi1;
    private javax.swing.JLabel txt_bp;
    private javax.swing.JTextField txt_brgy;
    private javax.swing.JLabel txt_category;
    private javax.swing.JTextField txt_city;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_diastolic1;
    private javax.swing.JTextField txt_drugs;
    private javax.swing.JTextField txt_duration;
    private javax.swing.JTextField txt_dysm;
    private javax.swing.JTextField txt_father;
    private javax.swing.JTextField txt_father1;
    public static javax.swing.JLabel txt_fullname;
    private javax.swing.JTextField txt_gcontact;
    private javax.swing.JTextField txt_gfname;
    private javax.swing.JLabel txt_hart;
    private javax.swing.JLabel txt_hayt;
    private javax.swing.JLabel txt_height;
    private javax.swing.JTextField txt_hos;
    private javax.swing.JLabel txt_hospital;
    private javax.swing.JTextField txt_hr1;
    private javax.swing.JTextField txt_inj;
    private javax.swing.JLabel txt_injuries;
    private javax.swing.JTextField txt_interval;
    private javax.swing.JLabel txt_mass;
    private javax.swing.JLabel txt_medical;
    private javax.swing.JLabel txt_medication;
    private javax.swing.JTextField txt_mother;
    private javax.swing.JTextField txt_mother1;
    private javax.swing.JTextField txt_ope;
    private javax.swing.JLabel txt_operations;
    private javax.swing.JTextField txt_past;
    private javax.swing.JLabel txt_pastmed;
    private javax.swing.JLabel txt_pres;
    public static javax.swing.JLabel txt_progyear;
    private javax.swing.JLabel txt_rec;
    private javax.swing.JTextArea txt_recommend;
    private javax.swing.JTextField txt_remarks;
    private javax.swing.JLabel txt_res;
    private javax.swing.JLabel txt_rr;
    private javax.swing.JTextField txt_rr1;
    private javax.swing.JTextField txt_sex;
    private javax.swing.JTextField txt_smoke;
    private javax.swing.JLabel txt_smoker;
    private javax.swing.JTextField txt_spouse;
    private javax.swing.JTextField txt_spouse1;
    private javax.swing.JLabel txt_sys;
    private javax.swing.JTextField txt_systolic1;
    private javax.swing.JLabel txt_typ;
    private javax.swing.JTextField txt_weight1;
    private javax.swing.JLabel txt_weyt;
    // End of variables declaration//GEN-END:variables
}
