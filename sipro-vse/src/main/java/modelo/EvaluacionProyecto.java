package modelo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.util.Date;

@Entity
public class EvaluacionProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Evaluacion;

    @ManyToOne
    @NotNull(message = "El proyecto es obligatorio")
    private Proyecto proyecto;

    @NotNull(message = "La fecha de evaluación es obligatoria")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Size(max = 500, message = "El resultado no puede exceder 500 caracteres")
    private String resultado;

    @Size(max = 1000, message = "Los comentarios no pueden exceder 1000 caracteres")
    @Lob
    private String comentarios;

    @Size(max = 1000, message = "Los hallazgos no pueden exceder 1000 caracteres")
    @Lob
    private String hallazgos;

    @Min(value = 0, message = "El porcentaje de avance no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje de avance no puede ser mayor a 100")
    private Double porcentajeAvance;

    @Size(max = 1000, message = "Los riesgos no pueden exceder 1000 caracteres")
    @Lob
    private String riesgos;

    @ManyToOne
    @NotNull(message = "El evaluador es obligatorio")
    private Miembro evaluador;

    // Getters y Setters
    public int getID_Evaluacion() {
        return ID_Evaluacion;
    }

    public void setID_Evaluacion(int ID_Evaluacion) {
        this.ID_Evaluacion = ID_Evaluacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getHallazgos() {
        return hallazgos;
    }

    public void setHallazgos(String hallazgos) {
        this.hallazgos = hallazgos;
    }

    public Double getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(Double porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(String riesgos) {
        this.riesgos = riesgos;
    }

    public Miembro getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Miembro evaluador) {
        this.evaluador = evaluador;
    }
}
