package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import modelo.Acuerdo;
import modelo.Proyecto;
import modelo.servicio.AcuerdoService;

@Named
@SessionScoped
public class AcuerdoBean implements Serializable {

    private Acuerdo acuerdo = new Acuerdo();

    @Inject
    private modelo.servicio.AcuerdoService acuerdoService;

    // Si tienes una lista de proyectos para seleccionar en el formulario
    private Proyecto proyectoSeleccionado;

    public String guardarAcuerdo() {
        // 1. Proyecto seleccionado
        if (acuerdo.getProyecto() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes seleccionar un proyecto.", null));
            return null;
        }

        // 2. Nombre del acuerdo no vacío y único
        if (acuerdo.getNombreProyecto() == null || acuerdo.getNombreProyecto().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre del acuerdo no puede estar vacío.", null));
            return null;
        }
        // Verifica unicidad
        if (acuerdoService.existeNombre(acuerdo.getNombreProyecto())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un acuerdo con ese nombre.", null));
            return null;
        }

        // 3. Fecha de firma válida
        if (acuerdo.getFechaFirma() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes ingresar la fecha de firma.", null));
            return null;
        }
        if (acuerdo.getFechaFirma().after(new Date())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha de firma no puede ser futura.", null));
            return null;
        }

        // 4. Cliente no vacío
        if (acuerdo.getCliente() == null || acuerdo.getCliente().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cliente no puede estar vacío.", null));
            return null;
        }

        // Guardar
        acuerdoService.guardar(acuerdo);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo guardado exitosamente.", null));
        // Redirige o limpia el formulario según tu flujo
        acuerdo = new Acuerdo();
        return "lista-acuerdos.xhtml?faces-redirect=true";
    }

    // Getters y setters
    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
        if (this.acuerdo != null) {
            this.acuerdo.setProyecto(proyectoSeleccionado);
        }
    }
}
