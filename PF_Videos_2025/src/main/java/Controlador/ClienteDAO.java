package Controlador;

import Modelo.Cliente;
import java.sql.*; 
import java.util.ArrayList;
import java.util.List;
import  ConneccionBD.ConexionBD;

public class ClienteDAO {

public List<Cliente> obtenerTodosLosClientes() {
    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES";
    Connection con = null; 
    try {
        con = ConexionBD.getInstance().getConnection(); 
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { 

            while (rs.next()) {
                Cliente cliente = new Cliente();
                // ... (Tu lógica de mapeo de Cliente) ...
                cliente.setNoCliente(rs.getInt("NO_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido1(rs.getString("APELLIDO1"));
                cliente.setApellido2(rs.getString("APELLIDO2"));
                cliente.setNo_exterior(rs.getInt("NO_EXTERIOR")); 
                cliente.setCalle(rs.getString("CALLE"));
                cliente.setColonia(rs.getString("COLONIA"));
                cliente.setCiudad(rs.getString("CIUDAD"));
                cliente.setCp(rs.getString("CP"));
                cliente.setEstado(rs.getString("ESTADO"));
                cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                cliente.setNoSucursal(rs.getShort("NO_SUCURSAL"));
                lista.add(cliente);
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener clientes: " + e.getMessage());
    }
    return lista;
}

public boolean insertarCliente(Cliente cliente) {
    String sql = "INSERT INTO DIANA931.CLIENTES (NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT DATE, ?)";    
    Connection con = null; 
    try {
        con = ConexionBD.getInstance().getConnection(); 
        try (PreparedStatement ps = con.prepareStatement(sql)) { 
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setInt(4, cliente.getNo_exterior());
            ps.setString(5, cliente.getCalle());
            ps.setString(6, cliente.getColonia());
            ps.setString(7, cliente.getCiudad());
            ps.setString(8, cliente.getCp());
            ps.setString(9, cliente.getEstado());
            ps.setShort(10, cliente.getNoSucursal());            
            return ps.executeUpdate() > 0;
        }
    } catch (SQLException e) {
        System.err.println("Error al insertar cliente: " + e.getMessage());
        return false;
    }
}
//CAMBIOS CLIENTE 
public boolean modificarCliente(Cliente cliente) {
    String sql = "UPDATE DIANA931.CLIENTES SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, CALLE=?, CIUDAD=?, ESTADO=?, CP=?, NO_SUCURSAL=?, NO_EXTERIOR=?, COLONIA=? WHERE NO_CLIENTE=?";
   
    Connection conn = null; 
    try {
        conn = ConexionBD.getInstance().getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql)) { 
            
            // ... (Tu lógica de asignación de parámetros) ...
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setString(4, cliente.getCalle());
            ps.setString(5, cliente.getCiudad());
            ps.setString(6, cliente.getEstado());
            ps.setString(7, cliente.getCp());
            ps.setShort(8, cliente.getNoSucursal());
            ps.setString(9, String.valueOf(cliente.getNo_exterior())); 
            ps.setString(10, cliente.getColonia());
            ps.setInt(11, cliente.getNoCliente());
            
            return ps.executeUpdate() > 0;
            
        } 
    } catch (SQLException e) {
        System.err.println("Error al modificar cliente: " + e.getMessage());
        return false;
    } 
}

// ELIMINAR CLIENTE
public boolean eliminarCliente(int id) {
    String sql = "DELETE FROM DIANA931.CLIENTES WHERE NO_CLIENTE = ?";
    
    Connection conn = null; // ⬅️ Declaramos fuera
    
    try {
        conn = ConexionBD.getInstance().getConnection(); // ⬅️ Inicializamos fuera        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {             
            ps.setInt(1, id);            
            return ps.executeUpdate() > 0;            
        } 
    } catch (SQLException e) {
        System.err.println("Error al eliminar cliente (DAO): " + e.getMessage());
        e.printStackTrace(); 
        return false;
    }
}
public Cliente obtenerClientePorId(int idCliente) {
    String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES WHERE NO_CLIENTE = ?";
    Cliente cliente = null;    
    Connection con = null; 
    try {
        con = ConexionBD.getInstance().getConnection(); 
        // try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) { 

            ps.setInt(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) { // ResultSet sí se cierra automáticamente
                if (rs.next()) {
                    cliente = new Cliente();
                    // ... (Tu lógica de mapeo de Cliente) ...
                    cliente.setNoCliente(rs.getInt("NO_CLIENTE"));
                    cliente.setNombre(rs.getString("NOMBRE"));
                    cliente.setApellido1(rs.getString("APELLIDO1"));
                    cliente.setApellido2(rs.getString("APELLIDO2"));
                    cliente.setNo_exterior(rs.getInt("NO_EXTERIOR"));
                    cliente.setCalle(rs.getString("CALLE"));
                    cliente.setColonia(rs.getString("COLONIA"));
                    cliente.setCiudad(rs.getString("CIUDAD"));
                    cliente.setCp(rs.getString("CP"));
                    cliente.setEstado(rs.getString("ESTADO"));
                    cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
                    cliente.setNoSucursal(rs.getShort("NO_SUCURSAL"));
                }
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener cliente por ID (" + idCliente + "): " + e.getMessage());
    }
    return cliente;
}

}
