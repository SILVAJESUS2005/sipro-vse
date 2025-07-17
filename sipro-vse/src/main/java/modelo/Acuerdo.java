package modelo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Acuerdo")
public class Acuerdo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Acuerdo")
    private Integer idAcuerdo;

    @Column(name = "Nombre_Proyecto", nullable = false, unique = true)
    private String nombreProyecto;

    @Column(name = "Fecha_Firma")
    @Temporal(TemporalType.DATE)
    private Date fechaFirma;

    @Column(name = "Cliente", nullable = false)
    private String cliente;

    @Column(name = "Entregables_Contratados")
    private String entregablesContratados;

    @Column(name = "Condiciones_Generales")
    private String condicionesGenerales;

    // Relación con Proyecto
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Proyecto proyecto;

    // Getters y setters
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEntregablesContratados() {
        return entregablesContratados;
    }

    public void setEntregablesContratados(String entregablesContratados) {
        this.entregablesContratados = entregablesContratados;
    }

    public String getCondicionesGenerales() {
        return condicionesGenerales;
    }

    public void setCondicionesGenerales(String condicionesGenerales) {
        this.condicionesGenerales = condicionesGenerales;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
