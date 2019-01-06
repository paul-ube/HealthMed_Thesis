/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import java.awt.Color;
import javax.swing.JToggleButton;

/**
 *
 * @author Benjie
 */
public class Toggle {
    
    public static void mouse_in(JToggleButton a){
        if (a.getForeground().equals(Color.yellow)){
            
        }else{
            a.setForeground(Color.yellow);
        }
    }
    
    public static void mouse_out(JToggleButton b){
        if (!b.isSelected()){
      
            b.setForeground(new Color(255,255,255));
        }
    }
 
}
