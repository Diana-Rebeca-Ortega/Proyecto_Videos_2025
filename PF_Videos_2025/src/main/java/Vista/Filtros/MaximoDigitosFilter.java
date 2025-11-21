// MaximoDigitosFilter.java (Nueva clase)
package Vista.Filtros;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.JOptionPane; // Necesitarás importar JOptionPane para el mensaje.

public class MaximoDigitosFilter extends DocumentFilter {
    private final int maximo;

    public MaximoDigitosFilter(int maximo) {
        this.maximo = maximo;
    }

    private boolean esValido(String str) {
        // Verifica si la cadena solo contiene dígitos (y no null)
        if (str == null) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (esValido(string)) {
            int nuevaLongitud = fb.getDocument().getLength() + string.length();
            if (nuevaLongitud <= maximo) {
                super.insertString(fb, offset, string, attr);
            } else {
                // Notificación "Toast" si se excede la longitud máxima
                JOptionPane.showMessageDialog(null, "El Código Postal solo admite 5 dígitos.", "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (esValido(text)) {
            int nuevaLongitud = fb.getDocument().getLength() - length + text.length();
            if (nuevaLongitud <= maximo) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // Notificación "Toast" si se excede la longitud máxima
                JOptionPane.showMessageDialog(null, "El campo solo admite 5 dígitos.", "Límite Excedido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}