package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import modelo.Proyecto;
import modelo.Miembro;
import modelo.enums.EstadoProyecto;
import modelo.servicio.ProyectoService;
import modelo.servicio.MiembroService;

@Named
@SessionScoped
public class ProyectoBean implements Serializable {

    private Proyecto proyectoSeleccionado = new Proyecto();
    private Proyecto proyectoAEliminar;
    private List<Proyecto> proyectos;
    private boolean modoEdicion = false;

    @Inject
    private ProyectoService proyectoService;

    @Inject
    private MiembroService miembroService;

    // Listar proyectos
    public List<Proyecto> getProyectos() {
        proyectos = proyectoService.obtenerTodos();
        return proyectos;
    }

    // Nuevo proyecto
    public void nuevo() {
        proyectoSeleccionado = new Proyecto();
        proyectoSeleccionado.setEstado(EstadoProyecto.BORRADOR);
        modoEdicion = false;
    }

    // Editar proyecto
    public void editar(Proyecto proyecto) {
        proyectoSeleccionado = new Proyecto();
        proyectoSeleccionado.setID_Proyecto(proyecto.getID_Proyecto());
        proyectoSeleccionado.setNombre(proyecto.getNombre());
        proyectoSeleccionado.setCliente(proyecto.getCliente());
        proyectoSeleccionado.setDescripcion(proyecto.getDescripcion());
        proyectoSeleccionado.setFechaInicio(proyecto.getFechaInicio());
        proyectoSeleccionado.setFechaFin(proyecto.getFechaFin());
        proyectoSeleccionado.setEstado(proyecto.getEstado());
        proyectoSeleccionado.setResponsable(proyecto.getResponsable());
        modoEdicion = true;
    }

    // Guardar o actualizar proyecto
    public void guardarOActualizar() {
        // Validar nombre vacío
        if (proyectoSeleccionado.getNombre() == null || proyectoSeleccionado.getNombre().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre del proyecto no puede estar vacío.", null));
            return;
        }

        // Validar cliente vacío
        if (proyectoSeleccionado.getCliente() == null || proyectoSeleccionado.getCliente().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El cliente del proyecto no puede estar vacío.", null));
            return;
        }

        // Validar fechas
        if (proyectoSeleccionado.getFechaInicio() != null && proyectoSeleccionado.getFechaFin() != null &&
            proyectoSeleccionado.getFechaInicio().after(proyectoSeleccionado.getFechaFin())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha de inicio no puede ser posterior a la fecha de fin.", null));
            return;
        }

        // Validar duplicado
        for (Proyecto p : getProyectos()) {
            if (p.getNombre().equalsIgnoreCase(proyectoSeleccionado.getNombre())
                    && (!modoEdicion || p.getID_Proyecto() != proyectoSeleccionado.getID_Proyecto())) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe un proyecto con ese nombre.", null));
                return;
            }
        }

        if (modoEdicion) {
            proyectoService.actualizar(proyectoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto actualizado correctamente.", null));
        } else {
            proyectoService.guardar(proyectoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto guardado correctamente.", null));
        }
        proyectos = null; // Recarga la lista
    }

    // Prepara el proyecto para eliminar
    public void prepararEliminar(Proyecto proyecto) {
        proyectoAEliminar = proyecto;
    }

    // Elimina el proyecto seleccionado
    public void eliminar() {
        if (proyectoAEliminar != null) {
            proyectoService.eliminar(proyectoAEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Proyecto eliminado correctamente.", null));
            proyectos = null; // Recarga la lista
            proyectoAEliminar = null;
        }
    }

    // Getters y Setters
    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }

    // Métodos auxiliares
    public EstadoProyecto[] getEstadosProyecto() {
        return EstadoProyecto.values();
    }

    public List<Miembro> getMiembros() {
        return miembroService.listarTodos();
    }
}
