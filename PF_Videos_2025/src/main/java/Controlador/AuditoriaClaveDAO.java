package Controlador;

import ConneccionBD.ConexionBD;
import Modelo.AuditoriaClave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaClaveDAO {

    public List<AuditoriaClave> obtenerTodosLosRegistros() throws SQLException {
        List<AuditoriaClave> listaAuditoria = new ArrayList<>();
        
        String sql = "SELECT ID_AUDITORIA, ID_EMPLEADO, FECHA_CAMBIO, EVENTO "
                   + "FROM DIANA931.AUDITORIA_CAMBIO_CLAVE "
                   + "ORDER BY FECHA_CAMBIO DESC"; 

        Connection con = null;

        try {
            con = ConexionBD.getInstance().getConnection();
            
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    AuditoriaClave ac = mapearResultado(rs);
                    listaAuditoria.add(ac);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener registros de auditoría de clave: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        } 
        return listaAuditoria;
    }

    private AuditoriaClave mapearResultado(ResultSet rs) throws SQLException {
        AuditoriaClave ac = new AuditoriaClave();
        ac.setIdAuditoria(rs.getInt("ID_AUDITORIA"));
        ac.setIdEmpleado(rs.getShort("ID_EMPLEADO")); 
        ac.setFechaCambio(rs.getTimestamp("FECHA_CAMBIO")); 
        ac.setEvento(rs.getString("EVENTO"));
        return ac;
    }
}