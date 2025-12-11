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
    Connection con = null;    
    try {
        con = ConexionBD.getInstance().getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VistaAlquilerDevolucion av = mapearResultado(rs);
                listaAlquileres.add(av);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener alquileres pendientes: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
    return listaAlquileres;
}
public List<VistaAlquilerDevolucion> buscarAlquileres(String textoBusqueda) throws SQLException {
    List<VistaAlquilerDevolucion> listaAlquileres = new ArrayList<>();    
    String sql = "SELECT * FROM V_ALQUILERES_CON_ESTADO WHERE " +
                 // Filtra solo los que están pendientes de entrega
                 "ESTADO_ENTREGA_UI = 'RENTADO' AND " +               
                 "(TITULO_PELICULA LIKE ? OR NOMBRE_CLIENTE LIKE ? OR CAST(ID_ALQUILER AS VARCHAR(10)) LIKE ?)";

    // Prepara el texto de búsqueda para LIKE (ej: '%texto%')
    String busquedaLike = "%" + textoBusqueda.toUpperCase() + "%";
    
    Connection con = null; 
    try {
        con = ConexionBD.getInstance().getConnection(); 
        try (PreparedStatement ps = con.prepareStatement(sql)) {  
            ps.setString(1, busquedaLike); // TITULO_PELICULA
            ps.setString(2, busquedaLike); // NOMBRE_CLIENTE
            ps.setString(3, busquedaLike); // ID_ALQUILER           
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
