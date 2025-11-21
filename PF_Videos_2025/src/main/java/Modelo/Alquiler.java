package Modelo;

import java.sql.Date; // Usaremos java.sql.Date para mapear directamente el tipo DATE de SQL

/**
 * Clase que representa el objeto de dominio "Alquiler" (Renta de Película).
 * Mapea las columnas de la tabla Alquiler.
 *
 * @author Diana
 */
public class Alquiler {
    
    // Corresponden a los campos del diagrama ER
    private int idAlquiler;     // ID_alquiler (TINYINT / INT)
    private int idCliente;      // ID_cliente (INT)
    private int idPelicula;     // ID_pelicula (TINYINT / INT)
    private Date fechaAlquiler; // fecha_alquiler (DATE)
    private Date fechaDevolucion; // fecha_devolucion (DATE)
    private String estado;      // Estado (VARCHAR(45))

    public Alquiler() {
        // Constructor vacío
    }

    // --- Getters y Setters ---

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
    
}