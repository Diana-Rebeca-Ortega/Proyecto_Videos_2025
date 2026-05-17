package Controlador;

import Modelo.CopiaPelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class CopiaPeliculaDAO {

    // 1. OBTENER TODAS LAS COPIAS DE UNA PELÍCULA EN UNA SUCURSAL
    public List<CopiaPelicula> obtenerCopiasPorPeliculaYSucursal(int idPelicula, int idSucursal) {
        List<CopiaPelicula> lista = new ArrayList<>();
        String sql = "SELECT ID_Copia_Pelicula, ID_Pelicula, ID_Sucursal, Estado FROM COPIA_PELICULA WHERE ID_Pelicula = ? AND ID_Sucursal = ?";
        Connection con = null; 
        
        try {
            con = ConexionBD.getInstance().getConnection(); 

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idPelicula);
                ps.setInt(2, idSucursal);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        CopiaPelicula copia = new CopiaPelicula();
                        copia.setIdCopiaPelicula(rs.getInt("ID_Copia_Pelicula"));
                        copia.setIdPelicula(rs.getInt("ID_Pelicula"));
                        copia.setIdSucursal(rs.getInt("ID_Sucursal"));
                        copia.setEstado(rs.getString("Estado"));
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

    // 2. OBTENER UNA COPIA DISPONIBLE PARA LA RENTA
    public int obtenerIdCopiaDisponible(int idPelicula, int idSucursal) {
        int idCopia = -1;
        
        // CAMBIO AQUÍ: Probamos con ID_Copia en lugar de ID_Copia_Pelicula
        String sql = "SELECT TOP 1 ID_Copia FROM COPIA_PELICULA " +
                     "WHERE ID_Pelicula = ? AND ID_Sucursal = ? AND UPPER(Estado) = 'DISPONIBLE'";
        Connection con = null; 

        try {
            con = ConexionBD.getInstance().getConnection();
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idPelicula);
                ps.setInt(2, idSucursal);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // CAMBIO AQUÍ TAMBIÉN: Debe coincidir con el SELECT
                        idCopia = rs.getInt("ID_Copia");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de copia disponible: " + e.getMessage());
            e.printStackTrace();
        }
        return idCopia;
    }

    // 3. OBTENER OBJETO COPIA COMPLETO POR SU ID ÚNICO
    public CopiaPelicula obtenerCopiaPorId(int idCopia) {
        CopiaPelicula copia = null;
        String sql = "SELECT ID_Copia_Pelicula, ID_Pelicula, ID_Sucursal, Estado FROM COPIA_PELICULA WHERE ID_Copia_Pelicula = ?";
        Connection con = null; 

        try {
            con = ConexionBD.getInstance().getConnection(); 
            
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idCopia);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        copia = new CopiaPelicula();
                        copia.setIdCopiaPelicula(rs.getInt("ID_Copia_Pelicula"));
                        copia.setIdPelicula(rs.getInt("ID_Pelicula"));
                        copia.setIdSucursal(rs.getInt("ID_Sucursal"));
                        copia.setEstado(rs.getString("Estado"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener copia por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return copia;
    }

    // 4. OBTENER EL ID DE LA PELÍCULA MAESTRA A PARTIR DE UNA COPIA FÍSICA
    public int obtenerIdPeliculaMaestraPorCopia(int idCopia) {   
        String sql = "SELECT ID_Pelicula FROM COPIA_PELICULA WHERE ID_Copia_Pelicula = ?";
        int idMaestra = -1;    
        Connection con = null;
        try {
            con = ConexionBD.getInstance().getConnection();        
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idCopia);
                
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        idMaestra = rs.getInt("ID_Pelicula");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID_Pelicula por ID_Copia_Pelicula: " + e.getMessage());
            e.printStackTrace();
        }
        return idMaestra;
    }

    // 5. ACTUALIZAR EL ESTADO DE LA COPIA (Ej: Disponible ➡️ Rentada)
    public boolean actualizarEstadoCopia(int idCopia, String nuevoEstado) {
        String sql = "UPDATE COPIA_PELICULA SET Estado = ? WHERE ID_Copia_Pelicula = ?";
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

       // 6. CONTAR COPIAS DISPONIBLES UTILIZANDO LA FUNCIÓN DE TU BASE DE DATOS  
    public int contarCopiasDisponibles(int idPelicula) {
        String sql = "SELECT dbo.ContarCopiasDisponibles(?) AS Total";
        int copiasDisponibles = 0;
        Connection con = null;

        try {
            con = ConexionBD.getInstance().getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idPelicula);
               
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        copiasDisponibles = rs.getInt(1); 
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la función CONTARCOPIASDISPONIBLES: " + e.getMessage());
            e.printStackTrace();
            return 0; 
        }
        return copiasDisponibles;
    }
}