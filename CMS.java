package cms;

import java.sql.SQLException;
import org.opencv.core.Core;
public class CMS {  
    public static void main(String[] args) throws SQLException {  
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new login_again().setVisible(true);
    }
}
