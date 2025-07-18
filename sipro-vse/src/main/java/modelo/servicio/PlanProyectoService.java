package modelo.servicio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import modelo.PlanProyecto;
import modelo.Proyecto;

@Stateless
public class PlanProyectoService {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public List<PlanProyecto> obtenerTodos() {
        TypedQuery<PlanProyecto> query = em.createQuery("SELECT p FROM PlanProyecto p ORDER BY p.fechaAprobacion DESC", PlanProyecto.class);
        return query.getResultList();
    }

    public List<PlanProyecto> obtenerPorProyecto(Proyecto proyecto) {
        TypedQuery<PlanProyecto> query = em.createQuery("SELECT p FROM PlanProyecto p WHERE p.proyecto = :proyecto ORDER BY p.fechaAprobacion DESC", PlanProyecto.class);
        query.setParameter("proyecto", proyecto);
        return query.getResultList();
    }

    public PlanProyecto buscarPorId(int id) {
        return em.find(PlanProyecto.class, id);
    }

    public void guardar(PlanProyecto planProyecto) {
        em.persist(planProyecto);
    }

    public void actualizar(PlanProyecto planProyecto) {
        em.merge(planProyecto);
    }

    public void eliminar(PlanProyecto planProyecto) {
        PlanProyecto p = em.find(PlanProyecto.class, planProyecto.getID_Plan());
        if (p != null) {
            em.remove(p);
        }
    }
}