package modelo;

import jakarta.persistence.*;
import java.util.Date;
import modelo.enums.EstadoDocumento;

@Entity
public class Repositorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Repositorio;

    @ManyToOne
    private Proyecto proyecto;

    private String rutaArchivo;
    private String tipoDocumento;
    private String version;

    @Enumerated(EnumType.STRING)
    private EstadoDocumento estado;

    private Date fechaSubida;

    public int getID_Repositorio() {
        return ID_Repositorio;
    }

    public void setID_Repositorio(int ID_Repositorio) {
        this.ID_Repositorio = ID_Repositorio;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public EstadoDocumento getEstado() {
        return estado;
    }

    public void setEstado(EstadoDocumento estado) {
        this.estado = estado;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
    
    
}
