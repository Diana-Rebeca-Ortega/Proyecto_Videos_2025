package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date;

public class ReporteAlquileresGenerador implements IReporteGenerador {
    private Date inicio, fin;
    private ReporteDAO reporteDAO;
    public ReporteAlquileresGenerador(Date i, Date f, ReporteDAO dao) { 
        this.inicio = i; 
        this.fin = f; 
        this.reporteDAO = dao;
    }

    @Override
public void generar() {
    var datos = reporteDAO.obtenerAlquileresPorPeriodo(inicio, fin);
    ReporteAlquileres vista = new ReporteAlquileres(datos, inicio.toString() + " a " + fin.toString());
    vista.setVisible(true); 
}
}