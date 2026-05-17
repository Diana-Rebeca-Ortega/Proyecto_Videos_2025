package Controlador;

import Modelo.Pelicula; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PeliculaDAO {
    // --- C: CREATE (INSERCIÓN) ---
  public boolean insertarPelicula(Pelicula pelicula, int stockInicial, int idSucursal) {
   String sql = "INSERT INTO PELICULA (TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total, ID_SUCURSAL) VALUES (?, ?, ?, ?, ?, ?, ?)"; int nuevoIdPelicula = -1;
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // ... (Asignación de parámetros) ...
            ps.setString(1, pelicula.getTitulo());
            ps.setString(2, pelicula.getCategoria());
            ps.setString(3, pelicula.getDirector());
            ps.setDouble(4, pelicula.getPrecioAlquiler());
            ps.setDouble(5, pelicula.getCosteAdquisicion());
            ps.setInt(6, pelicula.getStockTotal());
            ps.setInt(7, pelicula.getIdSucursal());
            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                // 1. OBTENER el ID
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        nuevoIdPelicula = rs.getInt(1);
                        pelicula.setIdPelicula(nuevoIdPelicula);
                    }
                }
                // 2. LLAMAR al método que genera el stock
                if (nuevoIdPelicula != -1) {
                    boolean stockGenerado = generarStock(nuevoIdPelicula, stockInicial, pelicula.getIdSucursal());
                    if (stockGenerado) {
                        System.out.println("Película y stock inicial generados con éxito.");
                        return true;
                    } else {
                        System.err.println("ADVERTENCIA: Película insertada, pero falló la generación de stock.");
                        return false;
                    }
                }
            }
            return false;
        }

    } catch (SQLException e) {
        System.err.println("Error en la inserción de película o stock: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
public List<Pelicula> obtenerPeliculasPorCategoria(String categoria) {
    List<Pelicula> lista = new ArrayList<>();
    String sql = "SELECT ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total FROM DIANA931.PELICULA";
    StringBuilder sb = new StringBuilder(sql);
    boolean filtrar = categoria != null && !categoria.trim().isEmpty() && !categoria.equals("Buscar Por Categoria");
    
    if (filtrar) {
        sb.append(" WHERE CATEGORIA = ?");
    }
    
    Connection con = null; // ⬅️ Declaración

    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sb.toString())) {
            
            // ... (Lógica de filtro y mapeo) ...
            int index = 1;
            if (filtrar) {
                ps.setString(index, categoria);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    // ... (Mapeo de propiedades) ...
                    pelicula.setIdPelicula(rs.getInt("ID_PELICULA"));
                    pelicula.setTitulo(rs.getString("TITULO"));
                    pelicula.setCategoria(rs.getString("CATEGORIA"));
                    pelicula.setDirector(rs.getString("DIRECTOR"));
                    pelicula.setPrecioAlquiler(rs.getDouble("alquiler_diario"));
                    pelicula.setCosteAdquisicion(rs.getDouble("coste_venta"));
                    pelicula.setStockTotal(rs.getInt("Stock_total"));
                    
                    lista.add(pelicula);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener películas por categoría: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}

    // --- R: READ (OBTENER POR ID) ---
 public Pelicula obtenerPeliculaPorId(int idPelicula) {
    String sql = "SELECT ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total FROM DIANA931.PELICULA WHERE ID_PELICULA = ?";
    Pelicula pelicula = null;
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources

        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPelicula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("ID_PELICULA"));
                    pelicula.setTitulo(rs.getString("TITULO"));
                    pelicula.setCategoria(rs.getString("CATEGORIA"));
                    pelicula.setDirector(rs.getString("DIRECTOR"));
                    pelicula.setPrecioAlquiler(rs.getDouble("alquiler_diario"));
                    pelicula.setCosteAdquisicion(rs.getDouble("coste_venta"));
                    pelicula.setStockTotal(rs.getInt("Stock_total"));
                }
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
    String sql = "UPDATE DIANA931.PELICULA SET TITULO=?, CATEGORIA=?, DIRECTOR=?, alquiler_diario=?, coste_venta=?, Stock_total=? WHERE ID_PELICULA=?";
    
    Connection conn = null; // ⬅️ Declaración

    try {
        conn = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

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
            
        } 
    } catch (SQLException e) {
        System.err.println("Error al modificar película: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    // --- D: DELETE (ELIMINAR) ---
 public boolean eliminarPelicula(int id) {
    String sql = "DELETE FROM DIANA931.PELICULA WHERE ID_PELICULA = ?";
    
    Connection conn = null; // ⬅️ Declaración

    try {
        conn = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            
            return ps.executeUpdate() > 0;
            
        }
    } catch (SQLException e) {
        System.err.println("Error al eliminar película (DAO): " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
 public int contarCopiasDisponibles(int idPelicula) {
   String sql = "SELECT dbo.ContarCopiasDisponibles(?)";
    int copiasDisponibles = -1;
    
    Connection con = null; // ⬅️ Declaración

    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idPelicula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    copiasDisponibles = rs.getInt(1);
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al llamar a la función ContarCopiasDisponibles: " + e.getMessage());
        e.printStackTrace();
    }
    return copiasDisponibles;
} 
 public boolean generarStock(int idCatalogo, int stockTotal, int idSucursal) {
  String call = "{call GenerarCopiasStock(?, ?, ?)}";
    
    Connection con = null; // ⬅️ Declaración

    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para CallableStatement
        try (CallableStatement cs = con.prepareCall(call)) {
            
            cs.setInt(1, idCatalogo);
            cs.setInt(2, stockTotal);
            cs.setInt(3, idSucursal);

            cs.execute();
            
            return true;
            
        } 
    } catch (SQLException e) {
        System.err.println("Error al generar copias de stock: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
 public DefaultTableModel cargarDatosTabla(String filtroCategoria) {
    // Definimos las columnas exactas de tu tabla en SQL Server
    String columnasSelect = "ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, ALQUILER_DIARIO, COSTE_VENTA, STOCK_TOTAL";
    String[] columnNames = {"ID_Pelicula", "Titulo", "Categoría", "Director", "Alquiler", "Coste", "Stock Disponible"};
    DefaultTableModel model = new DefaultTableModel(null, columnNames);
    
    String sql;
    // Ajuste para evitar el error de <nulltype> y manejar "Todas las Categorías"
    boolean tieneFiltro = filtroCategoria != null 
                          && !filtroCategoria.trim().isEmpty() 
                          && !filtroCategoria.equalsIgnoreCase("Todas las Categorías")
                          && !filtroCategoria.equalsIgnoreCase("null");

    if (tieneFiltro) {
        // Quitamos DIANA931.
        sql = "SELECT " + columnasSelect + " FROM PELICULA WHERE CATEGORIA = ?";
    } else {
        // Quitamos DIANA931.
        sql = "SELECT " + columnasSelect + " FROM PELICULA";
    }

    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement pstmt = con.prepareStatement(sql)) {
        
        if (tieneFiltro) {
            pstmt.setString(1, filtroCategoria);
        }

        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[7];
                row[0] = rs.getInt("ID_PELICULA");
                row[1] = rs.getString("TITULO");
                row[2] = rs.getString("CATEGORIA");
                row[3] = rs.getString("DIRECTOR");
                row[4] = rs.getDouble("ALQUILER_DIARIO");
                row[5] = rs.getDouble("COSTE_VENTA");
                row[6] = rs.getInt("STOCK_TOTAL");
                model.addRow(row);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error SQL al cargar datos en la tabla: " + e.getMessage());
        e.printStackTrace();
    }
    return model;
}
 public List<Pelicula> buscarPeliculasDinamico(String textoBusqueda, String tipoFiltro) {
    List<Pelicula> lista = new ArrayList<>();
    
    // Consulta base
    String sqlBase = "SELECT ID_PELICULA, TITULO, CATEGORIA, DIRECTOR, alquiler_diario, coste_venta, Stock_total FROM PELICULA";
    StringBuilder sb = new StringBuilder(sqlBase);
    
    boolean tieneTexto = textoBusqueda != null && !textoBusqueda.trim().isEmpty();
    
    // Construimos el filtro WHERE dinámicamente
    if (tieneTexto) {
        if ("ID".equalsIgnoreCase(tipoFiltro)) {
            sb.append(" WHERE ID_PELICULA = ?");
        } else if ("Nombre".equalsIgnoreCase(tipoFiltro)) {
            sb.append(" WHERE TITULO LIKE ?");
        }
    }
    
    Connection con = null;
    try {
        con = ConexionBD.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(sb.toString())) {
            
            // Asignamos el parámetro según el tipo de filtro
            if (tieneTexto) {
                if ("ID".equalsIgnoreCase(tipoFiltro)) {
                    // Convertimos la cadena a entero de forma segura
                    ps.setInt(1, Integer.parseInt(textoBusqueda.trim()));
                } else if ("Nombre".equalsIgnoreCase(tipoFiltro)) {
                    // Usamos % para búsquedas parciales (ej: "mar" encuentra "Matrix" o "Iron Man")
                    ps.setString(1, "%" + textoBusqueda.trim() + "%");
                }
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setIdPelicula(rs.getInt("ID_PELICULA"));
                    pelicula.setTitulo(rs.getString("TITULO"));
                    pelicula.setCategoria(rs.getString("CATEGORIA"));
                    pelicula.setDirector(rs.getString("DIRECTOR"));
                    pelicula.setPrecioAlquiler(rs.getDouble("alquiler_diario"));
                    pelicula.setCosteAdquisicion(rs.getDouble("coste_venta"));
                    pelicula.setStockTotal(rs.getInt("Stock_total"));
                    
                    lista.add(pelicula);
                }
            }
        }
    } catch (NumberFormatException e) {
        System.err.println("Error: El valor ingresado para ID no es un número válido.");
    } catch (SQLException e) {
        System.err.println("Error al realizar búsqueda dinámica de películas: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
}