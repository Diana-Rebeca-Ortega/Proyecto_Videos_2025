package Vista.Filtros;
import ConneccionBD.ConexionBD;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BuscadorPredictivo {

    // Registra el autocompletado en tu JTextField actual
    public static void registrarAutocompletado(JTextField txtBuscador) {
        JPopupMenu popupMenu = new JPopupMenu();
        JList<String> listResultados = new JList<>();
        
        // Colocamos la lista dentro de un panel con scroll por si hay muchas opciones
        JScrollPane scrollPane = new JScrollPane(listResultados);
        popupMenu.add(scrollPane);
        
        // Evitamos que el popup le quite el foco al teclado del JTextField
        popupMenu.setFocusable(false);

        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                // Omitir acciones si se presionan las flechas de dirección o Enter
                if (e.getKeyCode() == KeyEvent.VK_UP || 
                    e.getKeyCode() == KeyEvent.VK_DOWN || 
                    e.getKeyCode() == KeyEvent.VK_ENTER) {
                    return;
                }

                String textoFiltro = txtBuscador.getText().trim();
                
                if (textoFiltro.isEmpty()) {
                    popupMenu.setVisible(false);
                    return;
                }

                // 1. Obtener los datos filtrados (Aquí conectas con tu base de datos o lista local)
                // Reemplaza este método simulado por tu consulta SQL real (ej. buscando por título o ID)
                List<String> sugerencias = obtenerSugerenciasDesdeBD(textoFiltro);

                if (!sugerencias.isEmpty()) {
                    // Actualizar el modelo de la lista con los nuevos resultados hallados
                    DefaultListModel<String> model = new DefaultListModel<>();
                    for (String sugerencia : sugerencias) {
                        model.addElement(sugerencia);
                    }
                    listResultados.setModel(model);

                    // Ajustar tamaño del popup y mostrar justo debajo del JTextField
                    scrollPane.setPreferredSize(new java.awt.Dimension(txtBuscador.getWidth(), 120));
                    popupMenu.show(txtBuscador, 0, txtBuscador.getHeight());
                    txtBuscador.requestFocus(); // Reafirmar el foco en el input
                } else {
                    popupMenu.setVisible(false);
                }
            }
        });

        // Evento para cuando el usuario selecciona una opción con el mouse
        listResultados.addListSelectionListener(e -> {
           if (!e.getValueIsAdjusting() && listResultados.getSelectedValue() != null) {
        String seleccionCompleta = listResultados.getSelectedValue(); // "Ariel - ID Copia: 5"
        
        // Cortamos el texto justo donde aparece el primer " - "
        String soloNombre = seleccionCompleta.split(" - ")[0].trim(); // Guarda "Ariel"
        
        txtBuscador.setText(soloNombre); // Setea solo el nombre en el campo
        popupMenu.setVisible(false);
        
        System.out.println("Película seleccionada: " + soloNombre);
    }
        });
        
        // Permitir navegar por las opciones con las flechas del teclado desde el mismo textfield
        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
    public void keyPressed(KeyEvent e) {
        if (popupMenu.isShowing()) {
            int index = listResultados.getSelectedIndex();
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                listResultados.setSelectedIndex(index + 1);
                listResultados.ensureIndexIsVisible(index + 1);
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                listResultados.setSelectedIndex(index - 1);
                listResultados.ensureIndexIsVisible(index - 1);
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if (listResultados.getSelectedValue() != null) {
                    String seleccionCompleta = listResultados.getSelectedValue();
                    
                    // Al igual que con el mouse, cortamos para dejar solo el título
                    String soloNombre = seleccionCompleta.split(" - ")[0].trim();
                    
                    txtBuscador.setText(soloNombre);
                    popupMenu.setVisible(false);
                    e.consume(); // Evita que se disparen otros eventos involuntarios
                }
            }
        }
    }
        });
    }

    // Método de ejemplo para simular la procedencia de datos
  private static List<String> obtenerSugerenciasDesdeBD(String consulta) {
    List<String> filtradas = new ArrayList<>();
    
    // Consulta para traer coincidencias reales filtradas por título desde SQL Server
    String sql = "SELECT TOP 5 ID_COPIA_PELICULA, TITULO_PELICULA FROM dbo.VISTA_ALQUILERES_COMPLETO WHERE TITULO_PELICULA LIKE ?";
    
    try (java.sql.Connection con = ConexionBD.getInstance().getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
         
        // Configuramos el parámetro para que busque en cualquier parte del título
        ps.setString(1, "%" + consulta + "%");
        
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Formateamos el texto que aparecerá en tu lista desplegable estilo Google
                String sugerencia = rs.getString("TITULO_PELICULA") + " - ID Copia: " + rs.getInt("ID_COPIA_PELICULA");
                filtradas.add(sugerencia);
            }
        }
    } catch (java.sql.SQLException e) {
        System.out.println("Error al obtener sugerencias reales: " + e.getMessage());
    }
    
    return filtradas;
}
}