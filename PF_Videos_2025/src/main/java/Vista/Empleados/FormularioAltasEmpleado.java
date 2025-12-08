/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.Empleados;
import Modelo.Empleado;
import Modelo.Sucursal;
import Controlador.SucursalDAO;
import Vista.Filtros.MaximoDigitosFilter;
import Vista.Filtros.SoloLetrasFilter;
import Vista.Filtros.SoloNumerosFilter;
import java.awt.Color;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import java.util.logging.Logger;
/**
 *
 * @author Diana
 */
public class FormularioAltasEmpleado extends javax.swing.JDialog {
    private Empleado empleado;
    private boolean datosGuardados = false;
    private List<Sucursal> listaSucursales;
    private static final Logger logger = Logger.getLogger(FormularioAltasEmpleado.class.getName());
    /**
     * Creates new form FormularioAltasEmpleado
     */
   public FormularioAltasEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        aplicarEstiloModerno();
        Date fechaActual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        jLabel16.setText(format.format(fechaActual)); 
        aplicarFiltros();
        cargarComboBoxes();
    }
   private void aplicarFiltros() {
        SoloLetrasFilter letrasFilter = new SoloLetrasFilter();        
        // Asumiendo que 'nombre', 'apellido1' (FName) y 'apellido2' (LName) deben ser solo letras
        ((AbstractDocument) caja_nombre.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_apellido1.getDocument()).setDocumentFilter(letrasFilter);
        ((AbstractDocument) caja_apellido2.getDocument()).setDocumentFilter(letrasFilter);
        }
   private void cargarComboBoxes() {
        // Cargar Sucursales
        SucursalDAO sucursalDAO = new SucursalDAO();
        listaSucursales = sucursalDAO.obtenerTodasLasSucursales();
        DefaultComboBoxModel<String> sucursalModel = new DefaultComboBoxModel<>();
        sucursalModel.addElement("Seleccione una Sucursal");        
        for (Sucursal s : listaSucursales) {
            sucursalModel.addElement(s.getNoSucursal() + " - " + s.getNombreSucursal());
        }
        combo_sucursales.setModel(sucursalModel);     
        DefaultComboBoxModel<String> rolModel = new DefaultComboBoxModel<>();
        rolModel.addElement("Seleccione una Categoría");
        rolModel.addElement("Gerente");
        rolModel.addElement("Cajero");
        rolModel.addElement("Vendedor");
        combo_rol.setModel(rolModel);
    }    
    public Empleado getEmpleado() {
        return empleado;
    }
