/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista;

import Controlador.ClienteDAO;
import Modelo.Cliente;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diana
 */
public class Panel_Cliente extends javax.swing.JPanel {
private JPopupMenu popupMenu;
private JMenuItem menuItemModificar;
private JMenuItem menuItemEliminar;
    /**
     * Creates new form Panel_Cliente
     */
  public Panel_Cliente() {
    initComponents();
    popupMenu = new JPopupMenu();
    menuItemModificar = new JMenuItem("Modificar Cliente");
    menuItemEliminar = new JMenuItem("Eliminar Cliente");
    popupMenu.add(menuItemModificar);
    popupMenu.add(menuItemEliminar);
    configurarAccionesMenu(); 
    
    // Llama al MouseListener para detectar el clic derecho.
    añadirListenerTabla(); 
}
    
    private void configurarAccionesMenu() {
    // Acción para Modificar
    menuItemModificar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            ejecutarModificarCliente();
            System.out.println("bton modificar derecho");
        }
    });

    // Acción para Eliminar
    menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            ejecutarEliminarCliente();
        }
    });
}
  private void añadirListenerTabla() {
    tbla_clientes.addMouseListener(new MouseAdapter() {
        
        // Función auxiliar para manejar el clic derecho, llamado desde mousePressed y mouseReleased
        private void manejarPopupTrigger(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int fila = tbla_clientes.rowAtPoint(e.getPoint());
                
                // Forzar la selección de la fila del clic derecho (SOLUCIÓN)
                if (fila >= 0 && fila < tbla_clientes.getRowCount()) {
                    tbla_clientes.setRowSelectionInterval(fila, fila);
                }
                // Si la tabla no tiene elementos, la fila será -1 y no mostrará el menú.
                // Si la fila está seleccionada, se muestra el menú.
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
    
    private void ejecutarModificarCliente() {
   int filaSeleccionada = tbla_clientes.getSelectedRow();
        System.out.println(filaSeleccionada);
    if (filaSeleccionada == -1) {
        // Muestra la advertencia que te aparece en la imagen
        JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla para modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    try {
        // 1. Obtener el ID del cliente de la fila seleccionada (Columna 0)
        int idCliente = (int) tbla_clientes.getValueAt(filaSeleccionada, 0); 
        
        // 2. Obtener la referencia al Frame padre (para la visibilidad modal correcta)
        Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(this);
        
        // 3. Abrir el JDialog ModificacionesCliente
        ModificacionesCliente formModificar = new ModificacionesCliente(framePadre, true, idCliente); 
        
        formModificar.setVisible(true);

        // 4. Recargar la tabla si hubo cambios (después de que el diálogo se cierra)
        if (formModificar.isDatosGuardados()) { 
            cargarClientesATabla(); 
            JOptionPane.showMessageDialog(this, "Cliente modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        
    } catch (ClassCastException e) {
        JOptionPane.showMessageDialog(this, "Error: El ID del cliente no es un número entero. Revisar columna 0.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al intentar abrir la ventana de modificación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

private void ejecutarEliminarCliente() {
    int filaSeleccionada = tbla_clientes.getSelectedRow();
    
    if (filaSeleccionada == -1) return; 

    int idCliente = (int) tbla_clientes.getValueAt(filaSeleccionada, 0); 
    
    // Pide confirmación
    int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el cliente con ID: " + idCliente + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

    if (respuesta == JOptionPane.YES_OPTION) {
        // Ejecutar el DELETE usando el ClienteDAO
        ClienteDAO dao = new ClienteDAO();
        // Necesitas implementar dao.eliminarCliente(int id) en tu ClienteDAO
        if (dao.eliminarCliente(idCliente)) { 
            JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesATabla(); // Recargar la tabla
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente. Revise logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
       public void cargarClientesATabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        
        // Nombres de las columnas de tu JTable (ajustar si son diferentes)
        modelo.addColumn("ID_Cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Primer Ap");
        modelo.addColumn("Segundo Ap");
        modelo.addColumn("No Exterior");
        modelo.addColumn("Calle");
        modelo.addColumn("Colonia");
        modelo.addColumn("Ciudad");
        modelo.addColumn("CP");
        modelo.addColumn("Estado"); // Incluir la nueva columna
        modelo.addColumn("Fecha_Reg");
        modelo.addColumn("No_Sucursal");
        
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.obtenerTodosLosClientes();
        
        for (Cliente c : clientes) {
            Object[] fila = new Object[12]; // ¡Ahora son 10 columnas!
            
            fila[0] = c.getNoCliente();
        fila[1] = c.getNombre();
        fila[2] = c.getApellido1();
        fila[3] = c.getApellido2();
        fila[4] = c.getNo_exterior();
        fila[5] = c.getCalle();
        fila[6] = c.getColonia();    
        fila[7] = c.getCiudad();
        fila[8] = c.getCp();
        fila[9] = c.getEstado(); 
        fila[10] = c.getFechaRegistro();
        fila[11] = c.getNoSucursal();
            
            modelo.addRow(fila);
        }
        
        tbla_clientes.setModel(modelo); // Asignar el modelo a la tabla
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbla_clientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_Nuevo_Cliente = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tbla_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Cliente", "Nombre", "Primer_Ap", "Segundo_Ap", "NO_exterior", "Calle", "Colonia/Fraccionamiento", "Ciudad", "Estado", "CP", "Fecha_Registro", "No_Sucursal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbla_clientes);
        if (tbla_clientes.getColumnModel().getColumnCount() > 0) {
            tbla_clientes.getColumnModel().getColumn(4).setResizable(false);
            tbla_clientes.getColumnModel().getColumn(9).setResizable(false);
        }

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("GESTIÓN DE CLIENTES");
        add(jLabel1, java.awt.BorderLayout.PAGE_START);

        btn_Nuevo_Cliente.setText("Nuevo");
        btn_Nuevo_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Nuevo_ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Nuevo_Cliente);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Nuevo_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Nuevo_ClienteActionPerformed
        FormularioAltasCliente form = new FormularioAltasCliente(null, true); 
    form.setVisible(true); // Bloquea la ventana principal hasta que se cierre
    
    // 2. Verificar si el usuario presionó Guardar
    if (form.isDatosGuardados()) {
        Cliente nuevoCliente = form.getCliente(); 

        ClienteDAO dao = new ClienteDAO();
        
        // ¡Se debe usar el mismo tipo de dato que funciona en tu DAO (String o int para ID_CLIENTE)!
        // Si tienes problemas de tipo de dato en esta línea, revisa tu ClienteDAO y Cliente.java.
        
        if (dao.insertarCliente(nuevoCliente)) {
            JOptionPane.showMessageDialog(this, "Cliente insertado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // 3. Recargar la tabla
          cargarClientesATabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar el cliente. Revisa logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_btn_Nuevo_ClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Nuevo_Cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbla_clientes;
    // End of variables declaration//GEN-END:variables
}
