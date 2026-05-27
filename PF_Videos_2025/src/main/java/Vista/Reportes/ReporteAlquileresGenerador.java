package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date;

public class ReporteAlquileresGenerador implements IReporteGenerador {
    private Date inicio, fin;

    public ReporteAlquileresGenerador(Date i, Date f) { 
        this.inicio = i; 
        this.fin = f; 
    }

    @Override
public void generar() {
    var datos = new ReporteDAO().obtenerAlquileresPorPeriodo(inicio, fin);
    ReporteAlquileres vista = new ReporteAlquileres(datos, inicio.toString() + " a " + fin.toString());
    vista.setVisible(true); 
}
}