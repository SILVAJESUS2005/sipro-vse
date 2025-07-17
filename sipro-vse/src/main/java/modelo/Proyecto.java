package modelo;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Proyecto;

    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        @OneToMany(mappedBy = "proyecto")
    private List<Acuerdo> acuerdos;
    @ManyToOne
    private Acuerdo acuerdo;

    @ManyToOne
    private Miembro responsable;

    public int getID_Proyecto() {
        return ID_Proyecto;
    }
    public Long getId() { return id; }
 
    public void setID_Proyecto(int ID_Proyecto) {
        this.ID_Proyecto = ID_Proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }

    public Miembro getResponsable() {
        return responsable;
    }

    public void setResponsable(Miembro responsable) {
        this.responsable = responsable;
    }
        
}
