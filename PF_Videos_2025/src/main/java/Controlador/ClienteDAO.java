package Controlador;

import Modelo.Cliente;
import java.sql.*; // Importa todo lo necesario
import java.util.ArrayList;
import java.util.List;
import  ConneccionBD.ConexionBD;

public class ClienteDAO {
    
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> lista = new ArrayList<>();
        // Query para obtener todos los campos. Asegúrate de incluir el esquema (DIANA)
        String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, CALLE, CIUDAD, CP, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES"; 
        
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.getInstance().getConnection(); // Obtiene la conexión con DB2
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNoCliente(rs.getString("NO_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido1(rs.getString("APELLIDO1"));
                cliente.setApellido2(rs.getString("APELLIDO2"));
                cliente.setCalle(rs.getString("CALLE"));
                cliente.setCiudad(rs.getString("CIUDAD"));
                cliente.setCp(rs.getString("CP"));
                // Usa getDate() para campos DATE de la BD
                cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO")); 
                cliente.setNoSucursal(rs.getString("NO_SUCURSAL"));
                
                lista.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        } finally {
            // Cierra los recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
               // ConectionBD.closeConnection(con); // Cierra la conexión
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}