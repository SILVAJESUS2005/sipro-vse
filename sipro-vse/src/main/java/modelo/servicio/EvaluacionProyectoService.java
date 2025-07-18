package modelo.servicio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import modelo.EvaluacionProyecto;
import modelo.Proyecto;

@Stateless
public class EvaluacionProyectoService {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public List<EvaluacionProyecto> obtenerTodos() {
        TypedQuery<EvaluacionProyecto> query = em.createQuery("SELECT e FROM EvaluacionProyecto e ORDER BY e.fecha DESC", EvaluacionProyecto.class);
        return query.getResultList();
    }

    public List<EvaluacionProyecto> obtenerPorProyecto(Proyecto proyecto) {
        TypedQuery<EvaluacionProyecto> query = em.createQuery("SELECT e FROM EvaluacionProyecto e WHERE e.proyecto = :proyecto ORDER BY e.fecha DESC", EvaluacionProyecto.class);
        query.setParameter("proyecto", proyecto);
        return query.getResultList();
    }

    public EvaluacionProyecto buscarPorId(int id) {
        return em.find(EvaluacionProyecto.class, id);
    }

    public void guardar(EvaluacionProyecto evaluacion) {
        em.persist(evaluacion);
    }

    public void actualizar(EvaluacionProyecto evaluacion) {
        em.merge(evaluacion);
    }

    public void eliminar(EvaluacionProyecto evaluacion) {
        EvaluacionProyecto e = em.find(EvaluacionProyecto.class, evaluacion.getID_Evaluacion());
        if (e != null) {
            em.remove(e);
        }
    }
}