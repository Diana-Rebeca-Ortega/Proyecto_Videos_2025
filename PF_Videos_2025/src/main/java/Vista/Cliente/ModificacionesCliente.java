package Vista.Cliente;
import Modelo.Cliente;
import javax.swing.JOptionPane;
import Controlador.ClienteDAO;
import Vista.Filtros.MaximoDigitosFilter;
import Vista.Filtros.SoloLetrasFilter;
import Vista.Filtros.SoloNumerosFilter;
import javax.swing.text.AbstractDocument;
import java.awt.event.ActionEvent;

public class ModificacionesCliente extends javax.swing.JDialog {
    private Cliente cliente; 
    private final int idCliente;
    private boolean datosGuardados = false; // Bandera para saber si se guardó
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModificacionesCliente.class.getName());
private static final java.awt.Color COLOR_PRIMARIO = new java.awt.Color(0, 51, 153); // Azul oscuro
private static final java.awt.Color COLOR_EXITO = new java.awt.Color(51, 153, 51); // Verde
private static final java.awt.Color COLOR_PELIGRO = new java.awt.Color(255, 51, 51); // Rojo
private static final java.awt.Color COLOR_TEXTO_OSCURO = java.awt.Color.BLACK;
private static final java.awt.Font FUENTE_TITULO = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28);
private static final java.awt.Font FUENTE_SUBTITULO = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
private static final java.awt.Font FUENTE_ETIQUETA = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
    public ModificacionesCliente(java.awt.Frame parent, boolean modal, int idCliente) {
        super(parent, modal);
        initComponents();
        this.idCliente = idCliente;
        aplicarEstiloModificaciones();
        cargarDatosCliente(this.idCliente);
        this.getContentPane().setBackground(new java.awt.Color(230, 230, 250));
         this.setLocationRelativeTo(parent); 
    }
    private void aplicarEstiloModificaciones() {
       
        SoloLetrasFilter letrasFilter = new SoloLetrasFilter();
        SoloNumerosFilter numerosFilter = new SoloNumerosFilter();
        MaximoDigitosFilter cpFilter = new MaximoDigitosFilter(5); // Asumiendo que tienes esta clase
        
        ((AbstractDocument) caja_nombre.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_apellido1.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_apellido2.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_ciudad.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_calle.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_colonia.getDocument()).setDocumentFilter(letrasFilter);
        
        ((AbstractDocument) caja_num_exterior.getDocument()).setDocumentFilter(numerosFilter);       
        ((AbstractDocument) caja_CP.getDocument()).setDocumentFilter(cpFilter);
        
        jLabel17.setFont(FUENTE_TITULO);
        jLabel17.setForeground(COLOR_PRIMARIO);
        jLabel4.setFont(FUENTE_SUBTITULO); // Datos Personales
        jLabel4.setForeground(COLOR_PRIMARIO);
        jLabel5.setFont(FUENTE_SUBTITULO); // Domicilio
        jLabel5.setForeground(COLOR_PRIMARIO);
        jLabel10.setFont(FUENTE_SUBTITULO); // Etiqueta ID_Cliente:
        jLabel10.setForeground(COLOR_PRIMARIO);
        jLabel11.setFont(FUENTE_SUBTITULO);
        jLabel11.setForeground(COLOR_TEXTO_OSCURO);
        
        javax.swing.JLabel[] labels = {jLabel1, jLabel2, jLabel3, jLabel6, jLabel7, jLabel8, jLabel9, jLabel13, jLabel14};
        java.awt.Color colorEtiqueta = COLOR_TEXTO_OSCURO;
        java.awt.Font fontEtiqueta = FUENTE_ETIQUETA;

        for (javax.swing.JLabel label : labels) {
            label.setFont(fontEtiqueta);
            label.setForeground(colorEtiqueta);
        }
        javax.swing.JTextField[] textFields = {caja_nombre, caja_apellido1, caja_apellido2, caja_calle, caja_ciudad, caja_colonia, caja_CP, caja_num_exterior};
        for (javax.swing.JTextField field : textFields) {           
            field.setForeground(COLOR_TEXTO_OSCURO);
            field.setBackground(java.awt.Color.WHITE);
        }

        btn_cambiarCliente.setBackground(COLOR_EXITO);
        btn_cambiarCliente.setForeground(java.awt.Color.WHITE);
        btn_cambiarCliente.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        
        btn_cancelarCAMBIOSCliente.setBackground(COLOR_PELIGRO);
        btn_cancelarCAMBIOSCliente.setForeground(java.awt.Color.WHITE);
        btn_cancelarCAMBIOSCliente.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
    }
    public boolean isDatosGuardados() {
    return datosGuardados;
}   
    public void cargarDatosCliente(int idCliente) {
    Controlador.ClienteDAO dao = new Controlador.ClienteDAO();
   
    try {       
        this.cliente = dao.obtenerClientePorId(idCliente);
        if (cliente != null) {
            jLabel11.setText(String.valueOf(idCliente));
            caja_nombre.setText(cliente.getNombre());
            caja_apellido1.setText(cliente.getApellido1());
            caja_apellido2.setText(cliente.getApellido2());
            caja_num_exterior.setText(String.valueOf(cliente.getNo_exterior())); 
            
            caja_calle.setText(cliente.getCalle());
            caja_colonia.setText(cliente.getColonia());
            caja_ciudad.setText(cliente.getCiudad());
            caja_CP.setText(cliente.getCp());
            combo_estados.setSelectedItem(cliente.getEstado());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente con ID " + idCliente + " no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        combo_estados = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btn_cambiarCliente = new javax.swing.JButton();
        btn_cancelarCAMBIOSCliente = new javax.swing.JButton();
        caja_ciudad = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        caja_nombre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        caja_num_exterior = new javax.swing.JTextField();
        caja_apellido1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        caja_apellido2 = new javax.swing.JTextField();
        caja_colonia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        caja_calle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        caja_CP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("MODIFICAR DATOS CLIENTE");

        jLabel9.setText("Estado");

        combo_estados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opcion", "Zacatecas", "Jalisco", "Durango", " " }));
        combo_estados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_estadosActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre(s)");

        btn_cambiarCliente.setBackground(new java.awt.Color(153, 255, 153));
        btn_cambiarCliente.setText("GUARDAR");
        btn_cambiarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarClienteActionPerformed(evt);
            }
        });

        btn_cancelarCAMBIOSCliente.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelarCAMBIOSCliente.setText("CANCELAR");
        btn_cancelarCAMBIOSCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarCAMBIOSClienteActionPerformed(evt);
            }
        });

        caja_ciudad.setText("jTextField2");

        jLabel2.setText("Apellido 1");

        caja_nombre.setText("jTextField1");
        caja_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_nombreActionPerformed(evt);
            }
        });

        jLabel13.setText("NO_Exterior");

        caja_num_exterior.setText("jTextField1");
        caja_num_exterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_num_exteriorActionPerformed(evt);
            }
        });

        caja_apellido1.setText("jTextField1");

        jLabel3.setText("Apellido2");

        jLabel14.setText("Colonia/Fraccionamiento");

        caja_apellido2.setText("jTextField1");

        caja_colonia.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Datos Personales");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Domicilio");

        jLabel6.setText("Calle");

        jLabel7.setText("Ciudad");

        caja_calle.setText("jTextField1");

        jLabel8.setText("CP");

        caja_CP.setText("jTextField1");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("ID_Cliente:");

        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(caja_colonia, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(caja_calle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(caja_num_exterior, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(combo_estados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(caja_ciudad)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(caja_CP, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_cancelarCAMBIOSCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_cambiarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(caja_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(caja_apellido1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(caja_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(caja_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_ciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_num_exterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2)
                        .addComponent(caja_calle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(9, 9, 9)
                        .addComponent(caja_colonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(2, 2, 2)
                        .addComponent(combo_estados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_CP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_cancelarCAMBIOSCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_cambiarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo_estadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_estadosActionPerformed
       
    }//GEN-LAST:event_combo_estadosActionPerformed

    private void btn_cambiarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarClienteActionPerformed
        if (caja_nombre.getText().isEmpty() || caja_apellido1.getText().isEmpty()  ) {
            JOptionPane.showMessageDialog(this, "El Nombre y Apellido1 son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (caja_num_exterior.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Número Exterior es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (caja_CP.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El Código Postal (CP) es obligatorio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (combo_estados.getSelectedItem().toString().equals("Item 1")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un 'Estado'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {        
        if (this.cliente == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay datos de cliente cargados para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.cliente.setNombre(caja_nombre.getText());
        this.cliente.setApellido1(caja_apellido1.getText());
        this.cliente.setApellido2(caja_apellido2.getText());
        this.cliente.setNo_exterior(Integer.parseInt(caja_num_exterior.getText().trim()));
        this.cliente.setCalle(caja_calle.getText());
        this.cliente.setColonia(caja_colonia.getText());
        this.cliente.setCiudad(caja_ciudad.getText());
        this.cliente.setCp(caja_CP.getText());
        this.cliente.setEstado(String.valueOf(combo_estados.getSelectedItem()));
        ClienteDAO dao = new ClienteDAO();
        boolean modificadoExitosamente = dao.modificarCliente(this.cliente); 
        if (modificadoExitosamente) {
            datosGuardados = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "La modificación en la base de datos falló.", "Error BD", JOptionPane.ERROR_MESSAGE);
        }        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error de formato en campos numéricos (Exterior o CP).", "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error durante la modificación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_btn_cambiarClienteActionPerformed

    private void btn_cancelarCAMBIOSClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarCAMBIOSClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarCAMBIOSClienteActionPerformed

    private void caja_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_nombreActionPerformed

    private void caja_num_exteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_num_exteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_num_exteriorActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarCliente;
    private javax.swing.JButton btn_cancelarCAMBIOSCliente;
    private javax.swing.JTextField caja_CP;
    private javax.swing.JTextField caja_apellido1;
    private javax.swing.JTextField caja_apellido2;
    private javax.swing.JTextField caja_calle;
    private javax.swing.JTextField caja_ciudad;
    private javax.swing.JTextField caja_colonia;
    private javax.swing.JTextField caja_nombre;
    private javax.swing.JTextField caja_num_exterior;
    private javax.swing.JComboBox<String> combo_estados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
