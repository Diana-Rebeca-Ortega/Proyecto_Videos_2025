/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Empleados;
import Modelo.Empleado;
import Controlador.EmpleadoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem; 
import javax.swing.JPopupMenu; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.Frame; 
import javax.swing.SwingUtilities;
/**
 *
 * @author Diana
 */
public class Panel_Empleados extends javax.swing.JPanel {
private JPopupMenu popupMenu;
    private JMenuItem menuItemModificar;
    private JMenuItem menuItemEliminar;
    /**
     * Creates new form Panel_Empleados
     */
    public Panel_Empleados() {
        initComponents();
        inicializarPopupMenu();
        agregarListeners(); 
        cargarDatosTabla();
    }
    private void inicializarPopupMenu() {
        popupMenu = new JPopupMenu();

        // Crear la opción Modificar
        menuItemModificar = new JMenuItem("Modificar Empleado");
        popupMenu.add(menuItemModificar);

        // Crear la opción Eliminar
        menuItemEliminar = new JMenuItem("Eliminar Empleado");
        popupMenu.add(menuItemEliminar);
    }
    private void agregarListeners() {
        
        // Listener para Modificar
        menuItemModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarModificarEmpleado();
            }
        });

        // Listener para Eliminar
        menuItemEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tbl_empleados.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // Obtener el ID de la primera columna (columna 0)
                    int idEmpleado = (int) tbl_empleados.getValueAt(filaSeleccionada, 0); 
                    System.out.println("ID de Empleado seleccionado para eliminar: " + idEmpleado);
                    eliminarEmpleado(idEmpleado);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un empleado para eliminar.");
                }
            }
        });
        
        // Listener para mostrar el menú contextual al hacer clic derecho en la tabla
        tbl_empleados.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                manejarMouseClick(evt);
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                manejarMouseClick(evt);
            }
            
            private void manejarMouseClick(java.awt.event.MouseEvent evt) {
                 // Verificar si es el evento que dispara el pop-up (clic derecho)
                if (evt.isPopupTrigger()) {
                    int row = tbl_empleados.rowAtPoint(evt.getPoint());

                    if (row != -1) {
                        // Forzar la selección de la fila bajo el cursor
                        tbl_empleados.setRowSelectionInterval(row, row);
                        
                        // Mostrar el menú en las coordenadas del clic
                        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }
            }
        });
    }
    
    private void ejecutarModificarEmpleado() {
        int filaSeleccionada = tbl_empleados.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Obtener el ID de Empleado (asumo que es de tipo INT)
            int idEmpleado = (int) tbl_empleados.getValueAt(filaSeleccionada, 0);
            
            // Obtener el Frame padre
            Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(Panel_Empleados.this);
            
            // 1. Abrir el formulario de Modificación (DEBES TENER LA CLASE 'ModificacionesEmpleado' CREADA)
            // Asegúrate de que tu constructor acepte (Frame, boolean, int idEmpleado)
           ModificacionesEmpleado formModificar = new ModificacionesEmpleado(framePadre, true, idEmpleado);
           formModificar.setVisible(true);
            // 2. Verificar si se guardaron los datos al cerrar
            if (formModificar.isDatosGuardados()) { // DEBES TENER ESTE MÉTODO EN EL JDialog
                JOptionPane.showMessageDialog(this, "Empleado modificado con éxito.");
                cargarDatosTabla(); // Recargar la tabla
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para modificar.");
        }
    }
    
    // Método para la lógica de Eliminar (NUEVO, adaptado de Sucursal)
    private void eliminarEmpleado(int idEmpleado) {
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro de que desea eliminar al empleado con ID: " + idEmpleado + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            EmpleadoDAO dao = new EmpleadoDAO();
            if (dao.eliminarEmpleado(idEmpleado)) { // DEBES TENER ESTE MÉTODO EN EL DAO
                JOptionPane.showMessageDialog(this, "Empleado eliminado exitosamente.");
                // Refrescar la tabla
                cargarDatosTabla(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_empleados = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        label1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        label1.setText("GESTIÓN DE EMPLEADOS");
        jPanel1.add(label1, java.awt.BorderLayout.WEST);

        jButton1.setText("NUEVO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, java.awt.BorderLayout.CENTER);

        add(jPanel1, java.awt.BorderLayout.NORTH);

        tbl_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_Empleado", "Nombre", "Apellido 1", "Apellido 2", "categoria", "ID_Sucursal"
            }
        ));
        jScrollPane1.setViewportView(tbl_empleados);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
FormularioAltasEmpleado formEmpleado = new FormularioAltasEmpleado(null, true);
formEmpleado.setVisible(true); // Bloquea la ventana principal hasta que se cierre

// 2. Verificar si el usuario presionó Guardar en el formulario
if (formEmpleado.isDatosGuardados()) {
    // Obtener el objeto Empleado lleno con los datos del formulario
    Empleado nuevoEmpleado = formEmpleado.getEmpleado();

    // Crear una instancia del objeto de Acceso a Datos (DAO)
    EmpleadoDAO dao = new EmpleadoDAO();

    // 3. Intentar insertar el empleado en la base de datos
    if (dao.insertarEmpleado(nuevoEmpleado)) {
        // Éxito: Mostrar mensaje y recargar la tabla de la ventana principal
        // El ID_Empleado se habrá recuperado en el DAO si es autoincremental
        JOptionPane.showMessageDialog(this, 
            "Empleado insertado con éxito. ID: " + nuevoEmpleado.getIdEmpleado(), 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
        // 4. Recargar la tabla (método que debe existir en tu clase PanelEmpleados)
        cargarDatosTabla();
    } else {
        // Error: Mostrar mensaje de fallo
        JOptionPane.showMessageDialog(this, 
            "Error al insertar el empleado. Revisa la consola/logs.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed
private void cargarDatosTabla() {
    try {
        // 1. Definir el modelo de la tabla para Empleados
        DefaultTableModel modelo = new DefaultTableModel();
        // Definir las columnas de la tabla Empleados
        modelo.addColumn("ID_Empleado"); // Clave Primaria (PK)
        modelo.addColumn("Nombre(s)");
        modelo.addColumn("Apellido 1 (FName)");
        modelo.addColumn("Apellido 2 (LName)");
        modelo.addColumn("Categoría/Rol");
        modelo.addColumn("ID_Sucursal"); // Clave Foránea (FK)

        // 2. Obtener los datos actualizados de Empleados
        EmpleadoDAO dao = new EmpleadoDAO();
        List<Modelo.Empleado> listaEmpleados = dao.obtenerTodosLosEmpleados();

        // 3. Llenar el modelo con los nuevos datos
        for (Modelo.Empleado e : listaEmpleados) {
            modelo.addRow(new Object[]{
                e.getIdEmpleado(), // Asegúrate que el Modelo.Empleado tenga este método
                e.getNombre(),
                e.getFName(),      // Apellido 1
                e.getLName(),      // Apellido 2
                e.getCategoria(),
                e.getIdSucursal()  // Clave Foránea a Sucursal
            });
        }
        
        // 4. Asignar el nuevo modelo (con los datos) a la tabla de Empleados
        // Reemplaza 'tablaSucursales' con el nombre de tu JTable de Empleados
        tbl_empleados.setModel(modelo);
        
    } catch (Exception ex) {
        System.err.println("Error al cargar datos en la tabla de Empleados: " + ex.getMessage());
        // Puedes añadir aquí un JOptionPane para notificar al usuario de un error de conexión
        // JOptionPane.showMessageDialog(this, "Error al cargar la tabla de empleados.", "Error BD", JOptionPane.ERROR_MESSAGE);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable tbl_empleados;
    // End of variables declaration//GEN-END:variables
}
