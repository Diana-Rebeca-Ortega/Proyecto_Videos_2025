package Modelo;

public class CopiaPelicula {
    private int idCopiaPelicula; // Clave Primaria (ID_Copia_película - TINTYINT)
    private int idPelicula;      // Clave Foránea (ID_Pelicula - TINTYINT)
    private int idSucursal;      // Clave Foránea (ID_sucursal - TINTYINT)
    private String estado;       // Estado de la copia (estado - VARCHAR(45), e.g., "Disponible", "Alquilada", "Dañada")

    /**
     * Constructor por defecto.
     */
    public CopiaPelicula() {
    }

  
    public CopiaPelicula(int idCopiaPelicula, int idPelicula, int idSucursal, String estado) {
        this.idCopiaPelicula = idCopiaPelicula;
        this.idPelicula = idPelicula;
        this.idSucursal = idSucursal;
        this.estado = estado;
    }


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