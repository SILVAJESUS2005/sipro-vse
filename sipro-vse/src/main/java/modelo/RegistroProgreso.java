package modelo;

import jakarta.persistence.*;
import java.util.Date;
import modelo.enums.EstadoProgreso;

@Entity
public class RegistroProgreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Registro;

    @ManyToOne
    private Proyecto proyecto;

    private Date fecha;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoProgreso estado;

    @ManyToOne
    private Miembro autor;

    public int getID_Registro() {
        return ID_Registro;
    }

    public void setID_Registro(int ID_Registro) {
        this.ID_Registro = ID_Registro;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProgreso getEstado() {
        return estado;
    }

    public void setEstado(EstadoProgreso estado) {
        this.estado = estado;
    }

    public Miembro getAutor() {
        return autor;
    }

    public void setAutor(Miembro autor) {
        this.autor = autor;
    }
    
    
}
