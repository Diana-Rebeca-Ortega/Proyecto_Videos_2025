package Controlador;

import ConneccionBD.ConexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteDAO {
   
    public List<Map<String, Object>> obtenerPeliculasPopulares(Date fechaInicio, Date fechaFin) {
        
        List<Map<String, Object>> datosReporte = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // La consulta SQL definida arriba. Asegúrate de ajustar el límite si es necesario.
        String sql = "SELECT TITULO_PELICULA, COUNT(ID_ALQUILER) AS VECES_ALQUILADA " +
                     "FROM V_ALQUILERES_CON_ESTADO " +
                     "WHERE FECHA_ALQUILER >= ? AND FECHA_ALQUILER <= ? " +
                     "GROUP BY TITULO_PELICULA " +
                     "ORDER BY VECES_ALQUILADA DESC " +
                     "FETCH FIRST 10 ROWS ONLY";
        
        try {
            con = ConexionBD.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            
            // 1. Establecer parámetros de fecha
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            
            rs = ps.executeQuery();
            
            // 2. Mapear resultados a una lista de Mapas
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                
                // Usamos las claves exactas que JFreeChart espera:
                fila.put("TITULO", rs.getString("TITULO_PELICULA"));
                fila.put("VECES_ALQUILADA", rs.getInt("VECES_ALQUILADA"));
                
                datosReporte.add(fila);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener películas populares: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Asegúrate de tener una función para cerrar recursos (closeAll/closeRsPs)
            // Si usas try-with-resources, puedes simplificar esto
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close();                 
            } catch (Exception e) {
                System.err.println("Error cerrando recursos: " + e.getMessage());
            }
        }
        
        return datosReporte;
    }

    public List<Map<String, Object>> obtenerAlquileresPorPeriodo(java.util.Date fechaInicio, java.util.Date fechaFin) {
        
        List<Map<String, Object>> datosReporte = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // Consulta que extrae todos los campos relevantes de tu vista V_ALQUILERES_CON_ESTADO
        String sql = "SELECT ID_ALQUILER, NO_CLIENTE, NOMBRE_CLIENTE, TITULO_PELICULA, " + 
                     "FECHA_ALQUILER, FECHA_VENCIMIENTO, ESTADO_ACTUAL " +
                     "FROM V_ALQUILERES_CON_ESTADO " +
                     "WHERE FECHA_ALQUILER >= ? AND FECHA_ALQUILER <= ? " +
                     "ORDER BY FECHA_ALQUILER DESC"; // Ordenar por fecha para mejor visualización
        
        try {
            con = ConexionBD.getInstance().getConnection();
            ps = con.prepareStatement(sql);
            
            // Convertir java.util.Date a java.sql.Date para el PreparedStatement
            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));
            
            rs = ps.executeQuery();
            
            // Mapear resultados a una lista de Mapas
            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                
                // Mapeo de columnas (DEBEN COINCIDIR con los nombres de la consulta SQL)
                fila.put("ID_ALQUILER", rs.getInt("ID_ALQUILER"));
                fila.put("NO_CLIENTE", rs.getInt("NO_CLIENTE"));
                fila.put("NOMBRE_CLIENTE", rs.getString("NOMBRE_CLIENTE"));
                fila.put("TITULO_PELICULA", rs.getString("TITULO_PELICULA"));
                fila.put("FECHA_ALQUILER", rs.getDate("FECHA_ALQUILER"));
                fila.put("FECHA_VENCIMIENTO", rs.getDate("FECHA_VENCIMIENTO"));
                fila.put("ESTADO_ACTUAL", rs.getString("ESTADO_ACTUAL"));
                
                datosReporte.add(fila);
            }
            
        } catch (Exception e) {
            System.err.println("Error al obtener alquileres por período: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cierre seguro de recursos
            try { 
                if (rs != null) rs.close(); 
                if (ps != null) ps.close();                
            } catch (Exception e) {
                System.err.println("Error cerrando recursos en ReporteDAO: " + e.getMessage());
            }
        }
        
        return datosReporte;
    }
}