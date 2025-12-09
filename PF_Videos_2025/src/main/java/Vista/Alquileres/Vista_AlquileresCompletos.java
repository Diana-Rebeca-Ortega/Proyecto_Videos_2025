package Vista.Alquileres;

import Controlador.AlquilerDAO;
import Controlador.VistaAlquilerDevolucionDAO;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import Modelo.AlquilerCompleto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class Vista_AlquileresCompletos extends javax.swing.JDialog {
    private AlquilerDAO alquilerDao = new AlquilerDAO();
    private int idSucursalActual;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Vista_AlquileresCompletos.class.getName());
    private JPopupMenu popupMenu;
    private JMenuItem devolverMenuItem;
    public Vista_AlquileresCompletos(java.awt.Frame parent, boolean modal, int idSucursal) {
        super(parent, modal);
        initComponents();
        this.idSucursalActual = idSucursal;
        inicializarMenuContextual();
        cargarTablaAlquileresVista(); 
    }
public void cargarTablaAlquileresVista() {
    DefaultTableModel modelo = (DefaultTableModel) tablaVistaAlquileres.getModel();
    String[] nuevasColumnas = {"ID_Alquiler", "Cliente", "Película", "Alquiler", "Devolución", "Estado", "Tarifa", "ID_SUCURSAL", "ID_COPIA"};
    modelo.setColumnIdentifiers(nuevasColumnas);
    modelo.setRowCount(0);
    
    List<AlquilerCompleto> listado = alquilerDao.obtenerListadoAlquileres(1);
    
    for (AlquilerCompleto ac : listado) {
        Object[] fila = new Object[] {
            ac.getIdAlquiler(),
            ac.getNombreCliente(),    
            ac.getTituloPelicula(),  
            ac.getFechaAlquiler(),
            ac.getFechaDevolucion(),
            ac.getEstado(),
            ac.getTarifaTotal(),            
            ac.getIdSucursal(),            
            ac.getIdCopiaPelicula()
        };
        modelo.addRow(fila);
    }
    
}
   private void inicializarMenuContextual() {
    popupMenu = new JPopupMenu();
    devolverMenuItem = new JMenuItem("DEVOLVER PELÍCULA");
    devolverMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("se activo el jemun item ");
            ejecutarDevolucion();
        } 
    });
    popupMenu.add(devolverMenuItem);
    tablaVistaAlquileres.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) { 
            // Esto detecta si el evento debe mostrar el menú (clic derecho en la mayoría de OS)
            if (e.isPopupTrigger()) { 
                int row = tablaVistaAlquileres.rowAtPoint(e.getPoint());
                if (row >= 0 && row < tablaVistaAlquileres.getRowCount()) {
                    tablaVistaAlquileres.setRowSelectionInterval(row, row);                    
                    String estado = (String) tablaVistaAlquileres.getValueAt(row, 5);
                    devolverMenuItem.setEnabled(estado.equals("RENTADO"));                     
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    });
}
   
private void ejecutarDevolucion() {
    int selectedRow = tablaVistaAlquileres.getSelectedRow();    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un alquiler pendiente de la tabla.");
        return;    }        
    String estadoActual = (String) tablaVistaAlquileres.getValueAt(selectedRow, 5);
    if (!estadoActual.equals("RENTADO")) { // Usamos RENTADO porque es lo que se ve en la tabla (image_0c45fb.png)
        JOptionPane.showMessageDialog(this, "Esta película ya fue devuelta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    } 
    int idAlquiler = (int) tablaVistaAlquileres.getValueAt(selectedRow, 0);        
    int idCopiaPelicula = (int) tablaVistaAlquileres.getValueAt(selectedRow, 8); 
    String nombreCliente = (String) tablaVistaAlquileres.getValueAt(selectedRow, 1); 
    String tituloPelicula = (String) tablaVistaAlquileres.getValueAt(selectedRow, 2); 
   
    int respuesta = JOptionPane.showConfirmDialog(this,
        "¿Confirma la devolución de la película:\n" + 
        "'" + tituloPelicula + "' para el cliente " + nombreCliente + "?",
        "Confirmar Devolución", JOptionPane.YES_NO_OPTION);  
    if (respuesta == JOptionPane.YES_OPTION) {
        try {           
            boolean exito = alquilerDao.registrarDevolucion(idAlquiler, idCopiaPelicula);            
            if (exito) {
                JOptionPane.showMessageDialog(this, "Devolución registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarTablaAlquileresVista();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar la devolución. Consulte la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de base de datos durante la devolución: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        textArea1 = new java.awt.TextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVistaAlquileres = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(null);

        label1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        label1.setText("VISTA_ALQUILERES_COMPLETO");
        getContentPane().add(label1);
        label1.setBounds(0, 10, 1168, 32);

        textArea1.setText("\nLa vista realiza las siguientes UNIONES IZQUIERDAS (LEFT JOIN) para vincular los datos:\nALQUILER (A) con CLIENTES (C): Se une por A.NO_CLIENTE = C.NO_CLIENTE para obtener el nombre del cliente.\nALQUILER (A) con PELICULA (P): Se une por A.ID_PELICULA = P.ID_PELICULA para obtener el título de la película.");
        getContentPane().add(textArea1);
        textArea1.setBounds(10, 52, 639, 118);

        tablaVistaAlquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jScrollPane1.setViewportView(tablaVistaAlquileres);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 180, 980, 280);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTable tablaVistaAlquileres;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
