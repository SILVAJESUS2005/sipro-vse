package modelo;
import jakarta.persistence.*;
import modelo.enums.Rol;

@Entity
public class Miembro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Miembro;

    private String nombre;
    private String correo;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    // Getters y Setters

    public int getID_Miembro() {
        return ID_Miembro;
    }

    public void setID_Miembro(int ID_Miembro) {
        this.ID_Miembro = ID_Miembro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
}
