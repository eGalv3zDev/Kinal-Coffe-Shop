package org.asco_devs.kinalcoffeeshop.web.views;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.CategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.dto.categoria.ModCategoriaDto;
import org.asco_devs.kinalcoffeeshop.dominio.service.CategoriaService;
import org.asco_devs.kinalcoffeeshop.persistence.entity.CategoriaEntity;
import org.asco_devs.kinalcoffeeshop.persistence.mapper.CategoriaMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Named("categoriaControllerWeb")
@ViewScoped
@Getter
@Setter
public class CategoriaControllerWeb implements Serializable {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    private List<CategoriaDto> categorias;
    private CategoriaEntity categoriaSeleccionada;

    @Inject
    public CategoriaControllerWeb(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    public void prepararParaEliminar(CategoriaDto categoriaDto) {
        this.categoriaParaEliminar = categoriaDto;
    }

    @PostConstruct
    public void init() {
        cargarCategorias();
        this.categoriaSeleccionada = new CategoriaEntity();
        this.categoriaParaEliminar = new CategoriaDto(0L, "");
    }

    private void cargarCategorias() {
        this.categorias = categoriaService.obtenerCategorias();
    }

    public void prepararNuevaCategoria() {
        this.categoriaSeleccionada = new CategoriaEntity();
    }

    public void prepararEdicion(CategoriaDto categoriaDto) {
        this.categoriaSeleccionada = categoriaMapper.toEntity(categoriaDto);
    }

    public void guardarCategoria() {
        try {
            if (this.categoriaSeleccionada.getIdCategoria() == null) {
                CategoriaDto dto = categoriaMapper.toDto(this.categoriaSeleccionada);
                categoriaService.guardarCategoria(dto);
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría creada correctamente.");
            } else {
                ModCategoriaDto modDto = new ModCategoriaDto(this.categoriaSeleccionada.getNombre());
                categoriaService.modificarCategoria(this.categoriaSeleccionada.getIdCategoria(), modDto);
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría actualizada correctamente.");
            }
            cargarCategorias();
            prepararNuevaCategoria();
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la categoría: " + e.getMessage());
        }
    }

    public void eliminarCategoria() {
        try {
            if (this.categoriaParaEliminar != null && this.categoriaParaEliminar.id() != null) {
                categoriaService.eliminarCategoria(this.categoriaParaEliminar.id());
                agregarMensaje(FacesMessage.SEVERITY_INFO, "Éxito", "Categoría eliminada correctamente.");
                cargarCategorias();
                this.categoriaParaEliminar = null; // Limpiar después de usar
            } else {
                agregarMensaje(FacesMessage.SEVERITY_WARN, "Atención", "No se ha seleccionado ninguna categoría para eliminar.");
            }
        } catch (Exception e) {
            agregarMensaje(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la categoría: " + e.getMessage());
        }
    }

    private void agregarMensaje(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    @Getter
    @Setter
    private CategoriaDto categoriaParaEliminar;
}