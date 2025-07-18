package conversores;

import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.Converter;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import jakarta.inject.Inject;
import modelo.Miembro;
import modelo.servicio.MiembroService;

@FacesConverter(value = "miembroConverter", managed = true)
public class MiembroConverter implements Converter<Miembro> {

    @Inject
    private MiembroService miembroService;

    @Override
    public Miembro getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            return miembroService.listarTodos().stream()
                    .filter(m -> m.getID_Miembro() == id)
                    .findFirst()
                    .orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Miembro miembro) {
        return (miembro == null) ? "" : String.valueOf(miembro.getID_Miembro());
    }
}