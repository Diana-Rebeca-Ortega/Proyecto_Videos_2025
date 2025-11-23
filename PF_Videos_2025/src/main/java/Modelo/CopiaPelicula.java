package Modelo;

/**
 * Representa una copia física (un ejemplar) de una película en una sucursal.
 * Los atributos coinciden con las columnas en la tabla COPIA_PELICULA.
 *
 * @author [Tu Nombre]
 */
public class CopiaPelicula {

    // Atributos que corresponden a las columnas de la tabla COPIA_PELICULA
    private int idCopiaPelicula; // Clave Primaria (ID_Copia_película - TINTYINT)
    private int idPelicula;      // Clave Foránea (ID_Pelicula - TINTYINT)
    private int idSucursal;      // Clave Foránea (ID_sucursal - TINTYINT)
    private String estado;       // Estado de la copia (estado - VARCHAR(45), e.g., "Disponible", "Alquilada", "Dañada")

    /**
     * Constructor por defecto.
     */
    public CopiaPelicula() {
    }

    /**
     * Constructor con todos los campos.
     * @param idCopiaPelicula ID de la copia.
     * @param idPelicula ID de la película asociada (catálogo).
     * @param idSucursal ID de la sucursal donde se encuentra.
     * @param estado Estado actual de la copia.
     */
    public CopiaPelicula(int idCopiaPelicula, int idPelicula, int idSucursal, String estado) {
        this.idCopiaPelicula = idCopiaPelicula;
        this.idPelicula = idPelicula;
        this.idSucursal = idSucursal;
        this.estado = estado;
    }

    // ----------------------------------------------------
    // Getters y Setters
    // ----------------------------------------------------

    public int getIdCopiaPelicula() {
        return idCopiaPelicula;
    }

    public void setIdCopiaPelicula(int idCopiaPelicula) {
        this.idCopiaPelicula = idCopiaPelicula;
    }

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}