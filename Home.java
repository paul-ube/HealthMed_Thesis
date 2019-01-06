/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms;

import cms.function.SQLConnection;
import static cms.function.UI_function.hello;
import com.sun.glass.events.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

import static cms.Medication.hour;
import static cms.edit_purposes.lbl_medpurpose;
import static cms.function.CBox_function.CBox_function;
import static cms.function.CBox_function.CBox_function01;
import cms.function.ExcelExporter;
import cms.function.LimitDocumentFilter;
import static cms.function.Loginfunction.user_ID;
import static cms.function.Loginfunction.Firstname;
import static cms.function.Loginfunction.Lastname;
import static cms.function.Loginfunction.Position;
import static cms.function.Toggle.mouse_in;
import static cms.function.Toggle.mouse_out;
import static cms.function.Uppercase.uppercase;
import static cms.function.tbl_function.tbl_function;
import static cms.new_Registration.abc;
import static cms.new_Registration.nowna;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.lang.WordUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import static org.jfree.ui.RectangleEdge.BOTTOM;
import static org.jfree.ui.RectangleEdge.RIGHT;
import org.jfree.ui.TextAnchor;
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
public class Home extends javax.swing.JFrame {
    
    Connection conn = SQLConnection.ConnDB();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Statement st = null;
    Date max_date = new Date();
    public static SimpleDateFormat abc = new SimpleDateFormat("YYYY-MM-dd");
    public static SimpleDateFormat xyz = new SimpleDateFormat("MMMMM dd, yyyy");
    public static LocalDate nowna = LocalDate.now();
    public static String select_month = "";
    
