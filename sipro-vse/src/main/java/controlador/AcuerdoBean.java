package controlador;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import modelo.Acuerdo;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AcuerdoBean implements Serializable {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    private List<Acuerdo> acuerdos;
    private Acuerdo acuerdo = new Acuerdo();

    @PostConstruct
    public void init() {
        cargarAcuerdos();
    }

    public void cargarAcuerdos() {
        acuerdos = em.createQuery("SELECT a FROM Acuerdo a ORDER BY a.fechaFirma DESC", Acuerdo.class).getResultList();
    }

    @Transactional
    public void guardar() {
        try {
            if (acuerdo.getID_Acuerdo() == 0) {
                em.persist(acuerdo);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo creado", null));
            } else {
                em.merge(acuerdo);
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo actualizado", null));
            }
            acuerdo = new Acuerdo();
            cargarAcuerdos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
        }
    }

    public void nuevo() {
        acuerdo = new Acuerdo();
    }

    public void editar(Acuerdo a) {
        // Opcional: puedes clonar si no quieres editar directamente en la tabla
        this.acuerdo = a;
    }

    @Transactional
    public void eliminar(Acuerdo a) {
        try {
            Acuerdo toDelete = em.merge(a);
            em.remove(toDelete);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo eliminado", null));
            cargarAcuerdos();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar", e.getMessage()));
        }
    }

    // Getters y Setters
    public List<Acuerdo> getAcuerdos() { return acuerdos; }
    public Acuerdo getAcuerdo() { return acuerdo; }
    public void setAcuerdo(Acuerdo acuerdo) { this.acuerdo = acuerdo; }
}