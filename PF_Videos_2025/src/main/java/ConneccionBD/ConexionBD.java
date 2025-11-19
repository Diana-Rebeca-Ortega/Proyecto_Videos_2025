package ConneccionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {

    private Connection conexion;
    private Statement stm; 
    private ResultSet rs;
    private static ConexionBD instancia; 

    // Constructor privado: NO debe intentar conectar aquí.
    private ConexionBD(){
        System.out.println("YEEEEI Casi son ingeniera/o INMORTAL !!!!");
    }
    
    // Método estático para obtener la instancia (patrón Singleton)
    public static ConexionBD getInstance(){
        if (instancia == null){
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    // Método para obtener la conexión: verifica y recrea si es necesario.
    public Connection getConnection() throws SQLException {
        // Verifica si la conexión NO EXISTE o si fue CERRADA.
        if (conexion == null || conexion.isClosed()) {
            try {
                // Configuración de la conexión DB2
                Class.forName("com.ibm.db2.jcc.DB2Driver");
                String URL = "jdbc:db2://localhost:25000/PFVID";
                
                // Recrear la conexión
                conexion = DriverManager.getConnection(URL, "diana931", "1819diana");
                System.out.println("Conexión DB2 establecida/restablecida exitosamente.");

            } catch (ClassNotFoundException e) {
                System.err.println("Error en el connector/driver: " + e.getMessage());
                // Lanza como SQLException para ser manejada por la capa DAO
                throw new SQLException("DB Driver no encontrado: " + e.getMessage()); 
            } catch (SQLException e) {
                System.err.println("Error al reconectar a db2: " + e.getMessage());
                throw e; 
            }
        }
        return conexion;
    }

    // ... (Mantén tus métodos ejecutarInstruccionLMD y ejecutarInstruccionSQL) ...

}