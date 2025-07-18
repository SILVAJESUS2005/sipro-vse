package controlador;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import modelo.Documento;
import modelo.Proyecto;
import modelo.Miembro;
import modelo.enums.TipoDocumento;
import modelo.servicio.DocumentoService;
import modelo.servicio.ProyectoService;
import modelo.servicio.MiembroService;

@Named
@SessionScoped
public class DocumentoBean implements Serializable {

    private Documento documentoSeleccionado = new Documento();
    private Documento documentoAEliminar;
    private List<Documento> documentos;
    private boolean modoEdicion = false;
    private Proyecto proyectoFiltro;
    private String uploadDir = System.getProperty("java.io.tmpdir") + "/sipro-documentos/";

    @Inject
    private DocumentoService documentoService;

    @Inject
    private ProyectoService proyectoService;

    @Inject
    private MiembroService miembroService;

    // Listar documentos
    public List<Documento> getDocumentos() {
        if (proyectoFiltro != null) {
            documentos = documentoService.obtenerPorProyecto(proyectoFiltro);
        } else {
            documentos = documentoService.obtenerTodos();
        }
        return documentos;
    }

    // Nuevo documento
    public void nuevo() {
        documentoSeleccionado = new Documento();
        documentoSeleccionado.setFechaSubida(new Date());
        modoEdicion = false;
    }

    // Editar documento
    public void editar(Documento documento) {
        documentoSeleccionado = new Documento();
        documentoSeleccionado.setID_Documento(documento.getID_Documento());
        documentoSeleccionado.setNombre(documento.getNombre());
        documentoSeleccionado.setDescripcion(documento.getDescripcion());
        documentoSeleccionado.setProyecto(documento.getProyecto());
        documentoSeleccionado.setTipo(documento.getTipo());
        documentoSeleccionado.setRutaArchivo(documento.getRutaArchivo());
        documentoSeleccionado.setTamanoArchivo(documento.getTamanoArchivo());
        documentoSeleccionado.setFechaSubida(documento.getFechaSubida());
        documentoSeleccionado.setSubidoPor(documento.getSubidoPor());
        modoEdicion = true;
    }

    // Manejar subida de archivo
    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        
        if (file != null) {
            try {
                // Crear directorio si no existe
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Generar nombre único para el archivo
                String fileName = System.currentTimeMillis() + "_" + file.getFileName();
                Path filePath = uploadPath.resolve(fileName);

                // Guardar archivo
                Files.copy(file.getInputStream(), filePath);

                // Actualizar información del documento
                documentoSeleccionado.setNombre(file.getFileName());
                documentoSeleccionado.setRutaArchivo(filePath.toString());
                documentoSeleccionado.setTamanoArchivo(file.getSize());
                documentoSeleccionado.setTipo(determinarTipoDocumento(file.getFileName()));

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Archivo subido correctamente: " + file.getFileName(), null));

            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir el archivo: " + e.getMessage(), null));
            }
        }
    }

    // Determinar tipo de documento por extensión
    private TipoDocumento determinarTipoDocumento(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "pdf":
                return TipoDocumento.PDF;
            case "doc":
            case "docx":
                return TipoDocumento.WORD;
            case "xls":
            case "xlsx":
                return TipoDocumento.EXCEL;
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return TipoDocumento.IMAGEN;
            default:
                return TipoDocumento.OTRO;
        }
    }

    // Guardar o actualizar documento
    public void guardarOActualizar() {
        // Validaciones
        if (documentoSeleccionado.getProyecto() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar un proyecto.", null));
            return;
        }

        if (documentoSeleccionado.getSubidoPor() == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe seleccionar quien sube el documento.", null));
            return;
        }

        if (!modoEdicion && (documentoSeleccionado.getRutaArchivo() == null || documentoSeleccionado.getRutaArchivo().isEmpty())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe subir un archivo.", null));
            return;
        }

        if (modoEdicion) {
            documentoService.actualizar(documentoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento actualizado correctamente.", null));
        } else {
            documentoService.guardar(documentoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento guardado correctamente.", null));
        }
        documentos = null; // Recarga la lista
    }

    // Prepara el documento para eliminar
    public void prepararEliminar(Documento documento) {
        documentoAEliminar = documento;
    }

    // Elimina el documento seleccionado
    public void eliminar() {
        if (documentoAEliminar != null) {
            // Eliminar archivo físico
            try {
                Files.deleteIfExists(Paths.get(documentoAEliminar.getRutaArchivo()));
            } catch (IOException e) {
                // Log error but continue with database deletion
            }

            documentoService.eliminar(documentoAEliminar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento eliminado correctamente.", null));
            documentos = null; // Recarga la lista
            documentoAEliminar = null;
        }
    }

    // Getters y Setters
    public Documento getDocumentoSeleccionado() {
        return documentoSeleccionado;
    }

    public void setDocumentoSeleccionado(Documento documentoSeleccionado) {
        this.documentoSeleccionado = documentoSeleccionado;
    }

    public boolean isModoEdicion() {
        return modoEdicion;
    }

    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }

    public Proyecto getProyectoFiltro() {
        return proyectoFiltro;
    }

    public void setProyectoFiltro(Proyecto proyectoFiltro) {
        this.proyectoFiltro = proyectoFiltro;
    }

    // Métodos auxiliares
    public TipoDocumento[] getTiposDocumento() {
        return TipoDocumento.values();
    }

    public List<Proyecto> getProyectos() {
        return proyectoService.obtenerTodos();
    }

    public List<Miembro> getMiembros() {
        return miembroService.listarTodos();
    }

    public void filtrarPorProyecto() {
        documentos = null; // Forzar recarga
    }

    public String formatearTamano(Long tamano) {
        if (tamano == null) return "0 B";
        if (tamano < 1024) return tamano + " B";
        if (tamano < 1024 * 1024) return String.format("%.1f KB", tamano / 1024.0);
        return String.format("%.1f MB", tamano / (1024.0 * 1024.0));
    }
}