package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import modelo.Miembro;
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
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incompletos", "Ingresa correo y contraseña."));
            return "/acuerdo?faces-redirect=true";
        }
        try {
            TypedQuery<Miembro> query = em.createQuery(
                "SELECT m FROM Miembro m WHERE m.correo = :correo AND m.contrasena = :contrasena", Miembro.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            usuario = query.getSingleResult();

            // Autenticación exitosa: redirige a la página de acuerdos
            return "/acuerdo?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso denegado", "Credenciales incorrectas."));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }

    // Getters y Setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public Miembro getUsuario() { return usuario; }
    public boolean isAutenticado() { return usuario != null; }
}