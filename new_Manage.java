/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;


import static cms.function.CBox_function.CBox_function;
import static cms.function.CBox_function.CBox_function01;

import cms.function.SQLConnection;
import cms.function.tbl_function;
import static cms.function.tbl_function.tbl_function;
import com.sun.glass.events.KeyEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author Benjie
 */
public class new_Manage extends javax.swing.JFrame {

    
    Date max_date = new Date();
    
    
    
    
    
    public static LocalDate nowna = LocalDate.now();
    
    
    public static SimpleDateFormat abc = new SimpleDateFormat("YYYY-MM-dd");
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Connection conn = SQLConnection.ConnDB();
    
    
  
    
    
    
    /**
     * Creates new form new_Registration
     */
    public new_Manage() {
        initComponents();
        chooser_from.setMaxSelectableDate(max_date);
        chooser_to.setMaxSelectableDate(max_date);
        
        tbl_showmedicine.getTableHeader().setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
        tbl_showmedicine.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        
        
        for(int years = 2015 ; years<=Calendar.getInstance().get(Calendar.YEAR);years++){
                //years_tmp.add(years+"");
                cmb_year.addItem(years);
                
        }
        int last = cmb_year.getItemCount()-1;
        cmb_year.setSelectedIndex(last);
        tbl_function(tbl_specific);
        tbl_function(tbl_general);
        tbl_function(tbl_showmedicine);
        tbl_function(tbl_showpurpose);
        tbl_function(tbl_otherill);
   
    }
    
    
    private void refresh_report(){
        
        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();
        int i = 0;
//        String year = cmb_year.getSelectedItem().toString();
        
        
        DefaultTableModel mod = (DefaultTableModel)tbl_general.getModel();
        mod.setRowCount(0);
        cmb_specific.removeAllItems();
        
        try{
            
            String general = null;

            if (radio1.isSelected()==true){ // KUNG ANG PINILI AY MEDICINE DISPENSED


                general = "SELECT DATE_FORMAT(a.date_given, '%e') x, b.med_name y, NULLIF (COUNT(DISTINCT a.med_id),0)z"
                        + " FROM med_dspnsd a INNER JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE DATE_FORMAT(date_given,'%M') = '"+month+"' AND DATE_FORMAT(date_given, '%Y') = '"+year+"'";
                
                
                CBox_function01(cmb_specific); //show all medicines
                cmb_specific.addItem("ALL");
                cmb_specific.setSelectedItem("ALL");

            }else{ // KUNG ANG PINILI AY ILLNESSES OCCURRED
                
                general = "SELECT DATE_FORMAT(a.date_given, '%e') x, c.ill_name y, NULLIF (COUNT(DISTINCT a.ill_id),0)z"
                        + " FROM med_dspnsd a INNER JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE DATE_FORMAT(date_given,'%M') = '"+month+"' AND DATE_FORMAT(date_given, '%Y') = '"+year+"'";
                
                CBox_function(cmb_specific); //show all illnesses
                cmb_specific.addItem("ALL");
                cmb_specific.setSelectedItem("ALL");
            }
            
            pstmt = conn.prepareStatement(general);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()){
                        String date = rs.getString("x");
                        String name = rs.getString("y");
                        String count = rs.getString("z");
                        
                        
                        mod.addRow(new Object[]{date,name,count});   
                        int cnt = rs.getInt("z");
                        i = i + cnt;
                    }
                    if (i>0){
                        mod.addRow(new Object[]{"","TOTAL",i});
                    }
                    
                    
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
   
    
    private void refresh_purpose(){
    DefaultTableModel table1 = (DefaultTableModel)tbl_showpurpose.getModel();
        DefaultTableModel table2 = (DefaultTableModel)tbl_otherill.getModel();
        table1.setRowCount(0);
        table2.setRowCount(0);
        
        
        
        
        try {
           
            
            String populate = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
                    + " WHERE a.med_name = '"+lbl_medname.getText()+"'";
            
            pstmt = conn.prepareStatement(populate);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String purpose = rs.getString("ill_name");
                
                
                
                table1.addRow(new Object[]{purpose});
                
                
            }
            
            
            String sql = "SELECT ill_table.ill_name FROM ill_table"
                    + " WHERE ill_id NOT IN (SELECT ill_table.ill_id FROM ill_table"
                    + " INNER JOIN med_ill" 
                    + " ON ill_table.ill_id = med_ill.ill_id" 
                    + " WHERE med_ill.med_id = (SELECT med_table.med_id"
                    + " FROM med_table WHERE med_name='"+lbl_medname.getText()+"'))"
                    + " ORDER BY ill_table.ill_name";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
   
                String other_ill = rs.getString("ill_table.ill_name");
       
                
                table2.addRow(new Object[]{other_ill});
                
            }
            
            
            
            

            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
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

        RadioGroup = new javax.swing.ButtonGroup();
        ToggleGroup = new javax.swing.ButtonGroup();
        Navigation = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        tgl_medicine = new javax.swing.JToggleButton();
        tgl_report = new javax.swing.JToggleButton();
        Layout_Panel = new javax.swing.JPanel();
        View = new javax.swing.JPanel();
        txt_searchmed = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        cmb_avail = new javax.swing.JComboBox();
        jLabel107 = new javax.swing.JLabel();
        cmb_purpose = new javax.swing.JComboBox();
        jLabel108 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_showmedicine = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        Edit_Medicine = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbl_medname = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_showpurpose = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_otherill = new javax.swing.JTable();
        Reports = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_general = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        chooser_from = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chooser_to = new com.toedter.calendar.JDateChooser();
        cmb_specific = new javax.swing.JComboBox();
        jLabel110 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_specific = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Navigation.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator1.setBackground(new java.awt.Color(10, 61, 115));

        jLabel1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VIEW");

        ToggleGroup.add(tgl_medicine);
        tgl_medicine.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_medicine.setForeground(new java.awt.Color(255, 255, 0));
        tgl_medicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines.png"))); // NOI18N
        tgl_medicine.setSelected(true);
        tgl_medicine.setText("   Medicine");
        tgl_medicine.setBorder(null);
        tgl_medicine.setBorderPainted(false);
        tgl_medicine.setContentAreaFilled(false);
        tgl_medicine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_medicine.setFocusPainted(false);
        tgl_medicine.setFocusable(false);
        tgl_medicine.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_medicine.setIconTextGap(6);
        tgl_medicine.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_medicineItemStateChanged(evt);
            }
        });
        tgl_medicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_medicineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_medicineMouseExited(evt);
            }
        });
        tgl_medicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_medicineActionPerformed(evt);
            }
        });

        ToggleGroup.add(tgl_report);
        tgl_report.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_report.setForeground(new java.awt.Color(255, 255, 255));
        tgl_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/seo-report.png"))); // NOI18N
        tgl_report.setText("   Report");
        tgl_report.setBorder(null);
        tgl_report.setBorderPainted(false);
        tgl_report.setContentAreaFilled(false);
        tgl_report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_report.setFocusPainted(false);
        tgl_report.setFocusable(false);
        tgl_report.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_report.setIconTextGap(6);
        tgl_report.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/seo-report2.png"))); // NOI18N
        tgl_report.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/seo-report2.png"))); // NOI18N
        tgl_report.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/seo-report2.png"))); // NOI18N
        tgl_report.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_reportItemStateChanged(evt);
            }
        });
        tgl_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_reportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_reportMouseExited(evt);
            }
        });
        tgl_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_reportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(281, Short.MAX_VALUE))
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_medicine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(tgl_medicine, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_report, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(599, 599, 599))
        );

        Layout_Panel.setLayout(new java.awt.CardLayout());

        txt_searchmed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_searchmed.setForeground(new java.awt.Color(46, 47, 51));
        txt_searchmed.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchmedFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchmedFocusLost(evt);
            }
        });
        txt_searchmed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchmedActionPerformed(evt);
            }
        });
        txt_searchmed.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchmedKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchmedKeyTyped(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(145, 148, 150));
        jLabel106.setText("Medicine Name");

        cmb_avail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_avail.setForeground(new java.awt.Color(46, 47, 51));
        cmb_avail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Available" }));
        cmb_avail.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_avail.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_availItemStateChanged(evt);
            }
        });

        jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(145, 148, 150));
        jLabel107.setText("Availability");

        cmb_purpose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_purpose.setForeground(new java.awt.Color(46, 47, 51));
        cmb_purpose.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_purpose.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_purposeItemStateChanged(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(145, 148, 150));
        jLabel108.setText("Purpose");

        tbl_showmedicine.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_showmedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Name", "Count", "Purpose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_showmedicine.setRowHeight(27);
        tbl_showmedicine.setShowHorizontalLines(false);
        tbl_showmedicine.setShowVerticalLines(false);
        tbl_showmedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_showmedicineMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_showmedicine);
        if (tbl_showmedicine.getColumnModel().getColumnCount() > 0) {
            tbl_showmedicine.getColumnModel().getColumn(0).setResizable(false);
            tbl_showmedicine.getColumnModel().getColumn(1).setResizable(false);
            tbl_showmedicine.getColumnModel().getColumn(1).setHeaderValue("Count");
            tbl_showmedicine.getColumnModel().getColumn(2).setResizable(false);
            tbl_showmedicine.getColumnModel().getColumn(2).setHeaderValue("Purpose");
        }

        jLabel45.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(10, 61, 115));
        jLabel45.setText("View Medicine");

        javax.swing.GroupLayout ViewLayout = new javax.swing.GroupLayout(View);
        View.setLayout(ViewLayout);
        ViewLayout.setHorizontalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_searchmed, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmb_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_purpose, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(352, Short.MAX_VALUE))
        );
        ViewLayout.setVerticalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jLabel107)
                    .addComponent(jLabel108))
                .addGap(7, 7, 7)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchmed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_avail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_purpose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        Layout_Panel.add(View, "card2");

        Edit_Medicine.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                Edit_MedicineComponentShown(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(10, 61, 115));
        jLabel46.setText("Edit Medicine");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(145, 148, 150));
        jLabel109.setText("Medicine Name");

        jButton1.setText("Save");

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbl_medname.setFont(new java.awt.Font("Proxima Nova Alt Rg", 0, 16)); // NOI18N
        lbl_medname.setForeground(new java.awt.Color(70, 70, 70));
        lbl_medname.setText("Medicine Name");

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        tbl_showpurpose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CURRENT PURPOSES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_showpurpose.setRowHeight(27);
        tbl_showpurpose.setShowHorizontalLines(false);
        tbl_showpurpose.setShowVerticalLines(false);
        tbl_showpurpose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_showpurposeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_showpurpose);
        if (tbl_showpurpose.getColumnModel().getColumnCount() > 0) {
            tbl_showpurpose.getColumnModel().getColumn(0).setResizable(false);
        }

        tbl_otherill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OTHER ILLNESSES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_otherill.setRowHeight(27);
        tbl_otherill.setShowHorizontalLines(false);
        tbl_otherill.setShowVerticalLines(false);
        tbl_otherill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_otherillMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_otherill);
        if (tbl_otherill.getColumnModel().getColumnCount() > 0) {
            tbl_otherill.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout Edit_MedicineLayout = new javax.swing.GroupLayout(Edit_Medicine);
        Edit_Medicine.setLayout(Edit_MedicineLayout);
        Edit_MedicineLayout.setHorizontalGroup(
            Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_MedicineLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Edit_MedicineLayout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_medname, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel46)
                    .addGroup(Edit_MedicineLayout.createSequentialGroup()
                        .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(Edit_MedicineLayout.createSequentialGroup()
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(155, 155, 155))
                                .addGroup(Edit_MedicineLayout.createSequentialGroup()
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGap(71, 71, 71)))
                            .addGroup(Edit_MedicineLayout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(71, 71, 71)))
                        .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(466, Short.MAX_VALUE))
        );
        Edit_MedicineLayout.setVerticalGroup(
            Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Edit_MedicineLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(lbl_medname))
                .addGap(30, 30, 30)
                .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Edit_MedicineLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(Edit_MedicineLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addGap(50, 50, 50)
                .addGroup(Edit_MedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        Layout_Panel.add(Edit_Medicine, "card3");

        Reports.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                ReportsComponentShown(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(10, 61, 115));
        jLabel47.setText("Reports");

        RadioGroup.add(radio1);
        radio1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio1.setSelected(true);
        radio1.setText("Medicine Dispensed");

        RadioGroup.add(radio2);
        radio2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio2.setText("Illnesses Occured");

        jTabbedPane1.setForeground(new java.awt.Color(10, 61, 115));
        jTabbedPane1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        jPanel3.setFont(new java.awt.Font("Helvetica Neue", 0, 11)); // NOI18N

        cmb_month.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_month.setForeground(new java.awt.Color(40, 40, 30));
        cmb_month.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmb_month.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_monthItemStateChanged(evt);
            }
        });

        cmb_year.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_year.setForeground(new java.awt.Color(40, 40, 30));
        cmb_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_yearItemStateChanged(evt);
            }
        });

        jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(145, 148, 150));
        jLabel111.setText("Month");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(145, 148, 150));
        jLabel112.setText("Year");

        tbl_general.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_general.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "NAME", "COUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_general.setRowHeight(27);
        tbl_general.setShowHorizontalLines(false);
        tbl_general.setShowVerticalLines(false);
        jScrollPane5.setViewportView(tbl_general);
        if (tbl_general.getColumnModel().getColumnCount() > 0) {
            tbl_general.getColumnModel().getColumn(0).setResizable(false);
            tbl_general.getColumnModel().getColumn(1).setResizable(false);
            tbl_general.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_year, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_month, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(cmb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(cmb_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("GENERAL REPORT", jPanel3);

        chooser_from.setBackground(new java.awt.Color(255, 255, 255));
        chooser_from.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(70, 70, 70));
        jLabel6.setText("From");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(70, 70, 70));
        jLabel5.setText("To");

        chooser_to.setBackground(new java.awt.Color(255, 255, 255));
        chooser_to.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        cmb_specific.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_specific.setForeground(new java.awt.Color(46, 47, 51));
        cmb_specific.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_specific.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_specificItemStateChanged(evt);
            }
        });

        jLabel110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(70, 70, 70));
        jLabel110.setText("Name");

        tbl_specific.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_specific.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE", "NAME", "PURPOSE / MEDICINE", "COUNT"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_specific.setRowHeight(27);
        tbl_specific.setShowHorizontalLines(false);
        tbl_specific.setShowVerticalLines(false);
        jScrollPane4.setViewportView(tbl_specific);
        if (tbl_specific.getColumnModel().getColumnCount() > 0) {
            tbl_specific.getColumnModel().getColumn(0).setResizable(false);
            tbl_specific.getColumnModel().getColumn(1).setResizable(false);
            tbl_specific.getColumnModel().getColumn(2).setResizable(false);
            tbl_specific.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton5.setText("SHOW");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_specific, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(cmb_specific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(chooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addGap(29, 29, 29)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("SPECIFIC REPORT", jPanel4);

        javax.swing.GroupLayout ReportsLayout = new javax.swing.GroupLayout(Reports);
        Reports.setLayout(ReportsLayout);
        ReportsLayout.setHorizontalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ReportsLayout.createSequentialGroup()
                        .addComponent(radio1)
                        .addGap(39, 39, 39)
                        .addComponent(radio2))
                    .addComponent(jLabel47))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        ReportsLayout.setVerticalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio1)
                    .addComponent(radio2))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        Layout_Panel.add(Reports, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Layout_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Layout_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        try {
            DefaultTableModel mod = (DefaultTableModel)tbl_showmedicine.getModel();
            mod.setRowCount(0);
            
            String query = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id;";
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            
            while (rs.next()){
                
                String name = rs.getString("a.med_name");
                String remain = rs.getString("a.med_remain");
                String purpose = rs.getString("c.ill_name");
                
                
                mod.addRow(new Object[]{name, remain, purpose});
                
            }
            cmb_purpose.addItem("    -    ");
            
            String purpose = "SELECT * FROM ill_table;";
            pstmt = conn.prepareStatement(purpose);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String illname = rs.getString("ill_name");
                
                cmb_purpose.addItem(illname);
                
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                    
        
        
      
        
        
      
        
        
    }//GEN-LAST:event_formWindowActivated

    private void txt_searchmedFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchmedFocusGained
        txt_searchmed.selectAll();
    }//GEN-LAST:event_txt_searchmedFocusGained

    private void txt_searchmedFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchmedFocusLost

    }//GEN-LAST:event_txt_searchmedFocusLost

    private void txt_searchmedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchmedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchmedActionPerformed

    private void txt_searchmedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchmedKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE) ||
            (c==KeyEvent.VK_ENTER))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
        
        

    }//GEN-LAST:event_txt_searchmedKeyTyped

    private void cmb_availItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_availItemStateChanged
        DefaultTableModel mod = (DefaultTableModel)tbl_showmedicine.getModel();
        mod.setRowCount(0);
        
        
        try {
            
            
            
            String query = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
                    + " WHERE a.med_name like '%"+txt_searchmed.getText()+"%'";
                
                if (cmb_avail.getSelectedIndex()==0){
                    
                    
                }else{
                   query = query + "\n AND a.med_remain>0";
                }
                
                
                if (cmb_purpose.getSelectedIndex()==0){
                    
                }else{
                    query = query + "\n AND c.ill_name = '"+cmb_purpose.getSelectedItem().toString()+"'";
                }
                
                
                
                
                
            
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                
                String med_name = rs.getString("a.med_name");
                String med_remain = rs.getString("a.med_remain");
                String purpose = rs.getString("c.ill_name");
                
                Object karga [] = {med_name, med_remain, purpose};
                mod.addRow(karga);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmb_availItemStateChanged

    private void cmb_purposeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_purposeItemStateChanged
        DefaultTableModel mod = (DefaultTableModel)tbl_showmedicine.getModel();
        mod.setRowCount(0);
        
        
        try {
            
            
            
            String query = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
                    + " WHERE a.med_name like '%"+txt_searchmed.getText()+"%'";
                
                if (cmb_avail.getSelectedIndex()==0){
                    
                    
                }else{
                   query = query + "\n AND a.med_remain>0";
                }
                
                
                if (cmb_purpose.getSelectedIndex()==0){
                    
                }else{
                    query = query + "\n AND c.ill_name = '"+cmb_purpose.getSelectedItem().toString()+"'";
                }
                
                
                
                
                
            
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                
                String med_name = rs.getString("a.med_name");
                String med_remain = rs.getString("a.med_remain");
                String purpose = rs.getString("c.ill_name");
                
                Object karga [] = {med_name, med_remain, purpose};
                mod.addRow(karga);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmb_purposeItemStateChanged

    private void txt_searchmedKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchmedKeyReleased
        DefaultTableModel mod = (DefaultTableModel)tbl_showmedicine.getModel();
        mod.setRowCount(0);
        
        
        try {
            
            
            
            String query = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
                    + " WHERE a.med_name like '%"+txt_searchmed.getText()+"%'";
                
                if (cmb_avail.getSelectedIndex()==0){
                    
                    
                }else{
                   query = query + "\n AND a.med_remain>0";
                }
                
                
                if (cmb_purpose.getSelectedIndex()==0){
                    
                }else{
                    query = query + "\n AND c.ill_name = '"+cmb_purpose.getSelectedItem().toString()+"'";
                }
                
                
                
                
                
            
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                
                String med_name = rs.getString("a.med_name");
                String med_remain = rs.getString("a.med_remain");
                String purpose = rs.getString("c.ill_name");
                
                Object karga [] = {med_name, med_remain, purpose};
                mod.addRow(karga);
            }
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txt_searchmedKeyReleased

    private void Edit_MedicineComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_Edit_MedicineComponentShown
        refresh_purpose();
    }//GEN-LAST:event_Edit_MedicineComponentShown

    private void tbl_showmedicineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_showmedicineMouseClicked
        if (evt.getClickCount()==2){
            
            
             int row = tbl_showmedicine.getSelectedRow();
             
             String name = (tbl_showmedicine.getModel().getValueAt(row, 0).toString());
             
             lbl_medname.setText(name);
             
             CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
             c1.show(Layout_Panel,"card3");
        }
    }//GEN-LAST:event_tbl_showmedicineMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
             c1.show(Layout_Panel,"card2");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_showpurposeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_showpurposeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_showpurposeMouseClicked

    private void tbl_otherillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_otherillMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_otherillMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int row = tbl_showpurpose.getSelectedRow();
            
            String name = (tbl_showpurpose.getModel().getValueAt(row, 0).toString());
            
            String sql = "DELETE FROM med_ill WHERE"
                    + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+lbl_medname.getText()+"')"
                    + " AND ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+name+"');";
            
            
            
            pstmt = conn.prepareCall(sql);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        refresh_purpose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            int row = tbl_otherill.getSelectedRow();
            
            String name = (tbl_otherill.getModel().getValueAt(row, 0).toString());
            
            String sql = "INSERT INTO med_ill SET"
                    + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+lbl_medname.getText()+"')"
                    + " , ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+name+"');";
            
            
            
            pstmt = conn.prepareCall(sql);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        refresh_purpose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cmb_specificItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_specificItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_specificItemStateChanged

    private void cmb_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_yearItemStateChanged
        refresh_report();
    }//GEN-LAST:event_cmb_yearItemStateChanged

    private void ReportsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ReportsComponentShown
        refresh_report();
    }//GEN-LAST:event_ReportsComponentShown

    private void cmb_monthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_monthItemStateChanged
        refresh_report();
    }//GEN-LAST:event_cmb_monthItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String dynamic,entry,konoha = null;
        
        
        if (radio1.isSelected()==true){
            dynamic = "c.ill_name";
            entry = "b.med_name";
            konoha = "a.med_id";
        }else{
            dynamic = "b.med_name";
            entry = "c.ill_name";
            konoha = "a.ill_id";
        }
        
        
        
        if (chooser_to.getDate().toString().isEmpty() || chooser_from.getDate().toString().isEmpty()){
            JOptionPane.showMessageDialog(null,"Please Input Necessary Information", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            int i = 0;
            try {
                String query = null;
                String get_from = abc.format(chooser_from.getDate());
                String get_to = abc.format(chooser_to.getDate());
                String x = cmb_specific.getSelectedItem().toString();

                DefaultTableModel specific = (DefaultTableModel)tbl_specific.getModel();
                specific.setRowCount(0);


                if (cmb_specific.getSelectedItem().toString().equalsIgnoreCase("ALL")){

                    query = "SELECT "+dynamic+" w, DATE_FORMAT(a.date_given, '%M %e, %y') x, "+entry+" y, NULLIF (COUNT(DISTINCT "+konoha+"),0)z"
                            + " FROM med_dspnsd a INNER JOIN med_table b"
                            + " ON a.med_id = b.med_id"
                            + " INNER JOIN ill_table c"
                            + " ON a.ill_id = c.ill_id"
                            + " WHERE date_given BETWEEN '"+get_from+"' AND '"+get_to+"'";

                    pstmt = conn.prepareStatement(query);
                    rs = pstmt.executeQuery();
                    while(rs.next()){
                        String get_date = rs.getString("x");
                        String get_name = rs.getString("y");
                        String get_purpose = rs.getString("w");
                        String count = rs.getString("z");

                        specific.addRow(new Object[]{get_date, get_name, get_purpose, count});
                        int cnt = rs.getInt("z");
                        i = i + cnt;


                    }

                    if (i>0){
                        specific.addRow(new Object[]{"","TOTAL",i});
                    }

                }else{


                    query = "SELECT "+dynamic+" w, DATE_FORMAT(a.date_given, '%M %e, %y') x, "+entry+" y, NULLIF (COUNT(DISTINCT "+konoha+"),0)z"
                            + " FROM med_dspnsd a INNER JOIN med_table b"
                            + " ON a.med_id = b.med_id"
                            + " INNER JOIN ill_table c"
                            + " ON a.ill_id = c.ill_id"
                            + " WHERE date_given BETWEEN '"+get_from+"' AND '"+get_to+"'"
                            + " AND b.med_name = '"+x+"'";

                    pstmt = conn.prepareStatement(query);
                    rs = pstmt.executeQuery();
                    while(rs.next()){
                        String get_date = rs.getString("x");
                        String get_name = rs.getString("y");
                        String get_purpose = rs.getString("w");
                        String count = rs.getString("z");

                        specific.addRow(new Object[]{get_date, get_name, get_purpose, count});

                        int cnt = rs.getInt("z");
                        i = i + cnt;
                    }
                    if (i>0){
                        specific.addRow(new Object[]{"","","TOTAL",i});
                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tgl_medicineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_medicineItemStateChanged
        if (tgl_medicine.isSelected()){
            tgl_medicine.setForeground(Color.yellow);
            

        }else{
            tgl_medicine.setForeground(new Color(193,195,198));
            
        }
    }//GEN-LAST:event_tgl_medicineItemStateChanged

    private void tgl_medicineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medicineMouseEntered
        if (tgl_medicine.getForeground().equals(Color.yellow)){

        }else{
            tgl_medicine.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_medicineMouseEntered

    private void tgl_medicineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medicineMouseExited
        if (tgl_medicine.isSelected()){

        }else{
            tgl_medicine.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_medicineMouseExited

    private void tgl_medicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_medicineActionPerformed
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card2");
    }//GEN-LAST:event_tgl_medicineActionPerformed

    private void tgl_reportItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_reportItemStateChanged
        if (tgl_report.isSelected()){
            tgl_report.setForeground(Color.yellow);
            

        }else{
            tgl_report.setForeground(new Color(193,195,198));
            
        }
    }//GEN-LAST:event_tgl_reportItemStateChanged

    private void tgl_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_reportMouseEntered
        if (tgl_report.getForeground().equals(Color.yellow)){

        }else{
            tgl_report.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_reportMouseEntered

    private void tgl_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_reportMouseExited
         if (tgl_report.isSelected()){

        }else{
            tgl_report.setForeground(new Color(193,195,198));
        }
    }//GEN-LAST:event_tgl_reportMouseExited

    private void tgl_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_reportActionPerformed
        CardLayout c1 = (CardLayout)(Layout_Panel.getLayout());
        c1.show(Layout_Panel, "card4");
    }//GEN-LAST:event_tgl_reportActionPerformed
    
    
    
  
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new new_Manage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Edit_Medicine;
    private javax.swing.JPanel Layout_Panel;
    private javax.swing.JPanel Navigation;
    private javax.swing.ButtonGroup RadioGroup;
    private javax.swing.JPanel Reports;
    private javax.swing.ButtonGroup ToggleGroup;
    private javax.swing.JPanel View;
    private com.toedter.calendar.JDateChooser chooser_from;
    private com.toedter.calendar.JDateChooser chooser_to;
    private javax.swing.JComboBox cmb_avail;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_purpose;
    private javax.swing.JComboBox cmb_specific;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbl_medname;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JTable tbl_general;
    private javax.swing.JTable tbl_otherill;
    private javax.swing.JTable tbl_showmedicine;
    private javax.swing.JTable tbl_showpurpose;
    private javax.swing.JTable tbl_specific;
    private javax.swing.JToggleButton tgl_medicine;
    private javax.swing.JToggleButton tgl_report;
    private javax.swing.JTextField txt_searchmed;
    // End of variables declaration//GEN-END:variables
}
