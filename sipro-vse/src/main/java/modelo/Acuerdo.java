package modelo;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Acuerdo {

 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Acuerdo") // <- mapeo explícito
    private int ID_Acuerdo;

    @Column(name = "Nombre_Proyecto")
    private String nombreProyecto;

    @Column(name = "Fecha_Firma")
    private Date fechaFirma;

    @Column(name = "Cliente")
    private String cliente;

    @Column(name = "Entregables_Contratados")
    private String entregablesContratados;

    @Column(name = "Condiciones_Generales")
    private String condicionesGenerales;
    public int getID_Acuerdo() {
        return ID_Acuerdo;
    }

    public void setID_Acuerdo(int ID_Acuerdo) {
        this.ID_Acuerdo = ID_Acuerdo;
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
    
}
