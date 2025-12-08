package Vista.Sucursal;

import javax.swing.JOptionPane;
import Modelo.Sucursal;
import Controlador.SucursalDAO;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import java.awt.Frame; // Necesario para SwingUtilities.getWindowAncestor
import javax.swing.JTable;
import javax.swing.SwingUtilities; // Necesario para obtener el Frame padre
import javax.swing.table.DefaultTableCellRenderer;
public class PanelSucursales extends javax.swing.JPanel {
    public PanelSucursales() {
        initComponents();
        inicializarPopupMenu(); 
        agregarListeners();  
        cargarDatosTabla();
        
    }
    private JPopupMenu popupMenu; 
    private JMenuItem menuItemModificar;
    private JMenuItem menuItemEliminar;

private void inicializarPopupMenu() {
    popupMenu = new JPopupMenu();    
    // Crear la opción Modificar
    menuItemModificar = new JMenuItem("Modificar Sucursal");
    popupMenu.add(menuItemModificar);    
    // Crear la opción Eliminar
    menuItemEliminar = new JMenuItem("Eliminar Sucursal");
    popupMenu.add(menuItemEliminar);    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encabezado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        button1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSucursales = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(new java.awt.BorderLayout());

        encabezado.setBackground(new java.awt.Color(255, 255, 255));
        encabezado.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("GESTIÓN DE SUCURSALES");
        encabezado.add(jLabel1);

        button1.setBackground(new java.awt.Color(251, 190, 79));
        button1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos_blancos/puntero-de-ubicacion.png"))); // NOI18N
        button1.setText("NUEVO");
        button1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        encabezado.add(button1);

        add(encabezado, java.awt.BorderLayout.NORTH);

        tablaSucursales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_Sucursal", "Nombre", "telefono", "NO.Exterior", "calle", "colonia", "ciudad", "estado", "CP"
            }
        ));
        jScrollPane1.setViewportView(tablaSucursales);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
     FormularioAltasSucursal formSucursal = new FormularioAltasSucursal(null, true);
    formSucursal.setVisible(true);
    if (formSucursal.isDatosGuardados()) {          
        Modelo.Sucursal nuevaSucursal = formSucursal.getSucursal();        
        Controlador.SucursalDAO dao = new Controlador.SucursalDAO();        
        if (dao.insertarSucursal(nuevaSucursal)) {
           javax.swing.JOptionPane.showMessageDialog(this, "Sucursal insertada con éxito.", "Éxito", javax.swing.JOptionPane.INFORMATION_MESSAGE);         
           cargarDatosTabla();
        } else {          
           javax.swing.JOptionPane.showMessageDialog(this, "Error al insertar la sucursal. Revisa logs.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_button1ActionPerformed

private void agregarListeners() {
    menuItemModificar.addActionListener(new ActionListener() {
        @Override
      public void actionPerformed(ActionEvent e) {           
            ejecutarModificarSucursal(); 
        }
    });
    menuItemEliminar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int filaSeleccionada = tablaSucursales.getSelectedRow();
            if (filaSeleccionada != -1) {
                short idSucursal = (short) tablaSucursales.getValueAt(filaSeleccionada, 0);
                System.out.println("ID de Sucursal seleccionado para modificar: " + idSucursal);
                Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(PanelSucursales.this);             
                eliminarSucursal(idSucursal);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una sucursal para eliminar.");
            }
        }
    });
    tablaSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                int row = tablaSucursales.rowAtPoint(evt.getPoint());
                if (row != -1) {
                    tablaSucursales.setRowSelectionInterval(row, row);
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        }        
        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
             if (evt.isPopupTrigger()) {
                int row = tablaSucursales.rowAtPoint(evt.getPoint());
                if (row != -1) {
                    tablaSucursales.setRowSelectionInterval(row, row);
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
             }
        }
    });
}    
private void eliminarSucursal(short idSucursal) {
    int confirmacion = JOptionPane.showConfirmDialog(
        this, 
        "¿Está seguro de que desea eliminar la sucursal con ID: " + idSucursal + "?", 
        "Confirmar Eliminación", 
        JOptionPane.YES_NO_OPTION
    );

    if (confirmacion == JOptionPane.YES_OPTION) {
        SucursalDAO dao = new SucursalDAO();
        if (dao.eliminarSucursal(idSucursal)) {
            JOptionPane.showMessageDialog(this, "Sucursal eliminada exitosamente.");
            cargarDatosTabla(); 
        } else {
            JOptionPane.showMessageDialog(this, "Error al eliminar la sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
private void ejecutarModificarSucursal() {
    int filaSeleccionada = tablaSucursales.getSelectedRow();
    System.out.println(filaSeleccionada);
    if (filaSeleccionada != -1) {  
        short idSucursal = (short) tablaSucursales.getValueAt(filaSeleccionada, 0);
        Frame framePadre = (Frame) SwingUtilities.getWindowAncestor(PanelSucursales.this);
        ModificacionesSucursal formModificar = new ModificacionesSucursal(framePadre, true, idSucursal);
        formModificar.setVisible(true);        
        if (formModificar.isDatosGuardados()) {
            JOptionPane.showMessageDialog(this, "Sucursal modificada con éxito.");
            cargarDatosTabla(); 
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleccione una sucursal para modificar.");
    }
}
private void cargarDatosTabla() {
    tablaSucursales.getTableHeader().setBackground(new Color(235, 235, 235)); 
    tablaSucursales.getTableHeader().setForeground(new Color(47, 79, 79)); 
    tablaSucursales.getTableHeader().setFont(new java.awt.Font("Microsoft YaHei Light", java.awt.Font.BOLD, 12));
    tablaSucursales.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           if (row % 2 == 0) {
             c.setBackground(new Color(255, 255, 255));
        } else {  
            c.setBackground(new Color(245, 245, 245)); 
        }
        if (isSelected) {           
            c.setBackground(new Color(251, 190, 79)); 
        }
        return c;
    }
});
    try {
        // 1. Definir el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID_Sucursal"); // Usa el nombre de la columna que definiste en initComponents
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("NO.Exterior");
        modelo.addColumn("calle");
        modelo.addColumn("colonia");
        modelo.addColumn("ciudad");
        modelo.addColumn("estado");
        modelo.addColumn("CP");        
        // 2. Obtener los datos actualizados
        SucursalDAO dao = new SucursalDAO();
        List<Modelo.Sucursal> listaSucursales = dao.obtenerTodasLasSucursales();
        // 3. Llenar el modelo con los nuevos datos
        for (Modelo.Sucursal s : listaSucursales) {
            modelo.addRow(new Object[]{
                s.getNoSucursal(),
                s.getNombreSucursal(),
                s.getNoTelefono(),
                s.getNumeroExterior(),
                s.getCalle(),
                s.getColonia(),
                s.getCiudad(),
                s.getEstado(),
                s.getCp()
            });
        }        
        // 4. Asignar el nuevo modelo (con los datos) a la tabla
        tablaSucursales.setModel(modelo);        
    } catch (Exception ex) {
        System.err.println("Error al cargar datos en la tabla: " + ex.getMessage());       
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button1;
    private javax.swing.JPanel encabezado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaSucursales;
    // End of variables declaration//GEN-END:variables
}
