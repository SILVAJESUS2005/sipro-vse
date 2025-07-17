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
import modelo.Proyecto;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AcuerdoBean implements Serializable {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    private List<Acuerdo> acuerdos;
    private List<Proyecto> proyectosDisponibles;
    private Acuerdo acuerdoSeleccionado;
    private Acuerdo acuerdoAEliminar;
    private boolean modoEdicion = false;

    @PostConstruct
    public void init() {
        cargarAcuerdos();
        proyectosDisponibles = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class).getResultList();
    }

    public void cargarAcuerdos() {
        acuerdos = em.createQuery("SELECT a FROM Acuerdo a ORDER BY a.fechaFirma DESC", Acuerdo.class).getResultList();
    }

    public void nuevo() {
        acuerdoSeleccionado = new Acuerdo();
        modoEdicion = false;
    }

    public void editar(Acuerdo a) {
        acuerdoSeleccionado = a;
        modoEdicion = true;
    }

    @Transactional
    public void guardar() {
        try {
            em.persist(acuerdoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo creado", null));
            cargarAcuerdos();
            acuerdoSeleccionado = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al guardar", e.getMessage()));
        }
    }

    @Transactional
    public void actualizar() {
        try {
            em.merge(acuerdoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo actualizado", null));
            cargarAcuerdos();
            acuerdoSeleccionado = null;
            modoEdicion = false;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar", e.getMessage()));
        }
    }

    public void prepararEliminar(Acuerdo a) {
        acuerdoAEliminar = a;
    }

    @Transactional
    public void eliminar() {
        try {
            Acuerdo toDelete = em.merge(acuerdoAEliminar);
            em.remove(toDelete);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo eliminado", null));
            cargarAcuerdos();
            acuerdoAEliminar = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo eliminar", e.getMessage()));
        }
    }

    public void guardarOActualizar() {
        if (modoEdicion) {
            actualizar();
        } else {
            guardar();
        }
    }

    // Getters y Setters
    public List<Acuerdo> getAcuerdos() {
        return acuerdos;
    }

    public Acuerdo getAcuerdoSeleccionado() {
        return acuerdoSeleccionado;
    }

    public void setAcuerdoSeleccionado(Acuerdo acuerdoSeleccionado) {
        this.acuerdoSeleccionado = acuerdoSeleccionado;
    }

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }

    public Acuerdo getAcuerdoAEliminar() {
        return acuerdoAEliminar;
    }

    public void setAcuerdoAEliminar(Acuerdo acuerdoAEliminar) {
        this.acuerdoAEliminar = acuerdoAEliminar;
    }

    public List<Proyecto> getProyectosDisponibles() {
        return proyectosDisponibles;
    }
}
