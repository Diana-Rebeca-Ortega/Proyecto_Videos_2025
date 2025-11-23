package Modelo;

public class Usuario {
    private int idUsuario;
    private String nombreUsuario;
    private String tipoUsuario;
    // La contraseña no se almacena en el objeto modelo, solo se usa para la autenticación.

    public Usuario(int idUsuario, String nombreUsuario, String tipoUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters y Setters
    public int getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getTipoUsuario() { return tipoUsuario; }
    
    // ... otros métodos (opcional)
}