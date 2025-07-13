package modelo;

import jakarta.persistence.*;
import java.util.Date;
import modelo.enums.EstadoPlan;

@Entity
public class PlanProyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Plan;

    @ManyToOne
    private Proyecto proyecto;

    @Lob
    private String descripcionSistema;
    @Lob
    private String alcance;
    @Lob
    private String objetivos;
    private String cicloVida;
    private String estrategiaGestionVersiones;
    private String composicionEquipo;
    private String hitos;
    private String riesgos;
    private Date fechaAprobacion;

    @Enumerated(EnumType.STRING)
    private EstadoPlan estado;

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
