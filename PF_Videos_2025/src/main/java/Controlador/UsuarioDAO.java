package Controlador;

import Modelo.Usuario;
import ConneccionBD.ConexionBD; // Para la conexión a DB2
import java.sql.Connection;       // Específico para la conexión
import java.sql.PreparedStatement; // Específico para la consulta
import java.sql.ResultSet;         // Específico para los resultados
import java.sql.SQLException;      // Específico para el manejo de errores JDBC

public class UsuarioDAO {

    private Connection conexion;  
   
    public Usuario autenticar(String nombreUsuario, String contrasena) {
    String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO, CONTRASENA_HASH, TIPO_USUARIO FROM USUARIO WHERE NOMBRE_USUARIO = ?";
    Usuario usuarioAutenticado = null;
    
    Connection con = null; // ⬅️ Declaración
    
    try {
        // 1. Obtener la conexión del Singleton
        con = ConexionBD.getInstance().getConnection();
        
        // 2. try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nombreUsuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("CONTRASENA_HASH");
                    
                    if (contrasena.equals(hashGuardado)) {
                        usuarioAutenticado = new Usuario(
                            rs.getInt("ID_USUARIO"),
                            rs.getString("NOMBRE_USUARIO"),
                            rs.getString("TIPO_USUARIO")
                        );
                    }
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error de autenticación en la consulta SQL: " + e.getMessage());
        e.printStackTrace();
    }
    return usuarioAutenticado;
}
 public boolean cambiarClave(int idUsuario, String nuevaClave) {
    String sql = "UPDATE USUARIO SET CONTRASENA_HASH = ? WHERE ID_USUARIO = ?";
    
    Connection conn = null; // ⬅️ Declaración
    
    try {
        // 1. Obtener la conexión del Singleton
        conn = ConexionBD.getInstance().getConnection();
        
        // 2. try-with-resources solo para PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            // 3. Establecer parámetros
            ps.setString(1, nuevaClave); // El nuevo hash de la clave
            ps.setInt(2, idUsuario);    // El ID del usuario a modificar

            // 4. Ejecutar y verificar filas afectadas
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        }

    } catch (SQLException e) { // Cambiamos a capturar específicamente SQLException
        System.err.println("Error al cambiar la clave: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
}
}