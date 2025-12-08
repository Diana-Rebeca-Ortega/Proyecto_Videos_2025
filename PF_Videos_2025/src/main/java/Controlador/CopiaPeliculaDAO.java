package Controlador;

import Modelo.CopiaPelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;
import javax.swing.JOptionPane;

public class CopiaPeliculaDAO {

    private static final String ESQUEMA = "DIANA931"; // Asumiendo el mismo esquema que en PeliculaDAO
// R: READ (OBTENER POR ID)
public List<CopiaPelicula> obtenerCopiasPorPeliculaYSucursal(int idCatalogo, int idSucursal) {
    List<CopiaPelicula> lista = new ArrayList<>();
    
   String sql = "SELECT ID_PELICULA, ID_CATALOGO, ID_SUCURSAL, ESTADO FROM " + ESQUEMA + ".COPIA_PELICULA WHERE ID_CATALOGO = ? AND ID_SUCURSAL = ?";

    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idCatalogo); // Búsqueda por ID_CATALOGO
        ps.setInt(2, idSucursal);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CopiaPelicula copia = new CopiaPelicula();
                // 🔑 Lectura usando los nombres de DB2
                copia.setIdCopiaPelicula(rs.getInt("ID_PELICULA"));
                copia.setIdPelicula(rs.getInt("ID_CATALOGO"));
                copia.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                copia.setEstado(rs.getString("ESTADO"));
                lista.add(copia);
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
    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, idCatalogo);
        ps.setInt(2, idSucursal);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                // Si encontramos una copia, devolvemos su ID_PELICULA (que es su PK)
                idCopia = rs.getInt("ID_PELICULA"); 
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener ID de copia disponible: " + e.getMessage());
        e.printStackTrace();
    }
    return idCopia;
}
public boolean actualizarEstadoCopia(int idCopia, String nuevoEstado) {
    // 🔑 CRÍTICO: Este UPDATE es el que dispara el Trigger que ajusta el STOCK.
    String sql = "UPDATE " + ESQUEMA + ".COPIA_PELICULA SET ESTADO = ? WHERE ID_PELICULA = ?";

    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, nuevoEstado);
        ps.setInt(2, idCopia);

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0;

    } catch (SQLException e) {
        System.err.println("Error al actualizar estado de la copia: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}