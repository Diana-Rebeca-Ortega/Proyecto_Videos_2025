package Vista.Reportes;

import Modelo.AuditoriaClave;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReporteAuditoriaClave extends JFrame {

    private JTable tablaReporte;
    private JScrollPane scrollPane;
    private static final String[] NOMBRES_COLUMNAS = {
        "ID Audit.", "ID Empleado", "Fecha/Hora Cambio", "Evento"
    };

    public ReporteAuditoriaClave(List<AuditoriaClave> datosAuditoria) {
        
        // 1. Configuración de la Ventana
        setTitle("🔑 Historial de Auditoría de Cambio de Contraseñas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 450); 
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Título Superior
        JLabel lblTitulo = new JLabel("Auditoría: Cambios de Contraseña de Empleados", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(150, 0, 0)); 
        add(lblTitulo, BorderLayout.NORTH);

        // 3. Crear el Modelo de la Tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(NOMBRES_COLUMNAS);
        tablaReporte = new JTable(modelo);

        // 4. Procesar Datos y Llenar el Modelo
        if (datosAuditoria != null && !datosAuditoria.isEmpty()) {
            
            for (AuditoriaClave ac : datosAuditoria) {
                Object[] filaData = new Object[]{
                    ac.getIdAuditoria(),
                    ac.getIdEmpleado(),
                    formatearTimestamp(ac.getFechaCambio()),
                    ac.getEvento()
                };
                modelo.addRow(filaData);
            }
        } else {
            JLabel lblSinDatos = new JLabel("No se encontraron registros de auditoría de cambio de clave.", JLabel.CENTER);
            lblSinDatos.setFont(new Font("Arial", Font.ITALIC, 14));
            add(lblSinDatos, BorderLayout.CENTER);
            this.setVisible(true);
            return;
        }

        // 5. Añadir la tabla y botón de cierre
        scrollPane = new JScrollPane(tablaReporte);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar Ventana");
        btnCerrar.addActionListener(e -> dispose());
        panelSur.add(btnCerrar);
        add(panelSur, BorderLayout.SOUTH);

        this.setVisible(true);
    }
    
    private String formatearTimestamp(Timestamp ts) {
        if (ts == null) return "";
        // Simple formato legible: AAAA-MM-DD HH:MM:SS
        return ts.toString().substring(0, 19); 
    }
}