package Vista.Cliente;

import Vista.Cliente.ModificacionesCliente;
import Vista.Cliente.FormularioAltasCliente;
import Controlador.ClienteDAO;
import Modelo.Cliente;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class Panel_Cliente extends javax.swing.JPanel {
private JPopupMenu popupMenu;
private JMenuItem menuItemModificar;
private JMenuItem menuItemEliminar;  
  public Panel_Cliente() {
    initComponents();
    popupMenu = new JPopupMenu();
    menuItemModificar = new JMenuItem("Modificar Cliente");    
    menuItemEliminar = new JMenuItem("Eliminar Cliente");
    popupMenu.add(menuItemModificar);
    popupMenu.add(menuItemEliminar);
    configurarAccionesMenu(); 
    añadirListenerTabla(); 
    aplicarEstiloTabla();
} 
  private void aplicarEstiloTabla() {    
    Color COLOR_HEADER_FONDO = new Color(50, 100, 150); 
    Color COLOR_HEADER_TEXTO = Color.WHITE;
    Color COLOR_FILA_SELECCIONADA = new Color(180, 210, 240); 
    
    JTableHeader header = tbla_clientes.getTableHeader();    
    header.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
    header.setBackground(COLOR_HEADER_FONDO);
    header.setForeground(COLOR_HEADER_TEXTO);    
    header.setBorder(null);

    DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);    
    tbla_clientes.setBackground(Color.WHITE);     
    tbla_clientes.setSelectionBackground(COLOR_FILA_SELECCIONADA);     
    tbla_clientes.setRowHeight(28);
    tbla_clientes.setShowGrid(false); 
    tbla_clientes.setIntercellSpacing(new java.awt.Dimension(0, 1));
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
    
    for (int i = 0; i < tbla_clientes.getColumnCount(); i++) {       
        if (i == 0 || i == 4 || i == 5) { 
             tbla_clientes.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }    
}
    private void configurarAccionesMenu() {
    menuItemModificar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            ejecutarModificarCliente();
        }
    });
    menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent e) {
            ejecutarEliminarCliente();
        }
    });
}
  private void añadirListenerTabla() {
    tbla_clientes.addMouseListener(new MouseAdapter() {
        private void manejarPopupTrigger(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int fila = tbla_clientes.rowAtPoint(e.getPoint());
                if (fila >= 0 && fila < tbla_clientes.getRowCount()) {
                    tbla_clientes.setRowSelectionInterval(fila, fila);
                }
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
        int idCliente = (int) tbla_clientes.getValueAt(filaSeleccionada, 0); 
        Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(this);
        ModificacionesCliente formModificar = new ModificacionesCliente(framePadre, true, idCliente);         
        formModificar.setVisible(true);
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
    
    int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el cliente con ID: " + idCliente + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

    if (respuesta == JOptionPane.YES_OPTION) {
        ClienteDAO dao = new ClienteDAO();
        if (dao.eliminarCliente(idCliente)) { 
            JOptionPane.showMessageDialog(this, "Cliente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesATabla(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar el cliente. Revise logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
       public void cargarClientesATabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_Cliente");
        modelo.addColumn("Nombre");
        modelo.addColumn("Primer Ap");
        modelo.addColumn("Segundo Ap");
        modelo.addColumn("No Exterior");
        modelo.addColumn("Calle");
        modelo.addColumn("Colonia");
        modelo.addColumn("Ciudad");
        modelo.addColumn("CP");
        modelo.addColumn("Estado"); 
        modelo.addColumn("Fecha_Reg");
        modelo.addColumn("No_Sucursal");
        
        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.obtenerTodosLosClientes();
        
        for (Cliente c : clientes) {
            Object[] fila = new Object[12];
            
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbla_clientes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_Nuevo_Cliente = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(null);

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

        add(jScrollPane1);
        jScrollPane1.setBounds(0, 90, 1080, 290);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        jLabel1.setText("GESTIÓN DE CLIENTES");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 10, 470, 40);

        btn_Nuevo_Cliente.setBackground(new java.awt.Color(251, 190, 79));
        btn_Nuevo_Cliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_Nuevo_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/anadir-amigo.png"))); // NOI18N
        btn_Nuevo_Cliente.setText("AÑADIR");
        btn_Nuevo_Cliente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Nuevo_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Nuevo_ClienteActionPerformed(evt);
            }
        });
        jPanel2.add(btn_Nuevo_Cliente);
        btn_Nuevo_Cliente.setBounds(630, 0, 450, 50);

        add(jPanel2);
        jPanel2.setBounds(0, 0, 1090, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Nuevo_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Nuevo_ClienteActionPerformed
        FormularioAltasCliente form = new FormularioAltasCliente(null, true); 
        form.setVisible(true);
        if (form.isDatosGuardados()){
        Cliente nuevoCliente = form.getCliente(); 
        ClienteDAO dao = new ClienteDAO();        
        if (dao.insertarCliente(nuevoCliente)){
            JOptionPane.showMessageDialog(this, "Cliente insertado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarClientesATabla();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar el cliente. Revisa logs.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }    
    }//GEN-LAST:event_btn_Nuevo_ClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Nuevo_Cliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbla_clientes;
    // End of variables declaration//GEN-END:variables
}
