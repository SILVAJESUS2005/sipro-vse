package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import modelo.Acuerdo;
import modelo.Proyecto;
import modelo.servicio.AcuerdoService;
import modelo.servicio.ProyectoService;

@Named
@SessionScoped
public class AcuerdoBean implements Serializable {

    private Acuerdo acuerdoSeleccionado;
    private Acuerdo acuerdoAEliminar;
    private List<Acuerdo> acuerdos;
    private List<Proyecto> proyectosDisponibles;
    private Proyecto proyectoSeleccionado;
    private boolean modoEdicion = false;

    @Inject
    private AcuerdoService acuerdoService;

    @Inject
    private ProyectoService proyectoService;

    // Listar acuerdos
    public List<Acuerdo> getAcuerdos() {
        acuerdos = acuerdoService.obtenerTodos();
        return acuerdos;
    }

    // Listar proyectos para el selectOneMenu
    public List<Proyecto> getProyectosDisponibles() {
        proyectosDisponibles = proyectoService.obtenerTodos();
        return proyectosDisponibles;
    }

    public boolean isModoEdicion() { return modoEdicion; }
    public Acuerdo getAcuerdoSeleccionado() { return acuerdoSeleccionado; }
    public void setAcuerdoSeleccionado(Acuerdo acuerdoSeleccionado) { this.acuerdoSeleccionado = acuerdoSeleccionado; }

    // Buscar proyecto por ID para el converter
    public Proyecto buscarProyectoPorId(int id) {
        for (Proyecto p : getProyectosDisponibles()) {
            if (p.getID_Proyecto() == id) {
                return p;
            }
        }
        return null;
    }

    // Nuevo acuerdo
    public void nuevo() {
        acuerdoSeleccionado = new Acuerdo();
        modoEdicion = false;
    }

    // Editar acuerdo
    public void editar(Acuerdo acuerdo) {
        acuerdoSeleccionado = new Acuerdo();
        acuerdoSeleccionado.setIdAcuerdo(acuerdo.getIdAcuerdo());
        acuerdoSeleccionado.setNombreProyecto(acuerdo.getNombreProyecto());
        acuerdoSeleccionado.setFechaFirma(acuerdo.getFechaFirma());
        acuerdoSeleccionado.setCliente(acuerdo.getCliente());
        acuerdoSeleccionado.setEntregablesContratados(acuerdo.getEntregablesContratados());
        acuerdoSeleccionado.setCondicionesGenerales(acuerdo.getCondicionesGenerales());
        acuerdoSeleccionado.setProyecto(acuerdo.getProyecto());
        modoEdicion = true;
    }

    // Prepara el acuerdo para eliminar
    public void prepararEliminar(Acuerdo acuerdo) {
        acuerdoAEliminar = acuerdo;
    }

    // Elimina el acuerdo seleccionado
    public void eliminar() {
        if (acuerdoAEliminar != null) {
            acuerdoService.eliminar(acuerdoAEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo eliminado.", null));
            acuerdos = null; // Recarga lista
            acuerdoAEliminar = null;
        }
    }

    // Guardar o actualizar acuerdo con validaciones y mensajes
    public void guardarOActualizar() {
        boolean error = false;
        if (acuerdoSeleccionado.getProyecto() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes seleccionar un proyecto.", null));
            error = true;
        }
        if (acuerdoSeleccionado.getNombreProyecto() == null ||
            acuerdoSeleccionado.getNombreProyecto().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre no puede estar vacío.", null));
            error = true;
        }
        if (acuerdoSeleccionado.getFechaFirma() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debes ingresar la fecha de firma.", null));
            error = true;
        }
        if (acuerdoSeleccionado.getFechaFirma() != null && acuerdoSeleccionado.getFechaFirma().after(new Date())) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha no puede ser futura.", null));
            error = true;
        }
        if (acuerdoSeleccionado.getCliente() == null || acuerdoSeleccionado.getCliente().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cliente no puede estar vacío.", null));
            error = true;
        }
        // Puedes agregar más validaciones aquí si lo deseas

        if (error) {
            FacesContext.getCurrentInstance().validationFailed();
            return;
        }

        if (modoEdicion) {
            acuerdoService.actualizar(acuerdoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo actualizado.", null));
        } else {
            acuerdoService.guardar(acuerdoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Acuerdo guardado.", null));
        }
        acuerdos = null; // Recarga la lista
    }

    public Proyecto getProyectoSeleccionado() { return proyectoSeleccionado; }
    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
        if (this.acuerdoSeleccionado != null)
            this.acuerdoSeleccionado.setProyecto(proyectoSeleccionado);
    }
}