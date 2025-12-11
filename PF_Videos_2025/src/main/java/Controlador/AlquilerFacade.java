package Controlador;

import Modelo.Cliente;
import Modelo.CopiaPelicula;
import Modelo.Alquiler;
import Modelo.Pelicula;
import java.util.Date;
import java.sql.SQLException;

public class AlquilerFacade {
    /*
    El Facade es responsable de instanciar todos los objetos de acceso a datos (DAOs) que necesita para completar la renta.

ClienteDAO

CopiaPeliculaDAO

PeliculaDAO

AlquilerDAO

RentaValidatorContext
    */
    
    // Referencias a los subsistemas (DAOs)
    private final CopiaPeliculaDAO copiaDAO;
    private final ClienteDAO clienteDAO;
    private final AlquilerDAO alquilerDAO;
    private final PeliculaDAO peliculaDAO;

    public AlquilerFacade() {
        // Inicialización de todos los subsistemas requeridos por la renta
        this.copiaDAO = new CopiaPeliculaDAO();
        this.clienteDAO = new ClienteDAO();
        this.alquilerDAO = new AlquilerDAO();
        this.peliculaDAO = new PeliculaDAO(); // Necesario para obtener la tarifa
    }

    /**
     * Proporciona una interfaz simple para realizar una renta completa.
     * Oculta las validaciones, la obtención de tarifas y la transacción de DB.
     * @return String: "Éxito" si la renta fue exitosa, o un mensaje de error.
     */
    public String realizarNuevaRenta(int idCopia, int idCliente, Date fechaDevolucionUtil) { 
        try {
            // 1. Validar Cliente (Subsistema 1)
            Cliente cliente = clienteDAO.obtenerClientePorId(idCliente);
            if (cliente == null) {
                return "El Cliente ID " + idCliente + " no fue encontrado.";
            }

            // 2. Validar Copia y obtener datos (Subsistema 2)
            CopiaPelicula copia = copiaDAO.obtenerCopiaPorId(idCopia);
            if (copia == null || !"DISPONIBLE".equalsIgnoreCase(copia.getEstado())) {
                String estado = (copia != null) ? copia.getEstado() : "No Encontrada";
                return "La copia ID " + idCopia + " no está disponible. Estado: " + estado;
            }
            
            // 3. Obtener Tarifa y Metadatos de la Película (Subsistema 3)
            Pelicula pelicula = peliculaDAO.obtenerPeliculaPorId(copia.getIdPelicula());
            if (pelicula == null) {
                 return "Error de datos: La copia no tiene una película maestra asociada.";
            }
            double costoDiario = pelicula.getPrecioAlquiler(); // Usamos el método de tu DAO

            // 4. Preparar Objetos y Fechas
            java.sql.Date sqlFechaRenta = new java.sql.Date(new java.util.Date().getTime());
            java.sql.Date sqlFechaDevolucion = new java.sql.Date(fechaDevolucionUtil.getTime());
            
            // (Tu lógica de cálculo de tarifa total y estado va aquí)
            
            Alquiler nuevoAlquiler = new Alquiler();
            nuevoAlquiler.setIdCopia(idCopia);
            nuevoAlquiler.setIdCliente(idCliente);
            nuevoAlquiler.setFechaAlquiler(sqlFechaRenta);
            nuevoAlquiler.setFechaDevolucion(sqlFechaDevolucion);
            nuevoAlquiler.setCostoDiario(costoDiario); 
            nuevoAlquiler.setEstado("RENTADO"); // Estado inicial

            // 5. Ejecutar Transacción (Subsistema 4 y 2)
            // Aquí se oculta la orquestación: primero registrar el alquiler, luego cambiar estado de la copia.
            boolean registroExitoso = alquilerDAO.insertarAlquiler(nuevoAlquiler);
            
            if (registroExitoso) {
                copiaDAO.actualizarEstadoCopia(idCopia, "RENTADO");
                return "Éxito";
            } else {
                return "Error al registrar el alquiler en la base de datos (registro fallido).";
            }
            
        } catch (Exception e) {
            System.err.println("Error general en Facade de Renta: " + e.getMessage());
            return "Error inesperado: " + e.getMessage();
        }
    }
}