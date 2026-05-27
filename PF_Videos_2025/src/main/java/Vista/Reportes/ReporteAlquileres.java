package Vista.Reportes;

import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReporteAlquileres extends JFrame implements IReporteImprimible {

    private JTable tablaReporte;
    private JScrollPane scrollPane;
    private List<Map<String, Object>> datosRespaldados; // Para poder usarlos en la impresión

    public ReporteAlquileres(List<Map<String, Object>> datosReporte, String periodo) {
        this.datosRespaldados = datosReporte;
        
        setTitle(" Reporte de Alquileres por Período: " + periodo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Título Superior
        JLabel lblTitulo = new JLabel("Detalle de Alquileres", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);
        DefaultTableModel modelo = new DefaultTableModel();
        tablaReporte = new JTable(modelo);

        if (datosReporte != null && !datosReporte.isEmpty()) {
            Map<String, Object> primeraFila = datosReporte.get(0);
            Object[] columnas = primeraFila.keySet().toArray();
            modelo.setColumnIdentifiers(columnas);

            for (Map<String, Object> fila : datosReporte) {
                Object[] filaData = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    filaData[i] = fila.get(columnas[i].toString());
                }
                modelo.addRow(filaData);
            }
            tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        } else {
            JLabel lblSinDatos = new JLabel("No hay registros de alquileres para el período seleccionado.", JLabel.CENTER);
            add(lblSinDatos, BorderLayout.CENTER);
            return;
        }
        scrollPane = new JScrollPane(tablaReporte);
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnImprimir = new JButton("🖨️ Imprimir");
        btnImprimir.addActionListener(e -> imprimir()); // Llama al método de la interfaz segregada
        panelSur.add(btnImprimir);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());
        panelSur.add(btnCerrar);
        
        add(panelSur, BorderLayout.SOUTH);
    }

    @Override
    public void imprimir() {
        try {
            // Java Swing tiene soporte nativo para imprimir JTables directo a PDF/Impresora física
            boolean completo = tablaReporte.print(JTable.PrintMode.FIT_WIDTH, 
                    new java.text.MessageFormat("Reporte de Alquileres"), 
                    new java.text.MessageFormat("Página {0}"));
            
            if (completo) {
                JOptionPane.showMessageDialog(this, "Impresión finalizada con éxito.", "Imprimir", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Error de impresión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}