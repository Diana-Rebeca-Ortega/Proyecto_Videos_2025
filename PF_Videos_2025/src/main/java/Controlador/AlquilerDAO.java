package Controlador;

import Modelo.Alquiler; // Asegúrate de tener esta clase de modelo
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD; // Reutiliza tu clase de conexión

public class AlquilerDAO {

    private static final String ESQUEMA_TABLA = "DIANA931.ALQUILER"; // Ajusta el nombre de tu tabla de alquileres

    /**
     * Recupera todos los registros de alquiler de la base de datos.
     * @return Una lista de objetos Alquiler.
     */
    public List<Alquiler> obtenerTodosLosAlquileres() {
        List<Alquiler> lista = new ArrayList<>();
       String sql = "SELECT ID_ALQUILER, NO_CLIENTE, ID_PELICULA, FECHA_ALQUILER, FECHA_DEVOLUCION, ESTADO, ID_SUCURSAL "
                + "FROM " + ESQUEMA_TABLA;
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alquiler alquiler = new Alquiler();
                // Mapear los campos de la base de datos al objeto Alquiler
                alquiler.setIdAlquiler(rs.getInt("ID_ALQUILER"));
                alquiler.setIdCliente(rs.getInt("NO_CLIENTE"));
                alquiler.setIdPelicula(rs.getInt("ID_PELICULA"));
                
                // Usamos getDate para campos de fecha/hora, asumiendo tipo DATE/TIMESTAMP en DB
                alquiler.setFechaAlquiler(rs.getDate("FECHA_ALQUILER"));
                alquiler.setFechaDevolucion(rs.getDate("FECHA_DEVOLUCION")); 
                alquiler.setEstado(rs.getString("ESTADO"));
                alquiler.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                lista.add(alquiler);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener alquileres: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Inserta un nuevo registro de alquiler en la base de datos.
     * @param alquiler El objeto Alquiler con los datos a guardar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    public boolean insertarAlquiler(Alquiler alquiler) {
        // ID_ALQUILER generalmente se genera automáticamente en la base de datos.
        // FECHA_ALQUILER podemos usar CURRENT DATE o la fecha del objeto si la gestionas en Java.
     String sql = "INSERT INTO " + ESQUEMA_TABLA + 
                     " (NO_CLIENTE, ID_PELICULA, FECHA_ALQUILER, FECHA_DEVOLUCION, ESTADO, ID_SUCURSAL) " 
                     + "VALUES (?, ?, CURRENT DATE, ?, ?, ?)";
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Asignar parámetros:
            
            // 1. ID_CLIENTE
            ps.setInt(1, alquiler.getIdCliente());
            
            // 2. ID_PELICULA
            ps.setInt(2, alquiler.getIdPelicula());
            
            // 3. FECHA_DEVOLUCION (Usamos java.sql.Date para mapear el java.util.Date)
            long tiempoDevolucion = alquiler.getFechaDevolucion().getTime();
            ps.setDate(3, new java.sql.Date(tiempoDevolucion)); 
            
            // 4. ESTADO (Ej: "RENTADO")
            ps.setString(4, alquiler.getEstado()); 
            ps.setInt(5, alquiler.getIdSucursal());
            // Ejecutar la inserción.
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar alquiler: " + e.getMessage());
            // Para depuración avanzada, puedes imprimir la pila de errores
            // e.printStackTrace(); 
            return false;
        }
    }

    // Opcionalmente, puedes añadir métodos de Modificar, Eliminar y ObtenerPorID
    // usando la estructura de ClienteDAO como guía.
}