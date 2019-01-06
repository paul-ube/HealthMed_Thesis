package cms.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SQLConnection {
    Connection conn = null;
    
        public static Connection ConnDB(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms_v2.0","root","");
                return conn;
            }catch(ClassNotFoundException | SQLException e){
                JOptionPane.showMessageDialog(null, e);
                
                return null;
            }
        }
}
