package modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class EntregaFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Entrega;

    @ManyToOne
    private Proyecto proyecto;

    private Date fechaEntrega;
    private String productoEntregado;

    @ManyToOne
    private Miembro validador;

    private boolean aceptado;
    private String comentarios;

    public int getID_Entrega() {
        return ID_Entrega;
    }

    public void setID_Entrega(int ID_Entrega) {
        this.ID_Entrega = ID_Entrega;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getProductoEntregado() {
        return productoEntregado;
    }

    public void setProductoEntregado(String productoEntregado) {
        this.productoEntregado = productoEntregado;
    }

    public Miembro getValidador() {
        return validador;
    }

    public void setValidador(Miembro validador) {
        this.validador = validador;
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public void setAceptado(boolean aceptado) {
        this.aceptado = aceptado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

}