public boolean isDatosGuardados() {
        return datosGuardados;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        caja_nombre = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        caja_apellido1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        caja_apellido2 = new javax.swing.JTextField();
        combo_rol = new javax.swing.JComboBox<>();
        btn_altasClientes = new javax.swing.JButton();
        btn_cancelarRegistroCliente = new javax.swing.JButton();
        combo_sucursales = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Empresa");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Datos Generales");

        jLabel6.setText("Sucursal");

        jLabel12.setText("Nombre(s)");

        jLabel13.setText("Categoria/Rol");

        jLabel15.setText("Fecha de Registro");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("DD/MM/AAAA");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setText("REGISTRAR NUEVO EMPLEADO");

        jLabel2.setText("Apellido 1");

        jLabel3.setText("Apellido2");

        caja_apellido2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caja_apellido2ActionPerformed(evt);
            }
        });

        combo_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opción", "Administrador", "Empleado" }));

        btn_altasClientes.setBackground(new java.awt.Color(153, 255, 153));
        btn_altasClientes.setText("GUARDAR");
        btn_altasClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_altasClientesActionPerformed(evt);
            }
        });

        btn_cancelarRegistroCliente.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelarRegistroCliente.setText("CANCELAR");
        btn_cancelarRegistroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarRegistroClienteActionPerformed(evt);
            }
        });

        combo_sucursales.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "0001", "0000", "0009" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(caja_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(caja_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(41, 41, 41)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(caja_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)
                                    .addComponent(combo_rol, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_altasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caja_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caja_apellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caja_apellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88)))
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
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_cancelarRegistroCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_altasClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_altasClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_altasClientesActionPerformed
      // 1. Obtener y validar datos
        String nombreStr = caja_nombre.getText().trim();
        String fNameStr = caja_apellido1.getText().trim(); // Apellido 1 -> FName
        String lNameStr = caja_apellido2.getText().trim(); // Apellido 2 -> LName
        String categoriaStr = combo_rol.getSelectedItem() != null ? combo_rol.getSelectedItem().toString() : "";
        String sucursalSeleccionadaStr = combo_sucursales.getSelectedItem() != null ? combo_sucursales.getSelectedItem().toString() : "";
        // Validar campos de texto vacíos
        if (nombreStr.isEmpty() || fNameStr.isEmpty() || lNameStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar Nombre y ambos Apellidos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Validar ComboBox Rol/Categoría
        if (categoriaStr.equals("Seleccione una Categoría") || categoriaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una 'Categoría/Rol'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Validar ComboBox Sucursal
        if (sucursalSeleccionadaStr.equals("Seleccione una Sucursal") || sucursalSeleccionadaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una 'Sucursal'.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }        
        try {
            // 2. Obtener ID_Sucursal (Entero)
            // El formato es: "ID - NombreSucursal". Necesitamos el ID.
            int idSucursal;
            try {
                // Parseamos solo la primera parte de la cadena, antes del " - "
                String idStr = sucursalSeleccionadaStr.split(" - ")[0];
                idSucursal = Integer.parseInt(idStr);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener el ID de la sucursal seleccionada.", "Error Interno", JOptionPane.ERROR_MESSAGE);
                logger.severe("Error al parsear ID de sucursal: " + ex.getMessage());
                return;
            }
            // 3. Crear el objeto Empleado y asignar los valores
            empleado = new Empleado();
            empleado.setNombre(nombreStr);
            empleado.setFName(fNameStr);
            empleado.setLName(lNameStr);
            empleado.setCategoria(categoriaStr);
            empleado.setIdSucursal(idSucursal); // ID_SUCURSAL
            // 4. Marcar como guardado y cerrar
            datosGuardados = true;
            this.dispose(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear el objeto Empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            logger.severe("Error al intentar crear objeto Empleado: " + e.getMessage());
        }
    }//GEN-LAST:event_btn_altasClientesActionPerformed

    private void btn_cancelarRegistroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarRegistroClienteActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_cancelarRegistroClienteActionPerformed

    private void caja_apellido2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caja_apellido2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_caja_apellido2ActionPerformed

    private final Color COLOR_FONDO_BLANCO = new Color(255, 255, 255);
    private final Color COLOR_TEXTO_GRIS = new Color(47, 79, 79);
    private final Color COLOR_GUARDAR = new Color(34, 139, 34); // Verde Oscuro
    private final Color COLOR_CANCELAR = new Color(220, 20, 60); // Rojo Fuerte (para CANCELAR)
   private void aplicarEstiloModerno() {
        // 1. CONFIGURACIÓN DEL FONDO GENERAL DEL DIÁLOGO
        // Cambia el fondo del JDialog (JDialog se refiere a 'this')
        this.getContentPane().setBackground(COLOR_FONDO_BLANCO);        
        // 2. CONFIGURACIÓN DEL TÍTULO Y TEXTOS INFORMATIVOS        
        // Título Principal
        jLabel17.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        jLabel17.setForeground(COLOR_TEXTO_GRIS);        
        // Etiquetas de Sección (Datos Generales, Empresa)
        jLabel10.setForeground(COLOR_TEXTO_GRIS);
        jLabel5.setForeground(COLOR_TEXTO_GRIS);
        // Etiquetas de Campos (Nombre, Apellido, Rol, Sucursal)
        // Aplicamos el mismo color a todas las etiquetas
        jLabel12.setForeground(COLOR_TEXTO_GRIS);
        jLabel2.setForeground(COLOR_TEXTO_GRIS);
        jLabel3.setForeground(COLOR_TEXTO_GRIS);
        jLabel13.setForeground(COLOR_TEXTO_GRIS);
        jLabel6.setForeground(COLOR_TEXTO_GRIS);
        jLabel15.setForeground(COLOR_TEXTO_GRIS); // Etiqueta Fecha

        // Etiqueta de la Fecha (para que se lea bien sobre el fondo)
        jLabel16.setForeground(COLOR_TEXTO_GRIS);
        
        // 3. CONFIGURACIÓN DE LOS BOTONES DE ACCIÓN
        
        // Botón GUARDAR (Acción Positiva: Verde Oscuro)
        btn_altasClientes.setBackground(COLOR_GUARDAR);
        btn_altasClientes.setForeground(COLOR_FONDO_BLANCO);
        btn_altasClientes.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        // Puedes cambiar el borde a uno más plano o eliminarlo si usaste el editor
        // btn_altasClientes.setBorder(null); 
        
        // Botón CANCELAR (Acción Negativa: Rojo Fuerte)
        btn_cancelarRegistroCliente.setBackground(COLOR_CANCELAR);
        btn_cancelarRegistroCliente.setForeground(COLOR_FONDO_BLANCO);
        btn_cancelarRegistroCliente.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        // btn_cancelarRegistroCliente.setBorder(null);

        // 4. CONFIGURACIÓN DE CAMPOS DE ENTRADA (Opcional, pero mejora la estética)
        caja_nombre.setBackground(COLOR_FONDO_BLANCO);
        caja_apellido1.setBackground(COLOR_FONDO_BLANCO);
        caja_apellido2.setBackground(COLOR_FONDO_BLANCO);
        combo_rol.setBackground(COLOR_FONDO_BLANCO);
        combo_sucursales.setBackground(COLOR_FONDO_BLANCO);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_altasClientes;
    private javax.swing.JButton btn_cancelarRegistroCliente;
    private javax.swing.JTextField caja_apellido1;
    private javax.swing.JTextField caja_apellido2;
    private javax.swing.JTextField caja_nombre;
    private javax.swing.JComboBox<String> combo_rol;
    private javax.swing.JComboBox<String> combo_sucursales;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
