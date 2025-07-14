package controlador;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import modelo.Miembro;
import modelo.enums.Rol;
import java.io.Serializable;

@Named
@RequestScoped
public class RegistroBean implements Serializable {

    private Miembro miembro = new Miembro();

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    @Transactional
    public String registrar() {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(m) FROM Miembro m WHERE m.correo = :correo", Long.class);
            query.setParameter("correo", miembro.getCorreo());
            Long count = query.getSingleResult();

            if (count > 0) {
                FacesContext.getCurrentInstance().addMessage("registroForm:correo",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Este correo ya está registrado.", "Por favor intenta con otro."));
                return null;
            }

            em.persist(miembro);
            miembro = new Miembro(); // Limpiar formulario

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro exitoso", "Ahora puedes iniciar sesión."));
            return "/login?faces-redirect=true";
        } catch (Exception e) {
            String detalle = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar.", detalle));
            e.printStackTrace();
            return null;
        }
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Rol[] getRoles() {
        return Rol.values();
    }
}
