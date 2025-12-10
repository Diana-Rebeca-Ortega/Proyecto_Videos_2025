package Controlador;

import Modelo.Alquiler; // Asegúrate de tener esta clase de modelo
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ConneccionBD.ConexionBD; // Reutiliza tu clase de conexión
import Modelo.AlquilerCompleto;

public class AlquilerDAO {

    private static final String ESQUEMA_TABLA = "DIANA931.ALQUILER"; 
//CONSULTAS******************************************************************************************
    public List<Alquiler> obtenerTodosLosAlquileres() {
        List<Alquiler> lista = new ArrayList<>();
        String sql = "SELECT ID_ALQUILER, NO_CLIENTE, ID_PELICULA, ID_COPIA_PELICULA, FECHA_ALQUILER, FECHA_DEVOLUCION, ESTADO, ID_SUCURSAL, ALQUILER_DIARIO " 
        + "FROM " + ESQUEMA_TABLA;
        try (Connection con = ConexionBD.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alquiler alquiler = new Alquiler();              
                alquiler.setIdAlquiler(rs.getInt("ID_ALQUILER"));
                alquiler.setIdCliente(rs.getInt("NO_CLIENTE"));
                alquiler.setIdPelicula(rs.getInt("ID_PELICULA"));
                alquiler.setIdCopia(rs.getInt("ID_COPIA_PELICULA"));              
                alquiler.setFechaAlquiler(rs.getDate("FECHA_ALQUILER"));
                alquiler.setFechaDevolucion(rs.getDate("FECHA_DEVOLUCION")); 
                alquiler.setEstado(rs.getString("ESTADO"));
                alquiler.setIdSucursal(rs.getInt("ID_SUCURSAL"));
                alquiler.setCostoDiario(rs.getDouble("ALQUILER_DIARIO"));               
                lista.add(alquiler);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener alquileres: " + e.getMessage());
        }
        return lista;
    }
//ALTAS////Usando el procedimiento almacenado//********************************************************************************************
    public boolean insertarAlquiler(Alquiler alquiler) {
    String call = "{CALL RegistrarNuevoAlquiler(?, ?, ?,?,?)}"; 
        try (Connection conn = ConexionBD.getInstance().getConnection();
         CallableStatement cs = conn.prepareCall(call)) {        
        cs.setInt(1, alquiler.getIdCliente());        
        cs.setInt(2, alquiler.getIdPelicula());
        cs.setInt(3, alquiler.getIdCopia());
        cs.setDouble(4, alquiler.getCostoDiario());         
        java.sql.Date fechaDevolucionSQL = new java.sql.Date(alquiler.getFechaDevolucion().getTime());
        cs.setDate(5, fechaDevolucionSQL);        
        cs.execute();
        return true;        
    } catch (SQLException e) {
        System.err.println("Error al llamar al PA RegistrarNuevoAlquiler: " + e.getMessage());
        return false;
    }
}

 // VISTA ALQUILER COMPLETO//////////////////////////////////////////////
    public List<AlquilerCompleto> obtenerListadoAlquileres(int idSucursal) {
        List<AlquilerCompleto> listado = new ArrayList<>();        
        // Opcional: Filtramos por la sucursal actual si el usuario solo debe ver la suya.
        String sql = "SELECT * FROM VISTA_ALQUILERES_COMPLETO WHERE ID_SUCURSAL = ?"; 

        try (Connection con = ConexionBD.getInstance().getConnection();
             // Cambiamos a 'sql' y luego asignamos el parámetro de sucursal
             PreparedStatement ps = con.prepareStatement(sql)) { 
            
            // Asignamos el filtro de sucursal
            ps.setInt(1, idSucursal); 

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AlquilerCompleto ac = new AlquilerCompleto(
                        rs.getInt("ID_ALQUILER"),
                        rs.getString("NOMBRE_CLIENTE"), // Mayúsculas recomendadas en DB2
                        rs.getString("TITULO_PELICULA"), // Mayúsculas recomendadas en DB2
                        rs.getDate("FECHA_ALQUILER"),
                        rs.getDate("FECHA_DEVOLUCION"),
                        rs.getString("ESTADO"), // ¡CORREGIDO AQUÍ!
                        rs.getDouble("TARIFA_ALQUILER"),
                        rs.getInt("ID_SUCURSAL"),
                        rs.getInt("ID_COPIA_PELICULA")                    
                    );
                    listado.add(ac);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener listado de alquileres desde la VISTA: " + e.getMessage());
            e.printStackTrace();
        }
        return listado;
    }
public boolean registrarDevolucion(int idAlquiler, int idCopiaPelicula) throws SQLException {
    Connection con = null;
    PreparedStatement psAlquiler = null;
    PreparedStatement psCopia = null;
    boolean exito = false;
    String sqlAlquiler = "UPDATE ALQUILER SET FECHA_DEVOLUCION = CURRENT_DATE, ESTADO = 'DEVUELTO' WHERE ID_ALQUILER = ?";
    String sqlCopia = "UPDATE COPIA_PELICULA SET ESTADO = 'DISPONIBLE' WHERE ID_PELICULA = ?";
    try {
        con = ConexionBD.getInstance().getConnection();
        //Desactivamos el autocommit para que las insrucciones se ejecuten en un TODO O NADA con el commit
        con.setAutoCommit(false); // 1. INICIA la transacción

        // Ejecutar UPDATE ALQUILER
        psAlquiler = con.prepareStatement(sqlAlquiler);
        psAlquiler.setInt(1, idAlquiler);
        int filasAlquiler = psAlquiler.executeUpdate();

        // Ejecutar UPDATE COPIA_PELICULA
        psCopia = con.prepareStatement(sqlCopia);
        psCopia.setInt(1, idCopiaPelicula);
        int filasCopia = psCopia.executeUpdate();

        if (filasAlquiler > 0 && filasCopia > 0) {
            con.commit(); // 2. CONFIRMA
            exito = true;
        } else {
            con.rollback(); // 2. DESHACE
        }
    } catch (SQLException e) {
        if (con != null) {
            con.rollback(); // DESHACE en caso de error
        }
        throw e;
    } finally {
        if (psAlquiler != null) psAlquiler.close();
        if (psCopia != null) psCopia.close();
        if (con != null) {
            con.setAutoCommit(true); // RESTABLECE
            con.close(); 
        }
    }
    return exito;
}
public int calcularDiasRenta(java.util.Date fechaRentaUtil, java.util.Date fechaDevolucionUtil) {
    String sql = "SELECT DIANA931.CALCULARDIASRENTA_FECHAS(?, ?) FROM SYSIBM.SYSDUMMY1";
    int dias = 0;
    // Convertir java.util.Date a java.sql.Date para el PreparedStatement
    java.sql.Date sqlFechaRenta = new java.sql.Date(fechaRentaUtil.getTime());
    java.sql.Date sqlFechaDevolucion = new java.sql.Date(fechaDevolucionUtil.getTime());
    try (Connection con = ConexionBD.getInstance().getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setDate(1, sqlFechaRenta);       // Primer parámetro de la función (Fecha de Alquiler)
        ps.setDate(2, sqlFechaDevolucion);  // Segundo parámetro de la función (Fecha de Devolución)
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                dias = rs.getInt(1); // Obtiene el valor INT devuelto por la función
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al ejecutar la función CALCULARDIASRENTA_FECHAS: " + e.getMessage());
        e.printStackTrace();
        return 1; // Devuelve 1 día en caso de error para evitar fallos catastróficos.
    }    
    return Math.max(1, dias); 
}

}