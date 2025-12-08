package Modelo;
import java.sql.Date;
public class Cliente {
   
    private int noCliente; 
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int  No_exterior;
    private String calle;
    private String ciudad;
     private String colonia;
    private String estado;
    private String cp; 
    private java.sql.Date fechaRegistro; 
    private short noSucursal;

    public Cliente() {
    }

    public int getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(int noCliente) {
        this.noCliente = noCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public short getNoSucursal() {
        return noSucursal;
    }

    public void setNoSucursal(short noSucursal) {
        this.noSucursal = noSucursal;
    }
    public int getNo_exterior() {
        return No_exterior;
    }

    public void setNo_exterior(int No_exterior) {
        this.No_exterior = No_exterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
    
    
    
}
