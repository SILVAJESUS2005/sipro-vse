package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import modelo.EvaluacionProyecto;
import modelo.Proyecto;
import modelo.Miembro;
import modelo.servicio.EvaluacionProyectoService;
import modelo.servicio.ProyectoService;
import modelo.servicio.MiembroService;

@Named
@SessionScoped
public class EvaluacionProyectoBean implements Serializable {

    private EvaluacionProyecto evaluacionSeleccionada = new EvaluacionProyecto();
    private EvaluacionProyecto evaluacionAEliminar;
    private List<EvaluacionProyecto> evaluaciones;
    private boolean modoEdicion = false;
    private Proyecto proyectoFiltro;

    @Inject
    private EvaluacionProyectoService evaluacionService;

    @Inject
    private ProyectoService proyectoService;

    @Inject
    private MiembroService miembroService;

    // Listar evaluaciones
    public List<EvaluacionProyecto> getEvaluaciones() {
        if (proyectoFiltro != null) {
            evaluaciones = evaluacionService.obtenerPorProyecto(proyectoFiltro);
        } else {
            evaluaciones = evaluacionService.obtenerTodos();
        }
        return evaluaciones;
    }

    // Nueva evaluación
    public void nuevo() {
        evaluacionSeleccionada = new EvaluacionProyecto();
        evaluacionSeleccionada.setFecha(new Date());
        evaluacionSeleccionada.setPorcentajeAvance(0.0);
        modoEdicion = false;
    }

    // Editar evaluación
    public void editar(EvaluacionProyecto evaluacion) {
        evaluacionSeleccionada = new EvaluacionProyecto();
        evaluacionSeleccionada.setID_Evaluacion(evaluacion.getID_Evaluacion());
        evaluacionSeleccionada.setProyecto(evaluacion.getProyecto());
        evaluacionSeleccionada.setFecha(evaluacion.getFecha());
        evaluacionSeleccionada.setResultado(evaluacion.getResultado());
        evaluacionSeleccionada.setComentarios(evaluacion.getComentarios());
        evaluacionSeleccionada.setHallazgos(evaluacion.getHallazgos());
        evaluacionSeleccionada.setPorcentajeAvance(evaluacion.getPorcentajeAvance());
        evaluacionSeleccionada.setRiesgos(evaluacion.getRiesgos());
        evaluacionSeleccionada.setEvaluador(evaluacion.getEvaluador());
        modoEdicion = true;
    }

    // Guardar o actualizar evaluación
    public void guardarOActualizar() {
        // Validaciones
        if (evaluacionSeleccionada.getProyecto() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un proyecto.", null));
            return;
        }

        if (evaluacionSeleccionada.getEvaluador() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un evaluador.", null));
            return;
        }

        if (evaluacionSeleccionada.getFecha() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La fecha de evaluación es obligatoria.", null));
            return;
        }

        if (evaluacionSeleccionada.getPorcentajeAvance() != null && 
            (evaluacionSeleccionada.getPorcentajeAvance() < 0 || evaluacionSeleccionada.getPorcentajeAvance() > 100)) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "El porcentaje de avance debe estar entre 0 y 100.", null));
            return;
        }

        if (modoEdicion) {
            evaluacionService.actualizar(evaluacionSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluación actualizada correctamente.", null));
        } else {
            evaluacionService.guardar(evaluacionSeleccionada);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluación guardada correctamente.", null));
        }
        evaluaciones = null; // Recarga la lista
    }

    // Prepara la evaluación para eliminar
    public void prepararEliminar(EvaluacionProyecto evaluacion) {
        evaluacionAEliminar = evaluacion;
    }

    // Elimina la evaluación seleccionada
    public void eliminar() {
        if (evaluacionAEliminar != null) {
            evaluacionService.eliminar(evaluacionAEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Evaluación eliminada correctamente.", null));
            evaluaciones = null; // Recarga la lista
            evaluacionAEliminar = null;
        }
    }

    // Getters y Setters
    public EvaluacionProyecto getEvaluacionSeleccionada() {
        return evaluacionSeleccionada;
    }

    public void setEvaluacionSeleccionada(EvaluacionProyecto evaluacionSeleccionada) {
        this.evaluacionSeleccionada = evaluacionSeleccionada;
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
    public List<Proyecto> getProyectos() {
        return proyectoService.obtenerTodos();
    }

    public List<Miembro> getMiembros() {
        return miembroService.listarTodos();
    }

    public void filtrarPorProyecto() {
        evaluaciones = null; // Forzar recarga
    }
}