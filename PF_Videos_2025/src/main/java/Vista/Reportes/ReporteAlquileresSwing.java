package Vista.Reportes;

import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReporteAlquileresSwing extends JFrame {

    private JTable tablaReporte;
    private JScrollPane scrollPane;
 
    public ReporteAlquileresSwing(List<Map<String, Object>> datosReporte, String periodo) {
        
        // 1. Configuración de la Ventana
        setTitle("📊 Reporte de Alquileres por Período: " + periodo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Título Superior
        JLabel lblTitulo = new JLabel("Detalle de Alquileres", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // 3. Crear el Modelo de la Tabla
        DefaultTableModel modelo = new DefaultTableModel();
        tablaReporte = new JTable(modelo);

        // 4. Procesar Datos y Llenar el Modelo
        if (datosReporte != null && !datosReporte.isEmpty()) {
            
            // a. Obtener los nombres de las columnas (claves del primer mapa)
            Map<String, Object> primeraFila = datosReporte.get(0);
            Object[] columnas = primeraFila.keySet().toArray();
            modelo.setColumnIdentifiers(columnas);

            // b. Llenar las filas
            for (Map<String, Object> fila : datosReporte) {
                // Crear un array de objetos con los valores en el mismo orden de las columnas
                Object[] filaData = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    filaData[i] = fila.get(columnas[i].toString());
                }
                modelo.addRow(filaData);
            }
            
            // Opcional: Auto-ajustar el ancho de las columnas
            tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        } else {
            // Si no hay datos
            JLabel lblSinDatos = new JLabel("No hay registros de alquileres para el período seleccionado.", JLabel.CENTER);
            add(lblSinDatos, BorderLayout.CENTER);
            this.setVisible(true);
            return;
        }

        // 5. Añadir la tabla a un ScrollPane (para permitir desplazamiento)
        scrollPane = new JScrollPane(tablaReporte);
        add(scrollPane, BorderLayout.CENTER);
        
        // 6. Botón de Cierre (Opcional)
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelSur.add(btnCerrar);
        add(panelSur, BorderLayout.SOUTH);

        // 7. Mostrar la Ventana
        this.setVisible(true);
    }
}