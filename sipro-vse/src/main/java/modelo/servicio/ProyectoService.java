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

    // Retorna todos los proyectos de la base de datos
    public List<Proyecto> obtenerTodos() {
        TypedQuery<Proyecto> query = em.createQuery("SELECT p FROM Proyecto p", Proyecto.class);
        return query.getResultList();
    }

    // Puedes agregar otros métodos según tus necesidades, como buscar por ID, guardar, eliminar, etc.
    public Proyecto buscarPorId(int id) {
        return em.find(Proyecto.class, id);
    }
}