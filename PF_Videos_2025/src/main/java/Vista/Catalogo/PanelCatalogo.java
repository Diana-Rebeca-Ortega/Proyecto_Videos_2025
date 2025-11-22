/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Catalogo;
import Controlador.PeliculaDAO; // Asegúrate de importar tu DAO de Películas
import Modelo.Pelicula;         // Asegúrate de importar tu modelo Pelicula
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Diana
 */
public class PanelCatalogo extends javax.swing.JPanel {
private JPopupMenu popupMenu;
    private JMenuItem menuItemModificar;
    private JMenuItem menuItemEliminar;
    /**
     * Creates new form PanelCatalogo
     */
    public PanelCatalogo() {
        initComponents();
        inicializarMenuContextual(); 
        cargarPeliculasATabla(); 
        añadirListenerTabla();
    }
private void inicializarMenuContextual() {
        popupMenu = new JPopupMenu();
        menuItemModificar = new JMenuItem("Modificar Película");
        menuItemEliminar = new JMenuItem("Eliminar Película");
        
        popupMenu.add(menuItemModificar);
        popupMenu.add(menuItemEliminar);
        
        configurarAccionesMenu();
    }
private void configurarAccionesMenu() {
        // Acción para Modificar
        menuItemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarModificarPelicula();
            }
        });

        // Acción para Eliminar
        menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarEliminarPelicula();
            }
        });
    }
    private void añadirListenerTabla() {
        tablaPELICULA.addMouseListener(new MouseAdapter() {
            
            private void manejarPopupTrigger(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int fila = tablaPELICULA.rowAtPoint(e.getPoint());
                    
                    // Forzar la selección de la fila del clic derecho
                    if (fila >= 0 && fila < tablaPELICULA.getRowCount()) {
                        tablaPELICULA.setRowSelectionInterval(fila, fila);
                    }
                    
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                manejarPopupTrigger(e);
            }
            
            @Override
            public void mouseReleased(MouseEvent e) {
                manejarPopupTrigger(e);
            }
        });
    }
    private void ejecutarModificarPelicula() {
        int filaSeleccionada = tablaPELICULA.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        try {
            // 1. Obtener el ID de la película (Asumiendo que es la Columna 0)
            int idPelicula = (int) tablaPELICULA.getValueAt(filaSeleccionada, 0); 
            
            // 2. Obtener la referencia al Frame padre
            Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(this);
            
            // 3. Abrir el JDialog ModificacionesPelicula
            // Asegúrate de que ModificacionesPelicula tenga un constructor (Frame, boolean, int)
            ModificacionesPelicula formModificar = new ModificacionesPelicula(framePadre, true, idPelicula); 
          formModificar.setVisible(true);

            // 4. Recargar la tabla si hubo cambios
            if (formModificar.isDatosGuardados()) { 
                cargarPeliculasATabla(); 
                JOptionPane.showMessageDialog(this, "Película modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (ClassCastException e) {
            JOptionPane.showMessageDialog(this, "Error: El ID de la película no es un número entero. Revisar columna 0.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al intentar abrir la ventana de modificación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void ejecutarEliminarPelicula() {
        int filaSeleccionada = tablaPELICULA.getSelectedRow();
        if (filaSeleccionada == -1) return; 
        
        // 1. Obtener el ID de la película
        int idPelicula = (int) tablaPELICULA.getValueAt(filaSeleccionada, 0); 
        
        // 2. Pide confirmación
        int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar la película con ID: " + idPelicula + "?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            // 3. Ejecutar el DELETE usando el PeliculaDAO
            PeliculaDAO dao = new PeliculaDAO();
            
            if (dao.eliminarPelicula(idPelicula)) { 
                JOptionPane.showMessageDialog(this, "Película eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPeliculasATabla(); // Recargar la tabla
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la película. Revise logs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void cargarPeliculasATabla() {
        // Debes ajustar el número de columnas (8) y los nombres para que coincidan con tu JTable
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID_Pelicula");
        modelo.addColumn("Título");
        modelo.addColumn("Categoría");
        modelo.addColumn("Director");
        modelo.addColumn("Alquiler");
        modelo.addColumn("Coste");
        modelo.addColumn("Stock Total");
        // La columna "acciones Editar/Eliminar" no se llena con datos del modelo, es visual

        PeliculaDAO dao = new PeliculaDAO();
        List<Pelicula> peliculas = dao.obtenerTodasLasPeliculas();
        
        for (Pelicula p : peliculas) {
            Object[] fila = new Object[7]; // 7 columnas de datos reales + 1 visual
            
            fila[0] = p.getIdPelicula(); 
            fila[1] = p.getTitulo(); 
            fila[2] = p.getCategoria(); 
            fila[3] = p.getDirector();
            fila[4] = p.getPrecioAlquiler(); // Asumiendo getPrecioAlquiler()
            fila[5] = p.getCosteAdquisicion(); // Asumiendo getCosteAdquisicion()
            fila[6] = p.getStockTotal(); // Asumiendo getStockTotal()
            
            modelo.addRow(fila);
        }
        
        tablaPELICULA.setModel(modelo);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encabezado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPELICULA = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        encabezado.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CATÁLOGO DE PELÍCULAS");
        encabezado.add(jLabel1, java.awt.BorderLayout.WEST);

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        encabezado.add(jPanel1, java.awt.BorderLayout.EAST);

        add(encabezado, java.awt.BorderLayout.NORTH);

        tablaPELICULA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pelicula", "titulo", "categoria", "director", "alquiler", "coste", "stock_total", "acciones Editar/Eliminar"
            }
        ));
        jScrollPane1.setViewportView(tablaPELICULA);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       FormularioAltasPelicula form = new FormularioAltasPelicula(null, true); 
        form.setVisible(true);
        
        if (form.isDatosGuardados()) {
            Pelicula nuevaPelicula = form.getPelicula(); 

            PeliculaDAO dao = new PeliculaDAO();
            int stockInicial = nuevaPelicula.getStockTotal();
            int idSucursal = 1;
            
            if (dao.insertarPelicula(nuevaPelicula, stockInicial, idSucursal)) {
               JOptionPane.showMessageDialog(this, "Película y stock inicial generados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                // 3. Recargar la tabla
                cargarPeliculasATabla();
            } else {
               JOptionPane.showMessageDialog(this, "Error al insertar la película o generar stock. Revisa logs.", "Error", JOptionPane.ERROR_MESSAGE);    }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel encabezado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPELICULA;
    // End of variables declaration//GEN-END:variables
}
