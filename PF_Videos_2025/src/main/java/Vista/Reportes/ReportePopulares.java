package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date; 
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ReportePopulares extends JFrame {
    public ReportePopulares(Date fechaInicio, Date fechaFin) {        
        setTitle("Top 10 Películas Populares (" + fechaInicio + " a " + fechaFin + ")");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        ReporteDAO dao = new ReporteDAO();
        List<Map<String, Object>> datos = dao.obtenerPeliculasPopulares(fechaInicio, fechaFin); 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (datos.isEmpty()) {
             JOptionPane.showMessageDialog(this, "No se encontraron alquileres en ese período para el ranking.", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
             this.dispose();
             return;
        }
        for (Map<String, Object> fila : datos) {
            String titulo = (String) fila.get("TITULO");
            Number conteo = (Number) fila.get("VECES_ALQUILADA"); 
            dataset.addValue(conteo, "Veces Alquilada", titulo);
        }
        //Crear el Objeto JFreeChart (Gráfico de Barras)
        JFreeChart chart = ChartFactory.createBarChart(
            "Ranking de Películas Más Alquiladas por Periodo", // Título principal del gráfico
            "Película",                                        // Etiqueta del Eje X (Categorías)
            "Número de Alquileres",                            // Etiqueta del Eje Y (Valores)
            dataset,                                           // Modelo de datos
            PlotOrientation.VERTICAL,                          // Orientación (Vertical/Horizontal)
            false,                                             // Mostrar leyendas (Legend)
            true,                                              // Mostrar Tooltips
            false                                              // Generar URL
        );        
        // 6. Integrar el Gráfico en el JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true); // Permite hacer zoom con la rueda
        add(chartPanel, BorderLayout.CENTER);        
        // 7. Botón de Cierre 
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar Ventana");
        btnCerrar.addActionListener(e -> dispose());
        panelBoton.add(btnCerrar);
        add(panelBoton, BorderLayout.SOUTH);
        setVisible(true);
    }
}