    public static byte skip = 1;
    File file;
    String line = "";
    final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    final int N = alphabet.length();
    String nurseEmail = "";
    String nursePass = "";
    String nurseUser = "";
    int count = 0;
    int amount = 0;
    String oldestPass = "";
    //int_profile profile = new int_profile();
        
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
        hello();
        
        
        tbl_function(tbl_today);
        tbl_function(tbl_bed);
        tbl_function(medicine_table);
        tbl_function(tbl_export);
        tbl_function(jTable1);
        tbl_function(update_table);
        tbl_function(userlist);
        tbl_function(tbl_monitor);
        tbl_function(program_table);
        tbl_function(tbl_inc);
        tbl_function(tbl_vaccine);
        tbl_function(tbl_generic);
        tbl_function(tbl_purposes);
        tbl_function(tbl_confine);
        
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout, "card7");
        
        ((AbstractDocument)txt_years_complete.getDocument()).setDocumentFilter(new LimitDocumentFilter(1));
        ((AbstractDocument)txt_prog.getDocument()).setDocumentFilter(new LimitDocumentFilter(10));
        ((AbstractDocument)txt_desc.getDocument()).setDocumentFilter(new LimitDocumentFilter(50));
        
        
        ((AbstractDocument)a1.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)a2.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        ((AbstractDocument)a3.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        
        ((AbstractDocument)oldpass.getDocument()).setDocumentFilter(new LimitDocumentFilter(8));
        ((AbstractDocument)pass1.getDocument()).setDocumentFilter(new LimitDocumentFilter(8));
        ((AbstractDocument)pass2.getDocument()).setDocumentFilter(new LimitDocumentFilter(8));
 
         for(int years = 2015 ; years<=Calendar.getInstance().get(Calendar.YEAR);years++){
                //years_tmp.add(years+"");
                cmb_year.addItem(years);
                cmb_years.addItem(years);
                
        }
        int last = cmb_year.getItemCount()-1;
        cmb_year.setSelectedIndex(last);
        tbl_function(tbl_specific);
        tbl_function(tbl_general);
        tbl_function(tbl_showmedicine);
//        tbl_function(tbl_showpurpose);
//        tbl_function(tbl_otherill);
        tbl_function(supplierTable);
        tbl_function(tbl_criMed);
        tbl_function(tbl_hepa);
        tbl_function(tbl_summary);
        
        chooser_from.setMaxSelectableDate(max_date);
        chooser_to.setMaxSelectableDate(max_date);
        chooser_from_confine.setMaxSelectableDate(max_date);
        chooser_to_confine.setMaxSelectableDate(max_date);
        timeShow();
        
        show_all_generic();
        refresh_purpose();
        updatePurchaseOrders();
        
        
        xyz();
        sheetness();
        lbl_icon.setText(Lastname+", "+Firstname);
        lbl_position.setText(Position);
        
//        lbl_photo.setVisible(false);
//        lbl_photo.setVisible(true);
        
        
        
 
        
    }
    
    public void showmed(){
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
            
            if (chk_active.isSelected()==true){
                query = query + "\n AND a.isActive = '1'";
            }else{
                query = query + "\n AND a.isActive = '0'";
            }
            
            query = query + "\n ORDER BY a.med_name";

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
            ex.printStackTrace();
        }
    }
    
    public void ShowPic(){
            String nullimage = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS_Image\\NOIMAGE.png"; 
       try{
            //byte[] imageBytes;
            Image image;
            
            String getphoto = "SELECT image FROM photo"
                    + " WHERE id_no = '"+user_ID+"';";
            pstmt = conn.prepareStatement(getphoto);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                byte[] imageBytes = rs.getBytes("image");
                //System.out.println(Arrays.toString(imageBytes));
                if(imageBytes == null){
                    //JOptionPane.showMessageDialog(this, "WALANG PICTURE GAGO");
                    
                    //image = getToolkit().createImage(nullimage);
                    ImageIcon icon = new ImageIcon(nullimage);
                    lbl_photo.setIcon(icon);
                }else{
                    image = getToolkit().createImage(imageBytes);
                    ImageIcon icon = new ImageIcon(image);
                    lbl_photo.setIcon(icon);
                }
                
            }
        }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        } 
    }
    
    public void sheetness(){
        try {
            cmb_track.removeAllItems();
            
            
            int x = 0;
            if (tgl_college.isSelected()){
                x = 1;
                cmb_track.setEnabled(true);
            }else if (tgl_senior.isSelected()){
                x = 0;
                cmb_track.setEnabled(true);
            }else{
                cmb_track.setEnabled(false);
            }
            
            String xyza = "SELECT * FROM course WHERE College = '"+x+"';";
            pstmt = conn.prepareStatement(xyza);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String kors = rs.getString("course_name");
                cmb_track.addItem(kors);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public final void timeShow(){
        
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMMM d");
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        String hello = sdf1.format(cal.getTime());
        String time = sdf2.format(cal.getTime());
        String date = hello.toUpperCase();
        String day = time.toUpperCase();
        txt_day.setText(day+",  "+date);
       
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        clock.setText(sdf.format(cal.getTime()));
        
        Thread timer = new Thread(){
        
        public void run(){
            for(;;){
                Calendar cal = Calendar.getInstance();
                cal.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                clock.setText(sdf.format(cal.getTime()));
                
                //dateF = new SimpleDateFormat("MMMM d, yyyy (EEEE)");
               //timeF = new SimpleDateFormat("hh:mm:ss a");
               
                try{
                   sleep(1000); 
                }catch(InterruptedException ex){
                    ex.printStackTrace();;
                }
            }
        }
        
        };
        timer.start();
    }
    
    public static void refresh_purpose(){
    
        DefaultTableModel table2 = (DefaultTableModel)tbl_purposes.getModel();
        PreparedStatement pst;
        ResultSet r;
        Connection con = SQLConnection.ConnDB();
        table2.setRowCount(0);

        
        try {
            
            
            String sql = "SELECT a.ill_name FROM ill_table a WHERE a.ill_id "
                    + "NOT IN (SELECT a.ill_name FROM ill_table a INNER JOIN med_ill b "
                    + "on a.ill_id = b.ill_id WHERE b.med_id = (SELECT a.med_id FROM med_table a "
                    + "WHERE a.med_name = '"+txt_medname.getText()+"')) ORDER BY a.ill_name;";

            pst = con.prepareStatement(sql);
            r = pst.executeQuery();
            while (r.next()){
   
                String other_ill = r.getString("a.ill_name");
                Boolean x = false;
   
                table2.addRow(new Object[]{other_ill,x}); 
            }
            

            
        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void bed(){
        DefaultTableModel mode = (DefaultTableModel)tbl_bed.getModel();
        mode.setRowCount(0);
        
        try {
            
            String f;
            
            
            String karga = "SELECT DATE_FORMAT(a.inTime, '%h:%i %p') as 'intime',a.id_no, d.lastname, d.firstname, d.middlename,"
                    + " b.med_name, c.ill_name, a.bedrest, a.user_ID FROM med_dspnsd a LEFT JOIN med_table b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON a.ill_id = c.ill_id"
                    + " INNER JOIN patient_tbl d ON a.id_no = d.id_no"
                    + " WHERE date_given = '"+nowna+"' AND a.bedrest = 1 AND a.inTime is not null AND a.outTime is null;";
            
           
            pstmt = conn.prepareStatement(karga);
            rs = pstmt.executeQuery();
            while (rs.next()){

                String time = rs.getString("intime"); /// time
                String id = rs.getString("a.id_no");  /// STUDENT NUMBER
                
                String lname = rs.getString("d.lastname");
                String fname = rs.getString("d.firstname");
                String mname = rs.getString("d.middlename");
                
                
                
                    String firstname = WordUtils.initials(fname);
                    String midname = WordUtils.initials(mname);
                    
                    String upperlast = WordUtils.capitalizeFully(lname);
                    String upperfirst = WordUtils.capitalizeFully(firstname);
                    String uppermid = WordUtils.capitalizeFully(midname);
                    
                        
                String lastnato = upperlast+", "+upperfirst+" "+uppermid+"."; 
                        
                
                
                
                
                
                String md = rs.getString("b.med_name"); ////MEDICINE NAME
                if (md==null){
                    md = " - ";
                }
                String ill = rs.getString("c.ill_name");///ILLNESS NAME
                if (ill==null){
                    ill = "N/A";
                }
                
                
                String user = rs.getString("user_ID");
                String kwe = "SELECT a.lastname,b.position  FROM patient_tbl a INNER JOIN nurse_table b ON a.id_no = b.user_id WHERE a.ID_no = '"+user+"'";
                PreparedStatement pst = conn.prepareStatement(kwe);
                ResultSet r = pst.executeQuery();
                String lah = "";
                
                if(r.next()){
                    lah = r.getString("b.position") + " "+r.getString("a.lastname");
                    
                }

                Object[] content = {id, lastnato, ill,md,time,lah};
                mode.addRow(content);           
                
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    private void refresh_report(){
        
        String month = cmb_month.getSelectedItem().toString();
        String year = cmb_year.getSelectedItem().toString();
        int i = 0;
//        String year = cmb_year.getSelectedItem().toString();
        
        
        DefaultTableModel mod = (DefaultTableModel)tbl_general.getModel();
        mod.setRowCount(0);
        
        
        try{
            
            String general = null;

            if (radio1.isSelected()==true){ // KUNG ANG PINILI AY MEDICINE DISPENSED

                JTableHeader th = tbl_general.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn tc = tcm.getColumn(1);
                TableColumn tc1 = tcm.getColumn(2);
                tc.setHeaderValue("Medicine Name");
                tc1.setHeaderValue("Purpose");
                th.repaint();
                general = "SELECT DATE_FORMAT(a.date_given, '%M %e, %Y') x, b.med_name y, c.ill_name a, NULLIF (COUNT(a.med_id),0)z, a.amount abaka "
                        + " FROM med_dspnsd a INNER JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE DATE_FORMAT(date_given,'%M') = '"+month+"' AND DATE_FORMAT(date_given, '%Y') = '"+year+"'"
                        + " GROUP BY b.med_name, a.date_given"
                        + " ORDER BY a.date_given";
                
                

            }else{ // KUNG ANG PINILI AY ILLNESSES OCCURRED
                JTableHeader th = tbl_general.getTableHeader();
                TableColumnModel tcm = th.getColumnModel();
                TableColumn tc = tcm.getColumn(1);
                TableColumn tc1 = tcm.getColumn(2);
                tc1.setHeaderValue("Medicine Given");
                tc.setHeaderValue("Illness Name");
                th.repaint();
                general = "SELECT DATE_FORMAT(a.date_given, '%M %e, %Y') x, c.ill_name y, b.med_name a, NULLIF (COUNT(a.ill_id),0)z, a.amount abaka "
                        + " FROM med_dspnsd a LEFT JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE DATE_FORMAT(date_given,'%M') = '"+month+"' AND DATE_FORMAT(date_given, '%Y') = '"+year+"'"
                        + " GROUP BY c.ill_name, a.date_given"
                        + " ORDER BY a.date_given";
                
               
            }
            
            pstmt = conn.prepareStatement(general);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()){
                        String date = rs.getString("x");
                        String purpose = rs.getString("a");
                        String name = rs.getString("y");
                        int acount = rs.getInt("z");
                        int abaka = rs.getInt("abaka");
                        
                        int po = (acount * abaka);
                        
                        mod.addRow(new Object[]{date,name, purpose,po});   
                        
                        i = i + po;
                    }
                    if (i>0){
                        mod.addRow(new Object[]{"","","TOTAL",i});
                        
                    }
                    
                    
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    private void add_list(){
        try {
            DefaultListModel mod = new DefaultListModel();
            mod.clear();
            
            
            String populate = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
                    + " WHERE a.med_name = '"+lbl_medname.getText()+"'"
                    + " ORDER BY c.ill_name";
            
            pstmt = conn.prepareStatement(populate);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String purpose = rs.getString("ill_name");
                
                mod.addElement(purpose);
                
            }
            jList1.setModel(mod);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  
    }
    
    private void genericss(){
        try {
            DefaultListModel mod = new DefaultListModel();
            mod.clear();
            
            
            String populate = "SELECT * FROM med_table a INNER JOIN pivot_medicine b ON a.med_id = b.med_id"
                    + " INNER JOIN generic c ON b.generic_id = c.generic_id"
                    + " WHERE a.med_name = '"+lbl_medname.getText()+"'"
                    + " ORDER BY c.generic_desc";
            
            pstmt = conn.prepareStatement(populate);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String purpose = rs.getString("c.generic_desc");
                
                mod.addElement(purpose);
                
            }
            jList2.setModel(mod);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
  
    }
   
    
    //INVENTORY METHOD //
    
//    public void refresh_med_table(){
//        DefaultTableModel model = (DefaultTableModel)med_table.getModel();
//        model.getDataVector().removeAllElements();
//        med_table.repaint();
//
//        try {
//            
//            String query = "SELECT * FROM med_table WHERE med_name"
//                    + " NOT IN (SELECT med_name FROM med_table WHERE med_name = 'Screening');";
//            pstmt = conn.prepareStatement(query);
//            rs = pstmt.executeQuery();
////            System.out.println("SAMPLE");
//
//            while (rs.next()){
//
//                String med_id = rs.getString("med_id");
//                String med_name = rs.getString("med_name");
//                
//                String med_quantity = rs.getString("med_remain");
//                if (med_quantity.equals(null)){
//                    med_quantity = "0";
//                }
//                String med_qty_ordered = rs.getString("med_qty_ordered");
//
//                double qnty = Double.parseDouble(med_quantity);
//                double qnty_total = Double.parseDouble(med_qty_ordered);
//                //int sample = 100;
//
//                double gago =  qnty / qnty_total ;
//                
//                int gago2 = (int) (gago * 100);
//                
//
//                model.addRow(new Object[]{med_id, med_name, med_quantity, gago2});
//                //getTable();
//            }
//            //System.out.println("SUCSESS GAGO");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        //getTable();
//
//        int row = med_table.getRowCount();
//        for (int i = 0; i!=row;i++){
//
//            //med_table.setBackground(getBackground());
//            String tbl_select = (med_table.getModel().getValueAt(i,2).toString());
//            // String type = (String)getModel().getValueAt(row, 0);
//            if ("Biogesic".equals(tbl_select)) med_table.setBackground(Color.RED);
//            //if ("Sell".equals(tbl_select)) med_table.setBackground(Color.YELLOW);
//
//        }
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//
//        //int row = med_table.getRowCount();
//
//        for (int i = 0; i!=row;i++){
//            String mename = (med_table.getModel().getValueAt(i, 1).toString());
//            String qnty = (med_table.getModel().getValueAt(i, 3).toString());
//
//            dataset.setValue(new Double(qnty), "", mename);
//
//            JFreeChart chart = ChartFactory.createLineChart("Medicine Monitoring", "Medicine Name", "REMAINING QUANTITY", dataset, PlotOrientation.VERTICAL,
//                false, true, false);
//            chart.setBackgroundPaint(Color.GRAY); // Set the background colour of the chart
//            chart.getTitle().setPaint(Color.WHITE); // Adjust the colour of the title
//            chart.setBorderPaint(Color.BLUE);
//            
//
//            CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
//            p.setBackgroundPaint(Color.WHITE); // Modify the plot background
//            p.setRangeGridlinePaint(Color.BLUE);
//            
//
//            ChartPanel ps = new ChartPanel(chart);
//            pgraph.add(ps);
//            ps.setSize(pgraph.getWidth(), pgraph.getHeight());
//
//            ps.setVisible(true);
//        }
//    }
    public void xyz(){
        
        try {
            DefaultTableModel mod = (DefaultTableModel)tbl_today.getModel();
            mod.setRowCount(0);
            
            String karga = "SELECT DATE_FORMAT(a.time, '%h:%i %p') xyz, a.id_no, d.lastname, d.firstname, d.middlename, b.med_name, c.ill_name, a.bedrest, d.program, d.occupation, a.user_ID"
                    + " FROM med_dspnsd a INNER JOIN med_table b ON a.med_id = b.med_id"
                    + " INNER JOIN ill_table c ON a.ill_id = c.ill_id"
                    + " INNER JOIN patient_tbl d ON a.id_no = d.id_no"
                    + " INNER JOIN nurse_table e ON a.user_ID = e.user_ID"
                    + " WHERE date_given = '"+nowna+"'"
                    + " ORDER BY a.time DESC;";
            pstmt = conn.prepareStatement(karga);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String time = rs.getString("xyz");
                String id = rs.getString("a.id_no"); // student number
                
                String lname = rs.getString("d.lastname");
                String fname = rs.getString("d.firstname");
                String mname = rs.getString("d.middlename");
                String prog = rs.getString("d.program");
                if (prog.equals("null")){
                    prog = " - ";
                }
                
                
                String upperlast = WordUtils.capitalizeFully(lname);
                
                String firstname = WordUtils.capitalizeFully(fname);
                String upperfirst = WordUtils.initials(firstname);
                    
                String midname = WordUtils.initials(mname);
                String uppermid = WordUtils.capitalizeFully(midname);
                     
                String lastnato = upperlast+", "+upperfirst+" "+uppermid+"."; ///// FULL NAME
                String md = rs.getString("b.med_name"); ////MEDICINE NAME
                String ill = rs.getString("c.ill_name");///ILLNESS NAME
                
                if (md.equals("null")){
                    md = "  -  ";
                }
                
                if (ill.equals("null")){
                    
                    ill = "N/A";
                }
                
                
                String br = rs.getString("a.bedrest"); 
                String bedrest = null;
                if (br.equals("0")){
                    bedrest = "";
                }else{
                    bedrest = "w/ Bedrest";
                }
                
                String combi = md+" "+bedrest; /// medication given
                String oc = rs.getString("d.occupation");
                String user = rs.getString("a.user_ID");
                String kwe = "SELECT a.lastname,b.position  FROM patient_tbl a INNER JOIN nurse_table b ON a.id_no = b.user_id WHERE a.ID_no = '"+user+"'";
                PreparedStatement pst = conn.prepareStatement(kwe);
                ResultSet r = pst.executeQuery();
                String lah = "";
                
                if(r.next()){
                    lah = r.getString("b.position") + " "+r.getString("a.lastname");
                    
                }

                
                
                
                
                mod.addRow(new Object[]{time, id, lastnato, combi, ill,oc, prog,lah});
                

                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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
        Grp_Reports = new javax.swing.ButtonGroup();
        Manage_navigation = new javax.swing.ButtonGroup();
        Inventory_navigation = new javax.swing.ButtonGroup();
        Maintenance_navigation = new javax.swing.ButtonGroup();
        jFileChooser1 = new javax.swing.JFileChooser();
        College_Senior = new javax.swing.ButtonGroup();
        Complete_Incomplete = new javax.swing.ButtonGroup();
        Profile_Navigation = new javax.swing.ButtonGroup();
        Add_new_track = new javax.swing.ButtonGroup();
        jLabel71 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tgl_home = new javax.swing.JToggleButton();
        tgl_profile = new javax.swing.JToggleButton();
        tgl_manage = new javax.swing.JToggleButton();
        tgl_inventory = new javax.swing.JToggleButton();
        tgl_maintenance = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        tgl_logout = new javax.swing.JToggleButton();
        Layout = new javax.swing.JPanel();
        Profile = new javax.swing.JPanel();
        View_Students = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        lbl_other4 = new javax.swing.JLabel();
        tgl_list = new javax.swing.JToggleButton();
        tgl_vac = new javax.swing.JToggleButton();
        tgl_inc = new javax.swing.JToggleButton();
        jButton21 = new javax.swing.JButton();
        tgl_inc1 = new javax.swing.JToggleButton();
        tgl_vac1 = new javax.swing.JToggleButton();
        profile_layout = new javax.swing.JPanel();
        Profile_Home = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        List = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        tbl_monitor = new javax.swing.JTable();
        jLabel121 = new javax.swing.JLabel();
        cmb_assess = new javax.swing.JComboBox();
        jLabel123 = new javax.swing.JLabel();
        tgl_college = new javax.swing.JRadioButton();
        tgl_senior = new javax.swing.JRadioButton();
        jLabel54 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        cmb_track = new javax.swing.JComboBox();
        Employee = new javax.swing.JRadioButton();
        jButton31 = new javax.swing.JButton();
        jLabel127 = new javax.swing.JLabel();
        txt_sort = new javax.swing.JTextField();
        VAccine = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        cmb_complete = new javax.swing.JRadioButton();
        cmb_inc = new javax.swing.JRadioButton();
        jButton20 = new javax.swing.JButton();
        hrm_tm = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        tbl_vaccine = new javax.swing.JTable();
        jButton32 = new javax.swing.JButton();
        No_ID_No_Entry = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_txt = new javax.swing.JTextField();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbl_inc = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        txtPath_rfid = new javax.swing.JTextArea();
        jButton38 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        Flu = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tbl_hepa = new javax.swing.JTable();
        cmb_kategorya = new javax.swing.JComboBox();
        cmb_prog = new javax.swing.JComboBox();
        jButton23 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        Maintenance = new javax.swing.JPanel();
        Navigation = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        tgl_med = new javax.swing.JToggleButton();
        tgl_import = new javax.swing.JToggleButton();
        tgl_fam = new javax.swing.JToggleButton();
        tgl_problem = new javax.swing.JToggleButton();
        tgl_bmi = new javax.swing.JToggleButton();
        lbl_other2 = new javax.swing.JLabel();
        tgl_man = new javax.swing.JToggleButton();
        tgl_sec = new javax.swing.JToggleButton();
        lbl_other3 = new javax.swing.JLabel();
        tgl_man1 = new javax.swing.JToggleButton();
        Maintenance_Layout = new javax.swing.JPanel();
        Maintenance_Home = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        Add_Program = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txt_courseid = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txt_prog = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_desc = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        program_table = new javax.swing.JTable();
        jLabel120 = new javax.swing.JLabel();
        txt_years_complete = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        radio_college = new javax.swing.JRadioButton();
        radio_senior = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        AddNewUser = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        userlist = new javax.swing.JTable();
        jLabel119 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        txt_user_ID = new javax.swing.JTextField();
        nurse_name = new javax.swing.JTextField();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        usertxt = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        passtxt = new javax.swing.JPasswordField();
        Import_Records = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtPath = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel116 = new javax.swing.JLabel();
        txt_import = new javax.swing.JTextField();
        cmb_surt = new javax.swing.JComboBox();
        jLabel33 = new javax.swing.JLabel();
        Export_Print_Records = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tbl_export = new javax.swing.JTable();
        jLabel117 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        program = new javax.swing.JComboBox();
        lbl_other1 = new javax.swing.JLabel();
        Update_Records = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton16 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtPath1 = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        update_table = new javax.swing.JTable();
        jLabel118 = new javax.swing.JLabel();
        Manage_Account = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jLabel100 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        q1 = new javax.swing.JComboBox();
        q2 = new javax.swing.JComboBox();
        q3 = new javax.swing.JComboBox();
        a1 = new javax.swing.JPasswordField();
        a2 = new javax.swing.JPasswordField();
        jLabel69 = new javax.swing.JLabel();
        a3 = new javax.swing.JPasswordField();
        Security_Account = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        oldpass = new javax.swing.JPasswordField();
        pass1 = new javax.swing.JPasswordField();
        pass2 = new javax.swing.JPasswordField();
        Backup_Restore = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        txtPath_r = new javax.swing.JTextArea();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        txtPath_b = new javax.swing.JTextArea();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        Home = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_today = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bed = new javax.swing.JTable();
        jLabel82 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        clock = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txt_day = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbl_icon = new javax.swing.JLabel();
        lbl_position = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbl_photo = new javax.swing.JLabel();
        jScrollPane24 = new javax.swing.JScrollPane();
        tbl_criMed = new javax.swing.JTable();
        jLabel70 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        Inventory = new javax.swing.JPanel();
        Navigation1 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        lbl_medical1 = new javax.swing.JLabel();
        tgl_basic1 = new javax.swing.JToggleButton();
        tgl_contact1 = new javax.swing.JToggleButton();
        tgl_basic2 = new javax.swing.JToggleButton();
        tgl_contact2 = new javax.swing.JToggleButton();
        Inv_Layout = new javax.swing.JPanel();
        Inventory_Home = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        Med_Inventory = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        medicine_table = new javax.swing.JTable(){
        };
        jLabel81 = new javax.swing.JLabel();
        showGraph = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        Purchace = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        order_table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel68 = new javax.swing.JLabel();
        Graph = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        showPieGraph = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Supplier = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        sup_add = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        sup_name = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txt_supid = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        sup_cp = new javax.swing.JTextField();
        sup_ll = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        sup_email = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        Manage = new javax.swing.JPanel();
        Navigation2 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        tgl_medicine = new javax.swing.JToggleButton();
        tgl_report = new javax.swing.JToggleButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tgl_addmedicine = new javax.swing.JToggleButton();
        tgl_newill = new javax.swing.JToggleButton();
        tgl_newill1 = new javax.swing.JToggleButton();
        tgl_report1 = new javax.swing.JToggleButton();
        tgl_medicine1 = new javax.swing.JToggleButton();
        Manage_Layout = new javax.swing.JPanel();
        Reports = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        radio1 = new javax.swing.JRadioButton();
        radio2 = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        cmb_month = new javax.swing.JComboBox();
        cmb_year = new javax.swing.JComboBox();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_general = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        chooser_from = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        chooser_to = new com.toedter.calendar.JDateChooser();
        cmb_specific = new javax.swing.JComboBox();
        jLabel110 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_specific = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        Edit = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel114 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        btn_edit_purpose = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel124 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        btn_edit_purpose1 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        lbl_medname = new javax.swing.JTextField();
        jLabel126 = new javax.swing.JLabel();
        id_sheet = new javax.swing.JLabel();
        AddMed = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        lbl_medid = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        txt_medname = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        btn_add_generic1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbl_generic = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbl_purposes = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        btn_add_new_purpose = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        cmb_cat = new javax.swing.JComboBox();
        jLabel125 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        View = new javax.swing.JPanel();
        txt_searchmed = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        cmb_avail = new javax.swing.JComboBox();
        jLabel107 = new javax.swing.JLabel();
        cmb_purpose = new javax.swing.JComboBox();
        jLabel108 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_showmedicine = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        btn_edit = new javax.swing.JButton();
        btn_dispose = new javax.swing.JButton();
        btn_deactivate = new javax.swing.JButton();
        chk_active = new javax.swing.JCheckBox();
        jButton35 = new javax.swing.JButton();
        Medicine_Home = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        Bed = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        chooser_from_confine = new com.toedter.calendar.JDateChooser();
        chooser_to_confine = new com.toedter.calendar.JDateChooser();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        tbl_confine = new javax.swing.JTable();
        printResultBut = new javax.swing.JButton();
        Dipose = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        lbl_medneym = new javax.swing.JLabel();
        lbl_current = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel67 = new javax.swing.JLabel();
        lbl_med_id = new javax.swing.JLabel();
        Summary = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        tbl_summary = new javax.swing.JTable();
        btn_shooow = new javax.swing.JButton();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        txt_col = new javax.swing.JTextField();
        txt_shs = new javax.swing.JTextField();
        txt_emp = new javax.swing.JTextField();
        txt_visit = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        cmb_years = new javax.swing.JComboBox();
        jLabel129 = new javax.swing.JLabel();
        jButton42 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        jLabel71.setText("jLabel71");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(244, 244, 244));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(10, 61, 115));

        buttonGroup1.add(tgl_home);
        tgl_home.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_home.setForeground(new java.awt.Color(255, 255, 0));
        tgl_home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/home.png"))); // NOI18N
        tgl_home.setSelected(true);
        tgl_home.setText("HOME");
        tgl_home.setBorderPainted(false);
        tgl_home.setContentAreaFilled(false);
        tgl_home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_home.setFocusPainted(false);
        tgl_home.setFocusable(false);
        tgl_home.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_home.setIconTextGap(6);
        tgl_home.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/home2.png"))); // NOI18N
        tgl_home.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/home2.png"))); // NOI18N
        tgl_home.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/home2.png"))); // NOI18N
        tgl_home.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_home.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_home.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_homeItemStateChanged(evt);
            }
        });
        tgl_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_homeMouseExited(evt);
            }
        });
        tgl_home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_homeActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_profile);
        tgl_profile.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_profile.setForeground(new java.awt.Color(255, 255, 255));
        tgl_profile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/user.png"))); // NOI18N
        tgl_profile.setText("PROFILE");
        tgl_profile.setBorderPainted(false);
        tgl_profile.setContentAreaFilled(false);
        tgl_profile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_profile.setFocusPainted(false);
        tgl_profile.setFocusable(false);
        tgl_profile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_profile.setIconTextGap(6);
        tgl_profile.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/user2.png"))); // NOI18N
        tgl_profile.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/user2.png"))); // NOI18N
        tgl_profile.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/user2.png"))); // NOI18N
        tgl_profile.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_profile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_profile.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_profileItemStateChanged(evt);
            }
        });
        tgl_profile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_profileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_profileMouseExited(evt);
            }
        });
        tgl_profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_profileActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_manage);
        tgl_manage.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_manage.setForeground(new java.awt.Color(255, 255, 255));
        tgl_manage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/pills.png"))); // NOI18N
        tgl_manage.setText("MANAGE");
        tgl_manage.setBorderPainted(false);
        tgl_manage.setContentAreaFilled(false);
        tgl_manage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_manage.setFocusPainted(false);
        tgl_manage.setFocusable(false);
        tgl_manage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_manage.setIconTextGap(6);
        tgl_manage.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/pills2.png"))); // NOI18N
        tgl_manage.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/pills2.png"))); // NOI18N
        tgl_manage.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/pills2.png"))); // NOI18N
        tgl_manage.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_manage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_manage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_manageItemStateChanged(evt);
            }
        });
        tgl_manage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_manageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_manageMouseExited(evt);
            }
        });
        tgl_manage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_manageActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_inventory);
        tgl_inventory.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_inventory.setForeground(new java.awt.Color(255, 255, 255));
        tgl_inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/archive.png"))); // NOI18N
        tgl_inventory.setText("INVENTORY");
        tgl_inventory.setBorderPainted(false);
        tgl_inventory.setContentAreaFilled(false);
        tgl_inventory.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_inventory.setFocusPainted(false);
        tgl_inventory.setFocusable(false);
        tgl_inventory.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_inventory.setIconTextGap(6);
        tgl_inventory.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/archive2.png"))); // NOI18N
        tgl_inventory.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/archive2.png"))); // NOI18N
        tgl_inventory.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/archive2.png"))); // NOI18N
        tgl_inventory.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_inventory.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_inventory.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_inventoryItemStateChanged(evt);
            }
        });
        tgl_inventory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_inventoryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_inventoryMouseExited(evt);
            }
        });
        tgl_inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_inventoryActionPerformed(evt);
            }
        });

        buttonGroup1.add(tgl_maintenance);
        tgl_maintenance.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_maintenance.setForeground(new java.awt.Color(255, 255, 255));
        tgl_maintenance.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/settings.png"))); // NOI18N
        tgl_maintenance.setText("MAINTENANCE");
        tgl_maintenance.setBorderPainted(false);
        tgl_maintenance.setContentAreaFilled(false);
        tgl_maintenance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_maintenance.setFocusPainted(false);
        tgl_maintenance.setFocusable(false);
        tgl_maintenance.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_maintenance.setIconTextGap(6);
        tgl_maintenance.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/settings2.png"))); // NOI18N
        tgl_maintenance.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/settings2.png"))); // NOI18N
        tgl_maintenance.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/settings2.png"))); // NOI18N
        tgl_maintenance.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_maintenance.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_maintenance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_maintenanceItemStateChanged(evt);
            }
        });
        tgl_maintenance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_maintenanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_maintenanceMouseExited(evt);
            }
        });
        tgl_maintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_maintenanceActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/logo.png"))); // NOI18N

        buttonGroup1.add(tgl_logout);
        tgl_logout.setFont(new java.awt.Font("Raleway Medium", 0, 11)); // NOI18N
        tgl_logout.setForeground(new java.awt.Color(255, 255, 255));
        tgl_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/logout.png"))); // NOI18N
        tgl_logout.setText("LOG OUT");
        tgl_logout.setBorderPainted(false);
        tgl_logout.setContentAreaFilled(false);
        tgl_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_logout.setFocusPainted(false);
        tgl_logout.setFocusable(false);
        tgl_logout.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tgl_logout.setIconTextGap(6);
        tgl_logout.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/logout2.png"))); // NOI18N
        tgl_logout.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/logout2.png"))); // NOI18N
        tgl_logout.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/logout2.png"))); // NOI18N
        tgl_logout.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        tgl_logout.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tgl_logout.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_logoutItemStateChanged(evt);
            }
        });
        tgl_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_logoutMouseExited(evt);
            }
        });
        tgl_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tgl_home, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgl_profile, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgl_manage, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgl_inventory, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgl_maintenance, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tgl_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tgl_home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_profile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_manage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_inventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_maintenance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tgl_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );

        Layout.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(10, 61, 115)));
        Layout.setLayout(new java.awt.CardLayout());

        Profile.setPreferredSize(new java.awt.Dimension(1338, 657));
        Profile.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                ProfileComponentShown(evt);
            }
        });

        View_Students.setBackground(new java.awt.Color(10, 61, 115));
        View_Students.setPreferredSize(new java.awt.Dimension(319, 100));

        jSeparator4.setBackground(new java.awt.Color(10, 61, 115));

        lbl_other4.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_other4.setForeground(new java.awt.Color(255, 255, 255));
        lbl_other4.setText("VIEW");

        Profile_Navigation.add(tgl_list);
        tgl_list.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_list.setForeground(new java.awt.Color(255, 255, 0));
        tgl_list.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/stick-man.png"))); // NOI18N
        tgl_list.setSelected(true);
        tgl_list.setText("   List of Students / Employees");
        tgl_list.setBorderPainted(false);
        tgl_list.setContentAreaFilled(false);
        tgl_list.setFocusPainted(false);
        tgl_list.setFocusable(false);
        tgl_list.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_list.setIconTextGap(6);
        tgl_list.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/stick-man (1).png"))); // NOI18N
        tgl_list.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/stick-man (1).png"))); // NOI18N
        tgl_list.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/stick-man (1).png"))); // NOI18N
        tgl_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_listMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_listMouseExited(evt);
            }
        });
        tgl_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_listActionPerformed(evt);
            }
        });

        Profile_Navigation.add(tgl_vac);
        tgl_vac.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_vac.setForeground(new java.awt.Color(255, 255, 255));
        tgl_vac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe.png"))); // NOI18N
        tgl_vac.setText("   Vaccination");
        tgl_vac.setBorderPainted(false);
        tgl_vac.setContentAreaFilled(false);
        tgl_vac.setFocusPainted(false);
        tgl_vac.setFocusable(false);
        tgl_vac.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_vac.setIconTextGap(6);
        tgl_vac.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_vacMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_vacMouseExited(evt);
            }
        });
        tgl_vac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_vacActionPerformed(evt);
            }
        });

        Profile_Navigation.add(tgl_inc);
        tgl_inc.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_inc.setForeground(new java.awt.Color(255, 255, 255));
        tgl_inc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol.png"))); // NOI18N
        tgl_inc.setText("   Incomplete Profile (RFID)");
        tgl_inc.setBorderPainted(false);
        tgl_inc.setContentAreaFilled(false);
        tgl_inc.setFocusPainted(false);
        tgl_inc.setFocusable(false);
        tgl_inc.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_inc.setIconTextGap(6);
        tgl_inc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_incMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_incMouseExited(evt);
            }
        });
        tgl_inc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_incActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(0, 51, 255));
        jButton21.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        jButton21.setForeground(new java.awt.Color(240, 240, 240));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/add-document.png"))); // NOI18N
        jButton21.setText("     Add New Profile");
        jButton21.setContentAreaFilled(false);
        jButton21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton21.setFocusPainted(false);
        jButton21.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        jButton21.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home/add-document (1).png"))); // NOI18N
        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton21MouseExited(evt);
            }
        });
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        Profile_Navigation.add(tgl_inc1);
        tgl_inc1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_inc1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_inc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol.png"))); // NOI18N
        tgl_inc1.setText("   Vaccination Report");
        tgl_inc1.setBorderPainted(false);
        tgl_inc1.setContentAreaFilled(false);
        tgl_inc1.setFocusPainted(false);
        tgl_inc1.setFocusable(false);
        tgl_inc1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_inc1.setIconTextGap(6);
        tgl_inc1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/incomplete-hand-drawn-symbol (1).png"))); // NOI18N
        tgl_inc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_inc1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_inc1MouseExited(evt);
            }
        });
        tgl_inc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_inc1ActionPerformed(evt);
            }
        });

        Profile_Navigation.add(tgl_vac1);
        tgl_vac1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_vac1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_vac1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe.png"))); // NOI18N
        tgl_vac1.setText("   Visitors Medication");
        tgl_vac1.setBorderPainted(false);
        tgl_vac1.setContentAreaFilled(false);
        tgl_vac1.setFocusPainted(false);
        tgl_vac1.setFocusable(false);
        tgl_vac1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_vac1.setIconTextGap(6);
        tgl_vac1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/syringe (1).png"))); // NOI18N
        tgl_vac1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_vac1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_vac1MouseExited(evt);
            }
        });
        tgl_vac1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_vac1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout View_StudentsLayout = new javax.swing.GroupLayout(View_Students);
        View_Students.setLayout(View_StudentsLayout);
        View_StudentsLayout.setHorizontalGroup(
            View_StudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_StudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(View_StudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(View_StudentsLayout.createSequentialGroup()
                        .addComponent(lbl_other4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(View_StudentsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(View_StudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_vac, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_inc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_list, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_inc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_vac1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, View_StudentsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(View_StudentsLayout.createSequentialGroup()
                        .addComponent(jSeparator4)
                        .addContainerGap())))
        );
        View_StudentsLayout.setVerticalGroup(
            View_StudentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(View_StudentsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(lbl_other4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_list, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_vac, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_inc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_inc1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_vac1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(318, Short.MAX_VALUE))
        );

        profile_layout.setLayout(new java.awt.CardLayout());

        jLabel101.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(10, 61, 115));
        jLabel101.setText("Profiles");

        javax.swing.GroupLayout Profile_HomeLayout = new javax.swing.GroupLayout(Profile_Home);
        Profile_Home.setLayout(Profile_HomeLayout);
        Profile_HomeLayout.setHorizontalGroup(
            Profile_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Profile_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(677, Short.MAX_VALUE))
        );
        Profile_HomeLayout.setVerticalGroup(
            Profile_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Profile_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(572, Short.MAX_VALUE))
        );

        profile_layout.add(Profile_Home, "profile_home");

        tbl_monitor.setAutoCreateRowSorter(true);
        tbl_monitor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_monitor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Number", "Surname", "Firstname", "Middlename", "Program", "Year Level", "Birthday", "Address", "Contact Number"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_monitor.setRowHeight(27);
        tbl_monitor.setShowHorizontalLines(false);
        tbl_monitor.setShowVerticalLines(false);
        tbl_monitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_monitorMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(tbl_monitor);

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(46, 47, 51));
        jLabel121.setText("Program / Track :");

        cmb_assess.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_assess.setForeground(new java.awt.Color(46, 47, 51));
        cmb_assess.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Physically and Mentally Fit", "Needs Close Observation", "Needs Further Work Up" }));
        cmb_assess.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(46, 47, 51));
        jLabel123.setText("Assessment :");

        College_Senior.add(tgl_college);
        tgl_college.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tgl_college.setSelected(true);
        tgl_college.setText("College");
        tgl_college.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_collegeItemStateChanged(evt);
            }
        });

        College_Senior.add(tgl_senior);
        tgl_senior.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tgl_senior.setText("Senior High");
        tgl_senior.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_seniorItemStateChanged(evt);
            }
        });
        tgl_senior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_seniorActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(10, 61, 115));
        jLabel54.setText("List of Profiles");

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton19.setForeground(new java.awt.Color(10, 61, 115));
        jButton19.setText("Show");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        cmb_track.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_track.setForeground(new java.awt.Color(46, 47, 51));
        cmb_track.setPreferredSize(new java.awt.Dimension(56, 30));

        College_Senior.add(Employee);
        Employee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Employee.setText("Employee / Nurse");
        Employee.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EmployeeItemStateChanged(evt);
            }
        });
        Employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmployeeActionPerformed(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton31.setForeground(new java.awt.Color(10, 61, 115));
        jButton31.setText("Print Result");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setForeground(new java.awt.Color(46, 47, 51));
        jLabel127.setText("Search :");

        txt_sort.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_sort.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_sortKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout ListLayout = new javax.swing.GroupLayout(List);
        List.setLayout(ListLayout);
        ListLayout.setHorizontalGroup(
            ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16)
                    .addGroup(ListLayout.createSequentialGroup()
                        .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ListLayout.createSequentialGroup()
                                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel127, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel121, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                                    .addComponent(tgl_college, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ListLayout.createSequentialGroup()
                                        .addComponent(tgl_senior, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(Employee, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ListLayout.createSequentialGroup()
                                        .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(cmb_track, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txt_sort, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmb_assess, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(371, Short.MAX_VALUE))))
        );
        ListLayout.setVerticalGroup(
            ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ListLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel54)
                .addGap(12, 12, 12)
                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tgl_college)
                    .addComponent(tgl_senior)
                    .addComponent(Employee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel121)
                    .addComponent(cmb_track, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123)
                    .addComponent(cmb_assess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(txt_sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton31)
                .addContainerGap())
        );

        profile_layout.add(List, "card6");

        jLabel55.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(10, 61, 115));
        jLabel55.setText("Vaccination");

        Complete_Incomplete.add(cmb_complete);
        cmb_complete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmb_complete.setText("Complete");
        cmb_complete.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_completeItemStateChanged(evt);
            }
        });

        Complete_Incomplete.add(cmb_inc);
        cmb_inc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cmb_inc.setSelected(true);
        cmb_inc.setText("Incomplete");
        cmb_inc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_incItemStateChanged(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton20.setForeground(new java.awt.Color(10, 61, 115));
        jButton20.setText("Show");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        hrm_tm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        hrm_tm.setForeground(new java.awt.Color(46, 47, 51));
        hrm_tm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " - ALL -", "BSHRM", "BSTM" }));
        hrm_tm.setPreferredSize(new java.awt.Dimension(56, 30));
        hrm_tm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                hrm_tmItemStateChanged(evt);
            }
        });
        hrm_tm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrm_tmActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Program");

        tbl_vaccine.setAutoCreateRowSorter(true);
        tbl_vaccine.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_vaccine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Surname", "Firstname", "Middlename", "Program", "Influenza", "Hepa B Screening", "1st Dose", "2nd Dose", "3rd Dose"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_vaccine.setRowHeight(27);
        tbl_vaccine.setShowVerticalLines(false);
        tbl_vaccine.getTableHeader().setReorderingAllowed(false);
        tbl_vaccine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_vaccineMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tbl_vaccine);
        tbl_vaccine.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton32.setForeground(new java.awt.Color(10, 61, 115));
        jButton32.setText("Print Result");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout VAccineLayout = new javax.swing.GroupLayout(VAccine);
        VAccine.setLayout(VAccineLayout);
        VAccineLayout.setHorizontalGroup(
            VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VAccineLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18)
                    .addGroup(VAccineLayout.createSequentialGroup()
                        .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55)
                            .addGroup(VAccineLayout.createSequentialGroup()
                                .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_complete, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(VAccineLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(cmb_inc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hrm_tm, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(720, Short.MAX_VALUE))))
        );
        VAccineLayout.setVerticalGroup(
            VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VAccineLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_complete)
                    .addComponent(cmb_inc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(VAccineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hrm_tm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton32)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        profile_layout.add(VAccine, "card3");

        jLabel56.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(10, 61, 115));
        jLabel56.setText("Incomplete RFID");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Search :");

        txt_txt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_txtKeyTyped(evt);
            }
        });

        tbl_inc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_inc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fullname", "Program", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_inc.setRowHeight(27);
        tbl_inc.setShowHorizontalLines(false);
        tbl_inc.setShowVerticalLines(false);
        tbl_inc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_incMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tbl_inc);

        jLabel74.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(204, 0, 51));
        jLabel74.setText("*Double Click a row a process the updating of RFID");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update using CSV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(10, 61, 115))); // NOI18N

        txtPath_rfid.setColumns(20);
        txtPath_rfid.setRows(5);
        jScrollPane26.setViewportView(txtPath_rfid);

        jButton38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton38.setForeground(new java.awt.Color(10, 61, 115));
        jButton38.setText("Browse");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton41.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton41.setForeground(new java.awt.Color(10, 61, 115));
        jButton41.setText("Update RFID");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout No_ID_No_EntryLayout = new javax.swing.GroupLayout(No_ID_No_Entry);
        No_ID_No_Entry.setLayout(No_ID_No_EntryLayout);
        No_ID_No_EntryLayout.setHorizontalGroup(
            No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(No_ID_No_EntryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(No_ID_No_EntryLayout.createSequentialGroup()
                        .addGroup(No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, No_ID_No_EntryLayout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        No_ID_No_EntryLayout.setVerticalGroup(
            No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(No_ID_No_EntryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(No_ID_No_EntryLayout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addGap(31, 31, 31)
                        .addGroup(No_ID_No_EntryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        profile_layout.add(No_ID_No_Entry, "card4");

        tbl_hepa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_hepa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Full Name", "Program", "Section"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hepa.setRowHeight(27);
        tbl_hepa.setShowHorizontalLines(false);
        tbl_hepa.setShowVerticalLines(false);
        tbl_hepa.getTableHeader().setReorderingAllowed(false);
        jScrollPane25.setViewportView(tbl_hepa);

        cmb_kategorya.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmb_kategorya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Without Flu Vaccine", "Reactive to HEPA B", "Incomplete HEPA B" }));

        cmb_prog.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmb_prog.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- All -", "BSHRM", "BSTM" }));

        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton23.setForeground(new java.awt.Color(10, 61, 115));
        jButton23.setText("Show");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton37.setForeground(new java.awt.Color(10, 61, 115));
        jButton37.setText("Print");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton39.setForeground(new java.awt.Color(10, 61, 115));
        jButton39.setText("Export");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel72.setText("Select");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel73.setText("Program :");

        jLabel75.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(10, 61, 115));
        jLabel75.setText("Show Incomplete Vaccination");

        javax.swing.GroupLayout FluLayout = new javax.swing.GroupLayout(Flu);
        Flu.setLayout(FluLayout);
        FluLayout.setHorizontalGroup(
            FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FluLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane25)
                    .addGroup(FluLayout.createSequentialGroup()
                        .addGroup(FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel75)
                            .addGroup(FluLayout.createSequentialGroup()
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(cmb_kategorya, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmb_prog, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(FluLayout.createSequentialGroup()
                                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(304, Short.MAX_VALUE))))
        );
        FluLayout.setVerticalGroup(
            FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FluLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel75)
                .addGap(19, 19, 19)
                .addGroup(FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_kategorya, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel72)
                        .addComponent(cmb_prog, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FluLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton37)
                    .addComponent(jButton39))
                .addContainerGap())
        );

        profile_layout.add(Flu, "flu");

        javax.swing.GroupLayout ProfileLayout = new javax.swing.GroupLayout(Profile);
        Profile.setLayout(ProfileLayout);
        ProfileLayout.setHorizontalGroup(
            ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProfileLayout.createSequentialGroup()
                .addComponent(View_Students, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(profile_layout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        ProfileLayout.setVerticalGroup(
            ProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(View_Students, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(ProfileLayout.createSequentialGroup()
                .addComponent(profile_layout, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        Layout.add(Profile, "card3");

        Navigation.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator3.setBackground(new java.awt.Color(10, 61, 115));

        jSeparator8.setBackground(new java.awt.Color(10, 61, 115));

        Maintenance_navigation.add(tgl_med);
        tgl_med.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_med.setForeground(new java.awt.Color(255, 255, 255));
        tgl_med.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/export.png"))); // NOI18N
        tgl_med.setText("   Export/Print Records");
        tgl_med.setBorderPainted(false);
        tgl_med.setContentAreaFilled(false);
        tgl_med.setFocusPainted(false);
        tgl_med.setFocusable(false);
        tgl_med.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_med.setIconTextGap(6);
        tgl_med.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/export (1).png"))); // NOI18N
        tgl_med.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/export (1).png"))); // NOI18N
        tgl_med.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/export (1).png"))); // NOI18N
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

        Maintenance_navigation.add(tgl_import);
        tgl_import.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_import.setForeground(new java.awt.Color(255, 255, 255));
        tgl_import.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/import.png"))); // NOI18N
        tgl_import.setText("   Import Records");
        tgl_import.setBorderPainted(false);
        tgl_import.setContentAreaFilled(false);
        tgl_import.setFocusPainted(false);
        tgl_import.setFocusable(false);
        tgl_import.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_import.setIconTextGap(6);
        tgl_import.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/import (1).png"))); // NOI18N
        tgl_import.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/import (1).png"))); // NOI18N
        tgl_import.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/import (1).png"))); // NOI18N
        tgl_import.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_importItemStateChanged(evt);
            }
        });
        tgl_import.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_importMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_importMouseExited(evt);
            }
        });
        tgl_import.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_importActionPerformed(evt);
            }
        });

        Maintenance_navigation.add(tgl_fam);
        tgl_fam.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_fam.setForeground(new java.awt.Color(255, 255, 255));
        tgl_fam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/write-board.png"))); // NOI18N
        tgl_fam.setText("   Program / Track");
        tgl_fam.setBorderPainted(false);
        tgl_fam.setContentAreaFilled(false);
        tgl_fam.setFocusPainted(false);
        tgl_fam.setFocusable(false);
        tgl_fam.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_fam.setIconTextGap(6);
        tgl_fam.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/write-board (1).png"))); // NOI18N
        tgl_fam.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/write-board (1).png"))); // NOI18N
        tgl_fam.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/write-board (1).png"))); // NOI18N
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

        Maintenance_navigation.add(tgl_problem);
        tgl_problem.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_problem.setForeground(new java.awt.Color(255, 255, 255));
        tgl_problem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/update-arrow.png"))); // NOI18N
        tgl_problem.setText("   Update Records");
        tgl_problem.setBorderPainted(false);
        tgl_problem.setContentAreaFilled(false);
        tgl_problem.setFocusPainted(false);
        tgl_problem.setFocusable(false);
        tgl_problem.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_problem.setIconTextGap(6);
        tgl_problem.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/update-arrow (1).png"))); // NOI18N
        tgl_problem.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/update-arrow (1).png"))); // NOI18N
        tgl_problem.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/update-arrow (1).png"))); // NOI18N
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

        Maintenance_navigation.add(tgl_bmi);
        tgl_bmi.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_bmi.setForeground(new java.awt.Color(255, 255, 255));
        tgl_bmi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/user.png"))); // NOI18N
        tgl_bmi.setText("   Users");
        tgl_bmi.setBorderPainted(false);
        tgl_bmi.setContentAreaFilled(false);
        tgl_bmi.setFocusPainted(false);
        tgl_bmi.setFocusable(false);
        tgl_bmi.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_bmi.setIconTextGap(6);
        tgl_bmi.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/user (1).png"))); // NOI18N
        tgl_bmi.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/user (1).png"))); // NOI18N
        tgl_bmi.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/user (1).png"))); // NOI18N
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

        lbl_other2.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_other2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_other2.setText("MAINTENANCE");

        Maintenance_navigation.add(tgl_man);
        tgl_man.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_man.setForeground(new java.awt.Color(255, 255, 255));
        tgl_man.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings.png"))); // NOI18N
        tgl_man.setText("   Manage Account");
        tgl_man.setBorderPainted(false);
        tgl_man.setContentAreaFilled(false);
        tgl_man.setFocusPainted(false);
        tgl_man.setFocusable(false);
        tgl_man.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_man.setIconTextGap(6);
        tgl_man.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_manMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_manMouseExited(evt);
            }
        });
        tgl_man.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_manActionPerformed(evt);
            }
        });

        Maintenance_navigation.add(tgl_sec);
        tgl_sec.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_sec.setForeground(new java.awt.Color(255, 255, 255));
        tgl_sec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/unlock.png"))); // NOI18N
        tgl_sec.setText("   Security");
        tgl_sec.setBorderPainted(false);
        tgl_sec.setContentAreaFilled(false);
        tgl_sec.setFocusPainted(false);
        tgl_sec.setFocusable(false);
        tgl_sec.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_sec.setIconTextGap(6);
        tgl_sec.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/unlock (1).png"))); // NOI18N
        tgl_sec.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/unlock (1).png"))); // NOI18N
        tgl_sec.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/unlock (1).png"))); // NOI18N
        tgl_sec.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_secItemStateChanged(evt);
            }
        });
        tgl_sec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_secMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_secMouseExited(evt);
            }
        });
        tgl_sec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_secActionPerformed(evt);
            }
        });

        lbl_other3.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_other3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_other3.setText("ACCOUNT SETTINGS");

        Maintenance_navigation.add(tgl_man1);
        tgl_man1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_man1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_man1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings.png"))); // NOI18N
        tgl_man1.setText("   Backup and Restore");
        tgl_man1.setBorderPainted(false);
        tgl_man1.setContentAreaFilled(false);
        tgl_man1.setFocusPainted(false);
        tgl_man1.setFocusable(false);
        tgl_man1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_man1.setIconTextGap(6);
        tgl_man1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/settings (1).png"))); // NOI18N
        tgl_man1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_man1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_man1MouseExited(evt);
            }
        });
        tgl_man1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_man1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addComponent(jSeparator8))
                        .addContainerGap())
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_other2)
                            .addComponent(lbl_other3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(NavigationLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_fam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_bmi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_problem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_med, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(tgl_import, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_man1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_sec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_man, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(lbl_other2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_import, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_med, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_problem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_bmi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_fam, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_man1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbl_other3)
                .addGap(4, 4, 4)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_man, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_sec, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Maintenance_Layout.setPreferredSize(new java.awt.Dimension(1033, 657));
        Maintenance_Layout.setLayout(new java.awt.CardLayout());

        jLabel92.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(10, 61, 115));
        jLabel92.setText("Maintenance ");

        javax.swing.GroupLayout Maintenance_HomeLayout = new javax.swing.GroupLayout(Maintenance_Home);
        Maintenance_Home.setLayout(Maintenance_HomeLayout);
        Maintenance_HomeLayout.setHorizontalGroup(
            Maintenance_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Maintenance_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(677, Short.MAX_VALUE))
        );
        Maintenance_HomeLayout.setVerticalGroup(
            Maintenance_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Maintenance_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(576, Short.MAX_VALUE))
        );

        Maintenance_Layout.add(Maintenance_Home, "home1");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(120, 120, 120));
        jLabel23.setText("Program ID");

        txt_courseid.setEditable(false);
        txt_courseid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_courseid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_courseidKeyTyped(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(10, 61, 115));
        jLabel90.setText("Add new Program / Track");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(120, 120, 120));
        jLabel24.setText("Program Name");

        txt_prog.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_prog.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_progFocusGained(evt);
            }
        });
        txt_prog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_progKeyTyped(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(145, 148, 150));
        jLabel25.setText("e.g BSIT");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(120, 120, 120));
        jLabel26.setText("Program Description");

        txt_desc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_desc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_descFocusGained(evt);
            }
        });
        txt_desc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_descKeyTyped(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(145, 148, 150));
        jLabel27.setText("e.g BS in Information Technology");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(10, 61, 115));
        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        program_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        program_table.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        program_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PROGRAM / TRACK ID", "CODE", "DESCRIPTION", "TYPE", "YEARS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        program_table.setRowHeight(27);
        program_table.setShowHorizontalLines(false);
        program_table.setShowVerticalLines(false);
        jScrollPane22.setViewportView(program_table);

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(10, 61, 115));
        jLabel120.setText("Program / Track List");

        txt_years_complete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_years_complete.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_years_completeFocusGained(evt);
            }
        });
        txt_years_complete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_years_completeKeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(120, 120, 120));
        jLabel28.setText("Years to Complete");

        Add_new_track.add(radio_college);
        radio_college.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio_college.setForeground(new java.awt.Color(70, 70, 70));
        radio_college.setSelected(true);
        radio_college.setText("College");

        Add_new_track.add(radio_senior);
        radio_senior.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        radio_senior.setForeground(new java.awt.Color(70, 70, 70));
        radio_senior.setText("Senior High");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(120, 120, 120));
        jLabel9.setText("Please Select One");

        javax.swing.GroupLayout Add_ProgramLayout = new javax.swing.GroupLayout(Add_Program);
        Add_Program.setLayout(Add_ProgramLayout);
        Add_ProgramLayout.setHorizontalGroup(
            Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_ProgramLayout.createSequentialGroup()
                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Add_ProgramLayout.createSequentialGroup()
                        .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_courseid, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(Add_ProgramLayout.createSequentialGroup()
                                        .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                                .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel27))
                                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_prog, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_years_complete)
                                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                                .addComponent(radio_college)
                                                .addGap(36, 36, 36)
                                                .addComponent(radio_senior))
                                            .addComponent(jLabel9)))))
                            .addGroup(Add_ProgramLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 219, Short.MAX_VALUE))
                    .addGroup(Add_ProgramLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane22)))
                .addContainerGap())
        );
        Add_ProgramLayout.setVerticalGroup(
            Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_ProgramLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_courseid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_prog, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(radio_college)
                    .addComponent(radio_senior))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Add_ProgramLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Add_ProgramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(Add_ProgramLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_years_complete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel120)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
        );

        Maintenance_Layout.add(Add_Program, "card3");

        jLabel53.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(10, 61, 115));
        jLabel53.setText("Add New User");

        userlist.setAutoCreateRowSorter(true);
        userlist.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        userlist.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        userlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Position", "Username"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userlist.setRowHeight(27);
        userlist.setShowHorizontalLines(false);
        userlist.setShowVerticalLines(false);
        jScrollPane21.setViewportView(userlist);

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(46, 47, 51));
        jLabel119.setText("User List");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115)));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(136, 137, 140));
        jLabel83.setText("Employee Number");

        txt_user_ID.setEditable(false);
        txt_user_ID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_user_ID.setForeground(new java.awt.Color(46, 47, 51));

        nurse_name.setEditable(false);
        nurse_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nurse_name.setForeground(new java.awt.Color(46, 47, 51));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(136, 137, 140));
        jLabel84.setText("Employee Name");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(136, 137, 140));
        jLabel85.setText("Username");

        usertxt.setEditable(false);
        usertxt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usertxt.setForeground(new java.awt.Color(46, 47, 51));

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(136, 137, 140));
        jLabel86.setText("Password");

        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(10, 61, 115));
        jButton13.setText("Select User");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton17.setForeground(new java.awt.Color(10, 61, 115));
        jButton17.setText("Generate Username and Password");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton18.setForeground(new java.awt.Color(10, 61, 115));
        jButton18.setText("Register");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 91, 91)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(nurse_name)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txt_user_ID)
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 202, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel86, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(usertxt)
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(passtxt))
                .addGap(44, 44, 44))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usertxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel86)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passtxt))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel83)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_user_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel84)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurse_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton13)
                        .addGap(15, 15, 15)
                        .addComponent(jButton17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton18)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout AddNewUserLayout = new javax.swing.GroupLayout(AddNewUser);
        AddNewUser.setLayout(AddNewUserLayout);
        AddNewUserLayout.setHorizontalGroup(
            AddNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddNewUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(AddNewUserLayout.createSequentialGroup()
                        .addGroup(AddNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel53)
                            .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AddNewUserLayout.setVerticalGroup(
            AddNewUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddNewUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                .addContainerGap())
        );

        Maintenance_Layout.add(AddNewUser, "card4");

        jLabel50.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(10, 61, 115));
        jLabel50.setText("Import Patient Records");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upload Using CSV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(10, 61, 115))); // NOI18N

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(10, 61, 115));
        jToggleButton1.setText("Upload CSV  File");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(10, 61, 115));
        jButton11.setText("Browse File");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        txtPath.setColumns(20);
        txtPath.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPath.setRows(5);
        jScrollPane11.setViewportView(txtPath);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(4, 4, 4)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PROGRAM", "POSITION", "DATE ADDED"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(27);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane12.setViewportView(jTable1);

        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(46, 47, 51));
        jLabel116.setText("Patients List");

        txt_import.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txt_import.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_importKeyPressed(evt);
            }
        });

        cmb_surt.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        cmb_surt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Descending", "Ascending" }));
        cmb_surt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_surtItemStateChanged(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel33.setText("Sort by Date");

        javax.swing.GroupLayout Import_RecordsLayout = new javax.swing.GroupLayout(Import_Records);
        Import_Records.setLayout(Import_RecordsLayout);
        Import_RecordsLayout.setHorizontalGroup(
            Import_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Import_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Import_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(Import_RecordsLayout.createSequentialGroup()
                        .addGroup(Import_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Import_RecordsLayout.createSequentialGroup()
                                .addComponent(txt_import, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(248, 248, 248)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmb_surt, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Import_RecordsLayout.setVerticalGroup(
            Import_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Import_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(Import_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_import, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmb_surt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Maintenance_Layout.add(Import_Records, "card11");

        Export_Print_Records.setForeground(new java.awt.Color(10, 61, 115));

        jLabel51.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(10, 61, 115));
        jLabel51.setText("Export/Print Patient Record");

        tbl_export.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        tbl_export.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_export.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PROGRAM", "YEAR LEVEL", "AGE", "GENDER", "ADDRESS", "CONTACT NO."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_export.setRowHeight(25);
        tbl_export.setShowHorizontalLines(false);
        tbl_export.setShowVerticalLines(false);
        jScrollPane14.setViewportView(tbl_export);

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(46, 47, 51));
        jLabel117.setText("Patients List");

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(10, 61, 115));
        jButton14.setText("Print Record");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(10, 61, 115));
        jButton15.setText("Export to Excel");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        program.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        program.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- Please select -", "ABCOMM", "BSIT", "BSITDA", "BSCS", "BSCOE", "BSTM", "BSBM", "BSHRM", "BSAT", "Senior" }));
        program.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                programItemStateChanged(evt);
            }
        });

        lbl_other1.setBackground(new java.awt.Color(0, 0, 0));
        lbl_other1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_other1.setText("PROGRAM");

        javax.swing.GroupLayout Export_Print_RecordsLayout = new javax.swing.GroupLayout(Export_Print_Records);
        Export_Print_Records.setLayout(Export_Print_RecordsLayout);
        Export_Print_RecordsLayout.setHorizontalGroup(
            Export_Print_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Export_Print_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Export_Print_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Export_Print_RecordsLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Export_Print_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Export_Print_RecordsLayout.createSequentialGroup()
                        .addGroup(Export_Print_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_other1)
                            .addComponent(program, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Export_Print_RecordsLayout.setVerticalGroup(
            Export_Print_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Export_Print_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_other1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(program, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14)
                .addContainerGap())
        );

        Maintenance_Layout.add(Export_Print_Records, "card12");

        Update_Records.setForeground(new java.awt.Color(10, 61, 115));

        jLabel52.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(10, 61, 115));
        jLabel52.setText("Update Patient Records using CSV");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Update Using CSV", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 11), new java.awt.Color(10, 61, 115))); // NOI18N

        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(10, 61, 115));
        jToggleButton2.setText("Proceed and Update");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(10, 61, 115));
        jButton16.setText("Browse File");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        txtPath1.setColumns(20);
        txtPath1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPath1.setRows(5);
        jScrollPane13.setViewportView(txtPath1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addGap(4, 4, 4)
                .addComponent(jToggleButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        update_table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        update_table.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        update_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NAME", "PROGRAM", "POSITION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        update_table.setRowHeight(27);
        update_table.setShowHorizontalLines(false);
        update_table.setShowVerticalLines(false);
        jScrollPane15.setViewportView(update_table);

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(46, 47, 51));
        jLabel118.setText("Patients List");

        javax.swing.GroupLayout Update_RecordsLayout = new javax.swing.GroupLayout(Update_Records);
        Update_Records.setLayout(Update_RecordsLayout);
        Update_RecordsLayout.setHorizontalGroup(
            Update_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Update_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addGroup(Update_RecordsLayout.createSequentialGroup()
                        .addGroup(Update_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52)
                            .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Update_RecordsLayout.setVerticalGroup(
            Update_RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Update_RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel118)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        Maintenance_Layout.add(Update_Records, "card13");

        jLabel98.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(10, 61, 115));
        jLabel98.setText("Manage Account");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(120, 120, 120));
        jLabel29.setText("Security Question 1 ?");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(120, 120, 120));
        jLabel30.setText("Answer");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(120, 120, 120));
        jLabel31.setText("Security Question 2 ?");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(120, 120, 120));
        jLabel32.setText("Answer");

        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton22.setForeground(new java.awt.Color(10, 61, 115));
        jButton22.setText("Save");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jLabel100.setFont(new java.awt.Font("Arcon", 0, 14)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(10, 61, 115));
        jLabel100.setText("Manage Security Questions");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(120, 120, 120));
        jLabel34.setText("Security Question 3 ?");

        q1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        q1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                q1ItemStateChanged(evt);
            }
        });
        q1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                q1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        q2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        q2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                q2ItemStateChanged(evt);
            }
        });

        q3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        q3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                q3ItemStateChanged(evt);
            }
        });

        a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a1ActionPerformed(evt);
            }
        });

        a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a2ActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(120, 120, 120));
        jLabel69.setText("Answer");

        a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                a3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Manage_AccountLayout = new javax.swing.GroupLayout(Manage_Account);
        Manage_Account.setLayout(Manage_AccountLayout);
        Manage_AccountLayout.setHorizontalGroup(
            Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Manage_AccountLayout.createSequentialGroup()
                .addGroup(Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Manage_AccountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Manage_AccountLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(a3)
                                    .addComponent(q3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(a2)
                                    .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(451, Short.MAX_VALUE))
        );
        Manage_AccountLayout.setVerticalGroup(
            Manage_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Manage_AccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(q1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addGap(6, 6, 6)
                .addComponent(q2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addGap(4, 4, 4)
                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(q3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addGap(4, 4, 4)
                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        Maintenance_Layout.add(Manage_Account, "manage_account");

        jLabel99.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(10, 61, 115));
        jLabel99.setText("Security");

        jLabel102.setFont(new java.awt.Font("Arcon", 0, 14)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(10, 61, 115));
        jLabel102.setText("Change Account Password");

        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton24.setForeground(new java.awt.Color(10, 61, 115));
        jButton24.setText("Save");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(120, 120, 120));
        jLabel35.setText("Please enter the prevoius password");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(120, 120, 120));
        jLabel36.setText("Enter the new password");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(120, 120, 120));
        jLabel37.setText("Confirm the new Password");

        oldpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oldpassActionPerformed(evt);
            }
        });

        pass1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass1ActionPerformed(evt);
            }
        });

        pass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pass2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Security_AccountLayout = new javax.swing.GroupLayout(Security_Account);
        Security_Account.setLayout(Security_AccountLayout);
        Security_AccountLayout.setHorizontalGroup(
            Security_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Security_AccountLayout.createSequentialGroup()
                .addGroup(Security_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Security_AccountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Security_AccountLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(Security_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pass2)
                            .addComponent(jLabel102, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(oldpass)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pass1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(658, Short.MAX_VALUE))
        );
        Security_AccountLayout.setVerticalGroup(
            Security_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Security_AccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oldpass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );

        Maintenance_Layout.add(Security_Account, "security_account");

        jLabel130.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(10, 61, 115));
        jLabel130.setText("Backup and Restore Database");

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Restore Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(204, 0, 51))); // NOI18N

        txtPath_r.setColumns(20);
        txtPath_r.setRows(5);
        jScrollPane29.setViewportView(txtPath_r);

        jButton45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton45.setForeground(new java.awt.Color(10, 61, 115));
        jButton45.setText("Browse");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton46.setForeground(new java.awt.Color(10, 61, 115));
        jButton46.setText("Restore Database");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane29)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton46)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Backup Database", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 12), new java.awt.Color(204, 0, 51))); // NOI18N

        txtPath_b.setColumns(20);
        txtPath_b.setRows(5);
        jScrollPane28.setViewportView(txtPath_b);

        jButton43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton43.setForeground(new java.awt.Color(10, 61, 115));
        jButton43.setText("Browse");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton44.setForeground(new java.awt.Color(10, 61, 115));
        jButton44.setText("Backup Database");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton44, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton44)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel131.setText("This option is to allow the user to backup all the data in your database");

        jLabel132.setText("This option is to allow the user to restore data on your database");

        javax.swing.GroupLayout Backup_RestoreLayout = new javax.swing.GroupLayout(Backup_Restore);
        Backup_Restore.setLayout(Backup_RestoreLayout);
        Backup_RestoreLayout.setHorizontalGroup(
            Backup_RestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Backup_RestoreLayout.createSequentialGroup()
                .addGroup(Backup_RestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Backup_RestoreLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Backup_RestoreLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(Backup_RestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel132)
                            .addGroup(Backup_RestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel131, javax.swing.GroupLayout.Alignment.TRAILING)))))
                .addGap(29, 29, Short.MAX_VALUE))
        );
        Backup_RestoreLayout.setVerticalGroup(
            Backup_RestoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Backup_RestoreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        Maintenance_Layout.add(Backup_Restore, "backup");

        javax.swing.GroupLayout MaintenanceLayout = new javax.swing.GroupLayout(Maintenance);
        Maintenance.setLayout(MaintenanceLayout);
        MaintenanceLayout.setHorizontalGroup(
            MaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaintenanceLayout.createSequentialGroup()
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Maintenance_Layout, javax.swing.GroupLayout.DEFAULT_SIZE, 1086, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        MaintenanceLayout.setVerticalGroup(
            MaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Maintenance_Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        Layout.add(Maintenance, "maintenance");

        Home.setPreferredSize(new java.awt.Dimension(1338, 657));
        Home.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                HomeComponentAdded(evt);
            }
        });
        Home.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                HomeFocusGained(evt);
            }
        });
        Home.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                HomeComponentShown(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(10, 61, 115));
        jLabel80.setText("Activities today");

        tbl_today.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_today.setForeground(new java.awt.Color(70, 70, 70));
        tbl_today.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TIME", "ID NUMBER", "NAME", "MEDICATION GIVEN", "ILLNESS", "OCCUPATION", "PROGRAM", "CATERED BY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_today.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_today.setOpaque(false);
        tbl_today.setRowHeight(27);
        tbl_today.setShowHorizontalLines(false);
        tbl_today.setShowVerticalLines(false);
        tbl_today.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tbl_today);
        if (tbl_today.getColumnModel().getColumnCount() > 0) {
            tbl_today.getColumnModel().getColumn(0).setResizable(false);
            tbl_today.getColumnModel().getColumn(1).setResizable(false);
            tbl_today.getColumnModel().getColumn(2).setResizable(false);
            tbl_today.getColumnModel().getColumn(3).setResizable(false);
            tbl_today.getColumnModel().getColumn(4).setResizable(false);
            tbl_today.getColumnModel().getColumn(5).setResizable(false);
            tbl_today.getColumnModel().getColumn(6).setResizable(false);
            tbl_today.getColumnModel().getColumn(7).setResizable(false);
        }

        tbl_bed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_bed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student / Employee No.", "Name", "Reason", "Medicine Given", "Time In", "Catered By"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_bed.setOpaque(false);
        tbl_bed.setRowHeight(27);
        tbl_bed.setShowHorizontalLines(false);
        tbl_bed.setShowVerticalLines(false);
        tbl_bed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bed);
        if (tbl_bed.getColumnModel().getColumnCount() > 0) {
            tbl_bed.getColumnModel().getColumn(0).setResizable(false);
            tbl_bed.getColumnModel().getColumn(1).setResizable(false);
            tbl_bed.getColumnModel().getColumn(2).setResizable(false);
            tbl_bed.getColumnModel().getColumn(3).setResizable(false);
            tbl_bed.getColumnModel().getColumn(4).setResizable(false);
            tbl_bed.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel82.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(10, 61, 115));
        jLabel82.setText("Bed");

        jPanel10.setBackground(new java.awt.Color(10, 61, 115));
        jPanel10.setPreferredSize(new java.awt.Dimension(319, 334));

        txt_search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(46, 47, 51));
        txt_search.setToolTipText("");
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_searchActionPerformed(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchKeyTyped(evt);
            }
        });

        clock.setBackground(new java.awt.Color(255, 255, 255));
        clock.setFont(new java.awt.Font("Roboto", 0, 45)); // NOI18N
        clock.setForeground(new java.awt.Color(240, 240, 240));
        clock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clock.setText("88:88 PM");
        clock.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(240, 240, 240));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Search Profile");

        txt_day.setBackground(new java.awt.Color(255, 255, 255));
        txt_day.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txt_day.setForeground(new java.awt.Color(240, 240, 240));
        txt_day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_day.setText("txt_day");
        txt_day.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jSeparator1.setBackground(new java.awt.Color(10, 61, 115));
        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 3)); // NOI18N

        lbl_icon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_icon.setForeground(new java.awt.Color(255, 255, 255));
        lbl_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_icon.setText("ABAKADA");

        lbl_position.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbl_position.setForeground(new java.awt.Color(255, 255, 255));
        lbl_position.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_position.setText("jLabel70");

        jPanel4.setBackground(new java.awt.Color(10, 61, 115));
        jPanel4.setPreferredSize(new java.awt.Dimension(160, 160));

        lbl_photo.setPreferredSize(new java.awt.Dimension(160, 160));
        lbl_photo.setVerifyInputWhenFocusTarget(false);
        lbl_photo.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                lbl_photoComponentShown(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_photo, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        lbl_photo.getAccessibleContext().setAccessibleDescription("");

        tbl_criMed.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tbl_criMed.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Med Name", "Remaining", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_criMed.setRowHeight(27);
        tbl_criMed.setShowHorizontalLines(false);
        tbl_criMed.setShowVerticalLines(false);
        jScrollPane24.setViewportView(tbl_criMed);

        jLabel70.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 0, 0));
        jLabel70.setText("Medicine Displayed Here are on Critical Level");

        jButton40.setFont(new java.awt.Font("Arcon", 0, 14)); // NOI18N
        jButton40.setForeground(new java.awt.Color(10, 61, 115));
        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/magnifier12.png"))); // NOI18N
        jButton40.setBorderPainted(false);
        jButton40.setContentAreaFilled(false);
        jButton40.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(clock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(txt_search, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbl_position, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_day, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clock)
                .addGap(4, 4, 4)
                .addComponent(txt_day)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton40)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_position)
                .addContainerGap())
        );

        jLabel76.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(204, 0, 51));
        jLabel76.setText("*NOTE: Select the Patient to remove on the bed then Double click to process");

        jLabel77.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(204, 0, 51));
        jLabel77.setText("*NOTE: The activities are automatically updated everyday");

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel77))
                    .addGroup(HomeLayout.createSequentialGroup()
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(jLabel76))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );

        Layout.add(Home, "card7");

        Inventory.setPreferredSize(new java.awt.Dimension(1338, 657));

        Navigation1.setBackground(new java.awt.Color(10, 61, 115));
        Navigation1.setPreferredSize(new java.awt.Dimension(319, 334));

        jSeparator5.setBackground(new java.awt.Color(10, 61, 115));

        lbl_medical1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        lbl_medical1.setForeground(new java.awt.Color(255, 255, 255));
        lbl_medical1.setText("INVENTORY");

        Inventory_navigation.add(tgl_basic1);
        tgl_basic1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_basic1.setForeground(new java.awt.Color(255, 255, 0));
        tgl_basic1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/pills.png"))); // NOI18N
        tgl_basic1.setSelected(true);
        tgl_basic1.setText("   Supply");
        tgl_basic1.setBorderPainted(false);
        tgl_basic1.setContentAreaFilled(false);
        tgl_basic1.setFocusPainted(false);
        tgl_basic1.setFocusable(false);
        tgl_basic1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_basic1.setIconTextGap(6);
        tgl_basic1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/pills (1).png"))); // NOI18N
        tgl_basic1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/pills (1).png"))); // NOI18N
        tgl_basic1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/pills (1).png"))); // NOI18N
        tgl_basic1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_basic1ItemStateChanged(evt);
            }
        });
        tgl_basic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_basic1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_basic1MouseExited(evt);
            }
        });
        tgl_basic1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_basic1ActionPerformed(evt);
            }
        });

        Inventory_navigation.add(tgl_contact1);
        tgl_contact1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_contact1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_contact1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/online-purchase.png"))); // NOI18N
        tgl_contact1.setText("   Purchase");
        tgl_contact1.setBorderPainted(false);
        tgl_contact1.setContentAreaFilled(false);
        tgl_contact1.setFocusPainted(false);
        tgl_contact1.setFocusable(false);
        tgl_contact1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_contact1.setIconTextGap(6);
        tgl_contact1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/online-purchase (1).png"))); // NOI18N
        tgl_contact1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/online-purchase (1).png"))); // NOI18N
        tgl_contact1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/online-purchase (1).png"))); // NOI18N
        tgl_contact1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_contact1ItemStateChanged(evt);
            }
        });
        tgl_contact1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_contact1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_contact1MouseExited(evt);
            }
        });
        tgl_contact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_contact1ActionPerformed(evt);
            }
        });

        Inventory_navigation.add(tgl_basic2);
        tgl_basic2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_basic2.setForeground(new java.awt.Color(255, 255, 255));
        tgl_basic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/stats.png"))); // NOI18N
        tgl_basic2.setText("   Graphical Representation");
        tgl_basic2.setBorderPainted(false);
        tgl_basic2.setContentAreaFilled(false);
        tgl_basic2.setFocusPainted(false);
        tgl_basic2.setFocusable(false);
        tgl_basic2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_basic2.setIconTextGap(6);
        tgl_basic2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/stats (1).png"))); // NOI18N
        tgl_basic2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/stats (1).png"))); // NOI18N
        tgl_basic2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/stats (1).png"))); // NOI18N
        tgl_basic2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_basic2ItemStateChanged(evt);
            }
        });
        tgl_basic2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_basic2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_basic2MouseExited(evt);
            }
        });
        tgl_basic2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_basic2ActionPerformed(evt);
            }
        });

        Inventory_navigation.add(tgl_contact2);
        tgl_contact2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_contact2.setForeground(new java.awt.Color(255, 255, 255));
        tgl_contact2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/real-estate.png"))); // NOI18N
        tgl_contact2.setText("   Supplier's");
        tgl_contact2.setBorderPainted(false);
        tgl_contact2.setContentAreaFilled(false);
        tgl_contact2.setFocusPainted(false);
        tgl_contact2.setFocusable(false);
        tgl_contact2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_contact2.setIconTextGap(6);
        tgl_contact2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/real-estate (1).png"))); // NOI18N
        tgl_contact2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/real-estate (1).png"))); // NOI18N
        tgl_contact2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory_icon/real-estate (1).png"))); // NOI18N
        tgl_contact2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_contact2ItemStateChanged(evt);
            }
        });
        tgl_contact2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_contact2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_contact2MouseExited(evt);
            }
        });
        tgl_contact2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_contact2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Navigation1Layout = new javax.swing.GroupLayout(Navigation1);
        Navigation1.setLayout(Navigation1Layout);
        Navigation1Layout.setHorizontalGroup(
            Navigation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Navigation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Navigation1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(tgl_basic1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                    .addGroup(Navigation1Layout.createSequentialGroup()
                        .addGroup(Navigation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_medical1)
                            .addComponent(jSeparator5))
                        .addContainerGap())
                    .addGroup(Navigation1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(Navigation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tgl_basic2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(tgl_contact1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_contact2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );
        Navigation1Layout.setVerticalGroup(
            Navigation1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_medical1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_basic1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(tgl_basic2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_contact1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_contact2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(432, Short.MAX_VALUE))
        );

        Inv_Layout.setPreferredSize(new java.awt.Dimension(1033, 657));
        Inv_Layout.setLayout(new java.awt.CardLayout());

        jLabel97.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(10, 61, 115));
        jLabel97.setText("Inventory");

        javax.swing.GroupLayout Inventory_HomeLayout = new javax.swing.GroupLayout(Inventory_Home);
        Inventory_Home.setLayout(Inventory_HomeLayout);
        Inventory_HomeLayout.setHorizontalGroup(
            Inventory_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Inventory_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(661, Short.MAX_VALUE))
        );
        Inventory_HomeLayout.setVerticalGroup(
            Inventory_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Inventory_HomeLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(565, Short.MAX_VALUE))
        );

        Inv_Layout.add(Inventory_Home, "home2");

        medicine_table.setAutoCreateRowSorter(true);
        medicine_table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        medicine_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine Number", "Medicine Name", "Remaining Quantity ", "Total Quantity", "Percentage(%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        medicine_table.setFocusable(false);
        medicine_table.setGridColor(new java.awt.Color(153, 153, 153));
        medicine_table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        medicine_table.setRowHeight(27);
        medicine_table.setShowHorizontalLines(false);
        medicine_table.setShowVerticalLines(false);
        medicine_table.getTableHeader().setResizingAllowed(false);
        medicine_table.getTableHeader().setReorderingAllowed(false);
        medicine_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicine_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(medicine_table);

        jLabel81.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(10, 61, 115));
        jLabel81.setText("Supply Monitoring");

        showGraph.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(10, 61, 115)));

        javax.swing.GroupLayout showGraphLayout = new javax.swing.GroupLayout(showGraph);
        showGraph.setLayout(showGraphLayout);
        showGraphLayout.setHorizontalGroup(
            showGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        showGraphLayout.setVerticalGroup(
            showGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(10, 61, 115));
        jLabel103.setText("Medicine List");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(10, 61, 115));
        jLabel104.setText("Graphical Representation");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Legend: ");

        jLabel15.setText("jLabel15");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("100%");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("99% - 80%");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("79% - 50%");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("49% - 20%");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("19% and below");

        jLabel21.setBackground(new java.awt.Color(52, 152, 219));
        jLabel21.setOpaque(true);

        jLabel22.setBackground(new java.awt.Color(39, 174, 96));
        jLabel22.setOpaque(true);

        jLabel38.setBackground(new java.awt.Color(241, 196, 15));
        jLabel38.setOpaque(true);

        jLabel39.setBackground(new java.awt.Color(230, 126, 34));
        jLabel39.setOpaque(true);

        jLabel40.setBackground(new java.awt.Color(192, 57, 43));
        jLabel40.setOpaque(true);

        jButton30.setBackground(new java.awt.Color(10, 61, 115));
        jButton30.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton30.setForeground(new java.awt.Color(10, 61, 115));
        jButton30.setText("Print Result");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Med_InventoryLayout = new javax.swing.GroupLayout(Med_Inventory);
        Med_Inventory.setLayout(Med_InventoryLayout);
        Med_InventoryLayout.setHorizontalGroup(
            Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Med_InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Med_InventoryLayout.createSequentialGroup()
                        .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel103))
                        .addGap(316, 316, 316)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(Med_InventoryLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel10)
                            .addGap(30, 30, 30)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel16)
                            .addGap(25, 25, 25)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17)
                            .addGap(25, 25, 25)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel18)
                            .addGap(25, 25, 25)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel19)
                            .addGap(25, 25, 25)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel20))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(Med_InventoryLayout.createSequentialGroup()
                        .addComponent(jLabel104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(44, 44, 44))))
        );
        Med_InventoryLayout.setVerticalGroup(
            Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Med_InventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Med_InventoryLayout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(21, 21, 21))
                    .addGroup(Med_InventoryLayout.createSequentialGroup()
                        .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Med_InventoryLayout.createSequentialGroup()
                                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel103)
                                    .addComponent(jLabel104))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Med_InventoryLayout.createSequentialGroup()
                        .addComponent(showGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(Med_InventoryLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Med_InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20)
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Inv_Layout.add(Med_Inventory, "card2");

        order_table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        order_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PO ID", "Supplier", "Order Date", "Ship Date", "Prepared by", "Received by", "Received Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        order_table.setRowHeight(27);
        order_table.setShowHorizontalLines(false);
        order_table.setShowVerticalLines(false);
        order_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                order_tableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(order_table);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(10, 61, 115));
        jLabel3.setText("Purchase Orders");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(10, 61, 115));
        jLabel41.setText("Summary View");

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(10, 61, 115));
        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(10, 61, 115));
        jButton9.setText("Preview Order");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "RECEIVED", "PENDING", "CANCELLED" }));

        jLabel68.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 0, 0));
        jLabel68.setText("*Please choose purchase order to received on the table, then double click using the mouse");

        javax.swing.GroupLayout PurchaceLayout = new javax.swing.GroupLayout(Purchace);
        Purchace.setLayout(PurchaceLayout);
        PurchaceLayout.setHorizontalGroup(
            PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PurchaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1060, Short.MAX_VALUE)
                    .addGroup(PurchaceLayout.createSequentialGroup()
                        .addGroup(PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PurchaceLayout.createSequentialGroup()
                        .addGroup(PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(PurchaceLayout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        PurchaceLayout.setVerticalGroup(
            PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PurchaceLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PurchaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel68))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        Inv_Layout.add(Purchace, "card3");

        jLabel105.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(10, 61, 115));
        jLabel105.setText("Supply Graphical Representation");

        showPieGraph.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout showPieGraphLayout = new javax.swing.GroupLayout(showPieGraph);
        showPieGraph.setLayout(showPieGraphLayout);
        showPieGraphLayout.setHorizontalGroup(
            showPieGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        showPieGraphLayout.setVerticalGroup(
            showPieGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel11.setText("Supply Remaining");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel12.setText("Critical Level");

        jLabel13.setBackground(new java.awt.Color(241, 106, 106));
        jLabel13.setOpaque(true);

        jLabel14.setBackground(new java.awt.Color(52, 152, 219));
        jLabel14.setOpaque(true);

        javax.swing.GroupLayout GraphLayout = new javax.swing.GroupLayout(Graph);
        Graph.setLayout(GraphLayout);
        GraphLayout.setHorizontalGroup(
            GraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GraphLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showPieGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(GraphLayout.createSequentialGroup()
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(472, Short.MAX_VALUE))))
            .addGroup(GraphLayout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        GraphLayout.setVerticalGroup(
            GraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GraphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showPieGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(GraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jLabel12)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inv_Layout.add(Graph, "graph");

        jLabel109.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(10, 61, 115));
        jLabel109.setText("Supplier Maintenance");

        sup_add.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sup_addKeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(120, 120, 120));
        jLabel42.setText("Supplier Address");

        sup_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sup_nameKeyTyped(evt);
            }
        });

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(120, 120, 120));
        jLabel43.setText("Supplier Name/Campany");

        txt_supid.setEditable(false);
        txt_supid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_supid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_supidKeyTyped(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(120, 120, 120));
        jLabel44.setText("Supplier ID");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(120, 120, 120));
        jLabel57.setText("Supplier Cellphone Number");

        sup_cp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup_cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sup_cpKeyTyped(evt);
            }
        });

        sup_ll.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup_ll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sup_llKeyTyped(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(120, 120, 120));
        jLabel58.setText("Supplier Landline Number");

        sup_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sup_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sup_emailKeyTyped(evt);
            }
        });

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(120, 120, 120));
        jLabel59.setText("Supplier Email");

        jButton12.setText("Add");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier ID", "Name/Campany", "Address", "Email", "Landlin", "Cellphone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierTable.setRowHeight(25);
        jScrollPane19.setViewportView(supplierTable);

        javax.swing.GroupLayout SupplierLayout = new javax.swing.GroupLayout(Supplier);
        Supplier.setLayout(SupplierLayout);
        SupplierLayout.setHorizontalGroup(
            SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SupplierLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addComponent(jScrollPane19)
                        .addContainerGap())
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addGroup(SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_supid, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup_add, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup_name, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sup_ll, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(133, 133, 133))))
        );
        SupplierLayout.setVerticalGroup(
            SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_email, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel58)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_ll, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SupplierLayout.createSequentialGroup()
                        .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_supid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_add, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Inv_Layout.add(Supplier, "sup");

        javax.swing.GroupLayout InventoryLayout = new javax.swing.GroupLayout(Inventory);
        Inventory.setLayout(InventoryLayout);
        InventoryLayout.setHorizontalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryLayout.createSequentialGroup()
                .addComponent(Navigation1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1086, Short.MAX_VALUE))
            .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventoryLayout.createSequentialGroup()
                    .addContainerGap(325, Short.MAX_VALUE)
                    .addComponent(Inv_Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        InventoryLayout.setVerticalGroup(
            InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Navigation1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(InventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InventoryLayout.createSequentialGroup()
                    .addComponent(Inv_Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 646, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        Layout.add(Inventory, "card5");

        Manage.setPreferredSize(new java.awt.Dimension(1338, 657));

        Navigation2.setBackground(new java.awt.Color(10, 61, 115));
        Navigation2.setPreferredSize(new java.awt.Dimension(319, 334));

        jSeparator2.setBackground(new java.awt.Color(10, 61, 115));

        jLabel2.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("VIEW");

        Manage_navigation.add(tgl_medicine);
        tgl_medicine.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_medicine.setForeground(new java.awt.Color(255, 255, 0));
        tgl_medicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines.png"))); // NOI18N
        tgl_medicine.setText("   Medicine");
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

        Manage_navigation.add(tgl_report);
        tgl_report.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_report.setForeground(new java.awt.Color(255, 255, 255));
        tgl_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/seo-report.png"))); // NOI18N
        tgl_report.setText("   Report");
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

        jSeparator6.setBackground(new java.awt.Color(10, 61, 115));

        jLabel4.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("MANAGE");

        Manage_navigation.add(tgl_addmedicine);
        tgl_addmedicine.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_addmedicine.setForeground(new java.awt.Color(255, 255, 255));
        tgl_addmedicine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/heart-outline-with-a-plus-sign-inside.png"))); // NOI18N
        tgl_addmedicine.setText("   Add New Medicine");
        tgl_addmedicine.setBorderPainted(false);
        tgl_addmedicine.setContentAreaFilled(false);
        tgl_addmedicine.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_addmedicine.setFocusPainted(false);
        tgl_addmedicine.setFocusable(false);
        tgl_addmedicine.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_addmedicine.setIconTextGap(6);
        tgl_addmedicine.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/heart-outline-with-a-plus-sign-inside (1).png"))); // NOI18N
        tgl_addmedicine.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/heart-outline-with-a-plus-sign-inside (1).png"))); // NOI18N
        tgl_addmedicine.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/heart-outline-with-a-plus-sign-inside (1).png"))); // NOI18N
        tgl_addmedicine.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_addmedicineItemStateChanged(evt);
            }
        });
        tgl_addmedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_addmedicineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_addmedicineMouseExited(evt);
            }
        });
        tgl_addmedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_addmedicineActionPerformed(evt);
            }
        });

        tgl_newill.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_newill.setForeground(new java.awt.Color(255, 255, 255));
        tgl_newill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/wheelchair.png"))); // NOI18N
        tgl_newill.setText("   Add New Illness");
        tgl_newill.setBorderPainted(false);
        tgl_newill.setContentAreaFilled(false);
        tgl_newill.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_newill.setFocusPainted(false);
        tgl_newill.setFocusable(false);
        tgl_newill.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_newill.setIconTextGap(6);
        tgl_newill.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/wheelchair.png"))); // NOI18N
        tgl_newill.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/wheelchair.png"))); // NOI18N
        tgl_newill.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/wheelchair.png"))); // NOI18N
        tgl_newill.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_newillItemStateChanged(evt);
            }
        });
        tgl_newill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_newillMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_newillMouseExited(evt);
            }
        });
        tgl_newill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_newillActionPerformed(evt);
            }
        });

        tgl_newill1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_newill1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_newill1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/chromosome-string.png"))); // NOI18N
        tgl_newill1.setText("   Add New Generic");
        tgl_newill1.setBorderPainted(false);
        tgl_newill1.setContentAreaFilled(false);
        tgl_newill1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_newill1.setFocusPainted(false);
        tgl_newill1.setFocusable(false);
        tgl_newill1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_newill1.setIconTextGap(6);
        tgl_newill1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/chromosome-string.png"))); // NOI18N
        tgl_newill1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/chromosome-string.png"))); // NOI18N
        tgl_newill1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/chromosome-string.png"))); // NOI18N
        tgl_newill1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_newill1ItemStateChanged(evt);
            }
        });
        tgl_newill1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_newill1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_newill1MouseExited(evt);
            }
        });
        tgl_newill1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_newill1ActionPerformed(evt);
            }
        });

        Manage_navigation.add(tgl_report1);
        tgl_report1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_report1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_report1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/bed.png"))); // NOI18N
        tgl_report1.setText("   Temporary Confinement");
        tgl_report1.setBorderPainted(false);
        tgl_report1.setContentAreaFilled(false);
        tgl_report1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_report1.setFocusPainted(false);
        tgl_report1.setFocusable(false);
        tgl_report1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_report1.setIconTextGap(6);
        tgl_report1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/bed (1).png"))); // NOI18N
        tgl_report1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/bed (1).png"))); // NOI18N
        tgl_report1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage_icon/bed (1).png"))); // NOI18N
        tgl_report1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_report1ItemStateChanged(evt);
            }
        });
        tgl_report1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_report1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_report1MouseExited(evt);
            }
        });
        tgl_report1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_report1ActionPerformed(evt);
            }
        });

        Manage_navigation.add(tgl_medicine1);
        tgl_medicine1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        tgl_medicine1.setForeground(new java.awt.Color(255, 255, 255));
        tgl_medicine1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines.png"))); // NOI18N
        tgl_medicine1.setText("   Summary Report");
        tgl_medicine1.setBorderPainted(false);
        tgl_medicine1.setContentAreaFilled(false);
        tgl_medicine1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tgl_medicine1.setFocusPainted(false);
        tgl_medicine1.setFocusable(false);
        tgl_medicine1.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        tgl_medicine1.setIconTextGap(6);
        tgl_medicine1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage/medicines2.png"))); // NOI18N
        tgl_medicine1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tgl_medicine1ItemStateChanged(evt);
            }
        });
        tgl_medicine1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tgl_medicine1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tgl_medicine1MouseExited(evt);
            }
        });
        tgl_medicine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgl_medicine1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Navigation2Layout = new javax.swing.GroupLayout(Navigation2);
        Navigation2.setLayout(Navigation2Layout);
        Navigation2Layout.setHorizontalGroup(
            Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Navigation2Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(Navigation2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tgl_report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_medicine, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(tgl_report1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(Navigation2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(Navigation2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Navigation2Layout.createSequentialGroup()
                        .addComponent(jSeparator6)
                        .addContainerGap())
                    .addGroup(Navigation2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tgl_newill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tgl_addmedicine, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                            .addComponent(tgl_newill1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 18, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Navigation2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tgl_medicine1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        Navigation2Layout.setVerticalGroup(
            Navigation2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Navigation2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_medicine, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_report, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_report1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_medicine1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_addmedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_newill, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tgl_newill1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        Manage_Layout.setPreferredSize(new java.awt.Dimension(1033, 657));
        Manage_Layout.setLayout(new java.awt.CardLayout());

        jLabel47.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(10, 61, 115));
        jLabel47.setText("Reports");

        Grp_Reports.add(radio1);
        radio1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radio1.setSelected(true);
        radio1.setText("Medicine Dispensed");
        radio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio1ActionPerformed(evt);
            }
        });

        Grp_Reports.add(radio2);
        radio2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radio2.setText("Illnesses Occured");
        radio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio2ActionPerformed(evt);
            }
        });

        jTabbedPane1.setForeground(new java.awt.Color(10, 61, 115));
        jTabbedPane1.setFont(new java.awt.Font("Microsoft YaHei", 0, 12)); // NOI18N

        jPanel8.setFont(new java.awt.Font("Helvetica Neue", 0, 11)); // NOI18N

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

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(46, 47, 51));
        jLabel111.setText("Month");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(46, 47, 51));
        jLabel112.setText("Year");

        tbl_general.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_general.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Name", "Purpose", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
            tbl_general.getColumnModel().getColumn(3).setResizable(false);
        }

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton7.setText("Show");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Print Result");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                    .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmb_month, 0, 161, Short.MAX_VALUE)
                                    .addComponent(cmb_year, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 734, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(cmb_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(cmb_month, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addContainerGap())
        );

        jTabbedPane1.addTab("GENERAL REPORT", jPanel8);

        chooser_from.setBackground(new java.awt.Color(255, 255, 255));
        chooser_from.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(46, 47, 51));
        jLabel6.setText("From");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(46, 47, 51));
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

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(46, 47, 51));
        jLabel110.setText("Name");

        tbl_specific.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_specific.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Medicine Name", "Medicine Count", "Purpose", "Count"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_specific.setRowHeight(27);
        tbl_specific.setShowHorizontalLines(false);
        tbl_specific.setShowVerticalLines(false);
        jScrollPane6.setViewportView(tbl_specific);

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton5.setText("Show");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton25.setText("Print Result");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmb_specific, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(chooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 699, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 997, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(cmb_specific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooser_from, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(chooser_to, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("SPECIFIC REPORT", jPanel9);

        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton29.setForeground(new java.awt.Color(10, 61, 115));
        jButton29.setText("View Summary Reports");
        jButton29.setToolTipText("");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ReportsLayout = new javax.swing.GroupLayout(Reports);
        Reports.setLayout(ReportsLayout);
        ReportsLayout.setHorizontalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ReportsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel47))
                    .addGroup(ReportsLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1)
                            .addGroup(ReportsLayout.createSequentialGroup()
                                .addComponent(radio1)
                                .addGap(39, 39, 39)
                                .addComponent(radio2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton29)))))
                .addContainerGap())
        );
        ReportsLayout.setVerticalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio1)
                    .addComponent(radio2)
                    .addComponent(jButton29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Manage_Layout.add(Reports, "card3");

        Edit.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                EditComponentShown(evt);
            }
        });

        jLabel48.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(10, 61, 115));
        jLabel48.setText("Edit Medicine");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(46, 47, 51));
        jLabel113.setText("Medicine Name");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/left arrow30.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(46, 47, 51));
        jLabel114.setText("Generic Name/s");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(46, 47, 51));
        jLabel115.setText("Purpose/s");

        jList1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane10.setViewportView(jList1);

        btn_edit_purpose.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btn_edit_purpose.setForeground(new java.awt.Color(255, 0, 0));
        btn_edit_purpose.setText("Add Purpose");
        btn_edit_purpose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit_purpose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_purposeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit_purposeMouseEntered(evt);
            }
        });
        btn_edit_purpose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_edit_purposeKeyPressed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setForeground(new java.awt.Color(46, 47, 51));
        jLabel124.setText("Category");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tablets / Capsules", "Liquid / Drops", "Patches" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btn_edit_purpose1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btn_edit_purpose1.setForeground(new java.awt.Color(255, 0, 0));
        btn_edit_purpose1.setText("Add Generic");
        btn_edit_purpose1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit_purpose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_edit_purpose1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_edit_purpose1MouseEntered(evt);
            }
        });
        btn_edit_purpose1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_edit_purpose1KeyPressed(evt);
            }
        });

        jList2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane23.setViewportView(jList2);

        jButton33.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton33.setText("Delete");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton34.setText("Save Changes");
        jButton34.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        lbl_medname.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setForeground(new java.awt.Color(46, 47, 51));
        jLabel126.setText("Medicine ID");

        id_sheet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        id_sheet.setForeground(new java.awt.Color(46, 47, 51));
        id_sheet.setText("Category");

        javax.swing.GroupLayout EditLayout = new javax.swing.GroupLayout(Edit);
        Edit.setLayout(EditLayout);
        EditLayout.setHorizontalGroup(
            EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48)
                    .addGroup(EditLayout.createSequentialGroup()
                        .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel113, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(jLabel126, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 177, Short.MAX_VALUE)
                            .addComponent(lbl_medname)
                            .addComponent(id_sheet, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)))
                    .addGroup(EditLayout.createSequentialGroup()
                        .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_edit_purpose1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58)
                        .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_edit_purpose, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(576, Short.MAX_VALUE))
        );
        EditLayout.setVerticalGroup(
            EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel126)
                    .addComponent(id_sheet))
                .addGap(18, 18, 18)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel113)
                    .addComponent(lbl_medname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel124)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditLayout.createSequentialGroup()
                        .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit_purpose1)
                            .addComponent(btn_edit_purpose))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel114)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditLayout.createSequentialGroup()
                        .addComponent(jLabel115)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton33))
                .addGap(67, 67, 67)
                .addComponent(jButton34)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        Manage_Layout.add(Edit, "card4");

        AddMed.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                AddMedComponentShown(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(10, 61, 115));
        jLabel49.setText("Add Medicine");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(120, 120, 120));
        jLabel91.setText("Medicine ID");

        lbl_medid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_medid.setForeground(new java.awt.Color(70, 70, 70));
        lbl_medid.setText("Medicine ID");

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(120, 120, 120));
        jLabel93.setText("Medicine Name");

        txt_medname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_medname.setForeground(new java.awt.Color(46, 47, 51));
        txt_medname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_mednameKeyTyped(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(120, 120, 120));
        jLabel94.setText("Generic Name/s");

        btn_add_generic1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btn_add_generic1.setForeground(new java.awt.Color(255, 0, 0));
        btn_add_generic1.setText("Add New Generic");
        btn_add_generic1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add_generic1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_generic1MouseClicked(evt);
            }
        });
        btn_add_generic1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_add_generic1KeyPressed(evt);
            }
        });

        tbl_generic.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_generic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "CHECK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_generic.setRowHeight(27);
        tbl_generic.setShowHorizontalLines(false);
        tbl_generic.setShowVerticalLines(false);
        tbl_generic.getTableHeader().setReorderingAllowed(false);
        jScrollPane7.setViewportView(tbl_generic);
        if (tbl_generic.getColumnModel().getColumnCount() > 0) {
            tbl_generic.getColumnModel().getColumn(0).setResizable(false);
            tbl_generic.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(120, 120, 120));
        jLabel95.setText("Purpose/s");

        tbl_purposes.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_purposes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "CHECK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_purposes.setRowHeight(27);
        tbl_purposes.setShowHorizontalLines(false);
        tbl_purposes.setShowVerticalLines(false);
        jScrollPane8.setViewportView(tbl_purposes);
        if (tbl_purposes.getColumnModel().getColumnCount() > 0) {
            tbl_purposes.getColumnModel().getColumn(0).setResizable(false);
            tbl_purposes.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btn_add_new_purpose.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        btn_add_new_purpose.setForeground(new java.awt.Color(255, 0, 0));
        btn_add_new_purpose.setText("Add New Illness");
        btn_add_new_purpose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_add_new_purpose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_add_new_purposeMouseClicked(evt);
            }
        });
        btn_add_new_purpose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_add_new_purposeKeyPressed(evt);
            }
        });

        jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(120, 120, 120));
        jLabel122.setText("Category");

        cmb_cat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_cat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tablets / Capsules", "Liquid / Drops", "Patches" }));

        jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel125.setForeground(new java.awt.Color(120, 120, 120));
        jLabel125.setText("Quantity");

        txt_qty.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_qty.setForeground(new java.awt.Color(46, 47, 51));
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtyKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout AddMedLayout = new javax.swing.GroupLayout(AddMed);
        AddMed.setLayout(AddMedLayout);
        AddMedLayout.setHorizontalGroup(
            AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddMedLayout.createSequentialGroup()
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel49))
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(AddMedLayout.createSequentialGroup()
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(AddMedLayout.createSequentialGroup()
                                    .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_medname)
                                            .addComponent(btn_add_generic1, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                            .addComponent(jLabel94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(45, 45, 45)
                                    .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_add_new_purpose)
                                        .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(AddMedLayout.createSequentialGroup()
                                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_medid, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmb_cat, 0, 299, Short.MAX_VALUE)
                                    .addComponent(jLabel122, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(299, Short.MAX_VALUE))
        );
        AddMedLayout.setVerticalGroup(
            AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddMedLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addComponent(jLabel91)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_medid))
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addComponent(jLabel122)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(jLabel125))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_medname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addComponent(jLabel94)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddMedLayout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_generic1)
                    .addComponent(btn_add_new_purpose))
                .addGap(18, 18, 18)
                .addGroup(AddMedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Manage_Layout.add(AddMed, "card5");

        View.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                ViewComponentShown(evt);
            }
        });

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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchmedKeyTyped(evt);
            }
        });

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(46, 47, 51));
        jLabel106.setText("Medicine Name");

        cmb_avail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_avail.setForeground(new java.awt.Color(46, 47, 51));
        cmb_avail.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Available" }));
        cmb_avail.setPreferredSize(new java.awt.Dimension(56, 30));

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(46, 47, 51));
        jLabel107.setText("Availability");

        cmb_purpose.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmb_purpose.setForeground(new java.awt.Color(46, 47, 51));
        cmb_purpose.setPreferredSize(new java.awt.Dimension(56, 30));
        cmb_purpose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_purposeActionPerformed(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(46, 47, 51));
        jLabel108.setText("Purpose");

        tbl_showmedicine.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_showmedicine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MEDICINE NAME", "COUNT", "PURPOSE"
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
        jScrollPane4.setViewportView(tbl_showmedicine);

        jLabel46.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(10, 61, 115));
        jLabel46.setText("View Medicine");

        btn_edit.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_edit.setText("Edit");
        btn_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_dispose.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_dispose.setText("Dispose");
        btn_dispose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_dispose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_disposeActionPerformed(evt);
            }
        });

        btn_deactivate.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btn_deactivate.setText("Activate / Deactivate");
        btn_deactivate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_deactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deactivateActionPerformed(evt);
            }
        });

        chk_active.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chk_active.setSelected(true);
        chk_active.setText("Active");
        chk_active.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chk_activeItemStateChanged(evt);
            }
        });

        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton35.setText("Show");
        jButton35.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewLayout = new javax.swing.GroupLayout(View);
        View.setLayout(ViewLayout);
        ViewLayout.setHorizontalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_dispose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_deactivate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ViewLayout.createSequentialGroup()
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel107, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel106, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ViewLayout.createSequentialGroup()
                                .addComponent(cmb_avail, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chk_active, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txt_searchmed)
                            .addComponent(jButton35, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmb_purpose, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 396, Short.MAX_VALUE))
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel46)
                .addGap(234, 234, 234))
        );
        ViewLayout.setVerticalGroup(
            ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel106)
                    .addComponent(jLabel108)
                    .addComponent(cmb_purpose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_searchmed, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(cmb_avail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_active))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton35)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_edit)
                    .addComponent(btn_dispose)
                    .addComponent(btn_deactivate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Manage_Layout.add(View, "card2");

        jLabel96.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(10, 61, 115));
        jLabel96.setText("Medicine Management");

        javax.swing.GroupLayout Medicine_HomeLayout = new javax.swing.GroupLayout(Medicine_Home);
        Medicine_Home.setLayout(Medicine_HomeLayout);
        Medicine_HomeLayout.setHorizontalGroup(
            Medicine_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_HomeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(671, Short.MAX_VALUE))
        );
        Medicine_HomeLayout.setVerticalGroup(
            Medicine_HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Medicine_HomeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(564, Short.MAX_VALUE))
        );

        Manage_Layout.add(Medicine_Home, "home3");

        jLabel60.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(10, 61, 115));
        jLabel60.setText("Temporary Confinement");

        chooser_from_confine.setBackground(new java.awt.Color(255, 255, 255));
        chooser_from_confine.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        chooser_to_confine.setBackground(new java.awt.Color(255, 255, 255));
        chooser_to_confine.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(46, 47, 51));
        jLabel61.setText("To");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(46, 47, 51));
        jLabel62.setText("From");

        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton26.setText("Show Results");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        tbl_confine.setAutoCreateRowSorter(true);
        tbl_confine.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_confine.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date / Time In", "ID", "Name", "Illness", "Medication", "Duration"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_confine.setColumnSelectionAllowed(true);
        tbl_confine.setRowHeight(27);
        tbl_confine.setShowHorizontalLines(false);
        tbl_confine.setShowVerticalLines(false);
        tbl_confine.getTableHeader().setReorderingAllowed(false);
        jScrollPane20.setViewportView(tbl_confine);
        tbl_confine.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        printResultBut.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        printResultBut.setText("Print Result");
        printResultBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printResultButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BedLayout = new javax.swing.GroupLayout(Bed);
        Bed.setLayout(BedLayout);
        BedLayout.setHorizontalGroup(
            BedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(BedLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(BedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BedLayout.createSequentialGroup()
                        .addComponent(printResultBut, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BedLayout.createSequentialGroup()
                        .addGroup(BedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20)
                            .addGroup(BedLayout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooser_from_confine, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooser_to_confine, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 252, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        BedLayout.setVerticalGroup(
            BedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addGap(18, 18, 18)
                .addGroup(BedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooser_to_confine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooser_from_confine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printResultBut, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        Manage_Layout.add(Bed, "card7");

        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/png/left arrow30.png"))); // NOI18N
        jButton27.setBorderPainted(false);
        jButton27.setContentAreaFilled(false);
        jButton27.setFocusPainted(false);
        jButton27.setFocusable(false);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(10, 61, 115));
        jLabel63.setText("Dispose Medicine");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel64.setText("Medicine Name");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel65.setText("Current Count");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel66.setText("Dispose Amount");

        txt_amount.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txt_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_amountKeyPressed(evt);
            }
        });

        lbl_medneym.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_medneym.setText("jLabel67");

        lbl_current.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_current.setText("Current Count");

        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton28.setText("Dipose");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel67.setText("Medicine ID");

        lbl_med_id.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbl_med_id.setText("jLabel68");

        javax.swing.GroupLayout DiposeLayout = new javax.swing.GroupLayout(Dipose);
        Dipose.setLayout(DiposeLayout);
        DiposeLayout.setHorizontalGroup(
            DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiposeLayout.createSequentialGroup()
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DiposeLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel63))
                    .addGroup(DiposeLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(57, 57, 57)
                        .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_current, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addGroup(DiposeLayout.createSequentialGroup()
                                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_amount)
                                        .addComponent(lbl_medneym, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
                                    .addComponent(lbl_med_id))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(611, Short.MAX_VALUE))
        );
        DiposeLayout.setVerticalGroup(
            DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DiposeLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63)
                    .addComponent(jButton27))
                .addGap(24, 24, 24)
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(lbl_med_id))
                .addGap(18, 18, 18)
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(lbl_medneym))
                .addGap(18, 18, 18)
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(lbl_current))
                .addGap(18, 18, 18)
                .addGroup(DiposeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txt_amount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton28)
                .addContainerGap(347, Short.MAX_VALUE))
        );

        Manage_Layout.add(Dipose, "card8");

        tbl_summary.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_summary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Month", "College", "Senior High", "Employees / Staff", "Visitors", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_summary.setRowHeight(27);
        tbl_summary.setShowVerticalLines(false);
        tbl_summary.getTableHeader().setReorderingAllowed(false);
        tbl_summary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_summaryMouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(tbl_summary);

        btn_shooow.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_shooow.setText("Show");
        btn_shooow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_shooowActionPerformed(evt);
            }
        });

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel78.setText("Total Number of College:");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel79.setText("Total Number of Senior High:");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel87.setText("Total Number of Employee:");

        txt_col.setEditable(false);
        txt_col.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txt_shs.setEditable(false);
        txt_shs.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txt_emp.setEditable(false);
        txt_emp.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        txt_visit.setEditable(false);
        txt_visit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel88.setText("Total Number of Visitor:");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel89.setText("TOTAL:");

        total.setEditable(false);
        total.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel128.setText("Select Year");

        cmb_years.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        jLabel129.setFont(new java.awt.Font("Arcon", 0, 30)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(10, 61, 115));
        jLabel129.setText("Annual Report Summary");

        jButton42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton42.setText("Print Result");
        jButton42.setPreferredSize(new java.awt.Dimension(97, 30));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SummaryLayout = new javax.swing.GroupLayout(Summary);
        Summary.setLayout(SummaryLayout);
        SummaryLayout.setHorizontalGroup(
            SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SummaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(SummaryLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SummaryLayout.createSequentialGroup()
                        .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SummaryLayout.createSequentialGroup()
                                .addComponent(jLabel128)
                                .addGap(18, 18, 18)
                                .addComponent(cmb_years, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_shooow, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SummaryLayout.createSequentialGroup()
                                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel87, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_shs, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_emp, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_col, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(SummaryLayout.createSequentialGroup()
                                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(SummaryLayout.createSequentialGroup()
                                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_visit, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane27))
                .addContainerGap())
        );
        SummaryLayout.setVerticalGroup(
            SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SummaryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel129)
                .addGap(28, 28, 28)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_shooow)
                    .addComponent(jLabel128)
                    .addComponent(cmb_years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel78)
                        .addComponent(txt_col, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(txt_shs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(txt_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(txt_visit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(SummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        Manage_Layout.add(Summary, "summary");

        javax.swing.GroupLayout ManageLayout = new javax.swing.GroupLayout(Manage);
        Manage.setLayout(ManageLayout);
        ManageLayout.setHorizontalGroup(
            ManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManageLayout.createSequentialGroup()
                .addComponent(Navigation2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1086, 1086, 1086))
            .addGroup(ManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageLayout.createSequentialGroup()
                    .addContainerGap(325, Short.MAX_VALUE)
                    .addComponent(Manage_Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        ManageLayout.setVerticalGroup(
            ManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Navigation2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
            .addGroup(ManageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ManageLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Manage_Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 635, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        Layout.add(Manage, "card6");

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setForeground(new java.awt.Color(255, 255, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(1407, 4));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1407, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Layout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Layout, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained
        txt_search.selectAll();
    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost

    }//GEN-LAST:event_txt_searchFocusLost

    private void txt_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
        try{
        if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            
            if (txt_search.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Textfield is empty");
            }else{
                
                if (txt_search.getText().matches(".*\\d+.*")){
                    
                    String sql = "Select id_no as 'id number'"
                    + " FROM patient_tbl"
                    + " WHERE RFID = '"+txt_search.getText()+"' OR id_no = '"+txt_search.getText()+"';";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();

                    if (rs.next()){

                        String a = rs.getString("id number");
                        new Medication().setVisible(true);
                        this.dispose();
                        Medication.lbl_id.setText(a);
                        txt_search.setText("");

                    }else{
                        int dialog = JOptionPane.showConfirmDialog(rootPane, "No Record Found! Do you want to register this ID?", null, JOptionPane.YES_NO_OPTION);
                        txt_search.setText("");

                        if (dialog==0){
                            tgl_profile.setSelected(true);
                            CardLayout c2 = (CardLayout)Layout.getLayout();
                            c2.show(Layout,"card3");
                            
                            CardLayout c1 = (CardLayout)profile_layout.getLayout();
                            c1.show(profile_layout,"card4");
                            tgl_inc.setSelected(true);
                        }
                    }
                    
                }else{
                    String ya = txt_search.getText();
                    
                    System.out.println(ya);
                    this.dispose();
                    new frm_results().setVisible(true);
                    
                    
                    
                    
                }

                }
            }
        
        }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyTyped
       

    }//GEN-LAST:event_txt_searchKeyTyped

    private void HomeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_HomeComponentShown
        xyz();
        bed();
    }//GEN-LAST:event_HomeComponentShown

    private void tgl_homeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_homeItemStateChanged
        if (tgl_home.isSelected()){
            tgl_home.setForeground(Color.yellow);
        }else{
            tgl_home.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_homeItemStateChanged

    private void tgl_homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_homeActionPerformed
        txt_search.requestFocus();
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout,"card7");
    }//GEN-LAST:event_tgl_homeActionPerformed

    private void tgl_profileItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_profileItemStateChanged
         if (tgl_profile.isSelected()){
            tgl_profile.setForeground(Color.yellow);
        }else{
            tgl_profile.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_profileItemStateChanged

    private void tgl_profileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_profileActionPerformed
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout,"card3");
        
        CardLayout c2 = (CardLayout)(profile_layout.getLayout());
        c2.show(profile_layout, "profile_home");
    }//GEN-LAST:event_tgl_profileActionPerformed

    private void tgl_manageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_manageItemStateChanged
        if (tgl_manage.isSelected()){
            tgl_manage.setForeground(Color.yellow);
        }else{
            tgl_manage.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_manageItemStateChanged

    private void tgl_manageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_manageActionPerformed
        // TODO add your handling code here:
//        new new_Manage().setVisible(true);
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout,"card6");
        
        
        CardLayout c2 = (CardLayout)(Manage_Layout.getLayout());
        c2.show(Manage_Layout, "home3");
        
        
        try {
            
            cmb_purpose.removeAllItems();
            cmb_purpose.addItem("    -    ");
            
            String purpose = "SELECT * FROM ill_table;";
            pstmt = conn.prepareStatement(purpose);
            rs = pstmt.executeQuery();
            
            while (rs.next()){
                String illname = rs.getString("ill_name");
                
                cmb_purpose.addItem(illname);
                
            }
            
            
            
            
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_tgl_manageActionPerformed

    private void tgl_inventoryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_inventoryItemStateChanged
        if (tgl_inventory.isSelected()){
            tgl_inventory.setForeground(Color.yellow);
        }else{
            tgl_inventory.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_inventoryItemStateChanged

    private void tgl_inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_inventoryActionPerformed
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout,"card5");
        
        CardLayout c2 = (CardLayout)(Inv_Layout.getLayout());
        c2.show(Inv_Layout, "home2");
    }//GEN-LAST:event_tgl_inventoryActionPerformed

    private void tgl_maintenanceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_maintenanceItemStateChanged
        if (tgl_maintenance.isSelected()){
            tgl_maintenance.setForeground(Color.yellow);
        }else{
            tgl_maintenance.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_maintenanceItemStateChanged

    private void tgl_maintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_maintenanceActionPerformed
        CardLayout c1 = (CardLayout)(Layout.getLayout());
        c1.show(Layout,"maintenance");
        
        CardLayout c2 = (CardLayout)(Maintenance_Layout.getLayout());
        c2.show(Maintenance_Layout, "home1");
    }//GEN-LAST:event_tgl_maintenanceActionPerformed

    private void tgl_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_homeMouseEntered
        if (tgl_home.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_home.setForeground(Color.yellow);
        }

    }//GEN-LAST:event_tgl_homeMouseEntered

    private void tgl_profileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_profileMouseEntered
        if (tgl_profile.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_profile.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_profileMouseEntered

    private void tgl_manageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_manageMouseEntered
        if (tgl_manage.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_manage.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_manageMouseEntered

    private void tgl_inventoryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_inventoryMouseEntered
        if (tgl_inventory.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_inventory.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_inventoryMouseEntered

    private void tgl_maintenanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_maintenanceMouseEntered
        if (tgl_maintenance.getForeground().equals(Color.yellow)){
            
        }else{
            tgl_maintenance.setForeground(Color.yellow);
        }
    }//GEN-LAST:event_tgl_maintenanceMouseEntered

    private void tgl_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_homeMouseExited
        if (tgl_home.isSelected()){
            tgl_home.setForeground(Color.yellow);
            
        }else{
            tgl_home.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_homeMouseExited

    private void tgl_profileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_profileMouseExited
        if (tgl_profile.isSelected()){
            tgl_profile.setForeground(Color.yellow);
        }else{
            
        
            tgl_profile.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_profileMouseExited

    private void tgl_manageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_manageMouseExited
        if (tgl_manage.isSelected()){
            tgl_manage.setForeground(Color.yellow);
        }else{
            tgl_manage.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_manageMouseExited

    private void tgl_inventoryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_inventoryMouseExited
        if (tgl_inventory.isSelected()){
            tgl_inventory.setForeground(Color.yellow);
        }else{
            tgl_inventory.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_inventoryMouseExited

    private void tgl_maintenanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_maintenanceMouseExited
        if (tgl_maintenance.isSelected()){
            tgl_maintenance.setForeground(Color.yellow);
        }else{
            tgl_maintenance.setForeground(Color.white);
        }
    }//GEN-LAST:event_tgl_maintenanceMouseExited

    private void tgl_medItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_medItemStateChanged
       
    }//GEN-LAST:event_tgl_medItemStateChanged

    private void tgl_medMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medMouseEntered
        mouse_in(tgl_med);
    }//GEN-LAST:event_tgl_medMouseEntered

    private void tgl_medMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medMouseExited
        mouse_out(tgl_med);
    }//GEN-LAST:event_tgl_medMouseExited

    private void tgl_importItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_importItemStateChanged
       
        
    }//GEN-LAST:event_tgl_importItemStateChanged

    private void tgl_importMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_importMouseEntered
        mouse_in(tgl_import);
    }//GEN-LAST:event_tgl_importMouseEntered

    private void tgl_importMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_importMouseExited
       mouse_out(tgl_import);
    }//GEN-LAST:event_tgl_importMouseExited

    private void tgl_famItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_famItemStateChanged
        
    }//GEN-LAST:event_tgl_famItemStateChanged

    private void tgl_famMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_famMouseEntered
        mouse_in(tgl_fam);
    }//GEN-LAST:event_tgl_famMouseEntered

    private void tgl_famMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_famMouseExited
        mouse_out(tgl_fam);
    }//GEN-LAST:event_tgl_famMouseExited

    private void tgl_problemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_problemItemStateChanged
      
    }//GEN-LAST:event_tgl_problemItemStateChanged

    private void tgl_problemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_problemMouseEntered
        mouse_in(tgl_problem);
    }//GEN-LAST:event_tgl_problemMouseEntered

    private void tgl_problemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_problemMouseExited
        mouse_out(tgl_problem);
    }//GEN-LAST:event_tgl_problemMouseExited

    private void tgl_bmiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_bmiItemStateChanged
       
    }//GEN-LAST:event_tgl_bmiItemStateChanged

    private void tgl_bmiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bmiMouseEntered
        mouse_in(tgl_bmi);
    }//GEN-LAST:event_tgl_bmiMouseEntered

    private void tgl_bmiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_bmiMouseExited
        mouse_out(tgl_bmi);
    }//GEN-LAST:event_tgl_bmiMouseExited

    private void tgl_logoutItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_logoutItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_logoutItemStateChanged

    private void tgl_logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_logoutMouseEntered
        tgl_logout.setForeground(Color.red);
    }//GEN-LAST:event_tgl_logoutMouseEntered

    private void tgl_logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_logoutMouseExited
        tgl_logout.setForeground(new Color(193,195,198));
    }//GEN-LAST:event_tgl_logoutMouseExited

    private void tgl_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_logoutActionPerformed
        this.dispose();
        new login_again().setVisible(true);
    }//GEN-LAST:event_tgl_logoutActionPerformed

    private void ProfileComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ProfileComponentShown
        
    }//GEN-LAST:event_ProfileComponentShown

    private void tgl_medicineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_medicineItemStateChanged
        if (tgl_medicine.isSelected()){
            tgl_medicine.setForeground(Color.yellow);

        }else{
            tgl_medicine.setForeground(new Color(255,255,255));

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
            tgl_medicine.setForeground(new Color(255,255,255));
        }
    }//GEN-LAST:event_tgl_medicineMouseExited

    private void tgl_medicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_medicineActionPerformed
        
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout, "card2");
        txt_searchmed.requestFocus();
        
        txt_searchmed.setText("");
        cmb_avail.setSelectedIndex(0);
        cmb_purpose.setSelectedIndex(0);
        chk_active.setSelected(true);
        showmed();
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
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout, "card3");
        radio1.setSelected(true);
        CBox_function01(cmb_specific); // show all medicine
        jTabbedPane1.setSelectedIndex(0);
        int f = nowna.getYear();
        cmb_year.setSelectedItem(f);
        String e = nowna.getMonth().toString();
        cmb_month.setSelectedItem(e);
        DefaultTableModel mod = (DefaultTableModel)tbl_specific.getModel();
        mod.setRowCount(0);
        DefaultTableModel mode = (DefaultTableModel)tbl_general.getModel();
        mode.setRowCount(0);
        chooser_from.setDate(null);
        chooser_to.setDate(null);
        
    }//GEN-LAST:event_tgl_reportActionPerformed

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

    private void tbl_showmedicineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_showmedicineMouseClicked
        if (evt.getClickCount()==2){

            
            
            
        }
    }//GEN-LAST:event_tbl_showmedicineMouseClicked

    private void cmb_monthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_monthItemStateChanged
        refresh_report();
    }//GEN-LAST:event_cmb_monthItemStateChanged

    private void cmb_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_yearItemStateChanged
        refresh_report();
    }//GEN-LAST:event_cmb_yearItemStateChanged

    private void cmb_specificItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_specificItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_specificItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String dynamic,entry,konoha,senpu,dolphin = null;
        if (radio1.isSelected()==true){ //medicine dispensed 
            dynamic = "c.ill_name";
            entry = "b.med_name";
            konoha = "a.med_id";
            senpu = "a.ill_id";
            dolphin = "b.med_name";
            JTableHeader th = tbl_specific.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(1);
            TableColumn tc1 = tcm.getColumn(3);
            TableColumn tc2 = tcm.getColumn(2); //medicine count
            TableColumn tc3 = tcm.getColumn(4); //illness count
            tc.setHeaderValue("Medicine Name");
            tc1.setHeaderValue("Purpose");
            tc2.setHeaderValue("Medicine Count");
            tc3.setHeaderValue("Count");
            th.repaint();
        }else{ // illness occurred 
            dynamic = "b.med_name";
            entry = "c.ill_name";
            konoha = "a.ill_id";
            senpu = "a.med_id";
            dolphin = "c.ill_name";
            JTableHeader th = tbl_specific.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc = tcm.getColumn(1);
            TableColumn tc1 = tcm.getColumn(3);
            TableColumn tc2 = tcm.getColumn(2); //illness count
            TableColumn tc3 = tcm.getColumn(4); //medicine count
            tc1.setHeaderValue("Medicine Given");
            tc.setHeaderValue("Illness Name");
            tc3.setHeaderValue("Medicine Count");
            tc2.setHeaderValue("Count");
            th.repaint();
        }

        if (chooser_to.getDate() == null || chooser_from.getDate() == null){
            JOptionPane.showMessageDialog(null,"Please Input Necessary Information", "WARNING", JOptionPane.WARNING_MESSAGE);
        }else{
            if (chooser_from.getDate().after(chooser_to.getDate()) || chooser_to.getDate().before(chooser_from.getDate())){
                JOptionPane.showMessageDialog(null,"Please Input Valid Dates");
                chooser_from.setDate(null);
                chooser_to.setDate(null);
            }else{
                int i = 0;
                int y = 0;
                try {
                    String query = null;
                    String get_from = abc.format(chooser_from.getDate());
                    String get_to = abc.format(chooser_to.getDate());
                    String x = cmb_specific.getSelectedItem().toString();

                    DefaultTableModel specific = (DefaultTableModel)tbl_specific.getModel();
                    specific.setRowCount(0);

                    if (cmb_specific.getSelectedItem().toString().equalsIgnoreCase("ALL")){

                        query = "SELECT "+dynamic+" w, DATE_FORMAT(a.date_given, '%M %e, %Y') x, "+entry+" y, NULLIF (COUNT("+konoha+"),0)z, NULLIF (COUNT("+senpu+"),0) a, a.amount abaka"
                        + " FROM med_dspnsd a LEFT JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE date_given BETWEEN '"+get_from+"' AND '"+get_to+"'"
                        + " GROUP BY b.med_name, a.date_given"
                        + " ORDER BY a.date_given";

                        pstmt = conn.prepareStatement(query);
                        rs = pstmt.executeQuery();
                        while(rs.next()){
                            String get_date = rs.getString("x");
                            String get_name = rs.getString("y");
                            String get_purpose = rs.getString("w");
                            int acount = rs.getInt("z");
                            int acount2 = rs.getInt("a");
                            
                            int abaka = rs.getInt("abaka");
                            if (radio1.isSelected()==true){
                                acount = acount * abaka;
                            }else{
                                acount2 = acount2 * abaka;
                            }

                            specific.addRow(new Object[]{get_date, get_name,acount, get_purpose, acount2});
                            
                            i = i + acount;
                            y = y + acount2;

                        }

                        if (i>0){
                            specific.addRow(new Object[]{"","TOTAL",i,"TOTAL",y});
                        }

                    }else{

                        query = "SELECT "+dynamic+" w, DATE_FORMAT(a.date_given, '%M %e, %y') x, "+entry+" y, NULLIF (COUNT("+konoha+"),0)z, NULLIF (COUNT("+senpu+"),0) a, a.amount abaka"
                        + " FROM med_dspnsd a INNER JOIN med_table b"
                        + " ON a.med_id = b.med_id"
                        + " INNER JOIN ill_table c"
                        + " ON a.ill_id = c.ill_id"
                        + " WHERE date_given BETWEEN '"+get_from+"' AND '"+get_to+"'"
                        + " AND "+dolphin+" = '"+x+"'"
                        + " GROUP BY b.med_name, a.date_given"
                        + " ORDER BY a.date_given";

                        pstmt = conn.prepareStatement(query);
                        rs = pstmt.executeQuery();
                        while(rs.next()){
                            String get_date = rs.getString("x");
                            String get_name = rs.getString("y");
                            String get_purpose = rs.getString("w");
                            int acount = rs.getInt("z");
                            int acount2 = rs.getInt("a");
                            
                            

                            specific.addRow(new Object[]{get_date, get_name,acount, get_purpose,acount2 });

                            i = i + acount;
                            y = y + acount2;
                        }
                        if (i>0){
                            specific.addRow(new Object[]{"","TOTAL",i,"TOTAL", y});
                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout,"card2");
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void show_view(){
        DefaultTableModel mod = (DefaultTableModel)tbl_showmedicine.getModel();
        mod.setRowCount(0);

        try {

            String query = "SELECT * FROM med_table a INNER JOIN med_ill b ON a.med_id = b.med_id"
            + " INNER JOIN ill_table c ON b.ill_id = c.ill_id"
            + " WHERE a.med_name like '%"+txt_searchmed.getText()+"%'"
                    + " ORDER BY a.med_name";

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
        
    }
    
    
    private void ViewComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ViewComponentShown
        show_view();
        
    }//GEN-LAST:event_ViewComponentShown

    private void tgl_contact1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contact1MouseExited
        mouse_out(tgl_contact1);
    }//GEN-LAST:event_tgl_contact1MouseExited

    private void tgl_contact1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contact1MouseEntered
        mouse_in(tgl_contact1);
    }//GEN-LAST:event_tgl_contact1MouseEntered

    private void tgl_contact1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_contact1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_contact1ItemStateChanged

    private void tgl_basic1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_basic1ActionPerformed
        // TODO add your handling code here:
        updateMedTable();
        CardLayout c1 = (CardLayout)(Inv_Layout.getLayout());
        c1.show(Inv_Layout, "card2");
    }//GEN-LAST:event_tgl_basic1ActionPerformed

    private void tgl_basic1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basic1MouseExited
        mouse_out(tgl_basic1);
    }//GEN-LAST:event_tgl_basic1MouseExited

    private void tgl_basic1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basic1MouseEntered
        mouse_in(tgl_basic1);
    }//GEN-LAST:event_tgl_basic1MouseEntered

    private void tgl_basic1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_basic1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_basic1ItemStateChanged

    private void tgl_contact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_contact1ActionPerformed
        CardLayout c1 = (CardLayout)(Inv_Layout.getLayout());
        c1.show(Inv_Layout,"card3");
        
    }//GEN-LAST:event_tgl_contact1ActionPerformed
    
    public void updatePurchaseOrders(){
        DefaultTableModel model = (DefaultTableModel)order_table.getModel();
        model.setRowCount(0);
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM purchase_orders;";
            rs = st.executeQuery(query);

            while (rs.next()){
                String po_id = rs.getString("po_id");
                String po_sup = rs.getString("po_sup");
                String po_date = rs.getString("po_date");
                String po_ship = rs.getString("po_ship");
                String po_prepby = rs.getString("po_prepby");
                String po_recby = rs.getString("po_recby");
                String po_status = rs.getString("po_status");         
                String po_recibdate = rs.getString("po_recibdate");
                model.addRow(new Object[]{po_id, po_sup, po_date,po_ship,po_prepby,po_recby,po_recibdate,po_status});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        //updateCmbMedCat();
    }
    
    
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        DefaultTableModel mod = (DefaultTableModel) tbl_general.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\generalReports.jrxml";   
        int x = mod.getRowCount();
        
        String nurseName = Firstname +" "+ Lastname;

        
        String colName1 = tbl_general.getModel().getColumnName(0);
        String colName2 = tbl_general.getModel().getColumnName(1);
        String colName3 = tbl_general.getModel().getColumnName(2);
        String colName4 = tbl_general.getModel().getColumnName(3);
        String selectedbtn = radio1.getText();;
        
        if (radio2.isSelected()) {
            selectedbtn = radio2.getText();
            colName2 = "Illness";
            colName3 = "Medicine Given";
        }
        
        String year = cmb_year.getSelectedItem().toString();
        String month = cmb_month.getSelectedItem().toString();
        
        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{    
                
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("illOrMed", selectedbtn);
                params.put("year", year);
                params.put("month", month);
                
                params.put("colName1", colName1);
                params.put("colName2", colName2);
                params.put("colName3", colName3);
                params.put("colName4", colName4);
                params.put("nurseName", nurseName);
                
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void tbl_bedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bedMouseClicked
        if (evt.getClickCount()==2){
            
            try {
                int row = tbl_bed.getSelectedRow();
                String id = tbl_bed.getValueAt(row, 0).toString();
                String intime = tbl_bed.getValueAt(row, 4).toString();
                String name = tbl_bed.getValueAt(row, 1).toString();
                
                
                
                int dialogresult = JOptionPane.showConfirmDialog(null, "<html><body width='250'>Are you sure you want to time out<br><br>"+name+"?", "W A R N I N G !", JOptionPane.YES_NO_OPTION);
                if (dialogresult == 0){
                
                    String java = "UPDATE med_dspnsd SET"
                            + " outTime = '"+hour+"'"
                            + " WHERE id_no = '"+id+"'"
                            + " AND intime is not null";

                    pstmt = conn.prepareStatement(java);
                    pstmt.executeUpdate();

                    bed();
                    JOptionPane.showMessageDialog(null, "Success");
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_tbl_bedMouseClicked

    private void tgl_medActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_medActionPerformed
        //Export_Print_Records
        updateTable1();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "card12");
    }//GEN-LAST:event_tgl_medActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        refresh_report();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tgl_addmedicineItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_addmedicineItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_addmedicineItemStateChanged

    private void tgl_addmedicineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_addmedicineMouseEntered
        mouse_in(tgl_addmedicine);
    }//GEN-LAST:event_tgl_addmedicineMouseEntered

    private void tgl_addmedicineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_addmedicineMouseExited
        mouse_out(tgl_addmedicine);
    }//GEN-LAST:event_tgl_addmedicineMouseExited

    private void tgl_addmedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_addmedicineActionPerformed
        CardLayout c1 = (CardLayout)Manage_Layout.getLayout();
        c1.show(Manage_Layout,"card5");
        refresh_purpose();
        show_all_generic();
    }//GEN-LAST:event_tgl_addmedicineActionPerformed

    private void txt_courseidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_courseidKeyTyped
        char c = evt.getKeyChar();

        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_courseidKeyTyped

    private void txt_progKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_progKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        String text = txt_prog.getText().toUpperCase();
        txt_prog.setText(text);
    }//GEN-LAST:event_txt_progKeyTyped

    private void txt_descKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_descKeyTyped

    private void btn_edit_purposeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_purposeMouseClicked
        new edit_purposes().setVisible(true);

    }//GEN-LAST:event_btn_edit_purposeMouseClicked

    private void btn_edit_purposeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_edit_purposeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_purposeKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int i = jList1.getModel().getSize();
            
            if (i>1){
                
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?",null,JOptionPane.YES_NO_OPTION);
            
                if (dialogresult == 0){

                    String row = jList1.getSelectedValue().toString();



                    String sql = "DELETE FROM med_ill WHERE"
                    + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+lbl_medname.getText()+"')"
                    + " AND ill_id = (SELECT ill_id FROM ill_table WHERE ill_name = '"+row+"');";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();


                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Please leave at least one purpose for this medicine", null, JOptionPane.WARNING_MESSAGE);
            }
            
           

        } catch (SQLException ex) {
            Logger.getLogger(new_Manage.class.getName()).log(Level.SEVERE, null, ex);
        }

        add_list();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_mednameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mednameKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isAlphabetic(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Alphabet Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        uppercase(txt_medname);
    }//GEN-LAST:event_txt_mednameKeyTyped

    private void btn_add_generic1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_generic1MouseClicked
        new add_generic().setVisible(true);
    }//GEN-LAST:event_btn_add_generic1MouseClicked

    private void btn_add_generic1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_add_generic1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add_generic1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String medicine = txt_medname.getText();
        //check kung may karga yung generic names
        Boolean isChecked = false;
        
        for (int i = 0; i<tbl_generic.getRowCount();i++){
            isChecked = Boolean.valueOf(tbl_generic.getValueAt(i,1).toString());            
            if (isChecked){
                isChecked = true;
                break;
            }
        }
        
        //check kung may karga yung purposes
        Boolean isCheckedagain = false;
        for (int y = 0; y<tbl_purposes.getRowCount();y++){
            isCheckedagain = Boolean.valueOf(tbl_purposes.getValueAt(y,1).toString());          
            if (isCheckedagain){
                isCheckedagain = true;
                break;
            }
        }
        
        
        if (medicine.isEmpty() || isChecked == false || isCheckedagain == false || txt_qty.getText().isEmpty()){            
            JOptionPane.showMessageDialog(null, "Input necessary fields");
        }else{
            
            try {
                //check kung may kapangalan
                
                String sql = "select med_name from med_table where med_name = '"+medicine+"';";
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    
                    JOptionPane.showMessageDialog(null, "This medicine is already registered", null, JOptionPane.WARNING_MESSAGE);

                }else{ /// eh walang kapangalan
                    
                  
                    
                    conn.setAutoCommit(false);
                    
                    int a = cmb_cat.getSelectedIndex()+1;
                    int qty = Integer.parseInt(txt_qty.getText());
                    String xql = "insert into med_table set"
                            + " med_id = '"+lbl_medid.getText()+"',"
                            + " med_name = '"+medicine+"',"
                            + " user_ID = '"+user_ID+"',"
                            + " isActive = '1',"
                            + " med_remain = '"+qty+"',"
                            + " med_qty_ordered = '"+qty+"',"
                            + " med_category = '"+a+"',"
                            + " dateCreated = '"+nowna+"';";
                    
                    pstmt = conn.prepareStatement(xql);
                    pstmt.executeUpdate();
                    
                    
                    /// PARA SA MEDICINE GENERIC
                    for (int i = 0; i<tbl_generic.getRowCount();i++){
                    Boolean Checked = Boolean.valueOf(tbl_generic.getValueAt(i,1).toString());
                    

                        if (Checked==true){
                            String x = tbl_generic.getValueAt(i,0).toString();
                            String id = "select generic_id from generic where generic_desc = '"+x+"';";
                            
                            pstmt = conn.prepareStatement(id);
                            rs = pstmt.executeQuery();
                            
                            if (rs.next()){
                                String gen_id = rs.getString("generic_id");
                                
                                //insert generic
                                
                                String cql = "insert into pivot_medicine set"
                                        + " generic_id = '"+gen_id+"',"
                                        + " med_id = '"+lbl_medid.getText()+"';";
                                pstmt = conn.prepareStatement(cql);
                                pstmt.executeUpdate();
                                
                            }
                        }

                    }
                    
                    
                    // PARA SA MEDICINE PURPOSES
                    for (int y = 0; y<tbl_purposes.getRowCount();y++){
                        Boolean no = Boolean.valueOf(tbl_purposes.getValueAt(y,1).toString());
                        
                        if (no==true){
                            
                            String x = tbl_purposes.getValueAt(y,0).toString();
                            String id = "select ill_id from ill_table where ill_name = '"+x+"';";
                            
                            pstmt = conn.prepareStatement(id);
                            rs = pstmt.executeQuery();
                            
                            if (rs.next()){
                                String ill_id = rs.getString("ill_id");
                                
                                //insert sa med ill
                                
                                String cql = "insert into med_ill set"
                                        + " ill_id = '"+ill_id+"',"
                                        + " med_id = '"+lbl_medid.getText()+"';";
                                pstmt = conn.prepareStatement(cql);
                                pstmt.executeUpdate();
                                
                            }

                        }
                    }
                    
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                    
                    conn.commit();
                    CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
                    c1.show(Manage_Layout,"card2");
                    
                    
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AddMedComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_AddMedComponentShown
        try{
            
            String q = "SELECT med_id as ayu FROM med_table ORDER BY med_id ASC";
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
            if(rs.last()){
                int last = rs.getInt("ayu");
                int a = last + 1;
                String x = String.valueOf(a);
                lbl_medid.setText(x);
            }
            
            refresh_purpose();
            show_all_generic();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_AddMedComponentShown

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout,"card2");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btn_add_new_purposeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_add_new_purposeMouseClicked
        new add_purpose().setVisible(true);
    }//GEN-LAST:event_btn_add_new_purposeMouseClicked

    private void btn_add_new_purposeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_add_new_purposeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_add_new_purposeKeyPressed

    private void tgl_newillItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_newillItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newillItemStateChanged

    private void tgl_newillMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_newillMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newillMouseEntered

    private void tgl_newillMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_newillMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newillMouseExited

    private void tgl_newillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_newillActionPerformed
        new add_purpose().setVisible(true);
    }//GEN-LAST:event_tgl_newillActionPerformed

    private void tgl_importActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_importActionPerformed
        // TODO add your handling code here:
        updateTable();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "card11");
    }//GEN-LAST:event_tgl_importActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        String message = "Are you sure you want to import the data on the database ? To proceed click yes.";
        int dialogResult = JOptionPane.showConfirmDialog (null,message ,"Confirmation",dialogButton);
        if(dialogResult == 0){
        
            int loaded = 0;
            int skipped = 0;

            if ("".equals(txtPath.getText())) {
                JOptionPane.showMessageDialog(null, "Please select a path for your CSV.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "The specified file is not found.", "Not found.", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null){
                        if (skip == 1) { ///skip for header in the csv
                            skip++;
                            continue;
                        }
                        String[] value = line.split(",", 21);//Separator
                        try {

                            conn.setAutoCommit(false);

                            st = conn.createStatement();
                            String query = "INSERT INTO patient_tbl (id_no, RFID, lastname, firstname, middlename, program, year_level, student_type,"
                            + "gender, status, nationality, religion, bday, place_birth, occupation, unit_no, house_building, street, brgy, city,"
                            + "contact, insertedOn, user_id) VALUES"
                            + "('"+value[0]+"'"
                            + ", '"+value[1]+"'"
                            + ", '"+value[2]+"'"
                            + ", '"+value[3]+"'"
                            + ", '"+value[4]+"'"
                            + ", '"+value[5]+"'"
                            + ", '"+value[6]+"'"
                            + ", '"+value[7]+"'"
                            + ", '"+value[8]+"'"
                            + ", '"+value[9]+"'"
                            + ", '"+value[10]+"'"
                            + ", '"+value[11]+"'"
                            + ", '"+value[12]+"'"
                            + ", '"+value[13]+"'"
                            + ", '"+value[14]+"'"
                            + ", '"+value[15]+"'"
                            + ", '"+value[16]+"'"
                            + ", '"+value[17]+"'"
                            + ", '"+value[18]+"'"
                            + ", '"+value[19]+"'"
                            + ", '"+value[20]+"'"
                            + ", '"+nowna+"'"
                            + ", '"+user_ID+"');";

                            st.addBatch(query);
                            //System.out.println(abc.format(value[12]));

                            String motherInsert = "INSERT INTO mother_table (id_no) VALUES("+value[0]+");";
                            st.addBatch(motherInsert);

                            String fatherInsert = "INSERT INTO father_table (id_no) VALUES("+value[0]+");";
                            st.addBatch(fatherInsert);

                            String guardianInsert = "INSERT INTO guardian_table (id_no) VALUES("+value[0]+");";
                            st.addBatch(guardianInsert);

                            String spouseInsert = "INSERT INTO spouse_table (id_no) VALUES("+value[0]+");";
                            st.addBatch(spouseInsert);

                            String famhistoryInsert = "INSERT INTO fam_history (id_no) VALUES("+value[0]+");";
                            st.addBatch(famhistoryInsert);

                            String photoInsert = "INSERT INTO photo (id_no) VALUES("+value[0]+");";
                            st.addBatch(photoInsert);
                            
                            String vaccfluInsert = "INSERT INTO vacc_flu (id_no) VALUES("+value[0]+");";
                            st.addBatch(vaccfluInsert);
                            
                            String kwek = "INSERT INTO assessment_tbl (id_no) VALUES("+value[0]+");";
                            st.addBatch(kwek);

                            String vacchepaInsert = "INSERT INTO vacc_hepa_b (id_no, status) VALUES("+value[0]+", 'Ongoing');";
                            st.addBatch(vacchepaInsert);
                            
                            String vacchistoryInsert = "INSERT INTO vacc_history (id_no) VALUES("+value[0]+");";
                            st.addBatch(vacchistoryInsert);
                            
                            
                            String personal_historyInsert = "INSERT INTO personal_history (id_no) VALUES("+value[0]+");";
                            st.addBatch(personal_historyInsert);
                            
                            String med_historyInsert = "INSERT INTO med_history (id_no) VALUES("+value[0]+");";
                            st.addBatch(med_historyInsert);
                            
                            
                            int[] count = st.executeBatch();
                            conn.commit();
                            loaded++;

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error");
                            e.printStackTrace();
                        }

                    }
                    br.close();
                    //return;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(this, "Youre CSV File has been Uploaded Successfully");
                JOptionPane.showMessageDialog(this, "Number of items imported: " + loaded);
                //System.out.println("Number of items skipped: " + skipped);
                //System.out.println("Number of items loaded: " + loaded);
                updateTable();
                txtPath.setText("");
            }
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed
    
    public void updateTable(){
        
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.getDataVector().removeAllElements();
        jTable1.repaint();
        
        String lah = "ORDER BY insertedOn ASC, lastname";
        if (cmb_surt.getSelectedIndex()==1){
            lah = "ORDER BY insertedOn DESC, lastname";
        }

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl WHERE id_no LIKE '%"+txt_import.getText()+"%' or lastname LIKE '%"+txt_import.getText()+"%' or firstname LIKE '%"+txt_import.getText()+"%'"
                    + " OR middlename LIKE '%"+txt_import.getText()+"%' "+lah+"";
            rs = st.executeQuery(query);

            while (rs.next()){
                String id_no = rs.getString("id_no");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String programa = rs.getString("program");
                if (programa.equals("null") || programa.isEmpty()){
                    programa = "  -  ";
                }
                String occupation = rs.getString("occupation");
                
                String capfname = WordUtils.capitalizeFully(firstname);
                String capmname = WordUtils.capitalizeFully(middlename);
                String is = WordUtils.initials(capmname);
                String caplname = WordUtils.capitalizeFully(lastname);
                String lastnato = caplname + ", "+ capfname + " " + is+".";
                String deyt = rs.getString("insertedOn");
                
                model.addRow(new Object[]{id_no,lastnato , programa, occupation, deyt});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "updateTable");
        }

    }
    
    public void updateMedTable(){
        
        DefaultTableModel model = (DefaultTableModel)medicine_table.getModel();
        model.getDataVector().removeAllElements();
        medicine_table.repaint();
        
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM med_table";
            rs = st.executeQuery(query);

            while (rs.next()){
                String med_id = rs.getString("med_id");
                String med_name = rs.getString("med_name");
                String med_remain = rs.getString("med_remain");
                String med_qty_ordered = rs.getString("med_qty_ordered");
                
                double qntyRemaim = Double.parseDouble(med_remain);
                double qntyTotalOrdered = Double.parseDouble(med_qty_ordered);
                //int sample = 100;

                //float percentMed =  (float) ((qntyRemaim*100)/qntyTotalOrdered);

                BigDecimal bd = new BigDecimal(qntyRemaim*100/qntyTotalOrdered);

                bd = bd.setScale(2, RoundingMode.HALF_UP);  
                double percentMed =  bd.doubleValue();
                //System.out.print(gago);
                //int gago2 = (int) (gago * 100);
                
                
                model.addRow(new Object[]{med_id, med_name, med_remain, med_qty_ordered, percentMed+"%"});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "updateMedTable");
        }

    }
    
    public void showGraph(){
        
       // DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //String mename = (medicine_table.getModel().getValueAt(i, 1).toString());
        //String qnty = (medicine_table.getModel().getValueAt(i, 3).toString());
        
        
//        int row = medicine_table.getSelectedRow();
//        String nameMed = (medicine_table.getModel().getValueAt(row,0).toString());
//        try {
//            String query = "SELECT med_name, med_remain, med_qty_ordered FROM med_table WHERE med_id = '"+nameMed+"';";
//            JDBCCategoryDataset dataset  = new JDBCCategoryDataset(SQLConnection.ConnDB(), query);
//            
//            JFreeChart chart = ChartFactory.createBarChart("Medicine Monitoring", "Medicine Name","", dataset, PlotOrientation.VERTICAL,
//                false, true, false);
//            chart.setBackgroundPaint(Color.WHITE); // Set the background colour of the chart
//            chart.getTitle().setPaint(Color.WHITE); // Adjust the colour of the title
//            chart.setBorderPaint(Color.BLUE);
//            
//
//            CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
//            p.setBackgroundPaint(Color.WHITE); // Modify the plot background
//            p.setRangeGridlinePaint(Color.BLUE);
//            
//
//            ChartPanel ps = new ChartPanel(chart);
//            showGraph.add(ps);
//            ps.setSize(showGraph.getWidth(), showGraph.getHeight());
//            ps.setVisible(true);
//
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "showGraphPie");
//        }
        try {
            
            int row = medicine_table.getSelectedRow();
            String nameMed = (medicine_table.getModel().getValueAt(row,0).toString());
            
            String query = "SELECT med_name, med_remain, med_qty_ordered FROM med_table WHERE med_id = '"+nameMed+"';";
            JDBCCategoryDataset dataset  = new JDBCCategoryDataset(SQLConnection.ConnDB(), query);

            dataset.setValue(dataset.getValue(1,0).intValue()-dataset.getValue(0, 0).intValue(), dataset.getRowKey(1), dataset.getColumnKey(0));
            
            JFreeChart chart = ChartFactory.createStackedBarChart("", "","", dataset, PlotOrientation.VERTICAL,
                false, false, false);
            chart.setBackgroundPaint(Color.WHITE); // Set the background colour of the chart
            chart.getTitle().setPaint(Color.WHITE); // Adjust the colour of the title
            chart.setBorderPaint(Color.BLUE);
            
            
            CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
            p.setBackgroundPaint(Color.WHITE); // Modify the plot background
            p.setRangeGridlinePaint(Color.BLUE);
            
             
            p.setRenderer(new StackedBarRenderer3D() {
            private final Color BARCOLOR_1 = new Color(52, 152, 219);
            private final Color BARCOLOR_2 = new Color(39, 174, 96);
            private final Color BARCOLOR_3 = new Color(241, 196, 15);
            private final Color BARCOLOR_4 = new Color(230, 126, 34);
            private final Color BARCOLOR_5 = new Color(192, 57, 43);
            
            private double percent;
            @Override
            public Paint getItemPaint(int row, int column) {
                
                if (row==1) {
                    if (getPlot().getDataset().getValue(row, column).doubleValue()==0) {
                       return BARCOLOR_1; 
                    }
                  return Color.WHITE;  
                }
                percent = getPlot().getDataset().getValue(row, column).doubleValue()*100/(getPlot().getDataset().getValue(row+1, column).doubleValue()
                        +getPlot().getDataset().getValue(row, column).doubleValue());
                System.out.println(percent+"");
                if (percent == 100){
                    return BARCOLOR_1;
                }else if(percent > 79){
                    return BARCOLOR_2;
                }else if(percent > 49){
                    return BARCOLOR_3;
                }else if(percent > 19){
                    return BARCOLOR_4;
                }else{
                    return BARCOLOR_5;
                }
       
            }
           
            });

            ChartPanel ps = new ChartPanel(chart);
            ps.setMouseZoomable(false);
            showGraph.add(ps);
            ps.setSize(showGraph.getWidth(), showGraph.getHeight());
            ps.setVisible(true);

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "showGraphPie");
        }
        
        

    }
    
    public void showGraphPie(){
        
        //DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        try {
            String query = "SELECT med_name , med_remain as 'Critical Level', med_qty_ordered as 'Medicine Remaining' FROM med_table";
            JDBCCategoryDataset dataset  = new JDBCCategoryDataset(SQLConnection.ConnDB(), query);

            //System.out.println(dataset.getRowKey(0));System.out.println(dataset.getColumnKey(2));
            //System.out.println(2000-dataset.getValue(0, 0).intValue());
            //ArrayList<Integer> med_percent = new ArrayList<Integer>();
            int temp;
            for (int j = 0; j < dataset.getColumnKeys().size(); j++) {
                //med_percent.add(dataset.getValue(0, j).intValue()*100/dataset.getValue(1,j).intValue());
                //dataset.setValue(dataset.getValue(1,j).intValue()-dataset.getValue(0, j).intValue(), dataset.getRowKey(1), dataset.getColumnKey(j));
                dataset.setValue(dataset.getValue(1,j).intValue()*.10, dataset.getRowKey(1), dataset.getColumnKey(j));  //critical point = med_qty__ordered*.10 (1,j)
                
                if (dataset.getValue(1,j).intValue()<dataset.getValue(0,j).intValue()) {
                dataset.setValue(dataset.getValue(0,j).intValue()-dataset.getValue(1,j).intValue(), dataset.getRowKey(0), dataset.getColumnKey(j));    
                } else {
                dataset.setValue(dataset.getValue(0,j).intValue(),dataset.getRowKey(1), dataset.getColumnKey(j));
                dataset.setValue(0,dataset.getRowKey(0), dataset.getColumnKey(j));   
                }
                //Switch two values for printing[med_remain(0,j) and med_qty__ordered(1,j)]
                temp = dataset.getValue(0, j).intValue(); 
                dataset.setValue(dataset.getValue(1,j).intValue(), dataset.getRowKey(0), dataset.getColumnKey(j));
                dataset.setValue(temp, dataset.getRowKey(1), dataset.getColumnKey(j));
//                dataset.setValue(dataset.getValue(1,j).intValue()+dataset.getValue(0,j).intValue(), dataset.getRowKey(0), dataset.getColumnKey(j));
//                dataset.setValue(dataset.getValue(0,j).intValue()-dataset.getValue(1,j).intValue(), dataset.getRowKey(1), dataset.getColumnKey(j));
//                dataset.setValue(dataset.getValue(1,j).intValue()-dataset.getValue(0,j).intValue(), dataset.getRowKey(0), dataset.getColumnKey(j));
//            if (dataset.getValue(1,j).intValue()<0) {
//                    dataset.setValue(0,dataset.getRowKey(0), dataset.getColumnKey(j));
//                    
//                }
            }
            
            //JScrollBar scroller;
            JFreeChart chart = ChartFactory.createStackedBarChart3D("", "","", dataset, PlotOrientation.VERTICAL,
                false, false, false);
            chart.setBackgroundPaint(Color.WHITE); // Set the background colour of the chart
            chart.getTitle().setPaint(Color.WHITE); // Adjust the colour of the title
            chart.setBorderPaint(Color.BLUE);
            
            //scroller = new JScrollBar(JScrollBar.HORIZONTAL, 0, 10, 0, 50);
            //scroller.getModel().addChangeListener(this);
            

            
            
            
     
            
            
            CategoryPlot p = chart.getCategoryPlot(); // Get the Plot object for a bar graph
            p.setBackgroundPaint(Color.WHITE); // Modify the plot background
            p.setRangeGridlinePaint(Color.BLUE);

            
            
             
            p.setRenderer(new StackedBarRenderer3D() {
            private final Color BARCOLOR_1 = new Color(52, 152, 219);
            private final Color BARCOLOR_2 = new Color(39, 174, 96);
            private final Color BARCOLOR_3 = new Color(241, 196, 15);
            private final Color BARCOLOR_4 = new Color(230, 126, 34);
            private final Color BARCOLOR_5 = new Color(241, 106, 106);
//            private double percent;
            @Override
            public Paint getItemPaint(int row, int column) {
                
                if (getPlot().getDataset().getValue(row, column).doubleValue()==0) {
                    return BARCOLOR_5;
                }
                if (row==1) {
                if (getPlot().getDataset().getValue(row, column).doubleValue()<getPlot().getDataset().getValue(row-1, column).doubleValue()) {
                return BARCOLOR_5;     
                }
                  return BARCOLOR_1;  
                }
//                percent = getPlot().getDataset().getValue(row, column).doubleValue()*100/(getPlot().getDataset().getValue(row+1, column).doubleValue()
//                        +getPlot().getDataset().getValue(row, column).doubleValue());
//                System.out.println(percent+"");
//                if (percent == 100){
//                    return BARCOLOR_1;
//                }else if(percent > 79){
//                    return BARCOLOR_2;
//                }else if(percent > 49){
//                    return BARCOLOR_3;
//                }else if(percent > 19){
//                    return BARCOLOR_4;
//                }else{
//                    return BARCOLOR_5;
//                }
                
                return BARCOLOR_5;
       
            }
           
        });
            p.getRenderer().setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator() {
                private int total_remaining;
                private int total_ordered;
              
                @Override
                public String generateLabel(CategoryDataset dataset, int row, int column) {
                    total_remaining = p.getDataset().getValue(row, column).intValue();
                    total_ordered = total_remaining;
                    

                    
                    if (row==1) {
                            total_ordered = total_remaining + p.getDataset().getValue(row-1, column).intValue();
                            
                            return total_ordered+"";
                    }
                    
                    
                    return total_remaining+"";
                }
                   
            });
            p.getRenderer().setBaseItemLabelsVisible(true);
            
