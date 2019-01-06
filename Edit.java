package cms;

import static cms.Medication.lbl_id;
import cms.function.LimitDocumentFilter;
import cms.function.SQLConnection;
import static cms.function.Uppercase.uppercase;
import static cms.new_Registration.abc;
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JTextFieldDateEditor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import org.apache.commons.lang.WordUtils;
public class Edit extends javax.swing.JDialog {

    Connection conn = SQLConnection.ConnDB();
    PreparedStatement pstmt = null;
    Statement st = null;
    ResultSet rs = null;
   
    public Edit() {
        initComponents();
        txt_id.setText(lbl_id.getText());
        ((AbstractDocument)txt_end.getDocument()).setDocumentFilter(new LimitDocumentFilter(2));
        start();
    }
    public void start(){
        try {
            if (cmb_occupation.getSelectedIndex()==0 || cmb_occupation.getSelectedIndex()==1 ){
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
                
                String abc = "SELECT *, DATE_FORMAT(bday, '%Y') as yearngasabieh, DATE_FORMAT(bday, '%M') as b, extract(day from bday) as c FROM patient_tbl WHERE id_no = '"+txt_id.getText()+"'";
                pstmt = conn.prepareStatement(abc);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    String lname = rs.getString("lastname");
                    String last = WordUtils.capitalizeFully(lname);
                    txt_lname.setText(last);
                    String fname = rs.getString("firstname");
                    String first = WordUtils.capitalizeFully(fname);
                    txt_fname.setText(first);
                    String mname = rs.getString("middlename");
                    String mid = WordUtils.capitalizeFully(mname);
                    txt_mname.setText(mid);
                    String occ = rs.getString("occupation");
                    cmb_occupation.setSelectedItem(occ);
                    
                    String program = rs.getString("program");
                    cmb_program.setSelectedItem(program);
                    int yr = rs.getInt("year_level")-1;
                    cmb_lvl.setSelectedIndex(yr);
                    String sec = rs.getString("p_section");
                    String ini = WordUtils.initials(sec);
                    lbl_initial.setText(ini);
                    String end = sec.replaceFirst(ini,"");
                    txt_end.setText(end);
                    
                    String gen = rs.getString("gender");
                    if (gen.equalsIgnoreCase("male")){
                        radio_male.setSelected(true);
                    }else{
                        radio_female.setSelected(true);
                    }
                    
                    String type = rs.getString("student_type");
                    cmb_type.setSelectedItem(type);
                    
                    String yir = rs.getString("yearngasabieh");
                    String month = rs.getString("b");
                    String date = rs.getString("c");
                    System.out.println(yir);
                   
                    cmb_year.setSelectedItem(yir);
                    cmb_month.setSelectedItem(month);
                    cmb_days.setSelectedItem(date);
                    
                    String stat = rs.getString("status");
                    cmb_status.setSelectedItem(stat);

                }
            }else{
                
                cmb_lvl.setEnabled(false);
                cmb_lvl.removeAllItems();
                cmb_program.setEnabled(false);
                cmb_program.removeAllItems();          
            }    
        } catch (SQLException ex) {
            Logger.getLogger(Edit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txt_id = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        txt_lname = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        txt_fname = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        txt_mname = new javax.swing.JTextField();
        jLabel85 = new javax.swing.JLabel();
        radio_male = new javax.swing.JRadioButton();
        radio_female = new javax.swing.JRadioButton();
        jLabel86 = new javax.swing.JLabel();
        cmb_program = new javax.swing.JComboBox();
        jLabel87 = new javax.swing.JLabel();
        cmb_occupation = new javax.swing.JComboBox();
        jLabel88 = new javax.swing.JLabel();
        cmb_lvl = new javax.swing.JComboBox();
        jLabel89 = new javax.swing.JLabel();
        cmb_status = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel90 = new javax.swing.JLabel();
        cmb_type = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmb_month = new javax.swing.JComboBox();
        cmb_days = new javax.swing.JComboBox();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        cmb_year = new javax.swing.JComboBox();
        jLabel93 = new javax.swing.JLabel();
        txt_end = new javax.swing.JTextField();
        lbl_initial = new javax.swing.JLabel();

        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115)));

        txt_id.setEditable(false);
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

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(120, 120, 120));
        jLabel81.setText("ID Number");

        txt_lname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_lname.setForeground(new java.awt.Color(46, 47, 51));
        txt_lname.setNextFocusableComponent(txt_fname);
        txt_lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_lnameFocusGained(evt);
            }
        });
        txt_lname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_lnameKeyPressed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(120, 120, 120));
        jLabel82.setText("Last Name");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(120, 120, 120));
        jLabel83.setText("First Name");

        txt_fname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_fname.setForeground(new java.awt.Color(46, 47, 51));
        txt_fname.setNextFocusableComponent(txt_mname);
        txt_fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_fnameFocusGained(evt);
            }
        });
        txt_fname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_fnameKeyPressed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(120, 120, 120));
        jLabel84.setText("Middle Name");

        txt_mname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_mname.setForeground(new java.awt.Color(46, 47, 51));
        txt_mname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_mnameFocusGained(evt);
            }
        });
        txt_mname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mnameKeyPressed(evt);
            }
        });

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(120, 120, 120));
        jLabel85.setText("Year");

        buttonGroup1.add(radio_male);
        radio_male.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio_male.setForeground(new java.awt.Color(116, 117, 120));
        radio_male.setSelected(true);
        radio_male.setText("Male");
        radio_male.setNextFocusableComponent(radio_female);

        buttonGroup1.add(radio_female);
        radio_female.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio_female.setForeground(new java.awt.Color(116, 117, 120));
        radio_female.setText("Female");
        radio_female.setNextFocusableComponent(cmb_occupation);

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(120, 120, 120));
        jLabel86.setText("Program");

        cmb_program.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_program.setName(""); // NOI18N
        cmb_program.setNextFocusableComponent(cmb_lvl);
        cmb_program.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmb_programFocusLost(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(120, 120, 120));
        jLabel87.setText("Occupation");

        cmb_occupation.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_occupation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Student - College", "Student - Senior High", "Employee" }));
        cmb_occupation.setNextFocusableComponent(cmb_program);
        cmb_occupation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_occupationItemStateChanged(evt);
            }
        });

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(120, 120, 120));
        jLabel88.setText("Year Level");

        cmb_lvl.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_lvl.setNextFocusableComponent(cmb_type);
        cmb_lvl.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cmb_lvlFocusLost(evt);
            }
        });

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(120, 120, 120));
        jLabel89.setText("Status");

        cmb_status.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Single", "Widow/er", "Married", "Single Parent" }));
        cmb_status.setNextFocusableComponent(jButton1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton1.setText("Update");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setNextFocusableComponent(jButton2);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton2.setText("Cancel");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(120, 120, 120));
        jLabel90.setText("Student Type");

        cmb_type.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Regular", "Irregular" }));
        cmb_type.setNextFocusableComponent(cmb_status);

        jPanel2.setBackground(new java.awt.Color(10, 61, 115));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Edit Basic Info");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        cmb_month.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.setNextFocusableComponent(cmb_type);
        cmb_month.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_monthItemStateChanged(evt);
            }
        });
        cmb_month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_monthActionPerformed(evt);
            }
        });

        cmb_days.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_days.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmb_days.setNextFocusableComponent(cmb_type);

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(120, 120, 120));
        jLabel91.setText("Month");

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(120, 120, 120));
        jLabel92.setText("Day");

        cmb_year.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970" }));
        cmb_year.setNextFocusableComponent(cmb_type);
        cmb_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_yearItemStateChanged(evt);
            }
        });
        cmb_year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_yearActionPerformed(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(120, 120, 120));
        jLabel93.setText("Section");

        txt_end.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_end.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_endKeyTyped(evt);
            }
        });

        lbl_initial.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_initial.setText("1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(radio_male, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radio_female, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_id)
                    .addComponent(jLabel82, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_lname)
                    .addComponent(txt_fname)
                    .addComponent(txt_mname)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel85)
                            .addComponent(cmb_year, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_month, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel91))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_days, 0, 75, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                        .addGap(203, 203, 203))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(197, 197, 197))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmb_occupation, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_program, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmb_status, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_lvl, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(lbl_initial)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_end, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 27, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel90, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel82)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_lname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel87)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_occupation)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_program)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel84)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_mname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel88)
                                .addComponent(jLabel90)
                                .addComponent(jLabel93))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmb_lvl)
                                .addComponent(txt_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_initial))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(cmb_type, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel85)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel89)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel91)
                                .addComponent(jLabel92)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_status, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_days, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_month, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_year, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radio_female)
                    .addComponent(radio_male))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusGained

        
    }//GEN-LAST:event_txt_idFocusGained

    private void txt_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusLost

    }//GEN-LAST:event_txt_idFocusLost

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

    private void cmb_occupationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_occupationItemStateChanged
        if (cmb_occupation.getSelectedIndex()==0 || cmb_occupation.getSelectedIndex()==1 ){
            try {
                
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
            cmb_lvl.setEnabled(false);
            cmb_lvl.removeAllItems();
            cmb_program.setEnabled(false);
            cmb_program.removeAllItems();
            cmb_type.setEnabled(false);
            cmb_type.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmb_occupationItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        Medication e = new Medication();
        e.lbl_id.setText(txt_id.getText());
        e.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String gender = "Male";
            if (radio_female.isSelected()){
                gender = "Female";
            }
            String x = null;
            if (cmb_type.isEnabled()){
                x = cmb_type.getSelectedItem().toString();
            }
            String prog = null;
            if (cmb_program.isEnabled()){
                prog = cmb_program.getSelectedItem().toString();
            }
            String year = null;
            if (cmb_lvl.isEnabled()){
                year = cmb_lvl.getSelectedItem().toString();
            }
            String p = cmb_year.getSelectedItem().toString(); //year
            
            String b = String.valueOf(cmb_month.getSelectedIndex()+1); //month
            String m = cmb_days.getSelectedItem().toString(); //days
            
            String res = p+"-"+b+"-"+m;
            
            String query = "UPDATE patient_tbl SET"
                    + " lastname = '"+txt_lname.getText()+"',"
                    + " p_section = '"+lbl_initial.getText()+""+txt_end.getText()+"',"
                    + " firstname = '"+txt_fname.getText()+"',"
                    + " middlename = '"+txt_mname.getText()+"',"
                    + " program = '"+prog+"',"
                    + " year_level = '"+year+"',"
                    + " student_type = '"+x+"',"
                    + " gender = '"+gender+"',"
                    + " status = '"+cmb_status.getSelectedItem().toString()+"',"
                    + " bday = '"+res+"',"
//                    + " bday = '"+abc.format(date_birth.getDate())+"',"
                    + " occupation = '"+cmb_occupation.getSelectedItem().toString()+"'"
                    + " WHERE id_no = '"+txt_id.getText()+"'";
            pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully!");
            Medication n = new Medication();
            n.lbl_id.setText(txt_id.getText());
            this.dispose();
            n.setVisible(true);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    private void txt_lnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_lnameFocusGained
        txt_lname.selectAll();
    }//GEN-LAST:event_txt_lnameFocusGained

    private void txt_fnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_fnameFocusGained
        txt_fname.selectAll();
    }//GEN-LAST:event_txt_fnameFocusGained

    private void txt_mnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_mnameFocusGained
        txt_mname.selectAll();
    }//GEN-LAST:event_txt_mnameFocusGained

    private void cmb_monthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_monthItemStateChanged
       
    }//GEN-LAST:event_cmb_monthItemStateChanged

    private void cmb_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_yearItemStateChanged
        
    }//GEN-LAST:event_cmb_yearItemStateChanged

    private void cmb_yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_yearActionPerformed
        
    }//GEN-LAST:event_cmb_yearActionPerformed

    private void cmb_monthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_monthActionPerformed
        
    }//GEN-LAST:event_cmb_monthActionPerformed

    private void txt_lnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_lnameKeyPressed
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
    }//GEN-LAST:event_txt_lnameKeyPressed

    private void txt_fnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_fnameKeyPressed
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
    }//GEN-LAST:event_txt_fnameKeyPressed

    private void txt_mnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mnameKeyPressed
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
    }//GEN-LAST:event_txt_mnameKeyPressed

    private void cmb_lvlFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmb_lvlFocusLost
        int i = cmb_lvl.getSelectedIndex()+1;
        lbl_initial.setText(String.valueOf(i));
        
    }//GEN-LAST:event_cmb_lvlFocusLost

    private void txt_endKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_endKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_endKeyTyped


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cmb_days;
    private javax.swing.JComboBox cmb_lvl;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_occupation;
    private javax.swing.JComboBox cmb_program;
    private javax.swing.JComboBox cmb_status;
    private javax.swing.JComboBox cmb_type;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl_initial;
    private javax.swing.JRadioButton radio_female;
    private javax.swing.JRadioButton radio_male;
    private javax.swing.JTextField txt_end;
    private javax.swing.JTextField txt_fname;
    public javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_lname;
    private javax.swing.JTextField txt_mname;
    // End of variables declaration//GEN-END:variables
}
