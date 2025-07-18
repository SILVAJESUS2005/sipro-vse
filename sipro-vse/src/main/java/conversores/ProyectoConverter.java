package conversores;

import jakarta.faces.convert.FacesConverter;
import jakarta.faces.convert.Converter;
import jakarta.faces.context.FacesContext;
import jakarta.faces.component.UIComponent;
import controlador.AcuerdoBean;
import modelo.Proyecto;

@FacesConverter(value = "proyectoConverter")
public class ProyectoConverter implements Converter<Proyecto> {

    @Override
    public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            int id = Integer.parseInt(value);
            AcuerdoBean bean = context.getApplication()
                    .evaluateExpressionGet(context, "#{acuerdoBean}", AcuerdoBean.class);
            return bean.buscarProyectoPorId(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proyecto proyecto) {
        return (proyecto == null) ? "" : String.valueOf(proyecto.getID_Proyecto());
    }
}
