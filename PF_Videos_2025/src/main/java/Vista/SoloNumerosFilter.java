/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SoloNumerosFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // Solo permite la inserción si todos los caracteres son dígitos
        if (string == null) {
            return;
        }
        
        StringBuilder sb = new StringBuilder(string.length());
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            // Verifica si el carácter es un dígito
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        super.insertString(fb, offset, sb.toString(), attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }
        
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Verifica si el carácter es un dígito
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        super.replace(fb, offset, length, sb.toString(), attrs);
    }
}