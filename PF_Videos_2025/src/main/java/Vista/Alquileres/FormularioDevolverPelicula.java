package Vista.Alquileres;

import Controlador.AlquilerDAO;
import Controlador.VistaAlquilerDevolucionDAO;
import Modelo.VistaAlquilerDevolucion;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormularioDevolverPelicula extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioDevolverPelicula.class.getName());
    private AlquilerDAO alquilerDAO;
    private VistaAlquilerDevolucionDAO alquilerDevolucionDAO;
    private javax.swing.JPopupMenu popupMenuDevolucion;
    private javax.swing.JMenuItem miDevolver;
    public FormularioDevolverPelicula(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.alquilerDAO = new AlquilerDAO();
        this.alquilerDevolucionDAO = new VistaAlquilerDevolucionDAO();
        initComponents();
        configurarPopupMenu();
        cargarDatosTabla();
    }
private void cargarDatosTabla() {
        if (alquilerDAO == null) { return; }
        try {
            List<VistaAlquilerDevolucion> lista = alquilerDevolucionDAO.obtenerAlquileresPendientes();
            String[] columnas = {
            "ID_Alquiler", 
            "No_Cliente",      
            "Cliente",         
            "Título Película", 
            "ID_Película",      
            "Fecha Alquiler",  
            "Fecha Vencimiento", 
            "Estado Actual", 
            "ID_Copia Película",
            "Estado de Entrega UI" 
        };
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0); 
            for (VistaAlquilerDevolucion item : lista) {
                Object[] fila = new Object[10]; 
                fila[0] = item.getIdAlquiler();
                fila[1] = item.getNombreCliente(); 
                fila[2] = item.getTituloPelicula(); 
                fila[3] = item.getIdPelicula();
                fila[4] = item.getFechaAlquiler();
               fila[5] = item.getFechaDevolucion();
                fila[6] = item.getEstadoEntregaUI();  
                fila[7] = item.getEstadoActual();     
            fila[8] = item.getIdCopiaPelicula();    
            fila[9] = item.getNoCliente();
                modelo.addRow(fila);
            } jTable1.setModel(modelo);            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los alquileres: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, "Error al cargar la tabla de alquileres", e);
        }
    }
private void configurarPopupMenu() {
    // 1. Inicializar el menú emergente
    popupMenuDevolucion = new javax.swing.JPopupMenu();
    miDevolver = new javax.swing.JMenuItem("Registrar Devolución");
    // 2. Añadir el Listener al elemento del menú
    miDevolver.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            // Llama al método que contendrá la lógica de devolución
            ejecutarDevolucionSeleccionada(); 
        }
    });    
    // 3. Agregar el elemento al menú
    popupMenuDevolucion.add(miDevolver);    
    // 4. Añadir el MouseListener a la tabla para detectar el clic derecho
    jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            // isPopupTrigger() detecta el clic derecho específico de cada SO
         if (evt.isPopupTrigger()) {                
                // Seleccionar la fila donde se hizo clic para trabajar con ella
                int r = jTable1.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < jTable1.getRowCount()) {
                    jTable1.setRowSelectionInterval(r, r);
                } else {
                    jTable1.clearSelection();
                }                
                popupMenuDevolucion.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
    });
}
private void ejecutarDevolucionSeleccionada() {
    int selectedRow = jTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fila para registrar la devolución.");
        return;
    }
    int idAlquiler = (int) jTable1.getValueAt(selectedRow, 0); 
       int idCopiaPelicula = (int) jTable1.getValueAt(selectedRow, 8); 
       System.out.println(idCopiaPelicula+"el id de pelicula ");
    int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Confirmar devolución del Alquiler ID: " + idAlquiler + "?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);

    if (respuesta == JOptionPane.YES_OPTION) {
        try {
            // Llama al método del DAO que implementaste
            boolean exito = alquilerDAO.registrarDevolucion(idAlquiler, idCopiaPelicula);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Devolución registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla(); // Recargar la tabla para mostrar que el ítem desapareció
            } else {
                JOptionPane.showMessageDialog(this, "Fallo al registrar la devolución.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        textField1 = new java.awt.TextField();
        btn_buscarPelicula = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("DEVOLVER PELICULA");

        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });

        btn_buscarPelicula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/busqueda.png"))); // NOI18N
        btn_buscarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarPeliculaActionPerformed(evt);
            }
        });

        label1.setText("Ingrese el ID de la copia de pelicula");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Alquiler", "Cliente", "ID_CopiaPelicula", "Fecha Alquiler", "Fecha Devolucion", "Entrega de Pelicula", "Estado de Entrega"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_buscarPelicula))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 424, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_buscarPelicula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarPeliculaActionPerformed
       
    }//GEN-LAST:event_btn_buscarPeliculaActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscarPelicula;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
