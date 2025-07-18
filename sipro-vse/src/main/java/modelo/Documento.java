package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import modelo.enums.TipoDocumento;

@Entity
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Documento;

    @NotNull(message = "El nombre del documento es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @NotNull(message = "La ruta del archivo es obligatoria")
    @Size(max = 500, message = "La ruta no puede exceder 500 caracteres")
    private String rutaArchivo;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tipo de documento es obligatorio")
    private TipoDocumento tipo;

    @NotNull(message = "El tamaño del archivo es obligatorio")
    private Long tamanoArchivo;

    @NotNull(message = "La fecha de subida es obligatoria")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubida;

    @ManyToOne
    @NotNull(message = "El proyecto es obligatorio")
    private Proyecto proyecto;

    @ManyToOne
    @NotNull(message = "El usuario que subió el documento es obligatorio")
    private Miembro subidoPor;

    // Getters y Setters
    public int getID_Documento() {
        return ID_Documento;
    }

    public void setID_Documento(int ID_Documento) {
        this.ID_Documento = ID_Documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public Long getTamanoArchivo() {
        return tamanoArchivo;
    }

    public void setTamanoArchivo(Long tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Miembro getSubidoPor() {
        return subidoPor;
    }

    public void setSubidoPor(Miembro subidoPor) {
        this.subidoPor = subidoPor;
    }
}