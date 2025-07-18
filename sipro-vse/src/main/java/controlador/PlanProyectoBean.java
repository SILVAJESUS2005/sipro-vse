package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import modelo.PlanProyecto;
import modelo.Proyecto;
import modelo.enums.EstadoPlan;
import modelo.servicio.PlanProyectoService;
import modelo.servicio.ProyectoService;

@Named
@SessionScoped
public class PlanProyectoBean implements Serializable {

    private PlanProyecto planSeleccionado = new PlanProyecto();
    private PlanProyecto planAEliminar;
    private List<PlanProyecto> planes;
    private boolean modoEdicion = false;
    private Proyecto proyectoFiltro;

    @Inject
    private PlanProyectoService planProyectoService;

    @Inject
    private ProyectoService proyectoService;

    // Listar planes
    public List<PlanProyecto> getPlanes() {
        if (proyectoFiltro != null) {
            planes = planProyectoService.obtenerPorProyecto(proyectoFiltro);
        } else {
            planes = planProyectoService.obtenerTodos();
        }
        return planes;
    }

    // Nuevo plan
    public void nuevo() {
        planSeleccionado = new PlanProyecto();
        planSeleccionado.setEstado(EstadoPlan.Borrador);
        planSeleccionado.setFechaAprobacion(new Date());
        modoEdicion = false;
    }

    // Editar plan
    public void editar(PlanProyecto plan) {
        planSeleccionado = new PlanProyecto();
        planSeleccionado.setID_Plan(plan.getID_Plan());
        planSeleccionado.setProyecto(plan.getProyecto());
        planSeleccionado.setDescripcionSistema(plan.getDescripcionSistema());
        planSeleccionado.setAlcance(plan.getAlcance());
        planSeleccionado.setObjetivos(plan.getObjetivos());
        planSeleccionado.setCicloVida(plan.getCicloVida());
        planSeleccionado.setEstrategiaGestionVersiones(plan.getEstrategiaGestionVersiones());
        planSeleccionado.setComposicionEquipo(plan.getComposicionEquipo());
        planSeleccionado.setHitos(plan.getHitos());
        planSeleccionado.setRiesgos(plan.getRiesgos());
        planSeleccionado.setCronograma(plan.getCronograma());
        planSeleccionado.setResponsables(plan.getResponsables());
        planSeleccionado.setRecursos(plan.getRecursos());
        planSeleccionado.setFechaAprobacion(plan.getFechaAprobacion());
        planSeleccionado.setEstado(plan.getEstado());
        modoEdicion = true;
    }

    // Guardar o actualizar plan
    public void guardarOActualizar() {
        // Validaciones
        if (planSeleccionado.getProyecto() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un proyecto.", null));
            return;
        }

        if (planSeleccionado.getObjetivos() == null || planSeleccionado.getObjetivos().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los objetivos son obligatorios.", null));
            return;
        }

        if (modoEdicion) {
            planProyectoService.actualizar(planSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Plan de proyecto actualizado correctamente.", null));
        } else {
            planProyectoService.guardar(planSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Plan de proyecto guardado correctamente.", null));
        }
        planes = null; // Recarga la lista
    }

    // Prepara el plan para eliminar
    public void prepararEliminar(PlanProyecto plan) {
        planAEliminar = plan;
    }

    // Elimina el plan seleccionado
    public void eliminar() {
        if (planAEliminar != null) {
            planProyectoService.eliminar(planAEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Plan de proyecto eliminado correctamente.", null));
            planes = null; // Recarga la lista
            planAEliminar = null;
        }
    }

    // Getters y Setters
    public PlanProyecto getPlanSeleccionado() {
        return planSeleccionado;
    }

    public void setPlanSeleccionado(PlanProyecto planSeleccionado) {
        this.planSeleccionado = planSeleccionado;
    }

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }

    public Proyecto getProyectoFiltro() {
        return proyectoFiltro;
    }

    public void setProyectoFiltro(Proyecto proyectoFiltro) {
        this.proyectoFiltro = proyectoFiltro;
    }

    // Métodos auxiliares
    public EstadoPlan[] getEstadosPlan() {
        return EstadoPlan.values();
    }

    public List<Proyecto> getProyectos() {
        return proyectoService.obtenerTodos();
    }

    public void filtrarPorProyecto() {
        planes = null; // Forzar recarga
    }
}