package Controlador;

import Modelo.Pelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class PeliculaDAO {

    // --- C: CREATE (INSERCIÓN) ---
    public boolean insertarPelicula(Pelicula pelicula) {
        // CORREGIDO: Nombres de columnas según tu modelo
        String sql = "INSERT INTO DIANA931.PELICULA (TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getCategoria());
            ps.setString(3, pelicula.getDirector());
            ps.setDouble(4, pelicula.getPrecioAlquiler()); // Asumiendo que .getPrecioAlquiler() corresponde a alquiler_diario
            ps.setDouble(5, pelicula.getCosteAdquisicion()); // Asumiendo que .getCosteAdquisicion() corresponde a coste_venta
            ps.setInt(6, pelicula.getStockTotal());

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        pelicula.setIdPelicula(rs.getInt(1)); 
                    }
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.err.println("Error al insertar película: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- R: READ (OBTENER TODOS) ---
    public List<Pelicula> obtenerTodasLasPeliculas() {
        List<Pelicula> lista = new ArrayList<>();
        // CORREGIDO: Nombres de columnas en la SELECT
        String sql = "SELECT ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total FROM DIANA931.PELICULA";
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pelicula pelicula = new Pelicula();
                pelicula.setIdPelicula(rs.getInt("ID_PELICULA"));
                pelicula.setTitulo(rs.getString("TITULO"));
                pelicula.setCategoria(rs.getString("CATEGORIA"));
                pelicula.setDirector(rs.getString("DIRECTOR"));
                
                // CORREGIDO: Nombres de columnas al leer el ResultSet
                pelicula.setPrecioAlquiler(rs.getDouble("alquiler_diario")); 
                pelicula.setCosteAdquisicion(rs.getDouble("coste_venta")); 
                pelicula.setStockTotal(rs.getInt("Stock_total")); 
                
                lista.add(pelicula);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener películas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- R: READ (OBTENER POR ID) ---
    public Pelicula obtenerPeliculaPorId(int idPelicula) {
        // CORREGIDO: Nombres de columnas en la SELECT
        String sql = "SELECT ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total FROM DIANA931.PELICULA WHERE ID_PELICULA = ?";
        Pelicula pelicula = null;
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPelicula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("ID_PELICULA"));
                    pelicula.setTitulo(rs.getString("TITULO"));
                    pelicula.setCategoria(rs.getString("CATEGORIA"));
                    pelicula.setDirector(rs.getString("DIRECTOR"));
                    
                    // CORREGIDO: Nombres de columnas al leer el ResultSet
                    pelicula.setPrecioAlquiler(rs.getDouble("alquiler_diario"));
                    pelicula.setCosteAdquisicion(rs.getDouble("coste_venta"));
                    pelicula.setStockTotal(rs.getInt("Stock_total"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener película por ID (" + idPelicula + "): " + e.getMessage());
            e.printStackTrace();
        }
        return pelicula;
    }

    // --- U: UPDATE (MODIFICAR) ---
    public boolean modificarPelicula(Pelicula pelicula) {
        // CORREGIDO: Nombres de columnas en el UPDATE
        String sql = "UPDATE DIANA931.PELICULA SET TITULO=?, CATEGORIA=?, DIRECTOR=?, alquiler_diario=?, coste_venta=?, Stock_total=? WHERE ID_PELICULA=?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { 

            // 1. Parámetros SET (1-6)
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getCategoria());
            ps.setString(3, pelicula.getDirector());
            ps.setDouble(4, pelicula.getPrecioAlquiler());
            ps.setDouble(5, pelicula.getCosteAdquisicion());
            ps.setInt(6, pelicula.getStockTotal());

            // 2. Condición WHERE (el ID) - Posición 7
            ps.setInt(7, pelicula.getIdPelicula()); 
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al modificar película: " + e.getMessage());
            e.printStackTrace();
            return false;
        } 
    }

    // --- D: DELETE (ELIMINAR) ---
    public boolean eliminarPelicula(int id) {
        String sql = "DELETE FROM DIANA931.PELICULA WHERE ID_PELICULA = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al eliminar película (DAO): " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }
}