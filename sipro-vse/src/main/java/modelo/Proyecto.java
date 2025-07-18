package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import modelo.enums.EstadoProyecto;

@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Proyecto;

    @NotNull(message = "El nombre del proyecto es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El cliente es obligatorio")
    @Size(min = 2, max = 100, message = "El cliente debe tener entre 2 y 100 caracteres")
    private String cliente;

    @Size(max = 1000, message = "La descripción no puede exceder 1000 caracteres")
    @Lob
    private String descripcion;

    @Column(name = "Fecha_Inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "Fecha_Fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Enumerated(EnumType.STRING)
    private EstadoProyecto estado;

    // Relación OneToMany con Acuerdos
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Acuerdo> acuerdos;

    @ManyToOne
    @JoinColumn(name = "ID_Responsable")
    private Miembro responsable;

    // Getters y Setters
    public int getID_Proyecto() {
        return ID_Proyecto;
    }

    public void setID_Proyecto(int ID_Proyecto) {
        this.ID_Proyecto = ID_Proyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public EstadoProyecto getEstado() {
        return estado;
    }

    public void setEstado(EstadoProyecto estado) {
        this.estado = estado;
    }

    public List<Acuerdo> getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(List<Acuerdo> acuerdos) {
        this.acuerdos = acuerdos;
    }

    public Miembro getResponsable() {
        return responsable;
    }

    public void setResponsable(Miembro responsable) {
        this.responsable = responsable;
    }
}
