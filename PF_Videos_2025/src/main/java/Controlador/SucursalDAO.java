package Controlador;

import Modelo.Sucursal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD;

public class SucursalDAO {

    // --- C: CREATE (INSERCIÓN) ---
  public boolean insertarSucursal(Sucursal sucursal) {
    // La consulta omite NO_Sucursal porque es GENERATED ALWAYS AS IDENTITY
    String call = "{CALL RegistrarNuevaSucursal(?, ?, ?, ?, ?, ?, ?, ?)}";
    try (Connection con = ConexionBD.getInstance().getConnection();
         CallableStatement ps = con.prepareCall(call)) {

        // 1. Asignar parámetros (¡DEBEN SER 8 Y EN EL ORDEN CORRECTO!)
        
        // 1. NOMBRE_SUCURSAL (String)
        ps.setString(1, sucursal.getNombreSucursal()); 
        // 2. NO_TELEFONO (String)
        ps.setString(2, sucursal.getNoTelefono());
        // 3. NUMEROEXTERIOR (Int)
        ps.setInt(3, sucursal.getNumeroExterior());
        // 4. CALLE (String)
        ps.setString(4, sucursal.getCalle());
        // 5. COLONIA (String)
        ps.setString(5, sucursal.getColonia());
        // 6. CIUDAD (String)
        ps.setString(6, sucursal.getCiudad());
        // 7. ESTADO (String)
        ps.setString(7, sucursal.getEstado());
        // 8. CP (String)
        ps.setString(8, sucursal.getCp()); // ¡Este es el valor que faltaba!

       ps.execute();
        return true;

    } catch (SQLException e) {
        System.err.println("Error al insertar sucursal(LLAMADA AL PROCEDIMIENTO ALMACENADO): " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}

    // --- R: READ (OBTENER TODOS) ---
    public List<Sucursal> obtenerTodasLasSucursales() {
        List<Sucursal> lista = new ArrayList<>();
      String sql = "SELECT NO_Sucursal, nombre_sucursal, no_telefono, numeroExterior, calle, colonia, ciudad, estado, CP FROM DIANA931.Sucursal";  try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sucursal sucursal = new Sucursal();
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
        } catch (SQLException e) {
            System.err.println("Error al obtener sucursales: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    // --- U: UPDATE (MODIFICAR) ---
    public boolean modificarSucursal(Sucursal sucursal) {
        String sql = "UPDATE DIANA931.Sucursal SET nombre_sucursal=?, no_telefono=?, numeroExterior=?, calle=?, colonia=?, ciudad=?, estado=?, CP=? WHERE NO_Sucursal=?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { 

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
            
        } catch (SQLException e) {
            System.err.println("Error al modificar sucursal: " + e.getMessage());
            e.printStackTrace();
            return false;
        } 
    }

    // --- D: DELETE (ELIMINAR) ---
    public boolean eliminarSucursal(short id) {
        String sql = "DELETE FROM DIANA931.Sucursal WHERE NO_Sucursal = ?";
        
        try (Connection conn = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            // Asigna el ID (short) de la sucursal al placeholder (?)
            ps.setShort(1, id);
            
            return ps.executeUpdate() > 0;
            
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
        
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setShort(1, idSucursal);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sucursal = new Sucursal();
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
        } catch (SQLException e) {
            System.err.println("Error al obtener sucursal por ID (" + idSucursal + "): " + e.getMessage());
            e.printStackTrace();
        }
        return sucursal;
    }
}