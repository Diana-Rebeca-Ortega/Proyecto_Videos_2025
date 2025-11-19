package Controlador;

import Modelo.Cliente;
import java.sql.*; // Importa todo lo necesario
import java.util.ArrayList;
import java.util.List;
import  ConneccionBD.ConexionBD;

public class ClienteDAO {

public List<Cliente> obtenerTodosLosClientes() {
    List<Cliente> lista = new ArrayList<>();
  String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES";  
    try (Connection con = ConexionBD.getInstance().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNoCliente(rs.getString("NO_CLIENTE"));
            cliente.setNombre(rs.getString("NOMBRE"));
            cliente.setApellido1(rs.getString("APELLIDO1"));
            cliente.setApellido2(rs.getString("APELLIDO2"));
            cliente.setNo_exterior(rs.getInt("NO_EXTERIOR")); // Mapeo de la nueva columna
            cliente.setCalle(rs.getString("CALLE"));
            cliente.setColonia(rs.getString("COLONIA"));       // Mapeo de la nueva columna
            cliente.setCalle(rs.getString("CALLE"));
            cliente.setCiudad(rs.getString("CIUDAD"));
            cliente.setCp(rs.getString("CP"));
            cliente.setEstado(rs.getString("ESTADO")); // Asumo que el campo se llama ESTADO
            cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
            cliente.setNoSucursal(rs.getString("NO_SUCURSAL"));
            // ----------------------------------------------------
            lista.add(cliente);
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener clientes: " + e.getMessage());
    }
    return lista;
}

public boolean insertarCliente(Cliente cliente) {
    // La consulta ahora tiene 10 signos de interrogación ('?')
    String sql = "INSERT INTO DIANA931.CLIENTES (NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT DATE, ?)";
    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // --- INICIO DE LOS 10 PARÁMETROS ('?') ---

        // 1. Datos Personales (Índices 1, 2, 3)
        // ps.setString(1, cliente.getNoCliente()); <--- ¡ELIMINADA! NO ESTÁ EN EL VALUES
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido1());
        ps.setString(3, cliente.getApellido2());
        
        // 2. Domicilio (Índices 4, 5, 6)
        // **IMPORTANTE**: Aquí se asume que NO_EXTERIOR es INTEGER en Cliente.java,
        // ya que usas ps.setInt(4, ...). Si acepta letras ("10A"), debes cambiarlo a String.
        ps.setInt(4, cliente.getNo_exterior()); // NO_EXTERIOR (Si acepta int)
        ps.setString(5, cliente.getCalle());
        ps.setString(6, cliente.getColonia());
        
        // 3. Otros campos (Índices 7, 8, 9)
        ps.setString(7, cliente.getCiudad());
        ps.setString(8, cliente.getCp());
        ps.setString(9, cliente.getEstado());
        
        // 4. Sucursal (Índice 10)
        // FECHA_REGISTRO se salta porque usa CURRENT DATE
        ps.setString(10, cliente.getNoSucursal()); // NO_SUCURSAL (Último parámetro '?', índice 10)
        
        // --- FIN DE LOS 10 PARÁMETROS ---
        
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al insertar cliente: " + e.getMessage());
        return false;
    }
}


}