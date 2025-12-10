package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date;
import java.util.Map;
import javax.swing.JOptionPane;

public class Panel_Reportes extends javax.swing.JPanel {
private ReporteDAO reporteDAO = new ReporteDAO();

    public Panel_Reportes() {
        initComponents();
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        txt_alquileres = new java.awt.Label();
        radioAlquileres = new javax.swing.JRadioButton();
        radioPopulares = new javax.swing.JRadioButton();
        dateChooserInicio = new com.toedter.calendar.JDateChooser();
        dateChooserFin = new com.toedter.calendar.JDateChooser();
        btnGenerarReporte = new javax.swing.JButton();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setLayout(null);

        txt_alquileres.setBackground(new java.awt.Color(255, 255, 255));
        txt_alquileres.setFont(new java.awt.Font("Maiandra GD", 1, 36)); // NOI18N
        txt_alquileres.setMaximumSize(new java.awt.Dimension(32767, 40));
        txt_alquileres.setText("Generación de Reportes");
        add(txt_alquileres);
        txt_alquileres.setBounds(0, 0, 1010, 48);

        radioAlquileres.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(radioAlquileres);
        radioAlquileres.setText("Reporte de Alquileres por Periodo");
        add(radioAlquileres);
        radioAlquileres.setBounds(20, 50, 330, 21);

        radioPopulares.setBackground(new java.awt.Color(204, 204, 255));
        buttonGroup1.add(radioPopulares);
        radioPopulares.setText("Reporte de Peliculas más Populares");
        add(radioPopulares);
        radioPopulares.setBounds(20, 80, 300, 21);
        add(dateChooserInicio);
        dateChooserInicio.setBounds(570, 70, 190, 22);
        add(dateChooserFin);
        dateChooserFin.setBounds(790, 70, 190, 22);

        btnGenerarReporte.setBackground(new java.awt.Color(255, 204, 51));
        btnGenerarReporte.setFont(new java.awt.Font("Microsoft Tai Le", 0, 18)); // NOI18N
        btnGenerarReporte.setText("Generar Reporte");
        btnGenerarReporte.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReporteActionPerformed(evt);
            }
        });
        add(btnGenerarReporte);
        btnGenerarReporte.setBounds(800, 110, 180, 28);

        lblFechaInicio.setText("Fecha Inicio");
        add(lblFechaInicio);
        lblFechaInicio.setBounds(570, 50, 120, 16);

        lblFechaFin.setText("Fecha Fin");
        add(lblFechaFin);
        lblFechaFin.setBounds(790, 50, 110, 16);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReporteActionPerformed
   try {
            // Usa los nuevos métodos encapsulados
            Date fechaInicio = obtenerFechaInicio(); 
            Date fechaFin = obtenerFechaFin();
            
            // Verifica que las fechas sean válidas (opcional, pero buena práctica)
            if (fechaInicio == null || fechaFin == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar ambas fechas.", "Error de Fecha", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (radioPopulares.isSelected()) {
                // Generar gráfica (JFreeChart)
                new ReportePopularesChart(fechaInicio, fechaFin).setVisible(true); 

            } else if (radioAlquileres.isSelected()) {
                // Generar reporte tabular (Swing JTable)
                // Usaremos la clase ReporteAlquileresSwing que definimos antes.
                
                // 1. Obtener la lista de datos del DAO
                var datos = reporteDAO.obtenerAlquileresPorPeriodo(fechaInicio, fechaFin);
                
                if (datos.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No se encontraron alquileres en ese período.", "Sin Datos", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // 2. Abrir la ventana Swing con el reporte
                    String periodo = fechaInicio.toString() + " a " + fechaFin.toString();
                    new ReporteAlquileresSwing(datos, periodo); 
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }        
    }//GEN-LAST:event_btnGenerarReporteActionPerformed

private java.sql.Date obtenerFechaInicio() {
    try {
        // *** CAMBIADO a 'dateChooserInicio' ***
        java.util.Date utilDate = dateChooserInicio.getDate(); 
        if (utilDate != null) {
            // Asegúrate de importar java.sql.Date también
            return new java.sql.Date(utilDate.getTime());
        }
    } catch (Exception e) {
        // Manejar error si el componente no tiene fecha válida
    }
    return null;
}
private java.sql.Date obtenerFechaFin() {
    try {
        // *** CAMBIADO a 'dateChooserFin' ***
        java.util.Date utilDate = dateChooserFin.getDate(); 
        if (utilDate != null) {
            return new java.sql.Date(utilDate.getTime());
        }
    } catch (Exception e) {
        // Manejar error si el componente no tiene fecha válida
    }
    return null;
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarReporte;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser dateChooserFin;
    private com.toedter.calendar.JDateChooser dateChooserInicio;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JRadioButton radioAlquileres;
    private javax.swing.JRadioButton radioPopulares;
    private java.awt.Label txt_alquileres;
    // End of variables declaration//GEN-END:variables
}
