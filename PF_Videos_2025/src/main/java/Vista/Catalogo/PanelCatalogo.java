package Vista.Catalogo;
import Controlador.PeliculaDAO; 
import Modelo.Pelicula;         
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelCatalogo extends javax.swing.JPanel {
private JPopupMenu popupMenu;
    private JMenuItem menuItemModificar;
    private JMenuItem menuItemEliminar;
    private JMenuItem menuItemCopias;   
    public PanelCatalogo() {
        initComponents();
        inicializarMenuContextual(); 
        cargarPeliculasATabla(); 
        añadirListenerTabla();
        configurarRenderizadorPrecios();
    }
private void inicializarMenuContextual() {
        popupMenu = new JPopupMenu();
        menuItemModificar = new JMenuItem("Modificar Película");
        menuItemEliminar = new JMenuItem("Eliminar Película");
        menuItemCopias = new JMenuItem("Ver Copias");
        
        popupMenu.add(menuItemModificar);
        popupMenu.add(menuItemEliminar);
         popupMenu.add(menuItemCopias);
        
        configurarAccionesMenu();
    }
private void configurarAccionesMenu() {
        menuItemModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarModificarPelicula();
            }
        });
        menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarEliminarPelicula();
            }
        });       
        menuItemCopias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                ejecutarVerCopiaPelicula();             
            }
        });
    }
    private void añadirListenerTabla() {
        tablaPELICULA.addMouseListener(new MouseAdapter() {            
            private void manejarPopupTrigger(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    int fila = tablaPELICULA.rowAtPoint(e.getPoint());
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
            int idPelicula = (int) tablaPELICULA.getValueAt(filaSeleccionada, 0);             
            Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(this);            
            ModificacionesPelicula formModificar = new ModificacionesPelicula(framePadre, true, idPelicula); 
            formModificar.setVisible(true);

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
        int idPelicula = (int) tablaPELICULA.getValueAt(filaSeleccionada, 0);         
        int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de que desea eliminar la película con ID: " + idPelicula + "?", 
                "Confirmar Eliminación", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            PeliculaDAO dao = new PeliculaDAO();            
            if (dao.eliminarPelicula(idPelicula)) { 
                JOptionPane.showMessageDialog(this, "Película eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarPeliculasATabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la película. Revise logs.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void ejecutarVerCopiaPelicula() {
        int filaSeleccionada = tablaPELICULA.getSelectedRow();
        final int ID_SUCURSAL_ACTUAL =0;
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        try {         
            int idPelicula = (int) tablaPELICULA.getValueAt(filaSeleccionada, 0); 
            Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(this);            
          CopiasDePelicula cdp = new CopiasDePelicula(framePadre, true, idPelicula , ID_SUCURSAL_ACTUAL);
          cdp.setVisible(true);
            if (cdp.isDatosGuardados()) { 
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
    public void cargarPeliculasATabla() {
    tablaPELICULA.getTableHeader().setBackground(new Color(235, 235, 235)); 
    tablaPELICULA.getTableHeader().setForeground(new Color(47, 79, 79)); 
    tablaPELICULA.getTableHeader().setFont(new java.awt.Font("Microsoft YaHei Light", java.awt.Font.BOLD, 12));
    tablaPELICULA.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           if (row % 2 == 0) {
             c.setBackground(new Color(255, 255, 255));
        } else {  
            c.setBackground(new Color(245, 245, 245)); 
        }
        if (isSelected) {           
            c.setBackground(new Color(251, 190, 79)); 
        }
        return c;
    }
});
        PeliculaDAO dao = new PeliculaDAO();
        DefaultTableModel modeloCompleto = dao.cargarDatosTabla(null);
        tablaPELICULA.setModel(modeloCompleto);
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPELICULA = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 255));

        tablaPELICULA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Pelicula", "titulo", "categoria", "director", "alquiler", "coste", "stock_total"
            }
        ));
        jScrollPane1.setViewportView(tablaPELICULA);

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 100, 1100, 310);

        jComboBox1.setBackground(new java.awt.Color(251, 190, 79));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar Por Categoria ", "Acción", "Aventura", "Ciencia Ficción", "Comedia", "Drama", "Fantasía", "Terror", "Musical", "Documental", "Otro" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1);
        jComboBox1.setBounds(930, 60, 170, 30);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        jLabel1.setText("CATÁLOGO DE PELÍCULAS");
        jPanel1.add(jLabel1);

        jButton1.setBackground(new java.awt.Color(251, 190, 79));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rollo-de-pelicula.png"))); // NOI18N
        jButton1.setText("AÑADIR");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1190, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    FormularioAltasPelicula form = new FormularioAltasPelicula(null, true); 
    configurarRenderizadorPrecios();
        form.setVisible(true);        
        if (form.isDatosGuardados()) {
            Pelicula nuevaPelicula = form.getPelicula(); 
            PeliculaDAO dao = new PeliculaDAO();
            int stockInicial = nuevaPelicula.getStockTotal();
            int idSucursal = 1;            
            if (dao.insertarPelicula(nuevaPelicula, stockInicial, idSucursal)) {
               JOptionPane.showMessageDialog(this, "Película y stock inicial generados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
               cargarPeliculasATabla();
            } else {
               JOptionPane.showMessageDialog(this, "Error al insertar la película o generar stock. Revisa logs.", "Error", JOptionPane.ERROR_MESSAGE);    }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    PeliculaDAO dao = new PeliculaDAO(); 
    configurarRenderizadorPrecios();
    DefaultTableModel nuevoModelo;
    String categoriaSeleccionada = (String) jComboBox1.getSelectedItem();
    if (categoriaSeleccionada.equals("Todas las Categorías")) {      
        nuevoModelo = dao.cargarDatosTabla(null); 
    } else {
        nuevoModelo = dao.cargarDatosTabla(categoriaSeleccionada);
    }
    tablaPELICULA.setModel(nuevoModelo);
    }//GEN-LAST:event_jComboBox1ActionPerformed
private void configurarRenderizadorPrecios() {
        final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "MX")); 
        DefaultTableCellRenderer precioRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(255, 255, 255));
                } else {  
                    c.setBackground(new Color(245, 245, 245)); 
                }
                if (isSelected) {            
                    c.setBackground(new Color(251, 190, 79)); 
                }                
                final int COLUMNA_ALQUILER = 4;
                final int COLUMNA_COSTE = 5;                
                if (column == COLUMNA_ALQUILER || column == COLUMNA_COSTE) {
                    try {
                        Double precio = null;
                        if (value instanceof Number) {
                            precio = ((Number) value).doubleValue();
                        } else if (value instanceof String) {                            
                            precio = Double.parseDouble((String) value);
                        }                        
                        if (precio != null) {
                            setText(currencyFormat.format(precio));
                            setForeground(new Color(0, 150, 0));
                        } else {
                            setText(value != null ? value.toString() : "");
                            setForeground(Color.BLACK);
                        }
                    } catch (NumberFormatException ex) {
                        setText(value != null ? value.toString() : "Error");
                        setForeground(Color.RED); 
                    }
                    setHorizontalAlignment(RIGHT);
                } else {
                    setText(value != null ? value.toString() : "");
                    setForeground(Color.BLACK);
                    setHorizontalAlignment(LEFT);
                }
                return c;
            }
        };
        try {
            tablaPELICULA.getColumnModel().getColumn(4).setCellRenderer(precioRenderer); 
            tablaPELICULA.getColumnModel().getColumn(5).setCellRenderer(precioRenderer); 
        } catch (ArrayIndexOutOfBoundsException e) {           
            System.err.println("Error al aplicar renderizador: El modelo de la tabla no tiene suficientes columnas.");
        }
    }
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPELICULA;
    // End of variables declaration//GEN-END:variables
}
