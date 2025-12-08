package Vista.Sucursal;
import javax.swing.JOptionPane;
import Modelo.Sucursal; 
import Controlador.SucursalDAO; 
import Vista.Filtros.AlfanumericoFilter;
import Vista.Filtros.MaximoDigitosFilter;
import Vista.Filtros.SoloLetrasFilter;
import Vista.Filtros.SoloNumerosFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame; 
import javax.swing.text.AbstractDocument;

public class ModificacionesSucursal extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModificacionesSucursal.class.getName());
    private short idSucursal;
    private boolean datosGuardados = false;
    private Sucursal sucursal; 
   
   public ModificacionesSucursal(java.awt.Frame parent, boolean modal, short idSucursal) {
        super(parent, modal);
        this.idSucursal = idSucursal;
        initComponents();
        this.setLocationRelativeTo(parent);
        cargarDatosSucursal();
        aplicarFiltros();
        aplicarEstiloModerno();
    }
private void aplicarFiltros() {    
    ((AbstractDocument) caja_nombreSucursal.getDocument()).setDocumentFilter(new AlfanumericoFilter()); 
    ((AbstractDocument) caja_Telefono.getDocument()).setDocumentFilter(new MaximoDigitosFilter(10));
    ((AbstractDocument) caja_num_exterior.getDocument()).setDocumentFilter(new SoloNumerosFilter());
        // Calle, Colonia, Ciudad: Solo Letras
    SoloLetrasFilter letrasFilter = new SoloLetrasFilter();
    ((AbstractDocument) caja_calle.getDocument()).setDocumentFilter(letrasFilter);
    ((AbstractDocument) caja_colonia.getDocument()).setDocumentFilter(letrasFilter);
    ((AbstractDocument) caja_ciudad.getDocument()).setDocumentFilter(letrasFilter);
   
    MaximoDigitosFilter cpFilter = new MaximoDigitosFilter(5);
    ((AbstractDocument) caja_CP.getDocument()).setDocumentFilter(cpFilter);
   
}
    public boolean isDatosGuardados() {
        return datosGuardados;
    }
    private void cargarDatosSucursal() {
        SucursalDAO dao = new SucursalDAO();
        sucursal = dao.obtenerSucursalPorId(idSucursal);

        if (sucursal != null) {
            
            caja_nombreSucursal.setText(sucursal.getNombreSucursal());
            caja_Telefono.setText(sucursal.getNoTelefono());
            caja_num_exterior.setText(String.valueOf(sucursal.getNumeroExterior()));
            caja_calle.setText(sucursal.getCalle());
            caja_colonia.setText(sucursal.getColonia());
            caja_ciudad.setText(sucursal.getCiudad());
            caja_CP.setText(sucursal.getCp());
            
            // Seleccionar el estado correcto en el JComboBox
            combo_estados.setSelectedItem(sucursal.getEstado());
            
            // Opcional: Mostrar la fecha de registro si tienes el campo
            // jLabel16.setText(formatoFecha(sucursal.getFechaRegistro())); 
            
        } else {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la sucursal con ID: " + idSucursal, "Error de Carga", JOptionPane.ERROR_MESSAGE);
            this.dispose(); // Cierra si no se puede cargar
        }
    }
   private static final Color COLOR_FONDO_BLANCO = new Color(248, 248, 248); // Blanco Roto/Claro
