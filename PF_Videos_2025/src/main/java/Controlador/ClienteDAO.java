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
            cliente.setNoCliente(rs.getInt("NO_CLIENTE"));
            cliente.setNombre(rs.getString("NOMBRE"));
            cliente.setApellido1(rs.getString("APELLIDO1"));
            cliente.setApellido2(rs.getString("APELLIDO2"));
            cliente.setNo_exterior(rs.getInt("NO_EXTERIOR")); // Mapeo de la nueva columna
            cliente.setCalle(rs.getString("CALLE"));
            cliente.setColonia(rs.getString("COLONIA"));       // Mapeo de la nueva columna
            cliente.setCiudad(rs.getString("CIUDAD"));
            cliente.setCp(rs.getString("CP"));
            cliente.setEstado(rs.getString("ESTADO")); // Asumo que el campo se llama ESTADO
            cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
            cliente.setNoSucursal(rs.getShort("NO_SUCURSAL"));
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
        ps.setShort(10, cliente.getNoSucursal()); // NO_SUCURSAL (Último parámetro '?', índice 10)
        
        // --- FIN DE LOS 10 PARÁMETROS ---
        
        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.err.println("Error al insertar cliente: " + e.getMessage());
        return false;
    }
}


//CAMBIOS CLIENTE 
public boolean modificarCliente(Cliente cliente) {
    // La lista de columnas en el SET (NOMBRE=?, APELLIDO1=?, ...) está correcta.
    String sql = "UPDATE DIANA931.CLIENTES SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, CALLE=?, CIUDAD=?, ESTADO=?, CP=?, NO_SUCURSAL=?, NO_EXTERIOR=?, COLONIA=? WHERE NO_CLIENTE=?";
    
    // **USO DE try-with-resources**
    try (Connection conn = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) { // Recursos se cierran automáticamente
        
        // 1. Parámetros SET
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getApellido1());
        ps.setString(3, cliente.getApellido2());
        ps.setString(4, cliente.getCalle());
        ps.setString(5, cliente.getCiudad());
        ps.setString(6, cliente.getEstado());
        ps.setString(7, cliente.getCp());
        ps.setShort(8, cliente.getNoSucursal());
        
        // 2. CORRECCIÓN: Usar String para NO_EXTERIOR (posicion 9)
        // Ya que asumes que es CHAR(5) en DB2, la conversión a String aquí es correcta.
        ps.setString(9, String.valueOf(cliente.getNo_exterior())); 

        ps.setString(10, cliente.getColonia());

        // 3. Condición WHERE (el ID) - Posición 11
        ps.setInt(11, cliente.getNoCliente()); 
        
        return ps.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.err.println("Error al modificar cliente: " + e.getMessage());
        return false;
    } 
    // ¡Eliminamos el bloque finally manual!
}

// ELIMINAR CLIENTE
public boolean eliminarCliente(int id) {
    // Sentencia DELETE: elimina la fila donde NO_CLIENTE coincida con el ID proporcionado
    String sql = "DELETE FROM DIANA931.CLIENTES WHERE NO_CLIENTE = ?";
    
    // **USO DE try-with-resources**
    try (Connection conn = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        // Asigna el ID del cliente al placeholder (?)
        ps.setInt(1, id);
        
        // Ejecuta el DELETE. Si devuelve un número mayor a 0, la eliminación fue exitosa.
        return ps.executeUpdate() > 0;
        
    } catch (SQLException e) {
        System.err.println("Error al eliminar cliente (DAO): " + e.getMessage());
        e.printStackTrace(); 
        return false;
    }
    // ¡Eliminamos el bloque finally manual!
}

public Cliente obtenerClientePorId(int idCliente) {
    // Consulta SQL para seleccionar un único cliente por su ID
    String sql = "SELECT NO_CLIENTE, NOMBRE, APELLIDO1, APELLIDO2, NO_EXTERIOR, CALLE, COLONIA, CIUDAD, CP, ESTADO, FECHA_REGISTRO, NO_SUCURSAL FROM DIANA931.CLIENTES WHERE NO_CLIENTE = ?";
    Cliente cliente = null; // Inicializamos a null
    
    // Usamos try-with-resources para asegurar el cierre automático de recursos
    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        // Asignamos el parámetro al placeholder (?)
        ps.setInt(1, idCliente);

        try (ResultSet rs = ps.executeQuery()) {
            // Si encuentra un registro, lo procesa
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setNoCliente(rs.getInt("NO_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido1(rs.getString("APELLIDO1"));
                cliente.setApellido2(rs.getString("APELLIDO2"));
                
                // Mapeo de la dirección
                cliente.setNo_exterior(rs.getInt("NO_EXTERIOR")); 
                cliente.setCalle(rs.getString("CALLE"));
                cliente.setColonia(rs.getString("COLONIA"));
                cliente.setCiudad(rs.getString("CIUDAD"));
                cliente.setCp(rs.getString("CP"));
                cliente.setEstado(rs.getString("ESTADO"));
                
                // Mapeo de otros datos
                cliente.setFechaRegistro(rs.getDate("FECHA_REGISTRO"));
              cliente.setNoSucursal(rs.getShort("NO_SUCURSAL"));
            }
            // Si rs.next() es false, el cliente queda como null, lo cual es correcto.
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener cliente por ID (" + idCliente + "): " + e.getMessage());
        // En una aplicación real, usarías el logger aquí
    }
    return cliente;
}

}