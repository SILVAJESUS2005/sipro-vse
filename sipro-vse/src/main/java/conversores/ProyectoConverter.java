package conversores;

import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.Converter;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import modelo.Proyecto;

@FacesConverter(value = "proyectoConverter", managed = true)
public class ProyectoConverter implements Converter<Proyecto> {

    @Inject
    private EntityManager em;

    @Override
    public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        return em.find(Proyecto.class, Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proyecto proyecto) {
        return (proyecto == null || proyecto.getId() == null) ? "" : String.valueOf(proyecto.getId());
    }
}