
package Controlador;

import ConneccionBD.ConexionBD;
import Modelo.VistaAlquilerDevolucion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VistaAlquilerDevolucionDAO {
 
    public VistaAlquilerDevolucionDAO() {
    }
public List<VistaAlquilerDevolucion> obtenerAlquileresPendientes() throws SQLException {
    List<VistaAlquilerDevolucion> listaAlquileres = new ArrayList<>();
    String sql = "SELECT * FROM V_ALQUILERES_CON_ESTADO ";
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para Statement y ResultSet
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VistaAlquilerDevolucion av = mapearResultado(rs);
                listaAlquileres.add(av);
            }
        }
    } catch (SQLException e) {
        // En lugar de capturar en el try-with-resources, lo movemos al try principal
        System.err.println("Error al obtener alquileres pendientes: " + e.getMessage());
        e.printStackTrace();
        throw e; // O manejas el error, pero la firma del método requiere lanzarlo
    }
    return listaAlquileres;
}
   // Método para buscar alquileres (se modifica para obtener la conexión internamente)
public List<VistaAlquilerDevolucion> buscarAlquileres(String textoBusqueda) throws SQLException {
    List<VistaAlquilerDevolucion> listaAlquileres = new ArrayList<>();
    
    String sql = "SELECT * FROM V_ALQUILERES_CON_ESTADO WHERE " +
                 // Filtra solo los que están pendientes de entrega
                 "ESTADO_ENTREGA_UI = 'RENTADO' AND " +
                 // Búsqueda por Título, Nombre de Cliente o ID (convertido a String para LIKE)
                 "(TITULO_PELICULA LIKE ? OR NOMBRE_CLIENTE LIKE ? OR CAST(ID_ALQUILER AS VARCHAR(10)) LIKE ?)";

    // Prepara el texto de búsqueda para LIKE (ej: '%texto%')
    String busquedaLike = "%" + textoBusqueda.toUpperCase() + "%";
    
    Connection con = null; // ⬅️ Declaración

    try {
        con = ConexionBD.getInstance().getConnection(); 
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {            
            // 1. Asignar parámetros para la búsqueda (3 veces el mismo valor)
            ps.setString(1, busquedaLike); // TITULO_PELICULA
            ps.setString(2, busquedaLike); // NOMBRE_CLIENTE
            ps.setString(3, busquedaLike); // ID_ALQUILER (como VARCHAR)
            
            // 2. Ejecutar y mapear resultados
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VistaAlquilerDevolucion av = mapearResultado(rs);
                    listaAlquileres.add(av);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al buscar alquileres pendientes: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    return listaAlquileres;
}

    private VistaAlquilerDevolucion mapearResultado(ResultSet rs) throws SQLException {
        VistaAlquilerDevolucion av = new VistaAlquilerDevolucion();
        av.setIdAlquiler(rs.getInt("ID_ALQUILER"));
        av.setNoCliente(rs.getInt("NO_CLIENTE"));
        av.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
        av.setTituloPelicula(rs.getString("TITULO_PELICULA"));
        av.setIdPelicula(rs.getInt("ID_PELICULA"));
        av.setEstadoActual(rs.getString("ESTADO_ACTUAL"));
        av.setIdCopiaPelicula(rs.getInt("ID_COPIA_PELICULA"));
        av.setFechaAlquiler(rs.getDate("FECHA_ALQUILER"));
        av.setFechaDevolucion(rs.getDate("FECHA_VENCIMIENTO"));
        av.setEstadoEntregaUI(rs.getString("ESTADO_ENTREGA_UI"));
      
        return av;
    }
}
