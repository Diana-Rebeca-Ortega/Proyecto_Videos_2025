package Modelo;

import java.sql.Date;

public class AlquilerCompleto {
    private int idAlquiler;
    private String nombreCliente;
    private String tituloPelicula;
    private Date fechaAlquiler;
    private Date fechaDevolucion;
    private String estado;
    private double tarifaTotal;
    private int idCopiaPelicula;
private int idSucursal;

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    public int getIdCopiaPelicula() {
        return idCopiaPelicula;
    }

    public void setIdCopiaPelicula(int idCopiaPelicula) {
        this.idCopiaPelicula = idCopiaPelicula;
    }

    public AlquilerCompleto(int idAlquiler, String nombreCliente, String tituloPelicula, Date fechaAlquiler, Date fechaDevolucion, String estado, double tarifaTotal, int idCopiaPelicula, int idSucursal) {
        this.idAlquiler = idAlquiler;
        this.nombreCliente = nombreCliente;
        this.tituloPelicula = tituloPelicula;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.tarifaTotal = tarifaTotal;
        this.idCopiaPelicula = idCopiaPelicula;
        this.idSucursal = idSucursal;
    }

  
    

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTituloPelicula() {
        return tituloPelicula;
    }

    public void setTituloPelicula(String tituloPelicula) {
        this.tituloPelicula = tituloPelicula;
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

    public double getTarifaTotal() {
        return tarifaTotal;
    }

    public void setTarifaTotal(double tarifaTotal) {
        this.tarifaTotal = tarifaTotal;
    }

  
}


    

