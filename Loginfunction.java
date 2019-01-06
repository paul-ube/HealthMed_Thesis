/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author paul.ube
 */
public class Loginfunction {
    
    
    public static String user_ID;
    public static String Lastname;
    public static String Position;
    public static String Firstname;
    
    public static boolean CheckLogin(String Username, String Password) {
        boolean check = false;
        ResultSet rs = null;
        PreparedStatement st = null;
        Connection conn = SQLConnection.ConnDB();
        
        try{
            String sql = "Select * from nurse_table a INNER JOIN patient_tbl b ON a.user_ID = b.id_no where binary a.Username=? and binary a.Password=?";
            st = conn.prepareStatement(sql);
            st.setString(1, Username);
            st.setString(2, Password);
//            st.setString(3, "ACTIVE");
            rs = st.executeQuery();

            if (rs.next()) {
                check = true;
                setEmp_ID(rs.getString("a.user_ID"));
                setLastname(rs.getString("b.Lastname"));
                setPosition(rs.getString("a.Position"));
                setFirstname(rs.getString("b.Firstname"));

            } else {
                check = false;
            }
        }catch (Exception e){
            Logger.getLogger(Loginfunction.class.getName()).log(Level.SEVERE, null, e);
            check = false;
        }finally{
            try{
                if (st!=null){
                    st.close();
                }
                if (rs!=null){
                    rs.close();
                }
                conn.setAutoCommit(true);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }return check;
    
}

    private static void setEmp_ID(String user_ID) {
        Loginfunction.user_ID = user_ID;
    }
    
    private static void setFirstname(String Firstname){
        Loginfunction.Firstname = Firstname;
    }
          

    private static void setLastname(String Lastname) {
        Loginfunction.Lastname = Lastname;
        
    }

    private static void setPosition(String Position) {
        Loginfunction.Position = Position;
    }
    
    public static String getFirstname(){
        return Firstname;
    }

    public static String getuser_ID() {
        return user_ID;
    }

    public static String getLastname() {
        return Lastname;
    }

    public static String getPosition() {
        return Position;
    }
   

}
