package modelo;

import jakarta.persistence.*;

@Entity
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Riesgo;

    @ManyToOne
    private PlanProyecto plan;

    private String descripcion;
    private String impacto;
    private String probabilidad;
    private String estrategiaMitigacion;

    public int getID_Riesgo() {
        return ID_Riesgo;
    }

    public void setID_Riesgo(int ID_Riesgo) {
        this.ID_Riesgo = ID_Riesgo;
    }

    public PlanProyecto getPlan() {
        return plan;
    }

    public void setPlan(PlanProyecto plan) {
        this.plan = plan;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImpacto() {
        return impacto;
    }

    public void setImpacto(String impacto) {
        this.impacto = impacto;
    }

    public String getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(String probabilidad) {
        this.probabilidad = probabilidad;
    }

    public String getEstrategiaMitigacion() {
        return estrategiaMitigacion;
    }

    public void setEstrategiaMitigacion(String estrategiaMitigacion) {
        this.estrategiaMitigacion = estrategiaMitigacion;
    }
    
    
}
