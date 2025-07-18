package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import modelo.enums.EstadoPlan;

@Entity
public class PlanProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Plan;

    @ManyToOne
    @NotNull(message = "El proyecto es obligatorio")
    private Proyecto proyecto;

    @Lob
    @Size(max = 2000, message = "La descripción del sistema no puede exceder 2000 caracteres")
    private String descripcionSistema;
    
    @Lob
    @Size(max = 2000, message = "El alcance no puede exceder 2000 caracteres")
    private String alcance;
    
    @Lob
    @NotNull(message = "Los objetivos son obligatorios")
    @Size(max = 2000, message = "Los objetivos no pueden exceder 2000 caracteres")
    private String objetivos;
    
    @Size(max = 500, message = "El ciclo de vida no puede exceder 500 caracteres")
    private String cicloVida;
    
    @Size(max = 500, message = "La estrategia de gestión de versiones no puede exceder 500 caracteres")
    private String estrategiaGestionVersiones;
    
    @Size(max = 1000, message = "La composición del equipo no puede exceder 1000 caracteres")
    private String composicionEquipo;
    
    @Lob
    @Size(max = 2000, message = "Los hitos no pueden exceder 2000 caracteres")
    private String hitos;
    
    @Lob
    @Size(max = 2000, message = "Los riesgos no pueden exceder 2000 caracteres")
    private String riesgos;

    // Nuevos campos para PM.BP2.D2
    @Lob
    @Size(max = 2000, message = "El cronograma no puede exceder 2000 caracteres")
    private String cronograma;

    @Size(max = 1000, message = "Los responsables no pueden exceder 1000 caracteres")
    private String responsables;

    @Lob
    @Size(max = 2000, message = "Los recursos no pueden exceder 2000 caracteres")
    private String recursos;

    @Temporal(TemporalType.DATE)
    private Date fechaAprobacion;

    @Enumerated(EnumType.STRING)
    private EstadoPlan estado;

    // Getters y Setters
    public int getID_Plan() {
        return ID_Plan;
    }

    public void setID_Plan(int ID_Plan) {
        this.ID_Plan = ID_Plan;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getDescripcionSistema() {
        return descripcionSistema;
    }

    public void setDescripcionSistema(String descripcionSistema) {
        this.descripcionSistema = descripcionSistema;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getCicloVida() {
        return cicloVida;
    }

    public void setCicloVida(String cicloVida) {
        this.cicloVida = cicloVida;
    }

    public String getEstrategiaGestionVersiones() {
        return estrategiaGestionVersiones;
    }

    public void setEstrategiaGestionVersiones(String estrategiaGestionVersiones) {
        this.estrategiaGestionVersiones = estrategiaGestionVersiones;
    }

    public String getComposicionEquipo() {
        return composicionEquipo;
    }

    public void setComposicionEquipo(String composicionEquipo) {
        this.composicionEquipo = composicionEquipo;
    }

    public String getHitos() {
        return hitos;
    }

    public void setHitos(String hitos) {
        this.hitos = hitos;
    }

    public String getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(String riesgos) {
        this.riesgos = riesgos;
    }

    public String getCronograma() {
        return cronograma;
    }

    public void setCronograma(String cronograma) {
        this.cronograma = cronograma;
    }

    public String getResponsables() {
        return responsables;
    }

    public void setResponsables(String responsables) {
        this.responsables = responsables;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }
}
