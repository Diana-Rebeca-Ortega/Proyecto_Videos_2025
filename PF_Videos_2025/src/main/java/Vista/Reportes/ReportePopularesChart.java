package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date; // Necesario para recibir java.sql.Date del panel
import java.awt.*;
import java.util.List;
import java.util.Map;
import javax.swing.*;

// Importaciones de JFreeChart (Asegúrate de que Maven las haya descargado)
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ReportePopularesChart extends JFrame {

    /**
     * Constructor que recibe el rango de fechas para generar el reporte de popularidad.
     * @param fechaInicio Fecha de inicio del reporte.
     * @param fechaFin Fecha de fin del reporte.
     */
    public ReportePopularesChart(Date fechaInicio, Date fechaFin) {
        
        // 1. Configuración básica de la ventana
        setTitle("🍿 Top 10 Películas Populares (" + fechaInicio + " a " + fechaFin + ")");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 2. Obtener Datos del DAO
        ReporteDAO dao = new ReporteDAO();
        // Llamada al método que implementamos:
        List<Map<String, Object>> datos = dao.obtenerPeliculasPopulares(fechaInicio, fechaFin); 

        // 3. Verificar datos y crear el DataSet
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        if (datos.isEmpty()) {
             JOptionPane.showMessageDialog(this, "No se encontraron alquileres en ese período para el ranking.", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
             // Añadir un mensaje y cerrar si no hay datos
             this.dispose();
             return;
        }

        // 4. Mapear los resultados del DAO al modelo de JFreeChart
        for (Map<String, Object> fila : datos) {
            String titulo = (String) fila.get("TITULO");
            Number conteo = (Number) fila.get("VECES_ALQUILADA"); 
            
            // dataset.addValue(Valor, Serie, Categoría)
            // Valor: Conteo | Serie: "Alquileres" | Categoría: Título de la Película
            dataset.addValue(conteo, "Veces Alquilada", titulo);
        }

        // 5. Crear el Objeto JFreeChart (Gráfico de Barras)
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
        
        // 7. Botón de Cierre (Opcional, pero recomendado)
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar Ventana");
        btnCerrar.addActionListener(e -> dispose());
        panelBoton.add(btnCerrar);
        add(panelBoton, BorderLayout.SOUTH);

        // Mostrar la ventana del reporte
        setVisible(true);
    }
}