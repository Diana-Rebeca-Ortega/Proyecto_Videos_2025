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
    
    

public AlquilerCompleto(int idAlquiler, String nombreCliente, String tituloPelicula, Date fechaAlquiler, Date fechaDevolucion, String estado, double tarifaTotal) {
        this.idAlquiler = idAlquiler;
        this.nombreCliente = nombreCliente;
        this.tituloPelicula = tituloPelicula;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = estado;
        this.tarifaTotal = tarifaTotal;
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


    

