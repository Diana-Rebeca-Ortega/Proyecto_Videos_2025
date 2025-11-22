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
       String sql = "SELECT ID_ALQUILER, NO_CLIENTE, ID_PELICULA, FECHA_ALQUILER, FECHA_DEVOLUCION, ESTADO, ID_SUCURSAL, ALQUILER_DIARIO " 
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
                alquiler.setCostoDiario(rs.getDouble("ALQUILER_DIARIO"));
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
    String call = "{CALL RegistrarNuevoAlquiler(?, ?, ?,?)}"; 
        try (Connection conn = ConexionBD.getInstance().getConnection();
         CallableStatement cs = conn.prepareCall(call)) {
        
        // 1. Asignar p_no_alquiler (Omitido si es IDENTITY)
        // cs.setInt(1, nuevoAlquiler.getIdAlquiler()); 

        // 2. Asignar p_no_cliente
        cs.setInt(1, alquiler.getIdCliente());
        
        // 3. Asignar p_no_pelicula
        cs.setInt(2, alquiler.getIdPelicula());
        
        // 4. Asignar p_alquiler_diario
        // Debes asegurarte de que tu objeto Alquiler también guarde el costo diario.
        // Asumiendo que obtienes el costo de la película asociada:
        cs.setDouble(3, alquiler.getCostoDiario()); // Asumir que tienes este campo
        
        java.sql.Date fechaDevolucionSQL = new java.sql.Date(alquiler.getFechaDevolucion().getTime());
        cs.setDate(4, fechaDevolucionSQL);
        // NOTA: El PA usa CURRENT DATE, ¡no necesitas pasar fecha_alquiler! 

        cs.execute();
        return true;
        
    } catch (SQLException e) {
        System.err.println("Error al llamar al PA RegistrarNuevoAlquiler: " + e.getMessage());
        return false;
    }
    }

    // Opcionalmente, puedes añadir métodos de Modificar, Eliminar y ObtenerPorID
    // usando la estructura de ClienteDAO como guía.
}