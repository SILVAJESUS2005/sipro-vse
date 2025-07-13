package modelo.servicio;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import modelo.Miembro;
import java.util.List;

@Stateless
public class MiembroService {
     @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public void crear(Miembro miembro) {
        em.persist(miembro);
    }

    public List<Miembro> listarTodos() {
        return em.createQuery("SELECT m FROM Miembro m", Miembro.class).getResultList();
    }
}
