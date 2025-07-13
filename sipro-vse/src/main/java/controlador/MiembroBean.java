package controlador;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import modelo.Miembro;
import modelo.enums.Rol;
import modelo.servicio.MiembroService;
import java.util.List;

@Named
@RequestScoped
public class MiembroBean {
   @Inject
    private MiembroService miembroService;

    private Miembro miembro = new Miembro();

    public void registrar() {
        miembroService.crear(miembro);
        miembro = new Miembro(); // limpiar campos
    }

    public List<Miembro> getMiembros() {
        return miembroService.listarTodos();
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public void setMiembro(Miembro miembro) {
        this.miembro = miembro;
    }

    public Rol[] getRoles() {
        return Rol.values();
    } 
}
