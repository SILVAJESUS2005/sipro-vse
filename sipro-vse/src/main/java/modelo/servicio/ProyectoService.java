package modelo.servicio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import modelo.Proyecto;

@Stateless
public class ProyectoService {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public List<Proyecto> obtenerTodos() {
        TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
        return query.getResultList();
    }

    public Proyecto buscarPorId(int id) {
        return em.find(Proyecto.class, id);
    }

    public void guardar(Proyecto proyecto) {
        em.persist(proyecto);
    }

    public void actualizar(Proyecto proyecto) {
        em.merge(proyecto);
    }

    public void eliminar(Proyecto proyecto) {
        Proyecto p = em.find(Proyecto.class, proyecto.getID_Proyecto());
        if (p != null) {
            em.remove(p);
        }
    }
}