//            ((BarRenderer)p.getRenderer()).setBarPainter(new StandardBarPainter());
//            BarRenderer r = (BarRenderer)chart.getCategoryPlot().getRenderer();
//            Color barColor1 = new Color(39, 174, 96);
//            Color barColor2 = new Color(52, 152, 219); 
//          
//            r.setSeriesPaint(0, barColor1);
//            r.setSeriesPaint(1, barColor2);
            

            ChartPanel ps = new ChartPanel(chart);
            ps.setMouseZoomable(false);
            ps.setSize(showPieGraph.getWidth(), showPieGraph.getHeight());
            ps.setVisible(true);
            showPieGraph.add(ps);
            
            
            
                
            

            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"error");

            
        }

    }
    
  
    
    public void updateTable1(){
        
        DefaultTableModel model = (DefaultTableModel)tbl_export.getModel();
        model.getDataVector().removeAllElements();
        tbl_export.repaint();

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl";
            rs = st.executeQuery(query);

            while (rs.next()){
                String id_no = rs.getString("id_no");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String programa = rs.getString("program");
                if (programa.isEmpty()){
                    programa = "   N/A   ";
                }
                
                String yearlevel = rs.getString("year_level");
                if (yearlevel.isEmpty()){
                    yearlevel = "   N/A   ";
                }
                Date dob = rs.getDate("bday");
                Calendar today = Calendar.getInstance();
                Calendar dateofBirth = Calendar.getInstance();
                dateofBirth.setTime(dob);
                
                int x = today.get(Calendar.YEAR) - dateofBirth.get(Calendar.YEAR);
                
                if (today.get(Calendar.MONTH) < dateofBirth.get(Calendar.MONTH) || (today.get(Calendar.MONTH)==dateofBirth.get(Calendar.MONTH)&& today.get(Calendar.DAY_OF_MONTH) < dateofBirth.get(Calendar.DAY_OF_MONTH))){
                    x--;
                }
                
                String get_gender = rs.getString("gender");
                String add = rs.getString("brgy");
                String add2 = rs.getString("city");
                String up = WordUtils.capitalizeFully(add);
                String up2 = WordUtils.capitalizeFully(add2);
                
                String address = up+", "+up2;
                
                String contact = rs.getString("contact");
                if (contact.isEmpty()){
                    contact = "  N/A  ";
                }
                
                
                        
                String capfname = WordUtils.capitalizeFully(firstname);
                String capmname = WordUtils.capitalizeFully(middlename);
                String mid = WordUtils.initials(capmname);
                String caplname = WordUtils.capitalizeFully(lastname);
                
                model.addRow(new Object[]{id_no, capfname+" "+mid+" "+caplname, programa, yearlevel, x, get_gender,address, contact});  
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "hello");
        }

    }
    
    
    public void update_program_table(){
        
        DefaultTableModel model = (DefaultTableModel)program_table.getModel();
        model.setRowCount(0);

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM course";
            rs = st.executeQuery(query);

            while (rs.next()){
                String course_id = rs.getString("course_id");
                String course_name = rs.getString("course_name");
                String course_desc = rs.getString("course_desc");
                String type = rs.getString("college");
                String combo = "College";
                if (type.equals("0")){
                    combo = "Senior High";
                }
                String year = rs.getString("years");
                
                model.addRow(new Object[]{course_id, course_name, course_desc, combo, year});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    public void countCourse(){

        try {
            st = conn.createStatement();
            String query = "SELECT COUNT(course_id) as a FROM course;";
            rs = st.executeQuery(query);
            
            while (rs.next()){
                String count = rs.getString("a");
                int countFinal = Integer.parseInt(count)+1;
                txt_courseid.setText(Integer.toString(countFinal));
            }
            
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "hello");
        }

    }
    
    
    
    public void sortingProgram(){
        
        DefaultTableModel model = (DefaultTableModel)tbl_export.getModel();
        model.getDataVector().removeAllElements();
        tbl_export.repaint();

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl WHERE program = '"+program.getSelectedItem()+"';";
            rs = st.executeQuery(query);

            while (rs.next()){
                String id_no = rs.getString("id_no");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String programa = rs.getString("program");
                
                String yearlevel = rs.getString("year_level");
                Date dob = rs.getDate("bday");
                Calendar today = Calendar.getInstance();
                Calendar dateofBirth = Calendar.getInstance();
                dateofBirth.setTime(dob);
                
                int x = today.get(Calendar.YEAR) - dateofBirth.get(Calendar.YEAR);
                
                if (today.get(Calendar.MONTH) < dateofBirth.get(Calendar.MONTH) || (today.get(Calendar.MONTH)==dateofBirth.get(Calendar.MONTH)&& today.get(Calendar.DAY_OF_MONTH) < dateofBirth.get(Calendar.DAY_OF_MONTH))){
                    x--;
                }
                
                String get_gender = rs.getString("gender");
                String add = rs.getString("brgy");
                String add2 = rs.getString("city");
                String up = WordUtils.capitalizeFully(add);
                String up2 = WordUtils.capitalizeFully(add2);
                
                String address = up+", "+up2;
                
                String contact = rs.getString("contact");
                if (contact.isEmpty()){
                    contact = "  N/A  ";
                }
                
                
                        
                String capfname = WordUtils.capitalizeFully(firstname);
                String capmname = WordUtils.capitalizeFully(middlename);
                String mid = WordUtils.initials(capmname);
                String caplname = WordUtils.capitalizeFully(lastname);
                
                model.addRow(new Object[]{id_no, capfname+" "+mid+" "+caplname, programa, yearlevel, x, get_gender,address, contact});      
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "hello");
        }

    }
    
    public void update_table(){
        
        DefaultTableModel model = (DefaultTableModel)update_table.getModel();
        model.getDataVector().removeAllElements();
        update_table.repaint();

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM patient_tbl";
            rs = st.executeQuery(query);

            while (rs.next()){
                String id_no = rs.getString("id_no");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String programa = rs.getString("program");
                String occupation = rs.getString("occupation");
                
                String capfname = WordUtils.capitalizeFully(firstname);
                String capmname = WordUtils.capitalizeFully(middlename);
                String caplname = WordUtils.capitalizeFully(lastname);
                
                model.addRow(new Object[]{id_no, capfname+" "+capmname+" "+caplname, programa, occupation});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "hello");
        }

    }
    
    public void showUserlist(){
        //userlist
        DefaultTableModel model = (DefaultTableModel)userlist.getModel();
        model.getDataVector().removeAllElements();
        userlist.repaint();

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM nurse_table INNER JOIN patient_tbl ON nurse_table.user_ID = patient_tbl.id_no";
            rs = st.executeQuery(query);

            while (rs.next()){
                String id_no = rs.getString("user_ID");
                String lastname = rs.getString("lastname");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String occupation = rs.getString("occupation");
                
                String userName = rs.getString("Username");
                
                String capfname = WordUtils.capitalizeFully(firstname);
                String capmname = WordUtils.capitalizeFully(middlename);
                String caplname = WordUtils.capitalizeFully(lastname);
                
                model.addRow(new Object[]{id_no, capfname+" "+capmname+" "+caplname, occupation, userName});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "showUserlist");
        }

    }
    
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            String fname = file.getAbsolutePath();
            txtPath.setText(fname);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void tgl_famActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_famActionPerformed
        // TODO add your handling code here:
        update_program_table();
        countCourse();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "card3");
        txt_prog.setText("");
        txt_years_complete.setText("");
        txt_desc.setText("");
    }//GEN-LAST:event_tgl_famActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        if (program.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Please Select a Program");  
        }else{
            try {
                JasperDesign jd = JRXmlLoader.load("C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\PatientList.jrxml");

                String ci = "SELECT * FROM patient_tbl WHERE program = '"+program.getSelectedItem()+"';";

                JRDesignQuery newQuery = new JRDesignQuery();
                newQuery.setText(ci);
                jd.setQuery(newQuery);
                JasperReport jr = JasperCompileManager.compileReport(jd);
                JasperPrint jp=JasperFillManager.fillReport(jr, null, conn);

                JasperExportManager.exportReportToPdfFile(jp,"PatientList.pdf");

                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("PatientList.pdf");
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            // no application registered for PDFs
                        }
                    }


            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        if (program.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Please Select a Program");  
        }else{
            try {
                ExcelExporter exp = new ExcelExporter();
                exp.exportTable(tbl_export, new File ("C:/Users/Benjie/Desktop/'"+program.getSelectedItem()+"'.xls"));
                JOptionPane.showMessageDialog(this, "You have sucsessfully export the data to Excel, Please check the file on the computer");  
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed
    
    private void programItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_programItemStateChanged
        if (program.getSelectedIndex()==0){
            updateTable1();      
        }else{
            sortingProgram();
        }
    }//GEN-LAST:event_programItemStateChanged

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // TODO add your handling code here:
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String message = "Are you sure you want to update the data on the database ? To proceed click yes.";
        int dialogResult = JOptionPane.showConfirmDialog (null,message ,"Confirmation",dialogButton);
        if(dialogResult == 0){
            int loaded = 0;
            int skipped = 0;

            if ("".equals(txtPath1.getText())) {
                JOptionPane.showMessageDialog(null, "Please select a path for your CSV.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "The specified file is not found.", "Not found.", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null){
                        if (skip == 1) { ///skip for header in the csv
                            skip++;
                            continue;
                        }
                        String[] value = line.split(",", 21);//Separator
                        try {

                            conn.setAutoCommit(false);

                            st = conn.createStatement();
                            String updateQuery = "UPDATE patient_tbl SET "
                            //+ "id_no =  '"+value[0]+"'"
                            + "RFID =  '"+value[1]+"'"
                            + ",lastname = '"+value[2]+"'"
                            + ",firstname = '"+value[3]+"'"
                            + ",middlename = '"+value[4]+"'"
                            + ",program = '"+value[5]+"'"
                            + ",year_level = '"+value[6]+"'"
                            + ",student_type = '"+value[7]+"'"
                            + ",gender = '"+value[8]+"'"
                            + ",status = '"+value[9]+"'"
                            + ",nationality = '"+value[10]+"'"
                            + ",religion = '"+value[11]+"'"
                            + ",bday = '"+value[12]+"'"
                            + ",place_birth = '"+value[13]+"'"
                            + ",occupation = '"+value[14]+"'"
                            + ",unit_no = '"+value[15]+"'"
                            + ",house_building = '"+value[16]+"'"
                            + ",street = '"+value[17]+"'"
                            + ",brgy = '"+value[18]+"'"
                            + ",city = '"+value[19]+"'"
                            + ",contact = '"+value[20]+"'"
                            + ",insertedOn = '"+nowna+"'"
                            + ",user_id = '"+user_ID+"'"
                            + "WHERE id_no = '"+value[0]+"';";

                            st.addBatch(updateQuery);

                            int[] count = st.executeBatch();
                            conn.commit();
                            loaded++;

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error in Updating using CSV File");
                            e.printStackTrace();
                        }

                    }
                    br.close();
                    //return;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //return false;
                }

                JOptionPane.showMessageDialog(this, "Youre Patient Information has been updated Successfully");
                JOptionPane.showMessageDialog(this, "Number of items upadted: " + loaded);
                System.out.println("Number of items skipped: " + skipped);
                System.out.println("Number of items loaded: " + loaded);
                update_table();
                txtPath1.setText("");
            }
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            String fname = file.getAbsolutePath();
            txtPath1.setText(fname);
        }
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void tgl_problemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_problemActionPerformed
        // TODO add your handling code here:
        update_table();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "card13");
    }//GEN-LAST:event_tgl_problemActionPerformed

    private void tgl_bmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_bmiActionPerformed
        // TODO add your handling code here:
        showUserlist();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "card4");
    }//GEN-LAST:event_tgl_bmiActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        new frm_user_select().setVisible(true);
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        
        
        if(txt_user_ID.getText().equals("") || nurse_name.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please select nurse first before generation ");
        }else{
            setEmail();
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i<5; i++){
                sb.append(alphabet.charAt(r.nextInt(N)));
            }
            String randompass = sb.toString();
            passtxt.setText(randompass);
            nursePass = randompass;
            generatedUser();
            
        }
    }//GEN-LAST:event_jButton17ActionPerformed
    private void generatedUser(){
        try{
            st = conn.createStatement();
            String query = "SELECT COUNT(user_ID) as a FROM nurse_table;";//count user
            
            rs = st.executeQuery(query);
            if (rs.last() == false){
                usertxt.setText("NURSE-1");
                nurseUser = "NURSE-1";
            }else{
                String count =  rs.getString("a");
                System.out.println(count);
                int ord = Integer.parseInt(count)+1;
                usertxt.setText("NURSE-"+ord);   
                nurseUser = "NURSE-"+ord;
            }      
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void setEmail(){
        
        System.out.print("nag run ka ba?");
        try {
            
            st = conn.createStatement();
            String email = "SELECT * FROM patient_tbl WHERE id_no = '"+txt_user_ID.getText()+"';";
            rs = st.executeQuery(email);
            
            while (rs.next()){
                String p_lname = rs.getString("lastname");
                String p_fname = rs.getString("firstname");


                String remove_replace_space_fname = p_fname.replaceAll(" ", "."); //removing space on firstname and replace space to dot
                String replace_enye_fname = remove_replace_space_fname.replaceAll("ñ", "n"); //replacing ñ on firstname and replace space to n
                //System.out.println(replace_enye_fname);

                String remove_space_lnale = p_lname.replaceAll(" ", ""); //removing space on lastname
                String replace_enye_lname = remove_space_lnale.replaceAll("ñ", "n"); //replacing ñ on lastname and replace space to n
                //System.out.print(replace_enye_lname);

                nurseEmail = replace_enye_fname.toLowerCase()+"."+replace_enye_lname.toLowerCase()+"@globalcity.sti.edu";
                System.out.print(nurseEmail);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        //setEmail();
        String hello = txt_user_ID.getText();
        System.out.println(hello);
        if( hello.isEmpty() || nurse_name.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select nurse first before generation ");
        }else{
            try{
                st = conn.createStatement();
                String insertUser = "INSERT INTO nurse_table SET"
                        + " user_ID = '"+hello+"',"
                        + " Username = '"+usertxt.getText()+"',"
                        + " Password = '"+passtxt.getText()+"',"
                        + " email_add = '"+nurseEmail+"',"
                        + " Active = '1';";
                st.executeUpdate(insertUser);
                
                
                sendpwtoemail();
                JOptionPane.showMessageDialog(this, "Please advise the user to check his/her STI email for the Username and Password!");
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        }
        
        txt_user_ID.setText("");
        nurse_name.setText("");
        passtxt.setText("");
        usertxt.setText("");
        showUserlist();
        
        
        
    }//GEN-LAST:event_jButton18ActionPerformed
    
    
   
    
    
    public void sendpwtoemail(){
        
        String emailsubject = "ACCOUNT UNSERNAME AND PASSWORD";
        String bodymsg = "Hi "+ nurse_name.getText() +",\n\n      This is your Username :" +" "+ nurseUser +"    \n  This is your Password :" +" "+ nursePass +"    \n\n\n\n\n\n Best Regards, \n\nBenjie D. Pascual\nADMINISTRATOR";
        
        final String username = "clinic.stiglobal@gmail.com";
        final String pass = "Password1234@@";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session sesh = Session.getInstance(props,
            new javax.mail.Authenticator(){
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(username, pass);

                    //return sesh;

                }
            });
            try{
                Message msg = new MimeMessage(sesh);
                msg.setFrom(new InternetAddress("clinic.stiglobal@gmail.com"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(nurseEmail));
                msg.setSubject(emailsubject);
                msg.setText(bodymsg);

                Transport.send(msg);
                System.out.println("SUCCESS YES NAG SEND KA RIN");
                //JOptionPane.showMessageDialog(this, "NAG SEND NA");
            }catch(MessagingException e){
                throw new RuntimeException(e);
            }
    }
    
    
    
    private void tbl_monitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_monitorMouseClicked
        if (evt.getClickCount()==2){
            
            int x = tbl_monitor.getSelectedRow();
            String id = tbl_monitor.getValueAt(x, 0).toString();
            txt_search.selectAll();
            
            new Medication().setVisible(true);
            this.dispose();
            
            Medication.lbl_id.setText(id);
            
            
        }
    }//GEN-LAST:event_tbl_monitorMouseClicked

    private void HomeComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_HomeComponentAdded
       
    }//GEN-LAST:event_HomeComponentAdded

    private void HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HomeFocusGained
        
    }//GEN-LAST:event_HomeFocusGained

    private void tgl_secItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_secItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_secItemStateChanged

    private void tgl_secMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_secMouseEntered
        mouse_in(tgl_sec);
    }//GEN-LAST:event_tgl_secMouseEntered

    private void tgl_secMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_secMouseExited
        mouse_out(tgl_sec);
    }//GEN-LAST:event_tgl_secMouseExited

    private void tgl_secActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_secActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "security_account");
    }//GEN-LAST:event_tgl_secActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        String message = "Do you want to add this program?";
        int dialogResult = JOptionPane.showConfirmDialog(null,message ,"Confirmation",JOptionPane.YES_NO_OPTION);
        if(dialogResult == 0){
            insertProg();
            
            update_program_table();
            countCourse();
            
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tgl_seniorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_seniorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_seniorActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        search_profile();
    }//GEN-LAST:event_jButton19ActionPerformed

    private void tgl_seniorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_seniorItemStateChanged
        sheetness();
    }//GEN-LAST:event_tgl_seniorItemStateChanged

    private void tgl_collegeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_collegeItemStateChanged
        sheetness();
    }//GEN-LAST:event_tgl_collegeItemStateChanged

    private void txt_years_completeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_years_completeKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_txt_years_completeKeyTyped

    private void EmployeeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EmployeeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_EmployeeItemStateChanged

    private void EmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmployeeActionPerformed

    private void cmb_completeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_completeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_completeItemStateChanged

    private void cmb_incItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_incItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_incItemStateChanged

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try{
            String hrmba = hrm_tm.getSelectedItem().toString();
            String kaba = "";
            if (hrmba.equalsIgnoreCase("bshrm")){
                kaba = " AND a.program = 'BSHRM'";
            }else if(hrmba.equalsIgnoreCase("bstm")){
                kaba = " AND a.program = 'BSTM'";
            }else{
                kaba = " AND (a.program = 'BSHRM' || a.program = 'BSTM')";
            }
            DefaultTableModel mod = (DefaultTableModel)tbl_vaccine.getModel();
            mod.setRowCount(0);

            String q = "";
            if (cmb_complete.isSelected()){
                q = "completed";
            }else if (cmb_inc.isSelected()){
                q = "ongoing";
            }
            Vector x = new Vector();
            Connection con = SQLConnection.ConnDB();
            PreparedStatement pst;
            ResultSet r;
            
            String sql = "SELECT * FROM patient_tbl a"
                    + " inner join vacc_hepa_b c on a.id_no = c.id_no"
                    + " where c.status = '"+q+"' "+kaba+"";
                   
            pst = con.prepareStatement(sql);
            r = pst.executeQuery();
            while (r.next()){
                
                x.clear();
                System.out.println(x);
                
                String id = r.getString("a.id_no");
                String lastname = r.getString("a.lastname");
                String upperlast = WordUtils.capitalizeFully(lastname);
                String firstname = r.getString("a.firstname");
                String upperfirst = WordUtils.capitalizeFully(firstname);
                String middlename = r.getString("a.middlename");
                String uppermid = WordUtils.capitalizeFully(middlename);
                String programa = r.getString("a.program");
                x.add(id);
                x.add(upperlast);
                x.add(upperfirst);
                x.add(uppermid);
                x.add(programa);
                
                
                
                //////// PUNYETANG FLU
                String flow = "  -  ";
                String flu = "SELECT * FROM med_dspnsd WHERE id_no = '"+id+"' AND med_id = '708'";
                pstmt = conn.prepareStatement(flu);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    flow = rs.getString("date_given");
                    if (flow.equals("null")){
                        flow = "  -  ";
                    }
                    x.add(flow);
                    
                }else{
                    x.add(flow);
                }
                
                
                String screenresult = "";
                /// PUNYETANG RESULT
                String res = "SELECT * FROM vacc_hepa_b WHERE id_no = '"+id+"'";
                pstmt = conn.prepareStatement(res);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    String asd = rs.getString("result");
                    screenresult = asd;
                }
                
                ////PUNYETANG SCREENING
                
                String screen = "SELECT * FROM med_dspnsd WHERE id_no = '"+id+"' AND med_id = '710'";
                pstmt = conn.prepareStatement(screen);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    String date_given = rs.getString("date_given");
                    if (date_given.equals("null")){
                        date_given = "  -  ";
                       
                    }
                     
                    screenresult = screenresult + " :"+date_given;
                    x.add(screenresult);                    
                }
                

                String dosages = "SELECT * FROM med_dspnsd WHERE id_no = '"+id+"' AND med_id = '709' ORDER BY date_given ASC;";
                pstmt = conn.prepareStatement(dosages);
                rs = pstmt.executeQuery();
                while (rs.next()){
                    String date_given = rs.getString("date_given");
                    if (date_given.equals("null")){
                        date_given = "  -  ";
                    }
                    x.add(date_given);
                    
                }
            
            mod.addRow(x.toArray());

            } 
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void tgl_vacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_vacActionPerformed
        CardLayout c1 = (CardLayout)profile_layout.getLayout();
        c1.show(profile_layout, "card3");
    }//GEN-LAST:event_tgl_vacActionPerformed

    private void hrm_tmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_hrm_tmItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_hrm_tmItemStateChanged

    private void hrm_tmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrm_tmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrm_tmActionPerformed

    private void tgl_incActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_incActionPerformed
        search_inc();
        CardLayout c1 = (CardLayout)profile_layout.getLayout();
        c1.show(profile_layout,"card4");
        
        
    }//GEN-LAST:event_tgl_incActionPerformed

    private void txt_txtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_txtKeyTyped
        search_inc();
    }//GEN-LAST:event_txt_txtKeyTyped

    private void tbl_incMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_incMouseClicked
        if (evt.getClickCount()==2){
            int i = tbl_inc.getSelectedRow();
            String id = tbl_inc.getValueAt(i,0).toString();
            String name = tbl_inc.getValueAt(i,1).toString();

            new add_rfid().setVisible(true);
            add_rfid.txt_id.setText(id);
            add_rfid.txt_fname.setText(name);
            
        }
    }//GEN-LAST:event_tbl_incMouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        new new_Registration().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void tgl_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_listActionPerformed
        CardLayout c1 = (CardLayout)profile_layout.getLayout();
        c1.show(profile_layout, "card6");
    }//GEN-LAST:event_tgl_listActionPerformed

    private void jButton21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseEntered
        
    }//GEN-LAST:event_jButton21MouseEntered

    private void jButton21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton21MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21MouseExited

    private void tgl_manActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_manActionPerformed
        // TODO add your handling code here:
        populateCmb();
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "manage_account");
    }//GEN-LAST:event_tgl_manActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
        if(a1.getText().equals("") || a2.getText().equals("") || a3.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please complete the information", "Error Message", JOptionPane.ERROR_MESSAGE);
        }else{
            int dialogButton = JOptionPane.YES_NO_OPTION;
            String message = "Plese click yes if you want to save the changes you made ?";
            int dialogResult = JOptionPane.showConfirmDialog (null,message ,"Confirmation",dialogButton);
            if(dialogResult == 0){ 
                updateSecQues();
                a1.setText("");
                a2.setText("");
                a3.setText("");
        
                JOptionPane.showMessageDialog(this, "You have sucsesfully Updated a security question on your account! ");   
            } 
        }

        
    }//GEN-LAST:event_jButton22ActionPerformed
    
    public void populateCmb(){
        try {
            
            st = conn.createStatement();
            String getQuestion = "SELECT * FROM securityquestions";
            rs = st.executeQuery(getQuestion);
            
            while (rs.next()){
                String q = rs.getString("question");
                q1.addItem(q);
                q2.addItem(q);
                q3.addItem(q);
                //q1.addItem(rs.getString("question"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        
        if(oldpass.getText().equals("") || pass1.getText().equals("") || pass2.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please complete the information", "Error Message", JOptionPane.ERROR_MESSAGE);
        }else{
            getOldPass();
            //System.out.println(oldpass.getText()+":"+oldestPass);
            if(oldpass.getText().equals(oldestPass)){
                //System.out.println(oldpass+":"+oldestPass);
                if(pass1.getText().equals(pass2.getText())){
                    updateOldPass();
                    oldpass.setText("");
                    pass1.setText("");
                    pass2.setText("");
                    JOptionPane.showMessageDialog(null, "You have sucsessfully change your password", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "You have entered mis-match new password", "Error Message", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "You have entered invalid Old Password", "Error Message", JOptionPane.ERROR_MESSAGE);
            }
            
        }
//        oldpass.setText("");
//        pass1.setText("");
//        pass2.setText("");
        
    }//GEN-LAST:event_jButton24ActionPerformed
    public void showCritMed(){
        
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_criMed.getModel();
            mod.setRowCount(0);
            String sql = "SELECT * FROM med_table";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String medName = rs.getString("med_name");
                String medRem = rs.getString("med_remain");
                String medTotal = rs.getString("med_qty_ordered");
                
                
                double qntyRemaim = Double.parseDouble(medRem);
                double qntyTotalOrdered = Double.parseDouble(medTotal);
                

                BigDecimal bd = new BigDecimal(qntyRemaim*100/qntyTotalOrdered);//get the remain percentage

                bd = bd.setScale(2, RoundingMode.HALF_UP);  
                double percentMed =  bd.doubleValue();
                
                if(percentMed<10){
                    mod.addRow(new Object[] {medName, medRem, percentMed});
                }
                
                
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }    
        
    }
    
    public void getOldPass(){
        try {
            st = conn.createStatement();
            String getOldPass = "SELECT * FROM nurse_table WHERE user_ID = '"+user_ID+"'";
            rs = st.executeQuery(getOldPass);
            
            while (rs.next()){
                String oldPass = rs.getString("Password");
                oldestPass = oldPass;
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void updateOldPass(){
        try {
            String updateOldPass = "UPDATE nurse_table SET"
                   + " Password = '"+pass2.getText()+"'"
                   + " WHERE user_ID = '"+user_ID+"'";
           pstmt = conn.prepareStatement(updateOldPass);
           pstmt.executeUpdate();
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    
    public void updateSecQues(){
        try {
            String updateOldPass = "UPDATE nurse_table SET"
                + " question1 = '"+q1.getSelectedItem().toString()+"',"
                + " answer1 = '"+a1.getText()+"',"
                + " question2 = '"+q2.getSelectedItem().toString()+"',"
                + " answer2 = '"+a2.getText()+"',"
                + " question3 = '"+q3.getSelectedItem()+"',"
                + " answer3 = '"+a3.getText()+"'"
                + " WHERE user_ID = '"+user_ID+"'";
           pstmt = conn.prepareStatement(updateOldPass);
           pstmt.executeUpdate();
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }
    
    
    
    private void q1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_q1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_q1ItemStateChanged

    private void q2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_q2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_q2ItemStateChanged

    private void q3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_q3ItemStateChanged
        // TODO add your handling code here:            
    }//GEN-LAST:event_q3ItemStateChanged

    private void q1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_q1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_q1InputMethodTextChanged

    private void medicine_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicine_tableMouseClicked
        // TODO add your handling code here:
        showGraph();
    }//GEN-LAST:event_medicine_tableMouseClicked

    private void tgl_basic2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_basic2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_basic2ItemStateChanged

    private void tgl_basic2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basic2MouseEntered
        mouse_in(tgl_basic2);
    }//GEN-LAST:event_tgl_basic2MouseEntered

    private void tgl_basic2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_basic2MouseExited
        mouse_out(tgl_basic2);
    }//GEN-LAST:event_tgl_basic2MouseExited

    private void tgl_basic2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_basic2ActionPerformed
        // TODO add your handling code here:
        showGraphPie();
        CardLayout c1 = (CardLayout)(Inv_Layout.getLayout());
        c1.show(Inv_Layout, "graph");
    }//GEN-LAST:event_tgl_basic2ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_specific.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\PUTALAST.jrxml";
        
        int x = mod.getRowCount();
        String nurseName = Firstname +" "+ Lastname;

        
        String selectedbtn = radio1.getText();;
        if (radio2.isSelected()) {
            selectedbtn = radio2.getText();
        }
        
       
        //String finalTitle = z+" -"+" From: "+get_from+" To: "+get_to;
        
        
        
        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{ 
             String z = cmb_specific.getSelectedItem().toString();
            String get_from = xyz.format(chooser_from.getDate());
            String get_to = xyz.format(chooser_to.getDate());
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("titleTo", z);
                params.put("illOrMed", selectedbtn);
                params.put("date", get_from + " - " + get_to);
                params.put("nurseName", nurseName);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
       
    }//GEN-LAST:event_jButton25ActionPerformed

    private void tgl_newill1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_newill1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newill1ItemStateChanged

    private void tgl_newill1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_newill1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newill1MouseEntered

    private void tgl_newill1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_newill1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_newill1MouseExited

    private void tgl_newill1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_newill1ActionPerformed
        new add_generic().setVisible(true);
    }//GEN-LAST:event_tgl_newill1ActionPerformed

    private void tgl_contact2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_contact2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_contact2ItemStateChanged

    private void tgl_contact2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contact2MouseEntered
        mouse_in(tgl_contact2);
    }//GEN-LAST:event_tgl_contact2MouseEntered

    private void tgl_contact2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_contact2MouseExited
        mouse_out(tgl_contact2);
    }//GEN-LAST:event_tgl_contact2MouseExited

    private void tgl_contact2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_contact2ActionPerformed
        // TODO add your handling code here:
        countSupplier();
        update_supplierTable();
        CardLayout c1 = (CardLayout)(Inv_Layout.getLayout());
        c1.show(Inv_Layout, "sup");
    }//GEN-LAST:event_tgl_contact2ActionPerformed

     public void countSupplier(){

        try {
            st = conn.createStatement();
            String query = "SELECT COUNT(sup_id) as a FROM supplier;";
            rs = st.executeQuery(query);
            
            while (rs.next()){
                String count = rs.getString("a");
                int countFinal = Integer.parseInt(count)+1;
                txt_supid.setText(Integer.toString(countFinal));
            }
            
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "hello");
        }

    }
    
    public void update_supplierTable(){
        
        DefaultTableModel model = (DefaultTableModel)supplierTable.getModel();
        model.setRowCount(0);

        try {
            st = conn.createStatement();
            String query = "SELECT * FROM supplier";
            rs = st.executeQuery(query);

            while (rs.next()){
                String sup_id = rs.getString("sup_id");
                String sup_name = rs.getString("sup_name");
                String sup_add = rs.getString("sup_add");
                String sup_email = rs.getString("sup_email");
                String sup_phone = rs.getString("sup_phone");
                String sup_cp = rs.getString("sup_cp");   
                model.addRow(new Object[]{sup_id, sup_name, sup_add, sup_email, sup_phone, sup_cp});
                //getTable();
            }
            //System.out.println("SUCSESS GAGO");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void insertSupplier(){
         
        if(sup_name.getText().equals("") || sup_add.getText().equals("") || sup_email.getText().equals("") || sup_ll.getText().equals("") || sup_cp.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Please complete the details ");
        }else{
            try{
                st = conn.createStatement();
                String insertSupplier = "INSERT INTO supplier SET"
                        + " sup_id = '"+txt_supid.getText()+"',"
                        + " sup_name = '"+sup_name.getText()+"',"
                        + " sup_add = '"+sup_add.getText()+"',"
                        + " sup_email = '"+sup_email.getText()+"',"
                        + " sup_phone = '"+sup_ll.getText()+"',"
                        + " sup_cp = '"+sup_cp.getText()+"'";       
                st.executeUpdate(insertSupplier);
                JOptionPane.showMessageDialog(this, "You have sucsessfully added a new Supplier!");

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e, "Hello World", JOptionPane.INFORMATION_MESSAGE);
                e.printStackTrace();
            }
        }
     }
    private void sup_addKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_addKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_addKeyTyped

    private void sup_nameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_nameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_nameKeyTyped

    private void txt_supidKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_supidKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_supidKeyTyped

    private void sup_cpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_cpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_cpKeyTyped

    private void sup_llKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_llKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_llKeyTyped

    private void sup_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_emailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_emailKeyTyped

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String message = "Plese click yes if you want to add a new supplier";
        int dialogResult = JOptionPane.showConfirmDialog (null,message ,"Confirmation",dialogButton);
        if(dialogResult == 0){
            insertSupplier();
            update_supplierTable();
            countSupplier();
            sup_name.setText("");
            sup_add.setText("");
            sup_email.setText("");
            sup_ll.setText("");
            sup_cp.setText("");  
        }
        
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        new frm_purchase().setVisible(true);
       
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btn_edit_purposeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_purposeMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_purposeMouseEntered

    private void tgl_report1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_report1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_report1ItemStateChanged

    private void tgl_report1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_report1MouseEntered
        mouse_in(tgl_report1);
    }//GEN-LAST:event_tgl_report1MouseEntered

    private void tgl_report1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_report1MouseExited
        mouse_out(tgl_report1);
    }//GEN-LAST:event_tgl_report1MouseExited

    private void tgl_report1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_report1ActionPerformed
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout, "card7");
        chooser_from_confine.setDate(null);
        chooser_to_confine.setDate(null);
        
        DefaultTableModel mod = (DefaultTableModel)tbl_confine.getModel();
        mod.setRowCount(0);
    }//GEN-LAST:event_tgl_report1ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        
        if (chooser_from_confine.getDate() == null || chooser_to_confine.getDate() == null){
            JOptionPane.showMessageDialog(null,"Input Necessary Fields",null,JOptionPane.WARNING_MESSAGE);
        }else{
            
            if (chooser_from_confine.getDate().after(chooser_to_confine.getDate()) || chooser_to_confine.getDate().before(chooser_from_confine.getDate())){
                JOptionPane.showMessageDialog(null,"Please Input Valid Dates");
                chooser_from_confine.setDate(null);
                chooser_to_confine.setDate(null);
            }else{
                
            
        
                try{
                    String a = abc.format(chooser_from_confine.getDate());
                    String b = abc.format(chooser_to_confine.getDate());

                    DefaultTableModel mod = (DefaultTableModel)tbl_confine.getModel();
                    mod.setRowCount(0);

                    String query = "SELECT *,DATE_FORMAT(a.date_given, '%c/%e/%Y') as datu, TIME_FORMAT(a.time, '%h:%i %p') AS la,TIME_FORMAT(TIMEDIFF(a.outtime, a.intime), '%h') as ax, TIME_FORMAT(TIMEDIFF(a.outtime, a.intime), '%i') as bx FROM med_dspnsd a INNER JOIN patient_tbl b ON a.id_no = b.id_no"
                            + " LEFT JOIN med_table c ON a.med_id = c.med_id"
                            + " INNER JOIN ill_table d ON a.ill_id = d.ill_id"
                            + " WHERE a.bedrest = '1' and (date_given between '"+a+"' and '"+b+"');";


                    pstmt = conn.prepareStatement(query);
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        String date = rs.getString("datu");
                        String time = rs.getString("la");
                        String datetime = date+"  "+time;
                        String id = rs.getString("a.id_no");
                        String lname = rs.getString("b.lastname");
                        String fname = rs.getString("b.firstname");
                        String mname = rs.getString("b.middlename");
                        String med = rs.getString("c.med_name");
                        
                        String ill = rs.getString("d.ill_name");
                        String hour = rs.getString("ax");
                        String min = rs.getString("bx");
                        String sha = hour+" hour & "+min+" minutes";
                        if (sha.contains("null")){
                            sha = "Ongoing";
                        }
                        
                        

                        String upperlast = WordUtils.capitalizeFully(lname);

                        String firstname = WordUtils.capitalizeFully(fname);
                        String upperfirst = WordUtils.initials(firstname);

                        String midname = WordUtils.initials(mname);
                        String uppermid = WordUtils.capitalizeFully(midname);
                        String lastnato = upperlast+", "+upperfirst+" "+uppermid+".";
                        mod.addRow(new Object[]{datetime,id,lastnato,ill,med,sha});

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void tbl_vaccineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_vaccineMouseClicked
        if (evt.getClickCount()==2){
            
            int x = tbl_vaccine.getSelectedRow();
            String id = tbl_vaccine.getValueAt(x, 0).toString();
            txt_search.selectAll();
            
            new Medication().setVisible(true);
            this.dispose();
            
            Medication.lbl_id.setText(id);
            
            
        }
    }//GEN-LAST:event_tbl_vaccineMouseClicked

    private void cmb_purposeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_purposeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_purposeActionPerformed

    private void chk_activeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chk_activeItemStateChanged
        
    }//GEN-LAST:event_chk_activeItemStateChanged

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        CardLayout c1 = (CardLayout)(Manage_Layout.getLayout());
        c1.show(Manage_Layout,"card4");
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deactivateActionPerformed
        
        try{
            int row = tbl_showmedicine.getSelectedRow();
            if (row==-1){
                JOptionPane.showMessageDialog(null, "Please Select Medicine to Activate / Deactivate");
            }else{
                String name = tbl_showmedicine.getValueAt(row, 0).toString();
                int x = 0;
                String ab = "Deactivated!";
                if (chk_active.isSelected()==true){
                    x = 0;
                }else{
                    x = 1;
                    ab = "Activated!";
                }

                String query = "UPDATE med_table SET"
                        + " isActive = '"+x+"'"
                        + " WHERE med_name = '"+name+"'";
                pstmt = conn.prepareStatement(query);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "'"+ab+"'");
                showmed();
            }
 
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_deactivateActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        CardLayout c1 = (CardLayout)Manage_Layout.getLayout();
                c1.show(Manage_Layout,"card2");
                txt_searchmed.setText("");
                cmb_avail.setSelectedIndex(0);
                cmb_purpose.setSelectedIndex(0);
                chk_active.setSelected(true);
    }//GEN-LAST:event_jButton27ActionPerformed

    private void txt_amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_amountKeyPressed
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
        
        
            
        
        
    }//GEN-LAST:event_txt_amountKeyPressed

    private void btn_disposeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_disposeActionPerformed
        try{
            int row = tbl_showmedicine.getSelectedRow();
            if (row==-1){
                JOptionPane.showMessageDialog(null, "Please Select Medicine to Dispose");
            }else{
                String name = tbl_showmedicine.getValueAt(row, 0).toString();
                
                String query = "SELECT * FROM med_table WHERE med_name = '"+name+"'";
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    String q = rs.getString("med_name");
                    String w = rs.getString("med_remain");
                    int e = rs.getInt("med_remain");
                    String r = rs.getString("med_id");
                    count = e;
                    lbl_medneym.setText(q);
                    lbl_current.setText(w);
                    lbl_med_id.setText(r);
                }
                CardLayout c1 = (CardLayout)Manage_Layout.getLayout();
                c1.show(Manage_Layout,"card8");
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_disposeActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        String o = txt_amount.getText();
        amount = Integer.parseInt(o);
        
        if (amount>count){
            JOptionPane.showMessageDialog(null, "Cannot dispose higher the actual count");
        }else{
        
            try{
                int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to dispose "+txt_amount.getText()+" to "+lbl_medneym.getText()+"?","CONFIRM",JOptionPane.YES_NO_OPTION);
                int op = count - amount;
                if (dialogresult == 0){

                    String query = "UPDATE med_table SET"
                            + " med_remain = '"+op+"'"
                            + " WHERE med_name = '"+lbl_medneym.getText()+"'";
                    pstmt = conn.prepareStatement(query);
                    pstmt.executeUpdate();

                    String sql = "INSERT INTO med_dspnsd SET"
                            + " date_given = '"+nowna+"',"
                            + " med_id = '"+lbl_med_id.getText()+"',"
                            + " amount = '"+amount+"',"
                            + " ill_id = '1000',"
                            + " id_no = '101',"
                            + " bedrest = '0',"
                            + " time = '"+hour+"',"
                            + " inTime = null,"
                            + " outTime = null,"
                            + " user_ID = '"+user_ID+"'";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Successfully Disposed");
                    CardLayout c1 = (CardLayout)Manage_Layout.getLayout();
                    c1.show(Manage_Layout,"card2");
                    txt_searchmed.setText("");
                    cmb_avail.setSelectedIndex(0);
                    cmb_purpose.setSelectedIndex(0);
                    chk_active.setSelected(true);

                    showmed();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void radio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio2ActionPerformed
        if (radio2.isSelected()==true){
            cmb_specific.removeAllItems();
            CBox_function(cmb_specific); //show all illnesses
            cmb_specific.addItem("ALL");
            cmb_specific.setSelectedItem("ALL");
        }
    }//GEN-LAST:event_radio2ActionPerformed

    private void radio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio1ActionPerformed
        if (radio1.isSelected()==true){
            cmb_specific.removeAllItems();
            CBox_function01(cmb_specific); //show all medicines
            cmb_specific.addItem("ALL");
            cmb_specific.setSelectedItem("ALL");
        }
    }//GEN-LAST:event_radio1ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        new show_comp().setVisible(true);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void printResultButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printResultButActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_confine.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\tempConfineReports.jrxml";
        
        int x = mod.getRowCount();
        String nurseName = Firstname +" "+ Lastname;

        
        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{
            String get_from = xyz.format(chooser_from_confine.getDate());
            String get_to = xyz.format(chooser_to_confine.getDate());
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("date", get_from + " - "+ get_to);
                params.put("nurseName", nurseName);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_printResultButActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) medicine_table.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\medList.jrxml";
        
        int x = mod.getRowCount();
        String nurseName = Firstname +" "+ Lastname;

        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{ 
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("aasdsad", "asdsad");
                params.put("nurseName", nurseName);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_monitor.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\patientLisv2.jrxml";
        
        int x = mod.getRowCount();
        
        String nurseName = Firstname +" "+ Lastname;
        String track = cmb_track.getSelectedItem().toString();
        String assess = cmb_assess.getSelectedItem().toString();
        String patientCat = tgl_college.getText();
        
        
        if (tgl_senior.isSelected()) {
            patientCat = tgl_senior.getText();
        }else if(Employee.isSelected()){ 
             patientCat = Employee.getText();
             track = "";
        
        }    
             
             
             
        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("patientCat", patientCat);
                params.put("track", track);
                params.put("assess", assess);
                params.put("nurseName", nurseName);
                
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_vaccine.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\vaccReport.jrxml";
        
        int x = mod.getRowCount();
        
        String selectedbtn = cmb_inc.getText();;
        if (cmb_complete.isSelected()) {
            selectedbtn = cmb_complete.getText();
        } 
        
        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{ 
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("titleIn",selectedbtn);
                System.out.println(selectedbtn);
                
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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


            } catch (JRException ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jButton32ActionPerformed

    private void order_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_order_tableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==2){
             //JOptionPane.showMessageDialog(this, "Please select onother this PO: is already RECEIVED");
            int row = order_table.getSelectedRow();
            //System.out.print(row);
            String status = (order_table.getModel().getValueAt(row, 7).toString());
            String po = (order_table.getModel().getValueAt(row, 0).toString());
            if("CANCELLED".equals(status)){ 
                JOptionPane.showMessageDialog(this, "Please select onother this PO: "+po+ " is already CANCELLED");
            }else if("RECEIVED".equals(status)){
                JOptionPane.showMessageDialog(this, "Please select onother this PO: "+po+ " is already RECEIVED");
                
            }else{
                Object[] options = {"RECEIVED PURCHASE ORDER",
                    "CANCEL PURCHASE ORDER",
                    "CANCEL"};
                int n = JOptionPane.showOptionDialog(null,
                "Please select select transaction on this "+po+ " to proceed !! ",
                "Choose Transaction",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
                if(n==0){
                    int dialogresult = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to received this PO: "+po+ ", Click Yes to proceed", "COnfirmation", JOptionPane.YES_OPTION);;
                        if (dialogresult == 0){
                            JOptionPane.showMessageDialog(this, "You have sucsessfully RECEIVED PO: "+po+ "Received");
                            try {
                                st = conn.createStatement();
                                String query = "SELECT * FROM med_table INNER JOIN med_orders ON"
                                        + " med_orders.med_id = med_table.med_id"
                                        + " INNER JOIN purchase_orders ON purchase_orders.po_id = med_orders.po_order WHERE purchase_orders.po_id = '"+po+"';";
                                rs = st.executeQuery(query);

                                while (rs.next()){
                                    //get the value in med_orders
                                    String med_id = rs.getString("med_id");
                                    //String po_order = rs.getString("po_order");
                                    String med_qnty = rs.getString("med_qnty");
                                    //get the value in med_table
                                    String med_remain = rs.getString("med_remain");
                                    //String med_qty_ordered = rs.getString("med_qty_ordered");
                                    //formulas
                                    int new_med_remain = Integer.parseInt(med_remain)+Integer.parseInt(med_qnty);
                                    //value for the updated med_remain in med_table
                                    //value for the updated med_qty_ordered = new_med_remain  
                                    System.out.println(new_med_remain);

                                    String updateMedQnty = "UPDATE med_table SET"
                                        + " med_remain = '"+new_med_remain+"',"
                                        + " med_qty_ordered = '"+new_med_remain+"'"
                                        + " WHERE med_id = '"+med_id+"'";

                                    pstmt = conn.prepareStatement(updateMedQnty);
                                    pstmt.executeUpdate();

                                    String updatePO = "UPDATE purchase_orders SET"
                                        + " po_recby = '"+user_ID+"',"
                                        + " po_recibdate = '"+nowna+"',"
                                        + " po_status = 'RECEIVED'"
                                        + " WHERE po_id = '"+po+"'";

                                    pstmt = conn.prepareStatement(updatePO);
                                    pstmt.executeUpdate();


                                }
                            } catch (SQLException e) {
                                e.printStackTrace();

                            }
                            updatePurchaseOrders(); 
                        }
                }else if(n==1){
                    int dialogresult = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to cancel this PO: "+po+ ", Click Yes to proceed", "COnfirmation", JOptionPane.YES_OPTION);;
                        if (dialogresult == 0){
                            
                            try{
                                String again = "UPDATE purchase_orders SET"
                                        + " po_recby = '"+user_ID+"',"
                                        + " po_recibdate = '"+nowna+"',"
                                        + " po_status = 'CANCELLED'"
                                        + " WHERE po_id = '"+po+"'";

                                pstmt = conn.prepareStatement(again);
                                pstmt.executeUpdate();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(this, "You have sucsessfully CANCELLED PO: "+po+ "Cancelled");
                            updatePurchaseOrders();
                        }
                }
            }
        }
        
    }//GEN-LAST:event_order_tableMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        updatePurchaseOrders();
        showCritMed();
        ShowPic();
    }//GEN-LAST:event_formWindowActivated

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        int row = order_table.getSelectedRowCount();
        if(row == 0){
            JOptionPane.showMessageDialog(this, "Please select purchase order you want to preview");
        }else{
            new frm_preview().setVisible(true);
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void tgl_listMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_listMouseEntered
        mouse_in(tgl_list);
    }//GEN-LAST:event_tgl_listMouseEntered

    private void tgl_vacMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_vacMouseEntered
        mouse_in(tgl_vac);
    }//GEN-LAST:event_tgl_vacMouseEntered

    private void tgl_incMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_incMouseEntered
        mouse_in(tgl_inc);
    }//GEN-LAST:event_tgl_incMouseEntered

    private void tgl_manMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_manMouseEntered
        mouse_in(tgl_man);
    }//GEN-LAST:event_tgl_manMouseEntered

    private void tgl_listMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_listMouseExited
        mouse_out(tgl_list);
    }//GEN-LAST:event_tgl_listMouseExited

    private void tgl_vacMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_vacMouseExited
        mouse_out(tgl_vac);
    }//GEN-LAST:event_tgl_vacMouseExited

    private void tgl_incMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_incMouseExited
        mouse_out(tgl_inc);
    }//GEN-LAST:event_tgl_incMouseExited

    private void txt_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyTyped
        char c = evt.getKeyChar();
        
        if (!(Character.isDigit(c) ||
            (c==KeyEvent.VK_BACKSPACE) ||
            (c==KeyEvent.VK_SPACE) ||
            (c==KeyEvent.VK_DELETE))){
        getToolkit().beep();
        evt.consume();
        JOptionPane.showMessageDialog(null, "Please Enter Digit Only", "WARNING!", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_txt_qtyKeyTyped

    private void btn_edit_purpose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_purpose1MouseClicked
        new edit_generic().setVisible(true);
    }//GEN-LAST:event_btn_edit_purpose1MouseClicked

    private void btn_edit_purpose1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_edit_purpose1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_purpose1MouseEntered

    private void btn_edit_purpose1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_edit_purpose1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_edit_purpose1KeyPressed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        try {
            int i = jList2.getModel().getSize();
            
            if (i>1){
                
            int dialogresult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this?",null,JOptionPane.YES_NO_OPTION);
            
                if (dialogresult == 0){

                    String row = jList2.getSelectedValue().toString();



                    String sql = "DELETE FROM pivot_medicine WHERE"
                    + " med_id = (SELECT med_id FROM med_table WHERE med_name = '"+lbl_medname.getText()+"')"
                    + " AND generic_id = (SELECT generic_id FROM generic WHERE generic_desc = '"+row+"');";

                    pstmt = conn.prepareStatement(sql);
                    pstmt.executeUpdate();


                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Please leave at least one generic for this medicine", null, JOptionPane.WARNING_MESSAGE);
            }
            
           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        genericss();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        try{
            if (lbl_medname.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Medicine name cannot be empty");
            }else{
               int i = jComboBox1.getSelectedIndex()+1;
                String sql = "UPDATE med_table SET"
                        + " med_name = '"+lbl_medname.getText()+"',"
                        + " med_category = '"+i+"'"
                        + " WHERE med_id = '"+id_sheet.getText()+"'";
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Changes Saved"); 
            }
 
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void EditComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_EditComponentShown
        try {
            int row = tbl_showmedicine.getSelectedRow();
            
            if (row==-1){
                JOptionPane.showMessageDialog(null, "Please Select Medicine to Edit");
            }else{

                String name = (tbl_showmedicine.getModel().getValueAt(row, 0).toString());

                lbl_medname.setText(name);


                String abcd = "SELECT * FROM med_table a inner join pivot_medicine b on a.med_id = b.med_id INNER JOIN generic c on b.generic_id = c.generic_id"
                        + " WHERE a.med_name = '"+lbl_medname.getText()+"';";

                pstmt = conn.prepareStatement(abcd);
                rs = pstmt.executeQuery();

                
                while (rs.next()){


                    String id = rs.getString("a.med_id");
                    id_sheet.setText(id);
                    int o = rs.getInt("med_category");
                    int y = o - 1;
                    jComboBox1.setSelectedIndex(y);
                    
    //                txt = txt+" "+temp;



                }
                
                genericss();

                add_list();

                
                }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_EditComponentShown

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        showmed();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void oldpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oldpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_oldpassActionPerformed

    private void pass1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass1ActionPerformed

    private void pass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pass2ActionPerformed

    private void a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a1ActionPerformed

    private void a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a2ActionPerformed

    private void tgl_manMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_manMouseExited
        mouse_out(tgl_man);
    }//GEN-LAST:event_tgl_manMouseExited

    private void txt_progFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_progFocusGained
        txt_prog.selectAll();
    }//GEN-LAST:event_txt_progFocusGained

    private void txt_descFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_descFocusGained
        txt_desc.selectAll();
    }//GEN-LAST:event_txt_descFocusGained

    private void txt_years_completeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_years_completeFocusGained
        txt_years_complete.selectAll();
    }//GEN-LAST:event_txt_years_completeFocusGained

    private void a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_a3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_a3ActionPerformed

    private void lbl_photoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_lbl_photoComponentShown
        // TODO add your handling code here:

        
    }//GEN-LAST:event_lbl_photoComponentShown

    private void txt_importKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_importKeyPressed
        updateTable();
    }//GEN-LAST:event_txt_importKeyPressed

    private void cmb_surtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_surtItemStateChanged
        updateTable();
    }//GEN-LAST:event_cmb_surtItemStateChanged

    private void txt_sortKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_sortKeyPressed
        search_profile();
    }//GEN-LAST:event_txt_sortKeyPressed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_hepa.getModel();
            mod.setRowCount(0);
            
            String a = "";
            if (cmb_prog.getSelectedIndex()==1){
                a = "(a.program = 'BSHRM')";
            }else if (cmb_prog.getSelectedIndex()==2){
                a = "(a.program = 'BSTM')";
            }else{
                a = "(a.program = 'BSHRM' OR program = 'BSTM')";
            }
            
            String b = "";
            String c = "";
            if (cmb_kategorya.getSelectedIndex()==0){
                b = " vacc_flu ";
                c = " b.influenza = 0 ";
            }else if (cmb_kategorya.getSelectedIndex()==1){
                b = " vacc_hepa_b";
                c = " b.result = 'Reactive'";
            }else{
                b = " vacc_hepa_b";
                c = " b.status = 'Ongoing'";
            }
            
            String q = "SELECT DISTINCT(a.id_no) as xz, lastname, p_section, firstname, middlename,program FROM patient_tbl a INNER JOIN  "+b+" b"
                    + " ON a.id_no = b.id_no WHERE "+c+" AND "+a+" ORDER BY lastname";
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String l = rs.getString("lastname");
                String f = rs.getString("firstname");
                String m = rs.getString("middlename");
                String p = rs.getString("program");
                String i = rs.getString("xz");
                String s = rs.getString("p_section");
                String last = l + ", " + f + " " + m;
                mod.addRow(new Object[]{i,last,p,s});
            }
            
            if (mod.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "No results to display");
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void tgl_inc1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_inc1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_inc1MouseEntered

    private void tgl_inc1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_inc1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_inc1MouseExited

    private void tgl_inc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_inc1ActionPerformed
        CardLayout c = (CardLayout)profile_layout.getLayout();
        c.show(profile_layout,"flu");
    }//GEN-LAST:event_tgl_inc1ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_hepa.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\vaccReportsv2.jrxml";
        
        int x = mod.getRowCount();
        String kat = cmb_kategorya.getSelectedItem().toString();
        String prog = cmb_prog.getSelectedItem().toString();
        String nurseName = Firstname +" "+ Lastname;
        
        
        if(cmb_prog.getSelectedIndex() == 0){
            prog = "BSHRM and BSTM";
        }
        

        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("kat", kat);
                params.put("prog", prog);
                params.put("nurseName", nurseName);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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

            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new frm_captureEmp().setVisible(true);
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            String fname = file.getAbsolutePath();
            txtPath_rfid.setText(fname);
        }

    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        String message = "Are you sure you want to import the data on the database ? To proceed click yes.";
        int dialogResult = JOptionPane.showConfirmDialog (null,message ,"Confirmation",dialogButton);
        if(dialogResult == 0){
        
            int loaded = 0;
            int skipped = 0;

            if ("".equals(txtPath_rfid.getText())) {
                JOptionPane.showMessageDialog(null, "Please select a path for your CSV.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "The specified file is not found.", "Not found.", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((line = br.readLine()) != null){
                        if (skip == 1) { ///skip for header in the csv
                            skip++;
                            continue;
                        }
                        String[] value = line.split(",", 2);//Separator
                        try {

                            conn.setAutoCommit(false);

                            st = conn.createStatement();
                            String updateQuery = "UPDATE patient_tbl SET "
                            + "RFID =  '"+value[1]+"'"
                            + ",insertedOn = '"+nowna+"'"
                            + ",user_id = '"+user_ID+"'"
                            + "WHERE id_no = '"+value[0]+"';";

                            st.addBatch(updateQuery);

                            int[] count = st.executeBatch();
                            conn.commit();
                            loaded++;

                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(this, "Error in Updating RFID");
                            e.printStackTrace();
                        }

                    }
                    br.close();
                    //return;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

                JOptionPane.showMessageDialog(this, "Youre CSV File has been Uploaded Successfully");
                JOptionPane.showMessageDialog(this, "Number of items imported: " + loaded);
                System.out.println("Number of items skipped: " + skipped);
                System.out.println("Number of items loaded: " + loaded);
                search_inc();
                txtPath_rfid.setText("");
            }
        }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void tgl_vac1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_vac1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_vac1MouseEntered

    private void tgl_vac1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_vac1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_vac1MouseExited

    private void tgl_vac1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_vac1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new Visitor().setVisible(true);
    }//GEN-LAST:event_tgl_vac1ActionPerformed

    private void tgl_medicine1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tgl_medicine1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_medicine1ItemStateChanged

    private void tgl_medicine1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medicine1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_medicine1MouseEntered

    private void tgl_medicine1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_medicine1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_medicine1MouseExited

    private void tgl_medicine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_medicine1ActionPerformed
        CardLayout c1 = (CardLayout)Manage_Layout.getLayout();
        c1.show(Manage_Layout, "summary");
    }//GEN-LAST:event_tgl_medicine1ActionPerformed

    private void btn_shooowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_shooowActionPerformed
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_summary.getModel();
            mod.setRowCount(0);
            
            int kolehiyo, senior, empleyado, bisita = 0;
            
            int totalcollege = 0;
            int totalsenior = 0;
            int totalempleyado = 0;
            int totalbisita = 0;
            String ya = cmb_years.getSelectedItem().toString();
            String n[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
            
            for (int i = 0;i<n.length;i++){
                String buwan = n[i];
                
                String q = "SELECT COUNT(a.id_no) College FROM med_dspnsd a INNER JOIN patient_tbl b ON a.id_no = b.id_no WHERE DATE_FORMAT(a.date_given,'%Y') = '"+ya+"' AND DATE_FORMAT(a.date_given,'%M') = '"+buwan+"' AND b.occupation = 'Student - College'";
            pstmt = conn.prepareStatement(q);
            rs = pstmt.executeQuery();
            if (rs.next()){
                kolehiyo = rs.getInt("College");
            }else{
                kolehiyo = 0;
            }
            
            totalcollege = totalcollege + kolehiyo;
            
            String w = "SELECT COUNT(a.id_no) SHS FROM med_dspnsd a INNER JOIN patient_tbl b ON a.id_no = b.id_no WHERE DATE_FORMAT(a.date_given,'%Y') = '"+ya+"' AND DATE_FORMAT(a.date_given,'%M') = '"+buwan+"' AND b.occupation = 'Student - Senior High'";
            pstmt = conn.prepareStatement(w);
            rs = pstmt.executeQuery();
            if (rs.next()){
                senior = rs.getInt("SHS");
            }else{
                senior = 0;
            }
            
            totalsenior = totalsenior + senior;
            
            String e = "SELECT COUNT(a.id_no) ya FROM med_dspnsd a INNER JOIN patient_tbl b ON a.id_no = b.id_no WHERE DATE_FORMAT(a.date_given,'%Y') = '"+ya+"' AND DATE_FORMAT(a.date_given,'%M') = '"+buwan+"' AND b.occupation = 'Employee'";
            pstmt = conn.prepareStatement(e);
            rs = pstmt.executeQuery();
            if (rs.next()){
                empleyado = rs.getInt("ya");
            }else{
                empleyado = 0;
            }
            
            totalempleyado = totalempleyado + empleyado;
            
            String r = "SELECT COUNT(a.id_no) Visitor FROM med_dspnsd a INNER JOIN patient_tbl b ON a.id_no = b.id_no WHERE DATE_FORMAT(a.date_given,'%Y') = '"+ya+"' AND DATE_FORMAT(a.date_given,'%M') = '"+buwan+"' AND b.id_no = '100001'";
            pstmt = conn.prepareStatement(r);
            rs = pstmt.executeQuery();
            if (rs.next()){
                bisita = rs.getInt("Visitor");
            }else{
                bisita = 0;
            }
            
            totalbisita = totalbisita + bisita;
            int yahallo = bisita + empleyado + senior + kolehiyo;
            
            mod.addRow(new Object[]{buwan,kolehiyo,senior,empleyado,bisita,yahallo});
   
            }
 
            txt_col.setText(String.valueOf(totalcollege));
            txt_shs.setText(String.valueOf(totalsenior));
            txt_emp.setText(String.valueOf(totalempleyado));
            txt_visit.setText(String.valueOf(totalbisita));
            int a = totalcollege + totalsenior + totalempleyado + totalbisita;
            total.setText(String.valueOf(a));
   
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_shooowActionPerformed

    private void tbl_summaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_summaryMouseClicked
        if (evt.getClickCount()==2){
            
            int i = tbl_summary.getSelectedRow();
            
            if (i==-1){
                JOptionPane.showMessageDialog(null,"Please Select a Row");
            }else{
                select_month = tbl_summary.getValueAt(i, 0).toString();
                monthly_report.year = cmb_years.getSelectedItem().toString();
                new monthly_report().setVisible(true);
            }
            
            
        }
    }//GEN-LAST:event_tbl_summaryMouseClicked

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel mod = (DefaultTableModel) tbl_summary.getModel();
        JRTableModelDataSource  datasource = new JRTableModelDataSource(mod);
        String reportSource = "C:\\Users\\Benjie\\Desktop\\PROGRAM FILE THESIS 2015-2016\\CMS\\CMS\\src\\reports\\annualReports.jrxml";
        
        int x = mod.getRowCount();
        String year = cmb_years.getSelectedItem().toString();
        String txt_col1 = txt_col.getText();
        String txt_shs1 = txt_shs.getText();
        String txt_emp1 = txt_emp.getText();
        String txt_visit1 = txt_visit.getText();
        String total1 = total.getText();
        String nurseName = Firstname +" "+ Lastname;

        if (x == 0){
                JOptionPane.showMessageDialog(this, "No result to be print");
        }else{
            try {
                JasperReport jr =  JasperCompileManager.compileReport(reportSource);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("year", year);
                params.put("txt_col1", txt_col1);
                params.put("txt_shs1", txt_shs1);
                params.put("txt_emp1", txt_emp1);
                params.put("txt_visit1", txt_visit1);
                params.put("total1", total1);
                params.put("nurseName", nurseName);
                JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);
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

            } catch (JRException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void tgl_man1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_man1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_man1MouseEntered

    private void tgl_man1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tgl_man1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_man1MouseExited

    private void tgl_man1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgl_man1ActionPerformed
        // TODO add your handling code here:
        CardLayout c1 = (CardLayout)(Maintenance_Layout.getLayout());
        c1.show(Maintenance_Layout, "backup");
    }//GEN-LAST:event_tgl_man1ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            String fname = file.getAbsolutePath();
            txtPath_b.setText(fname);
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:
        if (txtPath_b.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Select path");
        } else {
            Date now = new Date();
            SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            String filename = date.format(now);
            String command = "C:\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysqldump -uroot -p --add-drop-table -B cms_v2.0 -r " + txtPath_b.getText().toString() + "\\" + filename + ".sql";

            Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec(command);
                int processComplete = p.waitFor();
                if (processComplete == 0) {
                    JOptionPane.showMessageDialog(null, "Succesfully created backup");
                } else {
                    JOptionPane.showMessageDialog(null, "Could not create backup");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }

        }
        
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        if (jFileChooser1.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = jFileChooser1.getSelectedFile();
            String fname = file.getAbsolutePath();
            txtPath_r.setText(fname);
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton46ActionPerformed

    
    private void receivedOrder(){
        
        int row = order_table.getSelectedRow();
        String poid = (order_table.getModel().getValueAt(row, 0).toString());
        try {
            st = conn.createStatement();
            String query = "SELECT * FROM med_table INNER JOIN med_orders ON"
                    + " med_orders.med_id = med_table.med_id"
                    + " INNER JOIN purchase_orders ON purchase_orders.po_id = med_orders.po_order WHERE purchase_orders.po_id = '"+poid+"';";
            rs = st.executeQuery(query);

            while (rs.next()){
                //get the value in med_orders
                String med_id = rs.getString("med_id");
                //String po_order = rs.getString("po_order");
                String med_qnty = rs.getString("med_qnty");
                //get the value in med_table
                String med_remain = rs.getString("med_remain");
                //String med_qty_ordered = rs.getString("med_qty_ordered");
                //formulas
                int new_med_remain = Integer.parseInt(med_remain)+Integer.parseInt(med_qnty);
                //value for the updated med_remain in med_table
                //value for the updated med_qty_ordered = new_med_remain  
                System.out.println(new_med_remain);
                
                String updateMedQnty = "UPDATE med_table SET"
                    + " med_remain = '"+new_med_remain+"',"
                    + " med_qty_ordered = '"+new_med_remain+"'"
                    + " WHERE med_id = '"+med_id+"'";

                pstmt = conn.prepareStatement(updateMedQnty);
                pstmt.executeUpdate();
                
                String updatePO = "UPDATE purchase_orders SET"
                    + " po_recby = '"+user_ID+"',"
                    + " po_recibdate = '"+nowna+"',"
                    + " po_status = 'RECEIVED'"
                    + " WHERE po_id = '"+poid+"'";

                pstmt = conn.prepareStatement(updatePO);
                pstmt.executeUpdate();
                
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        
        }
        
        
        
    }
    
    private void cancelOrder(){
         
        int row = order_table.getSelectedRow();
        String poid = (order_table.getModel().getValueAt(row, 0).toString());
        try{
            String again = "UPDATE purchase_orders SET"
                    + " po_recby = '"+user_ID+"',"
                    + " po_status = 'CANCELLED'"
                    + " WHERE po_id = '"+poid+"'";

            pstmt = conn.prepareStatement(again);
            pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    private void search_inc(){
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_inc.getModel();
            mod.setRowCount(0);
            
            String sql = "SELECT * FROM patient_tbl WHERE RFID IS NULL AND (id_no LIKE '%"+txt_txt.getText()+"%'"
                    + " OR lastname LIKE '%"+txt_txt.getText()+"%'"
                    + " OR firstname LIKE '%"+txt_txt.getText()+"%'"
                    + " OR middlename LIKE '%"+txt_txt.getText()+"%')"
                    + " AND ID_no != '100001'";
                   
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("id_no");
                String last = rs.getString("lastname");
                String upperlast = WordUtils.capitalizeFully(last);
                String first = rs.getString("firstname");
                String upperfirst = WordUtils.capitalizeFully(first);
                String middle = rs.getString("middlename");
                String mid = WordUtils.capitalizeFully(middle);
                String initial = WordUtils.initials(mid);
                String full = upperlast+", "+upperfirst+" "+initial+".";
                String prog = rs.getString("program");
                if (prog.equalsIgnoreCase("null") || prog.isEmpty()){
                    prog = "  -  ";
                }
                System.out.println(prog);
                String brgy = rs.getString("brgy");
                String upperbrgy = WordUtils.capitalizeFully(brgy);
                String city = rs.getString("city");
                String uppercity = WordUtils.capitalizeFully(city);
                String address = upperbrgy+", "+uppercity;
                
                mod.addRow(new Object[]{id, full, prog, address});
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
     private void insertProg(){
         
        if(txt_prog.getText().isEmpty() || txt_desc.getText().isEmpty() || txt_years_complete.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please complete the details ");
        }else{
            try{
                // check if may kaparehas
                String q = "SELECT * FROM course WHERE course_name = '"+txt_prog.getText()+"' OR course_desc = '"+txt_desc.getText()+"'";
                pstmt = conn.prepareStatement(q);
                rs = pstmt.executeQuery();
                if (rs.next()){
                    JOptionPane.showMessageDialog(null, "Duplicate course name/description found, enter another entry");
                }else{
                    String college = "1";
                    if (radio_college.isSelected()){
                        college = "1";
                    }else{
                        college = "0";
                    }
                    st = conn.createStatement();
                    String insertProg = "INSERT INTO course SET"
                            + " course_id = '"+txt_courseid.getText()+"',"
                            + " course_name = '"+txt_prog.getText()+"',"
                            + " course_desc = '"+txt_desc.getText()+"',"
                            + " years = '"+txt_years_complete.getText()+"',"
                            + " college = '"+college+"'";       
                    st.executeUpdate(insertProg);
                    JOptionPane.showMessageDialog(this, "You have sucsessfully added a new Program/Track!");
                    txt_prog.setText("");
                    txt_years_complete.setText("");
                    txt_desc.setText("");
                }
                
                
                
                
            }catch(Exception e){
                
                e.printStackTrace();
            }
        }
     }
    public static void show_all_generic(){
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_generic.getModel();
            mod.setRowCount(0);
            Connection con = SQLConnection.ConnDB();
            String sql = "SELECT * FROM generic ORDER BY generic_desc";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet r = pst.executeQuery();
            while (r.next()){
                
                String name = r.getString("generic_desc");
                Boolean x = false;
                mod.addRow(new Object[]{name, x});           
            }
        }catch(Exception e){
            
            e.printStackTrace();
            
        }  
    }
    
    public static void show_inc(){
        PreparedStatement pst;
        ResultSet r;
        Connection con = SQLConnection.ConnDB();
        
        try{
            DefaultTableModel mod = (DefaultTableModel)tbl_inc.getModel();
            mod.setRowCount(0);
            
            String sql = "SELECT * FROM patient_tbl WHERE RFID IS NULL AND ID_no != '100001'";
                   
            pst = con.prepareStatement(sql);
            r = pst.executeQuery();
            while (r.next()){
                String id = r.getString("id_no");
                String last = r.getString("lastname");
                String upperlast = WordUtils.capitalizeFully(last);
                String first = r.getString("firstname");
                String upperfirst = WordUtils.capitalizeFully(first);
                String middle = r.getString("middlename");
                String mid = WordUtils.capitalizeFully(middle);
                String initial = WordUtils.initials(mid);
                String full = upperlast+", "+upperfirst+" "+initial+".";
                String prog = r.getString("program");
                String brgy = r.getString("brgy");
                String upperbrgy = WordUtils.capitalizeFully(brgy);
                String city = r.getString("city");
                String uppercity = WordUtils.capitalizeFully(city);
                String address = upperbrgy+", "+uppercity;
                
                mod.addRow(new Object[]{id, full, prog, address});
                
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void search_profile(){
        try{
            String kurso, year, assessment;
            kurso = cmb_track.getSelectedItem().toString();
            
            assessment = cmb_assess.getSelectedItem().toString();
            
            
            DefaultTableModel mod = (DefaultTableModel)tbl_monitor.getModel();
            mod.setRowCount(0);
            String query = "";
            
            if (cmb_track.isEnabled()){
                query = "SELECT * FROM patient_tbl a INNER JOIN assessment_tbl b ON a.id_no = b.id_no"
                    + " WHERE b.assessment = '"+assessment+"' AND a.program = '"+kurso+"' "
                        + "AND (a.id_no LIKE '%"+txt_sort.getText()+"%' OR lastname LIKE '%"+txt_sort.getText()+"%' OR firstname LIKE '%"+txt_sort.getText()+"%'"
                        + " OR middlename LIKE '%"+txt_sort.getText()+"%');";
            }else{
                query = "SELECT * FROM patient_tbl a INNER JOIN assessment_tbl b ON a.id_no = b.id_no"
                        + " WHERE b.assessment = '"+assessment+"' AND a.occupation = 'Employee' AND a.ID_no != '100001'"
                        + "AND (a.id_no LIKE '%"+txt_sort.getText()+"%' OR lastname LIKE '%"+txt_sort.getText()+"%' OR firstname LIKE '%"+txt_sort.getText()+"%'"
                        + " OR middlename LIKE '%"+txt_sort.getText()+"%');";
            }

            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("a.id_no");
                String lname = rs.getString("a.lastname");
                String fname = rs.getString("a.firstname");
                String mname = rs.getString("a.middlename");
                String upperlast = WordUtils.capitalizeFully(lname);
                String upperfirst = WordUtils.capitalizeFully(fname);
                String uppermid = WordUtils.capitalizeFully(mname);
                
                String prog = rs.getString("a.program");
                if (prog.equals("null")){
                    prog = "  -  ";
                }
                String year_level = rs.getString("a.year_level");
                if (year_level.equals("null")){
                    year_level = "  -  ";
                }
                Date bday = rs.getDate("bday");
                String brgy = rs.getString("brgy");
                String upperbrgy = WordUtils.capitalizeFully(brgy);
                String city = rs.getString("city");
                String uppercity = WordUtils.capitalizeFully(city);
                String brgycity = upperbrgy+", "+uppercity;
                
                String contact = rs.getString("contact");
                
                mod.addRow(new Object[] {id, upperlast, upperfirst, uppermid, prog, year_level, bday,brgycity, contact});
   
            }         
        }catch(Exception e){
            e.printStackTrace();
        }
    }
  
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddMed;
    private javax.swing.JPanel AddNewUser;
    private javax.swing.JPanel Add_Program;
    private javax.swing.ButtonGroup Add_new_track;
    private javax.swing.JPanel Backup_Restore;
    private javax.swing.JPanel Bed;
    private javax.swing.ButtonGroup College_Senior;
    private javax.swing.ButtonGroup Complete_Incomplete;
    private javax.swing.JPanel Dipose;
    public static javax.swing.JPanel Edit;
    private javax.swing.JRadioButton Employee;
    private javax.swing.JPanel Export_Print_Records;
    private javax.swing.JPanel Flu;
    private javax.swing.JPanel Graph;
    private javax.swing.ButtonGroup Grp_Reports;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel Import_Records;
    private javax.swing.JPanel Inv_Layout;
    private javax.swing.JPanel Inventory;
    private javax.swing.JPanel Inventory_Home;
    private javax.swing.ButtonGroup Inventory_navigation;
    public static javax.swing.JPanel Layout;
    private javax.swing.JPanel List;
    private javax.swing.JPanel Maintenance;
    private javax.swing.JPanel Maintenance_Home;
    public static javax.swing.JPanel Maintenance_Layout;
    private javax.swing.ButtonGroup Maintenance_navigation;
    private javax.swing.JPanel Manage;
    private javax.swing.JPanel Manage_Account;
    private javax.swing.JPanel Manage_Layout;
    private javax.swing.ButtonGroup Manage_navigation;
    private javax.swing.JPanel Med_Inventory;
    private javax.swing.JPanel Medicine_Home;
    private javax.swing.JPanel Navigation;
    private javax.swing.JPanel Navigation1;
    private javax.swing.JPanel Navigation2;
    private javax.swing.JPanel No_ID_No_Entry;
    private javax.swing.JPanel Profile;
    private javax.swing.JPanel Profile_Home;
    private javax.swing.ButtonGroup Profile_Navigation;
    private javax.swing.JPanel Purchace;
    private javax.swing.JPanel Reports;
    private javax.swing.JPanel Security_Account;
    private javax.swing.JPanel Summary;
    private javax.swing.JPanel Supplier;
    private javax.swing.JPanel Update_Records;
    private javax.swing.JPanel VAccine;
    private javax.swing.JPanel View;
    private javax.swing.JPanel View_Students;
    private javax.swing.JPasswordField a1;
    private javax.swing.JPasswordField a2;
    private javax.swing.JPasswordField a3;
    private javax.swing.JLabel btn_add_generic1;
    private javax.swing.JLabel btn_add_new_purpose;
    private javax.swing.JButton btn_deactivate;
    private javax.swing.JButton btn_dispose;
    private javax.swing.JButton btn_edit;
    private javax.swing.JLabel btn_edit_purpose;
    private javax.swing.JLabel btn_edit_purpose1;
    private javax.swing.JButton btn_shooow;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chk_active;
    private com.toedter.calendar.JDateChooser chooser_from;
    private com.toedter.calendar.JDateChooser chooser_from_confine;
    private com.toedter.calendar.JDateChooser chooser_to;
    private com.toedter.calendar.JDateChooser chooser_to_confine;
    private javax.swing.JLabel clock;
    private javax.swing.JComboBox cmb_assess;
    private javax.swing.JComboBox cmb_avail;
    private javax.swing.JComboBox cmb_cat;
    private javax.swing.JRadioButton cmb_complete;
    private javax.swing.JRadioButton cmb_inc;
    private javax.swing.JComboBox cmb_kategorya;
    private javax.swing.JComboBox cmb_month;
    private javax.swing.JComboBox cmb_prog;
    private javax.swing.JComboBox cmb_purpose;
    private javax.swing.JComboBox cmb_specific;
    private javax.swing.JComboBox cmb_surt;
    private javax.swing.JComboBox cmb_track;
    private javax.swing.JComboBox cmb_year;
    private javax.swing.JComboBox cmb_years;
    private javax.swing.JComboBox hrm_tm;
    public static javax.swing.JLabel id_sheet;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JFileChooser jFileChooser1;
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
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
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
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
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
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JLabel lbl_current;
    private javax.swing.JLabel lbl_icon;
    private javax.swing.JLabel lbl_med_id;
    private javax.swing.JLabel lbl_medical1;
    private javax.swing.JLabel lbl_medid;
    public static javax.swing.JTextField lbl_medname;
    private javax.swing.JLabel lbl_medneym;
    private javax.swing.JLabel lbl_other1;
    private javax.swing.JLabel lbl_other2;
    private javax.swing.JLabel lbl_other3;
    private javax.swing.JLabel lbl_other4;
    private javax.swing.JLabel lbl_photo;
    private javax.swing.JLabel lbl_position;
    private static javax.swing.JTable medicine_table;
    public static javax.swing.JTextField nurse_name;
    private javax.swing.JPasswordField oldpass;
    public static javax.swing.JTable order_table;
    private javax.swing.JPasswordField pass1;
    private javax.swing.JPasswordField pass2;
    private javax.swing.JPasswordField passtxt;
    private javax.swing.JButton printResultBut;
    private javax.swing.JPanel profile_layout;
    private javax.swing.JComboBox program;
    private javax.swing.JTable program_table;
    private javax.swing.JComboBox q1;
    private javax.swing.JComboBox q2;
    private javax.swing.JComboBox q3;
    private javax.swing.JRadioButton radio1;
    private javax.swing.JRadioButton radio2;
    private javax.swing.JRadioButton radio_college;
    private javax.swing.JRadioButton radio_senior;
    private javax.swing.JPanel showGraph;
    private javax.swing.JPanel showPieGraph;
    private javax.swing.JTextField sup_add;
    private javax.swing.JTextField sup_cp;
    private javax.swing.JTextField sup_email;
    private javax.swing.JTextField sup_ll;
    private javax.swing.JTextField sup_name;
    private javax.swing.JTable supplierTable;
    private javax.swing.JTable tbl_bed;
    private javax.swing.JTable tbl_confine;
    private javax.swing.JTable tbl_criMed;
    private javax.swing.JTable tbl_export;
    private javax.swing.JTable tbl_general;
    public static javax.swing.JTable tbl_generic;
    private javax.swing.JTable tbl_hepa;
    public static javax.swing.JTable tbl_inc;
    private javax.swing.JTable tbl_monitor;
    public static javax.swing.JTable tbl_purposes;
    private javax.swing.JTable tbl_showmedicine;
    private javax.swing.JTable tbl_specific;
    private javax.swing.JTable tbl_summary;
    private javax.swing.JTable tbl_today;
    private javax.swing.JTable tbl_vaccine;
    private javax.swing.JToggleButton tgl_addmedicine;
    private javax.swing.JToggleButton tgl_basic1;
    private javax.swing.JToggleButton tgl_basic2;
    private javax.swing.JToggleButton tgl_bmi;
    private javax.swing.JRadioButton tgl_college;
    private javax.swing.JToggleButton tgl_contact1;
    private javax.swing.JToggleButton tgl_contact2;
    private javax.swing.JToggleButton tgl_fam;
    private javax.swing.JToggleButton tgl_home;
    private javax.swing.JToggleButton tgl_import;
    private javax.swing.JToggleButton tgl_inc;
    private javax.swing.JToggleButton tgl_inc1;
    private javax.swing.JToggleButton tgl_inventory;
    private javax.swing.JToggleButton tgl_list;
    private javax.swing.JToggleButton tgl_logout;
    private javax.swing.JToggleButton tgl_maintenance;
    private javax.swing.JToggleButton tgl_man;
    private javax.swing.JToggleButton tgl_man1;
    private javax.swing.JToggleButton tgl_manage;
    private javax.swing.JToggleButton tgl_med;
    private javax.swing.JToggleButton tgl_medicine;
    private javax.swing.JToggleButton tgl_medicine1;
    private javax.swing.JToggleButton tgl_newill;
    private javax.swing.JToggleButton tgl_newill1;
    private javax.swing.JToggleButton tgl_problem;
    private javax.swing.JToggleButton tgl_profile;
    private javax.swing.JToggleButton tgl_report;
    private javax.swing.JToggleButton tgl_report1;
    private javax.swing.JToggleButton tgl_sec;
    private javax.swing.JRadioButton tgl_senior;
    private javax.swing.JToggleButton tgl_vac;
    private javax.swing.JToggleButton tgl_vac1;
    private javax.swing.JTextField total;
    private javax.swing.JTextArea txtPath;
    private javax.swing.JTextArea txtPath1;
    private javax.swing.JTextArea txtPath_b;
    private javax.swing.JTextArea txtPath_r;
    private javax.swing.JTextArea txtPath_rfid;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_col;
    private javax.swing.JTextField txt_courseid;
    private javax.swing.JLabel txt_day;
    private javax.swing.JTextField txt_desc;
    private javax.swing.JTextField txt_emp;
    private javax.swing.JTextField txt_import;
    private static javax.swing.JTextField txt_medname;
    private javax.swing.JTextField txt_prog;
    private static javax.swing.JTextField txt_qty;
    public static javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_searchmed;
    private javax.swing.JTextField txt_shs;
    private javax.swing.JTextField txt_sort;
    private javax.swing.JTextField txt_supid;
    private javax.swing.JTextField txt_txt;
    public static javax.swing.JTextField txt_user_ID;
    private javax.swing.JTextField txt_visit;
    private javax.swing.JTextField txt_years_complete;
    private javax.swing.JTable update_table;
    private javax.swing.JTable userlist;
    private javax.swing.JTextField usertxt;
    // End of variables declaration//GEN-END:variables
}
