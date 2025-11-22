package Controlador;

import Modelo.Pelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class PeliculaDAO {

    // --- C: CREATE (INSERCIÓN) ---
   public boolean insertarPelicula(Pelicula pelicula, int stockInicial, int idSucursal) {
   String sql = "INSERT INTO DIANA931.PELICULA (TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total, ID_SUCURSAL) VALUES (?, ?, ?, ?, ?, ?, ?)";
   int nuevoIdPelicula = -1;

    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, pelicula.getTitulo());
        ps.setString(2, pelicula.getCategoria());
        ps.setString(3, pelicula.getDirector());
        ps.setDouble(4, pelicula.getPrecioAlquiler());
        ps.setDouble(5, pelicula.getCosteAdquisicion());
        ps.setInt(6, pelicula.getStockTotal());
        ps.setInt(7, pelicula.getIdSucursal());
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            
            // 1. OBTENER el ID de la película recién insertada
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    nuevoIdPelicula = rs.getInt(1); 
                    pelicula.setIdPelicula(nuevoIdPelicula); // Guardar el ID en el objeto
                }
            }

            // 2. LLAMAR al método que genera el stock
            if (nuevoIdPelicula != -1) {
                
                // NOTA: Asume que el método 'generarStock' existe en este mismo DAO.
                boolean stockGenerado = generarStock(nuevoIdPelicula, stockInicial, pelicula.getIdSucursal()); 
                
                if (stockGenerado) {
                    System.out.println("Película y stock inicial generados con éxito.");
                    return true;
                } else {
                    // Si falla la generación de stock, se podría considerar un ROLLBACK,
                    // pero por ahora solo manejamos el error.
                    System.err.println("ADVERTENCIA: Película insertada, pero falló la generación de stock.");
                    return false; 
                }
            }
        }
        return false;

    } catch (SQLException e) {
        System.err.println("Error en la inserción de película o stock: " + e.getMessage());
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
   public int contarCopiasDisponibles(int idPelicula) {
    // 🔑 CORRECCIÓN: Usamos SELECT de la FUNCIÓN sobre SYSIBM.SYSDUMMY1
    // Esto es la forma más estable de llamar una función escalar en DB2 desde JDBC
   String sql = "SELECT DIANA931.CONTARCOPIASDISPONIBLES(?) FROM SYSIBM.SYSDUMMY1";
   int copiasDisponibles = -1;

    // Cambiamos a PreparedStatement
    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // 1. Asignar el parámetro de ENTRADA (el ID de la película)
        ps.setInt(1, idPelicula);

        // 2. Ejecutar la consulta
        try (ResultSet rs = ps.executeQuery()) {
            
            // 3. Obtener el resultado
            if (rs.next()) {
                // El resultado está en la primera columna del ResultSet
                copiasDisponibles = rs.getInt(1); 
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al llamar a la función ContarCopiasDisponibles: " + e.getMessage());
        e.printStackTrace();
    }
    return copiasDisponibles;
}
   
   public boolean generarStock(int idCatalogo, int stockTotal, int idSucursal) {
    String call = "{CALL DIANA931.GenerarCopiasStock(?, ?, ?)}";

    try (Connection con = ConexionBD.getInstance().getConnection();
         CallableStatement cs = con.prepareCall(call)) {
        
        // 1. Asignar p_id_catalogo
        cs.setInt(1, idCatalogo);
        
        // 2. Asignar p_stock_total
        cs.setInt(2, stockTotal); 
        
        // 3. Asignar p_id_sucursal
        cs.setInt(3, idSucursal);

        // Ejecutar el procedimiento
        cs.execute();
        
        return true;
        
    } catch (SQLException e) {
        System.err.println("Error al generar copias de stock: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}