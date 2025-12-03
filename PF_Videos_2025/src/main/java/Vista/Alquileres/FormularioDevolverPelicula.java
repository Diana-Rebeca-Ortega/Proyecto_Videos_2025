/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.Alquileres;

import Controlador.VistaAlquilerDevolucionDAO;
import Modelo.VistaAlquilerDevolucion;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diana
 */
public class FormularioDevolverPelicula extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioDevolverPelicula.class.getName());
    private VistaAlquilerDevolucionDAO alquilerDAO;
  
    public FormularioDevolverPelicula(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.alquilerDAO = new VistaAlquilerDevolucionDAO();
        initComponents();
        cargarDatosTabla();
    }
private void cargarDatosTabla() {
        if (alquilerDAO == null) {
            return; // No se puede cargar sin conexión/DAO
        }
        
        try {
            // 1. Obtener la lista de alquileres pendientes
            List<VistaAlquilerDevolucion> lista = alquilerDAO.obtenerAlquileresPendientes();
            
            // 2. Definir las cabeceras (deben coincidir con el orden de tu JTable)
            String[] columnas = {
                "ID_Alquiler", "Cliente", "Título Película", "ID_Película", 
                "Fecha Alquiler", "Fecha Devolución", "Estado de Entrega"
            };
            
            // Usaremos un modelo de datos que se pueda modificar
            DefaultTableModel modelo = new DefaultTableModel(columnas, 0); 
            
            // 3. Llenar las filas del modelo con los datos del DAO
            for (VistaAlquilerDevolucion item : lista) {
                // Asegúrate de que los campos coinciden con el orden de 'columnas'
                Object[] fila = new Object[7]; 
                fila[0] = item.getIdAlquiler();
                // Usamos el NOMBRE_CLIENTE completo (C.nombre || ' ' || C.apellido1)
                fila[1] = item.getNombreCliente(); 
                fila[2] = item.getTituloPelicula(); 
                fila[3] = item.getIdPelicula();
                fila[4] = item.getFechaAlquiler();
                fila[5] = item.getFechaDevolucion(); // Será NULL para los pendientes
                // Columna ENUM calculada por la vista V_ALQUILERES_CON_ESTADO
                fila[6] = item.getEstadoEntregaUI(); 
                
                modelo.addRow(fila);
            }
            
            // 4. Asignar el nuevo modelo a la JTable
            jTable1.setModel(modelo);
            
            // Opcional: Configurar el Pop-up Menu aquí después de cargar los datos
            // configurarPopupMenu(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los alquileres: " + e.getMessage(), "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, "Error al cargar la tabla de alquileres", e);
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
                "ID_Alquiler", "Cliente", "ID_CopiaPelicula", "Fecha Alquiler", "Fecha Devolucion", "Entrega de Pelicula", "Title 7"
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
                        .addGap(0, 133, Short.MAX_VALUE)))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buscarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarPeliculaActionPerformed
       
    }//GEN-LAST:event_btn_buscarPeliculaActionPerformed

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormularioDevolverPelicula dialog = new FormularioDevolverPelicula(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscarPelicula;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.awt.Label label1;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
