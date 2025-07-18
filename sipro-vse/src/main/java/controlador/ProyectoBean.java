package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import modelo.Proyecto;
import modelo.servicio.ProyectoService;

@Named
@SessionScoped
public class ProyectoBean implements Serializable {

    private Proyecto proyectoSeleccionado = new Proyecto();
    private Proyecto proyectoAEliminar;
    private List<Proyecto> proyectos;
    private boolean modoEdicion = false;

    @Inject
    private ProyectoService proyectoService;

    // Listar proyectos
    public List<Proyecto> getProyectos() {
        if (proyectos == null) {
            proyectos = proyectoService.obtenerTodos();
        }
        return proyectos;
    }

    // Nuevo proyecto
    public void nuevo() {
        proyectoSeleccionado = new Proyecto();
        modoEdicion = false;
    }

    // Editar proyecto
    public void editar(Proyecto proyecto) {
        // Copia los datos para edición
        proyectoSeleccionado = new Proyecto();
        proyectoSeleccionado.setID_Proyecto(proyecto.getID_Proyecto());
        proyectoSeleccionado.setNombre(proyecto.getNombre());
        // Si tienes más atributos, cópialos aquí
        modoEdicion = true;
    }

    // Guardar o actualizar proyecto
    public void guardarOActualizar() {
        if (proyectoSeleccionado.getNombre() == null || proyectoSeleccionado.getNombre().trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nombre del proyecto no puede estar vacío.", null));
            return;
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
}