package conversores;

import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.Converter;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Inject;
import modelo.Proyecto;
import modelo.servicio.ProyectoService;

@FacesConverter(value = "proyectoConverter", managed = true)
public class ProyectoConverter implements Converter<Proyecto> {

    @Inject
    private ProyectoService proyectoService;

    @Override
    public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            return proyectoService.buscarPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proyecto proyecto) {
        return (proyecto == null) ? "" : String.valueOf(proyecto.getID_Proyecto());
    }
}
