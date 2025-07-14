package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import modelo.Miembro;
import modelo.enums.Rol;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String correo;
    private String contrasena;
    private Miembro usuario;

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public String login() {
        if (correo == null || correo.isBlank() || contrasena == null || contrasena.isBlank()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incompletos", "Ingrese correo y contraseña."));
            return null;
        }
        try {
            TypedQuery<Miembro> query = em.createQuery(
                    "SELECT m FROM Miembro m WHERE m.correo = :correo AND m.contrasena = :contrasena", Miembro.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            usuario = query.getSingleResult();
            return redirigirSegunRol();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso denegado", "Credenciales incorrectas."));
            return null;
        }
    }

    private String redirigirSegunRol() {
        if (usuario == null || usuario.getRol() == null) {
            return "/login?faces-redirect=true";
        }
        return switch (usuario.getRol()) {
            case ADMIN ->
                "/admin/dashboard?faces-redirect=true";
            case PJM ->
                "/pjm/inicio?faces-redirect=true";
            case WT, DEV, IVV ->
                "/equipo/actividades?faces-redirect=true";
            case INTERESADO, STK ->
                "/interesado/inicio?faces-redirect=true";
            default ->
                "/login?faces-redirect=true";
        };
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    // Getters y Setters
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

    public Miembro getUsuario() {
        return usuario;
    }

    public boolean isAutenticado() {
        return usuario != null;
    }
}
