package Modelo;

import java.sql.Date; 

public class Alquiler {

    private int idAlquiler;      // ID_ALQUILER (SMALLINT, PK)
    private int idCliente;       // NO_CLIENTE (INT)
    private int idPelicula;      // ID_PELICULA (SMALLINT)
    private Date fechaAlquiler;  // FECHA_ALQUILER (DATE)
    private Date fechaDevolucion; // FECHA_DEVOLUCION (DATE)
    private String estado;       // ESTADO (VARCHAR(45))
    private int idSucursal;      // ID_SUCURSAL (SMALLINT) 
    private double costoFinal;
    private int idCopia;
    public Alquiler() {
        // Constructor vacío
    }

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public int getIdCopia() {
        return idCopia;
    }

    public void setIdCopia(int idCopia) {
        this.idCopia = idCopia;
    }

    public double getCostoDiario() {
        return costoFinal;
    }

    // --- Getters y Setters ---
    public void setCostoDiario(double costoDiario) {
        this.costoFinal = costoDiario;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Date getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // Nuevo Getter para ID_SUCURSAL
    public int getIdSucursal() {
        return idSucursal;
    }

    // Nuevo Setter para ID_SUCURSAL
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
}