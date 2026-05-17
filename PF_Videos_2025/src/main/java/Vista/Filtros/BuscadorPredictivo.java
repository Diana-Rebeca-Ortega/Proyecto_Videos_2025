package Vista.Filtros;
import ConneccionBD.ConexionBD;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BuscadorPredictivo {

    public static void registrarAutocompletado(JTextField txtBuscador) {
        JPopupMenu popupMenu = new JPopupMenu();
        JList<String> listResultados = new JList<>();
        JScrollPane scrollPane = new JScrollPane(listResultados);
        
        popupMenu.add(scrollPane);
        popupMenu.setFocusable(false);

        final boolean[] programmaticChange = {false};

        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (programmaticChange[0]) {
                    return;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP || 
                    e.getKeyCode() == KeyEvent.VK_DOWN || 
                    e.getKeyCode() == KeyEvent.VK_ENTER ||
                    e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    return;
                }

                String textoFiltro = txtBuscador.getText().trim();
                
                if (textoFiltro.isEmpty()) {
                    popupMenu.setVisible(false);
                    return;
                }

                // Traer datos de la BD
                List<String> sugerencias = obtenerSugerenciasDesdeBD(textoFiltro);

                if (!sugerencias.isEmpty()) {
                    DefaultListModel<String> model = new DefaultListModel<>();
                    for (String sugerencia : sugerencias) {
                        model.addElement(sugerencia);
                    }
                    listResultados.setModel(model);

                    // --- TRUCO DE RENDERIZADO FORZADO PARA SWING ---
                    scrollPane.setPreferredSize(new java.awt.Dimension(txtBuscador.getWidth(), 140));
                    popupMenu.pack(); 
                    
                    if (popupMenu.isShowing()) {
                        popupMenu.setVisible(false);
                    }

                    SwingUtilities.invokeLater(() -> {
                        if (!txtBuscador.isShowing()) return;
                        
                        popupMenu.show(txtBuscador, 0, txtBuscador.getHeight());
                        txtBuscador.requestFocus();
                    });
                    
                } else {
                    popupMenu.setVisible(false);
                }
            }
        });

        // EVENTO 1: Selección con CLIC del MOUSE
        listResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (listResultados.getSelectedValue() != null) {
                    String seleccionCompleta = listResultados.getSelectedValue();
                    String soloNombre = seleccionCompleta.split(" - ")[0].trim();

                    programmaticChange[0] = true;
                    txtBuscador.setText(soloNombre);
                    popupMenu.setVisible(false);
                    programmaticChange[0] = false;
                    
                    txtBuscador.requestFocus();
                }
            }
        });

        // EVENTO 2: Navegación por TECLADO
        txtBuscador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (popupMenu.isShowing()) {
                    int index = listResultados.getSelectedIndex();
                    
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        listResultados.setSelectedIndex(index + 1);
                        listResultados.ensureIndexIsVisible(index + 1);
                        e.consume();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        listResultados.setSelectedIndex(index - 1);
                        listResultados.ensureIndexIsVisible(index - 1);
                        e.consume();
                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (listResultados.getSelectedValue() != null) {
                            String seleccionCompleta = listResultados.getSelectedValue();
                            String soloNombre = seleccionCompleta.split(" - ")[0].trim();

                            programmaticChange[0] = true;
                            txtBuscador.setText(soloNombre);
                            popupMenu.setVisible(false);
                            programmaticChange[0] = false;
                            
                            e.consume();
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        popupMenu.setVisible(false);
                        e.consume();
                    }
                }
            }
        });
    } // <-- Esta es la llave que faltaba para cerrar el método registrarAutocompletado

    private static List<String> obtenerSugerenciasDesdeBD(String consulta) {
    List<String> filtradas = new ArrayList<>();
    
    // Cadena de consulta utilizando tu vista o tablas correspondientes
    // Buscamos coincidencias tanto en el título como en el ID
    String sql = "SELECT TOP 5 CP_ID_COPIA_PELICULA, TITULO_PELICULA " +
                 "FROM dbo.VISTA_ALQUILERES_COMPLETO " +
                 "WHERE TITULO_PELICULA LIKE ? OR CAST(CP_ID_COPIA_PELICULA AS VARCHAR) LIKE ?";
    
    try (java.sql.Connection con = ConneccionBD.ConexionBD.getInstance().getConnection();
         java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
        String parametroBusqueda = "%" + consulta + "%";
        ps.setString(1, parametroBusqueda);
        ps.setString(2, parametroBusqueda);
        
        try (java.sql.ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // Extraemos los datos de la fila actual
                String idCopia = rs.getString("CP_ID_COPIA_PELICULA");
                String titulo = rs.getString("TITULO_PELICULA");
                
                // Formateamos la sugerencia de manera idéntica a tu diseño de interfaz
                filtradas.add(titulo + " - ID: " + idCopia);
            }
        }
        
    } catch (java.sql.SQLException e) {
        System.err.println("Error al consultar las sugerencias de películas: " + e.getMessage());
        e.printStackTrace();
    }
    
    return filtradas;
}
}