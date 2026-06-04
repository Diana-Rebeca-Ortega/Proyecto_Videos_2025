package Vista.Alquileres;

import Controlador.AlquilerDAO;
import Controlador.CopiaPeliculaDAO;
import Modelo.Alquiler;
import javax.swing.JOptionPane;

public class AlquilerService {
   
    public boolean registrarNuevaRenta(Alquiler nuevoAlquiler) {
        AlquilerDAO daoAlquiler = new AlquilerDAO();
        CopiaPeliculaDAO daoCopia = new CopiaPeliculaDAO();
        
        
        // Aquí ejecutas tus transacciones
        if (daoAlquiler.insertarAlquiler(nuevoAlquiler)) {
            return daoCopia.actualizarEstadoCopia(nuevoAlquiler.getIdCopia(), "RENTADO");
        }
        return false;
    }
}

