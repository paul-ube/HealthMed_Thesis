/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author paul.ube
 */
public class DesktopPane_function {
    public static void ClearDesktop(JDesktopPane centerpane){

        centerpane.removeAll();
        centerpane.setVisible(false);
        centerpane.setVisible(true);
        
    }
    
}
