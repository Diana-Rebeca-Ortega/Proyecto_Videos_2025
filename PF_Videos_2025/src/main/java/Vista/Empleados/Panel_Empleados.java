package Vista.Empleados;
import Modelo.Empleado;
import Controlador.EmpleadoDAO;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem; 
import javax.swing.JPopupMenu; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.awt.Frame; 
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;

public class Panel_Empleados extends javax.swing.JPanel {
    private JPopupMenu popupMenu;
    private JMenuItem menuItemModificar;
    private JMenuItem menuItemEliminar;
   
    public Panel_Empleados() {
        initComponents();
        inicializarPopupMenu();
        agregarListeners(); 
        cargarDatosTabla();
    }
    private void inicializarPopupMenu() {
        popupMenu = new JPopupMenu();
        menuItemModificar = new JMenuItem("Modificar Empleado");
        popupMenu.add(menuItemModificar);
        menuItemEliminar = new JMenuItem("Eliminar Empleado");
        popupMenu.add(menuItemEliminar);
    }
    private void agregarListeners() {
        menuItemModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarModificarEmpleado();
            }
        });
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
                if (evt.isPopupTrigger()) {
                    int row = tbl_empleados.rowAtPoint(evt.getPoint());
                    if (row != -1) {
                        tbl_empleados.setRowSelectionInterval(row, row);
                        popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    }
                }
            }
        });
    }
    
    private void ejecutarModificarEmpleado() {
        int filaSeleccionada = tbl_empleados.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) tbl_empleados.getValueAt(filaSeleccionada, 0);
            Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(Panel_Empleados.this);
           ModificacionesEmpleado formModificar = new ModificacionesEmpleado(framePadre, true, idEmpleado);
           formModificar.setVisible(true);
            if (formModificar.isDatosGuardados()) { 
                JOptionPane.showMessageDialog(this, "Empleado modificado con éxito.");
                cargarDatosTabla(); 
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
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_empleados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout());

        label1.setFont(new java.awt.Font("Microsoft YaHei Light", 1, 24)); // NOI18N
        label1.setText("GESTIÓN DE EMPLEADOS");
        jPanel1.add(label1);

        jButton1.setBackground(new java.awt.Color(251, 190, 79));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/agregar-usuario.png"))); // NOI18N
        jButton1.setText("NUEVO");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 255));

        tbl_empleados.setBackground(new java.awt.Color(204, 204, 255));
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
        tbl_empleados.setSelectionBackground(new java.awt.Color(251, 190, 79));
        jScrollPane1.setViewportView(tbl_empleados);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setBackground(new java.awt.Color(1, 20, 124));
        jLabel1.setText("Presione click derecho en un apartado de la tabla para ver más funciones");
        add(jLabel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
FormularioAltasEmpleado formEmpleado = new FormularioAltasEmpleado(null, true);
formEmpleado.setVisible(true); 
if (formEmpleado.isDatosGuardados()) {
    Empleado nuevoEmpleado = formEmpleado.getEmpleado();
    EmpleadoDAO dao = new EmpleadoDAO();
    if (dao.insertarEmpleado(nuevoEmpleado)) {
        JOptionPane.showMessageDialog(this, 
            "Empleado insertado con éxito. ID: " + nuevoEmpleado.getIdEmpleado(), 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        cargarDatosTabla();
    } else {
        JOptionPane.showMessageDialog(this, 
            "Error al insertar el empleado. Revisa la consola/logs.", 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    }//GEN-LAST:event_jButton1ActionPerformed
private void cargarDatosTabla() {
    try {
     tbl_empleados.getTableHeader().setBackground(new Color(235, 235, 235)); 
tbl_empleados.getTableHeader().setForeground(new Color(47, 79, 79)); 
tbl_empleados.getTableHeader().setFont(new java.awt.Font("Microsoft YaHei Light", java.awt.Font.BOLD, 12));
tbl_empleados.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           if (row % 2 == 0) {
             c.setBackground(new Color(255, 255, 255));
        } else {  
            c.setBackground(new Color(245, 245, 245)); 
        }

        // 2. Mantener la selección correcta
        if (isSelected) {
            c.setBackground(new Color(251, 190, 79)); 
        }

        return c;
    }
});
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_Empleado"); // Clave Primaria (PK)
        modelo.addColumn("Nombre(s)");
        modelo.addColumn("Apellido 1 ");
        modelo.addColumn("Apellido 2 ");
        modelo.addColumn("Categoría/Rol");
        modelo.addColumn("ID_Sucursal"); // Clave Foránea (FK)

        EmpleadoDAO dao = new EmpleadoDAO();
        List<Modelo.Empleado> listaEmpleados = dao.obtenerTodosLosEmpleados();
       
        for (Modelo.Empleado e : listaEmpleados) {
            modelo.addRow(new Object[]{
                e.getIdEmpleado(), 
                e.getNombre(),
                e.getFName(),      
                e.getLName(),      
                e.getCategoria(),
                e.getIdSucursal()  
            });
        }
        
        tbl_empleados.setModel(modelo);
        
    } catch (Exception ex) {
        System.err.println("Error al cargar datos en la tabla de Empleados: " + ex.getMessage());
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable tbl_empleados;
    // End of variables declaration//GEN-END:variables
}
