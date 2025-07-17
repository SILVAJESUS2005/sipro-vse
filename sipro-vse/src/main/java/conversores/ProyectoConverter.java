package conversores;

import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.Converter;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import modelo.Proyecto;

@FacesConverter(value = "proyectoConverter")
public class ProyectoConverter implements Converter<Proyecto> {

    @Override
    public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
        // Este método no puede recuperar la entidad solo con el ID.
        // Debes implementar la lógica en tu bean JSF asociado (por ejemplo, ProyectoBean)
        // Aquí puedes lanzar una excepción o retornar null para evitar errores.
        throw new UnsupportedOperationException("La recuperación del objeto Proyecto debe hacerse en el bean JSF.");
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proyecto proyecto) {
        return (proyecto == null) ? "" : String.valueOf(proyecto.getID_Proyecto());
    }
}