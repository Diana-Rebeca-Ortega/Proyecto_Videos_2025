package Controlador;

import Modelo.Sucursal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class SucursalDAO {

    // --- C: CREATE (INSERCIÓN) ---
 public boolean insertarSucursal(Sucursal sucursal) {
     //Llamando al procedimiento999999999999999999999999999999999999999999
    String call = "{CALL RegistrarNuevaSucursal(?, ?, ?, ?, ?, ?, ?, ?)}";    
    Connection con = null;
    try {
        con = ConexionBD.getInstance().getConnection(); 
        try (CallableStatement ps = con.prepareCall(call)) {
            ps.setString(1, sucursal.getNombreSucursal());
            ps.setString(2, sucursal.getNoTelefono());
            ps.setInt(3, sucursal.getNumeroExterior());
            ps.setString(4, sucursal.getCalle());
            ps.setString(5, sucursal.getColonia());
            ps.setString(6, sucursal.getCiudad());
            ps.setString(7, sucursal.getEstado());
            ps.setString(8, sucursal.getCp());

            ps.execute();
            return true;

        }
    } catch (SQLException e) {
        System.err.println("Error al insertar sucursal(LLAMADA AL PROCEDIMIENTO ALMACENADO): " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    // --- R: READ (OBTENER TODOS) ---
 public List<Sucursal> obtenerTodasLasSucursales() {
    List<Sucursal> lista = new ArrayList<>();
    String sql = "SELECT NO_Sucursal, nombre_sucursal, no_telefono, numeroExterior, calle, colonia, ciudad, estado, CP FROM DIANA931.Sucursal";
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement y ResultSet
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
                // ... (Tu lógica de mapeo) ...
                sucursal.setNoSucursal(rs.getShort("NO_Sucursal"));
                sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
                sucursal.setNoTelefono(rs.getString("no_telefono"));
                sucursal.setNumeroExterior(rs.getInt("numeroExterior"));
                sucursal.setCalle(rs.getString("calle"));
                sucursal.setColonia(rs.getString("colonia"));
                sucursal.setCiudad(rs.getString("ciudad"));
                sucursal.setEstado(rs.getString("estado"));
                sucursal.setCp(rs.getString("CP"));
                lista.add(sucursal);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener sucursales: " + e.getMessage());
        e.printStackTrace();
    }
    return lista;
}
    // --- U: UPDATE (MODIFICAR) ---
 public boolean modificarSucursal(Sucursal sucursal) {
    String sql = "UPDATE DIANA931.Sucursal SET nombre_sucursal=?, no_telefono=?, numeroExterior=?, calle=?, colonia=?, ciudad=?, estado=?, CP=? WHERE NO_Sucursal=?";
    
    Connection conn = null; // ⬅️ Declaración
    
    try {
        conn = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // 1. Parámetros SET (1-8)
            ps.setString(1, sucursal.getNombreSucursal());
            ps.setString(2, sucursal.getNoTelefono());
            ps.setInt(3, sucursal.getNumeroExterior());
            ps.setString(4, sucursal.getCalle());
            ps.setString(5, sucursal.getColonia());
            ps.setString(6, sucursal.getCiudad());
            ps.setString(7, sucursal.getEstado());
            ps.setString(8, sucursal.getCp());

            // 2. Condición WHERE (el ID) - Posición 9
            ps.setShort(9, sucursal.getNoSucursal());
            
            return ps.executeUpdate() > 0;
            
        } 
    } catch (SQLException e) {
        System.err.println("Error al modificar sucursal: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    // --- D: DELETE (ELIMINAR) ---
 public boolean eliminarSucursal(short id) {
    String sql = "DELETE FROM DIANA931.Sucursal WHERE NO_Sucursal = ?";
    
    Connection conn = null; // ⬅️ Declaración
    
    try {
        conn = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Asigna el ID (short) de la sucursal al placeholder (?)
            ps.setShort(1, id);
            
            return ps.executeUpdate() > 0;
            
        } 
    } catch (SQLException e) {
        System.err.println("Error al eliminar sucursal (DAO): " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
    // --- R: READ (OBTENER POR ID) ---
 public Sucursal obtenerSucursalPorId(short idSucursal) {
    String sql = "SELECT NO_Sucursal, nombre_sucursal, no_telefono, numeroExterior, calle, colonia, ciudad, estado, CP FROM DIANA931.Sucursal WHERE NO_Sucursal = ?";
    Sucursal sucursal = null;
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        con = ConexionBD.getInstance().getConnection(); // ⬅️ Obtención fuera del try-with-resources
        
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setShort(1, idSucursal);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sucursal = new Sucursal();
                    // ... (Tu lógica de mapeo) ...
                    sucursal.setNoSucursal(rs.getShort("NO_Sucursal"));
                    sucursal.setNombreSucursal(rs.getString("nombre_sucursal"));
                    sucursal.setNoTelefono(rs.getString("no_telefono"));
                    sucursal.setNumeroExterior(rs.getInt("numeroExterior"));
                    sucursal.setCalle(rs.getString("calle"));
                    sucursal.setColonia(rs.getString("colonia"));
                    sucursal.setCiudad(rs.getString("ciudad"));
                    sucursal.setEstado(rs.getString("estado"));
                    sucursal.setCp(rs.getString("CP"));
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener sucursal por ID (" + idSucursal + "): " + e.getMessage());
        e.printStackTrace();
    }
    return sucursal;
}
}