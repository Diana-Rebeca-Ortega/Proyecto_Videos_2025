package Controlador;

import Modelo.CopiaPelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class CopiaPeliculaDAO {

private static final String ESQUEMA = "DIANA931"; // Asumiendo el mismo esquema que en PeliculaDAO
// R: READ (OBTENER POR ID)
public List<CopiaPelicula> obtenerCopiasPorPeliculaYSucursal(int idCatalogo, int idSucursal) {
    List<CopiaPelicula> lista = new ArrayList<>();
    String sql = "SELECT ID_PELICULA, ID_CATALOGO, ID_SUCURSAL, ESTADO FROM " + ESQUEMA + ".COPIA_PELICULA WHERE ID_CATALOGO = ? AND ID_SUCURSAL = ?";

    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources

        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCatalogo);
            ps.setInt(2, idSucursal);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CopiaPelicula copia = new CopiaPelicula();
                    copia.setIdCopiaPelicula(rs.getInt("ID_PELICULA"));
                    copia.setIdPelicula(rs.getInt("ID_CATALOGO"));
                    copia.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                    copia.setEstado(rs.getString("ESTADO"));
                    lista.add(copia);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener copias por película/sucursal: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
 public int obtenerIdCopiaDisponible(int idCatalogo, int idSucursal) {
    int idCopia = -1;
    String sql = "SELECT ID_PELICULA FROM DIANA931.COPIA_PELICULA " +
                  "WHERE ID_CATALOGO = ? AND ID_SUCURSAL = ? AND ESTADO = 'Disponible' FETCH FIRST 1 ROW ONLY";

    Connection con = null; 

    try {
        con = ConexionBD.getInstance().getConnection(); 
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCatalogo);
            ps.setInt(2, idSucursal);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idCopia = rs.getInt("ID_PELICULA");
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID de copia disponible: " + e.getMessage());
        e.printStackTrace();
    }
    return idCopia;
}
public CopiaPelicula obtenerCopiaPorId(int idCopia) {
    CopiaPelicula copia = null;
    String sql = "SELECT ID_PELICULA, ID_CATALOGO, ID_SUCURSAL, ESTADO FROM " + ESQUEMA + ".COPIA_PELICULA WHERE ID_PELICULA = ?";

    Connection con = null; // ⬅️ Declaración

    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCopia);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    copia = new CopiaPelicula();
                    copia.setIdCopiaPelicula(rs.getInt("ID_PELICULA"));
                    copia.setIdPelicula(rs.getInt("ID_CATALOGO"));
                    copia.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                    copia.setEstado(rs.getString("ESTADO"));
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener copia por ID: " + e.getMessage());
        e.printStackTrace();
    }
    return copia;
}
public int obtenerIdPeliculaMaestraPorCopia(int idCopia) {   
    String sql = "SELECT ID_CATALOGO FROM DIANA931.COPIA_PELICULA WHERE ID_PELICULA = ?";
    int idMaestra = -1;    
    Connection con = null;
    try {
        con = ConexionBD.getInstance().getConnection();        
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idCopia); // ID_PELICULA de COPIA_PELICULA
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idMaestra = rs.getInt("ID_CATALOGO");
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID_CATALOGO por ID_COPIA: " + e.getMessage());
        e.printStackTrace();
    }
    return idMaestra;
}
public boolean actualizarEstadoCopia(int idCopia, String nuevoEstado) {
    String sql = "UPDATE " + ESQUEMA + ".COPIA_PELICULA SET ESTADO = ? WHERE ID_PELICULA = ?";
    Connection con = null;
    try {
        con = ConexionBD.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idCopia);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }
    } catch (SQLException e) {
        System.err.println("Error al actualizar estado de la copia: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

public int contarCopiasDisponibles(int idPelicula) {
    // La consulta invoca la Función Definida por el Usuario (UDF)
    String sql = "SELECT " + ESQUEMA + ".CONTARCOPIASDISPONIBLES(?) FROM SYSIBM.SYSDUMMY1";
    int copiasDisponibles = 0;
    
    Connection con = null;

    try {
        con = ConexionBD.getInstance().getConnection();
        
        // Usamos try-with-resources para PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idPelicula);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // La UDF devuelve el conteo como un único valor entero (columna 1)
                    copiasDisponibles = rs.getInt(1); 
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al ejecutar la función CONTARCOPIASDISPONIBLES: " + e.getMessage());
        e.printStackTrace();
        // En caso de error, devolvemos 0 para evitar rentas no validadas
        return 0; 
    }
    return copiasDisponibles;
}
}