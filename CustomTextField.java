package cms.function;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/**
 *
 * @author Jake
 */
public class CustomTextField extends JTextField {
    
    private Font orgFont;
    private Color orgForeground;
    
    private Color placeholderForeground = new Color(160, 160, 160);
    private boolean textWrittenIn;
    
    public CustomTextField(int col){
        super(col);
    }
    
    @Override
    public void setFont(Font f){
        super.setFont(f);
        if(!isTextWrittenIn()){
            orgFont = f;
        }
    }
    
    @Override
    public void setForeground(Color fg){
        super.setForeground(fg);
        if(!isTextWrittenIn()){
            orgForeground = fg;
        }
    }
       
    public Color getPlaceholderForeground(){
        return placeholderForeground;
    }

    public void setPlaceholderForeground(Color placeholderForeground){
        this.placeholderForeground = placeholderForeground;
    }
    
    public boolean isTextWrittenIn(){
        return textWrittenIn;
    }
    
    public void setTextWrittenIn(boolean textWrittenIn){
        this.textWrittenIn = textWrittenIn;
    }
    
    public void setPlaceholder(final String text){
        this.customizeText(text);
        
        this.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                warn();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e){
                warn();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e){
                warn();
            }
            
            public void warn(){
                if(getText().trim().length() != 0){
                    setFont(orgFont);
                    setForeground(orgForeground);
                    setTextWrittenIn(true);
                }
            }
        });
        
        this.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                if(!isTextWrittenIn()){
                    setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e){
                if (getText().trim().length() == 0){
                    customizeText(text);
                }
            }
        });
    }
    
    private void customizeText(String text){
        setText(text);
        setFont(new Font(getFont().getFamily(), Font.PLAIN, getFont().getSize()));
        setForeground(getPlaceholderForeground());
        setTextWrittenIn(false);
    }
}
