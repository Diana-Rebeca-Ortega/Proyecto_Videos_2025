package Controlador;

import Modelo.Usuario;
import ConneccionBD.ConexionBD; // Para la conexión a DB2
import java.sql.Connection;       // Específico para la conexión
import java.sql.PreparedStatement; // Específico para la consulta
import java.sql.ResultSet;         // Específico para los resultados
import java.sql.SQLException;      // Específico para el manejo de errores JDBC

public class UsuarioDAO {

    private Connection conexion;
    
    // El constructor usa try-catch para manejar el error de conexión internamente
    public UsuarioDAO() {
        try {
            // Usa el patrón Singleton para obtener la única instancia de la conexión
            this.conexion = ConexionBD.getInstance().getConnection();
        } catch (SQLException e) {
            System.err.println("FATAL: No se pudo obtener la conexión a la base de datos.");
            e.printStackTrace();
            this.conexion = null; // Asegura que la variable esté en un estado conocido
        }
    }

    public Usuario autenticar(String nombreUsuario, String contrasena) {
        
        // Si la conexión falló en el constructor, regresamos null inmediatamente.
        if (this.conexion == null) {
            System.err.println("La conexión es nula, no se puede autenticar.");
            return null;
        }
        
        String sql = "SELECT ID_USUARIO, NOMBRE_USUARIO, CONTRASENA_HASH, TIPO_USUARIO " +
                     "FROM USUARIO WHERE NOMBRE_USUARIO = ?";
        
        Usuario usuarioAutenticado = null; // Inicializamos el objeto a devolver

        // USO DE TRY-WITH-RESOURCES: 
        // PreparedStatement y ResultSet se cerrarán automáticamente.
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setString(1, nombreUsuario);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashGuardado = rs.getString("CONTRASENA_HASH");
                    
                    // Lógica de verificación: Usar un método de hashing es lo ideal.
                    if (contrasena.equals(hashGuardado)) { 
                        usuarioAutenticado = new Usuario(
                            rs.getInt("ID_USUARIO"),
                            rs.getString("NOMBRE_USUARIO"),
                            rs.getString("TIPO_USUARIO")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error de autenticación en la consulta SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarioAutenticado;
    }
}