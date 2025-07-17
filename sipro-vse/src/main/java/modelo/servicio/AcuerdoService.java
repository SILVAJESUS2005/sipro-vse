package modelo.servicio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import modelo.Acuerdo;

@Stateless
public class AcuerdoService {

    @PersistenceContext(unitName = "siproPU")
private EntityManager em;

    public void guardar(Acuerdo acuerdo) {
        em.persist(acuerdo);
    }

    public boolean existeNombre(String nombreProyecto) {
        TypedQuery<Long> query = em.createQuery(
            "SELECT COUNT(a) FROM Acuerdo a WHERE a.nombreProyecto = :nombre", Long.class);
        query.setParameter("nombre", nombreProyecto);
        return query.getSingleResult() > 0;
    }
}