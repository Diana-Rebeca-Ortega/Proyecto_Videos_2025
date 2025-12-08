package Vista.Empleados;
import Modelo.Empleado;
import Controlador.EmpleadoDAO;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import Vista.Filtros.SoloLetrasFilter; 
import java.awt.Color;
import java.awt.Font;
import javax.swing.text.AbstractDocument;

public class ModificacionesEmpleado extends javax.swing.JDialog {
    private int idEmpleadoActual;
    private boolean datosGuardados = false; // Estado para el formulario principal
    private Empleado empleadoModificar; // Objeto a modificar
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ModificacionesEmpleado.class.getName());

    public ModificacionesEmpleado(java.awt.Frame parent, boolean modal, int idEmpleado) {
        super(parent, modal);
        initComponents();
        aplicarFiltros();
        this.setLocationRelativeTo(null);
        this.idEmpleadoActual = idEmpleado; 
        jLabel11.setText(String.valueOf(idEmpleado)); 
        cargarDatosEmpleado(idEmpleado);
        aplicarEstiloModerno();
    }
private void cargarDatosEmpleado(int id) {
           Controlador.EmpleadoDAO dao = new Controlador.EmpleadoDAO(); 
        empleadoModificar = dao.obtenerEmpleadoPorId(id); 

    if (empleadoModificar != null) {
                caja_nombre.setText(empleadoModificar.getNombre());
        caja_apellido1.setText(empleadoModificar.getFName()); 
        caja_apellido2.setText(empleadoModificar.getLName());       
        combo_rol.setSelectedItem(empleadoModificar.getCategoria());
        combo_sucursales.setSelectedItem(String.valueOf(empleadoModificar.getIdSucursal()));
                
    } else {
        JOptionPane.showMessageDialog(this, "Error: No se encontró el empleado con ID: " + id, "Error de Carga", JOptionPane.ERROR_MESSAGE);
        this.dispose(); // Cierra si no se puede cargar
    }
}
public boolean isDatosGuardados() { 
        return datosGuardados;
    }
