package Modelo;

import java.sql.Timestamp;

public class AuditoriaClave {
    
    private int idAuditoria;
    private short idEmpleado; 
    private Timestamp fechaCambio; 
    private String evento; 
    
    public AuditoriaClave() {
    }
    
    // Getters y Setters
    
    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public short getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(short idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
}