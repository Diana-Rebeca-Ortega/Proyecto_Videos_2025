package Vista.Alquileres;
import Controlador.AlquilerDAO; 
import Controlador.CopiaPeliculaDAO;
import Modelo.Alquiler;     
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Modelo.Pelicula;
import Vista.Alquileres.FormularioRealizarRenta;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class Panel_Alquileres extends javax.swing.JPanel {

    public Panel_Alquileres() {
        initComponents();
        cargarAlquileresATabla();
        aplicarEstiloTabla();       
    }
    private void aplicarEstiloTabla() {        
        tbla_alquileres.getTableHeader().setBackground(new Color(153, 102, 255)); // Morado/Lavanda
        tbla_alquileres.getTableHeader().setForeground(Color.WHITE); 
        tbla_alquileres.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "MX")); 
        
        tbla_alquileres.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
              final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(230, 230, 250)); // Fondo muy claro
                } else { c.setBackground(Color.WHITE);}
                if (isSelected) {            
                    c.setBackground(new Color(204, 153, 255)); // Morado/Lavanda más oscuro
                    c.setForeground(Color.BLACK); // Asegura que el texto sea visible
                } else { c.setForeground(Color.BLACK); }                
                final int COLUMNA_TARIFA_FINAL = 6;                
                if (column == COLUMNA_TARIFA_FINAL) {
                    try {
                        setText(value != null ? value.toString() : "");
                        setForeground(new Color(0, 150, 0)); // Color Verde
                        setHorizontalAlignment(RIGHT);                        
                    } catch (Exception ex) {
                        setForeground(Color.RED); 
                        setHorizontalAlignment(LEFT);
                    }
                } else {
                    setHorizontalAlignment(LEFT);
                }                
                return c;
            }
        });
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Encabezado_Alquileres = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_alquileres = new java.awt.Label();
        btnDevolucion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbla_alquileres = new javax.swing.JTable();
        btn_VistaCompleta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout());

        pnl_Encabezado_Alquileres.setBackground(new java.awt.Color(255, 255, 255));
        pnl_Encabezado_Alquileres.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        pnl_Encabezado_Alquileres.setMinimumSize(new java.awt.Dimension(0, 50));
        pnl_Encabezado_Alquileres.setPreferredSize(new java.awt.Dimension(0, 40));
        pnl_Encabezado_Alquileres.setLayout(null);

        txt_alquileres.setBackground(new java.awt.Color(255, 255, 255));
        txt_alquileres.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        txt_alquileres.setMaximumSize(new java.awt.Dimension(32767, 40));
        txt_alquileres.setText("GESTIÓN DE ALQUILERES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(txt_alquileres, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(txt_alquileres, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnl_Encabezado_Alquileres.add(jPanel2);
        jPanel2.setBounds(0, 0, 624, 59);

        btnDevolucion.setBackground(new java.awt.Color(255, 204, 0));
        btnDevolucion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDevolucion.setText("DEVOLUCIÓN DE PELICULAS");
        btnDevolucion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucionActionPerformed(evt);
            }
        });
        pnl_Encabezado_Alquileres.add(btnDevolucion);
        btnDevolucion.setBounds(700, 80, 290, 40);

        tbla_alquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Alquiler", "ID_Cliente", "ID_pelicula", "fecha_alquiler", "fecha_devolucion", "Estado", "Tarifa Final"
            }
        ));
        jScrollPane1.setViewportView(tbla_alquileres);

        pnl_Encabezado_Alquileres.add(jScrollPane1);
        jScrollPane1.setBounds(0, 125, 1060, 540);

        btn_VistaCompleta.setBackground(new java.awt.Color(255, 204, 0));
        btn_VistaCompleta.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_VistaCompleta.setText("VER ALQUILERES COMPLETO");
        btn_VistaCompleta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_VistaCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_VistaCompletaActionPerformed(evt);
            }
        });
        pnl_Encabezado_Alquileres.add(btn_VistaCompleta);
        btn_VistaCompleta.setBounds(700, 40, 290, 40);

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("NUEVO");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnl_Encabezado_Alquileres.add(jButton1);
        jButton1.setBounds(700, 0, 290, 40);

        add(pnl_Encabezado_Alquileres);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_VistaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_VistaCompletaActionPerformed
    int idSucursalDelUsuario = 0; 
    Vista_AlquileresCompletos vista = new Vista_AlquileresCompletos(
        (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this),
        true,
        idSucursalDelUsuario
    );    
    vista.setVisible(true);
    }//GEN-LAST:event_btn_VistaCompletaActionPerformed

    private void btnDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucionActionPerformed
    FormularioDevolverPelicula vistaDevolucion = new FormularioDevolverPelicula( (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), true);
    vistaDevolucion.setVisible(true);
    }//GEN-LAST:event_btnDevolucionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       FormularioRealizarRenta form = new FormularioRealizarRenta(null, true);
    form.setVisible(true);
    //Verificar si la renta fue exitosa y guardada
    if (form.isDatosGuardados()) {
        Alquiler nuevoAlquiler = form.getAlquiler(); // Contiene el ID de la copia
        AlquilerDAO daoAlquiler = new AlquilerDAO();        
        if (daoAlquiler.insertarAlquiler(nuevoAlquiler)) {
            int idCopiaRentada = nuevoAlquiler.getIdCopia();             
            CopiaPeliculaDAO daoCopia = new CopiaPeliculaDAO(); 
            boolean estadoCambiado = daoCopia.actualizarEstadoCopia(idCopiaRentada, "RENTADO"); 

            if (estadoCambiado) {
                JOptionPane.showMessageDialog(this, "Renta registrada con éxito y copia actualizada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarAlquileresATabla();                
            } else {
                JOptionPane.showMessageDialog(this, "Alerta: Renta registrada, pero falló el cambio de estado de la Copia ID: " + idCopiaRentada + ". Revise logs y base de datos.", "Alerta de Consistencia", JOptionPane.WARNING_MESSAGE);
                }
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar la renta. Revise logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_jButton1ActionPerformed
public void cargarAlquileresATabla() {
        // Modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_Alquiler");
        modelo.addColumn("ID_Cliente");
        modelo.addColumn("ID_CopiaPelicula");
        modelo.addColumn("Fecha_Alquiler");
        modelo.addColumn("Fecha_Devolucion");
        modelo.addColumn("Estado");
        modelo.addColumn("Tarifa Final");
        
        AlquilerDAO dao = new AlquilerDAO();
        List<Alquiler> alquileres = dao.obtenerTodosLosAlquileres();
        final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
        for (Alquiler a : alquileres) {
            Object[] fila = new Object[7]; 
            fila[0] = a.getIdAlquiler();
            fila[1] = a.getIdCliente();
            fila[2] = a.getIdPelicula();
            fila[3] = a.getFechaAlquiler();
            fila[4] = a.getFechaDevolucion();
            fila[5] = a.getEstado(); 
            fila[6] = currencyFormat.format(a.getCostoFinal());
            modelo.addRow(fila);
        }
        tbla_alquileres.setModel(modelo);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDevolucion;
    private javax.swing.JButton btn_VistaCompleta;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnl_Encabezado_Alquileres;
    private javax.swing.JTable tbla_alquileres;
    private java.awt.Label txt_alquileres;
    // End of variables declaration//GEN-END:variables
}
