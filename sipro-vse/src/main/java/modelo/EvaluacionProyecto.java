package modelo;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class EvaluacionProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Evaluacion;

    @ManyToOne
    private Proyecto proyecto;

    private Date fecha;
    private String resultado;
    private String comentarios;

    @ManyToOne
    private Miembro evaluador;

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

    public Miembro getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Miembro evaluador) {
        this.evaluador = evaluador;
    }
    
    
}
