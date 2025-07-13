package modelo;
import jakarta.persistence.*;
import modelo.enums.EstadoCambio;

@Entity
public class Cambio {
  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID_Cambio;

    @ManyToOne
    private PlanProyecto plan;

    private String descripcion;
    private String motivo;
    private String impactoTecnico;
    private String impactoCosto;

    @Enumerated(EnumType.STRING)
    private EstadoCambio estado;

    public int getID_Cambio() {
        return ID_Cambio;
    }

    public void setID_Cambio(int ID_Cambio) {
        this.ID_Cambio = ID_Cambio;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getImpactoTecnico() {
        return impactoTecnico;
    }

    public void setImpactoTecnico(String impactoTecnico) {
        this.impactoTecnico = impactoTecnico;
    }

    public String getImpactoCosto() {
        return impactoCosto;
    }

    public void setImpactoCosto(String impactoCosto) {
        this.impactoCosto = impactoCosto;
    }

    public EstadoCambio getEstado() {
        return estado;
    }

    public void setEstado(EstadoCambio estado) {
        this.estado = estado;
    }
    
    
}
