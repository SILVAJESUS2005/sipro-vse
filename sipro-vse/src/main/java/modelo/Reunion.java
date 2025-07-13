package modelo;
import jakarta.persistence.*;
import java.util.Date;
import modelo.enums.TipoReunion;

@Entity
public class Reunion {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Reunion;

    @ManyToOne
    private Proyecto proyecto;

    private Date fecha;

    @Enumerated(EnumType.STRING)
    private TipoReunion tipo;

    private String resumen;
    private String conclusiones;

    @ManyToOne
    private Miembro responsable;

    public int getID_Reunion() {
        return ID_Reunion;
    }

    public void setID_Reunion(int ID_Reunion) {
        this.ID_Reunion = ID_Reunion;
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

    public TipoReunion getTipo() {
        return tipo;
    }

    public void setTipo(TipoReunion tipo) {
        this.tipo = tipo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public Miembro getResponsable() {
        return responsable;
    }

    public void setResponsable(Miembro responsable) {
        this.responsable = responsable;
    }
    
    
  
}
