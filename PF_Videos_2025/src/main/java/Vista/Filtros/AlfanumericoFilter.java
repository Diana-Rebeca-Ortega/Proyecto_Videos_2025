package Vista.Filtros;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AlfanumericoFilter extends DocumentFilter {

    /**
     * Reemplaza el texto para permitir solo caracteres alfanuméricos y espacios.
     */
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
            throws BadLocationException {
        
        if (text == null) {
            super.replace(fb, offset, length, text, attrs);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Permite letras (a-z, A-Z), números (0-9) y el espacio
            if (Character.isLetterOrDigit(c) || c == ' ') {
                sb.append(c);
            }
        }
        
        // Ejecuta la inserción solo con los caracteres validados
        super.replace(fb, offset, length, sb.toString(), attrs);
    }
    
    // El método insertString llama internamente a replace en muchas implementaciones, 
    // pero lo definimos explícitamente por si acaso.
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
            throws BadLocationException {
        replace(fb, offset, 0, string, attr);
    }
}