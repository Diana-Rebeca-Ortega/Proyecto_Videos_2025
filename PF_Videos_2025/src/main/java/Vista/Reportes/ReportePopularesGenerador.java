package Vista.Reportes;

import Controlador.ReporteDAO;
import java.sql.Date;

public class ReportePopularesGenerador implements IReporteGenerador {
    private Date inicio, fin;

    public ReportePopularesGenerador(Date i, Date f) { 
        this.inicio = i; 
        this.fin = f; 
    }

    @Override
    public void generar() {
        // LSP: Esta clase respeta el contrato de la interfaz sin excepciones
        new ReportePopulares(inicio, fin);
    }
}