private void aplicarFiltros() {
    SoloLetrasFilter letrasFilter = new SoloLetrasFilter();
    // Aplicar filtro de solo letras a los campos de Nombre y Apellidos
    ((AbstractDocument) caja_nombre.getDocument()).setDocumentFilter(letrasFilter);
    ((AbstractDocument) caja_apellido1.getDocument()).setDocumentFilter(letrasFilter);
    ((AbstractDocument) caja_apellido2.getDocument()).setDocumentFilter(letrasFilter);
}   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        caja_nombre = new javax.swing.JTextField();
        caja_apellido1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        caja_apellido2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combo_rol = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        combo_sucursales = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("MODIFICAR EMPLEADO");

        btn_guardar.setBackground(new java.awt.Color(153, 255, 153));
        btn_guardar.setText("GUARDAR");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_cancelar.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("ID_Empleado:");

        jLabel11.setText("jLabel11");

        jLabel1.setText("Nombre(s)");

        jLabel2.setText("Apellido 1");

        caja_nombre.setText("jTextField1");
        caja_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_nombreActionPerformed(evt);
            }
        });

        caja_apellido1.setText("jTextField1");

        jLabel3.setText("Apellido2");

        caja_apellido2.setText("jTextField1");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Datos Personales");

        combo_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Administrador", "Empleado" }));

        jLabel13.setText("Categoria/Rol");

        combo_sucursales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "0001", "0000", "0009" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Empresa");

        jLabel6.setText("Sucursal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13)
                            .addComponent(combo_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(caja_nombre)
                                    .addComponent(caja_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3)
                                    .addComponent(caja_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(53, 53, 53)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(combo_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(28, 28, 28)))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
    String nombreStr = caja_nombre.getText().trim();
    String apellido1Str = caja_apellido1.getText().trim();
    String apellido2Str = caja_apellido2.getText().trim();
    String rolStr = combo_rol.getSelectedItem().toString();
    
    // Obtener el ID de Sucursal como String y convertirlo a INT para el modelo.
    String idSucursalStr = combo_sucursales.getSelectedItem().toString();
    int idSucursalInt;
    // 2. VALIDACIONES DE CAMPOS   
    if (nombreStr.isEmpty() || apellido1Str.isEmpty() || apellido2Str.isEmpty() || idSucursalStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Todos los campos de texto son obligatorios.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (rolStr.equals("Seleccione una opción")) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una 'Categoría/Rol'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }    
    if (idSucursalStr.equals("Seleccione una opcion")) {
         JOptionPane.showMessageDialog(this, "Debe seleccionar una 'Sucursal'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }    
    try {      
        idSucursalInt = Integer.parseInt(idSucursalStr); 
        empleadoModificar.setNombre(nombreStr);
        empleadoModificar.setFName(apellido1Str);
        empleadoModificar.setLName(apellido2Str);
        empleadoModificar.setCategoria(rolStr);
        empleadoModificar.setIdSucursal(idSucursalInt);       
        Controlador.EmpleadoDAO dao = new Controlador.EmpleadoDAO();        
        if (dao.modificarEmpleado(empleadoModificar)) {
            datosGuardados = true;             
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al modificar el empleado en la base de datos.", "Error de Modificación", JOptionPane.ERROR_MESSAGE);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "El ID de Sucursal debe ser un valor numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        // Usa el logger que ya tienes en la clase:
        logger.severe("Error de formato al intentar parsear ID de Sucursal: " + e.getMessage());
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        logger.severe("Error general al intentar modificar Empleado: " + e.getMessage());
    }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void caja_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_nombreActionPerformed
private final Color COLOR_FONDO_BLANCO = new Color(255, 255, 255);
    private final Color COLOR_TEXTO_GRIS = new Color(47, 79, 79); // Gris Oscuro
    private final Color COLOR_GUARDAR = new Color(34, 139, 34);    // Verde Oscuro
    private final Color COLOR_CANCELAR = new Color(220, 20, 60);  // Rojo Fuerte
    
    private void aplicarEstiloModerno() {
        // 1. CONFIGURACIÓN DEL FONDO GENERAL DEL DIÁLOGO
        this.getContentPane().setBackground(COLOR_FONDO_BLANCO);
        
        // 2. CONFIGURACIÓN DEL TÍTULO Y DATOS INFORMATIVOS
        
        // Título Principal "MODIFICAR EMPLEADO"
        jLabel17.setFont(new Font("Segoe UI", Font.BOLD, 24));
        jLabel17.setForeground(COLOR_TEXTO_GRIS);
        
        // Etiqueta ID (Etiquetas informativas)
        jLabel10.setForeground(COLOR_TEXTO_GRIS); // "ID_Empleado:"
        jLabel11.setForeground(COLOR_TEXTO_GRIS); // Valor del ID

        // Etiquetas de Sección
        jLabel4.setForeground(COLOR_TEXTO_GRIS); // "Datos Personales"
        jLabel5.setForeground(COLOR_TEXTO_GRIS); // "Empresa"

        // Etiquetas de Campos (Nombre, Apellido, Rol, Sucursal)
        jLabel1.setForeground(COLOR_TEXTO_GRIS);
        jLabel2.setForeground(COLOR_TEXTO_GRIS);
        jLabel3.setForeground(COLOR_TEXTO_GRIS);
        jLabel13.setForeground(COLOR_TEXTO_GRIS); // "Categoría/Rol"
        jLabel6.setForeground(COLOR_TEXTO_GRIS); // "Sucursal"

        // 3. CONFIGURACIÓN DE LOS BOTONES DE ACCIÓN
        
        // Botón GUARDAR (Acción Positiva: Verde Oscuro)
        btn_guardar.setBackground(COLOR_GUARDAR);
        btn_guardar.setForeground(COLOR_FONDO_BLANCO);
        btn_guardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // Opcional: Eliminar bordes para un aspecto más plano (flat design)
        // btn_guardar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Botón CANCELAR (Acción Negativa/Retorno: Rojo Fuerte)
        btn_cancelar.setBackground(COLOR_CANCELAR);
        btn_cancelar.setForeground(COLOR_FONDO_BLANCO);
        btn_cancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        // btn_cancelar.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // 4. CONFIGURACIÓN DE CAMPOS DE ENTRADA
        caja_nombre.setBackground(COLOR_FONDO_BLANCO);
        caja_apellido1.setBackground(COLOR_FONDO_BLANCO);
        caja_apellido2.setBackground(COLOR_FONDO_BLANCO);
        
        combo_rol.setBackground(COLOR_FONDO_BLANCO);
        combo_rol.setForeground(COLOR_TEXTO_GRIS);
        
        combo_sucursales.setBackground(COLOR_FONDO_BLANCO);
        combo_sucursales.setForeground(COLOR_TEXTO_GRIS);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JTextField caja_apellido1;
    private javax.swing.JTextField caja_apellido2;
    private javax.swing.JTextField caja_nombre;
    private javax.swing.JComboBox<String> combo_rol;
    private javax.swing.JComboBox<String> combo_sucursales;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
