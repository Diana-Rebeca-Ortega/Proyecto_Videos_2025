package Controlador;

import Modelo.Empleado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class EmpleadoDAO {

    // Nombre de la tabla de la base de datos (Asumo que está en el esquema DIANA931)
    private static final String NOMBRE_TABLA = "DIANA931.EMPLEADO";

    // --- C: CREATE (INSERCIÓN) ---
    public boolean insertarEmpleado(Empleado empleado) {
        // La consulta omite ID_Empleado si es autoincrementable (GENERATED ALWAYS AS IDENTITY)
        String sql = "INSERT INTO " + NOMBRE_TABLA + 
                     " (NOMBRE, FNAME, LNAME, CATEGORIA, ID_SUCURSAL) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Asignar parámetros (¡DEBEN SER 5 Y EN EL ORDEN CORRECTO!)
            ps.setString(1, empleado.getNombre());        // 1. NOMBRE
            ps.setString(2, empleado.getFName());         // 2. FNAME
            ps.setString(3, empleado.getLName());         // 3. LNAME
            ps.setString(4, empleado.getCategoria());     // 4. CATEGORIA
            ps.setInt(5, empleado.getIdSucursal());       // 5. ID_SUCURSAL (INT)

            int filasAfectadas = ps.executeUpdate();
            
            // Si el ID_Empleado es autoincrementable, lo recuperamos y lo asignamos al objeto.
            if (filasAfectadas > 0) {
                 try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        // Asumo que el ID es un entero (INT) autogenerado
                        empleado.setIdEmpleado(rs.getInt(1)); 
                    }
                }
                return true;
            }
            return false;

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
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("ID_EMPLEADO"));   // INT
                empleado.setNombre(rs.getString("NOMBRE"));
                empleado.setFName(rs.getString("FNAME"));
                empleado.setLName(rs.getString("LNAME"));
                empleado.setCategoria(rs.getString("CATEGORIA"));
                empleado.setIdSucursal(rs.getInt("ID_SUCURSAL"));   // INT (Clave Foránea)
                lista.add(empleado);
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

        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                    empleado.setNombre(rs.getString("NOMBRE"));
                    empleado.setFName(rs.getString("FNAME"));
                    empleado.setLName(rs.getString("LNAME"));
                    empleado.setCategoria(rs.getString("CATEGORIA"));
                    empleado.setIdSucursal(rs.getInt("ID_SUCURSAL"));
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

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Parámetros SET (1-5)
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getFName());
            ps.setString(3, empleado.getLName());
            ps.setString(4, empleado.getCategoria());
            ps.setInt(5, empleado.getIdSucursal()); // ID_SUCURSAL

            // 2. Condición WHERE (el ID) - Posición 6
            ps.setInt(6, empleado.getIdEmpleado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar empleado: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- D: DELETE (ELIMINAR) ---
    public boolean eliminarEmpleado(int id) {
        String sql = "DELETE FROM " + NOMBRE_TABLA + " WHERE ID_EMPLEADO = ?";

        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Asigna el ID (int) del empleado al placeholder (?)
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar empleado (DAO): " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}