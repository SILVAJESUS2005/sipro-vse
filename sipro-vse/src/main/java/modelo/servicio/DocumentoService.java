package modelo.servicio;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;
import modelo.Documento;
import modelo.Proyecto;

@Stateless
public class DocumentoService {

    @PersistenceContext(unitName = "siproPU")
    private EntityManager em;

    public List<Documento> obtenerTodos() {
        TypedQuery<Documento> query = em.createQuery("SELECT d FROM Documento d ORDER BY d.fechaSubida DESC", Documento.class);
        return query.getResultList();
    }

    public List<Documento> obtenerPorProyecto(Proyecto proyecto) {
        TypedQuery<Documento> query = em.createQuery("SELECT d FROM Documento d WHERE d.proyecto = :proyecto ORDER BY d.fechaSubida DESC", Documento.class);
        query.setParameter("proyecto", proyecto);
        return query.getResultList();
    }

    public Documento buscarPorId(int id) {
        return em.find(Documento.class, id);
    }

    public void guardar(Documento documento) {
        em.persist(documento);
    }

    public void actualizar(Documento documento) {
        em.merge(documento);
    }

    public void eliminar(Documento documento) {
        Documento d = em.find(Documento.class, documento.getID_Documento());
        if (d != null) {
            em.remove(d);
        }
    }
}