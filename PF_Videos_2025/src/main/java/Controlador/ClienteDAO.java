package Controlador;

import Modelo.Cliente;
import java.sql.*; // Importa todo lo necesario
import java.util.ArrayList;
import java.util.List;
import  ConneccionBD.ConexionBD;

public class ClienteDAO {

public List<Cliente> obtenerTodosLosClientes() {
    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, CALLE, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES";
    
    try (Connection con = ConexionBD.getInstance().getConnection(); 
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setNoCliente(rs.getString("NO_CLIENTE"));
            // ⚠️ ASEGÚRATE DE QUE ESTAS LÍNEAS ESTÉN COMPLETAS ⚠️
            cliente.setNombre(rs.getString("NOMBRE"));
            cliente.setApellido1(rs.getString("APELLIDO1"));
            cliente.setApellido2(rs.getString("APELLIDO2"));
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
}