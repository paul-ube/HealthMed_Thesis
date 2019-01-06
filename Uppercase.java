    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.function;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.apache.commons.lang.WordUtils;

/**
 *
 * @author paul.ube
 */
public class Uppercase extends DocumentFilter  {
    //get the input
    public static void uppercase(JTextField txt) {
        
        String up_up_and_away = WordUtils.capitalizeFully(txt.getText());
        txt.setText(up_up_and_away);
    }
}


    