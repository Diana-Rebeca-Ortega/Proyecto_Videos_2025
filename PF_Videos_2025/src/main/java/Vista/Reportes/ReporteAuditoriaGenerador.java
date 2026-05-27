package Vista.Reportes;

import Controlador.AuditoriaClaveDAO;
import Modelo.AuditoriaClave;
import java.util.List;
import javax.swing.JOptionPane;

// Importante: Debe implementar IReporteGenerador
public class ReporteAuditoriaGenerador implements IReporteGenerador {
    
    @Override
public void generar() {
    try {
        AuditoriaClaveDAO dao = new AuditoriaClaveDAO();
        List<AuditoriaClave> historial = dao.obtenerTodosLosRegistros();
        
        if (historial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay registros de auditoría.");
        } else {
            new ReporteAuditoriaClave(historial).setVisible(true);
        }
    } catch (Exception e) {
        // Si ocurre un error (como SQLException), el programa entra aquí
        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage());
    }
}
}