private static final Color COLOR_TEXTO_GRIS = new Color(51, 51, 51); // Gris Oscuro para texto
private static final Color COLOR_GUARDAR = new Color(76, 175, 80); // Verde Material Design (para Guardar)
private static final Color COLOR_CANCELAR = new Color(244, 67, 54); // Rojo Material Design (para Cancelar)
private static final Color COLOR_BORDE_SUAVE = new Color(200, 200, 200);
private void aplicarEstiloModerno() {
        // 1. CONFIGURACIÓN DEL FONDO GENERAL DEL DIÁLOGO
        this.getContentPane().setBackground(COLOR_FONDO_BLANCO);
        // 2. CONFIGURACIÓN DEL TÍTULO Y ETIQUETAS
        // Título Principal "MODIFICAR SUCURSAL" (jLabel17)
        jLabel17.setFont(new Font("Segoe UI", Font.BOLD, 28)); 
        jLabel17.setForeground(COLOR_TEXTO_GRIS);

        // Etiqueta de Sección "Datos Generales" (jLabel10)
        jLabel10.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        jLabel10.setForeground(COLOR_TEXTO_GRIS); 
        
        // Etiqueta de Sección "Domicilio" (jLabel5)
        jLabel5.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel5.setForeground(COLOR_TEXTO_GRIS);

        // Etiquetas de Campos (Usando Font Sencilla)
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        
        // Datos Generales
        jLabel12.setFont(labelFont); // "Nombre Sucursal"
        jLabel12.setForeground(COLOR_TEXTO_GRIS);
        jLabel1.setFont(labelFont); // "Telefono"
        jLabel1.setForeground(COLOR_TEXTO_GRIS);

        // Domicilio
        jLabel13.setFont(labelFont); // "NO_Exterior"
        jLabel13.setForeground(COLOR_TEXTO_GRIS);
        jLabel7.setFont(labelFont); // "Ciudad"
        jLabel7.setForeground(COLOR_TEXTO_GRIS);
        jLabel6.setFont(labelFont); // "Calle"
        jLabel6.setForeground(COLOR_TEXTO_GRIS);
        jLabel9.setFont(labelFont); // "Estado"
        jLabel9.setForeground(COLOR_TEXTO_GRIS);
        jLabel14.setFont(labelFont); // "Colonia/Fraccionamiento"
        jLabel14.setForeground(COLOR_TEXTO_GRIS);
        jLabel8.setFont(labelFont); // "CP"
        jLabel8.setForeground(COLOR_TEXTO_GRIS);
        
        // Las etiquetas jLabel11 (Valor del ID) y jLabel4 (Datos Personales de Empleado) NO existen aquí y se eliminan.
        // Las etiquetas jLabel2, jLabel3, jLabel13, jLabel6 de Empleados se han mapeado/eliminado correctamente.

        // 3. CONFIGURACIÓN DE LOS BOTONES DE ACCIÓN
        javax.swing.border.Border padding = javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20);

        // Botón GUARDAR (Acción Positiva)
        btn_guardar.setBackground(COLOR_GUARDAR);
        btn_guardar.setForeground(Color.WHITE); 
        btn_guardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_guardar.setBorder(padding); 
        // Botón CANCELAR (Acción Negativa)
        btn_cancelar.setBackground(COLOR_CANCELAR);
        btn_cancelar.setForeground(Color.WHITE); 
        btn_cancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn_cancelar.setBorder(padding);         
        // 4. CONFIGURACIÓN DE CAMPOS DE ENTRADA
        javax.swing.border.Border lineBorder = javax.swing.BorderFactory.createLineBorder(COLOR_BORDE_SUAVE, 1);
        javax.swing.border.Border compoundBorder = javax.swing.BorderFactory.createCompoundBorder(
            lineBorder,
            javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5) // Padding interno
        );
        // Campos de Texto (JTextField)
        caja_nombreSucursal.setBackground(Color.WHITE);
        caja_nombreSucursal.setBorder(compoundBorder);
        
        caja_Telefono.setBackground(Color.WHITE);
        caja_Telefono.setBorder(compoundBorder);
        
        caja_num_exterior.setBackground(Color.WHITE);
        caja_num_exterior.setBorder(compoundBorder);
        
        caja_ciudad.setBackground(Color.WHITE);
        caja_ciudad.setBorder(compoundBorder);

        caja_calle.setBackground(Color.WHITE);
        caja_calle.setBorder(compoundBorder);
        
        caja_colonia.setBackground(Color.WHITE);
        caja_colonia.setBorder(compoundBorder);

        caja_CP.setBackground(Color.WHITE);
        caja_CP.setBorder(compoundBorder);

        // ComboBox
        combo_estados.setBackground(Color.WHITE);
        combo_estados.setForeground(COLOR_TEXTO_GRIS);
        combo_estados.setBorder(lineBorder); // Borde sutil
        
        // Los combo_rol y combo_sucursales se han eliminado ya que no son parte de esta interfaz.
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        caja_ciudad = new javax.swing.JTextField();
        combo_estados = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        caja_num_exterior = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        caja_nombreSucursal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        caja_Telefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        caja_colonia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        caja_calle = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        caja_CP = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        btn_cancelar.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        jLabel9.setText("Estado");

        caja_ciudad.setText("jTextField2");

        combo_estados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Opcion", "Zacatecas", "Jalisco", "Durango", " " }));
        combo_estados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_estadosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Domicilio");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Datos Generales");

        caja_num_exterior.setText("12");
        caja_num_exterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_num_exteriorActionPerformed(evt);
            }
        });

        jLabel6.setText("Calle");

        jLabel7.setText("Ciudad");

        jLabel12.setText("Nombre Sucursal");

        caja_nombreSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_nombreSucursalActionPerformed(evt);
            }
        });

        jLabel1.setText("Telefono");

        jLabel13.setText("NO_Exterior");

        caja_Telefono.setText("1234567890");

        jLabel14.setText("Colonia/Fraccionamiento");

        caja_colonia.setText("jTextField1");

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("MODIFICAR SUCURSAL");

        btn_guardar.setBackground(new java.awt.Color(153, 255, 153));
        btn_guardar.setText("GUARDAR");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        caja_calle.setText("jTextField1");

        jLabel8.setText("CP");

        caja_CP.setText("12345");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(419, 419, 419)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(81, 81, 81)
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
                                        .addGap(0, 99, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12)
                                            .addComponent(caja_nombreSucursal)
                                            .addComponent(jLabel1)
                                            .addComponent(caja_Telefono, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(caja_nombreSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(caja_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void combo_estadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_estadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_estadosActionPerformed

    private void caja_num_exteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_num_exteriorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_num_exteriorActionPerformed
 
    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
                                           
        String nombreSucursalStr = caja_nombreSucursal.getText().trim();
        String telefonoStr = caja_Telefono.getText().trim();
        int numExteriorInt;
        String calleStr = caja_calle.getText().trim();
        String coloniaStr = caja_colonia.getText().trim();
        String ciudadStr = caja_ciudad.getText().trim();
        String cpStr = caja_CP.getText().trim();
        String estadoStr = combo_estados.getSelectedItem().toString();

        // 1. Validaciones
        if (nombreSucursalStr.isEmpty() || telefonoStr.isEmpty() || caja_num_exterior.getText().trim().isEmpty() || calleStr.isEmpty() || coloniaStr.isEmpty() || ciudadStr.isEmpty() || cpStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de texto son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (estadoStr.equals("Seleccione una Opcion")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un 'Estado'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Intentar convertir el Número Exterior a int
            numExteriorInt = Integer.parseInt(caja_num_exterior.getText().trim());

            // 2. Actualizar el objeto sucursal con los nuevos valores
            sucursal.setNombreSucursal(nombreSucursalStr);
            sucursal.setNoTelefono(telefonoStr);
            sucursal.setNumeroExterior(numExteriorInt); 
            sucursal.setCalle(calleStr);
            sucursal.setColonia(coloniaStr);
            sucursal.setCiudad(ciudadStr);
            sucursal.setEstado(estadoStr);
            sucursal.setCp(cpStr);
            // El ID (NoSucursal) se mantiene sin cambios
            
            // 3. Llamar al DAO para modificar la sucursal en la base de datos
            SucursalDAO dao = new SucursalDAO();
            if (dao.modificarSucursal(sucursal)) {
                
                datosGuardados = true; // Éxito
                this.dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al modificar la sucursal en la base de datos.", "Error de Modificación", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El número exterior y el CP deben ser valores numéricos válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error de formato al intentar parsear número: " + e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error general al intentar modificar Sucursal: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void caja_nombreSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_nombreSucursalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_nombreSucursalActionPerformed

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JTextField caja_CP;
    private javax.swing.JTextField caja_Telefono;
    private javax.swing.JTextField caja_calle;
    private javax.swing.JTextField caja_ciudad;
    private javax.swing.JTextField caja_colonia;
    private javax.swing.JTextField caja_nombreSucursal;
    private javax.swing.JTextField caja_num_exterior;
    private javax.swing.JComboBox<String> combo_estados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
