
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
        // La conexión se abre y se cierra automáticamente en el try-with-resources
        try (Connection con = ConexionBD.getInstance().getConnection(); // <-- CONEXIÓN EN EL DAO
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                VistaAlquilerDevolucion av = mapearResultado(rs);
                listaAlquileres.add(av);
            }
        } // La conexión se cierra aquí automáticamente
        return listaAlquileres;
    }
   // Método para buscar alquileres (se modifica para obtener la conexión internamente)
    public List<VistaAlquilerDevolucion> buscarAlquileres(String textoBusqueda) throws SQLException {
        List<VistaAlquilerDevolucion> listaAlquileres = new ArrayList<>();
     String sql = "SELECT * FROM V_ALQUILERES_CON_ESTADO WHERE " +
                 "ESTADO_ACTUAL = 'RENTADO' AND " + // <--- Filtro con la nueva columna
                 "(TITULO_PELICULA LIKE ? OR NOMBRE_CLIENTE LIKE ? OR ID_ALQUILER = ?)";
        try (Connection con = ConexionBD.getInstance().getConnection(); // <-- CONEXIÓN EN EL DAO
             PreparedStatement ps = con.prepareStatement(sql)) {
           
        }
        return listaAlquileres;
    }

    // Método de Transacción de Devolución (también modificado)
    public void registrarDevolucion(int idAlquiler, int idPelicula, int idSucursal) throws SQLException {
        // Usamos una conexión local para manejar la transacción
        Connection con = null;
        boolean originalAutoCommit = true;

        try {
            con = ConexionBD.getInstance().getConnection(); // <-- CONEXIÓN EN EL DAO
            originalAutoCommit = con.getAutoCommit();
            con.setAutoCommit(false); // Desactiva el auto-commit para la transacción

            con.commit();

        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e; 
        } finally {
            // Es VITAL restaurar y cerrar
            if (con != null) {
                con.setAutoCommit(originalAutoCommit);
                con.close(); // Cierra la conexión
            }
        }
    }
    // Método para mapear una fila del ResultSet a un objeto VistaAlquilerDevolucion
    private VistaAlquilerDevolucion mapearResultado(ResultSet rs) throws SQLException {
        VistaAlquilerDevolucion av = new VistaAlquilerDevolucion();
        av.setIdAlquiler(rs.getInt("ID_ALQUILER"));
        av.setNoCliente(rs.getInt("NO_CLIENTE"));
        av.setNombreCliente(rs.getString("NOMBRE_CLIENTE"));
        av.setTituloPelicula(rs.getString("TITULO_PELICULA"));
        av.setIdPelicula(rs.getInt("ID_PELICULA"));
        av.setFechaAlquiler(rs.getDate("FECHA_ALQUILER"));
        
       av.setFechaDevolucion(rs.getDate("FECHA_VENCIMIENTO"));
       av.setEstadoEntregaUI(rs.getString("ESTADO_ENTREGA_UI"));
        av.setEstadoActual(rs.getString("ESTADO_ACTUAL"));
       av.setIdCopiaPelicula(rs.getInt("ID_COPIA_PELICULA"));
        return av;
    }
}
