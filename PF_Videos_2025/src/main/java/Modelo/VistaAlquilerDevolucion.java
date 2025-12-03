package Modelo;

import java.sql.Date;

public class VistaAlquilerDevolucion {
    
    private int idAlquiler;
    private int noCliente;
    private String nombreCliente;
    private String tituloPelicula;
    private int idPelicula;
    private Date fechaAlquiler;
    private Date fechaDevolucion; 
    private String estadoActual;  
    private String estadoEntregaUI; 
    private int idCopiaPelicula;  

    
    public VistaAlquilerDevolucion() {
    }

    public int getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(int noCliente) {
        this.noCliente = noCliente;
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

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

   

    public int getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(int idAlquiler) { this.idAlquiler = idAlquiler; }

    public Date getFechaAlquiler() { return fechaAlquiler; }
    public void setFechaAlquiler(Date fechaAlquiler) { this.fechaAlquiler = fechaAlquiler; }
    
    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getEstadoEntregaUI() { return estadoEntregaUI; }
    public void setEstadoEntregaUI(String estadoEntregaUI) { this.estadoEntregaUI = estadoEntregaUI; }
    
    public String getEstadoActual() { return estadoActual; }
    public void setEstadoActual(String estadoActual) { this.estadoActual = estadoActual; }

    public int getIdCopiaPelicula() { return idCopiaPelicula; }
    public void setIdCopiaPelicula(int idCopiaPelicula) { this.idCopiaPelicula = idCopiaPelicula; }
  
}