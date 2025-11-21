package Modelo;

/**
 * Representa una película en el catálogo.
 * Los atributos coinciden con las columnas en la tabla PELICULA.
 *
 * @author Diana
 */
public class Pelicula {

    // Atributos que corresponden a las columnas de la tabla PELICULA
    private int idPelicula; // Clave Primaria
    private String titulo;
    private String categoria;
    private String director;
    private double precioAlquiler; // Usamos double para valores monetarios
    private double costeAdquisicion; // Usamos double para valores monetarios
    private int stockTotal;

    /**
     * Constructor por defecto.
     */
    public Pelicula() {
    }

    // ----------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(double precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public double getCosteAdquisicion() {
        return costeAdquisicion;
    }

    public void setCosteAdquisicion(double costeAdquisicion) {
        this.costeAdquisicion = costeAdquisicion;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }
}