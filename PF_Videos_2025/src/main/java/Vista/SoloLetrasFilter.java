/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * DocumentFilter que permite solo letras y espacios.
 */
public class SoloLetrasFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (string == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            // Verifica si es una letra, un espacio o una letra con acento (adaptado para español)
            if (Character.isLetter(c) || Character.isWhitespace(c) || c == 'ñ' || c == 'Ñ' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú') {
                sb.append(c);
            }
        }
        super.insertString(fb, offset, sb.toString(), attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Verifica si es una letra, un espacio o una letra con acento
            if (Character.isLetter(c) || Character.isWhitespace(c) || c == 'ñ' || c == 'Ñ' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú' || c == 'Á' || c == 'É' || c == 'Í' || c == 'Ó' || c == 'Ú') {
                sb.append(c);
            }
        }
        super.replace(fb, offset, length, sb.toString(), attrs);
    }
}