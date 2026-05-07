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
    
    // Atributo estático para el patrón Singleton
    private static ConexionBD instancia;

    // Constructor privado para evitar que se creen instancias fuera de esta clase
    private ConexionBD() {
        System.out.println("YEEEEI Casi son ingeniera/o INMORTAL !!!!");
    }

    // 1. MÉTODO SINGLETON: Obtiene la única instancia de esta clase
    public static ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    // 2. MÉTODO DE CONEXIÓN: Configurado para SQL Server
    public Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String URL = "jdbc:sqlserver://localhost:1433;databaseName=bd_videos_proyecto_final;encrypt=true;trustServerCertificate=true;";

                conexion = DriverManager.getConnection(URL, "diana", "diana");
                
                System.out.println("Conexión a SQL Server establecida exitosamente.");

            } catch (ClassNotFoundException e) {
                System.err.println("Error: Driver de SQL Server no encontrado. " + e.getMessage());
                throw new SQLException("Driver no encontrado: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error al conectar a SQL Server: " + e.getMessage());
                throw e;
            }
        }
        return conexion;
    }
}