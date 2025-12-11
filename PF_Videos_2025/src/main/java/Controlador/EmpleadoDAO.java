package Controlador;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class EmpleadoDAO {
    private static final String NOMBRE_TABLA = "DIANA931.EMPLEADO";
    // --- C: CREATE (INSERCIÓN) ---
    public boolean insertarEmpleado(Empleado empleado) {
    String sql = "INSERT INTO " + NOMBRE_TABLA + 
                 " (NOMBRE, FNAME, LNAME, CATEGORIA, ID_SUCURSAL) " +
                 "VALUES (?, ?, ?, ?, ?)";
    // 1. Declarar la conexión fuera
    Connection con = null;
    try {
        // 2. Obtener la conexión fuera de try-with-resources
        con = ConexionBD.getInstance().getConnection();         
        // 3. Usar try-with-resources SOLO para PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { 
            // Asignar parámetros...
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getFName());
            ps.setString(3, empleado.getLName());
            ps.setString(4, empleado.getCategoria());
            ps.setInt(5, empleado.getIdSucursal());

            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                 try (ResultSet rs = ps.getGeneratedKeys()) {
                     if (rs.next()) {
                         empleado.setIdEmpleado(rs.getInt(1)); 
                     }
                 }
                 return true;
             }
             return false;
        }
    } catch (SQLException e) {
        System.err.println("Error al insertar empleado: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
  
}

    // --- R: READ (OBTENER TODOS) ---
    public List<Empleado> obtenerTodosLosEmpleados() {
    List<Empleado> lista = new ArrayList<>();
    String sql = "SELECT ID_EMPLEADO, NOMBRE, FNAME, LNAME, CATEGORIA, ID_SUCURSAL FROM " + NOMBRE_TABLA;
    
    // Obtenemos la conexión fuera del try-with-resources
    Connection con = null;

    try {
        con = ConexionBD.getInstance().getConnection();
        
        // El try-with-resources solo maneja PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                // ... (tu lógica de mapeo de Empleado) ...
                empleado.setIdEmpleado(rs.getInt("ID_EMPLEADO"));   // INT
                empleado.setNombre(rs.getString("NOMBRE"));
                empleado.setFName(rs.getString("FNAME"));
                empleado.setLName(rs.getString("LNAME"));
                empleado.setCategoria(rs.getString("CATEGORIA"));
                empleado.setIdSucursal(rs.getInt("ID_SUCURSAL"));   // INT (Clave Foránea)
                lista.add(empleado);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener empleados: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
    
    // --- R: READ (OBTENER POR ID) ---
  public Empleado obtenerEmpleadoPorId(int idEmpleado) {
    String sql = "SELECT ID_EMPLEADO, NOMBRE, FNAME, LNAME, CATEGORIA, ID_SUCURSAL FROM " + NOMBRE_TABLA + " WHERE ID_EMPLEADO = ?";
    Empleado empleado = null;
    Connection con = null; // Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // Obtención
        
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    // ... (tu lógica de mapeo de Empleado) ...
                    empleado.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                    empleado.setNombre(rs.getString("NOMBRE"));
                    empleado.setFName(rs.getString("FNAME"));
                    empleado.setLName(rs.getString("LNAME"));
                    empleado.setCategoria(rs.getString("CATEGORIA"));
                    empleado.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                }
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener empleado por ID (" + idEmpleado + "): " + e.getMessage());
        e.printStackTrace();
    }
    return empleado;
}

    // --- U: UPDATE (MODIFICAR) ---
 public boolean modificarEmpleado(Empleado empleado) {
    String sql = "UPDATE " + NOMBRE_TABLA + " SET NOMBRE=?, FNAME=?, LNAME=?, CATEGORIA=?, ID_SUCURSAL=? WHERE ID_EMPLEADO=?";
    Connection conn = null; // Declaración

    try {
        conn = ConexionBD.getInstance().getConnection(); // Obtención
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Parámetros SET (1-5)
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getFName());
            ps.setString(3, empleado.getLName());
            ps.setString(4, empleado.getCategoria());
            ps.setInt(5, empleado.getIdSucursal()); // ID_SUCURSAL

            // 2. Condición WHERE (el ID) - Posición 6
            ps.setInt(6, empleado.getIdEmpleado());

            return ps.executeUpdate() > 0;
        }

    } catch (SQLException e) {
        System.err.println("Error al modificar empleado: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    // --- D: DELETE (ELIMINAR) ---
  public boolean eliminarEmpleado(int id) {
    String sql = "DELETE FROM " + NOMBRE_TABLA + " WHERE ID_EMPLEADO = ?";
    Connection conn = null; // Declaración

    try {
        conn = ConexionBD.getInstance().getConnection(); // Obtención
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna el ID (int) del empleado al placeholder (?)
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }

    } catch (SQLException e) {
        System.err.println("Error al eliminar empleado (DAO): " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}